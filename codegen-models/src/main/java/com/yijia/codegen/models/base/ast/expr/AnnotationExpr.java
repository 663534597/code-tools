/*
 * Copyright (C) 2007-2010 Júlio Vilmar Gesser. Copyright (C) 2011, 2013-2016 The JavaParser Team. This file is part of JavaParser. JavaParser can be used either under the terms of
 * a) the GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. b) the terms of
 * the Apache License You should have received a copy of both licenses in LICENCE.LGPL and LICENCE.APACHE. Please refer to those files for details. JavaParser is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General
 * Public License for more details.
 */
package com.yijia.codegen.models.base.ast.expr;

import static com.yijia.codegen.models.base.utils.Utils.assertNotNull;
import javax.annotation.Generated;
import com.yijia.codegen.models.base.TokenRange;
import com.yijia.codegen.models.base.ast.AllFieldsConstructor;
import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithName;
import com.yijia.codegen.models.base.ast.observer.ObservableProperty;
import com.yijia.codegen.models.base.ast.visitor.CloneVisitor;
import com.yijia.codegen.models.base.metamodel.AnnotationExprMetaModel;
import com.yijia.codegen.models.base.metamodel.JavaParserMetaModel;

/**
 * A base class for the different types of annotations.
 * @author Julio Vilmar Gesser
 */
public abstract class AnnotationExpr extends Expression implements NodeWithName<AnnotationExpr> {

	protected Name name;

	public AnnotationExpr() {
		this(null, new Name());
	}

	@AllFieldsConstructor
	public AnnotationExpr(Name name) {
		this(null, name);
	}

	/** This constructor is used by the parser and is considered private. */
	@Generated("com.yijia.codegen.models.base.generator.core.node.MainConstructorGenerator")
	public AnnotationExpr(TokenRange tokenRange, Name name) {
		super(tokenRange);
		setName(name);
		customInitialization();
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public Name getName() {
		return name;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public AnnotationExpr setName(final Name name) {
		assertNotNull(name);
		if (name == this.name) {
			return (AnnotationExpr) this;
		}
		notifyPropertyChange(ObservableProperty.NAME, this.name, name);
		if (this.name != null)
			this.name.setParentNode(null);
		this.name = name;
		setAsParentNodeOf(name);
		return this;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.RemoveMethodGenerator")
	public boolean remove(Node node) {
		if (node == null)
			return false;
		return super.remove(node);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.CloneGenerator")
	public AnnotationExpr clone() {
		return (AnnotationExpr) accept(new CloneVisitor(), null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetMetaModelGenerator")
	public AnnotationExprMetaModel getMetaModel() {
		return JavaParserMetaModel.annotationExprMetaModel;
	}
}
