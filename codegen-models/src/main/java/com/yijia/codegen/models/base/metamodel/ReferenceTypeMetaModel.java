package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;
import com.yijia.codegen.models.base.ast.Node;

public class ReferenceTypeMetaModel extends TypeMetaModel {

	ReferenceTypeMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.type.ReferenceType.class, "ReferenceType", "com.yijia.codegen.models.base.ast.type", true, false);
	}

	protected ReferenceTypeMetaModel(Optional<BaseNodeMetaModel> superNodeMetaModel, Class<? extends Node> type, String name, String packageName, boolean isAbstract,
			boolean hasWildcard) {
		super(superNodeMetaModel, type, name, packageName, isAbstract, hasWildcard);
	}
}
