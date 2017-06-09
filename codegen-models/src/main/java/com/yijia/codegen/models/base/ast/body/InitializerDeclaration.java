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
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithBlockStmt;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithJavadoc;
import com.yijia.codegen.models.base.ast.observer.ObservableProperty;
import com.yijia.codegen.models.base.ast.stmt.BlockStmt;
import com.yijia.codegen.models.base.ast.visitor.CloneVisitor;
import com.yijia.codegen.models.base.ast.visitor.GenericVisitor;
import com.yijia.codegen.models.base.ast.visitor.VoidVisitor;
import com.yijia.codegen.models.base.metamodel.InitializerDeclarationMetaModel;
import com.yijia.codegen.models.base.metamodel.JavaParserMetaModel;

/**
 * A (possibly static) initializer body. "static { a=3; }" in this example: <code>class X { static { a=3; }  } </code>
 * @author Julio Vilmar Gesser
 */
public final class InitializerDeclaration extends BodyDeclaration<InitializerDeclaration>
		implements NodeWithJavadoc<InitializerDeclaration>, NodeWithBlockStmt<InitializerDeclaration> {

	private boolean isStatic;

	private BlockStmt body;

	public InitializerDeclaration() {
		this(null, false, new BlockStmt());
	}

	@AllFieldsConstructor
	public InitializerDeclaration(boolean isStatic, BlockStmt body) {
		this(null, isStatic, body);
	}

	/** This constructor is used by the parser and is considered private. */
	@Generated("com.yijia.codegen.models.base.generator.core.node.MainConstructorGenerator")
	public InitializerDeclaration(TokenRange tokenRange, boolean isStatic, BlockStmt body) {
		super(tokenRange);
		setStatic(isStatic);
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

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public boolean isStatic() {
		return isStatic;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public InitializerDeclaration setBody(final BlockStmt body) {
		assertNotNull(body);
		if (body == this.body) {
			return (InitializerDeclaration) this;
		}
		notifyPropertyChange(ObservableProperty.BODY, this.body, body);
		if (this.body != null)
			this.body.setParentNode(null);
		this.body = body;
		setAsParentNodeOf(body);
		return this;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public InitializerDeclaration setStatic(final boolean isStatic) {
		if (isStatic == this.isStatic) {
			return (InitializerDeclaration) this;
		}
		notifyPropertyChange(ObservableProperty.STATIC, this.isStatic, isStatic);
		this.isStatic = isStatic;
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
		return super.remove(node);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.CloneGenerator")
	public InitializerDeclaration clone() {
		return (InitializerDeclaration) accept(new CloneVisitor(), null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetMetaModelGenerator")
	public InitializerDeclarationMetaModel getMetaModel() {
		return JavaParserMetaModel.initializerDeclarationMetaModel;
	}
}
