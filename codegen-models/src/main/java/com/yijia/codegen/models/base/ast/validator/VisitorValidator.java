package com.yijia.codegen.models.base.ast.validator;

import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.visitor.VoidVisitorAdapter;

/**
 * A validator that uses a visitor for validation. This class is the visitor too. Implement the "visit" methods you want to use for validation.
 */
public abstract class VisitorValidator extends VoidVisitorAdapter<ProblemReporter> implements Validator {
	@Override
	public void accept(Node node, ProblemReporter problemReporter) {
		node.accept(this, problemReporter);
	}
}
