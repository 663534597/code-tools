/*
 * Copyright (C) 2007-2010 Júlio Vilmar Gesser. Copyright (C) 2011, 2013-2016 The JavaParser Team. This file is part of JavaParser. JavaParser can be used either under the terms of
 * a) the GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. b) the terms of
 * the Apache License You should have received a copy of both licenses in LICENCE.LGPL and LICENCE.APACHE. Please refer to those files for details. JavaParser is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General
 * Public License for more details.
 */
package com.yijia.codegen.models.base.ast.body;

import static com.yijia.codegen.models.base.utils.Utils.assertNonEmpty;
import static com.yijia.codegen.models.base.utils.Utils.assertNotNull;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;
import com.yijia.codegen.models.base.TokenRange;
import com.yijia.codegen.models.base.ast.AllFieldsConstructor;
import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.expr.Expression;
import com.yijia.codegen.models.base.ast.expr.NameExpr;
import com.yijia.codegen.models.base.ast.expr.SimpleName;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithSimpleName;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithType;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithVariables;
import com.yijia.codegen.models.base.ast.observer.AstObserverAdapter;
import com.yijia.codegen.models.base.ast.observer.ObservableProperty;
import com.yijia.codegen.models.base.ast.type.Type;
import com.yijia.codegen.models.base.ast.visitor.CloneVisitor;
import com.yijia.codegen.models.base.ast.visitor.GenericVisitor;
import com.yijia.codegen.models.base.ast.visitor.VoidVisitor;
import com.yijia.codegen.models.base.metamodel.JavaParserMetaModel;
import com.yijia.codegen.models.base.metamodel.NonEmptyProperty;
import com.yijia.codegen.models.base.metamodel.VariableDeclaratorMetaModel;

/**
 * The declaration of a variable.<br/>
 * In <code>int x = 14, y = 3;</code> "x = 14" and " y = 3" are VariableDeclarators.
 * @author Julio Vilmar Gesser
 */
public final class VariableDeclarator extends Node implements NodeWithType<VariableDeclarator, Type>, NodeWithSimpleName<VariableDeclarator> {

	private SimpleName name;

	@NonEmptyProperty
	private Expression initializer;

	private Type type;

	public VariableDeclarator() {
		this(null, new SimpleName(), null);
	}

	public VariableDeclarator(Type type, String variableName) {
		this(null, type, new SimpleName(variableName), null);
	}

	public VariableDeclarator(Type type, SimpleName name) {
		this(null, type, name, null);
	}

	public VariableDeclarator(Type type, String variableName, Expression initializer) {
		this(null, type, new SimpleName(variableName), initializer);
	}

	/**
	 * Defines the declaration of a variable.
	 * @param name The identifier for this variable. IE. The variables name.
	 * @param initializer What this variable should be initialized to. An {@link com.yijia.codegen.models.base.ast.expr.AssignExpr} is unnecessary as the <code>=</code> operator is already
	 *            added.
	 */
	@AllFieldsConstructor
	public VariableDeclarator(Type type, SimpleName name, Expression initializer) {
		this(null, type, name, initializer);
	}

	/** This constructor is used by the parser and is considered private. */
	@Generated("com.yijia.codegen.models.base.generator.core.node.MainConstructorGenerator")
	public VariableDeclarator(TokenRange tokenRange, Type type, SimpleName name, Expression initializer) {
		super(tokenRange);
		setType(type);
		setName(name);
		setInitializer(initializer);
		customInitialization();
	}

	@Override
	protected void customInitialization() {
		// We register an observer on the type property. When it is changed the MaximumCommonType is changes as well,
		// because it is derived from the type of the variables it contains, for this reason we notify about the change
		register(new AstObserverAdapter() {

			@Override
			public void propertyChange(Node observedNode, ObservableProperty property, Object oldValue, Object newValue) {
				if (property == ObservableProperty.TYPE) {
					VariableDeclarator vd = VariableDeclarator.this;
					if (vd.getParentNode().isPresent() && vd.getParentNode().get() instanceof NodeWithVariables) {
						NodeWithVariables nodeWithVariables = (NodeWithVariables) vd.getParentNode().get();
						// We calculate the value the property will assume after the change will be completed
						Type currentMaxCommonType = nodeWithVariables.getMaximumCommonType();
						List<Type> types = new LinkedList<>();
						int index = nodeWithVariables.getVariables().indexOf(vd);
						for (int i = 0; i < nodeWithVariables.getVariables().size(); i++) {
							if (i == index) {
								types.add((Type) newValue);
							} else {
								types.add(nodeWithVariables.getVariable(i).getType());
							}
						}
						Type newMaxCommonType = NodeWithVariables.calculateMaximumCommonType(types);
						((Node) nodeWithVariables).notifyPropertyChange(ObservableProperty.MAXIMUM_COMMON_TYPE, currentMaxCommonType, newMaxCommonType);
					}
				}
			}
		});
	}

	@Override
	public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
		return v.visit(this, arg);
	}

	@Override
	public <A> void accept(VoidVisitor<A> v, A arg) {
		v.visit(this, arg);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public Optional<Expression> getInitializer() {
		return Optional.ofNullable(initializer);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public SimpleName getName() {
		return name;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public VariableDeclarator setName(final SimpleName name) {
		assertNotNull(name);
		if (name == this.name) {
			return (VariableDeclarator) this;
		}
		notifyPropertyChange(ObservableProperty.NAME, this.name, name);
		if (this.name != null)
			this.name.setParentNode(null);
		this.name = name;
		setAsParentNodeOf(name);
		return this;
	}

	/**
	 * Sets the initializer expression
	 * @param initializer the initializer expression, can be null
	 * @return this, the VariableDeclarator
	 */
	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public VariableDeclarator setInitializer(final Expression initializer) {
		if (initializer == this.initializer) {
			return (VariableDeclarator) this;
		}
		notifyPropertyChange(ObservableProperty.INITIALIZER, this.initializer, initializer);
		if (this.initializer != null)
			this.initializer.setParentNode(null);
		this.initializer = initializer;
		setAsParentNodeOf(initializer);
		return this;
	}

	/**
	 * Will create a {@link NameExpr} with the initializer param
	 * @param init the initializer expression, can be null
	 * @return this, the VariableDeclarator
	 */
	public VariableDeclarator setInitializer(String init) {
		return setInitializer(new NameExpr(assertNonEmpty(init)));
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public Type getType() {
		return type;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public VariableDeclarator setType(final Type type) {
		assertNotNull(type);
		if (type == this.type) {
			return (VariableDeclarator) this;
		}
		notifyPropertyChange(ObservableProperty.TYPE, this.type, type);
		if (this.type != null)
			this.type.setParentNode(null);
		this.type = type;
		setAsParentNodeOf(type);
		return this;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.RemoveMethodGenerator")
	public boolean remove(Node node) {
		if (node == null)
			return false;
		if (initializer != null) {
			if (node == initializer) {
				removeInitializer();
				return true;
			}
		}
		return super.remove(node);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.RemoveMethodGenerator")
	public VariableDeclarator removeInitializer() {
		return setInitializer((Expression) null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.CloneGenerator")
	public VariableDeclarator clone() {
		return (VariableDeclarator) accept(new CloneVisitor(), null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetMetaModelGenerator")
	public VariableDeclaratorMetaModel getMetaModel() {
		return JavaParserMetaModel.variableDeclaratorMetaModel;
	}
}
