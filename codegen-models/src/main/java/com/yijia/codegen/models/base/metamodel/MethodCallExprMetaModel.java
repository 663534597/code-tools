package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class MethodCallExprMetaModel extends ExpressionMetaModel {

	MethodCallExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.expr.MethodCallExpr.class, "MethodCallExpr", "com.yijia.codegen.models.base.ast.expr", false, false);
	}

	public PropertyMetaModel argumentsPropertyMetaModel;

	public PropertyMetaModel namePropertyMetaModel;

	public PropertyMetaModel scopePropertyMetaModel;

	public PropertyMetaModel typeArgumentsPropertyMetaModel;

	public PropertyMetaModel usingDiamondOperatorPropertyMetaModel;
}
