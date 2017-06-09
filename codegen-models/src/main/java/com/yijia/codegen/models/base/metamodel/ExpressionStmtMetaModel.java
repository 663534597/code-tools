package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class ExpressionStmtMetaModel extends StatementMetaModel {

	ExpressionStmtMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.stmt.ExpressionStmt.class, "ExpressionStmt", "com.yijia.codegen.models.base.ast.stmt", false, false);
	}

	public PropertyMetaModel expressionPropertyMetaModel;
}
