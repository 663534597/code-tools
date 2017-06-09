package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class ImportDeclarationMetaModel extends NodeMetaModel {

	ImportDeclarationMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.ImportDeclaration.class, "ImportDeclaration", "com.yijia.codegen.models.base.ast", false, false);
	}

	public PropertyMetaModel isAsteriskPropertyMetaModel;

	public PropertyMetaModel isStaticPropertyMetaModel;

	public PropertyMetaModel namePropertyMetaModel;
}
