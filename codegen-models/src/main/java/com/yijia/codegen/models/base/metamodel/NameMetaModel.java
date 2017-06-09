package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class NameMetaModel extends NodeMetaModel {

	NameMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.expr.Name.class, "Name", "com.yijia.codegen.models.base.ast.expr", false, false);
	}

	public PropertyMetaModel annotationsPropertyMetaModel;

	public PropertyMetaModel identifierPropertyMetaModel;

	public PropertyMetaModel qualifierPropertyMetaModel;
}
