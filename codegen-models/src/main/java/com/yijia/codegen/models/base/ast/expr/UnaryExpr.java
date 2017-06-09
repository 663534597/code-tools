/*
 * Copyright (C) 2007-2010 Júlio Vilmar Gesser. Copyright (C) 2011, 2013-2016 The JavaParser Team. This file is part of JavaParser. JavaParser can be used either under the terms of
 * a) the GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. b) the terms of
 * the Apache License You should have received a copy of both licenses in LICENCE.LGPL and LICENCE.APACHE. Please refer to those files for details. JavaParser is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General
 * Public License for more details.
 */
package com.yijia.codegen.models.base.ast.expr;

import static com.yijia.codegen.models.base.utils.Utils.assertNotNull;
import javax.annotation.Generated;
import com.yijia.codegen.models.base.TokenRange;
import com.yijia.codegen.models.base.ast.AllFieldsConstructor;
import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithExpression;
import com.yijia.codegen.models.base.ast.observer.ObservableProperty;
import com.yijia.codegen.models.base.ast.visitor.CloneVisitor;
import com.yijia.codegen.models.base.ast.visitor.GenericVisitor;
import com.yijia.codegen.models.base.ast.visitor.VoidVisitor;
import com.yijia.codegen.models.base.metamodel.DerivedProperty;
import com.yijia.codegen.models.base.metamodel.JavaParserMetaModel;
import com.yijia.codegen.models.base.metamodel.UnaryExprMetaModel;
import com.yijia.codegen.models.base.printer.Printable;

/**
 * An expression where an operator is applied to a single expression. It supports the operators that are found the the UnaryExpr.Operator enum. <br/>
 * <code>11++</code> <br/>
 * <code>++11</code> <br/>
 * <code>~1</code> <br/>
 * <code>-333</code>
 * @author Julio Vilmar Gesser
 */
public final class UnaryExpr extends Expression implements NodeWithExpression<UnaryExpr> {

	public enum Operator implements Printable {

		PLUS("+", false), MINUS("-", false), PREFIX_INCREMENT("++", false), PREFIX_DECREMENT("--", false), LOGICAL_COMPLEMENT("!", false), BITWISE_COMPLEMENT("~",
				false), POSTFIX_INCREMENT("++", true), POSTFIX_DECREMENT("--", true);

		private final String codeRepresentation;

		private final boolean isPostfix;

		Operator(String codeRepresentation, boolean isPostfix) {
			this.codeRepresentation = codeRepresentation;
			this.isPostfix = isPostfix;
		}

		public String asString() {
			return codeRepresentation;
		}

		public boolean isPostfix() {
			return isPostfix;
		}

		public boolean isPrefix() {
			return !isPostfix();
		}
	}

	private Expression expression;

	private Operator operator;

	public UnaryExpr() {
		this(null, new IntegerLiteralExpr(), Operator.POSTFIX_INCREMENT);
	}

	@AllFieldsConstructor
	public UnaryExpr(final Expression expression, final Operator operator) {
		this(null, expression, operator);
	}

	/** This constructor is used by the parser and is considered private. */
	@Generated("com.yijia.codegen.models.base.generator.core.node.MainConstructorGenerator")
	public UnaryExpr(TokenRange tokenRange, Expression expression, Operator operator) {
		super(tokenRange);
		setExpression(expression);
		setOperator(operator);
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
	public Expression getExpression() {
		return expression;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public Operator getOperator() {
		return operator;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public UnaryExpr setExpression(final Expression expression) {
		assertNotNull(expression);
		if (expression == this.expression) {
			return (UnaryExpr) this;
		}
		notifyPropertyChange(ObservableProperty.EXPRESSION, this.expression, expression);
		if (this.expression != null)
			this.expression.setParentNode(null);
		this.expression = expression;
		setAsParentNodeOf(expression);
		return this;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public UnaryExpr setOperator(final Operator operator) {
		assertNotNull(operator);
		if (operator == this.operator) {
			return (UnaryExpr) this;
		}
		notifyPropertyChange(ObservableProperty.OPERATOR, this.operator, operator);
		this.operator = operator;
		return this;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.RemoveMethodGenerator")
	public boolean remove(Node node) {
		if (node == null)
			return false;
		return super.remove(node);
	}

	@DerivedProperty
	public boolean isPostfix() {
		return operator.isPostfix();
	}

	@DerivedProperty
	public boolean isPrefix() {
		return !isPostfix();
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.CloneGenerator")
	public UnaryExpr clone() {
		return (UnaryExpr) accept(new CloneVisitor(), null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetMetaModelGenerator")
	public UnaryExprMetaModel getMetaModel() {
		return JavaParserMetaModel.unaryExprMetaModel;
	}
}
