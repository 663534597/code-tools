/*
 * Copyright (C) 2007-2010 Júlio Vilmar Gesser. Copyright (C) 2011, 2013-2016 The JavaParser Team. This file is part of JavaParser. JavaParser can be used either under the terms of
 * a) the GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. b) the terms of
 * the Apache License You should have received a copy of both licenses in LICENCE.LGPL and LICENCE.APACHE. Please refer to those files for details. JavaParser is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General
 * Public License for more details.
 */
package com.yijia.codegen.models.base.ast.stmt;

import static com.yijia.codegen.models.base.utils.Utils.assertNotNull;
import javax.annotation.Generated;
import com.yijia.codegen.models.base.TokenRange;
import com.yijia.codegen.models.base.ast.AllFieldsConstructor;
import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.expr.Expression;
import com.yijia.codegen.models.base.ast.expr.NameExpr;
import com.yijia.codegen.models.base.ast.expr.VariableDeclarationExpr;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithBody;
import com.yijia.codegen.models.base.ast.observer.ObservableProperty;
import com.yijia.codegen.models.base.ast.visitor.CloneVisitor;
import com.yijia.codegen.models.base.ast.visitor.GenericVisitor;
import com.yijia.codegen.models.base.ast.visitor.VoidVisitor;
import com.yijia.codegen.models.base.metamodel.ForeachStmtMetaModel;
import com.yijia.codegen.models.base.metamodel.JavaParserMetaModel;

/**
 * A for-each statement. <br/>
 * <code>for(Object o: objects) { ... }</code>
 * @author Julio Vilmar Gesser
 */
public final class ForeachStmt extends Statement implements NodeWithBody<ForeachStmt> {

	private VariableDeclarationExpr variable;

	private Expression iterable;

	private Statement body;

	public ForeachStmt() {
		this(null, new VariableDeclarationExpr(), new NameExpr(), new ReturnStmt());
	}

	@AllFieldsConstructor
	public ForeachStmt(final VariableDeclarationExpr variable, final Expression iterable, final Statement body) {
		this(null, variable, iterable, body);
	}

	/** This constructor is used by the parser and is considered private. */
	@Generated("com.yijia.codegen.models.base.generator.core.node.MainConstructorGenerator")
	public ForeachStmt(TokenRange tokenRange, VariableDeclarationExpr variable, Expression iterable, Statement body) {
		super(tokenRange);
		setVariable(variable);
		setIterable(iterable);
		setBody(body);
		customInitialization();
	}

	public ForeachStmt(VariableDeclarationExpr variable, String iterable, BlockStmt body) {
		this(null, variable, new NameExpr(iterable), body);
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
	public Statement getBody() {
		return body;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public Expression getIterable() {
		return iterable;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public VariableDeclarationExpr getVariable() {
		return variable;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public ForeachStmt setBody(final Statement body) {
		assertNotNull(body);
		if (body == this.body) {
			return (ForeachStmt) this;
		}
		notifyPropertyChange(ObservableProperty.BODY, this.body, body);
		if (this.body != null)
			this.body.setParentNode(null);
		this.body = body;
		setAsParentNodeOf(body);
		return this;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public ForeachStmt setIterable(final Expression iterable) {
		assertNotNull(iterable);
		if (iterable == this.iterable) {
			return (ForeachStmt) this;
		}
		notifyPropertyChange(ObservableProperty.ITERABLE, this.iterable, iterable);
		if (this.iterable != null)
			this.iterable.setParentNode(null);
		this.iterable = iterable;
		setAsParentNodeOf(iterable);
		return this;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public ForeachStmt setVariable(final VariableDeclarationExpr variable) {
		assertNotNull(variable);
		if (variable == this.variable) {
			return (ForeachStmt) this;
		}
		notifyPropertyChange(ObservableProperty.VARIABLE, this.variable, variable);
		if (this.variable != null)
			this.variable.setParentNode(null);
		this.variable = variable;
		setAsParentNodeOf(variable);
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
	public ForeachStmt clone() {
		return (ForeachStmt) accept(new CloneVisitor(), null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetMetaModelGenerator")
	public ForeachStmtMetaModel getMetaModel() {
		return JavaParserMetaModel.foreachStmtMetaModel;
	}
}
