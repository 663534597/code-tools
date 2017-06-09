package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class ThisExprMetaModel extends ExpressionMetaModel {

	ThisExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.expr.ThisExpr.class, "ThisExpr", "com.yijia.codegen.models.base.ast.expr", false, false);
	}

	public PropertyMetaModel classExprPropertyMetaModel;
}
