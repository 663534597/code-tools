package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class ArrayAccessExprMetaModel extends ExpressionMetaModel {

	ArrayAccessExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.expr.ArrayAccessExpr.class, "ArrayAccessExpr", "com.yijia.codegen.models.base.ast.expr", false, false);
	}

	public PropertyMetaModel indexPropertyMetaModel;

	public PropertyMetaModel namePropertyMetaModel;
}
