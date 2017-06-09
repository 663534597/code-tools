package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class ExplicitConstructorInvocationStmtMetaModel extends StatementMetaModel {

	ExplicitConstructorInvocationStmtMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.stmt.ExplicitConstructorInvocationStmt.class, "ExplicitConstructorInvocationStmt", "com.yijia.codegen.models.base.ast.stmt",
				false, false);
	}

	public PropertyMetaModel argumentsPropertyMetaModel;

	public PropertyMetaModel expressionPropertyMetaModel;

	public PropertyMetaModel isThisPropertyMetaModel;

	public PropertyMetaModel typeArgumentsPropertyMetaModel;

	public PropertyMetaModel usingDiamondOperatorPropertyMetaModel;
}
