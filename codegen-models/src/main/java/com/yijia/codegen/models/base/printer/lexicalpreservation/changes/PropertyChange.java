package com.yijia.codegen.models.base.printer.lexicalpreservation.changes;

import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.NodeList;
import com.yijia.codegen.models.base.ast.observer.ObservableProperty;
import com.yijia.codegen.models.base.printer.concretesyntaxmodel.CsmConditional;

import java.util.Optional;

/**
 * The change in value of a property.
 */
public class PropertyChange implements Change {
    private ObservableProperty property;
    private Object oldValue;
    private Object newValue;

    public ObservableProperty getProperty() {
        return property;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }

    public PropertyChange(ObservableProperty property, Object oldValue, Object newValue) {
        this.property = property;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    @Override
    public Object getValue(ObservableProperty property, Node node) {
        if (property == this.property) {
            return newValue;
        } else {
            return property.getRawValue(node);
        }
    }
}
