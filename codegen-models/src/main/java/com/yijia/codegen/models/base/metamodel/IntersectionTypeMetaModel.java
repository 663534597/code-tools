package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class IntersectionTypeMetaModel extends TypeMetaModel {

	IntersectionTypeMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.type.IntersectionType.class, "IntersectionType", "com.yijia.codegen.models.base.ast.type", false, false);
	}

	public PropertyMetaModel elementsPropertyMetaModel;
}
