package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class BooleanLiteralExprMetaModel extends LiteralExprMetaModel {

	BooleanLiteralExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.expr.BooleanLiteralExpr.class, "BooleanLiteralExpr", "com.yijia.codegen.models.base.ast.expr", false, false);
	}

	public PropertyMetaModel valuePropertyMetaModel;
}
