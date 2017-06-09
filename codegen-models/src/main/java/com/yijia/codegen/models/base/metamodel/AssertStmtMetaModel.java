package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class AssertStmtMetaModel extends StatementMetaModel {

	AssertStmtMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.stmt.AssertStmt.class, "AssertStmt", "com.yijia.codegen.models.base.ast.stmt", false, false);
	}

	public PropertyMetaModel checkPropertyMetaModel;

	public PropertyMetaModel messagePropertyMetaModel;
}
