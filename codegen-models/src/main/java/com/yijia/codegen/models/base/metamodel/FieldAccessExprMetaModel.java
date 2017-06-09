package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class FieldAccessExprMetaModel extends ExpressionMetaModel {

	FieldAccessExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.expr.FieldAccessExpr.class, "FieldAccessExpr", "com.yijia.codegen.models.base.ast.expr", false, false);
	}

	public PropertyMetaModel namePropertyMetaModel;

	public PropertyMetaModel scopePropertyMetaModel;

	public PropertyMetaModel typeArgumentsPropertyMetaModel;

	public PropertyMetaModel usingDiamondOperatorPropertyMetaModel;
}
