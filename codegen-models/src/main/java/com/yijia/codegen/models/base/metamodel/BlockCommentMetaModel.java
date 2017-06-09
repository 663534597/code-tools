package com.yijia.codegen.models.base.metamodel;

import java.util.Optional;

public class BlockCommentMetaModel extends CommentMetaModel {

	BlockCommentMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
		super(superBaseNodeMetaModel, com.yijia.codegen.models.base.ast.comments.BlockComment.class, "BlockComment", "com.yijia.codegen.models.base.ast.comments", false, false);
	}
}
