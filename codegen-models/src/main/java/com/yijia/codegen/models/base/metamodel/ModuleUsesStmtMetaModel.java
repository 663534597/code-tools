package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class ModuleUsesStmtMetaModel extends ModuleStmtMetaModel {

	ModuleUsesStmtMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.modules.ModuleUsesStmt.class, "ModuleUsesStmt", "com.yijia.codegen.models.base.ast.modules", false, false);
	}

	public PropertyMetaModel typePropertyMetaModel;
}
