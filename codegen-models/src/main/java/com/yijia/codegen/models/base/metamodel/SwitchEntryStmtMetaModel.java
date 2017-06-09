package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class SwitchEntryStmtMetaModel extends StatementMetaModel {

	SwitchEntryStmtMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.stmt.SwitchEntryStmt.class, "SwitchEntryStmt", "com.yijia.codegen.models.base.ast.stmt", false, false);
	}

	public PropertyMetaModel labelPropertyMetaModel;

	public PropertyMetaModel statementsPropertyMetaModel;
}
