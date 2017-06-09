package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class ClassExprMetaModel extends ExpressionMetaModel {

	ClassExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.expr.ClassExpr.class, "ClassExpr", "com.yijia.codegen.models.base.ast.expr", false, false);
	}

	public PropertyMetaModel typePropertyMetaModel;
}
