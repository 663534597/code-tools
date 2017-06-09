package com.yijia.codegen.models.generator.core.node;

import static com.yijia.codegen.models.base.JavaParser.parseBodyDeclaration;
import static com.yijia.codegen.models.base.utils.CodeGenerationUtils.f;
import static com.yijia.codegen.models.base.utils.Utils.capitalize;
import com.yijia.codegen.models.base.ast.CompilationUnit;
import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.body.ClassOrInterfaceDeclaration;
import com.yijia.codegen.models.base.ast.body.MethodDeclaration;
import com.yijia.codegen.models.base.ast.stmt.BlockStmt;
import com.yijia.codegen.models.base.metamodel.BaseNodeMetaModel;
import com.yijia.codegen.models.base.metamodel.PropertyMetaModel;
import com.yijia.codegen.models.base.utils.SourceRoot;
import com.yijia.codegen.models.generator.NodeGenerator;

public class RemoveMethodGenerator extends NodeGenerator {
	public RemoveMethodGenerator(SourceRoot sourceRoot) {
		super(sourceRoot);
	}

	@Override
	protected void generateNode(BaseNodeMetaModel nodeMetaModel, CompilationUnit nodeCu, ClassOrInterfaceDeclaration nodeCoid) {
		MethodDeclaration removeNodeMethod = (MethodDeclaration) parseBodyDeclaration("public boolean remove(Node node) {}");
		nodeCu.addImport(Node.class);
		nodeMetaModel.getSuperNodeMetaModel().ifPresent(s -> annotateOverridden(removeNodeMethod));

		final BlockStmt body = removeNodeMethod.getBody().get();

		body.addStatement("if (node == null) return false;");

		for (PropertyMetaModel property : nodeMetaModel.getDeclaredPropertyMetaModels()) {
			if (!property.isNode()) {
				continue;
			}
			String check;
			if (property.isNodeList()) {
				check = nodeListCheck(property);
			} else {
				if (property.isRequired()) {
					continue;
				}
				String removeAttributeMethodName = generateRemoveMethodForAttribute(nodeCoid, nodeMetaModel, property);
				check = attributeCheck(property, removeAttributeMethodName);
			}
			if (property.isOptional()) {
				check = f("if (%s != null) { %s }", property.getName(), check);
			}
			body.addStatement(check);
		}
		if (nodeMetaModel.getSuperNodeMetaModel().isPresent()) {
			body.addStatement("return super.remove(node);");
		} else {
			body.addStatement("return false;");
		}

		addOrReplaceWhenSameSignature(nodeCoid, removeNodeMethod);
		annotateGenerated(removeNodeMethod);
	}

	private String attributeCheck(PropertyMetaModel property, String removeAttributeMethodName) {
		return f("if (node == %s) {" + "    %s();" + "    return true;\n" + "}", property.getName(), removeAttributeMethodName);
	}

	private String nodeListCheck(PropertyMetaModel property) {
		return f("for (int i = 0; i < %s.size(); i++) {" + "  if (%s.get(i) == node) {" + "    %s.remove(i);" + "    return true;" + "  }" + "}", property.getName(),
				property.getName(), property.getName());
	}

	private String generateRemoveMethodForAttribute(ClassOrInterfaceDeclaration nodeCoid, BaseNodeMetaModel nodeMetaModel, PropertyMetaModel property) {
		final String methodName = "remove" + capitalize(property.getName());
		final MethodDeclaration removeMethod = (MethodDeclaration) parseBodyDeclaration(f("public %s %s() {}", nodeMetaModel.getTypeName(), methodName));

		final BlockStmt block = removeMethod.getBody().get();
		block.addStatement(f("return %s((%s) null);", property.getSetterMethodName(), property.getTypeNameForSetter()));

		addOrReplaceWhenSameSignature(nodeCoid, removeMethod);
		annotateGenerated(removeMethod);
		return methodName;
	}
}
