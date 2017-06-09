package com.yijia.codegen.models.base.printer.lexicalpreservation.changes;

import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.NodeList;
import com.yijia.codegen.models.base.ast.observer.ObservableProperty;

/**
 * The replacement of an element in a list.
 */
public class ListReplacementChange implements Change {
    private ObservableProperty observableProperty;
    private NodeList nodeList;
    private int index;
    private Node oldValue;
    private Node newValue;

    public ListReplacementChange(ObservableProperty observableProperty, NodeList nodeList, int index, Node oldValue, Node newValue) {
        this.observableProperty = observableProperty;
        this.nodeList = nodeList;
        this.index = index;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    @Override
    public Object getValue(ObservableProperty property, Node node) {
        if (property == observableProperty) {
            NodeList nodeList = new NodeList();
            NodeList currentNodeList = (NodeList)(new NoChange().getValue(property, node));
            nodeList.addAll(currentNodeList);
            nodeList.set(index, newValue);
            return nodeList;
        } else {
            return new NoChange().getValue(property, node);
        }
    }
}
