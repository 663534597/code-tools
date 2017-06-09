package com.yijia.codegen.models.base.ast.validator;

import java.util.function.BiConsumer;
import com.yijia.codegen.models.base.ast.Node;

/**
 * A validator that validates a known node type.
 */
public interface TypedValidator<N extends Node> extends BiConsumer<N, ProblemReporter> {
	/**
	 * @param node the node that wants to be validated
	 * @param problemReporter when found, validation errors can be reported here
	 */
	void accept(N node, ProblemReporter problemReporter);
}
