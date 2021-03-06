/*
 * Copyright (C) 2007-2010 Júlio Vilmar Gesser. Copyright (C) 2011, 2013-2016 The JavaParser Team. This file is part of JavaParser. JavaParser can be used either under the terms of
 * a) the GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. b) the terms of
 * the Apache License You should have received a copy of both licenses in LICENCE.LGPL and LICENCE.APACHE. Please refer to those files for details. JavaParser is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General
 * Public License for more details.
 */

package com.yijia.codegen.models.base.ast.nodeTypes;

import static com.yijia.codegen.models.base.JavaParser.parseType;
import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import com.yijia.codegen.models.base.ast.Modifier;
import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.NodeList;
import com.yijia.codegen.models.base.ast.body.BodyDeclaration;
import com.yijia.codegen.models.base.ast.body.FieldDeclaration;
import com.yijia.codegen.models.base.ast.body.InitializerDeclaration;
import com.yijia.codegen.models.base.ast.body.MethodDeclaration;
import com.yijia.codegen.models.base.ast.body.VariableDeclarator;
import com.yijia.codegen.models.base.ast.stmt.BlockStmt;
import com.yijia.codegen.models.base.ast.type.Type;
import com.yijia.codegen.models.base.ast.type.VoidType;

/**
 * A node having members.
 * <p>
 * The main reason for this interface is to permit users to manipulate homogeneously all nodes with a getMembers method.
 */
public interface NodeWithMembers<N extends Node> {
	NodeList<BodyDeclaration<?>> getMembers();

	void tryAddImportToParentCompilationUnit(Class<?> clazz);

	default BodyDeclaration<?> getMember(int i) {
		return getMembers().get(i);
	}

	@SuppressWarnings("unchecked")
	default N setMember(int i, BodyDeclaration<?> member) {
		getMembers().set(i, member);
		return (N) this;
	}

	@SuppressWarnings("unchecked")
	default N addMember(BodyDeclaration<?> member) {
		getMembers().add(member);
		return (N) this;
	}

	N setMembers(NodeList<BodyDeclaration<?>> members);

	/**
	 * Add a field to this and automatically add the import of the type if needed
	 * @param typeClass the type of the field
	 * @param name the name of the field
	 * @param modifiers the modifiers like {@link Modifier#PUBLIC}
	 * @return the {@link FieldDeclaration} created
	 */
	default FieldDeclaration addField(Class<?> typeClass, String name, Modifier... modifiers) {
		tryAddImportToParentCompilationUnit(typeClass);
		return addField(typeClass.getSimpleName(), name, modifiers);
	}

	/**
	 * Add a field to this
	 * @param type the type of the field
	 * @param name the name of the field
	 * @param modifiers the modifiers like {@link Modifier#PUBLIC}
	 * @return the {@link FieldDeclaration} created
	 */
	default FieldDeclaration addField(String type, String name, Modifier... modifiers) {
		return addField(parseType(type), name, modifiers);
	}

	/**
	 * Add a field to this
	 * @param type the type of the field
	 * @param name the name of the field
	 * @param modifiers the modifiers like {@link Modifier#PUBLIC}
	 * @return the {@link FieldDeclaration} created
	 */
	default FieldDeclaration addField(Type type, String name, Modifier... modifiers) {
		FieldDeclaration fieldDeclaration = new FieldDeclaration();
		VariableDeclarator variable = new VariableDeclarator(type, name);
		fieldDeclaration.getVariables().add(variable);
		fieldDeclaration.setModifiers(Arrays.stream(modifiers).collect(toCollection(() -> EnumSet.noneOf(Modifier.class))));
		getMembers().add(fieldDeclaration);
		return fieldDeclaration;
	}

	/**
	 * Add a private field to this
	 * @param typeClass the type of the field
	 * @param name the name of the field
	 * @return the {@link FieldDeclaration} created
	 */
	default FieldDeclaration addPrivateField(Class<?> typeClass, String name) {
		return addField(typeClass, name, Modifier.PRIVATE);
	}

	/**
	 * Add a private field to this and automatically add the import of the type if needed
	 * @param type the type of the field
	 * @param name the name of the field
	 * @return the {@link FieldDeclaration} created
	 */
	default FieldDeclaration addPrivateField(String type, String name) {
		return addField(type, name, Modifier.PRIVATE);
	}

	/**
	 * Add a public field to this
	 * @param typeClass the type of the field
	 * @param name the name of the field
	 * @return the {@link FieldDeclaration} created
	 */
	default FieldDeclaration addPublicField(Class<?> typeClass, String name) {
		return addField(typeClass, name, Modifier.PUBLIC);
	}

	/**
	 * Add a public field to this and automatically add the import of the type if needed
	 * @param type the type of the field
	 * @param name the name of the field
	 * @return the {@link FieldDeclaration} created
	 */
	default FieldDeclaration addPublicField(String type, String name) {
		return addField(type, name, Modifier.PUBLIC);
	}

