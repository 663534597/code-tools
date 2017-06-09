package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class ArrayTypeMetaModel extends ReferenceTypeMetaModel {

	ArrayTypeMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.type.ArrayType.class, "ArrayType", "com.yijia.codegen.models.base.ast.type", false, false);
	}

	public PropertyMetaModel componentTypePropertyMetaModel;
}
