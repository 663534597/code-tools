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
import java.util.List;
import javax.annotation.Generated;
import com.yijia.codegen.models.base.TokenRange;
import com.yijia.codegen.models.base.ast.AllFieldsConstructor;
import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.NodeList;
import com.yijia.codegen.models.base.ast.expr.AnnotationExpr;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithAnnotations;
import com.yijia.codegen.models.base.ast.observer.ObservableProperty;
import com.yijia.codegen.models.base.ast.visitor.CloneVisitor;
import com.yijia.codegen.models.base.metamodel.BodyDeclarationMetaModel;
import com.yijia.codegen.models.base.metamodel.JavaParserMetaModel;

/**
 * Any declaration that can appear between the { and } of a class, interface, or enum.
 * @author Julio Vilmar Gesser
 */
public abstract class BodyDeclaration<T extends BodyDeclaration<?>> extends Node implements NodeWithAnnotations<T> {

	private NodeList<AnnotationExpr> annotations;

	public BodyDeclaration() {
		this(null, new NodeList<>());
	}

	@AllFieldsConstructor
	public BodyDeclaration(NodeList<AnnotationExpr> annotations) {
		this(null, annotations);
	}

	/** This constructor is used by the parser and is considered private. */
	@Generated("com.yijia.codegen.models.base.generator.core.node.MainConstructorGenerator")
	public BodyDeclaration(TokenRange tokenRange, NodeList<AnnotationExpr> annotations) {
		super(tokenRange);
		setAnnotations(annotations);
		customInitialization();
	}

	/**
	 * This supports {@link EmptyMemberDeclaration}.
	 */
	protected BodyDeclaration(TokenRange range) {
		this(range, new NodeList<>());
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public NodeList<AnnotationExpr> getAnnotations() {
		return annotations;
	}

	@SuppressWarnings("unchecked")
	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public T setAnnotations(final NodeList<AnnotationExpr> annotations) {
		assertNotNull(annotations);
		if (annotations == this.annotations) {
			return (T) this;
		}
		notifyPropertyChange(ObservableProperty.ANNOTATIONS, this.annotations, annotations);
		if (this.annotations != null)
			this.annotations.setParentNode(null);
		this.annotations = annotations;
		setAsParentNodeOf(annotations);
		return (T) this;
	}

	@Override
	public List<NodeList<?>> getNodeLists() {
		return Arrays.asList(annotations);
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
	public BodyDeclaration<?> clone() {
		return (BodyDeclaration<?>) accept(new CloneVisitor(), null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetMetaModelGenerator")
	public BodyDeclarationMetaModel getMetaModel() {
		return JavaParserMetaModel.bodyDeclarationMetaModel;
	}
}
