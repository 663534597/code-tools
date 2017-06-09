package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class CompilationUnitMetaModel extends NodeMetaModel {

	CompilationUnitMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.CompilationUnit.class, "CompilationUnit", "com.yijia.codegen.models.base.ast", false, false);
	}

	public PropertyMetaModel importsPropertyMetaModel;

	public PropertyMetaModel modulePropertyMetaModel;

	public PropertyMetaModel packageDeclarationPropertyMetaModel;

	public PropertyMetaModel typesPropertyMetaModel;
}
