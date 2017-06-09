package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class MethodReferenceExprMetaModel extends ExpressionMetaModel {

	MethodReferenceExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.expr.MethodReferenceExpr.class, "MethodReferenceExpr", "com.yijia.codegen.models.base.ast.expr", false, false);
	}

	public PropertyMetaModel identifierPropertyMetaModel;

	public PropertyMetaModel scopePropertyMetaModel;

	public PropertyMetaModel typeArgumentsPropertyMetaModel;

	public PropertyMetaModel usingDiamondOperatorPropertyMetaModel;
}
