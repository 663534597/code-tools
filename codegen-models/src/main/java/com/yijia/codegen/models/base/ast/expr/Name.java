/*
 * Copyright (C) 2007-2010 Júlio Vilmar Gesser. Copyright (C) 2011, 2013-2016 The JavaParser Team. This file is part of JavaParser. JavaParser can be used either under the terms of
 * a) the GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. b) the terms of
 * the Apache License You should have received a copy of both licenses in LICENCE.LGPL and LICENCE.APACHE. Please refer to those files for details. JavaParser is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General
 * Public License for more details.
 */
package com.yijia.codegen.models.base.ast.expr;

import static com.yijia.codegen.models.base.utils.Utils.assertNonEmpty;
import static com.yijia.codegen.models.base.utils.Utils.assertNotNull;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;
import com.yijia.codegen.models.base.JavaParser;
import com.yijia.codegen.models.base.TokenRange;
import com.yijia.codegen.models.base.ast.AllFieldsConstructor;
import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.NodeList;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithAnnotations;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithIdentifier;
import com.yijia.codegen.models.base.ast.observer.ObservableProperty;
import com.yijia.codegen.models.base.ast.visitor.CloneVisitor;
import com.yijia.codegen.models.base.ast.visitor.GenericVisitor;
import com.yijia.codegen.models.base.ast.visitor.VoidVisitor;
import com.yijia.codegen.models.base.metamodel.JavaParserMetaModel;
import com.yijia.codegen.models.base.metamodel.NameMetaModel;
import com.yijia.codegen.models.base.metamodel.NonEmptyProperty;

/**
 * A name that may consist of multiple identifiers. In other words: it.may.contain.dots.
 * <p>
 * The rightmost identifier is "identifier", The one to the left of it is "qualifier.identifier", etc.
 * <p>
 * You can construct one from a String with the name(...) method.
 * @author Julio Vilmar Gesser
 * @see SimpleName
 */
public class Name extends Node implements NodeWithIdentifier<Name>, NodeWithAnnotations<Name> {

	@NonEmptyProperty
	private String identifier;

	private Name qualifier;

	private NodeList<AnnotationExpr> annotations;

	public Name() {
		this(null, null, "empty", new NodeList<>());
	}

	public Name(final String identifier) {
		this(null, null, identifier, new NodeList<>());
	}

	public Name(Name qualifier, final String identifier) {
		this(null, qualifier, identifier, new NodeList<>());
	}

	@AllFieldsConstructor
	public Name(Name qualifier, final String identifier, NodeList<AnnotationExpr> annotations) {
		this(null, qualifier, identifier, annotations);
	}

	/** This constructor is used by the parser and is considered private. */
	@Generated("com.yijia.codegen.models.base.generator.core.node.MainConstructorGenerator")
	public Name(TokenRange tokenRange, Name qualifier, String identifier, NodeList<AnnotationExpr> annotations) {
		super(tokenRange);
		setQualifier(qualifier);
		setIdentifier(identifier);
		setAnnotations(annotations);
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
	public String getIdentifier() {
		return identifier;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public Name setIdentifier(final String identifier) {
		assertNonEmpty(identifier);
		if (identifier == this.identifier) {
			return (Name) this;
		}
		notifyPropertyChange(ObservableProperty.IDENTIFIER, this.identifier, identifier);
		this.identifier = identifier;
		return this;
	}

	/**
	 * Creates a new {@link Name} from a qualified name.<br>
	 * The qualified name can contains "." (dot) characters.
	 * @param qualifiedName qualified name
	 * @return instanceof {@link Name}
	 * @deprecated use JavaParser.parseName instead
	 */
	@Deprecated
	public static Name parse(String qualifiedName) {
		assertNonEmpty(qualifiedName);
		return JavaParser.parseName(qualifiedName);
	}

	/**
	 * @return the complete qualified name. Only the identifiers and the dots, so no comments or whitespace.
	 */
	public String asString() {
		if (qualifier != null) {
			return qualifier.asString() + "." + identifier;
		}
		return identifier;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public Optional<Name> getQualifier() {
		return Optional.ofNullable(qualifier);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public Name setQualifier(final Name qualifier) {
		if (qualifier == this.qualifier) {
			return (Name) this;
		}
		notifyPropertyChange(ObservableProperty.QUALIFIER, this.qualifier, qualifier);
		if (this.qualifier != null)
			this.qualifier.setParentNode(null);
		this.qualifier = qualifier;
		setAsParentNodeOf(qualifier);
		return this;
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
		if (qualifier != null) {
			if (node == qualifier) {
				removeQualifier();
				return true;
			}
		}
		return super.remove(node);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.RemoveMethodGenerator")
	public Name removeQualifier() {
		return setQualifier((Name) null);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public NodeList<AnnotationExpr> getAnnotations() {
		return annotations;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public Name setAnnotations(final NodeList<AnnotationExpr> annotations) {
		assertNotNull(annotations);
		if (annotations == this.annotations) {
			return (Name) this;
		}
		notifyPropertyChange(ObservableProperty.ANNOTATIONS, this.annotations, annotations);
		if (this.annotations != null)
			this.annotations.setParentNode(null);
		this.annotations = annotations;
		setAsParentNodeOf(annotations);
		return this;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetNodeListsGenerator")
	public List<NodeList<?>> getNodeLists() {
		return Arrays.asList(getAnnotations());
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.CloneGenerator")
	public Name clone() {
		return (Name) accept(new CloneVisitor(), null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetMetaModelGenerator")
	public NameMetaModel getMetaModel() {
		return JavaParserMetaModel.nameMetaModel;
	}
}