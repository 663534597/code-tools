package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class ArrayCreationExprMetaModel extends ExpressionMetaModel {

	ArrayCreationExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.expr.ArrayCreationExpr.class, "ArrayCreationExpr", "com.yijia.codegen.models.base.ast.expr", false, false);
	}

	public PropertyMetaModel elementTypePropertyMetaModel;

	public PropertyMetaModel initializerPropertyMetaModel;

	public PropertyMetaModel levelsPropertyMetaModel;
}
