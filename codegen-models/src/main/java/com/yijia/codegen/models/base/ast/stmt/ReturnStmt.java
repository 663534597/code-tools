/*
 * Copyright (C) 2007-2010 Júlio Vilmar Gesser. Copyright (C) 2011, 2013-2016 The JavaParser Team. This file is part of JavaParser. JavaParser can be used either under the terms of
 * a) the GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. b) the terms of
 * the Apache License You should have received a copy of both licenses in LICENCE.LGPL and LICENCE.APACHE. Please refer to those files for details. JavaParser is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General
 * Public License for more details.
 */
package com.yijia.codegen.models.base.ast.stmt;

import java.util.Optional;
import javax.annotation.Generated;
import com.yijia.codegen.models.base.TokenRange;
import com.yijia.codegen.models.base.ast.AllFieldsConstructor;
import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.expr.Expression;
import com.yijia.codegen.models.base.ast.expr.NameExpr;
import com.yijia.codegen.models.base.ast.observer.ObservableProperty;
import com.yijia.codegen.models.base.ast.visitor.CloneVisitor;
import com.yijia.codegen.models.base.ast.visitor.GenericVisitor;
import com.yijia.codegen.models.base.ast.visitor.VoidVisitor;
import com.yijia.codegen.models.base.metamodel.JavaParserMetaModel;
import com.yijia.codegen.models.base.metamodel.ReturnStmtMetaModel;

/**
 * The return statement, with an optional expression to return. <br/>
 * <code>return 5 * 5;</code>
 * @author Julio Vilmar Gesser
 */
public final class ReturnStmt extends Statement {

	private Expression expression;

	public ReturnStmt() {
		this(null, null);
	}

	@AllFieldsConstructor
	public ReturnStmt(final Expression expression) {
		this(null, expression);
	}

	/** This constructor is used by the parser and is considered private. */
	@Generated("com.yijia.codegen.models.base.generator.core.node.MainConstructorGenerator")
	public ReturnStmt(TokenRange tokenRange, Expression expression) {
		super(tokenRange);
		setExpression(expression);
		customInitialization();
	}

	/**
	 * Will create a NameExpr with the string param
	 */
	public ReturnStmt(String expression) {
		this(null, new NameExpr(expression));
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
	public Optional<Expression> getExpression() {
		return Optional.ofNullable(expression);
	}

	/**
	 * Sets the expression
	 * @param expression the expression, can be null
	 * @return this, the ReturnStmt
	 */
	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public ReturnStmt setExpression(final Expression expression) {
		if (expression == this.expression) {
			return (ReturnStmt) this;
		}
		notifyPropertyChange(ObservableProperty.EXPRESSION, this.expression, expression);
		if (this.expression != null)
			this.expression.setParentNode(null);
		this.expression = expression;
		setAsParentNodeOf(expression);
		return this;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.RemoveMethodGenerator")
	public boolean remove(Node node) {
		if (node == null)
			return false;
		if (expression != null) {
			if (node == expression) {
				removeExpression();
				return true;
			}
		}
		return super.remove(node);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.RemoveMethodGenerator")
	public ReturnStmt removeExpression() {
		return setExpression((Expression) null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.CloneGenerator")
	public ReturnStmt clone() {
		return (ReturnStmt) accept(new CloneVisitor(), null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetMetaModelGenerator")
	public ReturnStmtMetaModel getMetaModel() {
		return JavaParserMetaModel.returnStmtMetaModel;
	}
}
