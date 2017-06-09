/*
 * Copyright (C) 2007-2010 Júlio Vilmar Gesser. Copyright (C) 2011, 2013-2016 The JavaParser Team. This file is part of JavaParser. JavaParser can be used either under the terms of
 * a) the GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. b) the terms of
 * the Apache License You should have received a copy of both licenses in LICENCE.LGPL and LICENCE.APACHE. Please refer to those files for details. JavaParser is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General
 * Public License for more details.
 */
package com.yijia.codegen.models.base.ast.stmt;

import static com.yijia.codegen.models.base.utils.Utils.assertNotNull;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;
import com.yijia.codegen.models.base.TokenRange;
import com.yijia.codegen.models.base.ast.AllFieldsConstructor;
import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.NodeList;
import com.yijia.codegen.models.base.ast.expr.Expression;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithTypeArguments;
import com.yijia.codegen.models.base.ast.observer.ObservableProperty;
import com.yijia.codegen.models.base.ast.type.Type;
import com.yijia.codegen.models.base.ast.visitor.CloneVisitor;
import com.yijia.codegen.models.base.ast.visitor.GenericVisitor;
import com.yijia.codegen.models.base.ast.visitor.VoidVisitor;
import com.yijia.codegen.models.base.metamodel.ExplicitConstructorInvocationStmtMetaModel;
import com.yijia.codegen.models.base.metamodel.JavaParserMetaModel;

/**
 * A call to super or this in a constructor or initializer. <br/>
 * <code>class X { X() { super(15); } }</code> <br/>
 * <code>class X { X() { this(1, 2); } }</code>
 * @author Julio Vilmar Gesser
 * @see com.yijia.codegen.models.base.ast.expr.SuperExpr
 * @see com.yijia.codegen.models.base.ast.expr.ThisExpr
 */
public final class ExplicitConstructorInvocationStmt extends Statement implements NodeWithTypeArguments<ExplicitConstructorInvocationStmt> {

	private NodeList<Type> typeArguments;

	private boolean isThis;

	private Expression expression;

	private NodeList<Expression> arguments;

	public ExplicitConstructorInvocationStmt() {
		this(null, new NodeList<>(), true, null, new NodeList<>());
	}

	public ExplicitConstructorInvocationStmt(final boolean isThis, final Expression expression, final NodeList<Expression> arguments) {
		this(null, new NodeList<>(), isThis, expression, arguments);
	}

	@AllFieldsConstructor
	public ExplicitConstructorInvocationStmt(final NodeList<Type> typeArguments, final boolean isThis, final Expression expression, final NodeList<Expression> arguments) {
		this(null, typeArguments, isThis, expression, arguments);
	}

	/** This constructor is used by the parser and is considered private. */
	@Generated("com.yijia.codegen.models.base.generator.core.node.MainConstructorGenerator")
	public ExplicitConstructorInvocationStmt(TokenRange tokenRange, NodeList<Type> typeArguments, boolean isThis, Expression expression, NodeList<Expression> arguments) {
		super(tokenRange);
		setTypeArguments(typeArguments);
		setThis(isThis);
		setExpression(expression);
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

	public Expression getArgument(int i) {
		return getArguments().get(i);
	}

	public ExplicitConstructorInvocationStmt setArgument(int i, Expression argument) {
		getArguments().set(i, argument);
		return this;
	}

	public ExplicitConstructorInvocationStmt addArgument(Expression argument) {
		getArguments().add(argument);
		return this;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public Optional<Expression> getExpression() {
		return Optional.ofNullable(expression);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public boolean isThis() {
		return isThis;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public ExplicitConstructorInvocationStmt setArguments(final NodeList<Expression> arguments) {
		assertNotNull(arguments);
		if (arguments == this.arguments) {
			return (ExplicitConstructorInvocationStmt) this;
		}
		notifyPropertyChange(ObservableProperty.ARGUMENTS, this.arguments, arguments);
		if (this.arguments != null)
			this.arguments.setParentNode(null);
		this.arguments = arguments;
		setAsParentNodeOf(arguments);
		return this;
	}

	/**
	 * Sets the expression
	 * @param expression the expression, can be null
	 * @return this, the ExplicitConstructorInvocationStmt
	 */
	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public ExplicitConstructorInvocationStmt setExpression(final Expression expression) {
		if (expression == this.expression) {
			return (ExplicitConstructorInvocationStmt) this;
		}
		notifyPropertyChange(ObservableProperty.EXPRESSION, this.expression, expression);
		if (this.expression != null)
			this.expression.setParentNode(null);
		this.expression = expression;
		setAsParentNodeOf(expression);
		return this;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public ExplicitConstructorInvocationStmt setThis(final boolean isThis) {
		if (isThis == this.isThis) {
			return (ExplicitConstructorInvocationStmt) this;
		}
		notifyPropertyChange(ObservableProperty.THIS, this.isThis, isThis);
		this.isThis = isThis;
		return this;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public Optional<NodeList<Type>> getTypeArguments() {
		return Optional.ofNullable(typeArguments);
	}

	/**
	 * Sets the typeArguments
	 * @param typeArguments the typeArguments, can be null
	 * @return this, the ExplicitConstructorInvocationStmt
	 */
	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public ExplicitConstructorInvocationStmt setTypeArguments(final NodeList<Type> typeArguments) {
		if (typeArguments == this.typeArguments) {
			return (ExplicitConstructorInvocationStmt) this;
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
		if (expression != null) {
			if (node == expression) {
				removeExpression();
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
	public ExplicitConstructorInvocationStmt removeExpression() {
		return setExpression((Expression) null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.CloneGenerator")
	public ExplicitConstructorInvocationStmt clone() {
		return (ExplicitConstructorInvocationStmt) accept(new CloneVisitor(), null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetMetaModelGenerator")
	public ExplicitConstructorInvocationStmtMetaModel getMetaModel() {
		return JavaParserMetaModel.explicitConstructorInvocationStmtMetaModel;
	}
}
