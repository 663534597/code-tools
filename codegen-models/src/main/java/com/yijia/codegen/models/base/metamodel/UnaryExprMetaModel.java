package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class UnaryExprMetaModel extends ExpressionMetaModel {

	UnaryExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.expr.UnaryExpr.class, "UnaryExpr", "com.yijia.codegen.models.base.ast.expr", false, false);
	}

	public PropertyMetaModel expressionPropertyMetaModel;

	public PropertyMetaModel operatorPropertyMetaModel;

	public PropertyMetaModel postfixPropertyMetaModel;

	public PropertyMetaModel prefixPropertyMetaModel;
}
