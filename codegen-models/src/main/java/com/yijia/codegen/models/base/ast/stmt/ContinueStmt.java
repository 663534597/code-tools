/*
 * Copyright (C) 2007-2010 Júlio Vilmar Gesser. Copyright (C) 2011, 2013-2016 The JavaParser Team. This file is part of JavaParser. JavaParser can be used either under the terms of
 * a) the GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. b) the terms of
 * the Apache License You should have received a copy of both licenses in LICENCE.LGPL and LICENCE.APACHE. Please refer to those files for details. JavaParser is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General
 * Public License for more details.
 */
package com.yijia.codegen.models.base.ast.stmt;

import java.util.Optional;
import javax.annotation.Generated;
import com.yijia.codegen.models.base.TokenRange;
import com.yijia.codegen.models.base.ast.AllFieldsConstructor;
import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.expr.SimpleName;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithOptionalLabel;
import com.yijia.codegen.models.base.ast.observer.ObservableProperty;
import com.yijia.codegen.models.base.ast.visitor.CloneVisitor;
import com.yijia.codegen.models.base.ast.visitor.GenericVisitor;
import com.yijia.codegen.models.base.ast.visitor.VoidVisitor;
import com.yijia.codegen.models.base.metamodel.ContinueStmtMetaModel;
import com.yijia.codegen.models.base.metamodel.JavaParserMetaModel;

/**
 * A continue statement with an optional label; <br/>
 * <code>continue brains;</code> <br/>
 * <code>continue;</code>
 * @author Julio Vilmar Gesser
 */
public final class ContinueStmt extends Statement implements NodeWithOptionalLabel<ContinueStmt> {

	private SimpleName label;

	public ContinueStmt() {
		this(null, null);
	}

	public ContinueStmt(final String label) {
		this(null, new SimpleName(label));
	}

	@AllFieldsConstructor
	public ContinueStmt(final SimpleName label) {
		this(null, label);
	}

	/** This constructor is used by the parser and is considered private. */
	@Generated("com.yijia.codegen.models.base.generator.core.node.MainConstructorGenerator")
	public ContinueStmt(TokenRange tokenRange, SimpleName label) {
		super(tokenRange);
		setLabel(label);
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
	public Optional<SimpleName> getLabel() {
		return Optional.ofNullable(label);
	}

	/**
	 * Sets the label
	 * @param label the label, can be null
	 * @return this, the ContinueStmt
	 */
	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public ContinueStmt setLabel(final SimpleName label) {
		if (label == this.label) {
			return (ContinueStmt) this;
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
		if (label != null) {
			if (node == label) {
				removeLabel();
				return true;
			}
		}
		return super.remove(node);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.RemoveMethodGenerator")
	public ContinueStmt removeLabel() {
		return setLabel((SimpleName) null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.CloneGenerator")
	public ContinueStmt clone() {
		return (ContinueStmt) accept(new CloneVisitor(), null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetMetaModelGenerator")
	public ContinueStmtMetaModel getMetaModel() {
		return JavaParserMetaModel.continueStmtMetaModel;
	}
}
