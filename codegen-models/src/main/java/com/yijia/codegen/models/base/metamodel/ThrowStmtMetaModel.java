package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class ThrowStmtMetaModel extends StatementMetaModel {

	ThrowStmtMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.stmt.ThrowStmt.class, "ThrowStmt", "com.yijia.codegen.models.base.ast.stmt", false, false);
	}

	public PropertyMetaModel expressionPropertyMetaModel;
}
