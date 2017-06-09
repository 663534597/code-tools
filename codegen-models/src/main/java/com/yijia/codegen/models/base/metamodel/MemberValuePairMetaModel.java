package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class MemberValuePairMetaModel extends NodeMetaModel {

	MemberValuePairMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.expr.MemberValuePair.class, "MemberValuePair", "com.yijia.codegen.models.base.ast.expr", false, false);
	}

	public PropertyMetaModel namePropertyMetaModel;

	public PropertyMetaModel valuePropertyMetaModel;
}
