/*
 * Copyright (C) 2007-2010 Júlio Vilmar Gesser. Copyright (C) 2011, 2013-2016 The JavaParser Team. This file is part of JavaParser. JavaParser can be used either under the terms of
 * a) the GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. b) the terms of
 * the Apache License You should have received a copy of both licenses in LICENCE.LGPL and LICENCE.APACHE. Please refer to those files for details. JavaParser is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General
 * Public License for more details.
 */
package com.yijia.codegen.models.base.ast.expr;

import javax.annotation.Generated;
import com.yijia.codegen.models.base.TokenRange;
import com.yijia.codegen.models.base.ast.AllFieldsConstructor;
import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.visitor.CloneVisitor;
import com.yijia.codegen.models.base.ast.visitor.GenericVisitor;
import com.yijia.codegen.models.base.ast.visitor.VoidVisitor;
import com.yijia.codegen.models.base.metamodel.DoubleLiteralExprMetaModel;
import com.yijia.codegen.models.base.metamodel.JavaParserMetaModel;

/**
 * A float or a double constant. This value is stored exactly as found in the source. <br/>
 * <code>100.1f</code> <br/>
 * <code>23958D</code> <br/>
 * <code>0x4.5p1f</code>
 * @author Julio Vilmar Gesser
 */
public final class DoubleLiteralExpr extends LiteralStringValueExpr {

	public DoubleLiteralExpr() {
		this(null, "0");
	}

	@AllFieldsConstructor
	public DoubleLiteralExpr(final String value) {
		this(null, value);
	}

	/** This constructor is used by the parser and is considered private. */
	@Generated("com.yijia.codegen.models.base.generator.core.node.MainConstructorGenerator")
	public DoubleLiteralExpr(TokenRange tokenRange, String value) {
		super(tokenRange, value);
		customInitialization();
	}

	public DoubleLiteralExpr(final double value) {
		this(null, String.valueOf(value));
	}

	@Override
	public <R, A> R accept(final GenericVisitor<R, A> v, final A arg) {
		return v.visit(this, arg);
	}

	@Override
	public <A> void accept(final VoidVisitor<A> v, final A arg) {
		v.visit(this, arg);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.RemoveMethodGenerator")
	public boolean remove(Node node) {
		if (node == null)
			return false;
		return super.remove(node);
	}

	/**
	 * @return the literal value as a double
	 */
	public double asDouble() {
		return Double.parseDouble(value);
	}

	public DoubleLiteralExpr setDouble(double value) {
		this.value = String.valueOf(value);
		return this;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.CloneGenerator")
	public DoubleLiteralExpr clone() {
		return (DoubleLiteralExpr) accept(new CloneVisitor(), null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetMetaModelGenerator")
	public DoubleLiteralExprMetaModel getMetaModel() {
		return JavaParserMetaModel.doubleLiteralExprMetaModel;
	}
}
