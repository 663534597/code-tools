package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class VariableDeclarationExprMetaModel extends ExpressionMetaModel {

	VariableDeclarationExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.expr.VariableDeclarationExpr.class, "VariableDeclarationExpr", "com.yijia.codegen.models.base.ast.expr", false, false);
	}

	public PropertyMetaModel annotationsPropertyMetaModel;

	public PropertyMetaModel modifiersPropertyMetaModel;

	public PropertyMetaModel variablesPropertyMetaModel;

	public PropertyMetaModel maximumCommonTypePropertyMetaModel;
}
