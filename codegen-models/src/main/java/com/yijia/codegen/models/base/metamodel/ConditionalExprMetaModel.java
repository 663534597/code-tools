package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class ConditionalExprMetaModel extends ExpressionMetaModel {

	ConditionalExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.expr.ConditionalExpr.class, "ConditionalExpr", "com.yijia.codegen.models.base.ast.expr", false, false);
	}

	public PropertyMetaModel conditionPropertyMetaModel;

	public PropertyMetaModel elseExprPropertyMetaModel;

	public PropertyMetaModel thenExprPropertyMetaModel;
}
