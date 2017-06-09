package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class CharLiteralExprMetaModel extends LiteralStringValueExprMetaModel {

	CharLiteralExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.expr.CharLiteralExpr.class, "CharLiteralExpr", "com.yijia.codegen.models.base.ast.expr", false, false);
	}
}
