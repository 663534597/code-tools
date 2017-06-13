/*
 * Copyright (C) 2007-2010 Júlio Vilmar Gesser. Copyright (C) 2011, 2013-2016 The JavaParser Team. This file is part of JavaParser. JavaParser can be used either under the terms of
 * a) the GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. b) the terms of
 * the Apache License You should have received a copy of both licenses in LICENCE.LGPL and LICENCE.APACHE. Please refer to those files for details. JavaParser is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General
 * Public License for more details.
 */
package com.yijia.codegen.models.base.ast.expr;

import static com.yijia.codegen.models.base.utils.Utils.assertNotNull;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;
import com.yijia.codegen.models.base.TokenRange;
import com.yijia.codegen.models.base.ast.AllFieldsConstructor;
import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.NodeList;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithArguments;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithOptionalScope;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithSimpleName;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithTypeArguments;
import com.yijia.codegen.models.base.ast.observer.ObservableProperty;
import com.yijia.codegen.models.base.ast.type.Type;
import com.yijia.codegen.models.base.ast.visitor.CloneVisitor;
import com.yijia.codegen.models.base.ast.visitor.GenericVisitor;
import com.yijia.codegen.models.base.ast.visitor.VoidVisitor;
import com.yijia.codegen.models.base.metamodel.JavaParserMetaModel;
import com.yijia.codegen.models.base.metamodel.MethodCallExprMetaModel;

/**
 * A method call on an object. <br/>
 * <code>circle.circumference()</code> <br/>
 * In <code>a.&lt;String&gt;bb(15);</code> a is the scope, String is a type argument, bb is the name and 15 is an argument.
 * @author Julio Vilmar Gesser
 */
public final class MethodCallExpr extends Expression
		implements NodeWithTypeArguments<MethodCallExpr>, NodeWithArguments<MethodCallExpr>, NodeWithSimpleName<MethodCallExpr>, NodeWithOptionalScope<MethodCallExpr> {

	private Expression scope;

	private NodeList<Type> typeArguments;

	private SimpleName name;

	private NodeList<Expression> arguments;

	public MethodCallExpr() {
		this(null, null, new NodeList<>(), new SimpleName(), new NodeList<>());
	}

	public MethodCallExpr(final Expression scope, final String name) {
		this(null, scope, new NodeList<>(), new SimpleName(name), new NodeList<>());
	}

	public MethodCallExpr(final Expression scope, final SimpleName name) {
		this(null, scope, new NodeList<>(), name, new NodeList<>());
	}

	public MethodCallExpr(final Expression scope, final String name, final NodeList<Expression> arguments) {
		this(null, scope, new NodeList<>(), new SimpleName(name), arguments);
	}

	public MethodCallExpr(final Expression scope, final SimpleName name, final NodeList<Expression> arguments) {
		this(null, scope, new NodeList<>(), name, arguments);
	}

	@AllFieldsConstructor
	public MethodCallExpr(final Expression scope, final NodeList<Type> typeArguments, final SimpleName name, final NodeList<Expression> arguments) {
		this(null, scope, typeArguments, name, arguments);
	}

	/** This constructor is used by the parser and is considered private. */
	@Generated("com.yijia.codegen.models.base.generator.core.node.MainConstructorGenerator")
	public MethodCallExpr(TokenRange tokenRange, Expression scope, NodeList<Type> typeArguments, SimpleName name, NodeList<Expression> arguments) {
		super(tokenRange);
		setScope(scope);
		setTypeArguments(typeArguments);
		setName(name);
		setArguments(arguments);
		customInitialization();
	}

	@Override
	public <R, A> R accept(final GenericVisitor<R, A> v, final A arg) {
		return v.visit(this, arg);
	}

	@Override
	public <A> void accept(final VoidVisitor<A> v, final A arg) {
		v.visit(this, arg);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public NodeList<Expression> getArguments() {
		return arguments;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public SimpleName getName() {
		return name;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public Optional<Expression> getScope() {
		return Optional.ofNullable(scope);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public MethodCallExpr setArguments(final NodeList<Expression> arguments) {
		assertNotNull(arguments);
		if (arguments == this.arguments) {
			return (MethodCallExpr) this;
		}
		notifyPropertyChange(ObservableProperty.ARGUMENTS, this.arguments, arguments);
		if (this.arguments != null)
			this.arguments.setParentNode(null);
		this.arguments = arguments;
		setAsParentNodeOf(arguments);
		return this;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public MethodCallExpr setName(final SimpleName name) {
		assertNotNull(name);
		if (name == this.name) {
			return (MethodCallExpr) this;
		}
		notifyPropertyChange(ObservableProperty.NAME, this.name, name);
		if (this.name != null)
			this.name.setParentNode(null);
		this.name = name;
		setAsParentNodeOf(name);
		return this;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public MethodCallExpr setScope(final Expression scope) {
		if (scope == this.scope) {
			return (MethodCallExpr) this;
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
	 * @return this, the MethodCallExpr
	 */
	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public MethodCallExpr setTypeArguments(final NodeList<Type> typeArguments) {
		if (typeArguments == this.typeArguments) {
			return (MethodCallExpr) this;
		}
		notifyPropertyChange(ObservableProperty.TYPE_ARGUMENTS, this.typeArguments, typeArguments);
		if (this.typeArguments != null)
			this.typeArguments.setParentNode(null);
		this.typeArguments = typeArguments;
		setAsParentNodeOf(typeArguments);
		return this;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetNodeListsGenerator")
	public List<NodeList<?>> getNodeLists() {
		return Arrays.asList(getArguments(), getTypeArguments().orElse(null));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.RemoveMethodGenerator")
	public boolean remove(Node node) {
		if (node == null)
			return false;
		for (int i = 0; i < arguments.size(); i++) {
			if (arguments.get(i) == node) {
				arguments.remove(i);
				return true;
			}
		}
		if (scope != null) {
			if (node == scope) {
				removeScope();
				return true;
			}
		}
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

	@Generated("com.yijia.codegen.models.base.generator.core.node.RemoveMethodGenerator")
	public MethodCallExpr removeScope() {
		return setScope((Expression) null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.CloneGenerator")
	public MethodCallExpr clone() {
		return (MethodCallExpr) accept(new CloneVisitor(), null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetMetaModelGenerator")
	public MethodCallExprMetaModel getMetaModel() {
		return JavaParserMetaModel.methodCallExprMetaModel;
	}
}