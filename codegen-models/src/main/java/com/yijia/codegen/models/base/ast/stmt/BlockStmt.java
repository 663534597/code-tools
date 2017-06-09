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
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithStatements;
import com.yijia.codegen.models.base.ast.observer.ObservableProperty;
import com.yijia.codegen.models.base.ast.visitor.CloneVisitor;
import com.yijia.codegen.models.base.ast.visitor.GenericVisitor;
import com.yijia.codegen.models.base.ast.visitor.VoidVisitor;
import com.yijia.codegen.models.base.metamodel.BlockStmtMetaModel;
import com.yijia.codegen.models.base.metamodel.JavaParserMetaModel;

/**
 * Statements in between { and }.
 * @author Julio Vilmar Gesser
 */
public final class BlockStmt extends Statement implements NodeWithStatements<BlockStmt> {

	private NodeList<Statement> statements;

	public BlockStmt() {
		this(null, new NodeList<>());
	}

	@AllFieldsConstructor
	public BlockStmt(final NodeList<Statement> statements) {
		this(null, statements);
	}

	/** This constructor is used by the parser and is considered private. */
	@Generated("com.yijia.codegen.models.base.generator.core.node.MainConstructorGenerator")
	public BlockStmt(TokenRange tokenRange, NodeList<Statement> statements) {
		super(tokenRange);
		setStatements(statements);
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
	public NodeList<Statement> getStatements() {
		return statements;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public BlockStmt setStatements(final NodeList<Statement> statements) {
		assertNotNull(statements);
		if (statements == this.statements) {
			return (BlockStmt) this;
		}
		notifyPropertyChange(ObservableProperty.STATEMENTS, this.statements, statements);
		if (this.statements != null)
			this.statements.setParentNode(null);
		this.statements = statements;
		setAsParentNodeOf(statements);
		return this;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetNodeListsGenerator")
	public List<NodeList<?>> getNodeLists() {
		return Arrays.asList(getStatements());
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.RemoveMethodGenerator")
	public boolean remove(Node node) {
		if (node == null)
			return false;
		for (int i = 0; i < statements.size(); i++) {
			if (statements.get(i) == node) {
				statements.remove(i);
				return true;
			}
		}
		return super.remove(node);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.CloneGenerator")
	public BlockStmt clone() {
		return (BlockStmt) accept(new CloneVisitor(), null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetMetaModelGenerator")
	public BlockStmtMetaModel getMetaModel() {
		return JavaParserMetaModel.blockStmtMetaModel;
	}
}
