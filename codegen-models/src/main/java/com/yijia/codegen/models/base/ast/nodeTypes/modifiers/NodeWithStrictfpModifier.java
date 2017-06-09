package com.yijia.codegen.models.base.ast.nodeTypes.modifiers;

import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithModifiers;

import static com.yijia.codegen.models.base.ast.Modifier.STRICTFP;

/**
 * A node that can be strictfp.
 */
public interface NodeWithStrictfpModifier<N extends Node> extends NodeWithModifiers<N> {
    default boolean isStrictfp() {
        return getModifiers().contains(STRICTFP);
    }

    @SuppressWarnings("unchecked")
    default N setStrictfp(boolean set) {
        return setModifier(STRICTFP, set);
    }
}
