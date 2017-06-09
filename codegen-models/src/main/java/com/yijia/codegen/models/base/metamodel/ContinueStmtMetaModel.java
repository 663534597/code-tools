package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class ContinueStmtMetaModel extends StatementMetaModel {

	ContinueStmtMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.stmt.ContinueStmt.class, "ContinueStmt", "com.yijia.codegen.models.base.ast.stmt", false, false);
	}

	public PropertyMetaModel labelPropertyMetaModel;
}
