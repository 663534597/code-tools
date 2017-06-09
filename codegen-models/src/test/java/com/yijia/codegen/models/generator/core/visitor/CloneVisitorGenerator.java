package com.yijia.codegen.models.generator.core.visitor;

import static com.yijia.codegen.models.base.utils.CodeGenerationUtils.f;
import com.yijia.codegen.models.base.ast.CompilationUnit;
import com.yijia.codegen.models.base.ast.body.MethodDeclaration;
import com.yijia.codegen.models.base.ast.stmt.BlockStmt;
import com.yijia.codegen.models.base.metamodel.BaseNodeMetaModel;
import com.yijia.codegen.models.base.metamodel.PropertyMetaModel;
import com.yijia.codegen.models.base.utils.SeparatedItemStringBuilder;
import com.yijia.codegen.models.base.utils.SourceRoot;
import com.yijia.codegen.models.generator.VisitorGenerator;

/**
 * Generates JavaParser's CloneVisitor.
 */
public class CloneVisitorGenerator extends VisitorGenerator {
	public CloneVisitorGenerator(SourceRoot sourceRoot) {
		super(sourceRoot, "com.yijia.codegen.models.base.ast.visitor", "CloneVisitor", "Visitable", "Object", true);
	}

	@Override
	protected void generateVisitMethodBody(BaseNodeMetaModel node, MethodDeclaration visitMethod, CompilationUnit compilationUnit) {
		BlockStmt body = visitMethod.getBody().get();
		body.getStatements().clear();

		for (PropertyMetaModel field : node.getAllPropertyMetaModels()) {
			final String getter = field.getGetterMethodName() + "()";
			if (field.getNodeReference().isPresent()) {
				if (field.isOptional() && field.isNodeList()) {
					body.addStatement(f("NodeList<%s> %s = cloneList(n.%s.orElse(null), arg);", field.getTypeNameGenerified(), field.getName(), getter));
				} else if (field.isNodeList()) {
					body.addStatement(f("NodeList<%s> %s = cloneList(n.%s, arg);", field.getTypeNameGenerified(), field.getName(), getter));
				} else {
					body.addStatement(f("%s %s = cloneNode(n.%s, arg);", field.getTypeNameGenerified(), field.getName(), getter));
				}
			}
		}

		SeparatedItemStringBuilder builder = new SeparatedItemStringBuilder(f("%s r = new %s(", node.getTypeNameGenerified(), node.getTypeNameGenerified()), ",", ");");
		builder.append("n.getTokenRange().orElse(null)");
		for (PropertyMetaModel field : node.getConstructorParameters()) {
			if (field.getName().equals("comment")) {
				continue;
			}
			if (field.getNodeReference().isPresent()) {
				builder.append(field.getName());
			} else {
				builder.append(f("n.%s()", field.getGetterMethodName()));
			}
		}

		body.addStatement(builder.toString());
		body.addStatement("r.setComment(comment);");
		body.addStatement("return r;");
	}
}
