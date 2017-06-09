package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class WhileStmtMetaModel extends StatementMetaModel {

	WhileStmtMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.stmt.WhileStmt.class, "WhileStmt", "com.yijia.codegen.models.base.ast.stmt", false, false);
	}

	public PropertyMetaModel bodyPropertyMetaModel;

	public PropertyMetaModel conditionPropertyMetaModel;
}
