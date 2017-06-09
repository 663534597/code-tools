package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class ParameterMetaModel extends NodeMetaModel {

	ParameterMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.body.Parameter.class, "Parameter", "com.yijia.codegen.models.base.ast.body", false, false);
	}

	public PropertyMetaModel annotationsPropertyMetaModel;

	public PropertyMetaModel isVarArgsPropertyMetaModel;

	public PropertyMetaModel modifiersPropertyMetaModel;

	public PropertyMetaModel namePropertyMetaModel;

	public PropertyMetaModel typePropertyMetaModel;

	public PropertyMetaModel varArgsAnnotationsPropertyMetaModel;
}
