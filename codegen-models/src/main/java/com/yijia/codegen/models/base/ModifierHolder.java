package com.yijia.codegen.models.base;

import com.yijia.codegen.models.base.ast.Modifier;
import com.yijia.codegen.models.base.ast.NodeList;
import com.yijia.codegen.models.base.ast.expr.AnnotationExpr;
import java.util.EnumSet;
import static com.yijia.codegen.models.base.utils.Utils.assertNotNull;

/**
 * Helper class for {@link GeneratedJavaParser}
 */
class ModifierHolder {
	final EnumSet<Modifier> modifiers;
	final NodeList<AnnotationExpr> annotations;
	final JavaToken begin;

	public ModifierHolder(JavaToken begin, EnumSet<Modifier> modifiers, NodeList<AnnotationExpr> annotations) {
		this.begin = begin;
		this.modifiers = assertNotNull(modifiers);
		this.annotations = annotations;
	}
}
