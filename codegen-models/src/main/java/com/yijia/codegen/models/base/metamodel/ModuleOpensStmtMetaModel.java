package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class ModuleOpensStmtMetaModel extends ModuleStmtMetaModel {

	ModuleOpensStmtMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.modules.ModuleOpensStmt.class, "ModuleOpensStmt", "com.yijia.codegen.models.base.ast.modules", false, false);
	}

	public PropertyMetaModel moduleNamesPropertyMetaModel;

	public PropertyMetaModel namePropertyMetaModel;
}
