package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class LabeledStmtMetaModel extends StatementMetaModel {

	LabeledStmtMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.stmt.LabeledStmt.class, "LabeledStmt", "com.yijia.codegen.models.base.ast.stmt", false, false);
	}

	public PropertyMetaModel labelPropertyMetaModel;

	public PropertyMetaModel statementPropertyMetaModel;
}
