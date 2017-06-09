package com.yijia.codegen.models.base.ast.modules;

import javax.annotation.Generated;
import com.yijia.codegen.models.base.TokenRange;
import com.yijia.codegen.models.base.ast.AllFieldsConstructor;
import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.visitor.CloneVisitor;
import com.yijia.codegen.models.base.metamodel.JavaParserMetaModel;
import com.yijia.codegen.models.base.metamodel.ModuleStmtMetaModel;

public abstract class ModuleStmt extends Node {

	@AllFieldsConstructor
	public ModuleStmt() {
		this(null);
	}

	/** This constructor is used by the parser and is considered private. */
	@Generated("com.yijia.codegen.models.base.generator.core.node.MainConstructorGenerator")
	public ModuleStmt(TokenRange tokenRange) {
		super(tokenRange);
		customInitialization();
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.RemoveMethodGenerator")
	public boolean remove(Node node) {
		if (node == null)
			return false;
		return super.remove(node);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.CloneGenerator")
	public ModuleStmt clone() {
		return (ModuleStmt) accept(new CloneVisitor(), null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetMetaModelGenerator")
	public ModuleStmtMetaModel getMetaModel() {
		return JavaParserMetaModel.moduleStmtMetaModel;
	}
}
