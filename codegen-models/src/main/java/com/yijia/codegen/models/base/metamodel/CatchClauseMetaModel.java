package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class CatchClauseMetaModel extends NodeMetaModel {

	CatchClauseMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.stmt.CatchClause.class, "CatchClause", "com.yijia.codegen.models.base.ast.stmt", false, false);
	}

	public PropertyMetaModel bodyPropertyMetaModel;

	public PropertyMetaModel parameterPropertyMetaModel;
}
