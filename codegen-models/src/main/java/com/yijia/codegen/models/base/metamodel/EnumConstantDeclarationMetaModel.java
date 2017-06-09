package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class EnumConstantDeclarationMetaModel extends BodyDeclarationMetaModel {

	EnumConstantDeclarationMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.body.EnumConstantDeclaration.class, "EnumConstantDeclaration", "com.yijia.codegen.models.base.ast.body", false, false);
	}

	public PropertyMetaModel argumentsPropertyMetaModel;

	public PropertyMetaModel classBodyPropertyMetaModel;

	public PropertyMetaModel namePropertyMetaModel;
}
