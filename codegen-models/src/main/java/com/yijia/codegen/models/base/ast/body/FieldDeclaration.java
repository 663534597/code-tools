/*
 * Copyright (C) 2007-2010 Júlio Vilmar Gesser. Copyright (C) 2011, 2013-2016 The JavaParser Team. This file is part of JavaParser. JavaParser can be used either under the terms of
 * a) the GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. b) the terms of
 * the Apache License You should have received a copy of both licenses in LICENCE.LGPL and LICENCE.APACHE. Please refer to those files for details. JavaParser is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General
 * Public License for more details.
 */
package com.yijia.codegen.models.base.ast.body;

import static com.yijia.codegen.models.base.ast.Modifier.PUBLIC;
import static com.yijia.codegen.models.base.ast.Modifier.TRANSIENT;
import static com.yijia.codegen.models.base.ast.Modifier.VOLATILE;
import static com.yijia.codegen.models.base.ast.NodeList.nodeList;
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
import com.yijia.codegen.models.base.ast.expr.AssignExpr;
import com.yijia.codegen.models.base.ast.expr.AssignExpr.Operator;
import com.yijia.codegen.models.base.ast.expr.NameExpr;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithJavadoc;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithVariables;
import com.yijia.codegen.models.base.ast.nodeTypes.modifiers.NodeWithAccessModifiers;
import com.yijia.codegen.models.base.ast.nodeTypes.modifiers.NodeWithFinalModifier;
import com.yijia.codegen.models.base.ast.nodeTypes.modifiers.NodeWithStaticModifier;
import com.yijia.codegen.models.base.ast.observer.ObservableProperty;
import com.yijia.codegen.models.base.ast.stmt.BlockStmt;
import com.yijia.codegen.models.base.ast.stmt.ReturnStmt;
import com.yijia.codegen.models.base.ast.type.Type;
import com.yijia.codegen.models.base.ast.type.VoidType;
import com.yijia.codegen.models.base.ast.visitor.CloneVisitor;
import com.yijia.codegen.models.base.ast.visitor.GenericVisitor;
import com.yijia.codegen.models.base.ast.visitor.VoidVisitor;
import com.yijia.codegen.models.base.metamodel.FieldDeclarationMetaModel;
import com.yijia.codegen.models.base.metamodel.JavaParserMetaModel;
import com.yijia.codegen.models.base.metamodel.NonEmptyProperty;

/**
 * The declaration of a field in a class. "private static int a=15*15;" in this example: <code>class X { private static
 * int a=15*15; }</code>
 * @author Julio Vilmar Gesser
 */
