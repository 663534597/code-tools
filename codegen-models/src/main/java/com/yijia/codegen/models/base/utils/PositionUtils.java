package com.yijia.codegen.models.base.utils;

import static java.lang.Integer.signum;

import java.util.List;

import com.yijia.codegen.models.base.Position;
import com.yijia.codegen.models.base.Range;
import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.NodeList;
import com.yijia.codegen.models.base.ast.body.ClassOrInterfaceDeclaration;
import com.yijia.codegen.models.base.ast.body.FieldDeclaration;
import com.yijia.codegen.models.base.ast.body.MethodDeclaration;
import com.yijia.codegen.models.base.ast.expr.AnnotationExpr;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithAnnotations;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithType;
import com.yijia.codegen.models.base.ast.type.Type;

public final class PositionUtils {

	private PositionUtils() {
		// prevent instantiation
	}

	public static <T extends Node> void sortByBeginPosition(List<T> nodes) {
		sortByBeginPosition(nodes, false);
	}

	public static <T extends Node> void sortByBeginPosition(NodeList<T> nodes) {
		sortByBeginPosition(nodes, false);
	}

	public static <T extends Node> void sortByBeginPosition(List<T> nodes, final boolean ignoringAnnotations) {
		nodes.sort((o1, o2) -> PositionUtils.compare(o1, o2, ignoringAnnotations));
	}

	public static boolean areInOrder(Node a, Node b) {
		return areInOrder(a, b, false);
	}

	public static boolean areInOrder(Node a, Node b, boolean ignoringAnnotations) {
		return compare(a, b, ignoringAnnotations) <= 0;
	}

	private static int compare(Node a, Node b, boolean ignoringAnnotations) {
		if (!a.getRange().isPresent() || !b.getRange().isPresent()) {
			return 0;
		}
		if (ignoringAnnotations) {
			int signLine = signum(beginLineWithoutConsideringAnnotation(a) - beginLineWithoutConsideringAnnotation(b));
			if (signLine == 0) {
				return signum(beginColumnWithoutConsideringAnnotation(a) - beginColumnWithoutConsideringAnnotation(b));
			} else {
				return signLine;
			}
		}

		Position aBegin = a.getBegin().get();
		Position bBegin = b.getBegin().get();

		int signLine = signum(aBegin.line - bBegin.line);
		if (signLine == 0) {
			return signum(aBegin.column - bBegin.column);
		} else {
			return signLine;
		}
	}

	public static AnnotationExpr getLastAnnotation(Node node) {
		if (node instanceof NodeWithAnnotations) {
			NodeList<AnnotationExpr> annotations = NodeList.nodeList(((NodeWithAnnotations<?>) node).getAnnotations());
			if (annotations.isEmpty()) {
				return null;
			}
			sortByBeginPosition(annotations);
			return annotations.get(annotations.size() - 1);
		} else {
			return null;
		}
	}

	private static int beginLineWithoutConsideringAnnotation(Node node) {
		return beginNodeWithoutConsideringAnnotations(node).getRange().get().begin.line;
	}

	private static int beginColumnWithoutConsideringAnnotation(Node node) {
		return beginNodeWithoutConsideringAnnotations(node).getRange().get().begin.column;
	}

	private static Node beginNodeWithoutConsideringAnnotations(Node node) {
		if (node instanceof MethodDeclaration || node instanceof FieldDeclaration) {
			@SuppressWarnings("unchecked")
			NodeWithType<?, Type> casted = (NodeWithType<?, Type>) node;
			return casted.getType();
		} else if (node instanceof ClassOrInterfaceDeclaration) {
			ClassOrInterfaceDeclaration casted = (ClassOrInterfaceDeclaration) node;
			return casted.getName();
		} else {
			return node;
		}
	}

	public static boolean nodeContains(Node container, Node contained, boolean ignoringAnnotations) {
		final Range containedRange = contained.getRange().get();
		final Range containerRange = container.getRange().get();
		if (!ignoringAnnotations || PositionUtils.getLastAnnotation(container) == null) {
			return container.containsWithin(contained);
		}
		if (!container.containsWithin(contained)) {
			return false;
		}
		// if the node is contained, but it comes immediately after the annotations,
		// let's not consider it contained
		if (container instanceof NodeWithAnnotations) {
			int bl = beginLineWithoutConsideringAnnotation(container);
			int bc = beginColumnWithoutConsideringAnnotation(container);
			if (bl > containedRange.begin.line)
				return false;
			if (bl == containedRange.begin.line && bc > containedRange.begin.column)
				return false;
			if (containerRange.end.line < containedRange.end.line)
				return false;
			// TODO < or <= ?
			return !(containerRange.end.line == containedRange.end.line && containerRange.end.column < containedRange.end.column);
		}
		return true;
	}

}
