package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class UnknownTypeMetaModel extends TypeMetaModel {

	UnknownTypeMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.type.UnknownType.class, "UnknownType", "com.yijia.codegen.models.base.ast.type", false, false);
	}
}
