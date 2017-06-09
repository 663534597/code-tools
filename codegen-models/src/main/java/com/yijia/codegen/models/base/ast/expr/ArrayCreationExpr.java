/*
 * Copyright (C) 2007-2010 Júlio Vilmar Gesser. Copyright (C) 2011, 2013-2016 The JavaParser Team. This file is part of JavaParser. JavaParser can be used either under the terms of
 * a) the GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. b) the terms of
 * the Apache License You should have received a copy of both licenses in LICENCE.LGPL and LICENCE.APACHE. Please refer to those files for details. JavaParser is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General
 * Public License for more details.
 */
package com.yijia.codegen.models.base.ast.expr;

import static com.yijia.codegen.models.base.JavaParser.parseType;
import static com.yijia.codegen.models.base.utils.Utils.assertNotNull;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;
import com.yijia.codegen.models.base.Range;
import com.yijia.codegen.models.base.TokenRange;
import com.yijia.codegen.models.base.ast.AllFieldsConstructor;
import com.yijia.codegen.models.base.ast.ArrayCreationLevel;
import com.yijia.codegen.models.base.ast.CompilationUnit;
import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.NodeList;
import com.yijia.codegen.models.base.ast.observer.ObservableProperty;
import com.yijia.codegen.models.base.ast.type.ArrayType;
import com.yijia.codegen.models.base.ast.type.ClassOrInterfaceType;
import com.yijia.codegen.models.base.ast.type.Type;
import com.yijia.codegen.models.base.ast.visitor.CloneVisitor;
import com.yijia.codegen.models.base.ast.visitor.GenericVisitor;
import com.yijia.codegen.models.base.ast.visitor.VoidVisitor;
import com.yijia.codegen.models.base.metamodel.ArrayCreationExprMetaModel;
import com.yijia.codegen.models.base.metamodel.JavaParserMetaModel;
import com.yijia.codegen.models.base.metamodel.NonEmptyProperty;

/**
 * <code>new int[5][4][][]</code> or <code>new int[][]{{1},{2,3}}</code>. <br/>
 * "int" is the element type. <br/>
 * All the brackets are stored in the levels field, from left to right.
 * @author Julio Vilmar Gesser
 */
public final class ArrayCreationExpr extends Expression {

	@NonEmptyProperty
	private NodeList<ArrayCreationLevel> levels;

	private Type elementType;

	private ArrayInitializerExpr initializer;

	public ArrayCreationExpr() {
		this(null, new ClassOrInterfaceType(), new NodeList<>(), new ArrayInitializerExpr());
	}

	@AllFieldsConstructor
	public ArrayCreationExpr(Type elementType, NodeList<ArrayCreationLevel> levels, ArrayInitializerExpr initializer) {
		this(null, elementType, levels, initializer);
	}

	public ArrayCreationExpr(Type elementType) {
		this(null, elementType, new NodeList<>(), new ArrayInitializerExpr());
	}

	/**
	 * @deprecated range shouldn't be in utility constructors.
	 */
	@Deprecated
	public ArrayCreationExpr(Range range, Type elementType) {
		this(null, elementType, new NodeList<>(), new ArrayInitializerExpr());
		setRange(range);
	}

	/** This constructor is used by the parser and is considered private. */
	@Generated("com.yijia.codegen.models.base.generator.core.node.MainConstructorGenerator")
	public ArrayCreationExpr(TokenRange tokenRange, Type elementType, NodeList<ArrayCreationLevel> levels, ArrayInitializerExpr initializer) {
		super(tokenRange);
		setElementType(elementType);
		setLevels(levels);
		setInitializer(initializer);
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
	public Optional<ArrayInitializerExpr> getInitializer() {
		return Optional.ofNullable(initializer);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public Type getElementType() {
		return elementType;
	}

	/**
	 * Sets the initializer
	 * @param initializer the initializer, can be null
	 * @return this, the ArrayCreationExpr
	 */
	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public ArrayCreationExpr setInitializer(final ArrayInitializerExpr initializer) {
		if (initializer == this.initializer) {
			return (ArrayCreationExpr) this;
		}
		notifyPropertyChange(ObservableProperty.INITIALIZER, this.initializer, initializer);
		if (this.initializer != null)
			this.initializer.setParentNode(null);
		this.initializer = initializer;
		setAsParentNodeOf(initializer);
		return this;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public ArrayCreationExpr setElementType(final Type elementType) {
		assertNotNull(elementType);
		if (elementType == this.elementType) {
			return (ArrayCreationExpr) this;
		}
		notifyPropertyChange(ObservableProperty.ELEMENT_TYPE, this.elementType, elementType);
		if (this.elementType != null)
			this.elementType.setParentNode(null);
		this.elementType = elementType;
		setAsParentNodeOf(elementType);
		return this;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public NodeList<ArrayCreationLevel> getLevels() {
		return levels;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public ArrayCreationExpr setLevels(final NodeList<ArrayCreationLevel> levels) {
		assertNotNull(levels);
		if (levels == this.levels) {
			return (ArrayCreationExpr) this;
		}
		notifyPropertyChange(ObservableProperty.LEVELS, this.levels, levels);
		if (this.levels != null)
			this.levels.setParentNode(null);
		this.levels = levels;
		setAsParentNodeOf(levels);
		return this;
	}

	/**
	 * Takes the element type and wraps it in an ArrayType for every array creation level.
	 */
	public Type createdType() {
		Type result = elementType;
		for (int i = 0; i < levels.size(); i++) {
			result = new ArrayType(result, new NodeList<>());
		}
		return result;
	}

	/**
	 * Sets this type to this class and try to import it to the {@link CompilationUnit} if needed
	 * @param typeClass the type
	 * @return this
	 */
	public ArrayCreationExpr setElementType(Class<?> typeClass) {
		tryAddImportToParentCompilationUnit(typeClass);
		return setElementType(parseType(typeClass.getSimpleName()));
	}

	public ArrayCreationExpr setElementType(final String type) {
		return setElementType(parseType(type));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetNodeListsGenerator")
	public List<NodeList<?>> getNodeLists() {
		return Arrays.asList(getLevels());
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
		for (int i = 0; i < levels.size(); i++) {
			if (levels.get(i) == node) {
				levels.remove(i);
				return true;
			}
		}
		return super.remove(node);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.RemoveMethodGenerator")
	public ArrayCreationExpr removeInitializer() {
		return setInitializer((ArrayInitializerExpr) null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.CloneGenerator")
	public ArrayCreationExpr clone() {
		return (ArrayCreationExpr) accept(new CloneVisitor(), null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetMetaModelGenerator")
	public ArrayCreationExprMetaModel getMetaModel() {
		return JavaParserMetaModel.arrayCreationExprMetaModel;
	}
}
