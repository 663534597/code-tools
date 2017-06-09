package com.yijia.codegen.models.base.ast.nodeTypes.modifiers;

import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithModifiers;

import static com.yijia.codegen.models.base.ast.Modifier.*;

/**
 * A node that can be private.
 */
public interface NodeWithPrivateModifier<N extends Node> extends NodeWithModifiers<N> {
    default boolean isPrivate() {
        return getModifiers().contains(PRIVATE);
    }

    @SuppressWarnings("unchecked")
    default N setPrivate(boolean set) {
        return setModifier(PRIVATE, set);
    }
}
