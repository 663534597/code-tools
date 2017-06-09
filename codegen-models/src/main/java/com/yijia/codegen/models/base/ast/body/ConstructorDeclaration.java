/*
 * Copyright (C) 2007-2010 Júlio Vilmar Gesser. Copyright (C) 2011, 2013-2017 The JavaParser Team. This file is part of JavaParser. JavaParser can be used either under the terms of
 * a) the GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. b) the terms of
 * the Apache License You should have received a copy of both licenses in LICENCE.LGPL and LICENCE.APACHE. Please refer to those files for details. JavaParser is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General
 * Public License for more details.
 */
package com.yijia.codegen.models.base.ast.body;

import static com.yijia.codegen.models.base.utils.Utils.assertNotNull;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import javax.annotation.Generated;
import com.yijia.codegen.models.base.TokenRange;
import com.yijia.codegen.models.base.ast.AccessSpecifier;
import com.yijia.codegen.models.base.ast.AllFieldsConstructor;
import com.yijia.codegen.models.base.ast.Modifier;
import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.NodeList;
import com.yijia.codegen.models.base.ast.expr.AnnotationExpr;
import com.yijia.codegen.models.base.ast.expr.SimpleName;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithBlockStmt;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithDeclaration;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithJavadoc;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithParameters;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithSimpleName;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithThrownExceptions;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithTypeParameters;
import com.yijia.codegen.models.base.ast.nodeTypes.modifiers.NodeWithAccessModifiers;
import com.yijia.codegen.models.base.ast.observer.ObservableProperty;
import com.yijia.codegen.models.base.ast.stmt.BlockStmt;
import com.yijia.codegen.models.base.ast.type.ReferenceType;
import com.yijia.codegen.models.base.ast.type.TypeParameter;
import com.yijia.codegen.models.base.ast.visitor.CloneVisitor;
import com.yijia.codegen.models.base.ast.visitor.GenericVisitor;
import com.yijia.codegen.models.base.ast.visitor.VoidVisitor;
import com.yijia.codegen.models.base.metamodel.ConstructorDeclarationMetaModel;
import com.yijia.codegen.models.base.metamodel.JavaParserMetaModel;

/**
 * A constructor declaration: <code>class X { X() { } }</code> where X(){} is the constructor declaration.
 * @author Julio Vilmar Gesser
 */
public final class ConstructorDeclaration extends CallableDeclaration<ConstructorDeclaration> implements NodeWithBlockStmt<ConstructorDeclaration>,
		NodeWithAccessModifiers<ConstructorDeclaration>, NodeWithJavadoc<ConstructorDeclaration>, NodeWithDeclaration, NodeWithSimpleName<ConstructorDeclaration>,
		NodeWithParameters<ConstructorDeclaration>, NodeWithThrownExceptions<ConstructorDeclaration>, NodeWithTypeParameters<ConstructorDeclaration> {

	private BlockStmt body;

	public ConstructorDeclaration() {
		this(null, EnumSet.noneOf(Modifier.class), new NodeList<>(), new NodeList<>(), new SimpleName(), new NodeList<>(), new NodeList<>(), new BlockStmt());
	}

	public ConstructorDeclaration(EnumSet<Modifier> modifiers, String name) {
		this(null, modifiers, new NodeList<>(), new NodeList<>(), new SimpleName(name), new NodeList<>(), new NodeList<>(), new BlockStmt());
	}

	@AllFieldsConstructor
	public ConstructorDeclaration(EnumSet<Modifier> modifiers, NodeList<AnnotationExpr> annotations, NodeList<TypeParameter> typeParameters, SimpleName name,
			NodeList<Parameter> parameters, NodeList<ReferenceType> thrownExceptions, BlockStmt body) {
		this(null, modifiers, annotations, typeParameters, name, parameters, thrownExceptions, body);
	}

	/** This constructor is used by the parser and is considered private. */
	@Generated("com.yijia.codegen.models.base.generator.core.node.MainConstructorGenerator")
	public ConstructorDeclaration(TokenRange tokenRange, EnumSet<Modifier> modifiers, NodeList<AnnotationExpr> annotations, NodeList<TypeParameter> typeParameters, SimpleName name,
			NodeList<Parameter> parameters, NodeList<ReferenceType> thrownExceptions, BlockStmt body) {
		super(tokenRange, modifiers, annotations, typeParameters, name, parameters, thrownExceptions);
		setBody(body);
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
	public BlockStmt getBody() {
		return body;
	}

	/**
	 * Sets the body
	 * @param body the body, can not be null
	 * @return this, the ConstructorDeclaration
	 */
	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public ConstructorDeclaration setBody(final BlockStmt body) {
		assertNotNull(body);
		if (body == this.body) {
			return (ConstructorDeclaration) this;
		}
		notifyPropertyChange(ObservableProperty.BODY, this.body, body);
		if (this.body != null)
			this.body.setParentNode(null);
		this.body = body;
		setAsParentNodeOf(body);
		return this;
	}

	@Override
	public ConstructorDeclaration setModifiers(final EnumSet<Modifier> modifiers) {
		return super.setModifiers(modifiers);
	}

	@Override
	public ConstructorDeclaration setName(final SimpleName name) {
		return super.setName(name);
	}

	@Override
	public ConstructorDeclaration setParameters(final NodeList<Parameter> parameters) {
		return super.setParameters(parameters);
	}

	@Override
	public ConstructorDeclaration setThrownExceptions(final NodeList<ReferenceType> thrownExceptions) {
		return super.setThrownExceptions(thrownExceptions);
	}

	@Override
	public ConstructorDeclaration setTypeParameters(final NodeList<TypeParameter> typeParameters) {
		return super.setTypeParameters(typeParameters);
	}

	/**
	 * The declaration returned has this schema:
	 * <p>
	 * [accessSpecifier] className ([paramType [paramName]]) [throws exceptionsList]
	 */
	@Override
	public String getDeclarationAsString(boolean includingModifiers, boolean includingThrows, boolean includingParameterName) {
		StringBuilder sb = new StringBuilder();
		if (includingModifiers) {
			AccessSpecifier accessSpecifier = Modifier.getAccessSpecifier(getModifiers());
			sb.append(accessSpecifier.asString());
			sb.append(accessSpecifier == AccessSpecifier.DEFAULT ? "" : " ");
		}
		sb.append(getName());
		sb.append("(");
		boolean firstParam = true;
		for (Parameter param : getParameters()) {
			if (firstParam) {
				firstParam = false;
			} else {
				sb.append(", ");
			}
			if (includingParameterName) {
				sb.append(param.toString(prettyPrinterNoCommentsConfiguration));
			} else {
				sb.append(param.getType().toString(prettyPrinterNoCommentsConfiguration));
			}
		}
		sb.append(")");
		sb.append(appendThrowsIfRequested(includingThrows));
		return sb.toString();
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetNodeListsGenerator")
	public List<NodeList<?>> getNodeLists() {
		return Arrays.asList(getParameters(), getThrownExceptions(), getTypeParameters(), getAnnotations());
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
	public ConstructorDeclaration clone() {
		return (ConstructorDeclaration) accept(new CloneVisitor(), null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetMetaModelGenerator")
	public ConstructorDeclarationMetaModel getMetaModel() {
		return JavaParserMetaModel.constructorDeclarationMetaModel;
	}
}
