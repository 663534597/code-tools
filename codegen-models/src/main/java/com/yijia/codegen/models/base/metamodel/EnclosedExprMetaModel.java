package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class EnclosedExprMetaModel extends ExpressionMetaModel {

	EnclosedExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.expr.EnclosedExpr.class, "EnclosedExpr", "com.yijia.codegen.models.base.ast.expr", false, false);
	}

	public PropertyMetaModel innerPropertyMetaModel;
}
