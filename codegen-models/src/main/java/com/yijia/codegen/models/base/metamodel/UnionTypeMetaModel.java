package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class UnionTypeMetaModel extends TypeMetaModel {

	UnionTypeMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.type.UnionType.class, "UnionType", "com.yijia.codegen.models.base.ast.type", false, false);
	}

	public PropertyMetaModel elementsPropertyMetaModel;
}
