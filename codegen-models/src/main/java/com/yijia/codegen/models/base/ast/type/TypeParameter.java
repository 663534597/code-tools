/*
 * Copyright (C) 2007-2010 Júlio Vilmar Gesser. Copyright (C) 2011, 2013-2016 The JavaParser Team. This file is part of JavaParser. JavaParser can be used either under the terms of
 * a) the GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. b) the terms of
 * the Apache License You should have received a copy of both licenses in LICENCE.LGPL and LICENCE.APACHE. Please refer to those files for details. JavaParser is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General
 * Public License for more details.
 */
package com.yijia.codegen.models.base.ast.type;

import static com.yijia.codegen.models.base.utils.Utils.assertNotNull;
import static java.util.stream.Collectors.joining;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Generated;
import com.yijia.codegen.models.base.Range;
import com.yijia.codegen.models.base.TokenRange;
import com.yijia.codegen.models.base.ast.AllFieldsConstructor;
import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.NodeList;
import com.yijia.codegen.models.base.ast.expr.AnnotationExpr;
import com.yijia.codegen.models.base.ast.expr.SimpleName;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithAnnotations;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithSimpleName;
import com.yijia.codegen.models.base.ast.observer.ObservableProperty;
import com.yijia.codegen.models.base.ast.visitor.CloneVisitor;
import com.yijia.codegen.models.base.ast.visitor.GenericVisitor;
import com.yijia.codegen.models.base.ast.visitor.VoidVisitor;
import com.yijia.codegen.models.base.metamodel.JavaParserMetaModel;
import com.yijia.codegen.models.base.metamodel.TypeParameterMetaModel;

/**
 * A type parameter. <br/>
 * <code>&lt;<b>U</b>> U getU() { ... }</code> <br/>
 * <code>class D &lt;<b>@Brain T extends B & A & @Tripe C</b>> { ... }</code>
 * <p>
 * U and T are type parameter names. <br/>
 * B, A, and C are type parameter bounds. <br/>
 * Tripe is an annotation on type parameter bound C. <br/>
 * Brain is an annotation on type parameter T.
 * @author Julio Vilmar Gesser
 * @see com.yijia.codegen.models.base.ast.nodeTypes.NodeWithTypeParameters
 */
public final class TypeParameter extends ReferenceType implements NodeWithSimpleName<TypeParameter>, NodeWithAnnotations<TypeParameter> {

	private SimpleName name;

	private NodeList<ClassOrInterfaceType> typeBound;

	public TypeParameter() {
		this(null, new SimpleName(), new NodeList<>(), new NodeList<>());
	}

	public TypeParameter(final String name) {
		this(null, new SimpleName(name), new NodeList<>(), new NodeList<>());
	}

	public TypeParameter(final String name, final NodeList<ClassOrInterfaceType> typeBound) {
		this(null, new SimpleName(name), typeBound, new NodeList<>());
	}

	/**
	 * @deprecated range shouldn't be in utility constructors.
	 */
	@Deprecated
	public TypeParameter(Range range, final SimpleName name, final NodeList<ClassOrInterfaceType> typeBound) {
		this(null, name, typeBound, new NodeList<>());
		setRange(range);
	}

	@AllFieldsConstructor
	public TypeParameter(SimpleName name, NodeList<ClassOrInterfaceType> typeBound, NodeList<AnnotationExpr> annotations) {
		this(null, name, typeBound, annotations);
	}

	/** This constructor is used by the parser and is considered private. */
	@Generated("com.yijia.codegen.models.base.generator.core.node.MainConstructorGenerator")
	public TypeParameter(TokenRange tokenRange, SimpleName name, NodeList<ClassOrInterfaceType> typeBound, NodeList<AnnotationExpr> annotations) {
		super(tokenRange, annotations);
		setName(name);
		setTypeBound(typeBound);
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

	/**
	 * Return the name of the paramenter.
	 * @return the name of the paramenter
	 */
	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public SimpleName getName() {
		return name;
	}

	/**
	 * Return the list of {@link ClassOrInterfaceType} that this parameter extends. Return <code>null</code> null if there are no type.
	 * @return list of types that this paramente extends or <code>null</code>
	 */
	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public NodeList<ClassOrInterfaceType> getTypeBound() {
		return typeBound;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public TypeParameter setName(final SimpleName name) {
		assertNotNull(name);
		if (name == this.name) {
			return (TypeParameter) this;
		}
		notifyPropertyChange(ObservableProperty.NAME, this.name, name);
		if (this.name != null)
			this.name.setParentNode(null);
		this.name = name;
		setAsParentNodeOf(name);
		return this;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public TypeParameter setTypeBound(final NodeList<ClassOrInterfaceType> typeBound) {
		assertNotNull(typeBound);
		if (typeBound == this.typeBound) {
			return (TypeParameter) this;
		}
		notifyPropertyChange(ObservableProperty.TYPE_BOUND, this.typeBound, typeBound);
		if (this.typeBound != null)
			this.typeBound.setParentNode(null);
		this.typeBound = typeBound;
		setAsParentNodeOf(typeBound);
		return this;
	}

	@Override
	public TypeParameter setAnnotations(NodeList<AnnotationExpr> annotations) {
		super.setAnnotations(annotations);
		return this;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetNodeListsGenerator")
	public List<NodeList<?>> getNodeLists() {
		return Arrays.asList(getTypeBound(), getAnnotations());
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.RemoveMethodGenerator")
	public boolean remove(Node node) {
		if (node == null)
			return false;
		for (int i = 0; i < typeBound.size(); i++) {
			if (typeBound.get(i) == node) {
				typeBound.remove(i);
				return true;
			}
		}
		return super.remove(node);
	}

	@Override
	public String asString() {
		StringBuilder str = new StringBuilder(getNameAsString());
		getTypeBound().ifNonEmpty(l -> str.append(l.stream().map(ClassOrInterfaceType::asString).collect(joining("&", " extends ", ""))));
		return str.toString();
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.CloneGenerator")
	public TypeParameter clone() {
		return (TypeParameter) accept(new CloneVisitor(), null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetMetaModelGenerator")
	public TypeParameterMetaModel getMetaModel() {
		return JavaParserMetaModel.typeParameterMetaModel;
	}
}
