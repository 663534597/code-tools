package com.yijia.codegen.models.base.printer;

import static com.yijia.codegen.models.base.GeneratedJavaParserConstants.GT;
import static com.yijia.codegen.models.base.GeneratedJavaParserConstants.LBRACE;
import static com.yijia.codegen.models.base.GeneratedJavaParserConstants.LPAREN;
import static com.yijia.codegen.models.base.GeneratedJavaParserConstants.LT;
import static com.yijia.codegen.models.base.GeneratedJavaParserConstants.RBRACE;
import static com.yijia.codegen.models.base.GeneratedJavaParserConstants.RPAREN;
import static com.yijia.codegen.models.base.ast.observer.ObservableProperty.ANNOTATIONS;
import static com.yijia.codegen.models.base.ast.observer.ObservableProperty.ASTERISK;
import static com.yijia.codegen.models.base.ast.observer.ObservableProperty.BODY;
import static com.yijia.codegen.models.base.ast.observer.ObservableProperty.CLASS_BODY;
import static com.yijia.codegen.models.base.ast.observer.ObservableProperty.CLASS_EXPR;
import static com.yijia.codegen.models.base.ast.observer.ObservableProperty.DEFAULT_VALUE;
import static com.yijia.codegen.models.base.ast.observer.ObservableProperty.EXPRESSION;
import static com.yijia.codegen.models.base.ast.observer.ObservableProperty.EXTENDED_TYPE;
import static com.yijia.codegen.models.base.ast.observer.ObservableProperty.NAME;
import static com.yijia.codegen.models.base.ast.observer.ObservableProperty.SCOPE;
import static com.yijia.codegen.models.base.ast.observer.ObservableProperty.SUPER_TYPE;
import static com.yijia.codegen.models.base.ast.observer.ObservableProperty.TYPES;
import static com.yijia.codegen.models.base.ast.observer.ObservableProperty.TYPE_ARGUMENTS;
import static com.yijia.codegen.models.base.ast.observer.ObservableProperty.TYPE_PARAMETERS;
import static com.yijia.codegen.models.base.ast.observer.ObservableProperty.VALUE;
import static com.yijia.codegen.models.base.printer.concretesyntaxmodel.CsmConditional.Condition.FLAG;
import static com.yijia.codegen.models.base.printer.concretesyntaxmodel.CsmConditional.Condition.IS_EMPTY;
import static com.yijia.codegen.models.base.printer.concretesyntaxmodel.CsmConditional.Condition.IS_NOT_EMPTY;
import static com.yijia.codegen.models.base.printer.concretesyntaxmodel.CsmConditional.Condition.IS_PRESENT;
import static com.yijia.codegen.models.base.printer.concretesyntaxmodel.CsmElement.attribute;
import static com.yijia.codegen.models.base.printer.concretesyntaxmodel.CsmElement.block;
import static com.yijia.codegen.models.base.printer.concretesyntaxmodel.CsmElement.charToken;
import static com.yijia.codegen.models.base.printer.concretesyntaxmodel.CsmElement.child;
import static com.yijia.codegen.models.base.printer.concretesyntaxmodel.CsmElement.comma;
import static com.yijia.codegen.models.base.printer.concretesyntaxmodel.CsmElement.comment;
import static com.yijia.codegen.models.base.printer.concretesyntaxmodel.CsmElement.conditional;
import static com.yijia.codegen.models.base.printer.concretesyntaxmodel.CsmElement.indent;
import static com.yijia.codegen.models.base.printer.concretesyntaxmodel.CsmElement.list;
import static com.yijia.codegen.models.base.printer.concretesyntaxmodel.CsmElement.newline;
import static com.yijia.codegen.models.base.printer.concretesyntaxmodel.CsmElement.none;
import static com.yijia.codegen.models.base.printer.concretesyntaxmodel.CsmElement.orphanCommentsBeforeThis;
import static com.yijia.codegen.models.base.printer.concretesyntaxmodel.CsmElement.orphanCommentsEnding;
import static com.yijia.codegen.models.base.printer.concretesyntaxmodel.CsmElement.semicolon;
import static com.yijia.codegen.models.base.printer.concretesyntaxmodel.CsmElement.sequence;
import static com.yijia.codegen.models.base.printer.concretesyntaxmodel.CsmElement.space;
import static com.yijia.codegen.models.base.printer.concretesyntaxmodel.CsmElement.string;
import static com.yijia.codegen.models.base.printer.concretesyntaxmodel.CsmElement.stringToken;
import static com.yijia.codegen.models.base.printer.concretesyntaxmodel.CsmElement.token;
import static com.yijia.codegen.models.base.printer.concretesyntaxmodel.CsmElement.unindent;
import static com.yijia.codegen.models.base.utils.Utils.EOL;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.yijia.codegen.models.base.GeneratedJavaParserConstants;
import com.yijia.codegen.models.base.ast.ArrayCreationLevel;
import com.yijia.codegen.models.base.ast.CompilationUnit;
import com.yijia.codegen.models.base.ast.ImportDeclaration;
import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.PackageDeclaration;
import com.yijia.codegen.models.base.ast.body.AnnotationDeclaration;
import com.yijia.codegen.models.base.ast.body.AnnotationMemberDeclaration;
import com.yijia.codegen.models.base.ast.body.ClassOrInterfaceDeclaration;
import com.yijia.codegen.models.base.ast.body.ConstructorDeclaration;
import com.yijia.codegen.models.base.ast.body.EnumConstantDeclaration;
import com.yijia.codegen.models.base.ast.body.EnumDeclaration;
import com.yijia.codegen.models.base.ast.body.FieldDeclaration;
import com.yijia.codegen.models.base.ast.body.InitializerDeclaration;
import com.yijia.codegen.models.base.ast.body.MethodDeclaration;
import com.yijia.codegen.models.base.ast.body.Parameter;
import com.yijia.codegen.models.base.ast.body.VariableDeclarator;
import com.yijia.codegen.models.base.ast.comments.Comment;
import com.yijia.codegen.models.base.ast.comments.JavadocComment;
import com.yijia.codegen.models.base.ast.expr.ArrayAccessExpr;
import com.yijia.codegen.models.base.ast.expr.ArrayCreationExpr;
import com.yijia.codegen.models.base.ast.expr.ArrayInitializerExpr;
import com.yijia.codegen.models.base.ast.expr.AssignExpr;
import com.yijia.codegen.models.base.ast.expr.BinaryExpr;
import com.yijia.codegen.models.base.ast.expr.BooleanLiteralExpr;
import com.yijia.codegen.models.base.ast.expr.CastExpr;
import com.yijia.codegen.models.base.ast.expr.CharLiteralExpr;
import com.yijia.codegen.models.base.ast.expr.ClassExpr;
import com.yijia.codegen.models.base.ast.expr.ConditionalExpr;
import com.yijia.codegen.models.base.ast.expr.DoubleLiteralExpr;
import com.yijia.codegen.models.base.ast.expr.EnclosedExpr;
import com.yijia.codegen.models.base.ast.expr.FieldAccessExpr;
import com.yijia.codegen.models.base.ast.expr.InstanceOfExpr;
import com.yijia.codegen.models.base.ast.expr.IntegerLiteralExpr;
import com.yijia.codegen.models.base.ast.expr.LambdaExpr;
import com.yijia.codegen.models.base.ast.expr.LongLiteralExpr;
import com.yijia.codegen.models.base.ast.expr.MarkerAnnotationExpr;
import com.yijia.codegen.models.base.ast.expr.MemberValuePair;
import com.yijia.codegen.models.base.ast.expr.MethodCallExpr;
import com.yijia.codegen.models.base.ast.expr.MethodReferenceExpr;
import com.yijia.codegen.models.base.ast.expr.Name;
import com.yijia.codegen.models.base.ast.expr.NameExpr;
import com.yijia.codegen.models.base.ast.expr.NormalAnnotationExpr;
import com.yijia.codegen.models.base.ast.expr.NullLiteralExpr;
import com.yijia.codegen.models.base.ast.expr.ObjectCreationExpr;
import com.yijia.codegen.models.base.ast.expr.SimpleName;
import com.yijia.codegen.models.base.ast.expr.SingleMemberAnnotationExpr;
import com.yijia.codegen.models.base.ast.expr.StringLiteralExpr;
import com.yijia.codegen.models.base.ast.expr.SuperExpr;
import com.yijia.codegen.models.base.ast.expr.ThisExpr;
import com.yijia.codegen.models.base.ast.expr.TypeExpr;
import com.yijia.codegen.models.base.ast.expr.UnaryExpr;
import com.yijia.codegen.models.base.ast.expr.VariableDeclarationExpr;
import com.yijia.codegen.models.base.ast.modules.ModuleDeclaration;
import com.yijia.codegen.models.base.ast.modules.ModuleExportsStmt;
import com.yijia.codegen.models.base.ast.modules.ModuleOpensStmt;
import com.yijia.codegen.models.base.ast.modules.ModuleProvidesStmt;
import com.yijia.codegen.models.base.ast.modules.ModuleRequiresStmt;
import com.yijia.codegen.models.base.ast.modules.ModuleUsesStmt;
import com.yijia.codegen.models.base.ast.observer.ObservableProperty;
import com.yijia.codegen.models.base.ast.stmt.AssertStmt;
import com.yijia.codegen.models.base.ast.stmt.BlockStmt;
import com.yijia.codegen.models.base.ast.stmt.BreakStmt;
import com.yijia.codegen.models.base.ast.stmt.CatchClause;
import com.yijia.codegen.models.base.ast.stmt.ContinueStmt;
import com.yijia.codegen.models.base.ast.stmt.DoStmt;
import com.yijia.codegen.models.base.ast.stmt.EmptyStmt;
import com.yijia.codegen.models.base.ast.stmt.ExplicitConstructorInvocationStmt;
import com.yijia.codegen.models.base.ast.stmt.ExpressionStmt;
import com.yijia.codegen.models.base.ast.stmt.ForStmt;
import com.yijia.codegen.models.base.ast.stmt.ForeachStmt;
import com.yijia.codegen.models.base.ast.stmt.IfStmt;
import com.yijia.codegen.models.base.ast.stmt.LabeledStmt;
import com.yijia.codegen.models.base.ast.stmt.LocalClassDeclarationStmt;
import com.yijia.codegen.models.base.ast.stmt.ReturnStmt;
import com.yijia.codegen.models.base.ast.stmt.SwitchEntryStmt;
import com.yijia.codegen.models.base.ast.stmt.SwitchStmt;
import com.yijia.codegen.models.base.ast.stmt.SynchronizedStmt;
import com.yijia.codegen.models.base.ast.stmt.ThrowStmt;
import com.yijia.codegen.models.base.ast.stmt.TryStmt;
import com.yijia.codegen.models.base.ast.stmt.WhileStmt;
import com.yijia.codegen.models.base.ast.type.ArrayType;
import com.yijia.codegen.models.base.ast.type.ClassOrInterfaceType;
import com.yijia.codegen.models.base.ast.type.IntersectionType;
import com.yijia.codegen.models.base.ast.type.PrimitiveType;
import com.yijia.codegen.models.base.ast.type.TypeParameter;
import com.yijia.codegen.models.base.ast.type.UnionType;
import com.yijia.codegen.models.base.ast.type.UnknownType;
import com.yijia.codegen.models.base.ast.type.VoidType;
import com.yijia.codegen.models.base.ast.type.WildcardType;
import com.yijia.codegen.models.base.metamodel.JavaParserMetaModel;
import com.yijia.codegen.models.base.printer.concretesyntaxmodel.CsmConditional;
import com.yijia.codegen.models.base.printer.concretesyntaxmodel.CsmElement;
import com.yijia.codegen.models.base.printer.concretesyntaxmodel.CsmMix;
import com.yijia.codegen.models.base.printer.concretesyntaxmodel.CsmToken;

