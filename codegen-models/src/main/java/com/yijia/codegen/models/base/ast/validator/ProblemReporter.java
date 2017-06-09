package com.yijia.codegen.models.base.ast.validator;

import static com.yijia.codegen.models.base.utils.CodeGenerationUtils.f;
import java.util.List;
import com.yijia.codegen.models.base.Problem;
import com.yijia.codegen.models.base.TokenRange;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithTokenRange;

/**
 * A simple interface where validators can report found problems.
 */
public class ProblemReporter {
	private final List<Problem> problems;

	public ProblemReporter(List<Problem> problems) {
		this.problems = problems;
	}

	/**
	 * Report a problem.
	 * @param message description of the problem
	 * @param node the node in which the problem occurred, used to find the Range of the problem.
	 */
	public void report(NodeWithTokenRange<?> node, String message, Object... args) {
		report(node.getTokenRange().orElse(null), message, args);
	}

	public void report(TokenRange range, String message, Object... args) {
		problems.add(new Problem(f(message, args), range, null));
	}
}
