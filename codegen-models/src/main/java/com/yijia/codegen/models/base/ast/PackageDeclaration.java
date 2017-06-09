/*
 * Copyright (C) 2007-2010 Júlio Vilmar Gesser. Copyright (C) 2011, 2013-2016 The JavaParser Team. This file is part of JavaParser. JavaParser can be used either under the terms of
 * a) the GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. b) the terms of
 * the Apache License You should have received a copy of both licenses in LICENCE.LGPL and LICENCE.APACHE. Please refer to those files for details. JavaParser is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General
 * Public License for more details.
 */
package com.yijia.codegen.models.base.ast;

import static com.yijia.codegen.models.base.utils.Utils.assertNotNull;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Generated;
import com.yijia.codegen.models.base.TokenRange;
import com.yijia.codegen.models.base.ast.expr.AnnotationExpr;
import com.yijia.codegen.models.base.ast.expr.Name;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithAnnotations;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithName;
import com.yijia.codegen.models.base.ast.observer.ObservableProperty;
import com.yijia.codegen.models.base.ast.visitor.CloneVisitor;
import com.yijia.codegen.models.base.ast.visitor.GenericVisitor;
import com.yijia.codegen.models.base.ast.visitor.VoidVisitor;
import com.yijia.codegen.models.base.metamodel.JavaParserMetaModel;
import com.yijia.codegen.models.base.metamodel.PackageDeclarationMetaModel;

public final class PackageDeclaration extends Node implements NodeWithAnnotations<PackageDeclaration>, NodeWithName<PackageDeclaration> {

	private NodeList<AnnotationExpr> annotations = new NodeList<>();

	private Name name;

	public PackageDeclaration() {
		this(null, new NodeList<>(), new Name());
	}

	public PackageDeclaration(Name name) {
		this(null, new NodeList<>(), name);
	}

	@AllFieldsConstructor
	public PackageDeclaration(NodeList<AnnotationExpr> annotations, Name name) {
		this(null, annotations, name);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.MainConstructorGenerator")
	public PackageDeclaration(TokenRange tokenRange, NodeList<AnnotationExpr> annotations, Name name) {
		super(tokenRange);
		setAnnotations(annotations);
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
	public NodeList<AnnotationExpr> getAnnotations() {
		return annotations;
	}

	/**
	 * Return the name expression of the package.
	 * @return the name of the package
	 */
	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public Name getName() {
		return name;
	}

	/**
	 * @param annotations the annotations to set
	 */
	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public PackageDeclaration setAnnotations(final NodeList<AnnotationExpr> annotations) {
		assertNotNull(annotations);
		if (annotations == this.annotations) {
			return (PackageDeclaration) this;
		}
		notifyPropertyChange(ObservableProperty.ANNOTATIONS, this.annotations, annotations);
		if (this.annotations != null)
			this.annotations.setParentNode(null);
		this.annotations = annotations;
		setAsParentNodeOf(annotations);
		return this;
	}

	/**
	 * Sets the name of this package declaration.
	 * @param name the name to set
	 */
	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public PackageDeclaration setName(final Name name) {
		assertNotNull(name);
		if (name == this.name) {
			return (PackageDeclaration) this;
		}
		notifyPropertyChange(ObservableProperty.NAME, this.name, name);
		if (this.name != null)
			this.name.setParentNode(null);
		this.name = name;
		setAsParentNodeOf(name);
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
		for (int i = 0; i < annotations.size(); i++) {
			if (annotations.get(i) == node) {
				annotations.remove(i);
				return true;
			}
		}
		return super.remove(node);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.CloneGenerator")
	public PackageDeclaration clone() {
		return (PackageDeclaration) accept(new CloneVisitor(), null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetMetaModelGenerator")
	public PackageDeclarationMetaModel getMetaModel() {
		return JavaParserMetaModel.packageDeclarationMetaModel;
	}
}
