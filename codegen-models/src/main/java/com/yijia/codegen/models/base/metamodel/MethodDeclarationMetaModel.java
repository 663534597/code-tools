package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class MethodDeclarationMetaModel extends CallableDeclarationMetaModel {

	MethodDeclarationMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.body.MethodDeclaration.class, "MethodDeclaration", "com.yijia.codegen.models.base.ast.body", false, false);
	}

	public PropertyMetaModel bodyPropertyMetaModel;

	public PropertyMetaModel typePropertyMetaModel;
}
