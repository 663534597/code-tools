package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class ForStmtMetaModel extends StatementMetaModel {

	ForStmtMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.stmt.ForStmt.class, "ForStmt", "com.yijia.codegen.models.base.ast.stmt", false, false);
	}

	public PropertyMetaModel bodyPropertyMetaModel;

	public PropertyMetaModel comparePropertyMetaModel;

	public PropertyMetaModel initializationPropertyMetaModel;

	public PropertyMetaModel updatePropertyMetaModel;
}
