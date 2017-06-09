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
import com.yijia.codegen.models.base.ast.expr.Expression;
import com.yijia.codegen.models.base.ast.expr.SimpleName;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithArguments;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithJavadoc;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithSimpleName;
import com.yijia.codegen.models.base.ast.observer.ObservableProperty;
import com.yijia.codegen.models.base.ast.visitor.CloneVisitor;
import com.yijia.codegen.models.base.ast.visitor.GenericVisitor;
import com.yijia.codegen.models.base.ast.visitor.VoidVisitor;
import com.yijia.codegen.models.base.metamodel.EnumConstantDeclarationMetaModel;
import com.yijia.codegen.models.base.metamodel.JavaParserMetaModel;

/**
 * One of the values an enum can take. A(1) and B(2) in this example: <code>enum X { A(1), B(2) }</code>
 * @author Julio Vilmar Gesser
 */
public final class EnumConstantDeclaration extends BodyDeclaration<EnumConstantDeclaration>
		implements NodeWithJavadoc<EnumConstantDeclaration>, NodeWithSimpleName<EnumConstantDeclaration>, NodeWithArguments<EnumConstantDeclaration> {

	private SimpleName name;

	private NodeList<Expression> arguments;

	private NodeList<BodyDeclaration<?>> classBody;

	public EnumConstantDeclaration() {
		this(null, new NodeList<>(), new SimpleName(), new NodeList<>(), new NodeList<>());
	}

	public EnumConstantDeclaration(String name) {
		this(null, new NodeList<>(), new SimpleName(name), new NodeList<>(), new NodeList<>());
	}

	@AllFieldsConstructor
	public EnumConstantDeclaration(NodeList<AnnotationExpr> annotations, SimpleName name, NodeList<Expression> arguments, NodeList<BodyDeclaration<?>> classBody) {
		this(null, annotations, name, arguments, classBody);
	}

	/** This constructor is used by the parser and is considered private. */
	@Generated("com.yijia.codegen.models.base.generator.core.node.MainConstructorGenerator")
	public EnumConstantDeclaration(TokenRange tokenRange, NodeList<AnnotationExpr> annotations, SimpleName name, NodeList<Expression> arguments,
			NodeList<BodyDeclaration<?>> classBody) {
		super(tokenRange, annotations);
		setName(name);
		setArguments(arguments);
		setClassBody(classBody);
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
	public NodeList<Expression> getArguments() {
		return arguments;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public NodeList<BodyDeclaration<?>> getClassBody() {
		return classBody;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public SimpleName getName() {
		return name;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public EnumConstantDeclaration setArguments(final NodeList<Expression> arguments) {
		assertNotNull(arguments);
		if (arguments == this.arguments) {
			return (EnumConstantDeclaration) this;
		}
		notifyPropertyChange(ObservableProperty.ARGUMENTS, this.arguments, arguments);
		if (this.arguments != null)
			this.arguments.setParentNode(null);
		this.arguments = arguments;
		setAsParentNodeOf(arguments);
		return this;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public EnumConstantDeclaration setClassBody(final NodeList<BodyDeclaration<?>> classBody) {
		assertNotNull(classBody);
		if (classBody == this.classBody) {
			return (EnumConstantDeclaration) this;
		}
		notifyPropertyChange(ObservableProperty.CLASS_BODY, this.classBody, classBody);
		if (this.classBody != null)
			this.classBody.setParentNode(null);
		this.classBody = classBody;
		setAsParentNodeOf(classBody);
		return this;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public EnumConstantDeclaration setName(final SimpleName name) {
		assertNotNull(name);
		if (name == this.name) {
			return (EnumConstantDeclaration) this;
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
		return Arrays.asList(getArguments(), getClassBody(), getAnnotations());
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.RemoveMethodGenerator")
	public boolean remove(Node node) {
		if (node == null)
			return false;
		for (int i = 0; i < arguments.size(); i++) {
			if (arguments.get(i) == node) {
				arguments.remove(i);
				return true;
			}
		}
		for (int i = 0; i < classBody.size(); i++) {
			if (classBody.get(i) == node) {
				classBody.remove(i);
				return true;
			}
		}
		return super.remove(node);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.CloneGenerator")
	public EnumConstantDeclaration clone() {
		return (EnumConstantDeclaration) accept(new CloneVisitor(), null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetMetaModelGenerator")
	public EnumConstantDeclarationMetaModel getMetaModel() {
		return JavaParserMetaModel.enumConstantDeclarationMetaModel;
	}
}
