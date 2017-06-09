package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;
import com.yijia.codegen.models.base.ast.Node;

public class LiteralStringValueExprMetaModel extends LiteralExprMetaModel {

	LiteralStringValueExprMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.expr.LiteralStringValueExpr.class, "LiteralStringValueExpr", "com.yijia.codegen.models.base.ast.expr", true, false);
	}

	protected LiteralStringValueExprMetaModel(Optional<BaseNodeMetaModel> superNodeMetaModel, Class<? extends Node> type, String name, String packageName, boolean isAbstract,
			boolean hasWildcard) {
		super(superNodeMetaModel, type, name, packageName, isAbstract, hasWildcard);
	}

	public PropertyMetaModel valuePropertyMetaModel;
}
