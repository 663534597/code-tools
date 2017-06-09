package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class PackageDeclarationMetaModel extends NodeMetaModel {

	PackageDeclarationMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.PackageDeclaration.class, "PackageDeclaration", "com.yijia.codegen.models.base.ast", false, false);
	}

	public PropertyMetaModel annotationsPropertyMetaModel;

	public PropertyMetaModel namePropertyMetaModel;
}
