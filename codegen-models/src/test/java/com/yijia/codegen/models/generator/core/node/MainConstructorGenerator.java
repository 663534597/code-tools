package com.yijia.codegen.models.generator.core.node;

import static com.yijia.codegen.models.base.JavaParser.parseExplicitConstructorInvocationStmt;
import static com.yijia.codegen.models.base.utils.CodeGenerationUtils.f;
import com.yijia.codegen.models.base.TokenRange;
import com.yijia.codegen.models.base.ast.CompilationUnit;
import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.body.ClassOrInterfaceDeclaration;
import com.yijia.codegen.models.base.ast.body.ConstructorDeclaration;
import com.yijia.codegen.models.base.ast.stmt.BlockStmt;
import com.yijia.codegen.models.base.metamodel.BaseNodeMetaModel;
import com.yijia.codegen.models.base.metamodel.PropertyMetaModel;
import com.yijia.codegen.models.base.utils.SeparatedItemStringBuilder;
import com.yijia.codegen.models.base.utils.SourceRoot;
import com.yijia.codegen.models.generator.NodeGenerator;

public class MainConstructorGenerator extends NodeGenerator {
	public MainConstructorGenerator(SourceRoot sourceRoot) {
		super(sourceRoot);
	}

	@Override
	protected void generateNode(BaseNodeMetaModel nodeMetaModel, CompilationUnit nodeCu, ClassOrInterfaceDeclaration nodeCoid) {
		if (nodeMetaModel.is(Node.class)) {
			return;
		}
		ConstructorDeclaration constructor = new ConstructorDeclaration().setPublic(true).setName(nodeCoid.getNameAsString()).addParameter(TokenRange.class, "tokenRange")
				.setJavadocComment("This constructor is used by the parser and is considered private.");

		BlockStmt body = constructor.getBody();

		SeparatedItemStringBuilder superCall = new SeparatedItemStringBuilder("super(", ", ", ");");
		superCall.append("tokenRange");
		for (PropertyMetaModel parameter : nodeMetaModel.getConstructorParameters()) {
			constructor.addParameter(parameter.getTypeNameForSetter(), parameter.getName());
			if (nodeMetaModel.getDeclaredPropertyMetaModels().contains(parameter)) {
				body.addStatement(f("%s(%s);", parameter.getSetterMethodName(), parameter.getName()));
			} else {
				superCall.append(parameter.getName());
			}
		}

		body.getStatements().add(0, parseExplicitConstructorInvocationStmt(superCall.toString()));

		body.addStatement("customInitialization();");

		replaceWhenSameSignature(nodeCoid, constructor);
		nodeCu.addImport(TokenRange.class);
		annotateGenerated(constructor);
	}
}
