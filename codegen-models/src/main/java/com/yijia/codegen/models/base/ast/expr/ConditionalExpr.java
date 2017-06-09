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
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithCondition;
import com.yijia.codegen.models.base.ast.observer.ObservableProperty;
import com.yijia.codegen.models.base.ast.visitor.CloneVisitor;
import com.yijia.codegen.models.base.ast.visitor.GenericVisitor;
import com.yijia.codegen.models.base.ast.visitor.VoidVisitor;
import com.yijia.codegen.models.base.metamodel.ConditionalExprMetaModel;
import com.yijia.codegen.models.base.metamodel.JavaParserMetaModel;

/**
 * The ternary conditional expression. In <code>b==0?x:y</code>, b==0 is the condition, x is thenExpr, and y is elseExpr.
 * @author Julio Vilmar Gesser
 */
public final class ConditionalExpr extends Expression implements NodeWithCondition<ConditionalExpr> {

	private Expression condition;

	private Expression thenExpr;

	private Expression elseExpr;

	public ConditionalExpr() {
		this(null, new BooleanLiteralExpr(), new StringLiteralExpr(), new StringLiteralExpr());
	}

	@AllFieldsConstructor
	public ConditionalExpr(Expression condition, Expression thenExpr, Expression elseExpr) {
		this(null, condition, thenExpr, elseExpr);
	}

	/** This constructor is used by the parser and is considered private. */
	@Generated("com.yijia.codegen.models.base.generator.core.node.MainConstructorGenerator")
	public ConditionalExpr(TokenRange tokenRange, Expression condition, Expression thenExpr, Expression elseExpr) {
		super(tokenRange);
		setCondition(condition);
		setThenExpr(thenExpr);
		setElseExpr(elseExpr);
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
	public Expression getCondition() {
		return condition;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public Expression getElseExpr() {
		return elseExpr;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public Expression getThenExpr() {
		return thenExpr;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public ConditionalExpr setCondition(final Expression condition) {
		assertNotNull(condition);
		if (condition == this.condition) {
			return (ConditionalExpr) this;
		}
		notifyPropertyChange(ObservableProperty.CONDITION, this.condition, condition);
		if (this.condition != null)
			this.condition.setParentNode(null);
		this.condition = condition;
		setAsParentNodeOf(condition);
		return this;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public ConditionalExpr setElseExpr(final Expression elseExpr) {
		assertNotNull(elseExpr);
		if (elseExpr == this.elseExpr) {
			return (ConditionalExpr) this;
		}
		notifyPropertyChange(ObservableProperty.ELSE_EXPR, this.elseExpr, elseExpr);
		if (this.elseExpr != null)
			this.elseExpr.setParentNode(null);
		this.elseExpr = elseExpr;
		setAsParentNodeOf(elseExpr);
		return this;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public ConditionalExpr setThenExpr(final Expression thenExpr) {
		assertNotNull(thenExpr);
		if (thenExpr == this.thenExpr) {
			return (ConditionalExpr) this;
		}
		notifyPropertyChange(ObservableProperty.THEN_EXPR, this.thenExpr, thenExpr);
		if (this.thenExpr != null)
			this.thenExpr.setParentNode(null);
		this.thenExpr = thenExpr;
		setAsParentNodeOf(thenExpr);
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
	public ConditionalExpr clone() {
		return (ConditionalExpr) accept(new CloneVisitor(), null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetMetaModelGenerator")
	public ConditionalExprMetaModel getMetaModel() {
		return JavaParserMetaModel.conditionalExprMetaModel;
	}
}
