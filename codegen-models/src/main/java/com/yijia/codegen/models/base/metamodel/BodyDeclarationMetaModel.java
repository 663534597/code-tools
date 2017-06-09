package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;
import com.yijia.codegen.models.base.ast.Node;

public class BodyDeclarationMetaModel extends NodeMetaModel {

	BodyDeclarationMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.body.BodyDeclaration.class, "BodyDeclaration", "com.yijia.codegen.models.base.ast.body", true, true);
	}

	protected BodyDeclarationMetaModel(Optional<BaseNodeMetaModel> superNodeMetaModel, Class<? extends Node> type, String name, String packageName, boolean isAbstract,
			boolean hasWildcard) {
		super(superNodeMetaModel, type, name, packageName, isAbstract, hasWildcard);
	}

	public PropertyMetaModel annotationsPropertyMetaModel;
}
