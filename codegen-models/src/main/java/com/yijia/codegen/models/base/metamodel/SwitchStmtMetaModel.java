package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class SwitchStmtMetaModel extends StatementMetaModel {

	SwitchStmtMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.stmt.SwitchStmt.class, "SwitchStmt", "com.yijia.codegen.models.base.ast.stmt", false, false);
	}

	public PropertyMetaModel entriesPropertyMetaModel;

	public PropertyMetaModel selectorPropertyMetaModel;
}
