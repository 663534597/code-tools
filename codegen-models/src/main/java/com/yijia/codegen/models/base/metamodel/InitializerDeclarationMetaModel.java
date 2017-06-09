package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class InitializerDeclarationMetaModel extends BodyDeclarationMetaModel {

	InitializerDeclarationMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.body.InitializerDeclaration.class, "InitializerDeclaration", "com.yijia.codegen.models.base.ast.body", false, false);
	}

	public PropertyMetaModel bodyPropertyMetaModel;

	public PropertyMetaModel isStaticPropertyMetaModel;
}
