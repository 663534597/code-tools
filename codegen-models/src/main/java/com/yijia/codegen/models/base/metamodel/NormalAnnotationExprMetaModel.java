package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class NormalAnnotationExprMetaModel extends AnnotationExprMetaModel {

	NormalAnnotationExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.expr.NormalAnnotationExpr.class, "NormalAnnotationExpr", "com.yijia.codegen.models.base.ast.expr", false, false);
	}

	public PropertyMetaModel pairsPropertyMetaModel;
}
