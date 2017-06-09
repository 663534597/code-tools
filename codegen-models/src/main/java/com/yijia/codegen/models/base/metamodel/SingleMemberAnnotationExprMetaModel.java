package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class SingleMemberAnnotationExprMetaModel extends AnnotationExprMetaModel {

	SingleMemberAnnotationExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.expr.SingleMemberAnnotationExpr.class, "SingleMemberAnnotationExpr", "com.yijia.codegen.models.base.ast.expr", false,
				false);
	}

	public PropertyMetaModel memberValuePropertyMetaModel;
}
