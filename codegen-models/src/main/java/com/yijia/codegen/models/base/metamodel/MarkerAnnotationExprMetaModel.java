package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class MarkerAnnotationExprMetaModel extends AnnotationExprMetaModel {

	MarkerAnnotationExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.expr.MarkerAnnotationExpr.class, "MarkerAnnotationExpr", "com.yijia.codegen.models.base.ast.expr", false, false);
	}
}
