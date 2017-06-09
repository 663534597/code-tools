package com.yijia.codegen.models.generator.core.visitor;

import static com.yijia.codegen.models.base.JavaParser.parseStatement;
import java.util.List;
import com.yijia.codegen.models.base.ast.CompilationUnit;
import com.yijia.codegen.models.base.ast.body.MethodDeclaration;
import com.yijia.codegen.models.base.ast.stmt.BlockStmt;
import com.yijia.codegen.models.base.metamodel.BaseNodeMetaModel;
import com.yijia.codegen.models.base.metamodel.PropertyMetaModel;
import com.yijia.codegen.models.base.utils.SeparatedItemStringBuilder;
import com.yijia.codegen.models.base.utils.SourceRoot;
import com.yijia.codegen.models.generator.VisitorGenerator;

/**
 * Generates JavaParser's HashCodeVisitor.
 */
public class HashCodeVisitorGenerator extends VisitorGenerator {
	public HashCodeVisitorGenerator(SourceRoot sourceRoot) {
		super(sourceRoot, "com.yijia.codegen.models.base.ast.visitor", "HashCodeVisitor", "Integer", "Void", true);
	}

	@Override
	protected void generateVisitMethodBody(BaseNodeMetaModel node, MethodDeclaration visitMethod, CompilationUnit compilationUnit) {
		final BlockStmt body = visitMethod.getBody().get();
		body.getStatements().clear();

		final SeparatedItemStringBuilder builder = new SeparatedItemStringBuilder("return ", "* 31 +", ";");
		final List<PropertyMetaModel> propertyMetaModels = node.getAllPropertyMetaModels();
		if (propertyMetaModels.isEmpty()) {
			builder.append("0");
		} else {
			for (PropertyMetaModel field : propertyMetaModels) {
				final String getter = field.getGetterMethodName() + "()";
				// Is this field another AST node? Visit it.
				if (field.getNodeReference().isPresent()) {
					if (field.isOptional()) {
						builder.append("(n.%s.isPresent()? n.%s.get().accept(this, arg):0)", getter, getter);
					} else {
						builder.append("(n.%s.accept(this, arg))", getter);
					}
				} else {
					Class<?> type = field.getType();
					if (type.equals(boolean.class)) {
						builder.append("(n.%s?1:0)", getter);
					} else if (type.equals(int.class)) {
						builder.append("n.%s", getter);
					} else {
						builder.append("(n.%s.hashCode())", getter);
					}
				}
			}
		}
		body.addStatement(parseStatement(builder.toString()));
	}
}
