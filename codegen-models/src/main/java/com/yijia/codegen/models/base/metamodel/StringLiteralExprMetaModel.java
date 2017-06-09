package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class StringLiteralExprMetaModel extends LiteralStringValueExprMetaModel {

	StringLiteralExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.expr.StringLiteralExpr.class, "StringLiteralExpr", "com.yijia.codegen.models.base.ast.expr", false, false);
	}
}
