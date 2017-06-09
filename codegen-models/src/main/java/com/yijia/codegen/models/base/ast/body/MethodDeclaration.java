/*
 * Copyright (C) 2007-2010 Júlio Vilmar Gesser. Copyright (C) 2011, 2013-2017 The JavaParser Team. This file is part of JavaParser. JavaParser can be used either under the terms of
 * a) the GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. b) the terms of
 * the Apache License You should have received a copy of both licenses in LICENCE.LGPL and LICENCE.APACHE. Please refer to those files for details. JavaParser is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General
 * Public License for more details.
 */
package com.yijia.codegen.models.base.ast.body;

import static com.yijia.codegen.models.base.ast.Modifier.ABSTRACT;
import static com.yijia.codegen.models.base.ast.Modifier.DEFAULT;
import static com.yijia.codegen.models.base.ast.Modifier.FINAL;
import static com.yijia.codegen.models.base.ast.Modifier.NATIVE;
import static com.yijia.codegen.models.base.ast.Modifier.STATIC;
import static com.yijia.codegen.models.base.ast.Modifier.SYNCHRONIZED;
import static com.yijia.codegen.models.base.ast.Modifier.getAccessSpecifier;
import static com.yijia.codegen.models.base.utils.Utils.assertNotNull;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;
import com.yijia.codegen.models.base.TokenRange;
import com.yijia.codegen.models.base.ast.AccessSpecifier;
import com.yijia.codegen.models.base.ast.AllFieldsConstructor;
import com.yijia.codegen.models.base.ast.Modifier;
import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.NodeList;
import com.yijia.codegen.models.base.ast.expr.AnnotationExpr;
import com.yijia.codegen.models.base.ast.expr.SimpleName;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithDeclaration;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithJavadoc;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithOptionalBlockStmt;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithParameters;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithSimpleName;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithThrownExceptions;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithType;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithTypeParameters;
import com.yijia.codegen.models.base.ast.nodeTypes.modifiers.NodeWithAbstractModifier;
import com.yijia.codegen.models.base.ast.nodeTypes.modifiers.NodeWithAccessModifiers;
import com.yijia.codegen.models.base.ast.nodeTypes.modifiers.NodeWithFinalModifier;
import com.yijia.codegen.models.base.ast.nodeTypes.modifiers.NodeWithStaticModifier;
import com.yijia.codegen.models.base.ast.nodeTypes.modifiers.NodeWithStrictfpModifier;
import com.yijia.codegen.models.base.ast.observer.ObservableProperty;
import com.yijia.codegen.models.base.ast.stmt.BlockStmt;
import com.yijia.codegen.models.base.ast.type.ClassOrInterfaceType;
import com.yijia.codegen.models.base.ast.type.ReferenceType;
import com.yijia.codegen.models.base.ast.type.Type;
import com.yijia.codegen.models.base.ast.type.TypeParameter;
import com.yijia.codegen.models.base.ast.visitor.CloneVisitor;
import com.yijia.codegen.models.base.ast.visitor.GenericVisitor;
import com.yijia.codegen.models.base.ast.visitor.VoidVisitor;
import com.yijia.codegen.models.base.metamodel.JavaParserMetaModel;
import com.yijia.codegen.models.base.metamodel.MethodDeclarationMetaModel;

/**
 * A method declaration. "public int abc() {return 1;}" in this example: <code>class X { public int abc() {return 1;}
 * }</code>
 * @author Julio Vilmar Gesser
 */
