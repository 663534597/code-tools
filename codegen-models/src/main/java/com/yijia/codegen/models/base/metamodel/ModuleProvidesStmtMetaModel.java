package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class ModuleProvidesStmtMetaModel extends ModuleStmtMetaModel {

	ModuleProvidesStmtMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.modules.ModuleProvidesStmt.class, "ModuleProvidesStmt", "com.yijia.codegen.models.base.ast.modules", false, false);
	}

	public PropertyMetaModel typePropertyMetaModel;

	public PropertyMetaModel withTypesPropertyMetaModel;
}
