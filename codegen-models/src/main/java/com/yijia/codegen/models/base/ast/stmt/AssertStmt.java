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
import com.yijia.codegen.models.base.ast.observer.ObservableProperty;
import com.yijia.codegen.models.base.ast.visitor.CloneVisitor;
import com.yijia.codegen.models.base.ast.visitor.GenericVisitor;
import com.yijia.codegen.models.base.ast.visitor.VoidVisitor;
import com.yijia.codegen.models.base.metamodel.AssertStmtMetaModel;
import com.yijia.codegen.models.base.metamodel.JavaParserMetaModel;

/**
 * A usage of the keyword "assert" <br/>
 * In <code>assert dead : "Wasn't expecting to be dead here";</code> the check is "dead" and the message is the string.
 * @author Julio Vilmar Gesser
 */
public final class AssertStmt extends Statement {

	private Expression check;

	private Expression message;

	public AssertStmt() {
		this(null, new BooleanLiteralExpr(), null);
	}

	public AssertStmt(final Expression check) {
		this(null, check, null);
	}

	@AllFieldsConstructor
	public AssertStmt(final Expression check, final Expression message) {
		this(null, check, message);
	}

	/** This constructor is used by the parser and is considered private. */
	@Generated("com.yijia.codegen.models.base.generator.core.node.MainConstructorGenerator")
	public AssertStmt(TokenRange tokenRange, Expression check, Expression message) {
		super(tokenRange);
		setCheck(check);
		setMessage(message);
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
	public Expression getCheck() {
		return check;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public Optional<Expression> getMessage() {
		return Optional.ofNullable(message);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public AssertStmt setCheck(final Expression check) {
		assertNotNull(check);
		if (check == this.check) {
			return (AssertStmt) this;
		}
		notifyPropertyChange(ObservableProperty.CHECK, this.check, check);
		if (this.check != null)
			this.check.setParentNode(null);
		this.check = check;
		setAsParentNodeOf(check);
		return this;
	}

	/**
	 * Sets the message
	 * @param msg the message, can be null
	 * @return this, the AssertStmt
	 */
	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public AssertStmt setMessage(final Expression message) {
		if (message == this.message) {
			return (AssertStmt) this;
		}
		notifyPropertyChange(ObservableProperty.MESSAGE, this.message, message);
		if (this.message != null)
			this.message.setParentNode(null);
		this.message = message;
		setAsParentNodeOf(message);
		return this;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.RemoveMethodGenerator")
	public boolean remove(Node node) {
		if (node == null)
			return false;
		if (message != null) {
			if (node == message) {
				removeMessage();
				return true;
			}
		}
		return super.remove(node);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.RemoveMethodGenerator")
	public AssertStmt removeMessage() {
		return setMessage((Expression) null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.CloneGenerator")
	public AssertStmt clone() {
		return (AssertStmt) accept(new CloneVisitor(), null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetMetaModelGenerator")
	public AssertStmtMetaModel getMetaModel() {
		return JavaParserMetaModel.assertStmtMetaModel;
	}
}