public final class MethodDeclaration extends CallableDeclaration<MethodDeclaration>
		implements NodeWithType<MethodDeclaration, Type>, NodeWithOptionalBlockStmt<MethodDeclaration>, NodeWithJavadoc<MethodDeclaration>, NodeWithDeclaration,
		NodeWithSimpleName<MethodDeclaration>, NodeWithParameters<MethodDeclaration>, NodeWithThrownExceptions<MethodDeclaration>, NodeWithTypeParameters<MethodDeclaration>,
		NodeWithAccessModifiers<MethodDeclaration>, NodeWithAbstractModifier<MethodDeclaration>, NodeWithStaticModifier<MethodDeclaration>,
		NodeWithFinalModifier<MethodDeclaration>, NodeWithStrictfpModifier<MethodDeclaration> {

	private Type type;

	private BlockStmt body;

	public MethodDeclaration() {
		this(null, EnumSet.noneOf(Modifier.class), new NodeList<>(), new NodeList<>(), new ClassOrInterfaceType(), new SimpleName(), new NodeList<>(), new NodeList<>(),
				new BlockStmt());
	}

	public MethodDeclaration(final EnumSet<Modifier> modifiers, final Type type, final String name) {
		this(null, modifiers, new NodeList<>(), new NodeList<>(), type, new SimpleName(name), new NodeList<>(), new NodeList<>(), new BlockStmt());
	}

	public MethodDeclaration(final EnumSet<Modifier> modifiers, final String name, final Type type, final NodeList<Parameter> parameters) {
		this(null, modifiers, new NodeList<>(), new NodeList<>(), type, new SimpleName(name), parameters, new NodeList<>(), new BlockStmt());
	}

	@AllFieldsConstructor
	public MethodDeclaration(final EnumSet<Modifier> modifiers, final NodeList<AnnotationExpr> annotations, final NodeList<TypeParameter> typeParameters, final Type type,
			final SimpleName name, final NodeList<Parameter> parameters, final NodeList<ReferenceType> thrownExceptions, final BlockStmt body) {
		this(null, modifiers, annotations, typeParameters, type, name, parameters, thrownExceptions, body);
	}

	/**
	 * @deprecated this constructor allows you to set "isDefault", but this is no longer a field of this node, but simply one of the modifiers. Use setDefault(boolean) or add
	 *             DEFAULT to the modifiers set.
	 */
	@Deprecated
	public MethodDeclaration(final EnumSet<Modifier> modifiers, final NodeList<AnnotationExpr> annotations, final NodeList<TypeParameter> typeParameters, final Type type,
			final SimpleName name, final boolean isDefault, final NodeList<Parameter> parameters, final NodeList<ReferenceType> thrownExceptions, final BlockStmt body) {
		this(null, modifiers, annotations, typeParameters, type, name, parameters, thrownExceptions, body);
		setDefault(isDefault);
	}

	/** This constructor is used by the parser and is considered private. */
	@Generated("com.yijia.codegen.models.base.generator.core.node.MainConstructorGenerator")
	public MethodDeclaration(TokenRange tokenRange, EnumSet<Modifier> modifiers, NodeList<AnnotationExpr> annotations, NodeList<TypeParameter> typeParameters, Type type,
			SimpleName name, NodeList<Parameter> parameters, NodeList<ReferenceType> thrownExceptions, BlockStmt body) {
		super(tokenRange, modifiers, annotations, typeParameters, name, parameters, thrownExceptions);
		setType(type);
		setBody(body);
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
	public Optional<BlockStmt> getBody() {
		return Optional.ofNullable(body);
	}

	/**
	 * Sets the body
	 * @param body the body, can be null
	 * @return this, the MethodDeclaration
	 */
	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public MethodDeclaration setBody(final BlockStmt body) {
		if (body == this.body) {
			return (MethodDeclaration) this;
		}
		notifyPropertyChange(ObservableProperty.BODY, this.body, body);
		if (this.body != null)
			this.body.setParentNode(null);
		this.body = body;
		setAsParentNodeOf(body);
		return this;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public Type getType() {
		return type;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public MethodDeclaration setType(final Type type) {
		assertNotNull(type);
		if (type == this.type) {
			return (MethodDeclaration) this;
		}
		notifyPropertyChange(ObservableProperty.TYPE, this.type, type);
		if (this.type != null)
			this.type.setParentNode(null);
		this.type = type;
		setAsParentNodeOf(type);
		return this;
	}

	@Override
	public MethodDeclaration setModifiers(final EnumSet<Modifier> modifiers) {
		return super.setModifiers(modifiers);
	}

	@Override
	public MethodDeclaration setName(final SimpleName name) {
		return super.setName(name);
	}

	@Override
	public MethodDeclaration setParameters(final NodeList<Parameter> parameters) {
		return super.setParameters(parameters);
	}

	@Override
	public MethodDeclaration setThrownExceptions(final NodeList<ReferenceType> thrownExceptions) {
		return super.setThrownExceptions(thrownExceptions);
	}

	@Override
	public MethodDeclaration setTypeParameters(final NodeList<TypeParameter> typeParameters) {
		return super.setTypeParameters(typeParameters);
	}

	/**
	 * The declaration returned has this schema:
	 * <p>
	 * [accessSpecifier] [static] [abstract] [final] [native] [synchronized] returnType methodName ([paramType [paramName]]) [throws exceptionsList]
	 * @return method declaration as String
	 */
	@Override
	public String getDeclarationAsString(boolean includingModifiers, boolean includingThrows, boolean includingParameterName) {
		StringBuilder sb = new StringBuilder();
		if (includingModifiers) {
			AccessSpecifier accessSpecifier = getAccessSpecifier(getModifiers());
			sb.append(accessSpecifier.asString());
			sb.append(accessSpecifier == AccessSpecifier.DEFAULT ? "" : " ");
			if (getModifiers().contains(STATIC)) {
				sb.append("static ");
			}
			if (getModifiers().contains(ABSTRACT)) {
				sb.append("abstract ");
			}
			if (getModifiers().contains(FINAL)) {
				sb.append("final ");
			}
			if (getModifiers().contains(NATIVE)) {
				sb.append("native ");
			}
			if (getModifiers().contains(SYNCHRONIZED)) {
				sb.append("synchronized ");
			}
		}
		// TODO verify it does not print comments connected to the type
		sb.append(getType().toString(prettyPrinterNoCommentsConfiguration));
		sb.append(" ");
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
				if (param.isVarArgs()) {
					sb.append("...");
				}
			}
		}
		sb.append(")");
		sb.append(appendThrowsIfRequested(includingThrows));
		return sb.toString();
	}

	public boolean isNative() {
		return getModifiers().contains(NATIVE);
	}

	public boolean isSynchronized() {
		return getModifiers().contains(SYNCHRONIZED);
	}

	public boolean isDefault() {
		return getModifiers().contains(DEFAULT);
	}

	public MethodDeclaration setNative(boolean set) {
		return setModifier(NATIVE, set);
	}

	public MethodDeclaration setSynchronized(boolean set) {
		return setModifier(SYNCHRONIZED, set);
	}

	public MethodDeclaration setDefault(boolean set) {
		return setModifier(DEFAULT, set);
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
		if (body != null) {
			if (node == body) {
				removeBody();
				return true;
			}
		}
		return super.remove(node);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.RemoveMethodGenerator")
	public MethodDeclaration removeBody() {
		return setBody((BlockStmt) null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.CloneGenerator")
	public MethodDeclaration clone() {
		return (MethodDeclaration) accept(new CloneVisitor(), null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetMetaModelGenerator")
	public MethodDeclarationMetaModel getMetaModel() {
		return JavaParserMetaModel.methodDeclarationMetaModel;
	}
}
