package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class LineCommentMetaModel extends CommentMetaModel {

	LineCommentMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.comments.LineComment.class, "LineComment", "com.yijia.codegen.models.base.ast.comments", false, false);
	}
}
