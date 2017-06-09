package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;
import com.yijia.codegen.models.base.ast.Node;

public class AnnotationExprMetaModel extends ExpressionMetaModel {

	AnnotationExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.expr.AnnotationExpr.class, "AnnotationExpr", "com.yijia.codegen.models.base.ast.expr", true, false);
	}

	protected AnnotationExprMetaModel(Optional<BaseNodeMetaModel> superNodeMetaModel, Class<? extends Node> type, String name, String packageName, boolean isAbstract,
			boolean hasWildcard) {
		super(superNodeMetaModel, type, name, packageName, isAbstract, hasWildcard);
	}

	public PropertyMetaModel namePropertyMetaModel;
}
