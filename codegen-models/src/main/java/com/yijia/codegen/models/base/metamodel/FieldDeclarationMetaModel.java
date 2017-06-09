package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class FieldDeclarationMetaModel extends BodyDeclarationMetaModel {

	FieldDeclarationMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.body.FieldDeclaration.class, "FieldDeclaration", "com.yijia.codegen.models.base.ast.body", false, false);
	}

	public PropertyMetaModel modifiersPropertyMetaModel;

	public PropertyMetaModel variablesPropertyMetaModel;

	public PropertyMetaModel maximumCommonTypePropertyMetaModel;
}