	/**
	 * Add a protected field to this
	 * @param typeClass the type of the field
	 * @param name the name of the field
	 * @return the {@link FieldDeclaration} created
	 */
	default FieldDeclaration addProtectedField(Class<?> typeClass, String name) {
		return addField(typeClass, name, Modifier.PROTECTED);
	}

	/**
	 * Add a protected field to this and automatically add the import of the type if needed
	 * @param type the type of the field
	 * @param name the name of the field
	 * @return the {@link FieldDeclaration} created
	 */
	default FieldDeclaration addProtectedField(String type, String name) {
		return addField(type, name, Modifier.PROTECTED);
	}

	/**
	 * Adds a methods with void return by default to this
	 * @param methodName the method name
	 * @param modifiers the modifiers like {@link Modifier#PUBLIC}
	 * @return the {@link MethodDeclaration} created
	 */
	default MethodDeclaration addMethod(String methodName, Modifier... modifiers) {
		MethodDeclaration methodDeclaration = new MethodDeclaration();
		methodDeclaration.setName(methodName);
		methodDeclaration.setType(new VoidType());
		methodDeclaration.setModifiers(Arrays.stream(modifiers).collect(toCollection(() -> EnumSet.noneOf(Modifier.class))));
		getMembers().add(methodDeclaration);
		return methodDeclaration;
	}

	default BlockStmt addInitializer() {
		BlockStmt block = new BlockStmt();
		InitializerDeclaration initializerDeclaration = new InitializerDeclaration(false, block);
		getMembers().add(initializerDeclaration);
		return block;
	}

	default BlockStmt addStaticInitializer() {
		BlockStmt block = new BlockStmt();
		InitializerDeclaration initializerDeclaration = new InitializerDeclaration(true, block);
		getMembers().add(initializerDeclaration);
		return block;
	}

	/**
	 * Try to find a {@link MethodDeclaration} by its name
	 * @param name the name of the method
	 * @return the methods found (multiple in case of overloading)
	 */
	default List<MethodDeclaration> getMethodsByName(String name) {
		return unmodifiableList(getMethods().stream().filter(m -> m.getNameAsString().equals(name)).collect(toList()));
	}

	/**
	 * Find all methods in the members of this node.
	 * @return the methods found. This list is immutable.
	 */
	default List<MethodDeclaration> getMethods() {
		return unmodifiableList(getMembers().stream().filter(m -> m instanceof MethodDeclaration).map(m -> (MethodDeclaration) m).collect(toList()));
	}

	/**
	 * Try to find a {@link MethodDeclaration} by its parameters types
	 * @param paramTypes the types of parameters like "Map&lt;Integer,String&gt;","int" to match<br>
	 *            void foo(Map&lt;Integer,String&gt; myMap,int number)
	 * @return the methods found (multiple in case of overloading)
	 */
	default List<MethodDeclaration> getMethodsByParameterTypes(String... paramTypes) {
		return unmodifiableList(getMethods().stream().filter(m -> m.hasParametersOfType(paramTypes)).collect(toList()));
	}

	/**
	 * Try to find {@link MethodDeclaration}s by their name and parameters types
	 * @param paramTypes the types of parameters like "Map&lt;Integer,String&gt;","int" to match<br>
	 *            void foo(Map&lt;Integer,String&gt; myMap,int number)
	 * @return the methods found (multiple in case of overloading)
	 */
	default List<MethodDeclaration> getMethodsBySignature(String name, String... paramTypes) {
		return unmodifiableList(getMethodsByName(name).stream().filter(m -> m.hasParametersOfType(paramTypes)).collect(toList()));
	}

	/**
	 * Try to find a {@link MethodDeclaration} by its parameters types
	 * @param paramTypes the types of parameters like "Map&lt;Integer,String&gt;","int" to match<br>
	 *            void foo(Map&lt;Integer,String&gt; myMap,int number)
	 * @return the methods found (multiple in case of overloading)
	 */
	default List<MethodDeclaration> getMethodsByParameterTypes(Class<?>... paramTypes) {
		return unmodifiableList(getMethods().stream().filter(m -> m.hasParametersOfType(paramTypes)).collect(toList()));
	}

	/**
	 * Try to find a {@link FieldDeclaration} by its name
	 * @param name the name of the field
	 * @return null if not found, the FieldDeclaration otherwise
	 */
	default Optional<FieldDeclaration> getFieldByName(String name) {
		return getMembers().stream().filter(m -> m instanceof FieldDeclaration).map(f -> (FieldDeclaration) f)
				.filter(f -> f.getVariables().stream().anyMatch(var -> var.getNameAsString().equals(name))).findFirst();
	}

	/**
	 * Find all fields in the members of this node.
	 * @return the fields found. This list is immutable.
	 */
	default List<FieldDeclaration> getFields() {
		return unmodifiableList(getMembers().stream().filter(m -> m instanceof FieldDeclaration).map(m -> (FieldDeclaration) m).collect(toList()));
	}
}
