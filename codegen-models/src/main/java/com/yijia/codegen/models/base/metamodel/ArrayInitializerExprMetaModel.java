package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class ArrayInitializerExprMetaModel extends ExpressionMetaModel {

	ArrayInitializerExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.expr.ArrayInitializerExpr.class, "ArrayInitializerExpr", "com.yijia.codegen.models.base.ast.expr", false, false);
	}

	public PropertyMetaModel valuesPropertyMetaModel;
}
