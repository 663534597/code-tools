/*
 * Copyright (C) 2007-2010 Júlio Vilmar Gesser. Copyright (C) 2011, 2013-2016 The JavaParser Team. This file is part of JavaParser. JavaParser can be used either under the terms of
 * a) the GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. b) the terms of
 * the Apache License You should have received a copy of both licenses in LICENCE.LGPL and LICENCE.APACHE. Please refer to those files for details. JavaParser is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General
 * Public License for more details.
 */
package com.yijia.codegen.models.base.ast.stmt;

import static com.yijia.codegen.models.base.utils.Utils.assertNotNull;
import java.util.EnumSet;
import javax.annotation.Generated;
import com.yijia.codegen.models.base.TokenRange;
import com.yijia.codegen.models.base.ast.AllFieldsConstructor;
import com.yijia.codegen.models.base.ast.Modifier;
import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.NodeList;
import com.yijia.codegen.models.base.ast.body.Parameter;
import com.yijia.codegen.models.base.ast.expr.AnnotationExpr;
import com.yijia.codegen.models.base.ast.expr.SimpleName;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithBlockStmt;
import com.yijia.codegen.models.base.ast.observer.ObservableProperty;
import com.yijia.codegen.models.base.ast.type.ClassOrInterfaceType;
import com.yijia.codegen.models.base.ast.visitor.CloneVisitor;
import com.yijia.codegen.models.base.ast.visitor.GenericVisitor;
import com.yijia.codegen.models.base.ast.visitor.VoidVisitor;
import com.yijia.codegen.models.base.metamodel.CatchClauseMetaModel;
import com.yijia.codegen.models.base.metamodel.JavaParserMetaModel;

/**
 * The catch part of a try-catch-finally. <br/>
 * In <code>try { ... } catch (Exception e) { ... }</code> the CatchClause is <code>catch (Exception e) { ... }</code>. Exception e is the parameter. The { ... } is the body.
 * @author Julio Vilmar Gesser
 */
public final class CatchClause extends Node implements NodeWithBlockStmt<CatchClause> {

	private Parameter parameter;

	private BlockStmt body;

	public CatchClause() {
		this(null, new Parameter(), new BlockStmt());
	}

	public CatchClause(final EnumSet<Modifier> exceptModifier, final NodeList<AnnotationExpr> exceptAnnotations, final ClassOrInterfaceType exceptType, final SimpleName exceptName,
			final BlockStmt body) {
		this(null, new Parameter(null, exceptModifier, exceptAnnotations, exceptType, false, new NodeList<>(), exceptName), body);
	}

	@AllFieldsConstructor
	public CatchClause(final Parameter parameter, final BlockStmt body) {
		this(null, parameter, body);
	}

	/** This constructor is used by the parser and is considered private. */
	@Generated("com.yijia.codegen.models.base.generator.core.node.MainConstructorGenerator")
	public CatchClause(TokenRange tokenRange, Parameter parameter, BlockStmt body) {
		super(tokenRange);
		setParameter(parameter);
		setBody(body);
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

	/**
	 * Note that the type of the Parameter can be a UnionType. In this case, any annotations found at the start of the catch(@X A a |...) are found directly in the Parameter.
	 * Annotations that are on the second or later type - catch(A a | @X B b ...) are found on those types.
	 */
	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public Parameter getParameter() {
		return parameter;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public CatchClause setParameter(final Parameter parameter) {
		assertNotNull(parameter);
		if (parameter == this.parameter) {
			return (CatchClause) this;
		}
		notifyPropertyChange(ObservableProperty.PARAMETER, this.parameter, parameter);
		if (this.parameter != null)
			this.parameter.setParentNode(null);
		this.parameter = parameter;
		setAsParentNodeOf(parameter);
		return this;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public BlockStmt getBody() {
		return body;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public CatchClause setBody(final BlockStmt body) {
		assertNotNull(body);
		if (body == this.body) {
			return (CatchClause) this;
		}
		notifyPropertyChange(ObservableProperty.BODY, this.body, body);
		if (this.body != null)
			this.body.setParentNode(null);
		this.body = body;
		setAsParentNodeOf(body);
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
	public CatchClause clone() {
		return (CatchClause) accept(new CloneVisitor(), null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetMetaModelGenerator")
	public CatchClauseMetaModel getMetaModel() {
		return JavaParserMetaModel.catchClauseMetaModel;
	}
}
