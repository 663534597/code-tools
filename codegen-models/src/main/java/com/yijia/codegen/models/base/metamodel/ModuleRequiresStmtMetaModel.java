package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class ModuleRequiresStmtMetaModel extends ModuleStmtMetaModel {

	ModuleRequiresStmtMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.modules.ModuleRequiresStmt.class, "ModuleRequiresStmt", "com.yijia.codegen.models.base.ast.modules", false, false);
	}

	public PropertyMetaModel modifiersPropertyMetaModel;

	public PropertyMetaModel namePropertyMetaModel;
}
