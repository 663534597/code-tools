package com.yijia.codegen.models.base;

import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.observer.Observable;
import java.util.List;
import java.util.Optional;

/**
 * An object that has a parent node.
 */
public interface HasParentNode<T> extends Observable {

	/**
	 * Return the parent node or null, if no parent is set.
	 */
	Optional<Node> getParentNode();

	/**
	 * Set the parent node.
	 * @param parentNode the parent node or null, to set no parent
	 * @return return <i>this</i>
	 */
	T setParentNode(Node parentNode);

	/**
	 * <i>this</i> for everything except NodeLists. NodeLists use their parent as their children parent.
	 */
	Node getParentNodeForChildren();

	/**
	 * Get the ancestor of the node having the given type, or null if no ancestor of the given type is found.
	 */
	default <N> Optional<N> getAncestorOfType(Class<N> classType) {
		Node parent = getParentNode().orElse(null);
		while (parent != null) {
			if (classType.isAssignableFrom(parent.getClass())) {
				return Optional.of(classType.cast(parent));
			}
			parent = parent.getParentNode().orElse(null);
		}
		return Optional.empty();
	}

	// TODO should become protected once Java lets us
	default void setAsParentNodeOf(List<? extends Node> childNodes) {
		if (childNodes != null) {
			for (HasParentNode<Node> current : childNodes) {
				current.setParentNode(getParentNodeForChildren());
			}
		}
	}

	// TODO should become protected once Java lets us
	default void setAsParentNodeOf(Node childNode) {
		if (childNode != null) {
			childNode.setParentNode(getParentNodeForChildren());
		}
	}

}
