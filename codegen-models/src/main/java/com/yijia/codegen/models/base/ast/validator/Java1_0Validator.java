package com.yijia.codegen.models.base.ast.validator;

import com.yijia.codegen.models.base.ast.ImportDeclaration;
import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.body.AnnotationDeclaration;
import com.yijia.codegen.models.base.ast.body.ClassOrInterfaceDeclaration;
import com.yijia.codegen.models.base.ast.body.EnumDeclaration;
import com.yijia.codegen.models.base.ast.body.Parameter;
import com.yijia.codegen.models.base.ast.expr.AnnotationExpr;
import com.yijia.codegen.models.base.ast.expr.ClassExpr;
import com.yijia.codegen.models.base.ast.expr.LambdaExpr;
import com.yijia.codegen.models.base.ast.expr.StringLiteralExpr;
import com.yijia.codegen.models.base.ast.modules.ModuleDeclaration;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithTypeArguments;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithTypeParameters;
import com.yijia.codegen.models.base.ast.stmt.AssertStmt;
import com.yijia.codegen.models.base.ast.stmt.ForeachStmt;
import com.yijia.codegen.models.base.ast.stmt.SwitchEntryStmt;
import com.yijia.codegen.models.base.ast.stmt.TryStmt;
import com.yijia.codegen.models.base.ast.type.UnionType;
import com.yijia.codegen.models.base.ast.validator.chunks.CommonValidators;
import com.yijia.codegen.models.base.ast.validator.chunks.ModifierValidator;
import com.yijia.codegen.models.base.ast.validator.chunks.NoBinaryIntegerLiteralsValidator;
import com.yijia.codegen.models.base.ast.validator.chunks.NoUnderscoresInIntegerLiteralsValidator;

/**
 * This validator validates according to Java 1.0 syntax rules.
 */
public class Java1_0Validator extends Validators {
	protected final Validator modifiersWithoutStrictfpAndDefaultAndStaticInterfaceMethodsAndPrivateInterfaceMethods = new ModifierValidator(false, false, false);
	protected final Validator noAssertKeyword = new SimpleValidator<>(AssertStmt.class, n -> true, (n, reporter) -> reporter.report(n, "'assert' keyword is not supported."));
	protected final Validator noInnerClasses = new SimpleValidator<>(ClassOrInterfaceDeclaration.class, n -> !n.isTopLevelType(),
			(n, reporter) -> reporter.report(n, "inner classes or interfaces are not supported."));
	protected final Validator noReflection = new SimpleValidator<>(ClassExpr.class, n -> true, (n, reporter) -> reporter.report(n, "Reflection is not supported."));
	protected final Validator noGenerics = new TreeVisitorValidator((node, reporter) -> {
		if (node instanceof NodeWithTypeArguments) {
			if (((NodeWithTypeArguments<? extends Node>) node).getTypeArguments().isPresent()) {
				reporter.report(node, "Generics are not supported.");
			}
		}
		if (node instanceof NodeWithTypeParameters) {
			if (((NodeWithTypeParameters<? extends Node>) node).getTypeParameters().isNonEmpty()) {
				reporter.report(node, "Generics are not supported.");
			}
		}
	});
	protected final SingleNodeTypeValidator<TryStmt> tryWithoutResources = new SingleNodeTypeValidator<>(TryStmt.class, (n, reporter) -> {
		if (n.getCatchClauses().isEmpty() && !n.getFinallyBlock().isPresent()) {
			reporter.report(n, "Try has no finally and no catch.");
		}
		if (n.getResources().isNonEmpty()) {
			reporter.report(n, "Catch with resource is not supported.");
		}
	});
	protected final Validator noAnnotations = new TreeVisitorValidator((node, reporter) -> {
		if (node instanceof AnnotationExpr || node instanceof AnnotationDeclaration) {
			reporter.report(node, "Annotations are not supported.");
		}
	});
	protected final Validator noEnums = new SimpleValidator<>(EnumDeclaration.class, n -> true, (n, reporter) -> reporter.report(n, "Enumerations are not supported."));
	protected final Validator noVarargs = new SimpleValidator<>(Parameter.class, Parameter::isVarArgs, (n, reporter) -> reporter.report(n, "Varargs are not supported."));
	protected final Validator noForEach = new SimpleValidator<>(ForeachStmt.class, n -> true, (n, reporter) -> reporter.report(n, "For-each loops are not supported."));
	protected final Validator noStaticImports = new SimpleValidator<>(ImportDeclaration.class, ImportDeclaration::isStatic,
			(n, reporter) -> reporter.report(n, "Static imports are not supported."));
	protected final Validator noStringsInSwitch = new SimpleValidator<>(SwitchEntryStmt.class, n -> n.getLabel().map(l -> l instanceof StringLiteralExpr).orElse(false),
			(n, reporter) -> reporter.report(n.getLabel().get(), "Strings in switch statements are not supported."));
	protected final Validator noBinaryIntegerLiterals = new NoBinaryIntegerLiteralsValidator();
	protected final Validator noUnderscoresInIntegerLiterals = new NoUnderscoresInIntegerLiteralsValidator();
	protected final Validator noMultiCatch = new SimpleValidator<>(UnionType.class, n -> true, (n, reporter) -> reporter.report(n, "Multi-catch is not supported."));
	protected final Validator noLambdas = new SimpleValidator<>(LambdaExpr.class, n -> true, (n, reporter) -> reporter.report(n, "Lambdas are not supported."));
	protected final Validator noModules = new SimpleValidator<>(ModuleDeclaration.class, n -> true, (n, reporter) -> reporter.report(n, "Modules are not supported."));

	public Java1_0Validator() {
		super(new CommonValidators());
		add(modifiersWithoutStrictfpAndDefaultAndStaticInterfaceMethodsAndPrivateInterfaceMethods);
		add(noAssertKeyword);
		add(noInnerClasses);
		add(noReflection);
		add(noGenerics);
		add(tryWithoutResources);
		add(noAnnotations);
		add(noEnums);
		add(noVarargs);
		add(noForEach);
		add(noStaticImports);
		add(noStringsInSwitch);
		add(noBinaryIntegerLiterals);
		add(noUnderscoresInIntegerLiterals);
		add(noMultiCatch);
		add(noLambdas);
		add(noModules);
	}
}
