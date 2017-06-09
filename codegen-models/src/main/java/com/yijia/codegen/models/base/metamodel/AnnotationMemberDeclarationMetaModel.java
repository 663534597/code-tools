package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class AnnotationMemberDeclarationMetaModel extends BodyDeclarationMetaModel {

	AnnotationMemberDeclarationMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.body.AnnotationMemberDeclaration.class, "AnnotationMemberDeclaration", "com.yijia.codegen.models.base.ast.body", false,
				false);
	}

	public PropertyMetaModel defaultValuePropertyMetaModel;

	public PropertyMetaModel modifiersPropertyMetaModel;

	public PropertyMetaModel namePropertyMetaModel;

	public PropertyMetaModel typePropertyMetaModel;
}
