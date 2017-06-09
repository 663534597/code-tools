package com.yijia.codegen.models.generator.core.visitor;

import static com.yijia.codegen.models.base.utils.CodeGenerationUtils.f;
import com.yijia.codegen.models.base.ast.CompilationUnit;
import com.yijia.codegen.models.base.ast.body.MethodDeclaration;
import com.yijia.codegen.models.base.ast.stmt.BlockStmt;
import com.yijia.codegen.models.base.metamodel.BaseNodeMetaModel;
import com.yijia.codegen.models.base.metamodel.PropertyMetaModel;
import com.yijia.codegen.models.base.utils.SourceRoot;
import com.yijia.codegen.models.generator.VisitorGenerator;

/**
 * Generates JavaParser's VoidVisitorAdapter.
 */
public class VoidVisitorAdapterGenerator extends VisitorGenerator {
	public VoidVisitorAdapterGenerator(SourceRoot sourceRoot) {
		super(sourceRoot, "com.yijia.codegen.models.base.ast.visitor", "VoidVisitorAdapter", "void", "A", true);
	}

	@Override
	protected void generateVisitMethodBody(BaseNodeMetaModel node, MethodDeclaration visitMethod, CompilationUnit compilationUnit) {
		BlockStmt body = visitMethod.getBody().get();
		body.getStatements().clear();

		for (PropertyMetaModel field : node.getAllPropertyMetaModels()) {
			final String getter = field.getGetterMethodName() + "()";
			if (field.getNodeReference().isPresent()) {
				if (field.isOptional() && field.isNodeList()) {
					body.addStatement(f("n.%s.ifPresent( l -> l.forEach( v -> v.accept(this, arg)));", getter));
				} else if (field.isOptional()) {
					body.addStatement(f("n.%s.ifPresent(l -> l.accept(this, arg));", getter));
				} else if (field.isNodeList()) {
					body.addStatement(f("n.%s.forEach(p -> p.accept(this, arg));", getter));
				} else {
					body.addStatement(f("n.%s.accept(this, arg);", getter));
				}
			}
		}
	}
}
