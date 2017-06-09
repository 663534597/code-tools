/*
 * Copyright (C) 2007-2010 Júlio Vilmar Gesser. Copyright (C) 2011, 2013-2016 The JavaParser Team. This file is part of JavaParser. JavaParser can be used either under the terms of
 * a) the GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. b) the terms of
 * the Apache License You should have received a copy of both licenses in LICENCE.LGPL and LICENCE.APACHE. Please refer to those files for details. JavaParser is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General
 * Public License for more details.
 */
package com.yijia.codegen.models.base.ast.expr;

import static com.yijia.codegen.models.base.utils.Utils.assertNonEmpty;
import static com.yijia.codegen.models.base.utils.Utils.assertNotNull;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;
import com.yijia.codegen.models.base.TokenRange;
import com.yijia.codegen.models.base.ast.AllFieldsConstructor;
import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.NodeList;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithIdentifier;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithTypeArguments;
import com.yijia.codegen.models.base.ast.observer.ObservableProperty;
import com.yijia.codegen.models.base.ast.type.Type;
import com.yijia.codegen.models.base.ast.visitor.CloneVisitor;
import com.yijia.codegen.models.base.ast.visitor.GenericVisitor;
import com.yijia.codegen.models.base.ast.visitor.VoidVisitor;
import com.yijia.codegen.models.base.metamodel.JavaParserMetaModel;
import com.yijia.codegen.models.base.metamodel.MethodReferenceExprMetaModel;
import com.yijia.codegen.models.base.metamodel.NonEmptyProperty;

/**
 * Method reference expressions introduced in Java 8 specifically designed to simplify lambda Expressions. Note that the field "identifier", indicating the word to the right of the
 * ::, is not always a method name, it can be "new". <br/>
 * In <code>System.out::println;</code> the scope is System.out and the identifier is "println" <br/>
 * <code>(test ? stream.map(String::trim) : stream)::toArray;</code> <br/>
 * In <code>Bar&lt;String>::&lt;Integer>new</code> the String type argument is on the scope, and the Integer type argument is on this MethodReferenceExpr.
 * @author Raquel Pau
 */
public class MethodReferenceExpr extends Expression implements NodeWithTypeArguments<MethodReferenceExpr>, NodeWithIdentifier<MethodReferenceExpr> {

	private Expression scope;

	private NodeList<Type> typeArguments;

	@NonEmptyProperty
	private String identifier;

	public MethodReferenceExpr() {
		this(null, new ClassExpr(), null, "empty");
	}

	@AllFieldsConstructor
	public MethodReferenceExpr(Expression scope, NodeList<Type> typeArguments, String identifier) {
		this(null, scope, typeArguments, identifier);
	}

	/** This constructor is used by the parser and is considered private. */
	@Generated("com.yijia.codegen.models.base.generator.core.node.MainConstructorGenerator")
	public MethodReferenceExpr(TokenRange tokenRange, Expression scope, NodeList<Type> typeArguments, String identifier) {
		super(tokenRange);
		setScope(scope);
		setTypeArguments(typeArguments);
		setIdentifier(identifier);
		customInitialization();
	}

	@Override
	public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
		return v.visit(this, arg);
	}

	@Override
	public <A> void accept(VoidVisitor<A> v, A arg) {
		v.visit(this, arg);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public Expression getScope() {
		return scope;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public MethodReferenceExpr setScope(final Expression scope) {
		assertNotNull(scope);
		if (scope == this.scope) {
			return (MethodReferenceExpr) this;
		}
		notifyPropertyChange(ObservableProperty.SCOPE, this.scope, scope);
		if (this.scope != null)
			this.scope.setParentNode(null);
		this.scope = scope;
		setAsParentNodeOf(scope);
		return this;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public Optional<NodeList<Type>> getTypeArguments() {
		return Optional.ofNullable(typeArguments);
	}

	/**
	 * Sets the typeArguments
	 * @param typeArguments the typeArguments, can be null
	 * @return this, the MethodReferenceExpr
	 */
	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public MethodReferenceExpr setTypeArguments(final NodeList<Type> typeArguments) {
		if (typeArguments == this.typeArguments) {
			return (MethodReferenceExpr) this;
		}
		notifyPropertyChange(ObservableProperty.TYPE_ARGUMENTS, this.typeArguments, typeArguments);
		if (this.typeArguments != null)
			this.typeArguments.setParentNode(null);
		this.typeArguments = typeArguments;
		setAsParentNodeOf(typeArguments);
		return this;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public String getIdentifier() {
		return identifier;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public MethodReferenceExpr setIdentifier(final String identifier) {
		assertNonEmpty(identifier);
		if (identifier == this.identifier) {
			return (MethodReferenceExpr) this;
		}
		notifyPropertyChange(ObservableProperty.IDENTIFIER, this.identifier, identifier);
		this.identifier = identifier;
		return this;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetNodeListsGenerator")
	public List<NodeList<?>> getNodeLists() {
		return Arrays.asList(getTypeArguments().orElse(null));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.RemoveMethodGenerator")
	public boolean remove(Node node) {
		if (node == null)
			return false;
		if (typeArguments != null) {
			for (int i = 0; i < typeArguments.size(); i++) {
				if (typeArguments.get(i) == node) {
					typeArguments.remove(i);
					return true;
				}
			}
		}
		return super.remove(node);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.CloneGenerator")
	public MethodReferenceExpr clone() {
		return (MethodReferenceExpr) accept(new CloneVisitor(), null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetMetaModelGenerator")
	public MethodReferenceExprMetaModel getMetaModel() {
		return JavaParserMetaModel.methodReferenceExprMetaModel;
	}
}
