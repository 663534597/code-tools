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
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithType;
import com.yijia.codegen.models.base.ast.observer.ObservableProperty;
import com.yijia.codegen.models.base.ast.type.ClassOrInterfaceType;
import com.yijia.codegen.models.base.ast.type.Type;
import com.yijia.codegen.models.base.ast.visitor.CloneVisitor;
import com.yijia.codegen.models.base.ast.visitor.GenericVisitor;
import com.yijia.codegen.models.base.ast.visitor.VoidVisitor;
import com.yijia.codegen.models.base.metamodel.CastExprMetaModel;
import com.yijia.codegen.models.base.metamodel.JavaParserMetaModel;

/**
 * A typecast. The (long) in <code>(long)15</code>
 * @author Julio Vilmar Gesser
 */
public final class CastExpr extends Expression implements NodeWithType<CastExpr, Type>, NodeWithExpression<CastExpr> {

	private Type type;

	private Expression expression;

	public CastExpr() {
		this(null, new ClassOrInterfaceType(), new NameExpr());
	}

	@AllFieldsConstructor
	public CastExpr(Type type, Expression expression) {
		this(null, type, expression);
	}

	/** This constructor is used by the parser and is considered private. */
	@Generated("com.yijia.codegen.models.base.generator.core.node.MainConstructorGenerator")
	public CastExpr(TokenRange tokenRange, Type type, Expression expression) {
		super(tokenRange);
		setType(type);
		setExpression(expression);
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
	public Expression getExpression() {
		return expression;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public Type getType() {
		return type;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public CastExpr setExpression(final Expression expression) {
		assertNotNull(expression);
		if (expression == this.expression) {
			return (CastExpr) this;
		}
		notifyPropertyChange(ObservableProperty.EXPRESSION, this.expression, expression);
		if (this.expression != null)
			this.expression.setParentNode(null);
		this.expression = expression;
		setAsParentNodeOf(expression);
		return this;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public CastExpr setType(final Type type) {
		assertNotNull(type);
		if (type == this.type) {
			return (CastExpr) this;
		}
		notifyPropertyChange(ObservableProperty.TYPE, this.type, type);
		if (this.type != null)
			this.type.setParentNode(null);
		this.type = type;
		setAsParentNodeOf(type);
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
	public CastExpr clone() {
		return (CastExpr) accept(new CloneVisitor(), null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetMetaModelGenerator")
	public CastExprMetaModel getMetaModel() {
		return JavaParserMetaModel.castExprMetaModel;
	}
}
