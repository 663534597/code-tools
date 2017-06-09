/*
 * Copyright (C) 2007-2010 Júlio Vilmar Gesser. Copyright (C) 2011, 2013-2016 The JavaParser Team. This file is part of JavaParser. JavaParser can be used either under the terms of
 * a) the GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. b) the terms of
 * the Apache License You should have received a copy of both licenses in LICENCE.LGPL and LICENCE.APACHE. Please refer to those files for details. JavaParser is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General
 * Public License for more details.
 */
package com.yijia.codegen.models.base.ast.expr;

import static com.yijia.codegen.models.base.utils.Utils.assertNotNull;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;
import com.yijia.codegen.models.base.TokenRange;
import com.yijia.codegen.models.base.ast.AllFieldsConstructor;
import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.NodeList;
import com.yijia.codegen.models.base.ast.body.Parameter;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithParameters;
import com.yijia.codegen.models.base.ast.observer.ObservableProperty;
import com.yijia.codegen.models.base.ast.stmt.ExpressionStmt;
import com.yijia.codegen.models.base.ast.stmt.ReturnStmt;
import com.yijia.codegen.models.base.ast.stmt.Statement;
import com.yijia.codegen.models.base.ast.visitor.CloneVisitor;
import com.yijia.codegen.models.base.ast.visitor.GenericVisitor;
import com.yijia.codegen.models.base.ast.visitor.VoidVisitor;
import com.yijia.codegen.models.base.metamodel.DerivedProperty;
import com.yijia.codegen.models.base.metamodel.JavaParserMetaModel;
import com.yijia.codegen.models.base.metamodel.LambdaExprMetaModel;

/**
 * A lambda expression. The parameters are on the left side of the ->. If a parameter uses type inference (it has no type specified) then its type is set to UnknownType. If they
 * are in ( ), "isEnclosingParameters" is true. The body is to the right of the ->. <br/>
 * <code>(a, b) -> a+b</code> <br/>
 * <code>a -> ...</code> <br/>
 * <code>(Long a) -> {println(a);}</code>
 * @author Raquel Pau
 */
public class LambdaExpr extends Expression implements NodeWithParameters<LambdaExpr> {

	private NodeList<Parameter> parameters;

	private boolean isEnclosingParameters;

	private Statement body;

	public LambdaExpr() {
		this(null, new NodeList<>(), new ReturnStmt(), false);
	}

	@AllFieldsConstructor
	public LambdaExpr(NodeList<Parameter> parameters, Statement body, boolean isEnclosingParameters) {
		this(null, parameters, body, isEnclosingParameters);
	}

	/** This constructor is used by the parser and is considered private. */
	@Generated("com.yijia.codegen.models.base.generator.core.node.MainConstructorGenerator")
	public LambdaExpr(TokenRange tokenRange, NodeList<Parameter> parameters, Statement body, boolean isEnclosingParameters) {
		super(tokenRange);
		setParameters(parameters);
		setBody(body);
		setEnclosingParameters(isEnclosingParameters);
		customInitialization();
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public NodeList<Parameter> getParameters() {
		return parameters;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public LambdaExpr setParameters(final NodeList<Parameter> parameters) {
		assertNotNull(parameters);
		if (parameters == this.parameters) {
			return (LambdaExpr) this;
		}
		notifyPropertyChange(ObservableProperty.PARAMETERS, this.parameters, parameters);
		if (this.parameters != null)
			this.parameters.setParentNode(null);
		this.parameters = parameters;
		setAsParentNodeOf(parameters);
		return this;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public Statement getBody() {
		return body;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public LambdaExpr setBody(final Statement body) {
		assertNotNull(body);
		if (body == this.body) {
			return (LambdaExpr) this;
		}
		notifyPropertyChange(ObservableProperty.BODY, this.body, body);
		if (this.body != null)
			this.body.setParentNode(null);
		this.body = body;
		setAsParentNodeOf(body);
		return this;
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
	public boolean isEnclosingParameters() {
		return isEnclosingParameters;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public LambdaExpr setEnclosingParameters(final boolean isEnclosingParameters) {
		if (isEnclosingParameters == this.isEnclosingParameters) {
			return (LambdaExpr) this;
		}
		notifyPropertyChange(ObservableProperty.ENCLOSING_PARAMETERS, this.isEnclosingParameters, isEnclosingParameters);
		this.isEnclosingParameters = isEnclosingParameters;
		return this;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetNodeListsGenerator")
	public List<NodeList<?>> getNodeLists() {
		return Arrays.asList(getParameters());
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.RemoveMethodGenerator")
	public boolean remove(Node node) {
		if (node == null)
			return false;
		for (int i = 0; i < parameters.size(); i++) {
			if (parameters.get(i) == node) {
				parameters.remove(i);
				return true;
			}
		}
		return super.remove(node);
	}

	@DerivedProperty
	public Optional<Expression> getExpressionBody() {
		if (body instanceof ExpressionStmt) {
			return Optional.of(((ExpressionStmt) body).getExpression());
		} else {
			return Optional.empty();
		}
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.CloneGenerator")
	public LambdaExpr clone() {
		return (LambdaExpr) accept(new CloneVisitor(), null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetMetaModelGenerator")
	public LambdaExprMetaModel getMetaModel() {
		return JavaParserMetaModel.lambdaExprMetaModel;
	}
}
