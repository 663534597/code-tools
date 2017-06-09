package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class CastExprMetaModel extends ExpressionMetaModel {

	CastExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.expr.CastExpr.class, "CastExpr", "com.yijia.codegen.models.base.ast.expr", false, false);
	}

	public PropertyMetaModel expressionPropertyMetaModel;

	public PropertyMetaModel typePropertyMetaModel;
}
