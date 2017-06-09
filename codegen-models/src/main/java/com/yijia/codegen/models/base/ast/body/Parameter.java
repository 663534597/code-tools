/*
 * Copyright (C) 2007-2010 Júlio Vilmar Gesser. Copyright (C) 2011, 2013-2016 The JavaParser Team. This file is part of JavaParser. JavaParser can be used either under the terms of
 * a) the GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. b) the terms of
 * the Apache License You should have received a copy of both licenses in LICENCE.LGPL and LICENCE.APACHE. Please refer to those files for details. JavaParser is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General
 * Public License for more details.
 */
package com.yijia.codegen.models.base.ast.body;

import static com.yijia.codegen.models.base.utils.Utils.assertNotNull;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import javax.annotation.Generated;
import com.yijia.codegen.models.base.TokenRange;
import com.yijia.codegen.models.base.ast.AllFieldsConstructor;
import com.yijia.codegen.models.base.ast.Modifier;
import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.NodeList;
import com.yijia.codegen.models.base.ast.expr.AnnotationExpr;
import com.yijia.codegen.models.base.ast.expr.SimpleName;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithAnnotations;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithSimpleName;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithType;
import com.yijia.codegen.models.base.ast.nodeTypes.modifiers.NodeWithFinalModifier;
import com.yijia.codegen.models.base.ast.observer.ObservableProperty;
import com.yijia.codegen.models.base.ast.type.ClassOrInterfaceType;
import com.yijia.codegen.models.base.ast.type.Type;
import com.yijia.codegen.models.base.ast.visitor.CloneVisitor;
import com.yijia.codegen.models.base.ast.visitor.GenericVisitor;
import com.yijia.codegen.models.base.ast.visitor.VoidVisitor;
import com.yijia.codegen.models.base.metamodel.JavaParserMetaModel;
import com.yijia.codegen.models.base.metamodel.ParameterMetaModel;

/**
 * The parameters to a method or lambda. Lambda parameters may have inferred types, in that case "type" is UnknownType. <br/>
 * Note that <a href="https://en.wikipedia.org/wiki/Parameter_(computer_programming)#Parameters_and_arguments">parameters are different from arguments.</a> <br/>
 * "String x" and "float y" are the parameters in <code>int abc(String x, float
 * y) {...}</code>
 * @author Julio Vilmar Gesser
 */
