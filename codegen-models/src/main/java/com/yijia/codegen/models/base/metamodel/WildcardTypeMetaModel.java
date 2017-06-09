package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class WildcardTypeMetaModel extends TypeMetaModel {

	WildcardTypeMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.type.WildcardType.class, "WildcardType", "com.yijia.codegen.models.base.ast.type", false, false);
	}

	public PropertyMetaModel extendedTypePropertyMetaModel;

	public PropertyMetaModel superTypePropertyMetaModel;
}
