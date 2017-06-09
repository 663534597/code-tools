package com.yijia.codegen.models.base.ast.validator;

import com.yijia.codegen.models.base.ast.Node;

/**
 * Stub validator for when no validation is wanted.
 */
public final class NoProblemsValidator implements Validator {
	@Override
	public void accept(Node node, ProblemReporter problemReporter) {}
}
