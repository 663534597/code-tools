package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class VariableDeclaratorMetaModel extends NodeMetaModel {

	VariableDeclaratorMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.body.VariableDeclarator.class, "VariableDeclarator", "com.yijia.codegen.models.base.ast.body", false, false);
	}

	public PropertyMetaModel initializerPropertyMetaModel;

	public PropertyMetaModel namePropertyMetaModel;

	public PropertyMetaModel typePropertyMetaModel;
}
