package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class LongLiteralExprMetaModel extends LiteralStringValueExprMetaModel {

	LongLiteralExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.expr.LongLiteralExpr.class, "LongLiteralExpr", "com.yijia.codegen.models.base.ast.expr", false, false);
	}
}
