package com.yijia.codegen.models.generator;

import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.body.MethodDeclaration;
import com.yijia.codegen.models.base.ast.expr.Expression;
import com.yijia.codegen.models.base.ast.expr.StringLiteralExpr;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithAnnotations;
import com.yijia.codegen.models.base.utils.SourceRoot;
import javax.annotation.Generated;
import static com.yijia.codegen.models.base.ast.NodeList.toNodeList;

/**
 * A general pattern that the generators in this module will follow.
 */
public abstract class Generator {
	protected final SourceRoot sourceRoot;

	protected Generator(SourceRoot sourceRoot) {
		this.sourceRoot = sourceRoot;
	}

	public abstract void generate() throws Exception;

	protected <T extends Node & NodeWithAnnotations<T>> void annotateGenerated(T node) {
		annotate(node, Generated.class, new StringLiteralExpr(getClass().getName()));
	}

	protected <T extends Node & NodeWithAnnotations<T>> void annotateSuppressWarnings(T node) {
		annotate(node, SuppressWarnings.class, new StringLiteralExpr("unchecked"));
	}

	private <T extends Node & NodeWithAnnotations<T>> void annotate(T node, Class<?> annotation, Expression content) {
		node.setAnnotations(node.getAnnotations().stream().filter(a -> !a.getNameAsString().equals(annotation.getSimpleName())).collect(toNodeList()));

		if (content != null) {
			node.addSingleMemberAnnotation(annotation.getSimpleName(), content);
		} else {
			node.addMarkerAnnotation(annotation.getSimpleName());
		}
		node.tryAddImportToParentCompilationUnit(annotation);
	}

	protected void annotateOverridden(MethodDeclaration method) {
		annotate(method, Override.class, null);
	}

}
