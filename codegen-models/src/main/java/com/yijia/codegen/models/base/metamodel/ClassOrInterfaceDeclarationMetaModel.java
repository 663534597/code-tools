package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class ClassOrInterfaceDeclarationMetaModel extends TypeDeclarationMetaModel {

	ClassOrInterfaceDeclarationMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.body.ClassOrInterfaceDeclaration.class, "ClassOrInterfaceDeclaration", "com.yijia.codegen.models.base.ast.body", false,
				false);
	}

	public PropertyMetaModel extendedTypesPropertyMetaModel;

	public PropertyMetaModel implementedTypesPropertyMetaModel;

	public PropertyMetaModel isInterfacePropertyMetaModel;

	public PropertyMetaModel typeParametersPropertyMetaModel;
}
