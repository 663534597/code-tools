package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class SimpleNameMetaModel extends NodeMetaModel {

	SimpleNameMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.expr.SimpleName.class, "SimpleName", "com.yijia.codegen.models.base.ast.expr", false, false);
	}

	public PropertyMetaModel identifierPropertyMetaModel;
}
