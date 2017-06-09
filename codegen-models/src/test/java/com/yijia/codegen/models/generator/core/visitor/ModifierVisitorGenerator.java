package com.yijia.codegen.models.generator.core.visitor;

import static com.yijia.codegen.models.base.utils.CodeGenerationUtils.f;
import com.yijia.codegen.models.base.ast.CompilationUnit;
import com.yijia.codegen.models.base.ast.body.MethodDeclaration;
import com.yijia.codegen.models.base.ast.expr.BinaryExpr;
import com.yijia.codegen.models.base.ast.stmt.BlockStmt;
import com.yijia.codegen.models.base.metamodel.BaseNodeMetaModel;
import com.yijia.codegen.models.base.metamodel.PropertyMetaModel;
import com.yijia.codegen.models.base.utils.SeparatedItemStringBuilder;
import com.yijia.codegen.models.base.utils.SourceRoot;
import com.yijia.codegen.models.generator.VisitorGenerator;

public class ModifierVisitorGenerator extends VisitorGenerator {
	public ModifierVisitorGenerator(SourceRoot sourceRoot) {
		super(sourceRoot, "com.yijia.codegen.models.base.ast.visitor", "ModifierVisitor", "Visitable", "A", true);
	}

	@Override
	protected void generateVisitMethodBody(BaseNodeMetaModel node, MethodDeclaration visitMethod, CompilationUnit compilationUnit) {
		BlockStmt body = visitMethod.getBody().get();
		body.getStatements().clear();

		for (PropertyMetaModel property : node.getAllPropertyMetaModels()) {
			if (property.isNode()) {
				if (property.isNodeList()) {
					body.addStatement(f("NodeList<%s> %s = modifyList(n.%s(), arg);", property.getTypeNameGenerified(), property.getName(), property.getGetterMethodName()));
				} else if (property.isOptional()) {
					body.addStatement(f("%s %s = n.%s().map(s -> (%s) s.accept(this, arg)).orElse(null);", property.getTypeNameGenerified(), property.getName(),
							property.getGetterMethodName(), property.getTypeNameGenerified()));
				} else {
					body.addStatement(f("%s %s = (%s) n.%s().accept(this, arg);", property.getTypeNameGenerified(), property.getName(), property.getTypeNameGenerified(),
							property.getGetterMethodName()));
				}
			}
		}

		if (node.is(BinaryExpr.class)) {
			body.addStatement("if (left == null) return right;");
			body.addStatement("if (right == null) return left;");
		} else {
			final SeparatedItemStringBuilder collapseCheck = new SeparatedItemStringBuilder("if(", "||", ") return null;");
			for (PropertyMetaModel property : node.getAllPropertyMetaModels()) {
				if (property.isRequired() && property.isNode()) {
					if (property.isNodeList()) {
						if (property.isNonEmpty()) {
							collapseCheck.append(f("%s.isEmpty()", property.getName()));
						}
					} else {
						collapseCheck.append(f("%s==null", property.getName()));
					}
				}
			}
			if (collapseCheck.hasItems()) {
				body.addStatement(collapseCheck.toString());
			}
		}

		for (PropertyMetaModel property : node.getAllPropertyMetaModels()) {
			if (property.isNode()) {
				body.addStatement(f("n.%s(%s);", property.getSetterMethodName(), property.getName()));
			}
		}
		body.addStatement("return n;");
	}
}
