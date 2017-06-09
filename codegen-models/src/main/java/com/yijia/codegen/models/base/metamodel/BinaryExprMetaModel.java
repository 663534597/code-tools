package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class BinaryExprMetaModel extends ExpressionMetaModel {

	BinaryExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.expr.BinaryExpr.class, "BinaryExpr", "com.yijia.codegen.models.base.ast.expr", false, false);
	}

	public PropertyMetaModel leftPropertyMetaModel;

	public PropertyMetaModel operatorPropertyMetaModel;

	public PropertyMetaModel rightPropertyMetaModel;
}
