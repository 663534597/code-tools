package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class PrimitiveTypeMetaModel extends TypeMetaModel {

	PrimitiveTypeMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.type.PrimitiveType.class, "PrimitiveType", "com.yijia.codegen.models.base.ast.type", false, false);
	}

	public PropertyMetaModel typePropertyMetaModel;
}
