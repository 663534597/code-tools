package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class ObjectCreationExprMetaModel extends ExpressionMetaModel {

	ObjectCreationExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.expr.ObjectCreationExpr.class, "ObjectCreationExpr", "com.yijia.codegen.models.base.ast.expr", false, false);
	}

	public PropertyMetaModel anonymousClassBodyPropertyMetaModel;

	public PropertyMetaModel argumentsPropertyMetaModel;

	public PropertyMetaModel scopePropertyMetaModel;

	public PropertyMetaModel typePropertyMetaModel;

	public PropertyMetaModel typeArgumentsPropertyMetaModel;

	public PropertyMetaModel usingDiamondOperatorPropertyMetaModel;
}
