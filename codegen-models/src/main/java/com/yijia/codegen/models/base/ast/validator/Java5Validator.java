package com.yijia.codegen.models.base.ast.validator;

import java.util.Optional;
import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.NodeList;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithTypeArguments;
import com.yijia.codegen.models.base.ast.type.PrimitiveType;
import com.yijia.codegen.models.base.ast.type.Type;

/**
 * This validator validates according to Java 5 syntax rules.
 */
public class Java5Validator extends Java1_4Validator {
	protected Validator genericsWithoutDiamondOperator = new TreeVisitorValidator((node, reporter) -> {
		if (node instanceof NodeWithTypeArguments) {
			Optional<NodeList<Type>> typeArguments = ((NodeWithTypeArguments<? extends Node>) node).getTypeArguments();
			if (typeArguments.isPresent() && typeArguments.get().isEmpty()) {
				reporter.report(node, "The diamond operator is not supported.");
			}
		}
	});

	protected Validator noPrimitiveGenericArguments = new TreeVisitorValidator((node, reporter) -> {
		if (node instanceof NodeWithTypeArguments) {
			Optional<NodeList<Type>> typeArguments = ((NodeWithTypeArguments<? extends Node>) node).getTypeArguments();
			if (typeArguments.isPresent()) {
				typeArguments.get().forEach(ty -> {
					if (ty instanceof PrimitiveType) {
						reporter.report(node, "Type arguments may not be primitive.");
					}
				});
			}
		}
	});

	public Java5Validator() {
		super();
		replace(noGenerics, genericsWithoutDiamondOperator);
		add(noPrimitiveGenericArguments);

		// TODO validate annotations on classes, fields and methods but nowhere else
		// The following is probably too simple.
		remove(noAnnotations);

		remove(noEnums);
		remove(noVarargs);
		remove(noForEach);
		remove(noStaticImports);
	}
}
