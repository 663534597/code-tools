package com.yijia.codegen.models.base.ast.nodeTypes;

import java.util.Optional;
import com.yijia.codegen.models.base.Position;
import com.yijia.codegen.models.base.Range;
import com.yijia.codegen.models.base.ast.Node;

/**
 * A node that has a Range, which is every Node.
 */
public interface NodeWithRange<N> {
	Optional<Range> getRange();

	N setRange(Range range);

	/**
	 * The begin position of this node in the source file.
	 */
	default Optional<Position> getBegin() {
		return getRange().map(r -> r.begin);
	}

	/**
	 * The end position of this node in the source file.
	 */
	default Optional<Position> getEnd() {
		return getRange().map(r -> r.end);
	}

	default boolean containsWithin(Node other) {
		if (getRange().isPresent() && other.getRange().isPresent()) {
			return getRange().get().contains(other.getRange().get());
		}
		return false;
	}

	/**
	 * @deprecated use isAfter() on range
	 */
	@Deprecated
	default boolean isPositionedAfter(Position position) {
		return getRange().map(r -> r.isAfter(position)).orElse(false);
	}

	/**
	 * @deprecated use isBefore() on range
	 */
	@Deprecated
	default boolean isPositionedBefore(Position position) {
		return getRange().map(r -> r.isBefore(position)).orElse(false);
	}
}
