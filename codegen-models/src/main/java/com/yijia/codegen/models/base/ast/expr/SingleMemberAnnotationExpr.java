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
import com.yijia.codegen.models.base.metamodel.JavaParserMetaModel;
import com.yijia.codegen.models.base.metamodel.SingleMemberAnnotationExprMetaModel;

/**
 * An annotation that has a single value. <br/>
 * <code>@Count(15)</code>
 * @author Julio Vilmar Gesser
 */
public final class SingleMemberAnnotationExpr extends AnnotationExpr {

	private Expression memberValue;

	public SingleMemberAnnotationExpr() {
		this(null, new Name(), new StringLiteralExpr());
	}

	@AllFieldsConstructor
	public SingleMemberAnnotationExpr(final Name name, final Expression memberValue) {
		this(null, name, memberValue);
	}

	/** This constructor is used by the parser and is considered private. */
	@Generated("com.yijia.codegen.models.base.generator.core.node.MainConstructorGenerator")
	public SingleMemberAnnotationExpr(TokenRange tokenRange, Name name, Expression memberValue) {
		super(tokenRange, name);
		setMemberValue(memberValue);
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
	public Expression getMemberValue() {
		return memberValue;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public SingleMemberAnnotationExpr setMemberValue(final Expression memberValue) {
		assertNotNull(memberValue);
		if (memberValue == this.memberValue) {
			return (SingleMemberAnnotationExpr) this;
		}
		notifyPropertyChange(ObservableProperty.MEMBER_VALUE, this.memberValue, memberValue);
		if (this.memberValue != null)
			this.memberValue.setParentNode(null);
		this.memberValue = memberValue;
		setAsParentNodeOf(memberValue);
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
	public SingleMemberAnnotationExpr clone() {
		return (SingleMemberAnnotationExpr) accept(new CloneVisitor(), null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetMetaModelGenerator")
	public SingleMemberAnnotationExprMetaModel getMetaModel() {
		return JavaParserMetaModel.singleMemberAnnotationExprMetaModel;
	}
}