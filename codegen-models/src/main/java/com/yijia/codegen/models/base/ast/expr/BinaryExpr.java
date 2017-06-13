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
import com.yijia.codegen.models.base.ast.observer.ObservableProperty;
import com.yijia.codegen.models.base.ast.visitor.CloneVisitor;
import com.yijia.codegen.models.base.ast.visitor.GenericVisitor;
import com.yijia.codegen.models.base.ast.visitor.VoidVisitor;
import com.yijia.codegen.models.base.metamodel.BinaryExprMetaModel;
import com.yijia.codegen.models.base.metamodel.JavaParserMetaModel;
import com.yijia.codegen.models.base.printer.Printable;

/**
 * An expression with an expression on the left, an expression on the right, and an operator in the middle. It supports the operators that are found the the BinaryExpr.Operator
 * enum. <br/>
 * <code>a && b</code> <br/>
 * <code>155 * 33</code>
 * @author Julio Vilmar Gesser
 */
public final class BinaryExpr extends Expression {

	public enum Operator implements Printable {

		OR("||"), AND("&&"), BINARY_OR("|"), BINARY_AND("&"), XOR("^"), EQUALS("=="), NOT_EQUALS("!="), LESS("<"), GREATER(">"), LESS_EQUALS("<="), GREATER_EQUALS(
				">="), LEFT_SHIFT("<<"), SIGNED_RIGHT_SHIFT(">>"), UNSIGNED_RIGHT_SHIFT(">>>"), PLUS("+"), MINUS("-"), MULTIPLY("*"), DIVIDE("/"), REMAINDER("%");

		private final String codeRepresentation;

		Operator(String codeRepresentation) {
			this.codeRepresentation = codeRepresentation;
		}

		public String asString() {
			return codeRepresentation;
		}
	}

	private Expression left;

	private Expression right;

	private Operator operator;

	public BinaryExpr() {
		this(null, new BooleanLiteralExpr(), new BooleanLiteralExpr(), Operator.EQUALS);
	}

	@AllFieldsConstructor
	public BinaryExpr(Expression left, Expression right, Operator operator) {
		this(null, left, right, operator);
	}

	/** This constructor is used by the parser and is considered private. */
	@Generated("com.yijia.codegen.models.base.generator.core.node.MainConstructorGenerator")
	public BinaryExpr(TokenRange tokenRange, Expression left, Expression right, Operator operator) {
		super(tokenRange);
		setLeft(left);
		setRight(right);
		setOperator(operator);
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
	public Expression getLeft() {
		return left;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public Operator getOperator() {
		return operator;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public Expression getRight() {
		return right;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public BinaryExpr setLeft(final Expression left) {
		assertNotNull(left);
		if (left == this.left) {
			return (BinaryExpr) this;
		}
		notifyPropertyChange(ObservableProperty.LEFT, this.left, left);
		if (this.left != null)
			this.left.setParentNode(null);
		this.left = left;
		setAsParentNodeOf(left);
		return this;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public BinaryExpr setOperator(final Operator operator) {
		assertNotNull(operator);
		if (operator == this.operator) {
			return (BinaryExpr) this;
		}
		notifyPropertyChange(ObservableProperty.OPERATOR, this.operator, operator);
		this.operator = operator;
		return this;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public BinaryExpr setRight(final Expression right) {
		assertNotNull(right);
		if (right == this.right) {
			return (BinaryExpr) this;
		}
		notifyPropertyChange(ObservableProperty.RIGHT, this.right, right);
		if (this.right != null)
			this.right.setParentNode(null);
		this.right = right;
		setAsParentNodeOf(right);
		return this;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.RemoveMethodGenerator")
	public boolean remove(Node node) {
		if (node == null)
			return false;
		return super.remove(node);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.CloneGenerator")
	public BinaryExpr clone() {
		return (BinaryExpr) accept(new CloneVisitor(), null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetMetaModelGenerator")
	public BinaryExprMetaModel getMetaModel() {
		return JavaParserMetaModel.binaryExprMetaModel;
	}
}