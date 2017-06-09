package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class EnumDeclarationMetaModel extends TypeDeclarationMetaModel {

	EnumDeclarationMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.body.EnumDeclaration.class, "EnumDeclaration", "com.yijia.codegen.models.base.ast.body", false, false);
	}

	public PropertyMetaModel entriesPropertyMetaModel;

	public PropertyMetaModel implementedTypesPropertyMetaModel;
}
