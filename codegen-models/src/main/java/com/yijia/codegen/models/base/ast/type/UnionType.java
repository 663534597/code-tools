/*
 * Copyright (C) 2007-2010 Júlio Vilmar Gesser. Copyright (C) 2011, 2013-2016 The JavaParser Team. This file is part of JavaParser. JavaParser can be used either under the terms of
 * a) the GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. b) the terms of
 * the Apache License You should have received a copy of both licenses in LICENCE.LGPL and LICENCE.APACHE. Please refer to those files for details. JavaParser is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General
 * Public License for more details.
 */
package com.yijia.codegen.models.base.ast.type;

import static com.yijia.codegen.models.base.utils.Utils.assertNotNull;
import static java.util.stream.Collectors.joining;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Generated;
import com.yijia.codegen.models.base.TokenRange;
import com.yijia.codegen.models.base.ast.AllFieldsConstructor;
import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.NodeList;
import com.yijia.codegen.models.base.ast.expr.AnnotationExpr;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithAnnotations;
import com.yijia.codegen.models.base.ast.observer.ObservableProperty;
import com.yijia.codegen.models.base.ast.visitor.CloneVisitor;
import com.yijia.codegen.models.base.ast.visitor.GenericVisitor;
import com.yijia.codegen.models.base.ast.visitor.VoidVisitor;
import com.yijia.codegen.models.base.metamodel.JavaParserMetaModel;
import com.yijia.codegen.models.base.metamodel.NonEmptyProperty;
import com.yijia.codegen.models.base.metamodel.UnionTypeMetaModel;

/**
 * Represents a set of types. A given value of this type has to be assignable to at least one of the element types. As of Java 8 it is only used in catch clauses. <br/>
 * <code>try { ... } catch(<b>IOException | NullPointerException ex</b>) { ... }</code>
 */
public class UnionType extends Type implements NodeWithAnnotations<UnionType> {

	@NonEmptyProperty
	private NodeList<ReferenceType> elements;

	public UnionType() {
		this(null, new NodeList<>());
	}

	/** This constructor is used by the parser and is considered private. */
	@Generated("com.yijia.codegen.models.base.generator.core.node.MainConstructorGenerator")
	public UnionType(TokenRange tokenRange, NodeList<ReferenceType> elements) {
		super(tokenRange);
		setElements(elements);
		customInitialization();
	}

	@AllFieldsConstructor
	public UnionType(NodeList<ReferenceType> elements) {
		this(null, elements);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public NodeList<ReferenceType> getElements() {
		return elements;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public UnionType setElements(final NodeList<ReferenceType> elements) {
		assertNotNull(elements);
		if (elements == this.elements) {
			return (UnionType) this;
		}
		notifyPropertyChange(ObservableProperty.ELEMENTS, this.elements, elements);
		if (this.elements != null)
			this.elements.setParentNode(null);
		this.elements = elements;
		setAsParentNodeOf(elements);
		return this;
	}

	@Override
	public UnionType setAnnotations(NodeList<AnnotationExpr> annotations) {
		return (UnionType) super.setAnnotations(annotations);
	}

	@Override
	public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
		return v.visit(this, arg);
	}

	@Override
	public <A> void accept(VoidVisitor<A> v, A arg) {
		v.visit(this, arg);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetNodeListsGenerator")
	public List<NodeList<?>> getNodeLists() {
		return Arrays.asList(getElements(), getAnnotations());
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.RemoveMethodGenerator")
	public boolean remove(Node node) {
		if (node == null)
			return false;
		for (int i = 0; i < elements.size(); i++) {
			if (elements.get(i) == node) {
				elements.remove(i);
				return true;
			}
		}
		return super.remove(node);
	}

	@Override
	public String asString() {
		return elements.stream().map(Type::asString).collect(joining("|"));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.CloneGenerator")
	public UnionType clone() {
		return (UnionType) accept(new CloneVisitor(), null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetMetaModelGenerator")
	public UnionTypeMetaModel getMetaModel() {
		return JavaParserMetaModel.unionTypeMetaModel;
	}
}
