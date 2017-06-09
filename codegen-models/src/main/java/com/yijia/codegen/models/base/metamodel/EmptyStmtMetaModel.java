package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class EmptyStmtMetaModel extends StatementMetaModel {

	EmptyStmtMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.stmt.EmptyStmt.class, "EmptyStmt", "com.yijia.codegen.models.base.ast.stmt", false, false);
	}
}
