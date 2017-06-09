/*
 * Copyright (C) 2007-2010 Júlio Vilmar Gesser. Copyright (C) 2011, 2013-2016 The JavaParser Team. This file is part of JavaParser. JavaParser can be used either under the terms of
 * a) the GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. b) the terms of
 * the Apache License You should have received a copy of both licenses in LICENCE.LGPL and LICENCE.APACHE. Please refer to those files for details. JavaParser is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General
 * Public License for more details.
 */
package com.yijia.codegen.models.base.ast.expr;

import static com.yijia.codegen.models.base.ast.NodeList.nodeList;
import static com.yijia.codegen.models.base.utils.Utils.assertNotNull;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Generated;
import com.yijia.codegen.models.base.TokenRange;
import com.yijia.codegen.models.base.ast.AllFieldsConstructor;
import com.yijia.codegen.models.base.ast.Modifier;
import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.NodeList;
import com.yijia.codegen.models.base.ast.body.VariableDeclarator;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithAnnotations;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithVariables;
import com.yijia.codegen.models.base.ast.nodeTypes.modifiers.NodeWithFinalModifier;
import com.yijia.codegen.models.base.ast.observer.ObservableProperty;
import com.yijia.codegen.models.base.ast.type.Type;
import com.yijia.codegen.models.base.ast.visitor.CloneVisitor;
import com.yijia.codegen.models.base.ast.visitor.GenericVisitor;
import com.yijia.codegen.models.base.ast.visitor.VoidVisitor;
import com.yijia.codegen.models.base.metamodel.JavaParserMetaModel;
import com.yijia.codegen.models.base.metamodel.NonEmptyProperty;
import com.yijia.codegen.models.base.metamodel.VariableDeclarationExprMetaModel;

/**
 * A declaration of variables. It is an expression, so it can be put in places like the initializer of a for loop, or the resources part of the try statement. <br/>
 * <code>final int x=3, y=55</code>
 * @author Julio Vilmar Gesser
 */
public final class VariableDeclarationExpr extends Expression
		implements NodeWithFinalModifier<VariableDeclarationExpr>, NodeWithAnnotations<VariableDeclarationExpr>, NodeWithVariables<VariableDeclarationExpr> {

	private EnumSet<Modifier> modifiers;

	private NodeList<AnnotationExpr> annotations;

	@NonEmptyProperty
	private NodeList<VariableDeclarator> variables;

	public VariableDeclarationExpr() {
		this(null, EnumSet.noneOf(Modifier.class), new NodeList<>(), new NodeList<>());
	}

	public VariableDeclarationExpr(final Type type, String variableName) {
		this(null, EnumSet.noneOf(Modifier.class), new NodeList<>(), nodeList(new VariableDeclarator(type, variableName)));
	}

	public VariableDeclarationExpr(VariableDeclarator var) {
		this(null, EnumSet.noneOf(Modifier.class), new NodeList<>(), nodeList(var));
	}

	public VariableDeclarationExpr(final Type type, String variableName, Modifier... modifiers) {
		this(null, Arrays.stream(modifiers).collect(Collectors.toCollection(() -> EnumSet.noneOf(Modifier.class))), new NodeList<>(),
				nodeList(new VariableDeclarator(type, variableName)));
	}

	public VariableDeclarationExpr(VariableDeclarator var, Modifier... modifiers) {
		this(null, Arrays.stream(modifiers).collect(Collectors.toCollection(() -> EnumSet.noneOf(Modifier.class))), new NodeList<>(), nodeList(var));
	}

	public VariableDeclarationExpr(final NodeList<VariableDeclarator> variables) {
		this(null, EnumSet.noneOf(Modifier.class), new NodeList<>(), variables);
	}

	public VariableDeclarationExpr(final EnumSet<Modifier> modifiers, final NodeList<VariableDeclarator> variables) {
		this(null, modifiers, new NodeList<>(), variables);
	}

	@AllFieldsConstructor
	public VariableDeclarationExpr(final EnumSet<Modifier> modifiers, final NodeList<AnnotationExpr> annotations, final NodeList<VariableDeclarator> variables) {
		this(null, modifiers, annotations, variables);
	}

	/** This constructor is used by the parser and is considered private. */
	@Generated("com.yijia.codegen.models.base.generator.core.node.MainConstructorGenerator")
	public VariableDeclarationExpr(TokenRange tokenRange, EnumSet<Modifier> modifiers, NodeList<AnnotationExpr> annotations, NodeList<VariableDeclarator> variables) {
		super(tokenRange);
		setModifiers(modifiers);
		setAnnotations(annotations);
		setVariables(variables);
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
	public NodeList<AnnotationExpr> getAnnotations() {
		return annotations;
	}

	/**
	 * Return the modifiers of this variable declaration.
	 * @return modifiers
	 * @see Modifier
	 */
	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public EnumSet<Modifier> getModifiers() {
		return modifiers;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public NodeList<VariableDeclarator> getVariables() {
		return variables;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public VariableDeclarationExpr setAnnotations(final NodeList<AnnotationExpr> annotations) {
		assertNotNull(annotations);
		if (annotations == this.annotations) {
			return (VariableDeclarationExpr) this;
		}
		notifyPropertyChange(ObservableProperty.ANNOTATIONS, this.annotations, annotations);
		if (this.annotations != null)
			this.annotations.setParentNode(null);
		this.annotations = annotations;
		setAsParentNodeOf(annotations);
		return this;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public VariableDeclarationExpr setModifiers(final EnumSet<Modifier> modifiers) {
		assertNotNull(modifiers);
		if (modifiers == this.modifiers) {
			return (VariableDeclarationExpr) this;
		}
		notifyPropertyChange(ObservableProperty.MODIFIERS, this.modifiers, modifiers);
		this.modifiers = modifiers;
		return this;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public VariableDeclarationExpr setVariables(final NodeList<VariableDeclarator> variables) {
		assertNotNull(variables);
		if (variables == this.variables) {
			return (VariableDeclarationExpr) this;
		}
		notifyPropertyChange(ObservableProperty.VARIABLES, this.variables, variables);
		if (this.variables != null)
			this.variables.setParentNode(null);
		this.variables = variables;
		setAsParentNodeOf(variables);
		return this;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetNodeListsGenerator")
	public List<NodeList<?>> getNodeLists() {
		return Arrays.asList(getAnnotations(), getVariables());
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.RemoveMethodGenerator")
	public boolean remove(Node node) {
		if (node == null)
			return false;
		for (int i = 0; i < annotations.size(); i++) {
			if (annotations.get(i) == node) {
				annotations.remove(i);
				return true;
			}
		}
		for (int i = 0; i < variables.size(); i++) {
			if (variables.get(i) == node) {
				variables.remove(i);
				return true;
			}
		}
		return super.remove(node);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.CloneGenerator")
	public VariableDeclarationExpr clone() {
		return (VariableDeclarationExpr) accept(new CloneVisitor(), null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetMetaModelGenerator")
	public VariableDeclarationExprMetaModel getMetaModel() {
		return JavaParserMetaModel.variableDeclarationExprMetaModel;
	}
}
