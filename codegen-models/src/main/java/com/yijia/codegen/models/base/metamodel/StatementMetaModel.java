package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;
import com.yijia.codegen.models.base.ast.Node;

public class StatementMetaModel extends NodeMetaModel {

	StatementMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.stmt.Statement.class, "Statement", "com.yijia.codegen.models.base.ast.stmt", true, false);
	}

	protected StatementMetaModel(Optional<BaseNodeMetaModel> superNodeMetaModel, Class<? extends Node> type, String name, String packageName, boolean isAbstract,
			boolean hasWildcard) {
		super(superNodeMetaModel, type, name, packageName, isAbstract, hasWildcard);
	}
}
