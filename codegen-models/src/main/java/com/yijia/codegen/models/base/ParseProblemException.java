package com.yijia.codegen.models.base;

import java.util.List;
import static com.yijia.codegen.models.base.utils.Utils.EOL;
import static com.yijia.codegen.models.base.utils.Utils.assertNotNull;
import static java.util.Collections.singletonList;

/**
 * Thrown when parsing problems occur during parsing with the static methods on JavaParser.
 */
public class ParseProblemException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6482429074816628989L;

	/**
	 * The problems that were encountered during parsing
	 */
	private final List<Problem> problems;

	public ParseProblemException(List<Problem> problems) {
		super(createMessage(assertNotNull(problems)));
		this.problems = problems;
	}

	public ParseProblemException(Throwable throwable) {
		this(singletonList(new Problem(throwable.getMessage(), null, throwable)));
	}

	private static String createMessage(List<Problem> problems) {
		StringBuilder message = new StringBuilder();
		for (Problem problem : problems) {
			message.append(problem.toString()).append(EOL);
		}
		return message.toString();
	}

	public List<Problem> getProblems() {
		return problems;
	}
}
