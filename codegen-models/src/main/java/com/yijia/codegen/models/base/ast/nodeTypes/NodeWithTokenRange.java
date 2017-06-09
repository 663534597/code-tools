package com.yijia.codegen.models.base.ast.nodeTypes;

import java.util.Optional;
import com.yijia.codegen.models.base.TokenRange;

/**
 * A node that has a Range, which is every Node.
 */
public interface NodeWithTokenRange<N> {
	Optional<TokenRange> getTokenRange();

	N setTokenRange(TokenRange range);
}
