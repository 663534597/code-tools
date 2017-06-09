package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class IfStmtMetaModel extends StatementMetaModel {

	IfStmtMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.stmt.IfStmt.class, "IfStmt", "com.yijia.codegen.models.base.ast.stmt", false, false);
	}

	public PropertyMetaModel conditionPropertyMetaModel;

	public PropertyMetaModel elseStmtPropertyMetaModel;

	public PropertyMetaModel thenStmtPropertyMetaModel;

	public PropertyMetaModel elseBlockPropertyMetaModel;

	public PropertyMetaModel thenBlockPropertyMetaModel;
}
