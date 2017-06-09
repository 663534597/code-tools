package com.yijia.codegen.models.generator.core.node;

import static com.yijia.codegen.models.base.JavaParser.parseBodyDeclaration;
import static com.yijia.codegen.models.base.utils.CodeGenerationUtils.f;
import com.yijia.codegen.models.base.ast.CompilationUnit;
import com.yijia.codegen.models.base.ast.body.ClassOrInterfaceDeclaration;
import com.yijia.codegen.models.base.ast.body.MethodDeclaration;
import com.yijia.codegen.models.base.metamodel.BaseNodeMetaModel;
import com.yijia.codegen.models.base.metamodel.JavaParserMetaModel;
import com.yijia.codegen.models.base.utils.SourceRoot;
import com.yijia.codegen.models.generator.NodeGenerator;

public class GetMetaModelGenerator extends NodeGenerator {
	public GetMetaModelGenerator(SourceRoot sourceRoot) {
		super(sourceRoot);
	}

	@Override
	protected void generateNode(BaseNodeMetaModel nodeMetaModel, CompilationUnit nodeCu, ClassOrInterfaceDeclaration nodeCoid) {
		final MethodDeclaration getMetaModelMethod = (MethodDeclaration) parseBodyDeclaration(f("%s public %s getMetaModel() { return JavaParserMetaModel.%s; }",
				nodeMetaModel.isRootNode() ? "" : "@Override", nodeMetaModel.getClass().getSimpleName(), nodeMetaModel.getMetaModelFieldName()));

		addOrReplaceWhenSameSignature(nodeCoid, getMetaModelMethod);
		nodeCu.addImport(nodeMetaModel.getClass().getName());
		nodeCu.addImport(JavaParserMetaModel.class);
		annotateGenerated(getMetaModelMethod);
	}
}
