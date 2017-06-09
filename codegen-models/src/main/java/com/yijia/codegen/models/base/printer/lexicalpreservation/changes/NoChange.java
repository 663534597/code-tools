package com.yijia.codegen.models.base.printer.lexicalpreservation.changes;

import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.observer.ObservableProperty;
import com.yijia.codegen.models.base.printer.concretesyntaxmodel.CsmConditional;

/**
 * No change. The Node is not mutated.
 */
public class NoChange implements Change {

    @Override
    public Object getValue(ObservableProperty property, Node node) {
        return property.getRawValue(node);
    }
}
