package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class JavadocCommentMetaModel extends CommentMetaModel {

	JavadocCommentMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.comments.JavadocComment.class, "JavadocComment", "com.yijia.codegen.models.base.ast.comments", false, false);
	}
}
