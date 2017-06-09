package com.yijia.codegen.models.base.ast.nodeTypes;

import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.expr.Expression;

public interface NodeWithCondition<N extends Node> {
	Expression getCondition();

	N setCondition(Expression condition);
}