/**
 * The Concrete Syntax Model for a single node type. It knows the syntax used to represent a certain element in Java code.
 */
public class ConcreteSyntaxModel {

	private static Map<Class, CsmElement> concreteSyntaxModelByClass = new HashMap<>();
	private static Optional<String> initializationError;

	private static CsmElement modifiers() {
		return list(ObservableProperty.MODIFIERS, space(), none(), space());
	}

	/**
	 * Build a mix collecting all the elements specified.
	 */
	private static CsmElement mix(CsmElement... elements) {
		return new CsmMix(Arrays.asList(elements));
	}

	private static CsmElement memberAnnotations() {
		return list(ObservableProperty.ANNOTATIONS, none(), none(), newline());
	}

	private static CsmElement annotations() {
		return list(ObservableProperty.ANNOTATIONS, space(), none(), newline());
	}

	private static CsmElement typeParameters() {
		return list(ObservableProperty.TYPE_PARAMETERS, CsmElement.sequence(CsmElement.comma(), CsmElement.space()), CsmElement.token(GeneratedJavaParserConstants.LT),
				CsmElement.sequence(CsmElement.token(GeneratedJavaParserConstants.GT), CsmElement.space()));
	}

	private static CsmElement typeArguments() {
		return list(ObservableProperty.TYPE_ARGUMENTS, CsmElement.sequence(CsmElement.comma(), CsmElement.space()), CsmElement.token(GeneratedJavaParserConstants.LT),
				CsmElement.sequence(CsmElement.token(GeneratedJavaParserConstants.GT)));
	}

