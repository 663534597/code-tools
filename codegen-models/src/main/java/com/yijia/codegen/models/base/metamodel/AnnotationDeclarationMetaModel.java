package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class AnnotationDeclarationMetaModel extends TypeDeclarationMetaModel {

	AnnotationDeclarationMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.body.AnnotationDeclaration.class, "AnnotationDeclaration", "com.yijia.codegen.models.base.ast.body", false, false);
	}
}
