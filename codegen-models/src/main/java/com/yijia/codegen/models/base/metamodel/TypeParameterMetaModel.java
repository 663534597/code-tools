package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class TypeParameterMetaModel extends ReferenceTypeMetaModel {

	TypeParameterMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.type.TypeParameter.class, "TypeParameter", "com.yijia.codegen.models.base.ast.type", false, false);
	}

	public PropertyMetaModel namePropertyMetaModel;

	public PropertyMetaModel typeBoundPropertyMetaModel;
}
