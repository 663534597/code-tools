package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class AssignExprMetaModel extends ExpressionMetaModel {

	AssignExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.expr.AssignExpr.class, "AssignExpr", "com.yijia.codegen.models.base.ast.expr", false, false);
	}

	public PropertyMetaModel operatorPropertyMetaModel;

	public PropertyMetaModel targetPropertyMetaModel;

	public PropertyMetaModel valuePropertyMetaModel;
}
