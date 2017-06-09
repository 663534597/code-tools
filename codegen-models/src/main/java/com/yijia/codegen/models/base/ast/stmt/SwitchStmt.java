/*
 * Copyright (C) 2007-2010 Júlio Vilmar Gesser. Copyright (C) 2011, 2013-2016 The JavaParser Team. This file is part of JavaParser. JavaParser can be used either under the terms of
 * a) the GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. b) the terms of
 * the Apache License You should have received a copy of both licenses in LICENCE.LGPL and LICENCE.APACHE. Please refer to those files for details. JavaParser is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General
 * Public License for more details.
 */
package com.yijia.codegen.models.base.ast.stmt;

import static com.yijia.codegen.models.base.utils.Utils.assertNotNull;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Generated;
import com.yijia.codegen.models.base.TokenRange;
import com.yijia.codegen.models.base.ast.AllFieldsConstructor;
import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.NodeList;
import com.yijia.codegen.models.base.ast.expr.Expression;
import com.yijia.codegen.models.base.ast.expr.NameExpr;
import com.yijia.codegen.models.base.ast.observer.ObservableProperty;
import com.yijia.codegen.models.base.ast.visitor.CloneVisitor;
import com.yijia.codegen.models.base.ast.visitor.GenericVisitor;
import com.yijia.codegen.models.base.ast.visitor.VoidVisitor;
import com.yijia.codegen.models.base.metamodel.JavaParserMetaModel;
import com.yijia.codegen.models.base.metamodel.SwitchStmtMetaModel;

/**
 * A switch statement. <br/>
 * In <code>switch(a) { ... }</code> the selector is "a", and the contents of the { ... } are the entries.
 * @author Julio Vilmar Gesser
 * @see SwitchEntryStmt
 */
public final class SwitchStmt extends Statement {

	private Expression selector;

	private NodeList<SwitchEntryStmt> entries;

	public SwitchStmt() {
		this(null, new NameExpr(), new NodeList<>());
	}

	@AllFieldsConstructor
	public SwitchStmt(final Expression selector, final NodeList<SwitchEntryStmt> entries) {
		this(null, selector, entries);
	}

	/** This constructor is used by the parser and is considered private. */
	@Generated("com.yijia.codegen.models.base.generator.core.node.MainConstructorGenerator")
	public SwitchStmt(TokenRange tokenRange, Expression selector, NodeList<SwitchEntryStmt> entries) {
		super(tokenRange);
		setSelector(selector);
		setEntries(entries);
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
	public NodeList<SwitchEntryStmt> getEntries() {
		return entries;
	}

	public SwitchEntryStmt getEntry(int i) {
		return getEntries().get(i);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public Expression getSelector() {
		return selector;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public SwitchStmt setEntries(final NodeList<SwitchEntryStmt> entries) {
		assertNotNull(entries);
		if (entries == this.entries) {
			return (SwitchStmt) this;
		}
		notifyPropertyChange(ObservableProperty.ENTRIES, this.entries, entries);
		if (this.entries != null)
			this.entries.setParentNode(null);
		this.entries = entries;
		setAsParentNodeOf(entries);
		return this;
	}

	public SwitchStmt setEntry(int i, SwitchEntryStmt entry) {
		getEntries().set(i, entry);
		return this;
	}

	public SwitchStmt addEntry(SwitchEntryStmt entry) {
		getEntries().add(entry);
		return this;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public SwitchStmt setSelector(final Expression selector) {
		assertNotNull(selector);
		if (selector == this.selector) {
			return (SwitchStmt) this;
		}
		notifyPropertyChange(ObservableProperty.SELECTOR, this.selector, selector);
		if (this.selector != null)
			this.selector.setParentNode(null);
		this.selector = selector;
		setAsParentNodeOf(selector);
		return this;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetNodeListsGenerator")
	public List<NodeList<?>> getNodeLists() {
		return Arrays.asList(getEntries());
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.RemoveMethodGenerator")
	public boolean remove(Node node) {
		if (node == null)
			return false;
		for (int i = 0; i < entries.size(); i++) {
			if (entries.get(i) == node) {
				entries.remove(i);
				return true;
			}
		}
		return super.remove(node);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.CloneGenerator")
	public SwitchStmt clone() {
		return (SwitchStmt) accept(new CloneVisitor(), null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetMetaModelGenerator")
	public SwitchStmtMetaModel getMetaModel() {
		return JavaParserMetaModel.switchStmtMetaModel;
	}
}
