/*
 * Copyright (C) 2007-2010 Júlio Vilmar Gesser. Copyright (C) 2011, 2013-2016 The JavaParser Team. This file is part of JavaParser. JavaParser can be used either under the terms of
 * a) the GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. b) the terms of
 * the Apache License You should have received a copy of both licenses in LICENCE.LGPL and LICENCE.APACHE. Please refer to those files for details. JavaParser is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General
 * Public License for more details.
 */
package com.yijia.codegen.models.base.ast.type;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;
import com.yijia.codegen.models.base.TokenRange;
import com.yijia.codegen.models.base.ast.AllFieldsConstructor;
import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.NodeList;
import com.yijia.codegen.models.base.ast.expr.AnnotationExpr;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithAnnotations;
import com.yijia.codegen.models.base.ast.observer.ObservableProperty;
import com.yijia.codegen.models.base.ast.visitor.CloneVisitor;
import com.yijia.codegen.models.base.ast.visitor.GenericVisitor;
import com.yijia.codegen.models.base.ast.visitor.VoidVisitor;
import com.yijia.codegen.models.base.metamodel.JavaParserMetaModel;
import com.yijia.codegen.models.base.metamodel.WildcardTypeMetaModel;

/**
 * A wildcard type argument. <br/>
 * <code>void printCollection(Collection&lt;<b>?</b>> c) { ... }</code> <br/>
 * <code>boolean addAll(Collection&lt;<b>? extends E</b>> c)</code> <br/>
 * <code>Reference(T referent, ReferenceQueue&lt;<b>? super T</b>> queue)</code>
 * @author Julio Vilmar Gesser
 */
public final class WildcardType extends Type implements NodeWithAnnotations<WildcardType> {

	private ReferenceType extendedType;

	private ReferenceType superType;

	public WildcardType() {
		this(null, null, null);
	}

	public WildcardType(final ReferenceType extendedType) {
		this(null, extendedType, null);
	}

	@AllFieldsConstructor
	public WildcardType(final ReferenceType extendedType, final ReferenceType superType) {
		this(null, extendedType, superType);
	}

	/** This constructor is used by the parser and is considered private. */
	@Generated("com.yijia.codegen.models.base.generator.core.node.MainConstructorGenerator")
	public WildcardType(TokenRange tokenRange, ReferenceType extendedType, ReferenceType superType) {
		super(tokenRange);
		setExtendedType(extendedType);
		setSuperType(superType);
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
	public Optional<ReferenceType> getExtendedType() {
		return Optional.ofNullable(extendedType);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public Optional<ReferenceType> getSuperType() {
		return Optional.ofNullable(superType);
	}

	@Deprecated
	public Optional<ReferenceType> getExtendedTypes() {
		return getExtendedType();
	}

	@Deprecated
	public Optional<ReferenceType> getSuperTypes() {
		return getSuperType();
	}

	/**
	 * Sets the extended type
	 * @param extendedType the extends, can be null
	 * @return this, the WildcardType
	 */
	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public WildcardType setExtendedType(final ReferenceType extendedType) {
		if (extendedType == this.extendedType) {
			return (WildcardType) this;
		}
		notifyPropertyChange(ObservableProperty.EXTENDED_TYPE, this.extendedType, extendedType);
		if (this.extendedType != null)
			this.extendedType.setParentNode(null);
		this.extendedType = extendedType;
		setAsParentNodeOf(extendedType);
		return this;
	}

	/**
	 * Sets the extended type
	 * @param extendedType the extends, can be null
	 * @return this, the WildcardType
	 * @deprecated use setExtendedType instead,
	 */
	@Deprecated
	public WildcardType setExtendedTypes(final ReferenceType extendedType) {
		return setExtendedType(extendedType);
	}

	/**
	 * Sets the supertype
	 * @param superType the super, can be null
	 * @return this, the WildcardType
	 */
	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public WildcardType setSuperType(final ReferenceType superType) {
		if (superType == this.superType) {
			return (WildcardType) this;
		}
		notifyPropertyChange(ObservableProperty.SUPER_TYPE, this.superType, superType);
		if (this.superType != null)
			this.superType.setParentNode(null);
		this.superType = superType;
		setAsParentNodeOf(superType);
		return this;
	}

	/**
	 * Sets the supertype
	 * @param superType the super, can be null
	 * @return this, the WildcardType
	 * @deprecated use setSuperType instead
	 */
	@Deprecated
	public WildcardType setSuperTypes(final ReferenceType superType) {
		return setSuperType(superType);
	}

	@Override
	public WildcardType setAnnotations(NodeList<AnnotationExpr> annotations) {
		return (WildcardType) super.setAnnotations(annotations);
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
		if (extendedType != null) {
			if (node == extendedType) {
				removeExtendedType();
				return true;
			}
		}
		if (superType != null) {
			if (node == superType) {
				removeSuperType();
				return true;
			}
		}
		return super.remove(node);
	}

	@Override
	public String asString() {
		StringBuilder str = new StringBuilder("?");
		getExtendedType().ifPresent(t -> str.append(" extends ").append(t.asString()));
		getSuperType().ifPresent(t -> str.append(" super ").append(t.asString()));
		return str.toString();
	}

	@Deprecated
	public WildcardType removeExtendedTypes() {
		return removeExtendedType();
	}

	@Deprecated
	public WildcardType removeSuperTypes() {
		return removeSuperType();
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.RemoveMethodGenerator")
	public WildcardType removeExtendedType() {
		return setExtendedType((ReferenceType) null);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.RemoveMethodGenerator")
	public WildcardType removeSuperType() {
		return setSuperType((ReferenceType) null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.CloneGenerator")
	public WildcardType clone() {
		return (WildcardType) accept(new CloneVisitor(), null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetMetaModelGenerator")
	public WildcardTypeMetaModel getMetaModel() {
		return JavaParserMetaModel.wildcardTypeMetaModel;
	}
}
