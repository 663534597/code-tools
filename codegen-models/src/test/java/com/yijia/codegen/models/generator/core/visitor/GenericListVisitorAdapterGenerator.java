package com.yijia.codegen.models.generator.core.visitor;

import static com.yijia.codegen.models.base.utils.CodeGenerationUtils.f;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.yijia.codegen.models.base.ast.CompilationUnit;
import com.yijia.codegen.models.base.ast.body.MethodDeclaration;
import com.yijia.codegen.models.base.ast.stmt.BlockStmt;
import com.yijia.codegen.models.base.metamodel.BaseNodeMetaModel;
import com.yijia.codegen.models.base.metamodel.PropertyMetaModel;
import com.yijia.codegen.models.base.utils.SourceRoot;
import com.yijia.codegen.models.generator.VisitorGenerator;

/**
 * Generates JavaParser's GenericListVisitorAdapter.
 */
public class GenericListVisitorAdapterGenerator extends VisitorGenerator {
	public GenericListVisitorAdapterGenerator(SourceRoot sourceRoot) {
		super(sourceRoot, "com.yijia.codegen.models.base.ast.visitor", "GenericListVisitorAdapter", "List<R>", "A", true);
	}

	@Override
	protected void generateVisitMethodBody(BaseNodeMetaModel node, MethodDeclaration visitMethod, CompilationUnit compilationUnit) {
		BlockStmt body = visitMethod.getBody().get();
		body.getStatements().clear();
		body.addStatement("List<R> result = new ArrayList<>();");
		body.addStatement("List<R> tmp;");

		final String resultCheck = "if (tmp != null) result.addAll(tmp);";

		for (PropertyMetaModel field : node.getAllPropertyMetaModels()) {
			final String getter = field.getGetterMethodName() + "()";
			if (field.getNodeReference().isPresent()) {
				if (field.isOptional()) {
					body.addStatement(f("if (n.%s.isPresent()) {" + "   tmp = n.%s.get().accept(this, arg);" + "   %s" + "}", getter, getter, resultCheck));
				} else {
					body.addStatement(f("{ tmp = n.%s.accept(this, arg); %s }", getter, resultCheck));
				}
			}
		}
		body.addStatement("return result;");
		Arrays.stream(new Class<?>[] { List.class, ArrayList.class }).filter(c -> compilationUnit.getImports().stream().noneMatch(i -> c.getName().equals(i.getName().asString())))
				.forEach(compilationUnit::addImport);
	}
}
