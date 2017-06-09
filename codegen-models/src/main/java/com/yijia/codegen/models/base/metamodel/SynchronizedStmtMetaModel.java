package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class SynchronizedStmtMetaModel extends StatementMetaModel {

	SynchronizedStmtMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.stmt.SynchronizedStmt.class, "SynchronizedStmt", "com.yijia.codegen.models.base.ast.stmt", false, false);
	}

	public PropertyMetaModel bodyPropertyMetaModel;

	public PropertyMetaModel expressionPropertyMetaModel;
}