	static {

		///
		/// Body
		///

		concreteSyntaxModelByClass.put(AnnotationDeclaration.class,
				CsmElement.sequence(CsmElement.comment(), memberAnnotations(), modifiers(), CsmElement.token(GeneratedJavaParserConstants.AT),
						CsmElement.token(GeneratedJavaParserConstants.INTERFACE), CsmElement.space(), CsmElement.child(ObservableProperty.NAME), CsmElement.space(),
						CsmElement.token(LBRACE), CsmElement.newline(), CsmElement.indent(),
						CsmElement.list(ObservableProperty.MEMBERS, CsmElement.newline(), CsmElement.none(), CsmElement.none(), CsmElement.newline()), CsmElement.unindent(),
						CsmElement.token(RBRACE)));

		concreteSyntaxModelByClass.put(AnnotationMemberDeclaration.class, CsmElement.sequence(CsmElement.comment(), memberAnnotations(), modifiers(),
				CsmElement.child(ObservableProperty.TYPE), CsmElement.space(), CsmElement.child(ObservableProperty.NAME), CsmElement.token(LPAREN), CsmElement.token(RPAREN),
				CsmElement.conditional(ObservableProperty.DEFAULT_VALUE, IS_PRESENT,
						CsmElement.sequence(CsmElement.space(), CsmElement.token(GeneratedJavaParserConstants._DEFAULT), CsmElement.space(), CsmElement.child(DEFAULT_VALUE))),
				CsmElement.semicolon()));

		concreteSyntaxModelByClass.put(ClassOrInterfaceDeclaration.class, sequence(comment(), list(ObservableProperty.ANNOTATIONS, newline(), none(), newline()), modifiers(),
				conditional(ObservableProperty.INTERFACE, FLAG, token(GeneratedJavaParserConstants.INTERFACE), token(GeneratedJavaParserConstants.CLASS)), space(),
				child(ObservableProperty.NAME), list(TYPE_PARAMETERS, sequence(comma(), space()), string(GeneratedJavaParserConstants.LT), string(GeneratedJavaParserConstants.GT)),
				list(ObservableProperty.EXTENDED_TYPES, sequence(string(GeneratedJavaParserConstants.COMMA), space()),
						sequence(space(), token(GeneratedJavaParserConstants.EXTENDS), space()), none()),
				list(ObservableProperty.IMPLEMENTED_TYPES, sequence(string(GeneratedJavaParserConstants.COMMA), space()),
						sequence(space(), token(GeneratedJavaParserConstants.IMPLEMENTS), space()), none()),
				space(), block(sequence(newline(), list(ObservableProperty.MEMBERS, sequence(newline(), newline()), CsmElement.newline(), newline())))));

		concreteSyntaxModelByClass.put(ConstructorDeclaration.class,
				sequence(comment(), memberAnnotations(), modifiers(), typeParameters(), child(ObservableProperty.NAME), token(GeneratedJavaParserConstants.LPAREN),
						list(ObservableProperty.PARAMETERS, sequence(comma(), space()), none(), none()), token(GeneratedJavaParserConstants.RPAREN),
						list(ObservableProperty.THROWN_EXCEPTIONS, sequence(comma(), space()), sequence(space(), token(GeneratedJavaParserConstants.THROWS), space()), none()),
						space(), child(ObservableProperty.BODY)));

		concreteSyntaxModelByClass.put(EnumConstantDeclaration.class,
				sequence(comment(), memberAnnotations(), child(ObservableProperty.NAME),
						list(ObservableProperty.ARGUMENTS, sequence(comma(), space()), token(GeneratedJavaParserConstants.LPAREN), token(GeneratedJavaParserConstants.RPAREN)),
						conditional(CLASS_BODY, IS_NOT_EMPTY,
								sequence(space(), token(GeneratedJavaParserConstants.LBRACE), CsmElement.newline(), CsmElement.indent(), CsmElement.newline(),
										list(ObservableProperty.CLASS_BODY, newline(), newline(), none(), CsmElement.newline()), unindent(), token(RBRACE),
										CsmElement.newline()))));

		concreteSyntaxModelByClass.put(EnumDeclaration.class,
				sequence(comment(), annotations(), modifiers(), token(GeneratedJavaParserConstants.ENUM), space(), child(ObservableProperty.NAME),
						list(ObservableProperty.IMPLEMENTED_TYPES, sequence(comma(), space()), sequence(space(), token(GeneratedJavaParserConstants.IMPLEMENTS), space()), none()),
						space(), token(GeneratedJavaParserConstants.LBRACE), CsmElement.newline(), CsmElement.indent(), CsmElement.newline(),
						list(ObservableProperty.ENTRIES, sequence(comma(), space()), CsmElement.none(), none()),
						conditional(ObservableProperty.MEMBERS, IS_EMPTY, conditional(ObservableProperty.ENTRIES, IS_NOT_EMPTY, newline()), sequence(CsmElement.semicolon(),
								newline(), CsmElement.newline(), list(ObservableProperty.MEMBERS, newline(), newline(), none(), CsmElement.newline()))),
						unindent(), token(RBRACE)));

		concreteSyntaxModelByClass.put(FieldDeclaration.class,
				sequence(orphanCommentsBeforeThis(), comment(), annotations(), modifiers(),
						conditional(ObservableProperty.VARIABLES, IS_NOT_EMPTY, child(ObservableProperty.MAXIMUM_COMMON_TYPE)), space(),
						list(ObservableProperty.VARIABLES, sequence(comma(), space())), semicolon()));

		concreteSyntaxModelByClass.put(InitializerDeclaration.class,
				sequence(comment(), conditional(ObservableProperty.STATIC, FLAG, sequence(token(GeneratedJavaParserConstants.STATIC), space())), child(ObservableProperty.BODY)));

		concreteSyntaxModelByClass.put(MethodDeclaration.class,
				sequence(orphanCommentsBeforeThis(), comment(), mix(memberAnnotations(), modifiers()), typeParameters(), child(ObservableProperty.TYPE), space(),
						child(ObservableProperty.NAME), token(GeneratedJavaParserConstants.LPAREN), list(ObservableProperty.PARAMETERS, sequence(comma(), space()), none(), none()),
						token(GeneratedJavaParserConstants.RPAREN),
						list(ObservableProperty.THROWN_EXCEPTIONS, sequence(comma(), space()), sequence(space(), token(GeneratedJavaParserConstants.THROWS), space()), none()),
						conditional(ObservableProperty.BODY, IS_PRESENT, sequence(space(), child(ObservableProperty.BODY)), semicolon())));

		concreteSyntaxModelByClass.put(Parameter.class, sequence(comment(), list(ObservableProperty.ANNOTATIONS, CsmElement.space(), CsmElement.none(), CsmElement.space()),
				modifiers(), child(ObservableProperty.TYPE),
				conditional(ObservableProperty.VAR_ARGS, FLAG, CsmElement.sequence(
						list(ObservableProperty.VAR_ARGS_ANNOTATIONS, CsmElement.space(), CsmElement.none(), CsmElement.none()), token(GeneratedJavaParserConstants.ELLIPSIS))),
				space(), child(ObservableProperty.NAME)));

		concreteSyntaxModelByClass.put(VariableDeclarator.class,
				sequence(comment(), child(ObservableProperty.NAME),
						// FIXME: we should introduce a derived property
						// list(ObservableProperty.EXTRA_ARRAY_LEVELS),
						conditional(ObservableProperty.INITIALIZER, IS_PRESENT,
								sequence(space(), token(GeneratedJavaParserConstants.ASSIGN), space(), child(ObservableProperty.INITIALIZER)))));

		///
		/// Expressions
		///

		concreteSyntaxModelByClass.put(ArrayAccessExpr.class, CsmElement.sequence(CsmElement.comment(), CsmElement.child(ObservableProperty.NAME),
				CsmElement.token(GeneratedJavaParserConstants.LBRACKET), CsmElement.child(ObservableProperty.INDEX), CsmElement.token(GeneratedJavaParserConstants.RBRACKET)));

		concreteSyntaxModelByClass.put(ArrayCreationExpr.class,
				sequence(comment(), token(GeneratedJavaParserConstants.NEW), space(), CsmElement.child(ObservableProperty.ELEMENT_TYPE), list(ObservableProperty.LEVELS),
						conditional(ObservableProperty.INITIALIZER, IS_PRESENT, sequence(space(), child(ObservableProperty.INITIALIZER)))));

		concreteSyntaxModelByClass.put(ArrayInitializerExpr.class,
				sequence(comment(), token(GeneratedJavaParserConstants.LBRACE), list(ObservableProperty.VALUES, sequence(comma(), space()), space(), space()), token(RBRACE)));

		concreteSyntaxModelByClass.put(AssignExpr.class,
				sequence(comment(), child(ObservableProperty.TARGET), space(), attribute(ObservableProperty.OPERATOR), space(), child(ObservableProperty.VALUE)));

		concreteSyntaxModelByClass.put(BinaryExpr.class,
				sequence(comment(), child(ObservableProperty.LEFT), space(), attribute(ObservableProperty.OPERATOR), space(), child(ObservableProperty.RIGHT)));

		concreteSyntaxModelByClass.put(BooleanLiteralExpr.class, sequence(comment(), attribute(VALUE)));

		concreteSyntaxModelByClass.put(CastExpr.class, sequence(comment(), token(GeneratedJavaParserConstants.LPAREN), child(ObservableProperty.TYPE),
				token(GeneratedJavaParserConstants.RPAREN), space(), child(ObservableProperty.EXPRESSION)));

		concreteSyntaxModelByClass.put(CharLiteralExpr.class, sequence(comment(), charToken(ObservableProperty.VALUE)));

		concreteSyntaxModelByClass.put(ClassExpr.class,
				sequence(comment(), child(ObservableProperty.TYPE), token(GeneratedJavaParserConstants.DOT), token(GeneratedJavaParserConstants.CLASS)));

		concreteSyntaxModelByClass.put(ConditionalExpr.class,
				CsmElement.sequence(CsmElement.comment(), CsmElement.child(ObservableProperty.CONDITION), CsmElement.space(), CsmElement.token(GeneratedJavaParserConstants.HOOK),
						CsmElement.space(), CsmElement.child(ObservableProperty.THEN_EXPR), CsmElement.space(), CsmElement.token(GeneratedJavaParserConstants.COLON),
						CsmElement.space(), CsmElement.child(ObservableProperty.ELSE_EXPR)));

		concreteSyntaxModelByClass.put(DoubleLiteralExpr.class, sequence(comment(), attribute(ObservableProperty.VALUE)));

		concreteSyntaxModelByClass.put(EnclosedExpr.class,
				sequence(comment(), token(GeneratedJavaParserConstants.LPAREN), child(ObservableProperty.INNER), token(GeneratedJavaParserConstants.RPAREN)));

		concreteSyntaxModelByClass.put(FieldAccessExpr.class,
				CsmElement.sequence(CsmElement.comment(), CsmElement.child(SCOPE), CsmElement.token(GeneratedJavaParserConstants.DOT), child(ObservableProperty.NAME)));

		concreteSyntaxModelByClass.put(InstanceOfExpr.class,
				sequence(comment(), child(ObservableProperty.EXPRESSION), space(), token(GeneratedJavaParserConstants.INSTANCEOF), space(), child(ObservableProperty.TYPE)));

		concreteSyntaxModelByClass.put(IntegerLiteralExpr.class, sequence(comment(), attribute(ObservableProperty.VALUE)));

		concreteSyntaxModelByClass.put(LambdaExpr.class,
				sequence(comment(), conditional(ObservableProperty.ENCLOSING_PARAMETERS, FLAG, token(GeneratedJavaParserConstants.LPAREN)),
						list(ObservableProperty.PARAMETERS, sequence(comma(), space())),
						conditional(ObservableProperty.ENCLOSING_PARAMETERS, FLAG, token(GeneratedJavaParserConstants.RPAREN)), space(), token(GeneratedJavaParserConstants.ARROW),
						space(), conditional(ObservableProperty.EXPRESSION_BODY, IS_PRESENT, child(ObservableProperty.EXPRESSION_BODY), child(ObservableProperty.BODY))));

		concreteSyntaxModelByClass.put(LongLiteralExpr.class, sequence(comment(), attribute(ObservableProperty.VALUE)));

		concreteSyntaxModelByClass.put(MarkerAnnotationExpr.class, sequence(comment(), token(GeneratedJavaParserConstants.AT), attribute(ObservableProperty.NAME)));

		concreteSyntaxModelByClass.put(MemberValuePair.class, CsmElement.sequence(CsmElement.comment(), CsmElement.child(ObservableProperty.NAME), CsmElement.space(),
				CsmElement.token(GeneratedJavaParserConstants.ASSIGN), CsmElement.space(), CsmElement.child(ObservableProperty.VALUE)));

		concreteSyntaxModelByClass.put(MethodCallExpr.class,
				sequence(comment(), conditional(ObservableProperty.SCOPE, IS_PRESENT, sequence(child(ObservableProperty.SCOPE), token(GeneratedJavaParserConstants.DOT))),
						typeArguments(), child(ObservableProperty.NAME), token(GeneratedJavaParserConstants.LPAREN),
						list(ObservableProperty.ARGUMENTS, sequence(comma(), space()), none(), none()), token(GeneratedJavaParserConstants.RPAREN)));

		concreteSyntaxModelByClass.put(MethodReferenceExpr.class,
				sequence(comment(), child(ObservableProperty.SCOPE), token(GeneratedJavaParserConstants.DOUBLECOLON), typeArguments(), attribute(ObservableProperty.IDENTIFIER)));

		concreteSyntaxModelByClass.put(Name.class,
				sequence(comment(), conditional(ObservableProperty.QUALIFIER, IS_PRESENT, sequence(child(ObservableProperty.QUALIFIER), token(GeneratedJavaParserConstants.DOT))),
						list(ObservableProperty.ANNOTATIONS, CsmElement.space(), CsmElement.none(), CsmElement.space()), attribute(ObservableProperty.IDENTIFIER),
						orphanCommentsEnding()));

		concreteSyntaxModelByClass.put(NameExpr.class, sequence(comment(), child(ObservableProperty.NAME), orphanCommentsEnding()));

		concreteSyntaxModelByClass.put(NormalAnnotationExpr.class, sequence(comment(), token(GeneratedJavaParserConstants.AT), child(ObservableProperty.NAME),
				token(GeneratedJavaParserConstants.LPAREN), list(ObservableProperty.PAIRS, sequence(comma(), space())), token(GeneratedJavaParserConstants.RPAREN)));

		concreteSyntaxModelByClass.put(NullLiteralExpr.class, sequence(comment(), token(GeneratedJavaParserConstants.NULL)));

		concreteSyntaxModelByClass.put(ObjectCreationExpr.class, sequence(comment(),
				conditional(ObservableProperty.SCOPE, IS_PRESENT, sequence(child(ObservableProperty.SCOPE), token(GeneratedJavaParserConstants.DOT))),
				token(GeneratedJavaParserConstants.NEW), space(),
				list(ObservableProperty.TYPE_ARGUMENTS, CsmElement.sequence(CsmElement.comma(), CsmElement.space()), CsmElement.token(LT), CsmElement.token(GT)),
				conditional(ObservableProperty.TYPE_ARGUMENTS, IS_NOT_EMPTY, space()), child(ObservableProperty.TYPE), token(GeneratedJavaParserConstants.LPAREN),
				list(ObservableProperty.ARGUMENTS, sequence(comma(), space()), none(), none()), token(GeneratedJavaParserConstants.RPAREN),
				conditional(ObservableProperty.ANONYMOUS_CLASS_BODY, IS_PRESENT,
						CsmElement.sequence(CsmElement.space(), CsmElement.token(LBRACE), CsmElement.newline(), CsmElement.indent(),
								CsmElement.list(ObservableProperty.ANONYMOUS_CLASS_BODY, CsmElement.newline(), CsmElement.newline(), CsmElement.newline(), CsmElement.newline()),
								CsmElement.unindent(), CsmElement.token(RBRACE)))));

		concreteSyntaxModelByClass.put(SimpleName.class, attribute(ObservableProperty.IDENTIFIER));

		concreteSyntaxModelByClass.put(SingleMemberAnnotationExpr.class,
				CsmElement.sequence(CsmElement.comment(), CsmElement.token(GeneratedJavaParserConstants.AT), CsmElement.child(ObservableProperty.NAME),
						CsmElement.token(GeneratedJavaParserConstants.LPAREN), CsmElement.child(ObservableProperty.MEMBER_VALUE),
						CsmElement.token(GeneratedJavaParserConstants.RPAREN)));

		concreteSyntaxModelByClass.put(StringLiteralExpr.class, sequence(comment(), stringToken(ObservableProperty.VALUE)));

		concreteSyntaxModelByClass.put(SuperExpr.class,
				CsmElement.sequence(CsmElement.comment(),
						CsmElement.conditional(ObservableProperty.CLASS_EXPR, IS_PRESENT,
								CsmElement.sequence(CsmElement.child(ObservableProperty.CLASS_EXPR), CsmElement.token(GeneratedJavaParserConstants.DOT))),
						CsmElement.token(GeneratedJavaParserConstants.SUPER)));

		concreteSyntaxModelByClass.put(ThisExpr.class,
				sequence(comment(), conditional(ObservableProperty.CLASS_EXPR, IS_PRESENT, sequence(child(CLASS_EXPR), token(GeneratedJavaParserConstants.DOT))),
						token(GeneratedJavaParserConstants.THIS)));

		concreteSyntaxModelByClass.put(TypeExpr.class, sequence(comment(), child(ObservableProperty.TYPE)));

		concreteSyntaxModelByClass.put(UnaryExpr.class, sequence(conditional(ObservableProperty.PREFIX, FLAG, attribute(ObservableProperty.OPERATOR)),
				child(ObservableProperty.EXPRESSION), conditional(ObservableProperty.POSTFIX, FLAG, attribute(ObservableProperty.OPERATOR))));

		concreteSyntaxModelByClass.put(VariableDeclarationExpr.class,
				sequence(comment(), list(ObservableProperty.ANNOTATIONS, CsmElement.space(), CsmElement.none(), CsmElement.space()), modifiers(),
						child(ObservableProperty.MAXIMUM_COMMON_TYPE), space(), list(ObservableProperty.VARIABLES, sequence(comma(), space()))));

		///
		/// Statements
		///

		concreteSyntaxModelByClass.put(AssertStmt.class, CsmElement.sequence(CsmElement.comment(), CsmElement.token(GeneratedJavaParserConstants.ASSERT),
				CsmElement.space(), CsmElement.child(ObservableProperty.CHECK), CsmElement.conditional(ObservableProperty.MESSAGE, IS_PRESENT, CsmElement
						.sequence(CsmElement.space(), CsmElement.token(GeneratedJavaParserConstants.COLON), CsmElement.space(), CsmElement.child(ObservableProperty.MESSAGE))),
				CsmElement.semicolon()));

		concreteSyntaxModelByClass.put(BlockStmt.class, sequence(orphanCommentsBeforeThis(), comment(), token(GeneratedJavaParserConstants.LBRACE), newline(),
				list(ObservableProperty.STATEMENTS, newline(), indent(), sequence(newline(), unindent())), orphanCommentsEnding(), token(RBRACE)));

		concreteSyntaxModelByClass.put(BreakStmt.class,
				CsmElement.sequence(CsmElement.comment(), CsmElement.token(GeneratedJavaParserConstants.BREAK),
						CsmElement.conditional(ObservableProperty.LABEL, IS_PRESENT, CsmElement.sequence(CsmElement.space(), CsmElement.child(ObservableProperty.LABEL))),
						CsmElement.semicolon()));

		concreteSyntaxModelByClass.put(CatchClause.class,
				CsmElement.sequence(CsmElement.comment(), CsmElement.space(), CsmElement.token(GeneratedJavaParserConstants.CATCH), CsmElement.space(), CsmElement.token(LPAREN),
						CsmElement.child(ObservableProperty.PARAMETER), CsmElement.token(RPAREN), CsmElement.space(), CsmElement.child(BODY)));

		concreteSyntaxModelByClass.put(ContinueStmt.class,
				CsmElement.sequence(CsmElement.comment(), CsmElement.token(GeneratedJavaParserConstants.CONTINUE),
						CsmElement.conditional(ObservableProperty.LABEL, IS_PRESENT, CsmElement.sequence(CsmElement.space(), CsmElement.child(ObservableProperty.LABEL))),
						CsmElement.semicolon()));

		concreteSyntaxModelByClass.put(DoStmt.class,
				CsmElement.sequence(CsmElement.comment(), CsmElement.token(GeneratedJavaParserConstants.DO), CsmElement.space(), CsmElement.child(ObservableProperty.BODY),
						CsmElement.space(), CsmElement.token(GeneratedJavaParserConstants.WHILE), CsmElement.space(), CsmElement.token(GeneratedJavaParserConstants.LPAREN),
						child(ObservableProperty.CONDITION), CsmElement.token(GeneratedJavaParserConstants.RPAREN), CsmElement.semicolon()));

		concreteSyntaxModelByClass.put(EmptyStmt.class, CsmElement.sequence(CsmElement.comment(), CsmElement.token(GeneratedJavaParserConstants.SEMICOLON)));

		concreteSyntaxModelByClass.put(ExplicitConstructorInvocationStmt.class,
				CsmElement.sequence(
						CsmElement
								.comment(),
						CsmElement
								.conditional(ObservableProperty.THIS, FLAG, CsmElement.sequence(typeArguments(), CsmElement.token(GeneratedJavaParserConstants.THIS)),
										CsmElement
												.sequence(
														CsmElement.conditional(ObservableProperty.EXPRESSION, IS_PRESENT,
																CsmElement.sequence(CsmElement.child(ObservableProperty.EXPRESSION),
																		CsmElement.token(GeneratedJavaParserConstants.DOT))),
														typeArguments(), CsmElement.token(GeneratedJavaParserConstants.SUPER))),
						CsmElement.token(GeneratedJavaParserConstants.LPAREN),
						CsmElement.list(ObservableProperty.ARGUMENTS, CsmElement.sequence(CsmElement.comma(), CsmElement.space())),
						CsmElement.token(GeneratedJavaParserConstants.RPAREN), CsmElement.semicolon()));

		concreteSyntaxModelByClass.put(ExpressionStmt.class, sequence(orphanCommentsBeforeThis(), comment(), child(ObservableProperty.EXPRESSION), semicolon()));

		concreteSyntaxModelByClass.put(ForeachStmt.class,
				sequence(comment(), token(GeneratedJavaParserConstants.FOR), space(), token(GeneratedJavaParserConstants.LPAREN), child(ObservableProperty.VARIABLE), space(),
						token(GeneratedJavaParserConstants.COLON), space(), child(ObservableProperty.ITERABLE), token(GeneratedJavaParserConstants.RPAREN), space(),
						child(ObservableProperty.BODY)));

		concreteSyntaxModelByClass.put(ForStmt.class,
				sequence(comment(), token(GeneratedJavaParserConstants.FOR), space(), token(GeneratedJavaParserConstants.LPAREN),
						list(ObservableProperty.INITIALIZATION, sequence(comma(), space())), semicolon(), space(), child(ObservableProperty.COMPARE), semicolon(), space(),
						list(ObservableProperty.UPDATE, sequence(comma(), space())), token(GeneratedJavaParserConstants.RPAREN), space(), child(ObservableProperty.BODY)));

		concreteSyntaxModelByClass.put(IfStmt.class,
				sequence(comment(), token(GeneratedJavaParserConstants.IF), space(), token(GeneratedJavaParserConstants.LPAREN), child(ObservableProperty.CONDITION),
						token(GeneratedJavaParserConstants.RPAREN),
						conditional(ObservableProperty.THEN_BLOCK, CsmConditional.Condition.FLAG,
								sequence(space(), child(ObservableProperty.THEN_STMT), conditional(ObservableProperty.ELSE_STMT, IS_PRESENT, space())),
								sequence(newline(), CsmElement.indent(), child(ObservableProperty.THEN_STMT), conditional(ObservableProperty.ELSE_STMT, IS_PRESENT, newline()),
										unindent())),
						conditional(ObservableProperty.ELSE_STMT, IS_PRESENT,
								sequence(token(GeneratedJavaParserConstants.ELSE),
										conditional(ObservableProperty.ELSE_BLOCK, CsmConditional.Condition.FLAG, sequence(space(), child(ObservableProperty.ELSE_STMT)),
												sequence(newline(), CsmElement.indent(), child(ObservableProperty.ELSE_STMT), unindent()))))));

		concreteSyntaxModelByClass.put(LabeledStmt.class, CsmElement.sequence(CsmElement.comment(), CsmElement.child(ObservableProperty.LABEL),
				CsmElement.token(GeneratedJavaParserConstants.COLON), CsmElement.space(), child(ObservableProperty.STATEMENT)));

		concreteSyntaxModelByClass.put(LocalClassDeclarationStmt.class, CsmElement.sequence(CsmElement.comment(), CsmElement.child(ObservableProperty.CLASS_DECLARATION)));

		concreteSyntaxModelByClass.put(ReturnStmt.class, sequence(comment(), token(GeneratedJavaParserConstants.RETURN),
				conditional(ObservableProperty.EXPRESSION, IS_PRESENT, sequence(space(), child(ObservableProperty.EXPRESSION))), semicolon()));

		concreteSyntaxModelByClass.put(SwitchEntryStmt.class, CsmElement.sequence(CsmElement.comment(),
				CsmElement.conditional(ObservableProperty.LABEL, IS_PRESENT,
						CsmElement.sequence(CsmElement.token(GeneratedJavaParserConstants.CASE), CsmElement.space(), CsmElement.child(ObservableProperty.LABEL),
								CsmElement.token(GeneratedJavaParserConstants.COLON)),
						CsmElement.sequence(CsmElement.token(GeneratedJavaParserConstants._DEFAULT), CsmElement.token(GeneratedJavaParserConstants.COLON))),
				CsmElement.newline(), CsmElement.indent(), CsmElement.list(ObservableProperty.STATEMENTS, CsmElement.newline(), CsmElement.none(), CsmElement.newline()),
				CsmElement.unindent()));

		concreteSyntaxModelByClass.put(SwitchStmt.class, CsmElement.sequence(CsmElement.comment(), CsmElement.token(GeneratedJavaParserConstants.SWITCH),
				CsmElement.token(GeneratedJavaParserConstants.LPAREN), CsmElement.child(ObservableProperty.SELECTOR), CsmElement.token(GeneratedJavaParserConstants.RPAREN),
				CsmElement.space(), CsmElement.token(GeneratedJavaParserConstants.LBRACE), CsmElement.newline(),
				CsmElement.list(ObservableProperty.ENTRIES, CsmElement.none(), CsmElement.indent(), CsmElement.unindent()), CsmElement.token(GeneratedJavaParserConstants.RBRACE)));

		concreteSyntaxModelByClass.put(SynchronizedStmt.class, CsmElement.sequence(CsmElement.comment(), CsmElement.token(GeneratedJavaParserConstants.SYNCHRONIZED),
				CsmElement.space(), CsmElement.token(LPAREN), CsmElement.child(EXPRESSION), CsmElement.token(RPAREN), CsmElement.space(), CsmElement.child(BODY)));

		concreteSyntaxModelByClass.put(ThrowStmt.class, sequence(comment(), token(GeneratedJavaParserConstants.THROW), space(), child(ObservableProperty.EXPRESSION), semicolon()));

		concreteSyntaxModelByClass.put(TryStmt.class,
				CsmElement.sequence(CsmElement.comment(), CsmElement.token(GeneratedJavaParserConstants.TRY), CsmElement.space(),
						CsmElement.conditional(ObservableProperty.RESOURCES, CsmConditional.Condition.IS_NOT_EMPTY,
								CsmElement.sequence(CsmElement.token(LPAREN),
										list(ObservableProperty.RESOURCES, CsmElement.sequence(CsmElement.semicolon(), CsmElement.newline()), CsmElement.indent(),
												CsmElement.unindent()),
										CsmElement.token(RPAREN), CsmElement.space())),
						CsmElement.child(ObservableProperty.TRY_BLOCK), CsmElement.list(ObservableProperty.CATCH_CLAUSES),
						CsmElement.conditional(ObservableProperty.FINALLY_BLOCK, IS_PRESENT, CsmElement.sequence(CsmElement.space(),
								CsmElement.token(GeneratedJavaParserConstants.FINALLY), CsmElement.space(), CsmElement.child(ObservableProperty.FINALLY_BLOCK)))));

		concreteSyntaxModelByClass.put(WhileStmt.class, sequence(comment(), token(GeneratedJavaParserConstants.WHILE), space(), token(GeneratedJavaParserConstants.LPAREN),
				child(ObservableProperty.CONDITION), token(GeneratedJavaParserConstants.RPAREN), space(), child(ObservableProperty.BODY)));

		///
		/// Types
		///

		concreteSyntaxModelByClass.put(ArrayType.class, sequence(child(ObservableProperty.COMPONENT_TYPE), list(ObservableProperty.ANNOTATIONS),
				string(GeneratedJavaParserConstants.LBRACKET), string(GeneratedJavaParserConstants.RBRACKET)));

		concreteSyntaxModelByClass.put(ClassOrInterfaceType.class,
				sequence(comment(), conditional(SCOPE, IS_PRESENT, sequence(child(SCOPE), string(GeneratedJavaParserConstants.DOT))), list(ANNOTATIONS, space()), child(NAME),
						conditional(ObservableProperty.USING_DIAMOND_OPERATOR, FLAG, sequence(string(GeneratedJavaParserConstants.LT), string(GeneratedJavaParserConstants.GT)),
								list(TYPE_ARGUMENTS, sequence(comma(), space()), string(GeneratedJavaParserConstants.LT), string(GeneratedJavaParserConstants.GT)))));

		concreteSyntaxModelByClass.put(IntersectionType.class, CsmElement.sequence(CsmElement.comment(), annotations(),
				CsmElement.list(ObservableProperty.ELEMENTS, CsmElement.sequence(CsmElement.space(), CsmElement.token(GeneratedJavaParserConstants.BIT_AND), CsmElement.space()))));

		concreteSyntaxModelByClass.put(PrimitiveType.class, sequence(comment(), list(ObservableProperty.ANNOTATIONS), attribute(ObservableProperty.TYPE)));

		concreteSyntaxModelByClass.put(TypeParameter.class, sequence(comment(), annotations(), child(ObservableProperty.NAME), list(ObservableProperty.TYPE_BOUND,
				sequence(space(), token(GeneratedJavaParserConstants.BIT_AND), space()), sequence(space(), token(GeneratedJavaParserConstants.EXTENDS), space()), none())));

		concreteSyntaxModelByClass.put(UnionType.class, CsmElement.sequence(CsmElement.comment(), annotations(),
				CsmElement.list(ObservableProperty.ELEMENTS, CsmElement.sequence(CsmElement.space(), CsmElement.token(GeneratedJavaParserConstants.BIT_OR), CsmElement.space()))));

		concreteSyntaxModelByClass.put(UnknownType.class, none());

		concreteSyntaxModelByClass.put(VoidType.class, sequence(comment(), annotations(), token(GeneratedJavaParserConstants.VOID)));

		concreteSyntaxModelByClass.put(WildcardType.class,
				sequence(comment(), annotations(), token(GeneratedJavaParserConstants.HOOK),
						CsmElement.conditional(ObservableProperty.EXTENDED_TYPE, IS_PRESENT,
								CsmElement.sequence(space(), token(GeneratedJavaParserConstants.EXTENDS), space(), CsmElement.child(EXTENDED_TYPE))),
						CsmElement.conditional(ObservableProperty.SUPER_TYPE, IS_PRESENT,
								CsmElement.sequence(space(), token(GeneratedJavaParserConstants.SUPER), space(), CsmElement.child(SUPER_TYPE)))));

		///
		/// Top Level
		///

		concreteSyntaxModelByClass.put(ArrayCreationLevel.class,
				sequence(annotations(), token(GeneratedJavaParserConstants.LBRACKET), child(ObservableProperty.DIMENSION), token(GeneratedJavaParserConstants.RBRACKET)));

		concreteSyntaxModelByClass.put(CompilationUnit.class,
				sequence(comment(), child(ObservableProperty.PACKAGE_DECLARATION), list(ObservableProperty.IMPORTS, none(), none(), newline()),
						list(TYPES, newline(), CsmElement.newline(), CsmElement.none(), CsmElement.newline()), child(ObservableProperty.MODULE), orphanCommentsEnding()));

		concreteSyntaxModelByClass.put(ImportDeclaration.class,
				sequence(comment(), token(GeneratedJavaParserConstants.IMPORT), space(),
						conditional(ObservableProperty.STATIC, FLAG, sequence(token(GeneratedJavaParserConstants.STATIC), space())), child(ObservableProperty.NAME),
						conditional(ASTERISK, FLAG, sequence(token(GeneratedJavaParserConstants.DOT), token(GeneratedJavaParserConstants.STAR))), semicolon(), newline(),
						orphanCommentsEnding()));

		concreteSyntaxModelByClass.put(PackageDeclaration.class, sequence(comment(), list(ObservableProperty.ANNOTATIONS), token(GeneratedJavaParserConstants.PACKAGE), space(),
				child(ObservableProperty.NAME), semicolon(), newline(), newline(), orphanCommentsEnding()));

		///
		/// Module info
		///

		concreteSyntaxModelByClass.put(ModuleDeclaration.class,
				CsmElement.sequence(annotations(),
						CsmElement.conditional(ObservableProperty.OPEN, FLAG, CsmElement.sequence(CsmElement.token(GeneratedJavaParserConstants.OPEN), CsmElement.space())),
						CsmElement.token(GeneratedJavaParserConstants.MODULE), CsmElement.space(), CsmElement.child(ObservableProperty.NAME), CsmElement.space(),
						CsmElement.token(GeneratedJavaParserConstants.LBRACE), CsmElement.newline(), CsmElement.indent(), CsmElement.list(ObservableProperty.MODULE_STMTS),
						CsmElement.unindent(), CsmElement.token(GeneratedJavaParserConstants.RBRACE), CsmElement.newline()));

		concreteSyntaxModelByClass.put(ModuleExportsStmt.class,
				CsmElement.sequence(CsmElement.token(GeneratedJavaParserConstants.EXPORTS), CsmElement.space(), CsmElement.child(ObservableProperty.NAME),
						CsmElement.list(ObservableProperty.MODULE_NAMES, CsmElement.sequence(CsmElement.comma(), CsmElement.space()),
								CsmElement.sequence(CsmElement.space(), CsmElement.token(GeneratedJavaParserConstants.TO), CsmElement.space()), CsmElement.none()),
						CsmElement.semicolon(), CsmElement.newline()));

		concreteSyntaxModelByClass.put(ModuleOpensStmt.class,
				CsmElement.sequence(CsmElement.token(GeneratedJavaParserConstants.OPENS), CsmElement.space(), CsmElement.child(ObservableProperty.NAME),
						CsmElement.list(ObservableProperty.MODULE_NAMES, CsmElement.sequence(CsmElement.comma(), CsmElement.space()),
								CsmElement.sequence(CsmElement.space(), CsmElement.token(GeneratedJavaParserConstants.TO), CsmElement.space()), CsmElement.none()),
						CsmElement.semicolon(), CsmElement.newline()));

		concreteSyntaxModelByClass.put(ModuleProvidesStmt.class,
				CsmElement.sequence(CsmElement.token(GeneratedJavaParserConstants.PROVIDES), CsmElement.space(), CsmElement.child(ObservableProperty.TYPE),
						CsmElement.list(ObservableProperty.WITH_TYPES, CsmElement.sequence(CsmElement.comma(), CsmElement.space()),
								CsmElement.sequence(CsmElement.space(), CsmElement.token(GeneratedJavaParserConstants.WITH), CsmElement.space()), CsmElement.none()),
						CsmElement.semicolon(), CsmElement.newline()));

		concreteSyntaxModelByClass.put(ModuleRequiresStmt.class, CsmElement.sequence(CsmElement.token(GeneratedJavaParserConstants.REQUIRES), CsmElement.space(), modifiers(),
				CsmElement.child(ObservableProperty.NAME), CsmElement.semicolon(), CsmElement.newline()));

		concreteSyntaxModelByClass.put(ModuleUsesStmt.class, CsmElement.sequence(CsmElement.token(GeneratedJavaParserConstants.USES), CsmElement.space(),
				CsmElement.child(ObservableProperty.TYPE), CsmElement.semicolon(), CsmElement.newline()));

		List<String> unsupportedNodeClassNames = JavaParserMetaModel.getNodeMetaModels().stream()
				.filter(c -> !c.isAbstract() && !Comment.class.isAssignableFrom(c.getType()) && !concreteSyntaxModelByClass.containsKey(c.getType()))
				.map(nm -> nm.getType().getSimpleName()).collect(Collectors.toList());
		if (unsupportedNodeClassNames.isEmpty()) {
			initializationError = Optional.empty();
		} else {
			initializationError = Optional.of("The CSM should include support for these classes: " + String.join(", ", unsupportedNodeClassNames));
		}
	}

	private static class JavadocContentTokenCalculator implements CsmToken.TokenContentCalculator {
		@Override
		public String calculate(Node node) {
			return "/**" + ((JavadocComment) node).getContent() + "*";
		}

		@Override
		public int hashCode() {
			return 1;
		}

		@Override
		public boolean equals(Object obj) {
			return obj instanceof JavadocContentTokenCalculator;
		}
	}

	private ConcreteSyntaxModel() {

	}

	public static void genericPrettyPrint(Node node, SourcePrinter printer) {
		forClass(node.getClass()).prettyPrint(node, printer);
	}

	public static String genericPrettyPrint(Node node) {
		SourcePrinter sourcePrinter = new SourcePrinter("    ", EOL);
		forClass(node.getClass()).prettyPrint(node, sourcePrinter);
		return sourcePrinter.getSource();
	}

	public static CsmElement forClass(Class<? extends Node> nodeClazz) {
		if (initializationError.isPresent()) {
			throw new IllegalStateException(initializationError.get());
		}
		if (!concreteSyntaxModelByClass.containsKey(nodeClazz)) {
			throw new UnsupportedOperationException(nodeClazz.getSimpleName());
		}
		return concreteSyntaxModelByClass.get(nodeClazz);
	}

}
