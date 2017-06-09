package com.yijia.codegen.models.base.ast.nodeTypes;

import static com.yijia.codegen.models.base.utils.Utils.assertNonEmpty;
import java.util.Optional;
import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.expr.SimpleName;

/**
 * A node that has an optional label.
 */
public interface NodeWithOptionalLabel<T extends Node> {
	Optional<SimpleName> getLabel();

	T setLabel(SimpleName label);

	T removeLabel();

	default T setLabel(String label) {
		assertNonEmpty(label);
		return setLabel(new SimpleName(label));
	}

	default Optional<String> getLabelAsString() {
		return getLabel().flatMap(l -> Optional.of(l.getIdentifier()));
	}
}
