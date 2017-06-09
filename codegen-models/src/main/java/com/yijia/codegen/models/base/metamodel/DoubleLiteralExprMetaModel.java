package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class DoubleLiteralExprMetaModel extends LiteralStringValueExprMetaModel {

	DoubleLiteralExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.expr.DoubleLiteralExpr.class, "DoubleLiteralExpr", "com.yijia.codegen.models.base.ast.expr", false, false);
	}
}
