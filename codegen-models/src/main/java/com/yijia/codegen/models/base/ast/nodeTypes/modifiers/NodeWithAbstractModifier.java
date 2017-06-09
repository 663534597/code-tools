package com.yijia.codegen.models.base.ast.nodeTypes.modifiers;

import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithModifiers;

import static com.yijia.codegen.models.base.ast.Modifier.ABSTRACT;

/**
 * A node that can be abstract.
 */
public interface NodeWithAbstractModifier<N extends Node> extends NodeWithModifiers<N> {
    default boolean isAbstract() {
        return getModifiers().contains(ABSTRACT);
    }

    @SuppressWarnings("unchecked")
    default N setAbstract(boolean set) {
        return setModifier(ABSTRACT, set);
    }
}
