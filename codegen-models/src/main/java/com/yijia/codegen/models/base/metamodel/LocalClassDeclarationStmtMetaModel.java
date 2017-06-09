package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class LocalClassDeclarationStmtMetaModel extends StatementMetaModel {

	LocalClassDeclarationStmtMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.stmt.LocalClassDeclarationStmt.class, "LocalClassDeclarationStmt", "com.yijia.codegen.models.base.ast.stmt", false, false);
	}

	public PropertyMetaModel classDeclarationPropertyMetaModel;
}
