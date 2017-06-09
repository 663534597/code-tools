package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class SuperExprMetaModel extends ExpressionMetaModel {

	SuperExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.expr.SuperExpr.class, "SuperExpr", "com.yijia.codegen.models.base.ast.expr", false, false);
	}

	public PropertyMetaModel classExprPropertyMetaModel;
}
