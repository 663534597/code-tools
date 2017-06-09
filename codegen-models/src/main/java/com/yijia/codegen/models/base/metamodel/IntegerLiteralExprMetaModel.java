package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class IntegerLiteralExprMetaModel extends LiteralStringValueExprMetaModel {

	IntegerLiteralExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.expr.IntegerLiteralExpr.class, "IntegerLiteralExpr", "com.yijia.codegen.models.base.ast.expr", false, false);
	}
}