public final class Parameter extends Node
		implements NodeWithType<Parameter, Type>, NodeWithAnnotations<Parameter>, NodeWithSimpleName<Parameter>, NodeWithFinalModifier<Parameter> {

	private Type type;

	private boolean isVarArgs;

	private NodeList<AnnotationExpr> varArgsAnnotations;

	private EnumSet<Modifier> modifiers;

	private NodeList<AnnotationExpr> annotations;

	private SimpleName name;

	public Parameter() {
		this(null, EnumSet.noneOf(Modifier.class), new NodeList<>(), new ClassOrInterfaceType(), false, new NodeList<>(), new SimpleName());
	}

	public Parameter(Type type, SimpleName name) {
		this(null, EnumSet.noneOf(Modifier.class), new NodeList<>(), type, false, new NodeList<>(), name);
	}

	/**
	 * Creates a new {@link Parameter}.
	 * @param type type of the parameter
	 * @param name name of the parameter
	 */
	public Parameter(Type type, String name) {
		this(null, EnumSet.noneOf(Modifier.class), new NodeList<>(), type, false, new NodeList<>(), new SimpleName(name));
	}

	public Parameter(EnumSet<Modifier> modifiers, Type type, SimpleName name) {
		this(null, modifiers, new NodeList<>(), type, false, new NodeList<>(), name);
	}

	@AllFieldsConstructor
	public Parameter(EnumSet<Modifier> modifiers, NodeList<AnnotationExpr> annotations, Type type, boolean isVarArgs, NodeList<AnnotationExpr> varArgsAnnotations,
			SimpleName name) {
		this(null, modifiers, annotations, type, isVarArgs, varArgsAnnotations, name);
	}

	/** This constructor is used by the parser and is considered private. */
	@Generated("com.yijia.codegen.models.base.generator.core.node.MainConstructorGenerator")
	public Parameter(TokenRange tokenRange, EnumSet<Modifier> modifiers, NodeList<AnnotationExpr> annotations, Type type, boolean isVarArgs,
			NodeList<AnnotationExpr> varArgsAnnotations, SimpleName name) {
		super(tokenRange);
		setModifiers(modifiers);
		setAnnotations(annotations);
		setType(type);
		setVarArgs(isVarArgs);
		setVarArgsAnnotations(varArgsAnnotations);
		setName(name);
		customInitialization();
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
	public Type getType() {
		return type;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public boolean isVarArgs() {
		return isVarArgs;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public Parameter setType(final Type type) {
		assertNotNull(type);
		if (type == this.type) {
			return (Parameter) this;
		}
		notifyPropertyChange(ObservableProperty.TYPE, this.type, type);
		if (this.type != null)
			this.type.setParentNode(null);
		this.type = type;
		setAsParentNodeOf(type);
		return this;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public Parameter setVarArgs(final boolean isVarArgs) {
		if (isVarArgs == this.isVarArgs) {
			return (Parameter) this;
		}
		notifyPropertyChange(ObservableProperty.VAR_ARGS, this.isVarArgs, isVarArgs);
		this.isVarArgs = isVarArgs;
		return this;
	}

	/**
	 * @return the list returned could be immutable (in that case it will be empty)
	 */
	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public NodeList<AnnotationExpr> getAnnotations() {
		return annotations;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public SimpleName getName() {
		return name;
	}

	/**
	 * Return the modifiers of this parameter declaration.
	 * @return modifiers
	 * @see Modifier
	 */
	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public EnumSet<Modifier> getModifiers() {
		return modifiers;
	}

	/**
	 * @param annotations a null value is currently treated as an empty list. This behavior could change in the future, so please avoid passing null
	 */
	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public Parameter setAnnotations(final NodeList<AnnotationExpr> annotations) {
		assertNotNull(annotations);
		if (annotations == this.annotations) {
			return (Parameter) this;
		}
		notifyPropertyChange(ObservableProperty.ANNOTATIONS, this.annotations, annotations);
		if (this.annotations != null)
			this.annotations.setParentNode(null);
		this.annotations = annotations;
		setAsParentNodeOf(annotations);
		return this;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public Parameter setName(final SimpleName name) {
		assertNotNull(name);
		if (name == this.name) {
			return (Parameter) this;
		}
		notifyPropertyChange(ObservableProperty.NAME, this.name, name);
		if (this.name != null)
			this.name.setParentNode(null);
		this.name = name;
		setAsParentNodeOf(name);
		return this;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public Parameter setModifiers(final EnumSet<Modifier> modifiers) {
		assertNotNull(modifiers);
		if (modifiers == this.modifiers) {
			return (Parameter) this;
		}
		notifyPropertyChange(ObservableProperty.MODIFIERS, this.modifiers, modifiers);
		this.modifiers = modifiers;
		return this;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetNodeListsGenerator")
	public List<NodeList<?>> getNodeLists() {
		return Arrays.asList(getAnnotations(), getVarArgsAnnotations());
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
		for (int i = 0; i < varArgsAnnotations.size(); i++) {
			if (varArgsAnnotations.get(i) == node) {
				varArgsAnnotations.remove(i);
				return true;
			}
		}
		return super.remove(node);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public NodeList<AnnotationExpr> getVarArgsAnnotations() {
		return varArgsAnnotations;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public Parameter setVarArgsAnnotations(final NodeList<AnnotationExpr> varArgsAnnotations) {
		assertNotNull(varArgsAnnotations);
		if (varArgsAnnotations == this.varArgsAnnotations) {
			return (Parameter) this;
		}
		notifyPropertyChange(ObservableProperty.VAR_ARGS_ANNOTATIONS, this.varArgsAnnotations, varArgsAnnotations);
		if (this.varArgsAnnotations != null)
			this.varArgsAnnotations.setParentNode(null);
		this.varArgsAnnotations = varArgsAnnotations;
		setAsParentNodeOf(varArgsAnnotations);
		return this;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.CloneGenerator")
	public Parameter clone() {
		return (Parameter) accept(new CloneVisitor(), null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetMetaModelGenerator")
	public ParameterMetaModel getMetaModel() {
		return JavaParserMetaModel.parameterMetaModel;
	}
}