public final class FieldDeclaration extends BodyDeclaration<FieldDeclaration> implements NodeWithJavadoc<FieldDeclaration>, NodeWithVariables<FieldDeclaration>,
		NodeWithAccessModifiers<FieldDeclaration>, NodeWithStaticModifier<FieldDeclaration>, NodeWithFinalModifier<FieldDeclaration> {

	private EnumSet<Modifier> modifiers;

	@NonEmptyProperty
	private NodeList<VariableDeclarator> variables;

	public FieldDeclaration() {
		this(null, EnumSet.noneOf(Modifier.class), new NodeList<>(), new NodeList<>());
	}

	public FieldDeclaration(EnumSet<Modifier> modifiers, VariableDeclarator variable) {
		this(null, modifiers, new NodeList<>(), nodeList(variable));
	}

	public FieldDeclaration(EnumSet<Modifier> modifiers, NodeList<VariableDeclarator> variables) {
		this(null, modifiers, new NodeList<>(), variables);
	}

	@AllFieldsConstructor
	public FieldDeclaration(EnumSet<Modifier> modifiers, NodeList<AnnotationExpr> annotations, NodeList<VariableDeclarator> variables) {
		this(null, modifiers, annotations, variables);
	}

	/** This constructor is used by the parser and is considered private. */
	@Generated("com.yijia.codegen.models.base.generator.core.node.MainConstructorGenerator")
	public FieldDeclaration(TokenRange tokenRange, EnumSet<Modifier> modifiers, NodeList<AnnotationExpr> annotations, NodeList<VariableDeclarator> variables) {
		super(tokenRange, annotations);
		setModifiers(modifiers);
		setVariables(variables);
		customInitialization();
	}

	/**
	 * Creates a {@link FieldDeclaration}.
	 * @param modifiers modifiers
	 * @param type type
	 * @param name field name
	 */
	public FieldDeclaration(EnumSet<Modifier> modifiers, Type type, String name) {
		this(assertNotNull(modifiers), new VariableDeclarator(type, assertNotNull(name)));
	}

	@Override
	public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
		return v.visit(this, arg);
	}

	@Override
	public <A> void accept(VoidVisitor<A> v, A arg) {
		v.visit(this, arg);
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
	public NodeList<VariableDeclarator> getVariables() {
		return variables;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public FieldDeclaration setModifiers(final EnumSet<Modifier> modifiers) {
		assertNotNull(modifiers);
		if (modifiers == this.modifiers) {
			return (FieldDeclaration) this;
		}
		notifyPropertyChange(ObservableProperty.MODIFIERS, this.modifiers, modifiers);
		this.modifiers = modifiers;
		return this;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public FieldDeclaration setVariables(final NodeList<VariableDeclarator> variables) {
		assertNotNull(variables);
		if (variables == this.variables) {
			return (FieldDeclaration) this;
		}
		notifyPropertyChange(ObservableProperty.VARIABLES, this.variables, variables);
		if (this.variables != null)
			this.variables.setParentNode(null);
		this.variables = variables;
		setAsParentNodeOf(variables);
		return this;
	}

	/**
	 * Create a getter for this field, <b>will only work if this field declares only 1 identifier and if this field is already added to a ClassOrInterfaceDeclaration</b>
	 * @return the {@link MethodDeclaration} created
	 * @throws IllegalStateException if there is more than 1 variable identifier or if this field isn't attached to a class or enum
	 */
	public MethodDeclaration createGetter() {
		if (getVariables().size() != 1)
			throw new IllegalStateException("You can use this only when the field declares only 1 variable name");
		Optional<ClassOrInterfaceDeclaration> parentClass = getAncestorOfType(ClassOrInterfaceDeclaration.class);
		Optional<EnumDeclaration> parentEnum = getAncestorOfType(EnumDeclaration.class);
		if (!(parentClass.isPresent() || parentEnum.isPresent()) || (parentClass.isPresent() && parentClass.get().isInterface()))
			throw new IllegalStateException("You can use this only when the field is attached to a class or an enum");
		VariableDeclarator variable = getVariable(0);
		String fieldName = variable.getNameAsString();
		String fieldNameUpper = fieldName.toUpperCase().substring(0, 1) + fieldName.substring(1, fieldName.length());
		final MethodDeclaration getter;
		getter = parentClass.map(clazz -> clazz.addMethod("get" + fieldNameUpper, PUBLIC)).orElseGet(() -> parentEnum.get().addMethod("get" + fieldNameUpper, PUBLIC));
		getter.setType(variable.getType());
		BlockStmt blockStmt = new BlockStmt();
		getter.setBody(blockStmt);
		blockStmt.addStatement(new ReturnStmt(fieldName));
		return getter;
	}

	/**
	 * Create a setter for this field, <b>will only work if this field declares only 1 identifier and if this field is already added to a ClassOrInterfaceDeclaration</b>
	 * @return the {@link MethodDeclaration} created
	 * @throws IllegalStateException if there is more than 1 variable identifier or if this field isn't attached to a class or enum
	 */
	public MethodDeclaration createSetter() {
		if (getVariables().size() != 1)
			throw new IllegalStateException("You can use this only when the field declares only 1 variable name");
		Optional<ClassOrInterfaceDeclaration> parentClass = getAncestorOfType(ClassOrInterfaceDeclaration.class);
		Optional<EnumDeclaration> parentEnum = getAncestorOfType(EnumDeclaration.class);
		if (!(parentClass.isPresent() || parentEnum.isPresent()) || (parentClass.isPresent() && parentClass.get().isInterface()))
			throw new IllegalStateException("You can use this only when the field is attached to a class or an enum");
		VariableDeclarator variable = getVariable(0);
		String fieldName = variable.getNameAsString();
		String fieldNameUpper = fieldName.toUpperCase().substring(0, 1) + fieldName.substring(1, fieldName.length());
		final MethodDeclaration setter;
		setter = parentClass.map(clazz -> clazz.addMethod("set" + fieldNameUpper, PUBLIC)).orElseGet(() -> parentEnum.get().addMethod("set" + fieldNameUpper, PUBLIC));
		setter.setType(new VoidType());
		setter.getParameters().add(new Parameter(variable.getType(), fieldName));
		BlockStmt blockStmt2 = new BlockStmt();
		setter.setBody(blockStmt2);
		blockStmt2.addStatement(new AssignExpr(new NameExpr("this." + fieldName), new NameExpr(fieldName), Operator.ASSIGN));
		return setter;
	}

	public boolean isTransient() {
		return getModifiers().contains(TRANSIENT);
	}

	public boolean isVolatile() {
		return getModifiers().contains(VOLATILE);
	}

	public FieldDeclaration setTransient(boolean set) {
		return setModifier(TRANSIENT, set);
	}

	public FieldDeclaration setVolatile(boolean set) {
		return setModifier(VOLATILE, set);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetNodeListsGenerator")
	public List<NodeList<?>> getNodeLists() {
		return Arrays.asList(getVariables(), getAnnotations());
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.RemoveMethodGenerator")
	public boolean remove(Node node) {
		if (node == null)
			return false;
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
	public FieldDeclaration clone() {
		return (FieldDeclaration) accept(new CloneVisitor(), null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetMetaModelGenerator")
	public FieldDeclarationMetaModel getMetaModel() {
		return JavaParserMetaModel.fieldDeclarationMetaModel;
	}
}
