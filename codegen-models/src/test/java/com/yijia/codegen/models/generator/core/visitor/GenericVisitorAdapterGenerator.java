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
public class GenericVisitorAdapterGenerator extends VisitorGenerator {
	public GenericVisitorAdapterGenerator(SourceRoot sourceRoot) {
		super(sourceRoot, "com.yijia.codegen.models.base.ast.visitor", "GenericVisitorAdapter", "R", "A", true);
	}

	@Override
	protected void generateVisitMethodBody(BaseNodeMetaModel node, MethodDeclaration visitMethod, CompilationUnit compilationUnit) {
		BlockStmt body = visitMethod.getBody().get();
		body.getStatements().clear();

		body.addStatement("R result;");

		final String resultCheck = "if (result != null) return result;";

		for (PropertyMetaModel field : node.getAllPropertyMetaModels()) {
			final String getter = field.getGetterMethodName() + "()";
			if (field.getNodeReference().isPresent()) {
				if (field.isOptional()) {
					body.addStatement(f("if (n.%s.isPresent()) {" + "   result = n.%s.get().accept(this, arg);" + "   %s" + "}", getter, getter, resultCheck));
				} else {
					body.addStatement(f("{ result = n.%s.accept(this, arg); %s }", getter, resultCheck));
				}
			}
		}
		body.addStatement("return null;");
	}
}
