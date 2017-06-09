package com.yijia.codegen.models.base.ast.validator;

import com.yijia.codegen.models.base.ast.body.ClassOrInterfaceDeclaration;
import com.yijia.codegen.models.base.ast.stmt.LocalClassDeclarationStmt;

/**
 * This validator validates according to Java 1.1 syntax rules.
 */
public class Java1_1Validator extends Java1_0Validator {
	protected final Validator innerClasses = new SingleNodeTypeValidator<>(ClassOrInterfaceDeclaration.class, (n, reporter) -> n.getParentNode().ifPresent(p -> {
		if (p instanceof LocalClassDeclarationStmt && n.isInterface())
			reporter.report(n, "There is no such thing as a local interface.");
	}));

	public Java1_1Validator() {
		super();
		replace(noInnerClasses, innerClasses);
		remove(noReflection);
	}
}
