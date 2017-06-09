package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class ForeachStmtMetaModel extends StatementMetaModel {

	ForeachStmtMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.stmt.ForeachStmt.class, "ForeachStmt", "com.yijia.codegen.models.base.ast.stmt", false, false);
	}

	public PropertyMetaModel bodyPropertyMetaModel;

	public PropertyMetaModel iterablePropertyMetaModel;

	public PropertyMetaModel variablePropertyMetaModel;
}
