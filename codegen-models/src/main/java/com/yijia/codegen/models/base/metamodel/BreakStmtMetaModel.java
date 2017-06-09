package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class BreakStmtMetaModel extends StatementMetaModel {

	BreakStmtMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.stmt.BreakStmt.class, "BreakStmt", "com.yijia.codegen.models.base.ast.stmt", false, false);
	}

	public PropertyMetaModel labelPropertyMetaModel;
}
