package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class ModuleDeclarationMetaModel extends NodeMetaModel {

	ModuleDeclarationMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.modules.ModuleDeclaration.class, "ModuleDeclaration", "com.yijia.codegen.models.base.ast.modules", false, false);
	}

	public PropertyMetaModel annotationsPropertyMetaModel;

	public PropertyMetaModel isOpenPropertyMetaModel;

	public PropertyMetaModel moduleStmtsPropertyMetaModel;

	public PropertyMetaModel namePropertyMetaModel;
}
