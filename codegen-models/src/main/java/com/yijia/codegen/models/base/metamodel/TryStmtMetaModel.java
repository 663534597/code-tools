package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class TryStmtMetaModel extends StatementMetaModel {

	TryStmtMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.stmt.TryStmt.class, "TryStmt", "com.yijia.codegen.models.base.ast.stmt", false, false);
	}

	public PropertyMetaModel catchClausesPropertyMetaModel;

	public PropertyMetaModel finallyBlockPropertyMetaModel;

	public PropertyMetaModel resourcesPropertyMetaModel;

	public PropertyMetaModel tryBlockPropertyMetaModel;
}
