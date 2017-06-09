package com.yijia.codegen.models.base.printer.lexicalpreservation.changes;

import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.observer.ObservableProperty;
import com.yijia.codegen.models.base.printer.concretesyntaxmodel.CsmConditional;
import com.yijia.codegen.models.base.utils.Utils;

/**
 * This represent a change happened to a specific Node.
 */
public interface Change {

    default boolean evaluate(CsmConditional csmConditional, Node node) {
        switch (csmConditional.getCondition()) {
            case FLAG:
                return (Boolean) getValue(csmConditional.getProperty(), node);
            case IS_NOT_EMPTY:
                return !Utils.valueIsNullOrEmpty(getValue(csmConditional.getProperty(), node));
            case IS_EMPTY:
                return Utils.valueIsNullOrEmpty(getValue(csmConditional.getProperty(), node));
            case IS_PRESENT:
                return !Utils.valueIsNullOrEmpty(getValue(csmConditional.getProperty(), node));
            default:
                throw new UnsupportedOperationException("" + csmConditional.getProperty() + " " + csmConditional.getCondition());
        }
    }

    Object getValue(ObservableProperty property, Node node);
}
