package com.yijia.codegen.models.base.ast.nodeTypes.modifiers;

import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithModifiers;

import static com.yijia.codegen.models.base.ast.Modifier.FINAL;

/**
 * A node that can be final.
 */
public interface NodeWithFinalModifier<N extends Node> extends NodeWithModifiers<N> {
    default boolean isFinal() {
        return getModifiers().contains(FINAL);
    }

    @SuppressWarnings("unchecked")
    default N setFinal(boolean set) {
        return setModifier(FINAL, set);
    }
}
