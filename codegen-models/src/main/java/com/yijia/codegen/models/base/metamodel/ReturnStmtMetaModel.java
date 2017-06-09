package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class ReturnStmtMetaModel extends StatementMetaModel {

	ReturnStmtMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.stmt.ReturnStmt.class, "ReturnStmt", "com.yijia.codegen.models.base.ast.stmt", false, false);
	}

	public PropertyMetaModel expressionPropertyMetaModel;
}
