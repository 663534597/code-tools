package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class NullLiteralExprMetaModel extends LiteralExprMetaModel {

	NullLiteralExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.expr.NullLiteralExpr.class, "NullLiteralExpr", "com.yijia.codegen.models.base.ast.expr", false, false);
	}
}
