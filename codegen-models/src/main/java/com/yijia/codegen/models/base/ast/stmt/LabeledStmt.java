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
import com.yijia.codegen.models.base.ast.expr.SimpleName;
import com.yijia.codegen.models.base.ast.observer.ObservableProperty;
import com.yijia.codegen.models.base.ast.visitor.CloneVisitor;
import com.yijia.codegen.models.base.ast.visitor.GenericVisitor;
import com.yijia.codegen.models.base.ast.visitor.VoidVisitor;
import com.yijia.codegen.models.base.metamodel.JavaParserMetaModel;
import com.yijia.codegen.models.base.metamodel.LabeledStmtMetaModel;

/**
 * A statement that is labeled, like <code>label123: println("continuing");</code>
 * @author Julio Vilmar Gesser
 */
public final class LabeledStmt extends Statement {

	private SimpleName label;

	private Statement statement;

	public LabeledStmt() {
		this(null, new SimpleName(), new ReturnStmt());
	}

	public LabeledStmt(final String label, final Statement statement) {
		this(null, new SimpleName(label), statement);
	}

	@AllFieldsConstructor
	public LabeledStmt(final SimpleName label, final Statement statement) {
		this(null, label, statement);
	}

	/** This constructor is used by the parser and is considered private. */
	@Generated("com.yijia.codegen.models.base.generator.core.node.MainConstructorGenerator")
	public LabeledStmt(TokenRange tokenRange, SimpleName label, Statement statement) {
		super(tokenRange);
		setLabel(label);
		setStatement(statement);
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
	public Statement getStatement() {
		return statement;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public LabeledStmt setStatement(final Statement statement) {
		assertNotNull(statement);
		if (statement == this.statement) {
			return (LabeledStmt) this;
		}
		notifyPropertyChange(ObservableProperty.STATEMENT, this.statement, statement);
		if (this.statement != null)
			this.statement.setParentNode(null);
		this.statement = statement;
		setAsParentNodeOf(statement);
		return this;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public SimpleName getLabel() {
		return label;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public LabeledStmt setLabel(final SimpleName label) {
		assertNotNull(label);
		if (label == this.label) {
			return (LabeledStmt) this;
		}
		notifyPropertyChange(ObservableProperty.LABEL, this.label, label);
		if (this.label != null)
			this.label.setParentNode(null);
		this.label = label;
		setAsParentNodeOf(label);
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
	public LabeledStmt clone() {
		return (LabeledStmt) accept(new CloneVisitor(), null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetMetaModelGenerator")
	public LabeledStmtMetaModel getMetaModel() {
		return JavaParserMetaModel.labeledStmtMetaModel;
	}
}
