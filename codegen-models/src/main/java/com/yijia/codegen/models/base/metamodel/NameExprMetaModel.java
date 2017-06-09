package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class NameExprMetaModel extends ExpressionMetaModel {

	NameExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.expr.NameExpr.class, "NameExpr", "com.yijia.codegen.models.base.ast.expr", false, false);
	}

	public PropertyMetaModel namePropertyMetaModel;
}
