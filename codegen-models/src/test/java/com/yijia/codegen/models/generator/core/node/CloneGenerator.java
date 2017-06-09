package com.yijia.codegen.models.generator.core.node;

import static com.yijia.codegen.models.base.JavaParser.parseBodyDeclaration;
import static com.yijia.codegen.models.base.utils.CodeGenerationUtils.f;
import com.yijia.codegen.models.base.ast.CompilationUnit;
import com.yijia.codegen.models.base.ast.body.ClassOrInterfaceDeclaration;
import com.yijia.codegen.models.base.ast.body.MethodDeclaration;
import com.yijia.codegen.models.base.ast.visitor.CloneVisitor;
import com.yijia.codegen.models.base.metamodel.BaseNodeMetaModel;
import com.yijia.codegen.models.base.utils.SourceRoot;
import com.yijia.codegen.models.generator.NodeGenerator;

public class CloneGenerator extends NodeGenerator {
	public CloneGenerator(SourceRoot sourceRoot) {
		super(sourceRoot);
	}

	@Override
	protected void generateNode(BaseNodeMetaModel nodeMetaModel, CompilationUnit nodeCu, ClassOrInterfaceDeclaration nodeCoid) {
		nodeCu.addImport(CloneVisitor.class);
		MethodDeclaration cloneMethod = (MethodDeclaration) parseBodyDeclaration(
				f("@Override public %s clone() { return (%s) accept(new CloneVisitor(), null); }", nodeMetaModel.getTypeNameGenerified(), nodeMetaModel.getTypeNameGenerified()));
		addOrReplaceWhenSameSignature(nodeCoid, cloneMethod);
		annotateGenerated(cloneMethod);
	}
}
