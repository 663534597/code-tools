package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class ModuleExportsStmtMetaModel extends ModuleStmtMetaModel {

	ModuleExportsStmtMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.modules.ModuleExportsStmt.class, "ModuleExportsStmt", "com.yijia.codegen.models.base.ast.modules", false, false);
	}

	public PropertyMetaModel moduleNamesPropertyMetaModel;

	public PropertyMetaModel namePropertyMetaModel;
}
