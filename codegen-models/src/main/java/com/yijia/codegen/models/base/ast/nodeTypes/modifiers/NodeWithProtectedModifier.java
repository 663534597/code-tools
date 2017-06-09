package com.yijia.codegen.models.base.ast.nodeTypes.modifiers;

import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithModifiers;

import static com.yijia.codegen.models.base.ast.Modifier.*;

/**
 * A node that can be protected.
 */
public interface NodeWithProtectedModifier<N extends Node> extends NodeWithModifiers<N> {
    default boolean isProtected() {
        return getModifiers().contains(PROTECTED);
    }

    @SuppressWarnings("unchecked")
    default N setProtected(boolean set) {
        return setModifier(PROTECTED, set);
    }
}
