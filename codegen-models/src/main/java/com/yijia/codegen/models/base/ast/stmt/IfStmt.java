/*
 * Copyright (C) 2007-2010 Júlio Vilmar Gesser. Copyright (C) 2011, 2013-2016 The JavaParser Team. This file is part of JavaParser. JavaParser can be used either under the terms of
 * a) the GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. b) the terms of
 * the Apache License You should have received a copy of both licenses in LICENCE.LGPL and LICENCE.APACHE. Please refer to those files for details. JavaParser is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General
 * Public License for more details.
 */
package com.yijia.codegen.models.base.ast.stmt;

import static com.yijia.codegen.models.base.utils.Utils.assertNotNull;
import java.util.Optional;
import javax.annotation.Generated;
import com.yijia.codegen.models.base.TokenRange;
import com.yijia.codegen.models.base.ast.AllFieldsConstructor;
import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.expr.BooleanLiteralExpr;
import com.yijia.codegen.models.base.ast.expr.Expression;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithCondition;
import com.yijia.codegen.models.base.ast.observer.ObservableProperty;
import com.yijia.codegen.models.base.ast.visitor.CloneVisitor;
import com.yijia.codegen.models.base.ast.visitor.GenericVisitor;
import com.yijia.codegen.models.base.ast.visitor.VoidVisitor;
import com.yijia.codegen.models.base.metamodel.DerivedProperty;
import com.yijia.codegen.models.base.metamodel.IfStmtMetaModel;
import com.yijia.codegen.models.base.metamodel.JavaParserMetaModel;

/**
 * An if-then-else statement. The else is optional. <br/>
 * In <code>if(a==5) hurray() else boo();</code> the condition is a==5, hurray() is the thenStmt, and boo() is the elseStmt.
 * @author Julio Vilmar Gesser
 */
public final class IfStmt extends Statement implements NodeWithCondition<IfStmt> {

	private Expression condition;

	private Statement thenStmt;

	private Statement elseStmt;

	public IfStmt() {
		this(null, new BooleanLiteralExpr(), new ReturnStmt(), null);
	}

	@AllFieldsConstructor
	public IfStmt(final Expression condition, final Statement thenStmt, final Statement elseStmt) {
		this(null, condition, thenStmt, elseStmt);
	}

	/** This constructor is used by the parser and is considered private. */
	@Generated("com.yijia.codegen.models.base.generator.core.node.MainConstructorGenerator")
	public IfStmt(TokenRange tokenRange, Expression condition, Statement thenStmt, Statement elseStmt) {
		super(tokenRange);
		setCondition(condition);
		setThenStmt(thenStmt);
		setElseStmt(elseStmt);
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
	public Expression getCondition() {
		return condition;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public Optional<Statement> getElseStmt() {
		return Optional.ofNullable(elseStmt);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public Statement getThenStmt() {
		return thenStmt;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public IfStmt setCondition(final Expression condition) {
		assertNotNull(condition);
		if (condition == this.condition) {
			return (IfStmt) this;
		}
		notifyPropertyChange(ObservableProperty.CONDITION, this.condition, condition);
		if (this.condition != null)
			this.condition.setParentNode(null);
		this.condition = condition;
		setAsParentNodeOf(condition);
		return this;
	}

	/**
	 * Sets the elseStmt
	 * @param elseStmt the elseStmt, can be null
	 * @return this, the IfStmt
	 */
	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public IfStmt setElseStmt(final Statement elseStmt) {
		if (elseStmt == this.elseStmt) {
			return (IfStmt) this;
		}
		notifyPropertyChange(ObservableProperty.ELSE_STMT, this.elseStmt, elseStmt);
		if (this.elseStmt != null)
			this.elseStmt.setParentNode(null);
		this.elseStmt = elseStmt;
		setAsParentNodeOf(elseStmt);
		return this;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public IfStmt setThenStmt(final Statement thenStmt) {
		assertNotNull(thenStmt);
		if (thenStmt == this.thenStmt) {
			return (IfStmt) this;
		}
		notifyPropertyChange(ObservableProperty.THEN_STMT, this.thenStmt, thenStmt);
		if (this.thenStmt != null)
			this.thenStmt.setParentNode(null);
		this.thenStmt = thenStmt;
		setAsParentNodeOf(thenStmt);
		return this;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.RemoveMethodGenerator")
	public boolean remove(Node node) {
		if (node == null)
			return false;
		if (elseStmt != null) {
			if (node == elseStmt) {
				removeElseStmt();
				return true;
			}
		}
		return super.remove(node);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.RemoveMethodGenerator")
	public IfStmt removeElseStmt() {
		return setElseStmt((Statement) null);
	}

	@DerivedProperty
	public boolean hasThenBlock() {
		return thenStmt instanceof BlockStmt;
	}

	@DerivedProperty
	public boolean hasElseBlock() {
		return elseStmt instanceof BlockStmt || elseStmt instanceof IfStmt;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.CloneGenerator")
	public IfStmt clone() {
		return (IfStmt) accept(new CloneVisitor(), null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetMetaModelGenerator")
	public IfStmtMetaModel getMetaModel() {
		return JavaParserMetaModel.ifStmtMetaModel;
	}
}
