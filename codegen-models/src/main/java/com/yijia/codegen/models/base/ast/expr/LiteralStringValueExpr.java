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
import com.yijia.codegen.models.base.metamodel.JavaParserMetaModel;
import com.yijia.codegen.models.base.metamodel.LiteralStringValueExprMetaModel;

/**
 * Any literal value that is stored internally as a String.
 */
public abstract class LiteralStringValueExpr extends LiteralExpr {

	protected String value;

	@AllFieldsConstructor
	public LiteralStringValueExpr(final String value) {
		this(null, value);
	}

	/** This constructor is used by the parser and is considered private. */
	@Generated("com.yijia.codegen.models.base.generator.core.node.MainConstructorGenerator")
	public LiteralStringValueExpr(TokenRange tokenRange, String value) {
		super(tokenRange);
		setValue(value);
		customInitialization();
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public String getValue() {
		return value;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public LiteralStringValueExpr setValue(final String value) {
		assertNotNull(value);
		if (value == this.value) {
			return (LiteralStringValueExpr) this;
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
	public LiteralStringValueExpr clone() {
		return (LiteralStringValueExpr) accept(new CloneVisitor(), null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetMetaModelGenerator")
	public LiteralStringValueExprMetaModel getMetaModel() {
		return JavaParserMetaModel.literalStringValueExprMetaModel;
	}
}
