package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class InstanceOfExprMetaModel extends ExpressionMetaModel {

	InstanceOfExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.expr.InstanceOfExpr.class, "InstanceOfExpr", "com.yijia.codegen.models.base.ast.expr", false, false);
	}

	public PropertyMetaModel expressionPropertyMetaModel;

	public PropertyMetaModel typePropertyMetaModel;
}
