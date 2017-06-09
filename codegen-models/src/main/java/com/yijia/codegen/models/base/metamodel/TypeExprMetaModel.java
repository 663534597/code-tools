package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class TypeExprMetaModel extends ExpressionMetaModel {

	TypeExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.expr.TypeExpr.class, "TypeExpr", "com.yijia.codegen.models.base.ast.expr", false, false);
	}

	public PropertyMetaModel typePropertyMetaModel;
}
