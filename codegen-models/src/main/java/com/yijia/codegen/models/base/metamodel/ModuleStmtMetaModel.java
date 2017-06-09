package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;
import com.yijia.codegen.models.base.ast.Node;

public class ModuleStmtMetaModel extends NodeMetaModel {

	ModuleStmtMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.modules.ModuleStmt.class, "ModuleStmt", "com.yijia.codegen.models.base.ast.modules", true, false);
	}

	protected ModuleStmtMetaModel(Optional<BaseNodeMetaModel> superNodeMetaModel, Class<? extends Node> type, String name, String packageName, boolean isAbstract,
			boolean hasWildcard) {
		super(superNodeMetaModel, type, name, packageName, isAbstract, hasWildcard);
	}
}
