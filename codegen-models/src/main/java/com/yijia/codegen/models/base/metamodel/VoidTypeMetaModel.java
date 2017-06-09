package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class VoidTypeMetaModel extends TypeMetaModel {

	VoidTypeMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.type.VoidType.class, "VoidType", "com.yijia.codegen.models.base.ast.type", false, false);
	}
}
