package com.yijia.codegen.models.base.ast.nodeTypes.modifiers;

import com.yijia.codegen.models.base.ast.Node;

/**
 * A node that can be public, protected, and/or private.
 */
public interface NodeWithAccessModifiers<N extends Node> extends NodeWithPublicModifier<N>, NodeWithPrivateModifier<N>, NodeWithProtectedModifier<N> {
}
