package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class ArrayCreationLevelMetaModel extends NodeMetaModel {

	ArrayCreationLevelMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.ArrayCreationLevel.class, "ArrayCreationLevel", "com.yijia.codegen.models.base.ast", false, false);
	}

	public PropertyMetaModel annotationsPropertyMetaModel;

	public PropertyMetaModel dimensionPropertyMetaModel;
}
