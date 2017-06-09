package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class ClassOrInterfaceTypeMetaModel extends ReferenceTypeMetaModel {

	ClassOrInterfaceTypeMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.type.ClassOrInterfaceType.class, "ClassOrInterfaceType", "com.yijia.codegen.models.base.ast.type", false, false);
	}

	public PropertyMetaModel namePropertyMetaModel;

	public PropertyMetaModel scopePropertyMetaModel;

	public PropertyMetaModel typeArgumentsPropertyMetaModel;

	public PropertyMetaModel usingDiamondOperatorPropertyMetaModel;
}
