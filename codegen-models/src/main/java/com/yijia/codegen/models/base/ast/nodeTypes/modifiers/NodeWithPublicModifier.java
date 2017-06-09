package com.yijia.codegen.models.base.ast.nodeTypes.modifiers;

import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithModifiers;

import static com.yijia.codegen.models.base.ast.Modifier.*;

/**
 * A node that can be public.
 */
public interface NodeWithPublicModifier<N extends Node> extends NodeWithModifiers<N> {
    default boolean isPublic() {
        return getModifiers().contains(PUBLIC);
    }

    @SuppressWarnings("unchecked")
    default N setPublic(boolean set) {
        return setModifier(PUBLIC, set);
    }

}
