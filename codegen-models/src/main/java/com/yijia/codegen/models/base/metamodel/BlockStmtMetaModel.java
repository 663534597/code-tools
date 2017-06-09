package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class BlockStmtMetaModel extends StatementMetaModel {

	BlockStmtMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.stmt.BlockStmt.class, "BlockStmt", "com.yijia.codegen.models.base.ast.stmt", false, false);
	}

	public PropertyMetaModel statementsPropertyMetaModel;
}
