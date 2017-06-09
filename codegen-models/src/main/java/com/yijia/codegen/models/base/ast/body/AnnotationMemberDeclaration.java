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
import java.util.Optional;
import javax.annotation.Generated;
import com.yijia.codegen.models.base.TokenRange;
import com.yijia.codegen.models.base.ast.AllFieldsConstructor;
import com.yijia.codegen.models.base.ast.Modifier;
import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.NodeList;
import com.yijia.codegen.models.base.ast.expr.AnnotationExpr;
import com.yijia.codegen.models.base.ast.expr.Expression;
import com.yijia.codegen.models.base.ast.expr.SimpleName;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithJavadoc;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithSimpleName;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithType;
import com.yijia.codegen.models.base.ast.nodeTypes.modifiers.NodeWithAbstractModifier;
import com.yijia.codegen.models.base.ast.nodeTypes.modifiers.NodeWithPublicModifier;
import com.yijia.codegen.models.base.ast.observer.ObservableProperty;
import com.yijia.codegen.models.base.ast.type.ClassOrInterfaceType;
import com.yijia.codegen.models.base.ast.type.Type;
import com.yijia.codegen.models.base.ast.visitor.CloneVisitor;
import com.yijia.codegen.models.base.ast.visitor.GenericVisitor;
import com.yijia.codegen.models.base.ast.visitor.VoidVisitor;
import com.yijia.codegen.models.base.metamodel.AnnotationMemberDeclarationMetaModel;
import com.yijia.codegen.models.base.metamodel.JavaParserMetaModel;

/**
 * The "int id();" in <code>@interface X { int id(); }</code>
 * @author Julio Vilmar Gesser
 */
public final class AnnotationMemberDeclaration extends BodyDeclaration<AnnotationMemberDeclaration>
		implements NodeWithJavadoc<AnnotationMemberDeclaration>, NodeWithSimpleName<AnnotationMemberDeclaration>, NodeWithType<AnnotationMemberDeclaration, Type>,
		NodeWithPublicModifier<AnnotationMemberDeclaration>, NodeWithAbstractModifier<AnnotationMemberDeclaration> {

	private EnumSet<Modifier> modifiers;

	private Type type;

	private SimpleName name;

	private Expression defaultValue;

	public AnnotationMemberDeclaration() {
		this(null, EnumSet.noneOf(Modifier.class), new NodeList<>(), new ClassOrInterfaceType(), new SimpleName(), null);
	}

	public AnnotationMemberDeclaration(EnumSet<Modifier> modifiers, Type type, String name, Expression defaultValue) {
		this(null, modifiers, new NodeList<>(), type, new SimpleName(name), defaultValue);
	}

	@AllFieldsConstructor
	public AnnotationMemberDeclaration(EnumSet<Modifier> modifiers, NodeList<AnnotationExpr> annotations, Type type, SimpleName name, Expression defaultValue) {
		this(null, modifiers, annotations, type, name, defaultValue);
	}

	/** This constructor is used by the parser and is considered private. */
	@Generated("com.yijia.codegen.models.base.generator.core.node.MainConstructorGenerator")
	public AnnotationMemberDeclaration(TokenRange tokenRange, EnumSet<Modifier> modifiers, NodeList<AnnotationExpr> annotations, Type type, SimpleName name,
			Expression defaultValue) {
		super(tokenRange, annotations);
		setModifiers(modifiers);
		setType(type);
		setName(name);
		setDefaultValue(defaultValue);
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
	public Optional<Expression> getDefaultValue() {
		return Optional.ofNullable(defaultValue);
	}

	/**
	 * Return the modifiers of this member declaration.
	 * @return modifiers
	 * @see Modifier
	 */
	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public EnumSet<Modifier> getModifiers() {
		return modifiers;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public SimpleName getName() {
		return name;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public Type getType() {
		return type;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.RemoveMethodGenerator")
	public AnnotationMemberDeclaration removeDefaultValue() {
		return setDefaultValue((Expression) null);
	}

	/**
	 * Sets the default value
	 * @param defaultValue the default value, can be null
	 * @return this, the AnnotationMemberDeclaration
	 */
	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public AnnotationMemberDeclaration setDefaultValue(final Expression defaultValue) {
		if (defaultValue == this.defaultValue) {
			return (AnnotationMemberDeclaration) this;
		}
		notifyPropertyChange(ObservableProperty.DEFAULT_VALUE, this.defaultValue, defaultValue);
		if (this.defaultValue != null)
			this.defaultValue.setParentNode(null);
		this.defaultValue = defaultValue;
		setAsParentNodeOf(defaultValue);
		return this;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public AnnotationMemberDeclaration setModifiers(final EnumSet<Modifier> modifiers) {
		assertNotNull(modifiers);
		if (modifiers == this.modifiers) {
			return (AnnotationMemberDeclaration) this;
		}
		notifyPropertyChange(ObservableProperty.MODIFIERS, this.modifiers, modifiers);
		this.modifiers = modifiers;
		return this;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public AnnotationMemberDeclaration setName(final SimpleName name) {
		assertNotNull(name);
		if (name == this.name) {
			return (AnnotationMemberDeclaration) this;
		}
		notifyPropertyChange(ObservableProperty.NAME, this.name, name);
		if (this.name != null)
			this.name.setParentNode(null);
		this.name = name;
		setAsParentNodeOf(name);
		return this;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public AnnotationMemberDeclaration setType(final Type type) {
		assertNotNull(type);
		if (type == this.type) {
			return (AnnotationMemberDeclaration) this;
		}
		notifyPropertyChange(ObservableProperty.TYPE, this.type, type);
		if (this.type != null)
			this.type.setParentNode(null);
		this.type = type;
		setAsParentNodeOf(type);
		return this;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetNodeListsGenerator")
	public List<NodeList<?>> getNodeLists() {
		return Arrays.asList(getAnnotations());
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.RemoveMethodGenerator")
	public boolean remove(Node node) {
		if (node == null)
			return false;
		if (defaultValue != null) {
			if (node == defaultValue) {
				removeDefaultValue();
				return true;
			}
		}
		return super.remove(node);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.CloneGenerator")
	public AnnotationMemberDeclaration clone() {
		return (AnnotationMemberDeclaration) accept(new CloneVisitor(), null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetMetaModelGenerator")
	public AnnotationMemberDeclarationMetaModel getMetaModel() {
		return JavaParserMetaModel.annotationMemberDeclarationMetaModel;
	}
}
