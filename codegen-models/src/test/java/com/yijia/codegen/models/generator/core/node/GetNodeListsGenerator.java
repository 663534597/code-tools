package com.yijia.codegen.models.generator.core.node;

import static com.yijia.codegen.models.base.JavaParser.parseBodyDeclaration;
import static com.yijia.codegen.models.base.utils.CodeGenerationUtils.f;
import com.yijia.codegen.models.base.ast.CompilationUnit;
import com.yijia.codegen.models.base.ast.body.ClassOrInterfaceDeclaration;
import com.yijia.codegen.models.base.ast.body.MethodDeclaration;
import com.yijia.codegen.models.base.metamodel.BaseNodeMetaModel;
import com.yijia.codegen.models.base.metamodel.PropertyMetaModel;
import com.yijia.codegen.models.base.utils.SeparatedItemStringBuilder;
import com.yijia.codegen.models.base.utils.SourceRoot;
import com.yijia.codegen.models.generator.NodeGenerator;

public class GetNodeListsGenerator extends NodeGenerator {
	public GetNodeListsGenerator(SourceRoot sourceRoot) {
		super(sourceRoot);
	}

	@Override
	protected void generateNode(BaseNodeMetaModel nodeMetaModel, CompilationUnit nodeCu, ClassOrInterfaceDeclaration nodeCoid) {
		if (nodeMetaModel.isAbstract()) {
			return;
		}

		SeparatedItemStringBuilder statement = new SeparatedItemStringBuilder("return Arrays.asList(", ",", ");");
		for (PropertyMetaModel property : nodeMetaModel.getAllPropertyMetaModels()) {
			if (property.isNodeList()) {
				if (property.isOptional()) {
					statement.append(f("%s().orElse(null)", property.getGetterMethodName()));
				} else {
					statement.append(f("%s()", property.getGetterMethodName()));
				}
			}
		}

		if (!statement.hasItems()) {
			return;
		}

		final MethodDeclaration getNodeListsMethod = (MethodDeclaration) parseBodyDeclaration(f("@Override public List<NodeList<?>> getNodeLists() {%s}", statement));
		addOrReplaceWhenSameSignature(nodeCoid, getNodeListsMethod);
		annotateGenerated(getNodeListsMethod);
	}
}
