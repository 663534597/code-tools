package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class DoStmtMetaModel extends StatementMetaModel {

	DoStmtMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.stmt.DoStmt.class, "DoStmt", "com.yijia.codegen.models.base.ast.stmt", false, false);
	}

	public PropertyMetaModel bodyPropertyMetaModel;

	public PropertyMetaModel conditionPropertyMetaModel;
}
