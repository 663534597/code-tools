package com.yijia.codegen.models.base.ast.nodeTypes.modifiers;

import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithModifiers;

import static com.yijia.codegen.models.base.ast.Modifier.STATIC;

/**
 * A node that can be static.
 */
public interface NodeWithStaticModifier<N extends Node> extends NodeWithModifiers<N> {

    default boolean isStatic() {
        return getModifiers().contains(STATIC);
    }

    @SuppressWarnings("unchecked")
    default N setStatic(boolean set) {
        return setModifier(STATIC, set);
    }

}
