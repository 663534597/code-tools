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
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithImplements;
import com.yijia.codegen.models.base.ast.observer.ObservableProperty;
import com.yijia.codegen.models.base.ast.type.ClassOrInterfaceType;
import com.yijia.codegen.models.base.ast.visitor.CloneVisitor;
import com.yijia.codegen.models.base.ast.visitor.GenericVisitor;
import com.yijia.codegen.models.base.ast.visitor.VoidVisitor;
import com.yijia.codegen.models.base.metamodel.EnumDeclarationMetaModel;
import com.yijia.codegen.models.base.metamodel.JavaParserMetaModel;

/**
 * The declaration of an enum.<br/>
 * <code>enum X { ... }</code>
 * @author Julio Vilmar Gesser
 */
public final class EnumDeclaration extends TypeDeclaration<EnumDeclaration> implements NodeWithImplements<EnumDeclaration> {

	private NodeList<ClassOrInterfaceType> implementedTypes;

	private NodeList<EnumConstantDeclaration> entries;

	public EnumDeclaration() {
		this(null, EnumSet.noneOf(Modifier.class), new NodeList<>(), new SimpleName(), new NodeList<>(), new NodeList<>(), new NodeList<>());
	}

	public EnumDeclaration(EnumSet<Modifier> modifiers, String name) {
		this(null, modifiers, new NodeList<>(), new SimpleName(name), new NodeList<>(), new NodeList<>(), new NodeList<>());
	}

	@AllFieldsConstructor
	public EnumDeclaration(EnumSet<Modifier> modifiers, NodeList<AnnotationExpr> annotations, SimpleName name, NodeList<ClassOrInterfaceType> implementedTypes,
			NodeList<EnumConstantDeclaration> entries, NodeList<BodyDeclaration<?>> members) {
		this(null, modifiers, annotations, name, implementedTypes, entries, members);
	}

	/** This constructor is used by the parser and is considered private. */
	@Generated("com.yijia.codegen.models.base.generator.core.node.MainConstructorGenerator")
	public EnumDeclaration(TokenRange tokenRange, EnumSet<Modifier> modifiers, NodeList<AnnotationExpr> annotations, SimpleName name,
			NodeList<ClassOrInterfaceType> implementedTypes, NodeList<EnumConstantDeclaration> entries, NodeList<BodyDeclaration<?>> members) {
		super(tokenRange, modifiers, annotations, name, members);
		setImplementedTypes(implementedTypes);
		setEntries(entries);
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
	public NodeList<EnumConstantDeclaration> getEntries() {
		return entries;
	}

	public EnumConstantDeclaration getEntry(int i) {
		return getEntries().get(i);
	}

	public EnumDeclaration setEntry(int i, EnumConstantDeclaration element) {
		getEntries().set(i, element);
		return this;
	}

	public EnumDeclaration addEntry(EnumConstantDeclaration element) {
		getEntries().add(element);
		return this;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public NodeList<ClassOrInterfaceType> getImplementedTypes() {
		return implementedTypes;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public EnumDeclaration setEntries(final NodeList<EnumConstantDeclaration> entries) {
		assertNotNull(entries);
		if (entries == this.entries) {
			return (EnumDeclaration) this;
		}
		notifyPropertyChange(ObservableProperty.ENTRIES, this.entries, entries);
		if (this.entries != null)
			this.entries.setParentNode(null);
		this.entries = entries;
		setAsParentNodeOf(entries);
		return this;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public EnumDeclaration setImplementedTypes(final NodeList<ClassOrInterfaceType> implementedTypes) {
		assertNotNull(implementedTypes);
		if (implementedTypes == this.implementedTypes) {
			return (EnumDeclaration) this;
		}
		notifyPropertyChange(ObservableProperty.IMPLEMENTED_TYPES, this.implementedTypes, implementedTypes);
		if (this.implementedTypes != null)
			this.implementedTypes.setParentNode(null);
		this.implementedTypes = implementedTypes;
		setAsParentNodeOf(implementedTypes);
		return this;
	}

	public EnumConstantDeclaration addEnumConstant(String name) {
		assertNonEmpty(name);
		EnumConstantDeclaration enumConstant = new EnumConstantDeclaration(name);
		getEntries().add(enumConstant);
		return enumConstant;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetNodeListsGenerator")
	public List<NodeList<?>> getNodeLists() {
		return Arrays.asList(getEntries(), getImplementedTypes(), getMembers(), getAnnotations());
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.RemoveMethodGenerator")
	public boolean remove(Node node) {
		if (node == null)
			return false;
		for (int i = 0; i < entries.size(); i++) {
			if (entries.get(i) == node) {
				entries.remove(i);
				return true;
			}
		}
		for (int i = 0; i < implementedTypes.size(); i++) {
			if (implementedTypes.get(i) == node) {
				implementedTypes.remove(i);
				return true;
			}
		}
		return super.remove(node);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.CloneGenerator")
	public EnumDeclaration clone() {
		return (EnumDeclaration) accept(new CloneVisitor(), null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetMetaModelGenerator")
	public EnumDeclarationMetaModel getMetaModel() {
		return JavaParserMetaModel.enumDeclarationMetaModel;
	}
}
