package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class ConstructorDeclarationMetaModel extends CallableDeclarationMetaModel {

	ConstructorDeclarationMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.body.ConstructorDeclaration.class, "ConstructorDeclaration", "com.yijia.codegen.models.base.ast.body", false, false);
	}

	public PropertyMetaModel bodyPropertyMetaModel;
}
