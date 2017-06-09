package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class LambdaExprMetaModel extends ExpressionMetaModel {

	LambdaExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.expr.LambdaExpr.class, "LambdaExpr", "com.yijia.codegen.models.base.ast.expr", false, false);
	}

	public PropertyMetaModel bodyPropertyMetaModel;

	public PropertyMetaModel isEnclosingParametersPropertyMetaModel;

	public PropertyMetaModel parametersPropertyMetaModel;

	public PropertyMetaModel expressionBodyPropertyMetaModel;
}
