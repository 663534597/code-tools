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
import java.util.Optional;
import javax.annotation.Generated;
import com.yijia.codegen.models.base.TokenRange;
import com.yijia.codegen.models.base.ast.AllFieldsConstructor;
import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.NodeList;
import com.yijia.codegen.models.base.ast.expr.Expression;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithStatements;
import com.yijia.codegen.models.base.ast.observer.ObservableProperty;
import com.yijia.codegen.models.base.ast.visitor.CloneVisitor;
import com.yijia.codegen.models.base.ast.visitor.GenericVisitor;
import com.yijia.codegen.models.base.ast.visitor.VoidVisitor;
import com.yijia.codegen.models.base.metamodel.JavaParserMetaModel;
import com.yijia.codegen.models.base.metamodel.SwitchEntryStmtMetaModel;

/**
 * One case in a switch statement. <br/>
 * 
 * <pre>
 * switch (i) {
 * 	case 1:
 * 	case 2:
 * 		System.out.println(444);
 * 		break;
 * 	default:
 * 		System.out.println(0);
 * }
 * </pre>
 * 
 * This contains three SwitchEntryStmts. <br/>
 * The first one has label 1 and no statements. <br/>
 * The second has label 2 and two statements (the println and the break). <br/>
 * The third, the default, has no label and one statement.
 * @author Julio Vilmar Gesser
 * @see SwitchStmt
 */
public final class SwitchEntryStmt extends Statement implements NodeWithStatements<SwitchEntryStmt> {

	private Expression label;

	private NodeList<Statement> statements;

	public SwitchEntryStmt() {
		this(null, null, new NodeList<>());
	}

	@AllFieldsConstructor
	public SwitchEntryStmt(final Expression label, final NodeList<Statement> statements) {
		this(null, label, statements);
	}

	/** This constructor is used by the parser and is considered private. */
	@Generated("com.yijia.codegen.models.base.generator.core.node.MainConstructorGenerator")
	public SwitchEntryStmt(TokenRange tokenRange, Expression label, NodeList<Statement> statements) {
		super(tokenRange);
		setLabel(label);
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
	public Optional<Expression> getLabel() {
		return Optional.ofNullable(label);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public NodeList<Statement> getStatements() {
		return statements;
	}

	/**
	 * Sets the label
	 * @param label the label, can be null
	 * @return this, the SwitchEntryStmt
	 */
	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public SwitchEntryStmt setLabel(final Expression label) {
		if (label == this.label) {
			return (SwitchEntryStmt) this;
		}
		notifyPropertyChange(ObservableProperty.LABEL, this.label, label);
		if (this.label != null)
			this.label.setParentNode(null);
		this.label = label;
		setAsParentNodeOf(label);
		return this;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public SwitchEntryStmt setStatements(final NodeList<Statement> statements) {
		assertNotNull(statements);
		if (statements == this.statements) {
			return (SwitchEntryStmt) this;
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
		if (label != null) {
			if (node == label) {
				removeLabel();
				return true;
			}
		}
		for (int i = 0; i < statements.size(); i++) {
			if (statements.get(i) == node) {
				statements.remove(i);
				return true;
			}
		}
		return super.remove(node);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.RemoveMethodGenerator")
	public SwitchEntryStmt removeLabel() {
		return setLabel((Expression) null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.CloneGenerator")
	public SwitchEntryStmt clone() {
		return (SwitchEntryStmt) accept(new CloneVisitor(), null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetMetaModelGenerator")
	public SwitchEntryStmtMetaModel getMetaModel() {
		return JavaParserMetaModel.switchEntryStmtMetaModel;
	}
}
