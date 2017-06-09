package com.yijia.codegen.models.generator.core.visitor;

import com.yijia.codegen.models.base.ast.CompilationUnit;
import com.yijia.codegen.models.base.ast.body.MethodDeclaration;
import com.yijia.codegen.models.base.metamodel.BaseNodeMetaModel;
import com.yijia.codegen.models.base.utils.SourceRoot;
import com.yijia.codegen.models.generator.VisitorGenerator;

/**
 * Generates JavaParser's VoidVisitor.
 */
public class VoidVisitorGenerator extends VisitorGenerator {
	public VoidVisitorGenerator(SourceRoot sourceRoot) {
		super(sourceRoot, "com.yijia.codegen.models.base.ast.visitor", "VoidVisitor", "void", "A", true);
	}

	@Override
	protected void generateVisitMethodBody(BaseNodeMetaModel node, MethodDeclaration visitMethod, CompilationUnit compilationUnit) {
		visitMethod.setBody(null);
	}
}
