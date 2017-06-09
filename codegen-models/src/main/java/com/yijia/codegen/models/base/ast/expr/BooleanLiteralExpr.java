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
import com.yijia.codegen.models.base.ast.observer.ObservableProperty;
import com.yijia.codegen.models.base.ast.visitor.CloneVisitor;
import com.yijia.codegen.models.base.ast.visitor.GenericVisitor;
import com.yijia.codegen.models.base.ast.visitor.VoidVisitor;
import com.yijia.codegen.models.base.metamodel.BooleanLiteralExprMetaModel;
import com.yijia.codegen.models.base.metamodel.JavaParserMetaModel;

/**
 * The boolean literals. <br/>
 * <code>true</code> <br/>
 * <code>false</code>
 * @author Julio Vilmar Gesser
 */
public final class BooleanLiteralExpr extends LiteralExpr {

	private boolean value;

	public BooleanLiteralExpr() {
		this(null, false);
	}

	@AllFieldsConstructor
	public BooleanLiteralExpr(boolean value) {
		this(null, value);
	}

	/** This constructor is used by the parser and is considered private. */
	@Generated("com.yijia.codegen.models.base.generator.core.node.MainConstructorGenerator")
	public BooleanLiteralExpr(TokenRange tokenRange, boolean value) {
		super(tokenRange);
		setValue(value);
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
	public boolean getValue() {
		return value;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public BooleanLiteralExpr setValue(final boolean value) {
		if (value == this.value) {
			return (BooleanLiteralExpr) this;
		}
		notifyPropertyChange(ObservableProperty.VALUE, this.value, value);
		this.value = value;
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
	public BooleanLiteralExpr clone() {
		return (BooleanLiteralExpr) accept(new CloneVisitor(), null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetMetaModelGenerator")
	public BooleanLiteralExprMetaModel getMetaModel() {
		return JavaParserMetaModel.booleanLiteralExprMetaModel;
	}
}
