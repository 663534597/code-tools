package com.yijia.codegen.models.base;

import static com.yijia.codegen.models.base.GeneratedJavaParserSupport.add;
import static com.yijia.codegen.models.base.GeneratedJavaParserSupport.addModifier;
import static com.yijia.codegen.models.base.GeneratedJavaParserSupport.addWhenNotNull;
import static com.yijia.codegen.models.base.GeneratedJavaParserSupport.emptyList;
import static com.yijia.codegen.models.base.GeneratedJavaParserSupport.generateLambda;
import static com.yijia.codegen.models.base.GeneratedJavaParserSupport.juggleArrayCreation;
import static com.yijia.codegen.models.base.GeneratedJavaParserSupport.juggleArrayType;
import static com.yijia.codegen.models.base.GeneratedJavaParserSupport.nodeListBegin;
import static com.yijia.codegen.models.base.GeneratedJavaParserSupport.range;
import static com.yijia.codegen.models.base.JavaToken.INVALID;
import static com.yijia.codegen.models.base.ast.type.ArrayType.wrapInArrayTypes;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import com.yijia.codegen.models.base.ast.CompilationUnit;
import com.yijia.codegen.models.base.ast.ImportDeclaration;
import com.yijia.codegen.models.base.ast.Modifier;
import com.yijia.codegen.models.base.ast.NodeList;
import com.yijia.codegen.models.base.ast.PackageDeclaration;
import com.yijia.codegen.models.base.ast.body.AnnotationDeclaration;
import com.yijia.codegen.models.base.ast.body.AnnotationMemberDeclaration;
import com.yijia.codegen.models.base.ast.body.BodyDeclaration;
import com.yijia.codegen.models.base.ast.body.ClassOrInterfaceDeclaration;
import com.yijia.codegen.models.base.ast.body.ConstructorDeclaration;
import com.yijia.codegen.models.base.ast.body.EnumConstantDeclaration;
import com.yijia.codegen.models.base.ast.body.EnumDeclaration;
import com.yijia.codegen.models.base.ast.body.FieldDeclaration;
import com.yijia.codegen.models.base.ast.body.InitializerDeclaration;
import com.yijia.codegen.models.base.ast.body.MethodDeclaration;
import com.yijia.codegen.models.base.ast.body.Parameter;
import com.yijia.codegen.models.base.ast.body.TypeDeclaration;
import com.yijia.codegen.models.base.ast.body.VariableDeclarator;
import com.yijia.codegen.models.base.ast.comments.CommentsCollection;
import com.yijia.codegen.models.base.ast.expr.AnnotationExpr;
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
import com.yijia.codegen.models.base.ast.expr.Expression;
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
import com.yijia.codegen.models.base.ast.modules.ModuleStmt;
import com.yijia.codegen.models.base.ast.modules.ModuleUsesStmt;
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
import com.yijia.codegen.models.base.ast.stmt.Statement;
import com.yijia.codegen.models.base.ast.stmt.SwitchEntryStmt;
import com.yijia.codegen.models.base.ast.stmt.SwitchStmt;
import com.yijia.codegen.models.base.ast.stmt.SynchronizedStmt;
import com.yijia.codegen.models.base.ast.stmt.ThrowStmt;
import com.yijia.codegen.models.base.ast.stmt.TryStmt;
import com.yijia.codegen.models.base.ast.stmt.WhileStmt;
import com.yijia.codegen.models.base.ast.type.ArrayType.ArrayBracketPair;
import com.yijia.codegen.models.base.ast.type.ClassOrInterfaceType;
import com.yijia.codegen.models.base.ast.type.IntersectionType;
import com.yijia.codegen.models.base.ast.type.PrimitiveType;
import com.yijia.codegen.models.base.ast.type.ReferenceType;
import com.yijia.codegen.models.base.ast.type.Type;
import com.yijia.codegen.models.base.ast.type.TypeParameter;
import com.yijia.codegen.models.base.ast.type.UnionType;
import com.yijia.codegen.models.base.ast.type.UnknownType;
import com.yijia.codegen.models.base.ast.type.VoidType;
import com.yijia.codegen.models.base.ast.type.WildcardType;
import com.yijia.codegen.models.base.utils.Pair;

/**
 * <p>
 * This class was generated automatically by javacc, do not edit.
 * </p>
 */
@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
final class GeneratedJavaParser implements GeneratedJavaParserConstants {
	List<Problem> problems = new ArrayList<Problem>();

	void reset(Provider provider) {
		ReInit(provider);
		problems = new ArrayList<Problem>();
		token_source.reset();
	}

	/**
	 * Return the list of tokens that have been encountered while parsing code using this parser.
	 * @return a list of tokens
	 */
	public List<JavaToken> getTokens() {
		return token_source.getTokens();
	}

	public CommentsCollection getCommentsCollection() {
		return token_source.getCommentsCollection();
	}

	void addProblem(String message) {
		// TODO tokenRange only takes the final token. Need all the tokens.
		problems.add(new Problem(message, tokenRange(), null));
	}

	static final class CustomToken extends Token {
		/**
		 * 
		 */
		private static final long serialVersionUID = -6326983940626405951L;

		int realKind = GeneratedJavaParserConstants.GT;
		JavaToken javaToken;

		CustomToken(int kind, String image) {
			this.kind = kind;
			this.image = image;
		}

		public static Token newToken(int kind, String image) {
			return new CustomToken(kind, image);
		}
	}

	private JavaToken token() {
		return ((CustomToken) token).javaToken;
	}

	private TokenRange tokenRange() {
		return new TokenRange(token(), token());
	}

	public void setTabSize(int size) {
		jj_input_stream.setTabSize(size);
	}

	/*****************************************
	 * THE JAVA LANGUAGE GRAMMAR STARTS HERE *
	 *****************************************/

	/*
	 * Program structuring syntax follows.
	 */
	final public CompilationUnit CompilationUnit() throws ParseException {
		PackageDeclaration pakage = null;
		NodeList<ImportDeclaration> imports = emptyList();
		ImportDeclaration in = null;
		NodeList<TypeDeclaration<?>> types = emptyList();
		ModifierHolder modifier;
		TypeDeclaration tn = null;
		ModuleDeclaration module = null;
		label_1:
		while (true) {
			if (jj_2_1(2)) {
				;
			} else {
				break label_1;
			}
			jj_consume_token(SEMICOLON);
		}
		if (jj_2_2(2147483647)) {
			pakage = PackageDeclaration();
		} else {
			;
		}
		label_2:
		while (true) {
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case ABSTRACT:
				case CLASS:
				case _DEFAULT:
				case ENUM:
				case FINAL:
				case IMPORT:
				case INTERFACE:
				case NATIVE:
				case PRIVATE:
				case PROTECTED:
				case PUBLIC:
				case STATIC:
				case STRICTFP:
				case SYNCHRONIZED:
				case TRANSIENT:
				case VOLATILE:
				case OPEN:
				case MODULE:
				case TRANSITIVE:
				case SEMICOLON:
				case AT: {
					;
					break;
				}
				default:
					jj_la1[0] = jj_gen;
					break label_2;
			}
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case IMPORT: {
					in = ImportDeclaration();
					imports = add(imports, in);
					break;
				}
				case ABSTRACT:
				case CLASS:
				case _DEFAULT:
				case ENUM:
				case FINAL:
				case INTERFACE:
				case NATIVE:
				case PRIVATE:
				case PROTECTED:
				case PUBLIC:
				case STATIC:
				case STRICTFP:
				case SYNCHRONIZED:
				case TRANSIENT:
				case VOLATILE:
				case OPEN:
				case MODULE:
				case TRANSITIVE:
				case SEMICOLON:
				case AT: {
					modifier = Modifiers();
					switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
						case CLASS:
						case INTERFACE: {
							tn = ClassOrInterfaceDeclaration(modifier);
							types = add(types, tn);
							break;
						}
						case ENUM: {
							tn = EnumDeclaration(modifier);
							types = add(types, tn);
							break;
						}
						case AT: {
							tn = AnnotationTypeDeclaration(modifier);
							types = add(types, tn);
							break;
						}
						case OPEN:
						case MODULE: {
							module = ModuleDeclaration(modifier);
							break;
						}
						case SEMICOLON: {
							jj_consume_token(SEMICOLON);
							break;
						}
						default:
							jj_la1[1] = jj_gen;
							jj_consume_token(-1);
							throw new ParseException();
					}
					break;
				}
				default:
					jj_la1[2] = jj_gen;
					jj_consume_token(-1);
					throw new ParseException();
			}
		}
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case 0: {
				jj_consume_token(0);
				break;
			}
			case 167: {
				jj_consume_token(167);
				break;
			}
			default:
				jj_la1[3] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
		}
		return new CompilationUnit(range(token_source.getHomeToken(), token()), pakage, imports, types, module);
	}

	final public PackageDeclaration PackageDeclaration() throws ParseException {
		NodeList<AnnotationExpr> annotations = new NodeList<AnnotationExpr>();
		Name name;
		JavaToken begin;
		annotations = Annotations();
		jj_consume_token(PACKAGE);
		begin = token();
		name = Name();
		jj_consume_token(SEMICOLON);
		return new PackageDeclaration(range(begin, token()), annotations, name);
	}

	final public ImportDeclaration ImportDeclaration() throws ParseException {
		Name name;
		boolean isStatic = false;
		boolean isAsterisk = false;
		JavaToken begin;
		jj_consume_token(IMPORT);
		begin = token();
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case STATIC: {
				jj_consume_token(STATIC);
				isStatic = true;
				break;
			}
			default:
				jj_la1[4] = jj_gen;
				;
		}
		name = Name();
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case DOT: {
				jj_consume_token(DOT);
				jj_consume_token(STAR);
				isAsterisk = true;
				break;
			}
			default:
				jj_la1[5] = jj_gen;
				;
		}
		jj_consume_token(SEMICOLON);
		return new ImportDeclaration(range(begin, token()), name, isStatic, isAsterisk);
	}

	/*
	 * Modifiers. We match all modifiers in a single rule to reduce the chances of syntax errors for simple modifier mistakes. It will also enable us to give better error messages.
	 */
	final public ModifierHolder Modifiers() throws ParseException {
		JavaToken begin = INVALID;
		EnumSet<Modifier> modifiers = EnumSet.noneOf(Modifier.class);
		NodeList<AnnotationExpr> annotations = new NodeList<AnnotationExpr>();
		AnnotationExpr ann;
		label_3:
		while (true) {
			if (jj_2_3(2)) {
				;
			} else {
				break label_3;
			}
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case PUBLIC: {
					jj_consume_token(PUBLIC);
					addModifier(this, modifiers, Modifier.PUBLIC);
					begin = begin.orIfInvalid(token());
					break;
				}
				case STATIC: {
					jj_consume_token(STATIC);
					addModifier(this, modifiers, Modifier.STATIC);
					begin = begin.orIfInvalid(token());
					break;
				}
				case PROTECTED: {
					jj_consume_token(PROTECTED);
					addModifier(this, modifiers, Modifier.PROTECTED);
					begin = begin.orIfInvalid(token());
					break;
				}
				case PRIVATE: {
					jj_consume_token(PRIVATE);
					addModifier(this, modifiers, Modifier.PRIVATE);
					begin = begin.orIfInvalid(token());
					break;
				}
				case FINAL: {
					jj_consume_token(FINAL);
					addModifier(this, modifiers, Modifier.FINAL);
					begin = begin.orIfInvalid(token());
					break;
				}
				case ABSTRACT: {
					jj_consume_token(ABSTRACT);
					addModifier(this, modifiers, Modifier.ABSTRACT);
					begin = begin.orIfInvalid(token());
					break;
				}
				case SYNCHRONIZED: {
					jj_consume_token(SYNCHRONIZED);
					addModifier(this, modifiers, Modifier.SYNCHRONIZED);
					begin = begin.orIfInvalid(token());
					break;
				}
				case NATIVE: {
					jj_consume_token(NATIVE);
					addModifier(this, modifiers, Modifier.NATIVE);
					begin = begin.orIfInvalid(token());
					break;
				}
				case TRANSIENT: {
					jj_consume_token(TRANSIENT);
					addModifier(this, modifiers, Modifier.TRANSIENT);
					begin = begin.orIfInvalid(token());
					break;
				}
				case VOLATILE: {
					jj_consume_token(VOLATILE);
					addModifier(this, modifiers, Modifier.VOLATILE);
					begin = begin.orIfInvalid(token());
					break;
				}
				case STRICTFP: {
					jj_consume_token(STRICTFP);
					addModifier(this, modifiers, Modifier.STRICTFP);
					begin = begin.orIfInvalid(token());
					break;
				}
				case TRANSITIVE: {
					jj_consume_token(TRANSITIVE);
					addModifier(this, modifiers, Modifier.TRANSITIVE);
					begin = begin.orIfInvalid(token());
					break;
				}
				case _DEFAULT: {
					jj_consume_token(_DEFAULT);
					addModifier(this, modifiers, Modifier.DEFAULT);
					begin = begin.orIfInvalid(token());
					break;
				}
				case AT: {
					ann = Annotation();
					annotations = add(annotations, ann);
					begin = begin.orIfInvalid(ann.getTokenRange().get().getBegin());
					break;
				}
				default:
					jj_la1[6] = jj_gen;
					jj_consume_token(-1);
					throw new ParseException();
			}
		}
		return new ModifierHolder(begin, modifiers, annotations);
	}

	/*
	 * Declaration syntax follows.
	 */
	final public ClassOrInterfaceDeclaration ClassOrInterfaceDeclaration(ModifierHolder modifier) throws ParseException {
		boolean isInterface = false;
		SimpleName name;
		RangedList<TypeParameter> typePar = new RangedList<TypeParameter>(emptyList());
		NodeList<ClassOrInterfaceType> extList = emptyList();
		NodeList<ClassOrInterfaceType> impList = emptyList();
		NodeList<BodyDeclaration<?>> members = emptyList();
		JavaToken begin = modifier.begin;
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case CLASS: {
				jj_consume_token(CLASS);
				break;
			}
			case INTERFACE: {
				jj_consume_token(INTERFACE);
				isInterface = true;
				break;
			}
			default:
				jj_la1[7] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
		}
		begin = begin.orIfInvalid(token());
		name = SimpleName();
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case LT: {
				typePar = TypeParameters();
				break;
			}
			default:
				jj_la1[8] = jj_gen;
				;
		}
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case EXTENDS: {
				extList = ExtendsList();
				break;
			}
			default:
				jj_la1[9] = jj_gen;
				;
		}
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case IMPLEMENTS: {
				impList = ImplementsList();
				break;
			}
			default:
				jj_la1[10] = jj_gen;
				;
		}
		members = ClassOrInterfaceBody();
		return new ClassOrInterfaceDeclaration(range(begin, token()), modifier.modifiers, modifier.annotations, isInterface, name, typePar.list, extList, impList, members);
	}

	final public NodeList<ClassOrInterfaceType> ExtendsList() throws ParseException {
		boolean extendsMoreThanOne = false;
		NodeList<ClassOrInterfaceType> ret = new NodeList<ClassOrInterfaceType>();
		ClassOrInterfaceType cit;
		jj_consume_token(EXTENDS);
		cit = AnnotatedClassOrInterfaceType();
		ret.add(cit);
		label_4:
		while (true) {
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case COMMA: {
					;
					break;
				}
				default:
					jj_la1[11] = jj_gen;
					break label_4;
			}
			jj_consume_token(COMMA);
			cit = AnnotatedClassOrInterfaceType();
			ret.add(cit);
			extendsMoreThanOne = true;
		}
		return ret;
	}

	final public NodeList<ClassOrInterfaceType> ImplementsList() throws ParseException {
		NodeList<ClassOrInterfaceType> ret = new NodeList<ClassOrInterfaceType>();
		ClassOrInterfaceType cit;
		jj_consume_token(IMPLEMENTS);
		cit = AnnotatedClassOrInterfaceType();
		ret.add(cit);
		label_5:
		while (true) {
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case COMMA: {
					;
					break;
				}
				default:
					jj_la1[12] = jj_gen;
					break label_5;
			}
			jj_consume_token(COMMA);
			cit = AnnotatedClassOrInterfaceType();
			ret.add(cit);
		}
		return ret;
	}

	final public EnumDeclaration EnumDeclaration(ModifierHolder modifier) throws ParseException {
		SimpleName name;
		NodeList<ClassOrInterfaceType> impList = emptyList();
		EnumConstantDeclaration entry;
		NodeList<EnumConstantDeclaration> entries = emptyList();
		BodyDeclaration<?> member;
		NodeList<BodyDeclaration<?>> members = emptyList();
		JavaToken begin = modifier.begin;
		jj_consume_token(ENUM);
		begin = begin.orIfInvalid(token());
		name = SimpleName();
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case IMPLEMENTS: {
				impList = ImplementsList();
				break;
			}
			default:
				jj_la1[13] = jj_gen;
				;
		}
		jj_consume_token(LBRACE);
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case REQUIRES:
			case TO:
			case WITH:
			case OPEN:
			case OPENS:
			case USES:
			case MODULE:
			case EXPORTS:
			case PROVIDES:
			case TRANSITIVE:
			case IDENTIFIER:
			case AT: {
				entry = EnumConstantDeclaration();
				entries.add(entry);
				label_6:
				while (true) {
					if (jj_2_4(2)) {
						;
					} else {
						break label_6;
					}
					jj_consume_token(COMMA);
					entry = EnumConstantDeclaration();
					entries.add(entry);
				}
				break;
			}
			default:
				jj_la1[14] = jj_gen;
				;
		}
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case COMMA: {
				jj_consume_token(COMMA);
				break;
			}
			default:
				jj_la1[15] = jj_gen;
				;
		}
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case SEMICOLON: {
				jj_consume_token(SEMICOLON);
				label_7:
				while (true) {
					switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
						case ABSTRACT:
						case BOOLEAN:
						case BYTE:
						case CHAR:
						case CLASS:
						case _DEFAULT:
						case DOUBLE:
						case ENUM:
						case FINAL:
						case FLOAT:
						case INT:
						case INTERFACE:
						case LONG:
						case NATIVE:
						case PRIVATE:
						case PROTECTED:
						case PUBLIC:
						case SHORT:
						case STATIC:
						case STRICTFP:
						case SYNCHRONIZED:
						case TRANSIENT:
						case VOID:
						case VOLATILE:
						case REQUIRES:
						case TO:
						case WITH:
						case OPEN:
						case OPENS:
						case USES:
						case MODULE:
						case EXPORTS:
						case PROVIDES:
						case TRANSITIVE:
						case IDENTIFIER:
						case LBRACE:
						case SEMICOLON:
						case AT:
						case LT: {
							;
							break;
						}
						default:
							jj_la1[16] = jj_gen;
							break label_7;
					}
					switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
						case ABSTRACT:
						case BOOLEAN:
						case BYTE:
						case CHAR:
						case CLASS:
						case _DEFAULT:
						case DOUBLE:
						case ENUM:
						case FINAL:
						case FLOAT:
						case INT:
						case INTERFACE:
						case LONG:
						case NATIVE:
						case PRIVATE:
						case PROTECTED:
						case PUBLIC:
						case SHORT:
						case STATIC:
						case STRICTFP:
						case SYNCHRONIZED:
						case TRANSIENT:
						case VOID:
						case VOLATILE:
						case REQUIRES:
						case TO:
						case WITH:
						case OPEN:
						case OPENS:
						case USES:
						case MODULE:
						case EXPORTS:
						case PROVIDES:
						case TRANSITIVE:
						case IDENTIFIER:
						case LBRACE:
						case AT:
						case LT: {
							member = ClassOrInterfaceBodyDeclaration();
							members = add(members, member);
							break;
						}
						case SEMICOLON: {
							jj_consume_token(SEMICOLON);
							break;
						}
						default:
							jj_la1[17] = jj_gen;
							jj_consume_token(-1);
							throw new ParseException();
					}
				}
				break;
			}
			default:
				jj_la1[18] = jj_gen;
				;
		}
		jj_consume_token(RBRACE);
		return new EnumDeclaration(range(begin, token()), modifier.modifiers, modifier.annotations, name, impList, entries, members);
	}

	final public EnumConstantDeclaration EnumConstantDeclaration() throws ParseException {
		NodeList<AnnotationExpr> annotations = new NodeList<AnnotationExpr>();
		AnnotationExpr ann;
		SimpleName name;
		NodeList<Expression> args = emptyList();
		NodeList<BodyDeclaration<?>> classBody = emptyList();
		JavaToken begin = INVALID;

		label_8:
		while (true) {
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case AT: {
					;
					break;
				}
				default:
					jj_la1[19] = jj_gen;
					break label_8;
			}
			ann = Annotation();
			annotations = add(annotations, ann);
			begin = begin.orIfInvalid(ann.getTokenRange().get().getBegin());
		}
		name = SimpleName();
		begin = begin.orIfInvalid(token());
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case LPAREN: {
				args = Arguments();
				break;
			}
			default:
				jj_la1[20] = jj_gen;
				;
		}
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case LBRACE: {
				classBody = ClassOrInterfaceBody();
				break;
			}
			default:
				jj_la1[21] = jj_gen;
				;
		}
		return new EnumConstantDeclaration(range(begin, token()), annotations, name, args, classBody);
	}

	/**
	 * If the list inside the returned RangedList is null, there are no brackets. If it is empty, there are brackets, but nothing is in them <>. The normal case is that it contains
	 * TypeParameters, like <A, B, C>.
	 */
	final public RangedList<TypeParameter> TypeParameters() throws ParseException {
		RangedList<TypeParameter> ret = new RangedList<TypeParameter>(new NodeList<TypeParameter>());
		TypeParameter tp;
		NodeList<AnnotationExpr> annotations = new NodeList<AnnotationExpr>();
		jj_consume_token(LT);
		ret.beginAt(token());
		annotations = Annotations();
		tp = TypeParameter();
		ret.add(tp);
		tp.setAnnotations(annotations);
		annotations = null;
		label_9:
		while (true) {
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case COMMA: {
					;
					break;
				}
				default:
					jj_la1[22] = jj_gen;
					break label_9;
			}
			jj_consume_token(COMMA);
			annotations = Annotations();
			tp = TypeParameter();
			ret.add(tp);
			tp.setAnnotations(annotations);
			annotations = null;
		}
		jj_consume_token(GT);
		ret.endAt(token());
		return ret;
	}

	final public TypeParameter TypeParameter() throws ParseException {
		SimpleName name;
		NodeList<ClassOrInterfaceType> typeBound = emptyList();
		JavaToken begin;
		name = SimpleName();
		begin = token();
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case EXTENDS: {
				typeBound = TypeBound();
				break;
			}
			default:
				jj_la1[23] = jj_gen;
				;
		}
		return new TypeParameter(range(begin, token()), name, typeBound, new NodeList());
	}

	final public NodeList<ClassOrInterfaceType> TypeBound() throws ParseException {
		NodeList<ClassOrInterfaceType> ret = emptyList();
		ClassOrInterfaceType cit;
		jj_consume_token(EXTENDS);
		cit = AnnotatedClassOrInterfaceType();
		ret.add(cit);
		label_10:
		while (true) {
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case BIT_AND: {
					;
					break;
				}
				default:
					jj_la1[24] = jj_gen;
					break label_10;
			}
			jj_consume_token(BIT_AND);
			cit = AnnotatedClassOrInterfaceType();
			ret.add(cit);
		}
		return ret;
	}

	final public NodeList<BodyDeclaration<?>> ClassOrInterfaceBody() throws ParseException {
		NodeList<BodyDeclaration<?>> ret = emptyList();
		BodyDeclaration member;
		jj_consume_token(LBRACE);
		label_11:
		while (true) {
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case ABSTRACT:
				case BOOLEAN:
				case BYTE:
				case CHAR:
				case CLASS:
				case _DEFAULT:
				case DOUBLE:
				case ENUM:
				case FINAL:
				case FLOAT:
				case INT:
				case INTERFACE:
				case LONG:
				case NATIVE:
				case PRIVATE:
				case PROTECTED:
				case PUBLIC:
				case SHORT:
				case STATIC:
				case STRICTFP:
				case SYNCHRONIZED:
				case TRANSIENT:
				case VOID:
				case VOLATILE:
				case REQUIRES:
				case TO:
				case WITH:
				case OPEN:
				case OPENS:
				case USES:
				case MODULE:
				case EXPORTS:
				case PROVIDES:
				case TRANSITIVE:
				case IDENTIFIER:
				case LBRACE:
				case SEMICOLON:
				case AT:
				case LT: {
					;
					break;
				}
				default:
					jj_la1[25] = jj_gen;
					break label_11;
			}
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case ABSTRACT:
				case BOOLEAN:
				case BYTE:
				case CHAR:
				case CLASS:
				case _DEFAULT:
				case DOUBLE:
				case ENUM:
				case FINAL:
				case FLOAT:
				case INT:
				case INTERFACE:
				case LONG:
				case NATIVE:
				case PRIVATE:
				case PROTECTED:
				case PUBLIC:
				case SHORT:
				case STATIC:
				case STRICTFP:
				case SYNCHRONIZED:
				case TRANSIENT:
				case VOID:
				case VOLATILE:
				case REQUIRES:
				case TO:
				case WITH:
				case OPEN:
				case OPENS:
				case USES:
				case MODULE:
				case EXPORTS:
				case PROVIDES:
				case TRANSITIVE:
				case IDENTIFIER:
				case LBRACE:
				case AT:
				case LT: {
					member = ClassOrInterfaceBodyDeclaration();
					ret.add(member);
					break;
				}
				case SEMICOLON: {
					jj_consume_token(SEMICOLON);
					break;
				}
				default:
					jj_la1[26] = jj_gen;
					jj_consume_token(-1);
					throw new ParseException();
			}
		}
		jj_consume_token(RBRACE);
		return ret;
	}

	final public BodyDeclaration<?> ClassOrInterfaceBodyDeclaration() throws ParseException {
		ModifierHolder modifier;
		BodyDeclaration<?> ret;

		if (jj_2_8(2)) {
			ret = InitializerDeclaration();
		} else {
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case ABSTRACT:
				case BOOLEAN:
				case BYTE:
				case CHAR:
				case CLASS:
				case _DEFAULT:
				case DOUBLE:
				case ENUM:
				case FINAL:
				case FLOAT:
				case INT:
				case INTERFACE:
				case LONG:
				case NATIVE:
				case PRIVATE:
				case PROTECTED:
				case PUBLIC:
				case SHORT:
				case STATIC:
				case STRICTFP:
				case SYNCHRONIZED:
				case TRANSIENT:
				case VOID:
				case VOLATILE:
				case REQUIRES:
				case TO:
				case WITH:
				case OPEN:
				case OPENS:
				case USES:
				case MODULE:
				case EXPORTS:
				case PROVIDES:
				case TRANSITIVE:
				case IDENTIFIER:
				case AT:
				case LT: {
					// Just get all the modifiers out of the way. If you want to do
					// more checks, pass the modifiers down to the member
					modifier = Modifiers();
					switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
						case CLASS:
						case INTERFACE: {
							ret = ClassOrInterfaceDeclaration(modifier);
							break;
						}
						case ENUM: {
							ret = EnumDeclaration(modifier);
							break;
						}
						default:
							jj_la1[27] = jj_gen;
							if (jj_2_5(2147483647)) {
								ret = AnnotationTypeDeclaration(modifier);
							} else if (jj_2_6(2147483647)) {
								ret = ConstructorDeclaration(modifier);
							} else if (jj_2_7(2147483647)) {
								ret = FieldDeclaration(modifier);
							} else {
								switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
									case BOOLEAN:
									case BYTE:
									case CHAR:
									case DOUBLE:
									case FLOAT:
									case INT:
									case LONG:
									case SHORT:
									case VOID:
									case REQUIRES:
									case TO:
									case WITH:
									case OPEN:
									case OPENS:
									case USES:
									case MODULE:
									case EXPORTS:
									case PROVIDES:
									case TRANSITIVE:
									case IDENTIFIER:
									case AT:
									case LT: {
										ret = MethodDeclaration(modifier);
										break;
									}
									default:
										jj_la1[28] = jj_gen;
										jj_consume_token(-1);
										throw new ParseException();
								}
							}
					}
					break;
				}
				default:
					jj_la1[29] = jj_gen;
					jj_consume_token(-1);
					throw new ParseException();
			}
		}
		return ret;
	}

	final public FieldDeclaration FieldDeclaration(ModifierHolder modifier) throws ParseException {
		Type partialType;
		NodeList<VariableDeclarator> variables = new NodeList<VariableDeclarator>();
		VariableDeclarator val;
		// Modifiers are already matched in the caller
		partialType = Type();
		val = VariableDeclarator(partialType);
		variables.add(val);
		label_12:
		while (true) {
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case COMMA: {
					;
					break;
				}
				default:
					jj_la1[30] = jj_gen;
					break label_12;
			}
			jj_consume_token(COMMA);
			val = VariableDeclarator(partialType);
			variables.add(val);
		}
		jj_consume_token(SEMICOLON);
		JavaToken begin = modifier.begin.orIfInvalid(partialType.getTokenRange().get().getBegin());
		return new FieldDeclaration(range(begin, token()), modifier.modifiers, modifier.annotations, variables);
	}

	final public VariableDeclarator VariableDeclarator(Type partialType) throws ParseException {
		Pair<SimpleName, List<ArrayBracketPair>> id;
		Expression init = null;
		id = VariableDeclaratorId();
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case ASSIGN: {
				jj_consume_token(ASSIGN);
				init = VariableInitializer();
				break;
			}
			default:
				jj_la1[31] = jj_gen;
				;
		}
		return new VariableDeclarator(range(id.a.getTokenRange().get().getBegin(), token()), juggleArrayType(partialType, id.b), id.a, init);
	}

	final public Pair<SimpleName, List<ArrayBracketPair>> VariableDeclaratorId() throws ParseException {
		SimpleName name;
		JavaToken begin;
		ArrayBracketPair arrayBracketPair;
		List<ArrayBracketPair> arrayBracketPairs = new ArrayList(0);
		name = SimpleName();
		begin = token();
		label_13:
		while (true) {
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case LBRACKET:
				case AT: {
					;
					break;
				}
				default:
					jj_la1[32] = jj_gen;
					break label_13;
			}
			arrayBracketPair = ArrayBracketPair();
			arrayBracketPairs = add(arrayBracketPairs, arrayBracketPair);
		}
		name.setTokenRange(name.getTokenRange().get().withEnd(token()));
		return new Pair(name, arrayBracketPairs);
	}

	final public Expression VariableInitializer() throws ParseException {
		Expression ret;
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case LBRACE: {
				ret = ArrayInitializer();
				break;
			}
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FALSE:
			case FLOAT:
			case INT:
			case LONG:
			case NEW:
			case NULL:
			case SHORT:
			case SUPER:
			case THIS:
			case TRUE:
			case VOID:
			case REQUIRES:
			case TO:
			case WITH:
			case OPEN:
			case OPENS:
			case USES:
			case MODULE:
			case EXPORTS:
			case PROVIDES:
			case TRANSITIVE:
			case LONG_LITERAL:
			case INTEGER_LITERAL:
			case FLOATING_POINT_LITERAL:
			case CHARACTER_LITERAL:
			case STRING_LITERAL:
			case IDENTIFIER:
			case LPAREN:
			case BANG:
			case TILDE:
			case INCR:
			case DECR:
			case PLUS:
			case MINUS: {
				ret = Expression();
				break;
			}
			default:
				jj_la1[33] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
		}
		return ret;
	}

	final public ArrayInitializerExpr ArrayInitializer() throws ParseException {
		NodeList<Expression> values = emptyList();
		Expression val;
		JavaToken begin;
		jj_consume_token(LBRACE);
		begin = token();
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FALSE:
			case FLOAT:
			case INT:
			case LONG:
			case NEW:
			case NULL:
			case SHORT:
			case SUPER:
			case THIS:
			case TRUE:
			case VOID:
			case REQUIRES:
			case TO:
			case WITH:
			case OPEN:
			case OPENS:
			case USES:
			case MODULE:
			case EXPORTS:
			case PROVIDES:
			case TRANSITIVE:
			case LONG_LITERAL:
			case INTEGER_LITERAL:
			case FLOATING_POINT_LITERAL:
			case CHARACTER_LITERAL:
			case STRING_LITERAL:
			case IDENTIFIER:
			case LPAREN:
			case LBRACE:
			case BANG:
			case TILDE:
			case INCR:
			case DECR:
			case PLUS:
			case MINUS: {
				val = VariableInitializer();
				values = add(values, val);
				label_14:
				while (true) {
					if (jj_2_9(2)) {
						;
					} else {
						break label_14;
					}
					jj_consume_token(COMMA);
					val = VariableInitializer();
					values = add(values, val);
				}
				break;
			}
			default:
				jj_la1[34] = jj_gen;
				;
		}
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case COMMA: {
				jj_consume_token(COMMA);
				break;
			}
			default:
				jj_la1[35] = jj_gen;
				;
		}
		jj_consume_token(RBRACE);
		return new ArrayInitializerExpr(range(begin, token()), values);
	}

	final public MethodDeclaration MethodDeclaration(ModifierHolder modifier) throws ParseException {
		RangedList<TypeParameter> typeParameters = new RangedList<TypeParameter>(emptyList());
		Type type;
		SimpleName name;
		NodeList<Parameter> parameters = emptyList();
		ArrayBracketPair arrayBracketPair;
		List<ArrayBracketPair> arrayBracketPairs = new ArrayList(0);
		NodeList<ReferenceType> throws_ = emptyList();
		BlockStmt body = null;
		NodeList<AnnotationExpr> annotations;
		JavaToken begin = modifier.begin;
		ReferenceType throwType;
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case LT: {
				typeParameters = TypeParameters();
				begin = begin.orIfInvalid(typeParameters.range.getBegin());
				break;
			}
			default:
				jj_la1[36] = jj_gen;
				;
		}
		annotations = Annotations();
		modifier.annotations.addAll(annotations);
		begin = begin.orIfInvalid(nodeListBegin(annotations));
		type = ResultType();
		begin = begin.orIfInvalid(type.getTokenRange().get().getBegin());
		name = SimpleName();
		parameters = Parameters();
		label_15:
		while (true) {
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case LBRACKET:
				case AT: {
					;
					break;
				}
				default:
					jj_la1[37] = jj_gen;
					break label_15;
			}
			arrayBracketPair = ArrayBracketPair();
			arrayBracketPairs = add(arrayBracketPairs, arrayBracketPair);
		}
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case THROWS: {
				jj_consume_token(THROWS);
				throwType = ReferenceTypeWithAnnotations();
				throws_ = add(throws_, throwType);
				label_16:
				while (true) {
					switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
						case COMMA: {
							;
							break;
						}
						default:
							jj_la1[38] = jj_gen;
							break label_16;
					}
					jj_consume_token(COMMA);
					throwType = ReferenceTypeWithAnnotations();
					throws_ = add(throws_, throwType);
				}
				break;
			}
			default:
				jj_la1[39] = jj_gen;
				;
		}
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case LBRACE: {
				body = Block();
				break;
			}
			case SEMICOLON: {
				jj_consume_token(SEMICOLON);
				break;
			}
			default:
				jj_la1[40] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
		}
		type = juggleArrayType(type, arrayBracketPairs);
		return new MethodDeclaration(range(begin, token()), modifier.modifiers, modifier.annotations, typeParameters.list, type, name, parameters, throws_, body);
	}

	final public ReferenceType ReferenceTypeWithAnnotations() throws ParseException {
		NodeList<AnnotationExpr> annotations = new NodeList<AnnotationExpr>();
		ReferenceType type;
		annotations = Annotations();
		type = ReferenceType();
		if (annotations != null) {
			if (type.getAnnotations() != null) {
				type.getAnnotations().addAll(annotations);
			} else {
				type.setAnnotations(annotations);
			}
		}
		return type;
	}

	final public NodeList<Parameter> Parameters() throws ParseException {
		NodeList<Parameter> ret = emptyList();
		Parameter par;
		jj_consume_token(LPAREN);
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case ABSTRACT:
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case _DEFAULT:
			case DOUBLE:
			case FINAL:
			case FLOAT:
			case INT:
			case LONG:
			case NATIVE:
			case PRIVATE:
			case PROTECTED:
			case PUBLIC:
			case SHORT:
			case STATIC:
			case STRICTFP:
			case SYNCHRONIZED:
			case TRANSIENT:
			case VOLATILE:
			case REQUIRES:
			case TO:
			case WITH:
			case OPEN:
			case OPENS:
			case USES:
			case MODULE:
			case EXPORTS:
			case PROVIDES:
			case TRANSITIVE:
			case IDENTIFIER:
			case AT: {
				par = Parameter();
				ret = add(ret, par);
				label_17:
				while (true) {
					switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
						case COMMA: {
							;
							break;
						}
						default:
							jj_la1[41] = jj_gen;
							break label_17;
					}
					jj_consume_token(COMMA);
					par = Parameter();
					ret = add(ret, par);
				}
				break;
			}
			default:
				jj_la1[42] = jj_gen;
				;
		}
		jj_consume_token(RPAREN);
		return ret;
	}

	final public NodeList<Parameter> LambdaParameters() throws ParseException {
		NodeList<Parameter> ret = null;
		Parameter par;
		jj_consume_token(COMMA);
		par = Parameter();
		ret = add(ret, par);
		label_18:
		while (true) {
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case COMMA: {
					;
					break;
				}
				default:
					jj_la1[43] = jj_gen;
					break label_18;
			}
			jj_consume_token(COMMA);
			par = Parameter();
			ret = add(ret, par);
		}
		return ret;
	}

	final public NodeList<Parameter> InferredLambdaParameters() throws ParseException {
		NodeList<Parameter> ret = null;
		Pair<SimpleName, List<ArrayBracketPair>> id;
		jj_consume_token(COMMA);
		id = VariableDeclaratorId();
		ret = add(ret, new Parameter(range(id.a.getTokenRange().get().getBegin(), id.a.getTokenRange().get().getEnd()), EnumSet.noneOf(Modifier.class), emptyList(),
				new UnknownType(), false, emptyList(), id.a));
		label_19:
		while (true) {
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case COMMA: {
					;
					break;
				}
				default:
					jj_la1[44] = jj_gen;
					break label_19;
			}
			jj_consume_token(COMMA);
			id = VariableDeclaratorId();
			ret = add(ret, new Parameter(range(id.a.getTokenRange().get().getBegin(), id.a.getTokenRange().get().getEnd()), EnumSet.noneOf(Modifier.class), emptyList(),
					new UnknownType(), false, emptyList(), id.a));
		}
		return ret;
	}

	final public Parameter Parameter() throws ParseException {
		ModifierHolder modifier;
		Type partialType;
		boolean isVarArg = false;
		Pair<SimpleName, List<ArrayBracketPair>> id;
		SimpleName receiverId;
		NodeList<AnnotationExpr> varArgAnnotations = emptyList();
		Parameter ret;
		modifier = Modifiers();
		partialType = Type();
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case AT:
			case ELLIPSIS: {
				varArgAnnotations = Annotations();
				jj_consume_token(ELLIPSIS);
				isVarArg = true;
				break;
			}
			default:
				jj_la1[45] = jj_gen;
				;
		}
		if (jj_2_10(2147483647)) {
			receiverId = ReceiverParameterId();
			JavaToken begin = modifier.begin.orIfInvalid(partialType.getTokenRange().get().getBegin());
			ret = new Parameter(range(begin, token()), modifier.modifiers, modifier.annotations, partialType, isVarArg, varArgAnnotations, receiverId);
		} else {
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case REQUIRES:
				case TO:
				case WITH:
				case OPEN:
				case OPENS:
				case USES:
				case MODULE:
				case EXPORTS:
				case PROVIDES:
				case TRANSITIVE:
				case IDENTIFIER: {
					id = VariableDeclaratorId();
					JavaToken begin = modifier.begin.orIfInvalid(partialType.getTokenRange().get().getBegin());
					ret = new Parameter(range(begin, token()), modifier.modifiers, modifier.annotations, juggleArrayType(partialType, id.b), isVarArg, varArgAnnotations, id.a);
					break;
				}
				default:
					jj_la1[46] = jj_gen;
					jj_consume_token(-1);
					throw new ParseException();
			}
		}
		return ret;
	}

	final public SimpleName ReceiverParameterId() throws ParseException {
		String id;
		String ret = "";
		NodeList<AnnotationExpr> annotations = new NodeList<AnnotationExpr>();
		JavaToken begin = INVALID;
		label_20:
		while (true) {
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case REQUIRES:
				case TO:
				case WITH:
				case OPEN:
				case OPENS:
				case USES:
				case MODULE:
				case EXPORTS:
				case PROVIDES:
				case TRANSITIVE:
				case IDENTIFIER: {
					;
					break;
				}
				default:
					jj_la1[47] = jj_gen;
					break label_20;
			}
			id = Identifier();
			jj_consume_token(DOT);
			ret = ret + id + ".";
			begin = begin.orIfInvalid(token());
		}
		jj_consume_token(THIS);
		begin = begin.orIfInvalid(token());
		return new SimpleName(range(begin, token()), ret + "this");
	}

	final public ConstructorDeclaration ConstructorDeclaration(ModifierHolder modifier) throws ParseException {
		RangedList<TypeParameter> typeParameters = new RangedList<TypeParameter>(emptyList());
		SimpleName name;
		NodeList<Parameter> parameters = emptyList();
		NodeList<ReferenceType> throws_ = emptyList();
		ExplicitConstructorInvocationStmt exConsInv = null;
		NodeList<Statement> stmts = emptyList();
		JavaToken begin = modifier.begin;
		JavaToken blockBegin = INVALID;
		ReferenceType throwType;
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case LT: {
				typeParameters = TypeParameters();
				begin = begin.orIfInvalid(typeParameters.range.getBegin());
				break;
			}
			default:
				jj_la1[48] = jj_gen;
				;
		}
		// Modifiers matched in the caller
		name = SimpleName();
		begin = begin.orIfInvalid(typeParameters.range.getBegin());
		begin = begin.orIfInvalid(token());
		parameters = Parameters();
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case THROWS: {
				jj_consume_token(THROWS);
				throwType = ReferenceTypeWithAnnotations();
				throws_ = add(throws_, throwType);
				label_21:
				while (true) {
					switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
						case COMMA: {
							;
							break;
						}
						default:
							jj_la1[49] = jj_gen;
							break label_21;
					}
					jj_consume_token(COMMA);
					throwType = ReferenceTypeWithAnnotations();
					throws_ = add(throws_, throwType);
				}
				break;
			}
			default:
				jj_la1[50] = jj_gen;
				;
		}
		jj_consume_token(LBRACE);
		blockBegin = token();
		if (jj_2_11(2147483647)) {
			exConsInv = ExplicitConstructorInvocation();
		} else {
			;
		}
		stmts = Statements();
		jj_consume_token(RBRACE);
		if (exConsInv != null) {
			stmts = add(0, stmts, exConsInv);
		}
		return new ConstructorDeclaration(range(begin, token()), modifier.modifiers, modifier.annotations, typeParameters.list, name, parameters, throws_,
				new BlockStmt(range(blockBegin, token()), stmts));
	}

	final public ExplicitConstructorInvocationStmt ExplicitConstructorInvocation() throws ParseException {
		boolean isThis = false;
		NodeList<Expression> args;
		Expression expr = null;
		RangedList<Type> typeArgs = new RangedList<Type>(null);
		JavaToken begin = INVALID;
		if (jj_2_13(2147483647)) {
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case LT: {
					typeArgs = TypeArguments();
					begin = typeArgs.range.getBegin();
					break;
				}
				default:
					jj_la1[51] = jj_gen;
					;
			}
			jj_consume_token(THIS);
			begin = begin.orIfInvalid(token());
			isThis = true;
			args = Arguments();
			jj_consume_token(SEMICOLON);
		} else {
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case BOOLEAN:
				case BYTE:
				case CHAR:
				case DOUBLE:
				case FALSE:
				case FLOAT:
				case INT:
				case LONG:
				case NEW:
				case NULL:
				case SHORT:
				case SUPER:
				case THIS:
				case TRUE:
				case VOID:
				case REQUIRES:
				case TO:
				case WITH:
				case OPEN:
				case OPENS:
				case USES:
				case MODULE:
				case EXPORTS:
				case PROVIDES:
				case TRANSITIVE:
				case LONG_LITERAL:
				case INTEGER_LITERAL:
				case FLOATING_POINT_LITERAL:
				case CHARACTER_LITERAL:
				case STRING_LITERAL:
				case IDENTIFIER:
				case LPAREN:
				case LT: {
					if (jj_2_12(2147483647)) {
						expr = PrimaryExpressionWithoutSuperSuffix();
						jj_consume_token(DOT);
						begin = expr.getTokenRange().get().getBegin();
					} else {
						;
					}
					switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
						case LT: {
							typeArgs = TypeArguments();
							begin = begin.orIfInvalid(typeArgs.range.getBegin());
							break;
						}
						default:
							jj_la1[52] = jj_gen;
							;
					}
					jj_consume_token(SUPER);
					begin = begin.orIfInvalid(token());
					args = Arguments();
					jj_consume_token(SEMICOLON);
					break;
				}
				default:
					jj_la1[53] = jj_gen;
					jj_consume_token(-1);
					throw new ParseException();
			}
		}
		return new ExplicitConstructorInvocationStmt(range(begin, token()), typeArgs.list, isThis, expr, args);
	}

	final public NodeList<Statement> Statements() throws ParseException {
		NodeList<Statement> ret = emptyList();
		Statement stmt;
		label_22:
		while (true) {
			if (jj_2_14(2)) {
				;
			} else {
				break label_22;
			}
			stmt = BlockStatement();
			ret = add(ret, stmt);
		}
		return ret;
	}

	final public InitializerDeclaration InitializerDeclaration() throws ParseException {
		BlockStmt body;
		JavaToken begin = INVALID;
		boolean isStatic = false;
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case STATIC: {
				jj_consume_token(STATIC);
				isStatic = true;
				begin = token();
				break;
			}
			default:
				jj_la1[54] = jj_gen;
				;
		}
		body = Block();
		begin = begin.orIfInvalid(body.getTokenRange().get().getBegin());
		return new InitializerDeclaration(range(begin, token()), isStatic, body);
	}

	/*
	 * Type, name and expression syntax follows.
	 */
	final public Type Type() throws ParseException {
		Type ret;
		if (jj_2_15(2)) {
			ret = ReferenceType();
		} else {
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case BOOLEAN:
				case BYTE:
				case CHAR:
				case DOUBLE:
				case FLOAT:
				case INT:
				case LONG:
				case SHORT: {
					ret = PrimitiveType();
					break;
				}
				default:
					jj_la1[55] = jj_gen;
					jj_consume_token(-1);
					throw new ParseException();
			}
		}
		return ret;
	}

	final public ReferenceType ReferenceType() throws ParseException {
		Type type;
		ArrayBracketPair arrayBracketPair;
		List<ArrayBracketPair> arrayBracketPairs = new ArrayList(0);
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FLOAT:
			case INT:
			case LONG:
			case SHORT: {
				type = PrimitiveType();
				label_23:
				while (true) {
					arrayBracketPair = ArrayBracketPair();
					arrayBracketPairs = add(arrayBracketPairs, arrayBracketPair);
					if (jj_2_16(2147483647)) {
						;
					} else {
						break label_23;
					}
				}
				break;
			}
			case REQUIRES:
			case TO:
			case WITH:
			case OPEN:
			case OPENS:
			case USES:
			case MODULE:
			case EXPORTS:
			case PROVIDES:
			case TRANSITIVE:
			case IDENTIFIER: {
				type = ClassOrInterfaceType();
				label_24:
				while (true) {
					if (jj_2_17(2147483647)) {
						;
					} else {
						break label_24;
					}
					arrayBracketPair = ArrayBracketPair();
					arrayBracketPairs = add(arrayBracketPairs, arrayBracketPair);
				}
				break;
			}
			default:
				jj_la1[56] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
		}
		return (ReferenceType) wrapInArrayTypes(type, arrayBracketPairs);
	}

	final public ArrayBracketPair ArrayBracketPair() throws ParseException {
		NodeList<AnnotationExpr> annotations = new NodeList<AnnotationExpr>();
		JavaToken begin = INVALID;
		annotations = Annotations();
		jj_consume_token(LBRACKET);
		begin = begin.orIfInvalid(token());
		jj_consume_token(RBRACKET);
		return new ArrayBracketPair(range(begin, token()), annotations);
	}

	final public IntersectionType IntersectionType() throws ParseException {
		JavaToken begin;
		ReferenceType elementType;
		NodeList<ReferenceType> elements = emptyList();
		elementType = ReferenceType();
		begin = elementType.getTokenRange().get().getBegin();
		elements = add(elements, elementType);
		jj_consume_token(BIT_AND);
		label_25:
		while (true) {
			elementType = ReferenceType();
			elements = add(elements, elementType);
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case BOOLEAN:
				case BYTE:
				case CHAR:
				case DOUBLE:
				case FLOAT:
				case INT:
				case LONG:
				case SHORT:
				case REQUIRES:
				case TO:
				case WITH:
				case OPEN:
				case OPENS:
				case USES:
				case MODULE:
				case EXPORTS:
				case PROVIDES:
				case TRANSITIVE:
				case IDENTIFIER: {
					;
					break;
				}
				default:
					jj_la1[57] = jj_gen;
					break label_25;
			}
		}
		return new IntersectionType(range(begin, token()), elements);
	}

	final public ClassOrInterfaceType AnnotatedClassOrInterfaceType() throws ParseException {
		NodeList<AnnotationExpr> annotations = new NodeList<AnnotationExpr>();
		ClassOrInterfaceType cit;
		annotations = Annotations();
		cit = ClassOrInterfaceType();
		if (cit.getScope().isPresent()) {
			cit.getScope().get().setAnnotations(annotations);
		} else {
			cit.setAnnotations(annotations);
		}
		return cit;
	}

	final public ClassOrInterfaceType ClassOrInterfaceType() throws ParseException {
		ClassOrInterfaceType ret;
		SimpleName name;
		RangedList<Type> typeArgs = new RangedList<Type>(null);
		JavaToken begin;
		NodeList<AnnotationExpr> annotations = new NodeList<AnnotationExpr>();
		name = SimpleName();
		begin = token();
		if (jj_2_18(2)) {
			typeArgs = TypeArguments();
		} else {
			;
		}
		ret = new ClassOrInterfaceType(range(begin, token()), null, name, typeArgs.list, annotations);
		label_26:
		while (true) {
			if (jj_2_19(2)) {
				;
			} else {
				break label_26;
			}
			jj_consume_token(DOT);
			annotations = Annotations();
			name = SimpleName();
			if (jj_2_20(2)) {
				typeArgs = TypeArguments();
			} else {
				;
			}
			ret = new ClassOrInterfaceType(range(begin, token()), ret, name, typeArgs.list, annotations);
			annotations = null;
		}
		return ret;
	}

	final public RangedList<Type> TypeArguments() throws ParseException {
		RangedList<Type> ret = new RangedList<Type>(new NodeList<Type>());
		Type type;
		jj_consume_token(LT);
		ret.beginAt(token());
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FLOAT:
			case INT:
			case LONG:
			case SHORT:
			case REQUIRES:
			case TO:
			case WITH:
			case OPEN:
			case OPENS:
			case USES:
			case MODULE:
			case EXPORTS:
			case PROVIDES:
			case TRANSITIVE:
			case IDENTIFIER:
			case AT:
			case HOOK: {
				type = TypeArgument();
				ret.add(type);
				label_27:
				while (true) {
					switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
						case COMMA: {
							;
							break;
						}
						default:
							jj_la1[58] = jj_gen;
							break label_27;
					}
					jj_consume_token(COMMA);
					type = TypeArgument();
					ret.add(type);
				}
				break;
			}
			default:
				jj_la1[59] = jj_gen;
				;
		}
		jj_consume_token(GT);
		ret.endAt(token());
		return ret;
	}

	final public Type TypeArgument() throws ParseException {
		Type ret;
		NodeList<AnnotationExpr> annotations = new NodeList<AnnotationExpr>();
		annotations = Annotations();
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FLOAT:
			case INT:
			case LONG:
			case SHORT:
			case REQUIRES:
			case TO:
			case WITH:
			case OPEN:
			case OPENS:
			case USES:
			case MODULE:
			case EXPORTS:
			case PROVIDES:
			case TRANSITIVE:
			case IDENTIFIER: {
				ret = Type();
				break;
			}
			case HOOK: {
				ret = Wildcard();
				break;
			}
			default:
				jj_la1[60] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
		}
		ret.setAnnotations(annotations);
		return ret;
	}

	final public WildcardType Wildcard() throws ParseException {
		ReferenceType ext = null;
		ReferenceType sup = null;
		JavaToken begin;
		NodeList<AnnotationExpr> annotations = new NodeList<AnnotationExpr>();
		jj_consume_token(HOOK);
		begin = token();
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case EXTENDS:
			case SUPER: {
				switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
					case EXTENDS: {
						jj_consume_token(EXTENDS);
						annotations = Annotations();
						ext = ReferenceType();
						ext.setAnnotations(annotations);
						break;
					}
					case SUPER: {
						jj_consume_token(SUPER);
						annotations = Annotations();
						sup = ReferenceType();
						sup.setAnnotations(annotations);
						break;
					}
					default:
						jj_la1[61] = jj_gen;
						jj_consume_token(-1);
						throw new ParseException();
				}
				break;
			}
			default:
				jj_la1[62] = jj_gen;
				;
		}
		return new WildcardType(range(begin, token()), ext, sup);
	}

	final public PrimitiveType PrimitiveType() throws ParseException {
		PrimitiveType ret;
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case BOOLEAN: {
				jj_consume_token(BOOLEAN);
				ret = new PrimitiveType(tokenRange(), PrimitiveType.Primitive.BOOLEAN);
				break;
			}
			case CHAR: {
				jj_consume_token(CHAR);
				ret = new PrimitiveType(tokenRange(), PrimitiveType.Primitive.CHAR);
				break;
			}
			case BYTE: {
				jj_consume_token(BYTE);
				ret = new PrimitiveType(tokenRange(), PrimitiveType.Primitive.BYTE);
				break;
			}
			case SHORT: {
				jj_consume_token(SHORT);
				ret = new PrimitiveType(tokenRange(), PrimitiveType.Primitive.SHORT);
				break;
			}
			case INT: {
				jj_consume_token(INT);
				ret = new PrimitiveType(tokenRange(), PrimitiveType.Primitive.INT);
				break;
			}
			case LONG: {
				jj_consume_token(LONG);
				ret = new PrimitiveType(tokenRange(), PrimitiveType.Primitive.LONG);
				break;
			}
			case FLOAT: {
				jj_consume_token(FLOAT);
				ret = new PrimitiveType(tokenRange(), PrimitiveType.Primitive.FLOAT);
				break;
			}
			case DOUBLE: {
				jj_consume_token(DOUBLE);
				ret = new PrimitiveType(tokenRange(), PrimitiveType.Primitive.DOUBLE);
				break;
			}
			default:
				jj_la1[63] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
		}
		return ret;
	}

	final public Type ResultType() throws ParseException {
		Type ret;
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case VOID: {
				jj_consume_token(VOID);
				ret = new VoidType(tokenRange());
				break;
			}
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FLOAT:
			case INT:
			case LONG:
			case SHORT:
			case REQUIRES:
			case TO:
			case WITH:
			case OPEN:
			case OPENS:
			case USES:
			case MODULE:
			case EXPORTS:
			case PROVIDES:
			case TRANSITIVE:
			case IDENTIFIER: {
				ret = Type();
				break;
			}
			default:
				jj_la1[64] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
		}
		return ret;
	}

	final public Name Name() throws ParseException {
		Name ret;
		NodeList<AnnotationExpr> annotations = new NodeList<AnnotationExpr>();
		annotations = Annotations();
		Identifier();
		ret = new Name(tokenRange(), null, token.image, annotations);
		label_28:
		while (true) {
			if (jj_2_21(2147483647)) {
				;
			} else {
				break label_28;
			}
			jj_consume_token(DOT);
			annotations = Annotations();
			Identifier();
			ret = new Name(range(ret.getTokenRange().get().getBegin(), token()), ret, token.image, annotations);
		}
		return ret;
	}

	final public SimpleName SimpleName() throws ParseException {
		SimpleName ret;
		Identifier();
		ret = new SimpleName(tokenRange(), token.image);
		return ret;
	}

	final public String Identifier() throws ParseException {
		String ret;
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case MODULE: {
				jj_consume_token(MODULE);
				break;
			}
			case REQUIRES: {
				jj_consume_token(REQUIRES);
				break;
			}
			case TO: {
				jj_consume_token(TO);
				break;
			}
			case WITH: {
				jj_consume_token(WITH);
				break;
			}
			case OPEN: {
				jj_consume_token(OPEN);
				break;
			}
			case OPENS: {
				jj_consume_token(OPENS);
				break;
			}
			case USES: {
				jj_consume_token(USES);
				break;
			}
			case EXPORTS: {
				jj_consume_token(EXPORTS);
				break;
			}
			case PROVIDES: {
				jj_consume_token(PROVIDES);
				break;
			}
			case TRANSITIVE: {
				jj_consume_token(TRANSITIVE);
				break;
			}
			case IDENTIFIER: {
				jj_consume_token(IDENTIFIER);
				break;
			}
			default:
				jj_la1[65] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
		}
		ret = token.image;
		return ret;
	}

	/*
	 * Expression syntax follows.
	 */
	final public Expression Expression() throws ParseException {
		Expression ret;
		AssignExpr.Operator op;
		Expression value;
		Statement lambdaBody = null;
		RangedList<Type> typeArgs = new RangedList<Type>(null);
		ret = ConditionalExpression();
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case ASSIGN:
			case PLUSASSIGN:
			case MINUSASSIGN:
			case STARASSIGN:
			case SLASHASSIGN:
			case ANDASSIGN:
			case ORASSIGN:
			case XORASSIGN:
			case REMASSIGN:
			case LSHIFTASSIGN:
			case RSIGNEDSHIFTASSIGN:
			case RUNSIGNEDSHIFTASSIGN:
			case ARROW:
			case DOUBLECOLON: {
				if (jj_2_22(2)) {
					op = AssignmentOperator();
					value = Expression();
					ret = new AssignExpr(range(ret.getTokenRange().get().getBegin(), token()), ret, value, op);
				} else {
					switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
						case ARROW: {
							jj_consume_token(ARROW);
							lambdaBody = LambdaBody();
							if (ret instanceof CastExpr) {
								ret = generateLambda(this, ret, lambdaBody);
							} else if (ret instanceof ConditionalExpr) {
								ConditionalExpr ce = (ConditionalExpr) ret;
								if (ce.getElseExpr() != null) {
									ce.setElseExpr(generateLambda(this, ce.getElseExpr(), lambdaBody));
								}
							} else {
								ret = generateLambda(this, ret, lambdaBody);
							}
							break;
						}
						case DOUBLECOLON: {
							jj_consume_token(DOUBLECOLON);
							switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
								case LT: {
									typeArgs = TypeArguments();
									break;
								}
								default:
									jj_la1[66] = jj_gen;
									;
							}
							switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
								case REQUIRES:
								case TO:
								case WITH:
								case OPEN:
								case OPENS:
								case USES:
								case MODULE:
								case EXPORTS:
								case PROVIDES:
								case TRANSITIVE:
								case IDENTIFIER: {
									Identifier();
									break;
								}
								case NEW: {
									jj_consume_token(NEW);
									break;
								}
								default:
									jj_la1[67] = jj_gen;
									jj_consume_token(-1);
									throw new ParseException();
							}
							ret = new MethodReferenceExpr(range(ret.getTokenRange().get().getBegin(), token()), ret, typeArgs.list, token.image);
							break;
						}
						default:
							jj_la1[68] = jj_gen;
							jj_consume_token(-1);
							throw new ParseException();
					}
				}
				break;
			}
			default:
				jj_la1[69] = jj_gen;
				;
		}
		return ret;
	}

	final public AssignExpr.Operator AssignmentOperator() throws ParseException {
		AssignExpr.Operator ret;
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case ASSIGN: {
				jj_consume_token(ASSIGN);
				ret = AssignExpr.Operator.ASSIGN;
				break;
			}
			case STARASSIGN: {
				jj_consume_token(STARASSIGN);
				ret = AssignExpr.Operator.MULTIPLY;
				break;
			}
			case SLASHASSIGN: {
				jj_consume_token(SLASHASSIGN);
				ret = AssignExpr.Operator.DIVIDE;
				break;
			}
			case REMASSIGN: {
				jj_consume_token(REMASSIGN);
				ret = AssignExpr.Operator.REMAINDER;
				break;
			}
			case PLUSASSIGN: {
				jj_consume_token(PLUSASSIGN);
				ret = AssignExpr.Operator.PLUS;
				break;
			}
			case MINUSASSIGN: {
				jj_consume_token(MINUSASSIGN);
				ret = AssignExpr.Operator.MINUS;
				break;
			}
			case LSHIFTASSIGN: {
				jj_consume_token(LSHIFTASSIGN);
				ret = AssignExpr.Operator.LEFT_SHIFT;
				break;
			}
			case RSIGNEDSHIFTASSIGN: {
				jj_consume_token(RSIGNEDSHIFTASSIGN);
				ret = AssignExpr.Operator.SIGNED_RIGHT_SHIFT;
				break;
			}
			case RUNSIGNEDSHIFTASSIGN: {
				jj_consume_token(RUNSIGNEDSHIFTASSIGN);
				ret = AssignExpr.Operator.UNSIGNED_RIGHT_SHIFT;
				break;
			}
			case ANDASSIGN: {
				jj_consume_token(ANDASSIGN);
				ret = AssignExpr.Operator.AND;
				break;
			}
			case XORASSIGN: {
				jj_consume_token(XORASSIGN);
				ret = AssignExpr.Operator.XOR;
				break;
			}
			case ORASSIGN: {
				jj_consume_token(ORASSIGN);
				ret = AssignExpr.Operator.OR;
				break;
			}
			default:
				jj_la1[70] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
		}
		return ret;
	}

	final public Expression ConditionalExpression() throws ParseException {
		Expression ret;
		Expression left;
		Expression right;
		ret = ConditionalOrExpression();
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case HOOK: {
				jj_consume_token(HOOK);
				left = Expression();
				jj_consume_token(COLON);
				right = ConditionalExpression();
				ret = new ConditionalExpr(range(ret.getTokenRange().get().getBegin(), token()), ret, left, right);
				break;
			}
			default:
				jj_la1[71] = jj_gen;
				;
		}
		return ret;
	}

	final public Expression ConditionalOrExpression() throws ParseException {
		Expression ret;
		Expression right;
		ret = ConditionalAndExpression();
		label_29:
		while (true) {
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case SC_OR: {
					;
					break;
				}
				default:
					jj_la1[72] = jj_gen;
					break label_29;
			}
			jj_consume_token(SC_OR);
			right = ConditionalAndExpression();
			ret = new BinaryExpr(range(ret.getTokenRange().get().getBegin(), token()), ret, right, BinaryExpr.Operator.OR);
		}
		return ret;
	}

	final public Expression ConditionalAndExpression() throws ParseException {
		Expression ret;
		Expression right;
		ret = InclusiveOrExpression();
		label_30:
		while (true) {
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case SC_AND: {
					;
					break;
				}
				default:
					jj_la1[73] = jj_gen;
					break label_30;
			}
			jj_consume_token(SC_AND);
			right = InclusiveOrExpression();
			ret = new BinaryExpr(range(ret.getTokenRange().get().getBegin(), token()), ret, right, BinaryExpr.Operator.AND);
		}
		return ret;
	}

	final public Expression InclusiveOrExpression() throws ParseException {
		Expression ret;
		Expression right;
		ret = ExclusiveOrExpression();
		label_31:
		while (true) {
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case BIT_OR: {
					;
					break;
				}
				default:
					jj_la1[74] = jj_gen;
					break label_31;
			}
			jj_consume_token(BIT_OR);
			right = ExclusiveOrExpression();
			ret = new BinaryExpr(range(ret.getTokenRange().get().getBegin(), token()), ret, right, BinaryExpr.Operator.BINARY_OR);
		}
		return ret;
	}

	final public Expression ExclusiveOrExpression() throws ParseException {
		Expression ret;
		Expression right;
		ret = AndExpression();
		label_32:
		while (true) {
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case XOR: {
					;
					break;
				}
				default:
					jj_la1[75] = jj_gen;
					break label_32;
			}
			jj_consume_token(XOR);
			right = AndExpression();
			ret = new BinaryExpr(range(ret.getTokenRange().get().getBegin(), token()), ret, right, BinaryExpr.Operator.XOR);
		}
		return ret;
	}

	final public Expression AndExpression() throws ParseException {
		Expression ret;
		Expression right;
		ret = EqualityExpression();
		label_33:
		while (true) {
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case BIT_AND: {
					;
					break;
				}
				default:
					jj_la1[76] = jj_gen;
					break label_33;
			}
			jj_consume_token(BIT_AND);
			right = EqualityExpression();
			ret = new BinaryExpr(range(ret.getTokenRange().get().getBegin(), token()), ret, right, BinaryExpr.Operator.BINARY_AND);
		}
		return ret;
	}

	final public Expression EqualityExpression() throws ParseException {
		Expression ret;
		Expression right;
		BinaryExpr.Operator op;
		ret = InstanceOfExpression();
		label_34:
		while (true) {
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case EQ:
				case NE: {
					;
					break;
				}
				default:
					jj_la1[77] = jj_gen;
					break label_34;
			}
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case EQ: {
					jj_consume_token(EQ);
					op = BinaryExpr.Operator.EQUALS;
					break;
				}
				case NE: {
					jj_consume_token(NE);
					op = BinaryExpr.Operator.NOT_EQUALS;
					break;
				}
				default:
					jj_la1[78] = jj_gen;
					jj_consume_token(-1);
					throw new ParseException();
			}
			right = InstanceOfExpression();
			ret = new BinaryExpr(range(ret.getTokenRange().get().getBegin(), token()), ret, right, op);
		}
		return ret;
	}

	final public Expression InstanceOfExpression() throws ParseException {
		Expression ret;
		ReferenceType type;
		NodeList<AnnotationExpr> annotations;
		ret = RelationalExpression();
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case INSTANCEOF: {
				jj_consume_token(INSTANCEOF);
				annotations = Annotations();
				type = ReferenceType();
				type.getAnnotations().addAll(annotations);
				ret = new InstanceOfExpr(range(ret.getTokenRange().get().getBegin(), token()), ret, type);
				break;
			}
			default:
				jj_la1[79] = jj_gen;
				;
		}
		return ret;
	}

	final public Expression RelationalExpression() throws ParseException {
		Expression ret;
		Expression right;
		BinaryExpr.Operator op;
		ret = ShiftExpression();
		label_35:
		while (true) {
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case LT:
				case LE:
				case GE:
				case GT: {
					;
					break;
				}
				default:
					jj_la1[80] = jj_gen;
					break label_35;
			}
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case LT: {
					jj_consume_token(LT);
					op = BinaryExpr.Operator.LESS;
					break;
				}
				case GT: {
					jj_consume_token(GT);
					op = BinaryExpr.Operator.GREATER;
					break;
				}
				case LE: {
					jj_consume_token(LE);
					op = BinaryExpr.Operator.LESS_EQUALS;
					break;
				}
				case GE: {
					jj_consume_token(GE);
					op = BinaryExpr.Operator.GREATER_EQUALS;
					break;
				}
				default:
					jj_la1[81] = jj_gen;
					jj_consume_token(-1);
					throw new ParseException();
			}
			right = ShiftExpression();
			ret = new BinaryExpr(range(ret.getTokenRange().get().getBegin(), token()), ret, right, op);
		}
		return ret;
	}

	final public Expression ShiftExpression() throws ParseException {
		Expression ret;
		Expression right;
		BinaryExpr.Operator op;
		ret = AdditiveExpression();
		label_36:
		while (true) {
			if (jj_2_23(1)) {
				;
			} else {
				break label_36;
			}
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case LSHIFT: {
					jj_consume_token(LSHIFT);
					op = BinaryExpr.Operator.LEFT_SHIFT;
					break;
				}
				default:
					jj_la1[82] = jj_gen;
					if (jj_2_24(1)) {
						RSIGNEDSHIFT();
						op = BinaryExpr.Operator.SIGNED_RIGHT_SHIFT;
					} else if (jj_2_25(1)) {
						RUNSIGNEDSHIFT();
						op = BinaryExpr.Operator.UNSIGNED_RIGHT_SHIFT;
					} else {
						jj_consume_token(-1);
						throw new ParseException();
					}
			}
			right = AdditiveExpression();
			ret = new BinaryExpr(range(ret.getTokenRange().get().getBegin(), token()), ret, right, op);
		}
		return ret;
	}

	final public Expression AdditiveExpression() throws ParseException {
		Expression ret;
		Expression right;
		BinaryExpr.Operator op;
		ret = MultiplicativeExpression();
		label_37:
		while (true) {
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case PLUS:
				case MINUS: {
					;
					break;
				}
				default:
					jj_la1[83] = jj_gen;
					break label_37;
			}
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case PLUS: {
					jj_consume_token(PLUS);
					op = BinaryExpr.Operator.PLUS;
					break;
				}
				case MINUS: {
					jj_consume_token(MINUS);
					op = BinaryExpr.Operator.MINUS;
					break;
				}
				default:
					jj_la1[84] = jj_gen;
					jj_consume_token(-1);
					throw new ParseException();
			}
			right = MultiplicativeExpression();
			ret = new BinaryExpr(range(ret.getTokenRange().get().getBegin(), token()), ret, right, op);
		}
		return ret;
	}

	final public Expression MultiplicativeExpression() throws ParseException {
		Expression ret;
		Expression right;
		BinaryExpr.Operator op;
		ret = UnaryExpression();
		label_38:
		while (true) {
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case STAR:
				case SLASH:
				case REM: {
					;
					break;
				}
				default:
					jj_la1[85] = jj_gen;
					break label_38;
			}
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case STAR: {
					jj_consume_token(STAR);
					op = BinaryExpr.Operator.MULTIPLY;
					break;
				}
				case SLASH: {
					jj_consume_token(SLASH);
					op = BinaryExpr.Operator.DIVIDE;
					break;
				}
				case REM: {
					jj_consume_token(REM);
					op = BinaryExpr.Operator.REMAINDER;
					break;
				}
				default:
					jj_la1[86] = jj_gen;
					jj_consume_token(-1);
					throw new ParseException();
			}
			right = UnaryExpression();
			ret = new BinaryExpr(range(ret.getTokenRange().get().getBegin(), token()), ret, right, op);
		}
		return ret;
	}

	final public Expression UnaryExpression() throws ParseException {
		Expression ret;
		UnaryExpr.Operator op;
		JavaToken begin = INVALID;
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case INCR: {
				ret = PreIncrementExpression();
				break;
			}
			case DECR: {
				ret = PreDecrementExpression();
				break;
			}
			case PLUS:
			case MINUS: {
				switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
					case PLUS: {
						jj_consume_token(PLUS);
						op = UnaryExpr.Operator.PLUS;
						begin = token();
						break;
					}
					case MINUS: {
						jj_consume_token(MINUS);
						op = UnaryExpr.Operator.MINUS;
						begin = token();
						break;
					}
					default:
						jj_la1[87] = jj_gen;
						jj_consume_token(-1);
						throw new ParseException();
				}
				ret = UnaryExpression();
				ret = new UnaryExpr(range(begin, token()), ret, op);
				break;
			}
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FALSE:
			case FLOAT:
			case INT:
			case LONG:
			case NEW:
			case NULL:
			case SHORT:
			case SUPER:
			case THIS:
			case TRUE:
			case VOID:
			case REQUIRES:
			case TO:
			case WITH:
			case OPEN:
			case OPENS:
			case USES:
			case MODULE:
			case EXPORTS:
			case PROVIDES:
			case TRANSITIVE:
			case LONG_LITERAL:
			case INTEGER_LITERAL:
			case FLOATING_POINT_LITERAL:
			case CHARACTER_LITERAL:
			case STRING_LITERAL:
			case IDENTIFIER:
			case LPAREN:
			case BANG:
			case TILDE: {
				ret = UnaryExpressionNotPlusMinus();
				break;
			}
			default:
				jj_la1[88] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
		}
		return ret;
	}

	final public Expression PreIncrementExpression() throws ParseException {
		Expression ret;
		JavaToken begin = INVALID;
		jj_consume_token(INCR);
		begin = token();
		ret = UnaryExpression();
		ret = new UnaryExpr(range(begin, token()), ret, UnaryExpr.Operator.PREFIX_INCREMENT);
		return ret;
	}

	final public Expression PreDecrementExpression() throws ParseException {
		Expression ret;
		JavaToken begin;
		jj_consume_token(DECR);
		begin = token();
		ret = UnaryExpression();
		ret = new UnaryExpr(range(begin, token()), ret, UnaryExpr.Operator.PREFIX_DECREMENT);
		return ret;
	}

	final public Expression UnaryExpressionNotPlusMinus() throws ParseException {
		Expression ret;
		UnaryExpr.Operator op;
		JavaToken begin = INVALID;
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case BANG:
			case TILDE: {
				switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
					case TILDE: {
						jj_consume_token(TILDE);
						op = UnaryExpr.Operator.BITWISE_COMPLEMENT;
						begin = token();
						break;
					}
					case BANG: {
						jj_consume_token(BANG);
						op = UnaryExpr.Operator.LOGICAL_COMPLEMENT;
						begin = token();
						break;
					}
					default:
						jj_la1[89] = jj_gen;
						jj_consume_token(-1);
						throw new ParseException();
				}
				ret = UnaryExpression();
				ret = new UnaryExpr(range(begin, token()), ret, op);
				break;
			}
			default:
				jj_la1[90] = jj_gen;
				if (jj_2_26(2147483647)) {
					ret = CastExpression();
				} else {
					switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
						case BOOLEAN:
						case BYTE:
						case CHAR:
						case DOUBLE:
						case FALSE:
						case FLOAT:
						case INT:
						case LONG:
						case NEW:
						case NULL:
						case SHORT:
						case SUPER:
						case THIS:
						case TRUE:
						case VOID:
						case REQUIRES:
						case TO:
						case WITH:
						case OPEN:
						case OPENS:
						case USES:
						case MODULE:
						case EXPORTS:
						case PROVIDES:
						case TRANSITIVE:
						case LONG_LITERAL:
						case INTEGER_LITERAL:
						case FLOATING_POINT_LITERAL:
						case CHARACTER_LITERAL:
						case STRING_LITERAL:
						case IDENTIFIER:
						case LPAREN: {
							ret = PostfixExpression();
							break;
						}
						default:
							jj_la1[91] = jj_gen;
							jj_consume_token(-1);
							throw new ParseException();
					}
				}
		}
		return ret;
	}

	final public Expression PostfixExpression() throws ParseException {
		Expression ret;
		UnaryExpr.Operator op;
		ret = PrimaryExpression();
		if (jj_2_27(2)) {
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case INCR: {
					jj_consume_token(INCR);
					op = UnaryExpr.Operator.POSTFIX_INCREMENT;
					break;
				}
				case DECR: {
					jj_consume_token(DECR);
					op = UnaryExpr.Operator.POSTFIX_DECREMENT;
					break;
				}
				default:
					jj_la1[92] = jj_gen;
					jj_consume_token(-1);
					throw new ParseException();
			}
			ret = new UnaryExpr(range(ret.getTokenRange().get().getBegin(), token()), ret, op);
		} else {
			;
		}
		return ret;
	}

	final public Expression CastExpression() throws ParseException {
		Expression ret;
		ReferenceType referenceType;
		PrimitiveType primitiveType;
		JavaToken begin = INVALID;
		NodeList<AnnotationExpr> annotations = new NodeList<AnnotationExpr>();
		NodeList<ReferenceType> typesOfMultiCast = emptyList();
		jj_consume_token(LPAREN);
		begin = token();
		annotations = Annotations();
		if (jj_2_28(2)) {
			primitiveType = PrimitiveType();
			jj_consume_token(RPAREN);
			ret = UnaryExpression();
			primitiveType.setAnnotations(annotations);
			ret = new CastExpr(range(begin, token()), primitiveType, ret);
		} else {
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case BOOLEAN:
				case BYTE:
				case CHAR:
				case DOUBLE:
				case FLOAT:
				case INT:
				case LONG:
				case SHORT:
				case REQUIRES:
				case TO:
				case WITH:
				case OPEN:
				case OPENS:
				case USES:
				case MODULE:
				case EXPORTS:
				case PROVIDES:
				case TRANSITIVE:
				case IDENTIFIER: {
					referenceType = ReferenceType();
					typesOfMultiCast = add(typesOfMultiCast, referenceType);
					referenceType.setAnnotations(annotations);
					label_39:
					while (true) {
						switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
							case BIT_AND: {
								;
								break;
							}
							default:
								jj_la1[93] = jj_gen;
								break label_39;
						}
						jj_consume_token(BIT_AND);
						referenceType = ReferenceType();
						typesOfMultiCast = add(typesOfMultiCast, referenceType);
					}
					jj_consume_token(RPAREN);
					ret = UnaryExpressionNotPlusMinus();
					if (typesOfMultiCast.size() > 1) {
						ret = new CastExpr(range(begin, token()), new IntersectionType(range(begin, token()), typesOfMultiCast), ret);
					} else {
						ret = new CastExpr(range(begin, token()), referenceType, ret);
					}
					break;
				}
				default:
					jj_la1[94] = jj_gen;
					jj_consume_token(-1);
					throw new ParseException();
			}
		}
		return ret;
	}

	final public Expression PrimaryExpression() throws ParseException {
		Expression ret;
		ret = PrimaryPrefix();
		label_40:
		while (true) {
			if (jj_2_29(2)) {
				;
			} else {
				break label_40;
			}
			ret = PrimarySuffix(ret);
		}
		return ret;
	}

	final public Expression PrimaryExpressionWithoutSuperSuffix() throws ParseException {
		Expression ret;
		ret = PrimaryPrefix();
		label_41:
		while (true) {
			if (jj_2_30(2147483647)) {
				;
			} else {
				break label_41;
			}
			ret = PrimarySuffixWithoutSuper(ret);
		}
		return ret;
	}

	final public Expression PrimaryPrefix() throws ParseException {
		Expression ret = null;
		SimpleName name;
		RangedList<Type> typeArgs = new RangedList<Type>(null);
		NodeList<Expression> args = emptyList();
		NodeList<Parameter> params = emptyList();
		boolean hasArgs = false;
		boolean isLambda = false;
		Type type;
		JavaToken begin;
		Parameter p = null;
		SimpleName id = null;
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case FALSE:
			case NULL:
			case TRUE:
			case LONG_LITERAL:
			case INTEGER_LITERAL:
			case FLOATING_POINT_LITERAL:
			case CHARACTER_LITERAL:
			case STRING_LITERAL: {
				ret = Literal();
				break;
			}
			case THIS: {
				jj_consume_token(THIS);
				ret = new ThisExpr(tokenRange(), null);
				break;
			}
			case SUPER: {
				jj_consume_token(SUPER);
				ret = new SuperExpr(tokenRange(), null);
				switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
					case DOT: {
						jj_consume_token(DOT);
						switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
							case LT: {
								typeArgs = TypeArguments();
								break;
							}
							default:
								jj_la1[95] = jj_gen;
								;
						}
						name = SimpleName();
						switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
							case LPAREN: {
								args = Arguments();
								hasArgs = true;
								break;
							}
							default:
								jj_la1[96] = jj_gen;
								;
						}
						if (hasArgs) {
							ret = new MethodCallExpr(range(ret.getTokenRange().get().getBegin(), token()), ret, typeArgs.list, name, args);
						} else {
							ret = new FieldAccessExpr(range(ret.getTokenRange().get().getBegin(), token()), ret, emptyList(), name);
						}
						break;
					}
					case DOUBLECOLON: {
						jj_consume_token(DOUBLECOLON);
						switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
							case LT: {
								typeArgs = TypeArguments();
								break;
							}
							default:
								jj_la1[97] = jj_gen;
								;
						}
						switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
							case REQUIRES:
							case TO:
							case WITH:
							case OPEN:
							case OPENS:
							case USES:
							case MODULE:
							case EXPORTS:
							case PROVIDES:
							case TRANSITIVE:
							case IDENTIFIER: {
								Identifier();
								break;
							}
							case NEW: {
								jj_consume_token(NEW);
								break;
							}
							default:
								jj_la1[98] = jj_gen;
								jj_consume_token(-1);
								throw new ParseException();
						}
						ret = new MethodReferenceExpr(range(ret.getTokenRange().get().getBegin(), token()), ret, typeArgs.list, token.image);
						break;
					}
					default:
						jj_la1[99] = jj_gen;
						jj_consume_token(-1);
						throw new ParseException();
				}
				break;
			}
			case LPAREN: {
				jj_consume_token(LPAREN);
				begin = token();
				switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
					case ABSTRACT:
					case BOOLEAN:
					case BYTE:
					case CHAR:
					case _DEFAULT:
					case DOUBLE:
					case FALSE:
					case FINAL:
					case FLOAT:
					case INT:
					case LONG:
					case NATIVE:
					case NEW:
					case NULL:
					case PRIVATE:
					case PROTECTED:
					case PUBLIC:
					case SHORT:
					case STATIC:
					case STRICTFP:
					case SUPER:
					case SYNCHRONIZED:
					case THIS:
					case TRANSIENT:
					case TRUE:
					case VOID:
					case VOLATILE:
					case REQUIRES:
					case TO:
					case WITH:
					case OPEN:
					case OPENS:
					case USES:
					case MODULE:
					case EXPORTS:
					case PROVIDES:
					case TRANSITIVE:
					case LONG_LITERAL:
					case INTEGER_LITERAL:
					case FLOATING_POINT_LITERAL:
					case CHARACTER_LITERAL:
					case STRING_LITERAL:
					case IDENTIFIER:
					case LPAREN:
					case AT:
					case BANG:
					case TILDE:
					case INCR:
					case DECR:
					case PLUS:
					case MINUS: {
						if (jj_2_31(2147483647)) {
							p = Parameter();
							isLambda = true;
							switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
								case COMMA: {
									params = LambdaParameters();
									break;
								}
								default:
									jj_la1[100] = jj_gen;
									;
							}
						} else {
							switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
								case BOOLEAN:
								case BYTE:
								case CHAR:
								case DOUBLE:
								case FALSE:
								case FLOAT:
								case INT:
								case LONG:
								case NEW:
								case NULL:
								case SHORT:
								case SUPER:
								case THIS:
								case TRUE:
								case VOID:
								case REQUIRES:
								case TO:
								case WITH:
								case OPEN:
								case OPENS:
								case USES:
								case MODULE:
								case EXPORTS:
								case PROVIDES:
								case TRANSITIVE:
								case LONG_LITERAL:
								case INTEGER_LITERAL:
								case FLOATING_POINT_LITERAL:
								case CHARACTER_LITERAL:
								case STRING_LITERAL:
								case IDENTIFIER:
								case LPAREN:
								case BANG:
								case TILDE:
								case INCR:
								case DECR:
								case PLUS:
								case MINUS: {
									ret = Expression();
									switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
										case COMMA: {
											params = InferredLambdaParameters();
											isLambda = true;
											break;
										}
										default:
											jj_la1[101] = jj_gen;
											;
									}
									break;
								}
								default:
									jj_la1[102] = jj_gen;
									jj_consume_token(-1);
									throw new ParseException();
							}
						}
						break;
					}
					default:
						jj_la1[103] = jj_gen;
						;
				}
				jj_consume_token(RPAREN);
				if (!isLambda) {
					ret = new EnclosedExpr(range(begin, token()), ret);
				} else {
					if (ret != null) {
						if (ret instanceof NameExpr) {
							id = ((NameExpr) ret).getName();
							p = new Parameter(range(ret.getTokenRange().get().getBegin(), ret.getTokenRange().get().getEnd()), EnumSet.noneOf(Modifier.class), emptyList(),
									new UnknownType(), false, emptyList(), id);
						}

					}
					params = add(0, params, p);
					// TODO p may be null here
					ret = new LambdaExpr(range(p.getTokenRange().get().getBegin(), token()), params, new BlockStmt(), true);
				}
				break;
			}
			case NEW: {
				ret = AllocationExpression(null);
				break;
			}
			default:
				jj_la1[107] = jj_gen;
				if (jj_2_32(2147483647)) {
					type = ResultType();
					jj_consume_token(DOT);
					jj_consume_token(CLASS);
					ret = new ClassExpr(range(type.getTokenRange().get().getBegin(), token()), type);
				} else if (jj_2_33(2147483647)) {
					type = ResultType();
					jj_consume_token(DOUBLECOLON);
					switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
						case LT: {
							typeArgs = TypeArguments();
							break;
						}
						default:
							jj_la1[104] = jj_gen;
							;
					}
					switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
						case REQUIRES:
						case TO:
						case WITH:
						case OPEN:
						case OPENS:
						case USES:
						case MODULE:
						case EXPORTS:
						case PROVIDES:
						case TRANSITIVE:
						case IDENTIFIER: {
							Identifier();
							break;
						}
						case NEW: {
							jj_consume_token(NEW);
							break;
						}
						default:
							jj_la1[105] = jj_gen;
							jj_consume_token(-1);
							throw new ParseException();
					}
					ret = new TypeExpr(type.getTokenRange().get(), type);
					ret = new MethodReferenceExpr(ret.getTokenRange().get(), ret, typeArgs.list, token.image);
				} else {
					switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
						case REQUIRES:
						case TO:
						case WITH:
						case OPEN:
						case OPENS:
						case USES:
						case MODULE:
						case EXPORTS:
						case PROVIDES:
						case TRANSITIVE:
						case IDENTIFIER: {
							name = SimpleName();
							begin = token();
							switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
								case LPAREN: {
									args = Arguments();
									hasArgs = true;
									break;
								}
								default:
									jj_la1[106] = jj_gen;
									;
							}
							if (hasArgs) {
								ret = new MethodCallExpr(range(begin, token()), null, emptyList(), name, args);
							} else {
								ret = new NameExpr(name);
							}
							break;
						}
						default:
							jj_la1[108] = jj_gen;
							jj_consume_token(-1);
							throw new ParseException();
					}
				}
		}
		return ret;
	}

	final public Expression PrimarySuffix(Expression scope) throws ParseException {
		Expression ret;
		if (jj_2_34(2)) {
			ret = PrimarySuffixWithoutSuper(scope);
		} else {
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case DOT: {
					jj_consume_token(DOT);
					jj_consume_token(SUPER);
					ret = new SuperExpr(range(scope.getTokenRange().get().getBegin(), token()), scope);
					break;
				}
				default:
					jj_la1[109] = jj_gen;
					jj_consume_token(-1);
					throw new ParseException();
			}
		}
		return ret;
	}

	final public Expression PrimarySuffixWithoutSuper(Expression scope) throws ParseException {
		Expression ret;
		RangedList<Type> typeArgs = new RangedList<Type>(null);
		NodeList<Expression> args = emptyList();
		boolean hasArgs = false;
		SimpleName name;
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case DOT: {
				jj_consume_token(DOT);
				switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
					case THIS: {
						jj_consume_token(THIS);
						ret = new ThisExpr(range(scope.getTokenRange().get().getBegin(), token()), scope);
						break;
					}
					case NEW: {
						ret = AllocationExpression(scope);
						break;
					}
					default:
						jj_la1[112] = jj_gen;
						if (jj_2_35(2147483647)) {
							switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
								case LT: {
									typeArgs = TypeArguments();
									break;
								}
								default:
									jj_la1[110] = jj_gen;
									;
							}
							name = SimpleName();
							switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
								case LPAREN: {
									args = Arguments();
									hasArgs = true;
									break;
								}
								default:
									jj_la1[111] = jj_gen;
									;
							}
							if (hasArgs) {
								ret = new MethodCallExpr(range(scope.getTokenRange().get().getBegin(), token()), scope, typeArgs.list, name, args);
							} else {
								ret = new FieldAccessExpr(range(scope.getTokenRange().get().getBegin(), token()), scope, typeArgs.list, name);
							}
						} else {
							jj_consume_token(-1);
							throw new ParseException();
						}
				}
				break;
			}
			case LBRACKET: {
				jj_consume_token(LBRACKET);
				ret = Expression();
				jj_consume_token(RBRACKET);
				ret = new ArrayAccessExpr(range(scope.getTokenRange().get().getBegin(), token()), scope, ret);
				break;
			}
			default:
				jj_la1[113] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
		}
		return ret;
	}

	final public Expression Literal() throws ParseException {
		Expression ret;
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case INTEGER_LITERAL: {
				jj_consume_token(INTEGER_LITERAL);
				ret = new IntegerLiteralExpr(tokenRange(), token.image);
				break;
			}
			case LONG_LITERAL: {
				jj_consume_token(LONG_LITERAL);
				ret = new LongLiteralExpr(tokenRange(), token.image);
				break;
			}
			case FLOATING_POINT_LITERAL: {
				jj_consume_token(FLOATING_POINT_LITERAL);
				ret = new DoubleLiteralExpr(tokenRange(), token.image);
				break;
			}
			case CHARACTER_LITERAL: {
				jj_consume_token(CHARACTER_LITERAL);
				ret = new CharLiteralExpr(tokenRange(), token.image.substring(1, token.image.length() - 1));
				break;
			}
			case STRING_LITERAL: {
				jj_consume_token(STRING_LITERAL);
				ret = new StringLiteralExpr(tokenRange(), token.image.substring(1, token.image.length() - 1));
				break;
			}
			case FALSE:
			case TRUE: {
				ret = BooleanLiteral();
				break;
			}
			case NULL: {
				ret = NullLiteral();
				break;
			}
			default:
				jj_la1[114] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
		}
		return ret;
	}

	final public Expression BooleanLiteral() throws ParseException {
		Expression ret;
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case TRUE: {
				jj_consume_token(TRUE);
				ret = new BooleanLiteralExpr(tokenRange(), true);
				break;
			}
			case FALSE: {
				jj_consume_token(FALSE);
				ret = new BooleanLiteralExpr(tokenRange(), false);
				break;
			}
			default:
				jj_la1[115] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
		}
		return ret;
	}

	final public Expression NullLiteral() throws ParseException {
		jj_consume_token(NULL);
		return new NullLiteralExpr(tokenRange());
	}

	final public NodeList<Expression> Arguments() throws ParseException {
		NodeList<Expression> ret = emptyList();
		jj_consume_token(LPAREN);
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FALSE:
			case FLOAT:
			case INT:
			case LONG:
			case NEW:
			case NULL:
			case SHORT:
			case SUPER:
			case THIS:
			case TRUE:
			case VOID:
			case REQUIRES:
			case TO:
			case WITH:
			case OPEN:
			case OPENS:
			case USES:
			case MODULE:
			case EXPORTS:
			case PROVIDES:
			case TRANSITIVE:
			case LONG_LITERAL:
			case INTEGER_LITERAL:
			case FLOATING_POINT_LITERAL:
			case CHARACTER_LITERAL:
			case STRING_LITERAL:
			case IDENTIFIER:
			case LPAREN:
			case BANG:
			case TILDE:
			case INCR:
			case DECR:
			case PLUS:
			case MINUS: {
				ret = ArgumentList();
				break;
			}
			default:
				jj_la1[116] = jj_gen;
				;
		}
		jj_consume_token(RPAREN);
		return ret;
	}

	final public NodeList<Expression> ArgumentList() throws ParseException {
		NodeList<Expression> ret = emptyList();
		Expression expr;
		expr = Expression();
		ret.add(expr);
		label_42:
		while (true) {
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case COMMA: {
					;
					break;
				}
				default:
					jj_la1[117] = jj_gen;
					break label_42;
			}
			jj_consume_token(COMMA);
			expr = Expression();
			ret.add(expr);
		}
		return ret;
	}

	final public Expression AllocationExpression(Expression scope) throws ParseException {
		Expression ret;
		Type type;
		RangedList<Type> typeArgs = new RangedList<Type>(null);
		NodeList<BodyDeclaration<?>> anonymousBody = null;
		NodeList<Expression> args;
		JavaToken begin;
		NodeList<AnnotationExpr> annotations = new NodeList<AnnotationExpr>();
		jj_consume_token(NEW);
		if (scope == null) {
			begin = token();
		} else {
			begin = scope.getTokenRange().get().getBegin();
		}
		annotations = Annotations();
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FLOAT:
			case INT:
			case LONG:
			case SHORT: {
				type = PrimitiveType();
				type.setAnnotations(annotations);
				ret = ArrayCreation(begin, type);
				break;
			}
			case REQUIRES:
			case TO:
			case WITH:
			case OPEN:
			case OPENS:
			case USES:
			case MODULE:
			case EXPORTS:
			case PROVIDES:
			case TRANSITIVE:
			case IDENTIFIER:
			case AT:
			case LT: {
				switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
					case LT: {
						typeArgs = TypeArguments();
						annotations = Annotations();
						break;
					}
					default:
						jj_la1[118] = jj_gen;
						;
				}
				type = AnnotatedClassOrInterfaceType();
				switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
					case LBRACKET:
					case AT: {
						ret = ArrayCreation(begin, type);
						break;
					}
					case LPAREN: {
						args = Arguments();
						if (jj_2_36(2)) {
							anonymousBody = ClassOrInterfaceBody();
						} else {
							;
						}
						ret = new ObjectCreationExpr(range(begin, token()), scope, (ClassOrInterfaceType) type, typeArgs.list, args, anonymousBody);
						break;
					}
					default:
						jj_la1[119] = jj_gen;
						jj_consume_token(-1);
						throw new ParseException();
				}
				break;
			}
			default:
				jj_la1[120] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
		}
		return ret;
	}

	final public ArrayCreationExpr ArrayCreation(JavaToken begin, Type type) throws ParseException {
		Expression expr = null;
		ArrayInitializerExpr arrayInitializerExpr = null;
		NodeList<Expression> inits = emptyList();
		List<NodeList<AnnotationExpr>> accum = new ArrayList<NodeList<AnnotationExpr>>();
		NodeList<AnnotationExpr> annotations = new NodeList<AnnotationExpr>();
		JavaToken arrayCreationLevelStart = null;
		List<TokenRange> levelRanges = new ArrayList<TokenRange>();
		label_43:
		while (true) {
			annotations = Annotations();
			jj_consume_token(LBRACKET);
			arrayCreationLevelStart = annotations.isEmpty() ? token() : annotations.get(0).getTokenRange().get().getBegin();
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case BOOLEAN:
				case BYTE:
				case CHAR:
				case DOUBLE:
				case FALSE:
				case FLOAT:
				case INT:
				case LONG:
				case NEW:
				case NULL:
				case SHORT:
				case SUPER:
				case THIS:
				case TRUE:
				case VOID:
				case REQUIRES:
				case TO:
				case WITH:
				case OPEN:
				case OPENS:
				case USES:
				case MODULE:
				case EXPORTS:
				case PROVIDES:
				case TRANSITIVE:
				case LONG_LITERAL:
				case INTEGER_LITERAL:
				case FLOATING_POINT_LITERAL:
				case CHARACTER_LITERAL:
				case STRING_LITERAL:
				case IDENTIFIER:
				case LPAREN:
				case BANG:
				case TILDE:
				case INCR:
				case DECR:
				case PLUS:
				case MINUS: {
					expr = Expression();
					break;
				}
				default:
					jj_la1[121] = jj_gen;
					;
			}
			accum = add(accum, annotations);
			inits = add(inits, expr);
			annotations = null;
			expr = null;
			jj_consume_token(RBRACKET);
			levelRanges.add(new TokenRange(arrayCreationLevelStart, token()));
			if (jj_2_37(2)) {
				;
			} else {
				break label_43;
			}
		}
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case LBRACE: {
				arrayInitializerExpr = ArrayInitializer();
				break;
			}
			default:
				jj_la1[122] = jj_gen;
				;
		}
		return juggleArrayCreation(range(begin, token()), levelRanges, type, inits, accum, arrayInitializerExpr);
	}

	/*
	 * Statement syntax follows.
	 */
	final public Statement Statement() throws ParseException {
		Statement ret;
		if (jj_2_38(2)) {
			ret = LabeledStatement();
		} else {
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case ASSERT: {
					ret = AssertStatement();
					break;
				}
				case LBRACE: {
					ret = Block();
					break;
				}
				case SEMICOLON: {
					ret = EmptyStatement();
					break;
				}
				case BOOLEAN:
				case BYTE:
				case CHAR:
				case DOUBLE:
				case FALSE:
				case FLOAT:
				case INT:
				case LONG:
				case NEW:
				case NULL:
				case SHORT:
				case SUPER:
				case THIS:
				case TRUE:
				case VOID:
				case REQUIRES:
				case TO:
				case WITH:
				case OPEN:
				case OPENS:
				case USES:
				case MODULE:
				case EXPORTS:
				case PROVIDES:
				case TRANSITIVE:
				case LONG_LITERAL:
				case INTEGER_LITERAL:
				case FLOATING_POINT_LITERAL:
				case CHARACTER_LITERAL:
				case STRING_LITERAL:
				case IDENTIFIER:
				case LPAREN:
				case INCR:
				case DECR: {
					ret = StatementExpression();
					break;
				}
				case SWITCH: {
					ret = SwitchStatement();
					break;
				}
				case IF: {
					ret = IfStatement();
					break;
				}
				case WHILE: {
					ret = WhileStatement();
					break;
				}
				case DO: {
					ret = DoStatement();
					break;
				}
				case FOR: {
					ret = ForStatement();
					break;
				}
				case BREAK: {
					ret = BreakStatement();
					break;
				}
				case CONTINUE: {
					ret = ContinueStatement();
					break;
				}
				case RETURN: {
					ret = ReturnStatement();
					break;
				}
				case THROW: {
					ret = ThrowStatement();
					break;
				}
				case SYNCHRONIZED: {
					ret = SynchronizedStatement();
					break;
				}
				case TRY: {
					ret = TryStatement();
					break;
				}
				default:
					jj_la1[123] = jj_gen;
					jj_consume_token(-1);
					throw new ParseException();
			}
		}
		return ret;
	}

	final public AssertStmt AssertStatement() throws ParseException {
		Expression check;
		Expression msg = null;
		JavaToken begin;
		jj_consume_token(ASSERT);
		begin = token();
		check = Expression();
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case COLON: {
				jj_consume_token(COLON);
				msg = Expression();
				break;
			}
			default:
				jj_la1[124] = jj_gen;
				;
		}
		jj_consume_token(SEMICOLON);
		return new AssertStmt(range(begin, token()), check, msg);
	}

	final public LabeledStmt LabeledStatement() throws ParseException {
		SimpleName label;
		Statement stmt;
		JavaToken begin;
		label = SimpleName();
		begin = token();
		jj_consume_token(COLON);
		stmt = Statement();
		return new LabeledStmt(range(begin, token()), label, stmt);
	}

	final public BlockStmt Block() throws ParseException {
		NodeList<Statement> stmts = emptyList();
		JavaToken begin;
		jj_consume_token(LBRACE);
		begin = token();
		stmts = Statements();
		jj_consume_token(RBRACE);
		return new BlockStmt(range(begin, token()), stmts);
	}

	/*
	 * Classes inside body stametents can only be abstract or final. The semantic must check it.
	 */
	final public Statement BlockStatement() throws ParseException {
		Statement ret;
		Expression expr;
		ClassOrInterfaceDeclaration typeDecl;
		ModifierHolder modifier;
		if (jj_2_39(2147483647)) {
			modifier = Modifiers();
			typeDecl = ClassOrInterfaceDeclaration(modifier);
			ret = new LocalClassDeclarationStmt(range(typeDecl.getTokenRange().get().getBegin(), token()), typeDecl);
		} else if (jj_2_40(2147483647)) {
			expr = VariableDeclarationExpression();
			jj_consume_token(SEMICOLON);
			ret = new ExpressionStmt(range(expr.getTokenRange().get().getBegin(), token()), expr);
		} else {
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case ASSERT:
				case BOOLEAN:
				case BREAK:
				case BYTE:
				case CHAR:
				case CONTINUE:
				case DO:
				case DOUBLE:
				case FALSE:
				case FLOAT:
				case FOR:
				case IF:
				case INT:
				case LONG:
				case NEW:
				case NULL:
				case RETURN:
				case SHORT:
				case SUPER:
				case SWITCH:
				case SYNCHRONIZED:
				case THIS:
				case THROW:
				case TRUE:
				case TRY:
				case VOID:
				case WHILE:
				case REQUIRES:
				case TO:
				case WITH:
				case OPEN:
				case OPENS:
				case USES:
				case MODULE:
				case EXPORTS:
				case PROVIDES:
				case TRANSITIVE:
				case LONG_LITERAL:
				case INTEGER_LITERAL:
				case FLOATING_POINT_LITERAL:
				case CHARACTER_LITERAL:
				case STRING_LITERAL:
				case IDENTIFIER:
				case LPAREN:
				case LBRACE:
				case SEMICOLON:
				case INCR:
				case DECR: {
					ret = Statement();
					break;
				}
				default:
					jj_la1[125] = jj_gen;
					jj_consume_token(-1);
					throw new ParseException();
			}
		}
		return ret;
	}

	final public VariableDeclarationExpr VariableDeclarationExpression() throws ParseException {
		ModifierHolder modifier;
		Type partialType;
		NodeList<VariableDeclarator> variables = new NodeList<VariableDeclarator>();
		VariableDeclarator var;
		modifier = Modifiers();
		partialType = Type();
		var = VariableDeclarator(partialType);
		variables.add(var);
		label_44:
		while (true) {
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case COMMA: {
					;
					break;
				}
				default:
					jj_la1[126] = jj_gen;
					break label_44;
			}
			jj_consume_token(COMMA);
			var = VariableDeclarator(partialType);
			variables.add(var);
		}
		JavaToken begin = modifier.begin.orIfInvalid(partialType.getTokenRange().get().getBegin());
		return new VariableDeclarationExpr(range(begin, token()), modifier.modifiers, modifier.annotations, variables);
	}

	final public EmptyStmt EmptyStatement() throws ParseException {
		jj_consume_token(SEMICOLON);
		return new EmptyStmt(tokenRange());
	}

	final public Statement LambdaBody() throws ParseException {
		Expression expr;
		Statement n = null;
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FALSE:
			case FLOAT:
			case INT:
			case LONG:
			case NEW:
			case NULL:
			case SHORT:
			case SUPER:
			case THIS:
			case TRUE:
			case VOID:
			case REQUIRES:
			case TO:
			case WITH:
			case OPEN:
			case OPENS:
			case USES:
			case MODULE:
			case EXPORTS:
			case PROVIDES:
			case TRANSITIVE:
			case LONG_LITERAL:
			case INTEGER_LITERAL:
			case FLOATING_POINT_LITERAL:
			case CHARACTER_LITERAL:
			case STRING_LITERAL:
			case IDENTIFIER:
			case LPAREN:
			case BANG:
			case TILDE:
			case INCR:
			case DECR:
			case PLUS:
			case MINUS: {
				expr = Expression();
				n = new ExpressionStmt(range(expr.getTokenRange().get().getBegin(), token()), expr);
				break;
			}
			case LBRACE: {
				n = Block();
				break;
			}
			default:
				jj_la1[127] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
		}
		return n;
	}

	final public ExpressionStmt StatementExpression() throws ParseException {
		Expression expr;
		AssignExpr.Operator op;
		Expression value;
		RangedList<Type> typeArgs = new RangedList<Type>(null);
		Statement lambdaBody;
		if (jj_2_41(2)) {
			expr = PreIncrementExpression();
		} else {
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case DECR: {
					expr = PreDecrementExpression();
					break;
				}
				case BOOLEAN:
				case BYTE:
				case CHAR:
				case DOUBLE:
				case FALSE:
				case FLOAT:
				case INT:
				case LONG:
				case NEW:
				case NULL:
				case SHORT:
				case SUPER:
				case THIS:
				case TRUE:
				case VOID:
				case REQUIRES:
				case TO:
				case WITH:
				case OPEN:
				case OPENS:
				case USES:
				case MODULE:
				case EXPORTS:
				case PROVIDES:
				case TRANSITIVE:
				case LONG_LITERAL:
				case INTEGER_LITERAL:
				case FLOATING_POINT_LITERAL:
				case CHARACTER_LITERAL:
				case STRING_LITERAL:
				case IDENTIFIER:
				case LPAREN: {
					expr = PrimaryExpression();
					switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
						case ASSIGN:
						case INCR:
						case DECR:
						case PLUSASSIGN:
						case MINUSASSIGN:
						case STARASSIGN:
						case SLASHASSIGN:
						case ANDASSIGN:
						case ORASSIGN:
						case XORASSIGN:
						case REMASSIGN:
						case LSHIFTASSIGN:
						case RSIGNEDSHIFTASSIGN:
						case RUNSIGNEDSHIFTASSIGN:
						case ARROW:
						case DOUBLECOLON: {
							switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
								case INCR: {
									jj_consume_token(INCR);
									expr = new UnaryExpr(range(expr.getTokenRange().get().getBegin(), token()), expr, UnaryExpr.Operator.POSTFIX_INCREMENT);
									break;
								}
								case DECR: {
									jj_consume_token(DECR);
									expr = new UnaryExpr(range(expr.getTokenRange().get().getBegin(), token()), expr, UnaryExpr.Operator.POSTFIX_DECREMENT);
									break;
								}
								case ASSIGN:
								case PLUSASSIGN:
								case MINUSASSIGN:
								case STARASSIGN:
								case SLASHASSIGN:
								case ANDASSIGN:
								case ORASSIGN:
								case XORASSIGN:
								case REMASSIGN:
								case LSHIFTASSIGN:
								case RSIGNEDSHIFTASSIGN:
								case RUNSIGNEDSHIFTASSIGN: {
									op = AssignmentOperator();
									value = Expression();
									expr = new AssignExpr(range(expr.getTokenRange().get().getBegin(), token()), expr, value, op);
									break;
								}
								case DOUBLECOLON: {
									jj_consume_token(DOUBLECOLON);
									switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
										case LT: {
											typeArgs = TypeArguments();
											break;
										}
										default:
											jj_la1[128] = jj_gen;
											;
									}
									switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
										case REQUIRES:
										case TO:
										case WITH:
										case OPEN:
										case OPENS:
										case USES:
										case MODULE:
										case EXPORTS:
										case PROVIDES:
										case TRANSITIVE:
										case IDENTIFIER: {
											Identifier();
											break;
										}
										case NEW: {
											jj_consume_token(NEW);
											break;
										}
										default:
											jj_la1[129] = jj_gen;
											jj_consume_token(-1);
											throw new ParseException();
									}
									expr = new MethodReferenceExpr(range(expr.getTokenRange().get().getBegin(), token()), expr, typeArgs.list, token.image);
									break;
								}
								case ARROW: {
									jj_consume_token(ARROW);
									lambdaBody = LambdaBody();
									expr = generateLambda(this, expr, lambdaBody);
									break;
								}
								default:
									jj_la1[130] = jj_gen;
									jj_consume_token(-1);
									throw new ParseException();
							}
							break;
						}
						default:
							jj_la1[131] = jj_gen;
							;
					}
					break;
				}
				default:
					jj_la1[132] = jj_gen;
					jj_consume_token(-1);
					throw new ParseException();
			}
		}
		jj_consume_token(SEMICOLON);
		return new ExpressionStmt(range(expr.getTokenRange().get().getBegin(), token()), expr);
	}

	final public SwitchStmt SwitchStatement() throws ParseException {
		Expression selector;
		SwitchEntryStmt entry;
		NodeList<SwitchEntryStmt> entries = emptyList();
		JavaToken begin;
		jj_consume_token(SWITCH);
		begin = token();
		jj_consume_token(LPAREN);
		selector = Expression();
		jj_consume_token(RPAREN);
		jj_consume_token(LBRACE);
		label_45:
		while (true) {
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case CASE:
				case _DEFAULT: {
					;
					break;
				}
				default:
					jj_la1[133] = jj_gen;
					break label_45;
			}
			entry = SwitchEntry();
			entries = add(entries, entry);
		}
		jj_consume_token(RBRACE);
		return new SwitchStmt(range(begin, token()), selector, entries);
	}

	final public SwitchEntryStmt SwitchEntry() throws ParseException {
		Expression label = null;
		NodeList<Statement> stmts;
		JavaToken begin;
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case CASE: {
				jj_consume_token(CASE);
				begin = token();
				label = Expression();
				break;
			}
			case _DEFAULT: {
				jj_consume_token(_DEFAULT);
				begin = token();
				break;
			}
			default:
				jj_la1[134] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
		}
		jj_consume_token(COLON);
		stmts = Statements();
		return new SwitchEntryStmt(range(begin, token()), label, stmts);
	}

	final public IfStmt IfStatement() throws ParseException {
		Expression condition;
		Statement thenStmt;
		Statement elseStmt = null;
		JavaToken begin;
		jj_consume_token(IF);
		begin = token();
		jj_consume_token(LPAREN);
		condition = Expression();
		jj_consume_token(RPAREN);

		thenStmt = Statement();
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case ELSE: {
				jj_consume_token(ELSE);

				elseStmt = Statement();
				break;
			}
			default:
				jj_la1[135] = jj_gen;
				;
		}
		return new IfStmt(range(begin, token()), condition, thenStmt, elseStmt);
	}

	final public WhileStmt WhileStatement() throws ParseException {
		Expression condition;
		Statement body;
		JavaToken begin;
		jj_consume_token(WHILE);
		begin = token();
		jj_consume_token(LPAREN);
		condition = Expression();
		jj_consume_token(RPAREN);
		body = Statement();
		return new WhileStmt(range(begin, token()), condition, body);
	}

	final public DoStmt DoStatement() throws ParseException {
		Expression condition;
		Statement body;
		JavaToken begin;
		jj_consume_token(DO);
		begin = token();
		body = Statement();
		jj_consume_token(WHILE);
		jj_consume_token(LPAREN);
		condition = Expression();
		jj_consume_token(RPAREN);
		jj_consume_token(SEMICOLON);
		return new DoStmt(range(begin, token()), body, condition);
	}

	final public Statement ForStatement() throws ParseException {
		VariableDeclarationExpr varExpr = null;
		Expression expr = null;
		NodeList<Expression> init = emptyList();
		NodeList<Expression> update = emptyList();
		Statement body;
		JavaToken begin;
		jj_consume_token(FOR);
		begin = token();
		jj_consume_token(LPAREN);
		if (jj_2_42(2147483647)) {
			varExpr = VariableDeclarationExpression();
			jj_consume_token(COLON);
			expr = Expression();
		} else {
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case ABSTRACT:
				case BOOLEAN:
				case BYTE:
				case CHAR:
				case _DEFAULT:
				case DOUBLE:
				case FALSE:
				case FINAL:
				case FLOAT:
				case INT:
				case LONG:
				case NATIVE:
				case NEW:
				case NULL:
				case PRIVATE:
				case PROTECTED:
				case PUBLIC:
				case SHORT:
				case STATIC:
				case STRICTFP:
				case SUPER:
				case SYNCHRONIZED:
				case THIS:
				case TRANSIENT:
				case TRUE:
				case VOID:
				case VOLATILE:
				case REQUIRES:
				case TO:
				case WITH:
				case OPEN:
				case OPENS:
				case USES:
				case MODULE:
				case EXPORTS:
				case PROVIDES:
				case TRANSITIVE:
				case LONG_LITERAL:
				case INTEGER_LITERAL:
				case FLOATING_POINT_LITERAL:
				case CHARACTER_LITERAL:
				case STRING_LITERAL:
				case IDENTIFIER:
				case LPAREN:
				case SEMICOLON:
				case AT:
				case BANG:
				case TILDE:
				case INCR:
				case DECR:
				case PLUS:
				case MINUS: {
					switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
						case ABSTRACT:
						case BOOLEAN:
						case BYTE:
						case CHAR:
						case _DEFAULT:
						case DOUBLE:
						case FALSE:
						case FINAL:
						case FLOAT:
						case INT:
						case LONG:
						case NATIVE:
						case NEW:
						case NULL:
						case PRIVATE:
						case PROTECTED:
						case PUBLIC:
						case SHORT:
						case STATIC:
						case STRICTFP:
						case SUPER:
						case SYNCHRONIZED:
						case THIS:
						case TRANSIENT:
						case TRUE:
						case VOID:
						case VOLATILE:
						case REQUIRES:
						case TO:
						case WITH:
						case OPEN:
						case OPENS:
						case USES:
						case MODULE:
						case EXPORTS:
						case PROVIDES:
						case TRANSITIVE:
						case LONG_LITERAL:
						case INTEGER_LITERAL:
						case FLOATING_POINT_LITERAL:
						case CHARACTER_LITERAL:
						case STRING_LITERAL:
						case IDENTIFIER:
						case LPAREN:
						case AT:
						case BANG:
						case TILDE:
						case INCR:
						case DECR:
						case PLUS:
						case MINUS: {
							init = ForInit();
							break;
						}
						default:
							jj_la1[136] = jj_gen;
							;
					}
					jj_consume_token(SEMICOLON);
					switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
						case BOOLEAN:
						case BYTE:
						case CHAR:
						case DOUBLE:
						case FALSE:
						case FLOAT:
						case INT:
						case LONG:
						case NEW:
						case NULL:
						case SHORT:
						case SUPER:
						case THIS:
						case TRUE:
						case VOID:
						case REQUIRES:
						case TO:
						case WITH:
						case OPEN:
						case OPENS:
						case USES:
						case MODULE:
						case EXPORTS:
						case PROVIDES:
						case TRANSITIVE:
						case LONG_LITERAL:
						case INTEGER_LITERAL:
						case FLOATING_POINT_LITERAL:
						case CHARACTER_LITERAL:
						case STRING_LITERAL:
						case IDENTIFIER:
						case LPAREN:
						case BANG:
						case TILDE:
						case INCR:
						case DECR:
						case PLUS:
						case MINUS: {
							expr = Expression();
							break;
						}
						default:
							jj_la1[137] = jj_gen;
							;
					}
					jj_consume_token(SEMICOLON);
					switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
						case BOOLEAN:
						case BYTE:
						case CHAR:
						case DOUBLE:
						case FALSE:
						case FLOAT:
						case INT:
						case LONG:
						case NEW:
						case NULL:
						case SHORT:
						case SUPER:
						case THIS:
						case TRUE:
						case VOID:
						case REQUIRES:
						case TO:
						case WITH:
						case OPEN:
						case OPENS:
						case USES:
						case MODULE:
						case EXPORTS:
						case PROVIDES:
						case TRANSITIVE:
						case LONG_LITERAL:
						case INTEGER_LITERAL:
						case FLOATING_POINT_LITERAL:
						case CHARACTER_LITERAL:
						case STRING_LITERAL:
						case IDENTIFIER:
						case LPAREN:
						case BANG:
						case TILDE:
						case INCR:
						case DECR:
						case PLUS:
						case MINUS: {
							update = ForUpdate();
							break;
						}
						default:
							jj_la1[138] = jj_gen;
							;
					}
					break;
				}
				default:
					jj_la1[139] = jj_gen;
					jj_consume_token(-1);
					throw new ParseException();
			}
		}
		jj_consume_token(RPAREN);
		body = Statement();
		if (varExpr != null) {
			return new ForeachStmt(range(begin, token()), varExpr, expr, body);
		}
		return new ForStmt(range(begin, token()), init, expr, update, body);
	}

	final public NodeList<Expression> ForInit() throws ParseException {
		NodeList<Expression> ret;
		Expression expr;
		if (jj_2_43(2147483647)) {
			expr = VariableDeclarationExpression();
			ret = new NodeList<Expression>();
			ret.add(expr);
		} else {
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case BOOLEAN:
				case BYTE:
				case CHAR:
				case DOUBLE:
				case FALSE:
				case FLOAT:
				case INT:
				case LONG:
				case NEW:
				case NULL:
				case SHORT:
				case SUPER:
				case THIS:
				case TRUE:
				case VOID:
				case REQUIRES:
				case TO:
				case WITH:
				case OPEN:
				case OPENS:
				case USES:
				case MODULE:
				case EXPORTS:
				case PROVIDES:
				case TRANSITIVE:
				case LONG_LITERAL:
				case INTEGER_LITERAL:
				case FLOATING_POINT_LITERAL:
				case CHARACTER_LITERAL:
				case STRING_LITERAL:
				case IDENTIFIER:
				case LPAREN:
				case BANG:
				case TILDE:
				case INCR:
				case DECR:
				case PLUS:
				case MINUS: {
					ret = ExpressionList();
					break;
				}
				default:
					jj_la1[140] = jj_gen;
					jj_consume_token(-1);
					throw new ParseException();
			}
		}
		return ret;
	}

	final public NodeList<Expression> ExpressionList() throws ParseException {
		NodeList<Expression> ret = new NodeList<Expression>();
		Expression expr;
		expr = Expression();
		ret.add(expr);
		label_46:
		while (true) {
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case COMMA: {
					;
					break;
				}
				default:
					jj_la1[141] = jj_gen;
					break label_46;
			}
			jj_consume_token(COMMA);
			expr = Expression();
			ret.add(expr);
		}
		return ret;
	}

	final public NodeList<Expression> ForUpdate() throws ParseException {
		NodeList<Expression> ret;
		ret = ExpressionList();
		return ret;
	}

	final public BreakStmt BreakStatement() throws ParseException {
		SimpleName label = null;
		JavaToken begin;
		jj_consume_token(BREAK);
		begin = token();
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case REQUIRES:
			case TO:
			case WITH:
			case OPEN:
			case OPENS:
			case USES:
			case MODULE:
			case EXPORTS:
			case PROVIDES:
			case TRANSITIVE:
			case IDENTIFIER: {
				label = SimpleName();
				break;
			}
			default:
				jj_la1[142] = jj_gen;
				;
		}
		jj_consume_token(SEMICOLON);
		return new BreakStmt(range(begin, token()), label);
	}

	final public ContinueStmt ContinueStatement() throws ParseException {
		SimpleName label = null;
		JavaToken begin;
		jj_consume_token(CONTINUE);
		begin = token();
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case REQUIRES:
			case TO:
			case WITH:
			case OPEN:
			case OPENS:
			case USES:
			case MODULE:
			case EXPORTS:
			case PROVIDES:
			case TRANSITIVE:
			case IDENTIFIER: {
				label = SimpleName();
				break;
			}
			default:
				jj_la1[143] = jj_gen;
				;
		}
		jj_consume_token(SEMICOLON);
		return new ContinueStmt(range(begin, token()), label);
	}

	final public ReturnStmt ReturnStatement() throws ParseException {
		Expression expr = null;
		JavaToken begin;
		jj_consume_token(RETURN);
		begin = token();
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FALSE:
			case FLOAT:
			case INT:
			case LONG:
			case NEW:
			case NULL:
			case SHORT:
			case SUPER:
			case THIS:
			case TRUE:
			case VOID:
			case REQUIRES:
			case TO:
			case WITH:
			case OPEN:
			case OPENS:
			case USES:
			case MODULE:
			case EXPORTS:
			case PROVIDES:
			case TRANSITIVE:
			case LONG_LITERAL:
			case INTEGER_LITERAL:
			case FLOATING_POINT_LITERAL:
			case CHARACTER_LITERAL:
			case STRING_LITERAL:
			case IDENTIFIER:
			case LPAREN:
			case BANG:
			case TILDE:
			case INCR:
			case DECR:
			case PLUS:
			case MINUS: {
				expr = Expression();
				break;
			}
			default:
				jj_la1[144] = jj_gen;
				;
		}
		jj_consume_token(SEMICOLON);
		return new ReturnStmt(range(begin, token()), expr);
	}

	final public ThrowStmt ThrowStatement() throws ParseException {
		Expression expr;
		JavaToken begin;
		jj_consume_token(THROW);
		begin = token();
		expr = Expression();
		jj_consume_token(SEMICOLON);
		return new ThrowStmt(range(begin, token()), expr);
	}

	final public SynchronizedStmt SynchronizedStatement() throws ParseException {
		Expression expr;
		BlockStmt body;
		JavaToken begin;
		jj_consume_token(SYNCHRONIZED);
		begin = token();
		jj_consume_token(LPAREN);
		expr = Expression();
		jj_consume_token(RPAREN);
		body = Block();
		return new SynchronizedStmt(range(begin, token()), expr, body);
	}

	final public TryStmt TryStatement() throws ParseException {
		NodeList<VariableDeclarationExpr> resources = emptyList();
		BlockStmt tryBlock;
		BlockStmt finallyBlock = null;
		NodeList<CatchClause> catchs = emptyList();
		BlockStmt catchBlock;
		ModifierHolder exceptModifier;
		ReferenceType exceptionType;
		NodeList<ReferenceType> exceptionTypes = emptyList();
		Pair<SimpleName, List<ArrayBracketPair>> exceptId;
		JavaToken begin;
		JavaToken catchBegin;
		JavaToken typesBegin;
		JavaToken paramEnd;
		Type type;
		jj_consume_token(TRY);
		begin = token();
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case LPAREN: {
				resources = ResourceSpecification();
				break;
			}
			default:
				jj_la1[145] = jj_gen;
				;
		}
		tryBlock = Block();
		label_47:
		while (true) {
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case CATCH: {
					;
					break;
				}
				default:
					jj_la1[146] = jj_gen;
					break label_47;
			}
			jj_consume_token(CATCH);
			catchBegin = token();
			jj_consume_token(LPAREN);
			typesBegin = token();
			exceptModifier = Modifiers();
			exceptionType = ReferenceType();
			exceptionTypes.add(exceptionType);
			label_48:
			while (true) {
				switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
					case BIT_OR: {
						;
						break;
					}
					default:
						jj_la1[147] = jj_gen;
						break label_48;
				}
				jj_consume_token(BIT_OR);
				exceptionType = ReferenceTypeWithAnnotations();
				exceptionTypes.add(exceptionType);
			}
			exceptId = VariableDeclaratorId();
			paramEnd = token();
			jj_consume_token(RPAREN);
			catchBlock = Block();
			if (exceptionTypes.size() > 1) {
				type = new UnionType(range(exceptionTypes.get(0), exceptionTypes.get(exceptionTypes.size() - 1)), exceptionTypes);
			} else {
				type = (Type) exceptionTypes.get(0);
			}
			Parameter catchType = new Parameter(range(type.getTokenRange().get().getBegin(), paramEnd), exceptModifier.modifiers, exceptModifier.annotations, type, false,
					emptyList(), exceptId.a);
			catchs = add(catchs, new CatchClause(range(catchBegin, token()), catchType, catchBlock));
			exceptionTypes = emptyList();
		}
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case FINALLY: {
				jj_consume_token(FINALLY);
				finallyBlock = Block();
				break;
			}
			default:
				jj_la1[148] = jj_gen;
				;
		}
		return new TryStmt(range(begin, token()), resources, tryBlock, catchs, finallyBlock);
	}

	final public NodeList<VariableDeclarationExpr> ResourceSpecification() throws ParseException {
		NodeList<VariableDeclarationExpr> variables;
		jj_consume_token(LPAREN);
		variables = Resources();
		if (jj_2_44(2)) {
			jj_consume_token(SEMICOLON);
		} else {
			;
		}
		jj_consume_token(RPAREN);
		return variables;
	}

	final public NodeList<VariableDeclarationExpr> Resources() throws ParseException {
		NodeList<VariableDeclarationExpr> variables = new NodeList<VariableDeclarationExpr>();
		VariableDeclarationExpr var;
		/* this is a bit more lenient than we need to be, eg allowing access modifiers like private */
		var = VariableDeclarationExpression();
		variables.add(var);
		label_49:
		while (true) {
			if (jj_2_45(2)) {
				;
			} else {
				break label_49;
			}
			jj_consume_token(SEMICOLON);
			var = VariableDeclarationExpression();
			variables.add(var);
		}
		return variables;
	}

	/*
	 * We use productions to match >>>, >> and > so that we can keep the type declaration syntax with generics clean
	 */
	final public void RUNSIGNEDSHIFT() throws ParseException {
		if (getToken(1).kind == GT && ((CustomToken) getToken(1)).realKind == RUNSIGNEDSHIFT) {

		} else {
			jj_consume_token(-1);
			throw new ParseException();
		}
		jj_consume_token(GT);
		jj_consume_token(GT);
		jj_consume_token(GT);
	}

	final public void RSIGNEDSHIFT() throws ParseException {
		if (getToken(1).kind == GT && ((CustomToken) getToken(1)).realKind == RSIGNEDSHIFT) {

		} else {
			jj_consume_token(-1);
			throw new ParseException();
		}
		jj_consume_token(GT);
		jj_consume_token(GT);
	}

	/* Annotation syntax follows. */
	final public NodeList<AnnotationExpr> Annotations() throws ParseException {
		NodeList<AnnotationExpr> annotations = new NodeList<AnnotationExpr>();
		AnnotationExpr annotation;
		label_50:
		while (true) {
			if (jj_2_46(2147483647)) {
				;
			} else {
				break label_50;
			}
			annotation = Annotation();
			annotations = add(annotations, annotation);
		}
		return annotations;
	}

	final public AnnotationExpr Annotation() throws ParseException {
		AnnotationExpr ret;
		Name name;
		NodeList<MemberValuePair> pairs = emptyList();
		JavaToken begin;
		Expression memberVal;
		jj_consume_token(AT);
		begin = token();
		name = Name();
		if (jj_2_47(2147483647)) {
			jj_consume_token(LPAREN);
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case REQUIRES:
				case TO:
				case WITH:
				case OPEN:
				case OPENS:
				case USES:
				case MODULE:
				case EXPORTS:
				case PROVIDES:
				case TRANSITIVE:
				case IDENTIFIER: {
					pairs = MemberValuePairs();
					break;
				}
				default:
					jj_la1[149] = jj_gen;
					;
			}
			jj_consume_token(RPAREN);
			ret = new NormalAnnotationExpr(range(begin, token()), name, pairs);
		} else if (jj_2_48(2147483647)) {
			jj_consume_token(LPAREN);
			memberVal = MemberValue();
			jj_consume_token(RPAREN);
			ret = new SingleMemberAnnotationExpr(range(begin, token()), name, memberVal);
		} else {
			ret = new MarkerAnnotationExpr(range(begin, token()), name);
		}
		return ret;
	}

	final public NodeList<MemberValuePair> MemberValuePairs() throws ParseException {
		NodeList<MemberValuePair> ret = new NodeList<MemberValuePair>();
		MemberValuePair pair;
		pair = MemberValuePair();
		ret.add(pair);
		label_51:
		while (true) {
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case COMMA: {
					;
					break;
				}
				default:
					jj_la1[150] = jj_gen;
					break label_51;
			}
			jj_consume_token(COMMA);
			pair = MemberValuePair();
			ret.add(pair);
		}
		return ret;
	}

	final public MemberValuePair MemberValuePair() throws ParseException {
		SimpleName name;
		Expression value;
		JavaToken begin;
		name = SimpleName();
		begin = token();
		jj_consume_token(ASSIGN);
		value = MemberValue();
		return new MemberValuePair(range(begin, token()), name, value);
	}

	final public Expression MemberValue() throws ParseException {
		Expression ret;
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case AT: {
				ret = Annotation();
				break;
			}
			case LBRACE: {
				ret = MemberValueArrayInitializer();
				break;
			}
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FALSE:
			case FLOAT:
			case INT:
			case LONG:
			case NEW:
			case NULL:
			case SHORT:
			case SUPER:
			case THIS:
			case TRUE:
			case VOID:
			case REQUIRES:
			case TO:
			case WITH:
			case OPEN:
			case OPENS:
			case USES:
			case MODULE:
			case EXPORTS:
			case PROVIDES:
			case TRANSITIVE:
			case LONG_LITERAL:
			case INTEGER_LITERAL:
			case FLOATING_POINT_LITERAL:
			case CHARACTER_LITERAL:
			case STRING_LITERAL:
			case IDENTIFIER:
			case LPAREN:
			case BANG:
			case TILDE:
			case INCR:
			case DECR:
			case PLUS:
			case MINUS: {
				ret = ConditionalExpression();
				break;
			}
			default:
				jj_la1[151] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
		}
		return ret;
	}

	final public Expression MemberValueArrayInitializer() throws ParseException {
		NodeList<Expression> ret = emptyList();
		Expression member;
		JavaToken begin;
		jj_consume_token(LBRACE);
		begin = token();
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FALSE:
			case FLOAT:
			case INT:
			case LONG:
			case NEW:
			case NULL:
			case SHORT:
			case SUPER:
			case THIS:
			case TRUE:
			case VOID:
			case REQUIRES:
			case TO:
			case WITH:
			case OPEN:
			case OPENS:
			case USES:
			case MODULE:
			case EXPORTS:
			case PROVIDES:
			case TRANSITIVE:
			case LONG_LITERAL:
			case INTEGER_LITERAL:
			case FLOATING_POINT_LITERAL:
			case CHARACTER_LITERAL:
			case STRING_LITERAL:
			case IDENTIFIER:
			case LPAREN:
			case LBRACE:
			case AT:
			case BANG:
			case TILDE:
			case INCR:
			case DECR:
			case PLUS:
			case MINUS: {
				member = MemberValue();
				ret.add(member);
				label_52:
				while (true) {
					if (jj_2_49(2)) {
						;
					} else {
						break label_52;
					}
					jj_consume_token(COMMA);
					member = MemberValue();
					ret.add(member);
				}
				break;
			}
			default:
				jj_la1[152] = jj_gen;
				;
		}
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case COMMA: {
				jj_consume_token(COMMA);
				break;
			}
			default:
				jj_la1[153] = jj_gen;
				;
		}
		jj_consume_token(RBRACE);
		return new ArrayInitializerExpr(range(begin, token()), ret);
	}

	/* Annotation Types. */
	final public AnnotationDeclaration AnnotationTypeDeclaration(ModifierHolder modifier) throws ParseException {
		SimpleName name;
		NodeList<BodyDeclaration<?>> members = emptyList();
		JavaToken begin = modifier.begin;
		jj_consume_token(AT);
		begin = begin.orIfInvalid(token());
		jj_consume_token(INTERFACE);
		name = SimpleName();
		members = AnnotationTypeBody();
		return new AnnotationDeclaration(range(begin, token()), modifier.modifiers, modifier.annotations, name, members);
	}

	final public NodeList<BodyDeclaration<?>> AnnotationTypeBody() throws ParseException {
		NodeList<BodyDeclaration<?>> ret = emptyList();
		BodyDeclaration member;
		jj_consume_token(LBRACE);
		label_53:
		while (true) {
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case ABSTRACT:
				case BOOLEAN:
				case BYTE:
				case CHAR:
				case CLASS:
				case _DEFAULT:
				case DOUBLE:
				case ENUM:
				case FINAL:
				case FLOAT:
				case INT:
				case INTERFACE:
				case LONG:
				case NATIVE:
				case PRIVATE:
				case PROTECTED:
				case PUBLIC:
				case SHORT:
				case STATIC:
				case STRICTFP:
				case SYNCHRONIZED:
				case TRANSIENT:
				case VOLATILE:
				case REQUIRES:
				case TO:
				case WITH:
				case OPEN:
				case OPENS:
				case USES:
				case MODULE:
				case EXPORTS:
				case PROVIDES:
				case TRANSITIVE:
				case IDENTIFIER:
				case SEMICOLON:
				case AT: {
					;
					break;
				}
				default:
					jj_la1[154] = jj_gen;
					break label_53;
			}
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case ABSTRACT:
				case BOOLEAN:
				case BYTE:
				case CHAR:
				case CLASS:
				case _DEFAULT:
				case DOUBLE:
				case ENUM:
				case FINAL:
				case FLOAT:
				case INT:
				case INTERFACE:
				case LONG:
				case NATIVE:
				case PRIVATE:
				case PROTECTED:
				case PUBLIC:
				case SHORT:
				case STATIC:
				case STRICTFP:
				case SYNCHRONIZED:
				case TRANSIENT:
				case VOLATILE:
				case REQUIRES:
				case TO:
				case WITH:
				case OPEN:
				case OPENS:
				case USES:
				case MODULE:
				case EXPORTS:
				case PROVIDES:
				case TRANSITIVE:
				case IDENTIFIER:
				case AT: {
					member = AnnotationBodyDeclaration();
					ret = addWhenNotNull(ret, member);
					break;
				}
				case SEMICOLON: {
					jj_consume_token(SEMICOLON);
					break;
				}
				default:
					jj_la1[155] = jj_gen;
					jj_consume_token(-1);
					throw new ParseException();
			}
		}
		jj_consume_token(RBRACE);
		return ret;
	}

	final public BodyDeclaration<?> AnnotationBodyDeclaration() throws ParseException {
		ModifierHolder modifier;
		BodyDeclaration ret;
		modifier = Modifiers();
		if (jj_2_50(2147483647)) {
			ret = AnnotationTypeMemberDeclaration(modifier);
		} else {
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case CLASS:
				case INTERFACE: {
					ret = ClassOrInterfaceDeclaration(modifier);
					break;
				}
				case ENUM: {
					ret = EnumDeclaration(modifier);
					break;
				}
				case AT: {
					ret = AnnotationTypeDeclaration(modifier);
					break;
				}
				case BOOLEAN:
				case BYTE:
				case CHAR:
				case DOUBLE:
				case FLOAT:
				case INT:
				case LONG:
				case SHORT:
				case REQUIRES:
				case TO:
				case WITH:
				case OPEN:
				case OPENS:
				case USES:
				case MODULE:
				case EXPORTS:
				case PROVIDES:
				case TRANSITIVE:
				case IDENTIFIER: {
					ret = FieldDeclaration(modifier);
					break;
				}
				default:
					jj_la1[156] = jj_gen;
					jj_consume_token(-1);
					throw new ParseException();
			}
		}
		return ret;
	}

	final public AnnotationMemberDeclaration AnnotationTypeMemberDeclaration(ModifierHolder modifier) throws ParseException {
		Type type;
		SimpleName name;
		Expression defaultVal = null;
		type = Type();
		name = SimpleName();
		jj_consume_token(LPAREN);
		jj_consume_token(RPAREN);
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case _DEFAULT: {
				defaultVal = DefaultValue();
				break;
			}
			default:
				jj_la1[157] = jj_gen;
				;
		}
		jj_consume_token(SEMICOLON);
		JavaToken begin = modifier.begin.orIfInvalid(type.getTokenRange().get().getBegin());
		return new AnnotationMemberDeclaration(range(begin, token()), modifier.modifiers, modifier.annotations, type, name, defaultVal);
	}

	final public Expression DefaultValue() throws ParseException {
		Expression ret;
		jj_consume_token(_DEFAULT);
		ret = MemberValue();
		return ret;
	}

	/* Module syntax follows */
	final public

			ModuleStmt ModuleStmt() throws ParseException {
		ModifierHolder modifiers;
		Name name;
		Name tmpName;
		NodeList<Name> names = emptyList();
		Type type;
		Type tmpType;
		NodeList<Type> types = emptyList();
		JavaToken begin;
		ModuleStmt stmt = new ModuleRequiresStmt();
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case REQUIRES: {
				jj_consume_token(REQUIRES);
				begin = token();
				modifiers = Modifiers();
				name = Name();
				jj_consume_token(SEMICOLON);
				stmt = new ModuleRequiresStmt(range(begin, token()), modifiers.modifiers, name);
				break;
			}
			case EXPORTS: {
				jj_consume_token(EXPORTS);
				begin = token();
				name = Name();
				switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
					case TO: {
						jj_consume_token(TO);
						tmpName = Name();
						names.add(tmpName);
						label_54:
						while (true) {
							switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
								case COMMA: {
									;
									break;
								}
								default:
									jj_la1[158] = jj_gen;
									break label_54;
							}
							jj_consume_token(COMMA);
							tmpName = Name();
							names.add(tmpName);
						}
						break;
					}
					default:
						jj_la1[159] = jj_gen;
						;
				}
				jj_consume_token(SEMICOLON);
				stmt = new ModuleExportsStmt(range(begin, token()), name, names);
				break;
			}
			case OPENS: {
				jj_consume_token(OPENS);
				begin = token();
				name = Name();
				switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
					case TO: {
						jj_consume_token(TO);
						tmpName = Name();
						names.add(tmpName);
						label_55:
						while (true) {
							switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
								case COMMA: {
									;
									break;
								}
								default:
									jj_la1[160] = jj_gen;
									break label_55;
							}
							jj_consume_token(COMMA);
							tmpName = Name();
							names.add(tmpName);
						}
						break;
					}
					default:
						jj_la1[161] = jj_gen;
						;
				}
				jj_consume_token(SEMICOLON);
				stmt = new ModuleOpensStmt(range(begin, token()), name, names);
				break;
			}
			case USES: {
				jj_consume_token(USES);
				begin = token();
				type = Type();
				jj_consume_token(SEMICOLON);
				stmt = new ModuleUsesStmt(range(begin, token()), type);
				break;
			}
			case PROVIDES: {
				jj_consume_token(PROVIDES);
				begin = token();
				type = Type();
				jj_consume_token(WITH);
				tmpType = Type();
				types.add(tmpType);
				label_56:
				while (true) {
					switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
						case COMMA: {
							;
							break;
						}
						default:
							jj_la1[162] = jj_gen;
							break label_56;
					}
					jj_consume_token(COMMA);
					tmpType = Type();
					types.add(tmpType);
				}
				jj_consume_token(SEMICOLON);
				stmt = new ModuleProvidesStmt(range(begin, token()), type, types);
				break;
			}
			default:
				jj_la1[163] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
		}
		return stmt;
	}

	final public ModuleDeclaration ModuleDeclaration(ModifierHolder modifier) throws ParseException {
		NodeList<ModuleStmt> statements = new NodeList<ModuleStmt>();
		boolean open = false;
		ModuleStmt st;
		Name name;
		JavaToken begin = modifier.begin;
		switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
			case OPEN: {
				jj_consume_token(OPEN);
				open = true;
				begin = begin.orIfInvalid(token());
				break;
			}
			default:
				jj_la1[164] = jj_gen;
				;
		}
		jj_consume_token(MODULE);
		begin = begin.orIfInvalid(token());
		name = Name();
		jj_consume_token(LBRACE);
		label_57:
		while (true) {
			switch ((jj_ntk == -1) ? jj_ntk_f() : jj_ntk) {
				case REQUIRES:
				case OPENS:
				case USES:
				case EXPORTS:
				case PROVIDES: {
					;
					break;
				}
				default:
					jj_la1[165] = jj_gen;
					break label_57;
			}
			st = ModuleStmt();
			statements = add(statements, st);
		}
		jj_consume_token(RBRACE);
		return new ModuleDeclaration(range(begin, token()), modifier.annotations, name, open, statements);
	}

	private boolean jj_2_1(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_1();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(0, xla);
		}
	}

	private boolean jj_2_2(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_2();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(1, xla);
		}
	}

	private boolean jj_2_3(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_3();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(2, xla);
		}
	}

	private boolean jj_2_4(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_4();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(3, xla);
		}
	}

	private boolean jj_2_5(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_5();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(4, xla);
		}
	}

	private boolean jj_2_6(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_6();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(5, xla);
		}
	}

	private boolean jj_2_7(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_7();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(6, xla);
		}
	}

	private boolean jj_2_8(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_8();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(7, xla);
		}
	}

	private boolean jj_2_9(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_9();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(8, xla);
		}
	}

	private boolean jj_2_10(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_10();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(9, xla);
		}
	}

	private boolean jj_2_11(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_11();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(10, xla);
		}
	}

	private boolean jj_2_12(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_12();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(11, xla);
		}
	}

	private boolean jj_2_13(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_13();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(12, xla);
		}
	}

	private boolean jj_2_14(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_14();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(13, xla);
		}
	}

	private boolean jj_2_15(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_15();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(14, xla);
		}
	}

	private boolean jj_2_16(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_16();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(15, xla);
		}
	}

	private boolean jj_2_17(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_17();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(16, xla);
		}
	}

	private boolean jj_2_18(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_18();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(17, xla);
		}
	}

	private boolean jj_2_19(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_19();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(18, xla);
		}
	}

	private boolean jj_2_20(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_20();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(19, xla);
		}
	}

	private boolean jj_2_21(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_21();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(20, xla);
		}
	}

	private boolean jj_2_22(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_22();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(21, xla);
		}
	}

	private boolean jj_2_23(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_23();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(22, xla);
		}
	}

	private boolean jj_2_24(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_24();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(23, xla);
		}
	}

	private boolean jj_2_25(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_25();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(24, xla);
		}
	}

	private boolean jj_2_26(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_26();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(25, xla);
		}
	}

	private boolean jj_2_27(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_27();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(26, xla);
		}
	}

	private boolean jj_2_28(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_28();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(27, xla);
		}
	}

	private boolean jj_2_29(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_29();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(28, xla);
		}
	}

	private boolean jj_2_30(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_30();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(29, xla);
		}
	}

	private boolean jj_2_31(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_31();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(30, xla);
		}
	}

	private boolean jj_2_32(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_32();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(31, xla);
		}
	}

	private boolean jj_2_33(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_33();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(32, xla);
		}
	}

	private boolean jj_2_34(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_34();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(33, xla);
		}
	}

	private boolean jj_2_35(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_35();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(34, xla);
		}
	}

	private boolean jj_2_36(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_36();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(35, xla);
		}
	}

	private boolean jj_2_37(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_37();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(36, xla);
		}
	}

	private boolean jj_2_38(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_38();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(37, xla);
		}
	}

	private boolean jj_2_39(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_39();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(38, xla);
		}
	}

	private boolean jj_2_40(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_40();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(39, xla);
		}
	}

	private boolean jj_2_41(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_41();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(40, xla);
		}
	}

	private boolean jj_2_42(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_42();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(41, xla);
		}
	}

	private boolean jj_2_43(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_43();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(42, xla);
		}
	}

	private boolean jj_2_44(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_44();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(43, xla);
		}
	}

	private boolean jj_2_45(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_45();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(44, xla);
		}
	}

	private boolean jj_2_46(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_46();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(45, xla);
		}
	}

	private boolean jj_2_47(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_47();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(46, xla);
		}
	}

	private boolean jj_2_48(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_48();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(47, xla);
		}
	}

	private boolean jj_2_49(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_49();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(48, xla);
		}
	}

	private boolean jj_2_50(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_50();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(49, xla);
		}
	}

	private boolean jj_3R_144() {
		if (jj_scan_token(ORASSIGN))
			return true;
		return false;
	}

	private boolean jj_3R_143() {
		if (jj_scan_token(XORASSIGN))
			return true;
		return false;
	}

	private boolean jj_3R_142() {
		if (jj_scan_token(ANDASSIGN))
			return true;
		return false;
	}

	private boolean jj_3R_417() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_424()) {
			jj_scanpos = xsp;
			if (jj_3R_425())
				return true;
		}
		if (jj_scan_token(COLON))
			return true;
		if (jj_3R_175())
			return true;
		return false;
	}

	private boolean jj_3R_72() {
		if (jj_3R_112())
			return true;
		return false;
	}

	private boolean jj_3R_141() {
		if (jj_scan_token(RUNSIGNEDSHIFTASSIGN))
			return true;
		return false;
	}

	private boolean jj_3R_140() {
		if (jj_scan_token(RSIGNEDSHIFTASSIGN))
			return true;
		return false;
	}

	private boolean jj_3R_71() {
		if (jj_scan_token(_DEFAULT))
			return true;
		return false;
	}

	private boolean jj_3R_139() {
		if (jj_scan_token(LSHIFTASSIGN))
			return true;
		return false;
	}

	private boolean jj_3R_138() {
		if (jj_scan_token(MINUSASSIGN))
			return true;
		return false;
	}

	private boolean jj_3R_70() {
		if (jj_scan_token(TRANSITIVE))
			return true;
		return false;
	}

	private boolean jj_3R_137() {
		if (jj_scan_token(PLUSASSIGN))
			return true;
		return false;
	}

	private boolean jj_3R_136() {
		if (jj_scan_token(REMASSIGN))
			return true;
		return false;
	}

	private boolean jj_3R_69() {
		if (jj_scan_token(STRICTFP))
			return true;
		return false;
	}

	private boolean jj_3R_135() {
		if (jj_scan_token(SLASHASSIGN))
			return true;
		return false;
	}

	private boolean jj_3R_134() {
		if (jj_scan_token(STARASSIGN))
			return true;
		return false;
	}

	private boolean jj_3R_68() {
		if (jj_scan_token(VOLATILE))
			return true;
		return false;
	}

	private boolean jj_3R_133() {
		if (jj_scan_token(ASSIGN))
			return true;
		return false;
	}

	private boolean jj_3R_403() {
		if (jj_3R_417())
			return true;
		return false;
	}

	private boolean jj_3R_67() {
		if (jj_scan_token(TRANSIENT))
			return true;
		return false;
	}

	private boolean jj_3R_66() {
		if (jj_scan_token(NATIVE))
			return true;
		return false;
	}

	private boolean jj_3R_89() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_133()) {
			jj_scanpos = xsp;
			if (jj_3R_134()) {
				jj_scanpos = xsp;
				if (jj_3R_135()) {
					jj_scanpos = xsp;
					if (jj_3R_136()) {
						jj_scanpos = xsp;
						if (jj_3R_137()) {
							jj_scanpos = xsp;
							if (jj_3R_138()) {
								jj_scanpos = xsp;
								if (jj_3R_139()) {
									jj_scanpos = xsp;
									if (jj_3R_140()) {
										jj_scanpos = xsp;
										if (jj_3R_141()) {
											jj_scanpos = xsp;
											if (jj_3R_142()) {
												jj_scanpos = xsp;
												if (jj_3R_143()) {
													jj_scanpos = xsp;
													if (jj_3R_144())
														return true;
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	private boolean jj_3R_65() {
		if (jj_scan_token(SYNCHRONIZED))
			return true;
		return false;
	}

	private boolean jj_3R_273() {
		if (jj_scan_token(SWITCH))
			return true;
		if (jj_scan_token(LPAREN))
			return true;
		if (jj_3R_90())
			return true;
		if (jj_scan_token(RPAREN))
			return true;
		if (jj_scan_token(LBRACE))
			return true;
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_403()) {
				jj_scanpos = xsp;
				break;
			}
		}
		if (jj_scan_token(RBRACE))
			return true;
		return false;
	}

	private boolean jj_3R_64() {
		if (jj_scan_token(ABSTRACT))
			return true;
		return false;
	}

	private boolean jj_3R_435() {
		if (jj_3R_87())
			return true;
		return false;
	}

	private boolean jj_3R_63() {
		if (jj_scan_token(FINAL))
			return true;
		return false;
	}

	private boolean jj_3R_316() {
		if (jj_3R_87())
			return true;
		return false;
	}

	private boolean jj_3R_62() {
		if (jj_scan_token(PRIVATE))
			return true;
		return false;
	}

	private boolean jj_3R_61() {
		if (jj_scan_token(PROTECTED))
			return true;
		return false;
	}

	private boolean jj_3R_352() {
		if (jj_scan_token(ARROW))
			return true;
		if (jj_3R_315())
			return true;
		return false;
	}

	private boolean jj_3R_351() {
		if (jj_scan_token(DOUBLECOLON))
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_435())
			jj_scanpos = xsp;
		xsp = jj_scanpos;
		if (jj_3R_436()) {
			jj_scanpos = xsp;
			if (jj_scan_token(69))
				return true;
		}
		return false;
	}

	private boolean jj_3R_60() {
		if (jj_scan_token(STATIC))
			return true;
		return false;
	}

	private boolean jj_3R_350() {
		if (jj_3R_89())
			return true;
		if (jj_3R_90())
			return true;
		return false;
	}

	private boolean jj_3R_59() {
		if (jj_scan_token(PUBLIC))
			return true;
		return false;
	}

	private boolean jj_3R_292() {
		if (jj_scan_token(DOUBLECOLON))
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_316())
			jj_scanpos = xsp;
		xsp = jj_scanpos;
		if (jj_3R_317()) {
			jj_scanpos = xsp;
			if (jj_scan_token(69))
				return true;
		}
		return false;
	}

	private boolean jj_3R_349() {
		if (jj_scan_token(DECR))
			return true;
		return false;
	}

	private boolean jj_3_3() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_59()) {
			jj_scanpos = xsp;
			if (jj_3R_60()) {
				jj_scanpos = xsp;
				if (jj_3R_61()) {
					jj_scanpos = xsp;
					if (jj_3R_62()) {
						jj_scanpos = xsp;
						if (jj_3R_63()) {
							jj_scanpos = xsp;
							if (jj_3R_64()) {
								jj_scanpos = xsp;
								if (jj_3R_65()) {
									jj_scanpos = xsp;
									if (jj_3R_66()) {
										jj_scanpos = xsp;
										if (jj_3R_67()) {
											jj_scanpos = xsp;
											if (jj_3R_68()) {
												jj_scanpos = xsp;
												if (jj_3R_69()) {
													jj_scanpos = xsp;
													if (jj_3R_70()) {
														jj_scanpos = xsp;
														if (jj_3R_71()) {
															jj_scanpos = xsp;
															if (jj_3R_72())
																return true;
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	private boolean jj_3R_348() {
		if (jj_scan_token(INCR))
			return true;
		return false;
	}

	private boolean jj_3R_330() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_348()) {
			jj_scanpos = xsp;
			if (jj_3R_349()) {
				jj_scanpos = xsp;
				if (jj_3R_350()) {
					jj_scanpos = xsp;
					if (jj_3R_351()) {
						jj_scanpos = xsp;
						if (jj_3R_352())
							return true;
					}
				}
			}
		}
		return false;
	}

	private boolean jj_3R_106() {
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3_3()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_306() {
		if (jj_3R_313())
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_330())
			jj_scanpos = xsp;
		return false;
	}

	private boolean jj_3R_305() {
		if (jj_3R_250())
			return true;
		return false;
	}

	private boolean jj_3_22() {
		if (jj_3R_89())
			return true;
		if (jj_3R_90())
			return true;
		return false;
	}

	private boolean jj_3R_291() {
		if (jj_scan_token(ARROW))
			return true;
		if (jj_3R_315())
			return true;
		return false;
	}

	private boolean jj_3_41() {
		if (jj_3R_108())
			return true;
		return false;
	}

	private boolean jj_3R_247() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3_22()) {
			jj_scanpos = xsp;
			if (jj_3R_291()) {
				jj_scanpos = xsp;
				if (jj_3R_292())
					return true;
			}
		}
		return false;
	}

	private boolean jj_3R_272() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3_41()) {
			jj_scanpos = xsp;
			if (jj_3R_305()) {
				jj_scanpos = xsp;
				if (jj_3R_306())
					return true;
			}
		}
		if (jj_scan_token(SEMICOLON))
			return true;
		return false;
	}

	private boolean jj_3R_90() {
		if (jj_3R_145())
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_247())
			jj_scanpos = xsp;
		return false;
	}

	private boolean jj_3R_337() {
		if (jj_3R_118())
			return true;
		return false;
	}

	private boolean jj_3R_58() {
		if (jj_3R_86())
			return true;
		if (jj_scan_token(PACKAGE))
			return true;
		if (jj_3R_111())
			return true;
		if (jj_scan_token(SEMICOLON))
			return true;
		return false;
	}

	private boolean jj_3R_336() {
		if (jj_3R_90())
			return true;
		return false;
	}

	private boolean jj_3R_315() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_336()) {
			jj_scanpos = xsp;
			if (jj_3R_337())
				return true;
		}
		return false;
	}

	private boolean jj_3R_75() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_scan_token(97)) {
			jj_scanpos = xsp;
			if (jj_scan_token(91)) {
				jj_scanpos = xsp;
				if (jj_scan_token(92)) {
					jj_scanpos = xsp;
					if (jj_scan_token(93)) {
						jj_scanpos = xsp;
						if (jj_scan_token(94)) {
							jj_scanpos = xsp;
							if (jj_scan_token(95)) {
								jj_scanpos = xsp;
								if (jj_scan_token(96)) {
									jj_scanpos = xsp;
									if (jj_scan_token(98)) {
										jj_scanpos = xsp;
										if (jj_scan_token(99)) {
											jj_scanpos = xsp;
											if (jj_scan_token(100)) {
												jj_scanpos = xsp;
												if (jj_scan_token(114))
													return true;
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	private boolean jj_3_2() {
		if (jj_3R_58())
			return true;
		return false;
	}

	private boolean jj_3R_271() {
		if (jj_scan_token(SEMICOLON))
			return true;
		return false;
	}

	private boolean jj_3_21() {
		if (jj_scan_token(DOT))
			return true;
		if (jj_3R_86())
			return true;
		if (jj_3R_75())
			return true;
		return false;
	}

	private boolean jj_3R_88() {
		if (jj_3R_75())
			return true;
		return false;
	}

	private boolean jj_3_1() {
		if (jj_scan_token(SEMICOLON))
			return true;
		return false;
	}

	private boolean jj_3_40() {
		if (jj_3R_107())
			return true;
		return false;
	}

	private boolean jj_3R_107() {
		if (jj_3R_106())
			return true;
		if (jj_3R_76())
			return true;
		if (jj_3R_166())
			return true;
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_167()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3_39() {
		if (jj_3R_106())
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_scan_token(46)) {
			jj_scanpos = xsp;
			if (jj_scan_token(66))
				return true;
		}
		return false;
	}

	private boolean jj_3R_172() {
		if (jj_scan_token(DOT))
			return true;
		if (jj_3R_86())
			return true;
		if (jj_3R_75())
			return true;
		return false;
	}

	private boolean jj_3R_128() {
		if (jj_3R_190())
			return true;
		return false;
	}

	private boolean jj_3R_111() {
		if (jj_3R_86())
			return true;
		if (jj_3R_75())
			return true;
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_172()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_127() {
		if (jj_3R_107())
			return true;
		if (jj_scan_token(SEMICOLON))
			return true;
		return false;
	}

	private boolean jj_3R_402() {
		if (jj_scan_token(COLON))
			return true;
		if (jj_3R_90())
			return true;
		return false;
	}

	private boolean jj_3R_164() {
		if (jj_3R_76())
			return true;
		return false;
	}

	private boolean jj_3R_126() {
		if (jj_3R_106())
			return true;
		if (jj_3R_189())
			return true;
		return false;
	}

	private boolean jj_3R_163() {
		if (jj_scan_token(VOID))
			return true;
		return false;
	}

	private boolean jj_3R_84() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_126()) {
			jj_scanpos = xsp;
			if (jj_3R_127()) {
				jj_scanpos = xsp;
				if (jj_3R_128())
					return true;
			}
		}
		return false;
	}

	private boolean jj_3R_101() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_163()) {
			jj_scanpos = xsp;
			if (jj_3R_164())
				return true;
		}
		return false;
	}

	private boolean jj_3R_156() {
		if (jj_scan_token(DOUBLE))
			return true;
		return false;
	}

	private boolean jj_3R_155() {
		if (jj_scan_token(FLOAT))
			return true;
		return false;
	}

	private boolean jj_3R_154() {
		if (jj_scan_token(LONG))
			return true;
		return false;
	}

	private boolean jj_3R_118() {
		if (jj_scan_token(LBRACE))
			return true;
		if (jj_3R_175())
			return true;
		if (jj_scan_token(RBRACE))
			return true;
		return false;
	}

	private boolean jj_3R_153() {
		if (jj_scan_token(INT))
			return true;
		return false;
	}

	private boolean jj_3R_152() {
		if (jj_scan_token(SHORT))
			return true;
		return false;
	}

	private boolean jj_3R_151() {
		if (jj_scan_token(BYTE))
			return true;
		return false;
	}

	private boolean jj_3R_150() {
		if (jj_scan_token(CHAR))
			return true;
		return false;
	}

	private boolean jj_3R_149() {
		if (jj_scan_token(BOOLEAN))
			return true;
		return false;
	}

	private boolean jj_3R_105() {
		if (jj_3R_88())
			return true;
		if (jj_scan_token(COLON))
			return true;
		if (jj_3R_190())
			return true;
		return false;
	}

	private boolean jj_3R_347() {
		if (jj_scan_token(SUPER))
			return true;
		if (jj_3R_86())
			return true;
		if (jj_3R_85())
			return true;
		return false;
	}

	private boolean jj_3R_97() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_149()) {
			jj_scanpos = xsp;
			if (jj_3R_150()) {
				jj_scanpos = xsp;
				if (jj_3R_151()) {
					jj_scanpos = xsp;
					if (jj_3R_152()) {
						jj_scanpos = xsp;
						if (jj_3R_153()) {
							jj_scanpos = xsp;
							if (jj_3R_154()) {
								jj_scanpos = xsp;
								if (jj_3R_155()) {
									jj_scanpos = xsp;
									if (jj_3R_156())
										return true;
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	private boolean jj_3R_219() {
		if (jj_scan_token(COMMA))
			return true;
		if (jj_3R_194())
			return true;
		return false;
	}

	private boolean jj_3R_346() {
		if (jj_scan_token(EXTENDS))
			return true;
		if (jj_3R_86())
			return true;
		if (jj_3R_85())
			return true;
		return false;
	}

	private boolean jj_3R_329() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_346()) {
			jj_scanpos = xsp;
			if (jj_3R_347())
				return true;
		}
		return false;
	}

	private boolean jj_3R_270() {
		if (jj_scan_token(ASSERT))
			return true;
		if (jj_3R_90())
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_402())
			jj_scanpos = xsp;
		if (jj_scan_token(SEMICOLON))
			return true;
		return false;
	}

	private boolean jj_3R_235() {
		if (jj_3R_283())
			return true;
		return false;
	}

	private boolean jj_3R_234() {
		if (jj_3R_282())
			return true;
		return false;
	}

	private boolean jj_3R_233() {
		if (jj_3R_281())
			return true;
		return false;
	}

	private boolean jj_3R_285() {
		if (jj_scan_token(HOOK))
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_329())
			jj_scanpos = xsp;
		return false;
	}

	private boolean jj_3R_232() {
		if (jj_3R_280())
			return true;
		return false;
	}

	private boolean jj_3R_231() {
		if (jj_3R_279())
			return true;
		return false;
	}

	private boolean jj_3R_230() {
		if (jj_3R_278())
			return true;
		return false;
	}

	private boolean jj_3R_229() {
		if (jj_3R_277())
			return true;
		return false;
	}

	private boolean jj_3R_228() {
		if (jj_3R_276())
			return true;
		return false;
	}

	private boolean jj_3R_227() {
		if (jj_3R_275())
			return true;
		return false;
	}

	private boolean jj_3R_226() {
		if (jj_3R_274())
			return true;
		return false;
	}

	private boolean jj_3R_240() {
		if (jj_3R_285())
			return true;
		return false;
	}

	private boolean jj_3R_225() {
		if (jj_3R_273())
			return true;
		return false;
	}

	private boolean jj_3R_239() {
		if (jj_3R_76())
			return true;
		return false;
	}

	private boolean jj_3R_224() {
		if (jj_3R_272())
			return true;
		return false;
	}

	private boolean jj_3R_223() {
		if (jj_3R_271())
			return true;
		return false;
	}

	private boolean jj_3R_194() {
		if (jj_3R_86())
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_239()) {
			jj_scanpos = xsp;
			if (jj_3R_240())
				return true;
		}
		return false;
	}

	private boolean jj_3R_222() {
		if (jj_3R_118())
			return true;
		return false;
	}

	private boolean jj_3R_221() {
		if (jj_3R_270())
			return true;
		return false;
	}

	private boolean jj_3_38() {
		if (jj_3R_105())
			return true;
		return false;
	}

	private boolean jj_3R_132() {
		if (jj_3R_194())
			return true;
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_219()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_87() {
		if (jj_scan_token(LT))
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_132())
			jj_scanpos = xsp;
		if (jj_scan_token(GT))
			return true;
		return false;
	}

	private boolean jj_3R_190() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3_38()) {
			jj_scanpos = xsp;
			if (jj_3R_221()) {
				jj_scanpos = xsp;
				if (jj_3R_222()) {
					jj_scanpos = xsp;
					if (jj_3R_223()) {
						jj_scanpos = xsp;
						if (jj_3R_224()) {
							jj_scanpos = xsp;
							if (jj_3R_225()) {
								jj_scanpos = xsp;
								if (jj_3R_226()) {
									jj_scanpos = xsp;
									if (jj_3R_227()) {
										jj_scanpos = xsp;
										if (jj_3R_228()) {
											jj_scanpos = xsp;
											if (jj_3R_229()) {
												jj_scanpos = xsp;
												if (jj_3R_230()) {
													jj_scanpos = xsp;
													if (jj_3R_231()) {
														jj_scanpos = xsp;
														if (jj_3R_232()) {
															jj_scanpos = xsp;
															if (jj_3R_233()) {
																jj_scanpos = xsp;
																if (jj_3R_234()) {
																	jj_scanpos = xsp;
																	if (jj_3R_235())
																		return true;
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	private boolean jj_3_20() {
		if (jj_3R_87())
			return true;
		return false;
	}

	private boolean jj_3_19() {
		if (jj_scan_token(DOT))
			return true;
		if (jj_3R_86())
			return true;
		if (jj_3R_88())
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3_20())
			jj_scanpos = xsp;
		return false;
	}

	private boolean jj_3_36() {
		if (jj_3R_103())
			return true;
		return false;
	}

	private boolean jj_3R_104() {
		if (jj_3R_90())
			return true;
		return false;
	}

	private boolean jj_3R_328() {
		if (jj_3R_176())
			return true;
		return false;
	}

	private boolean jj_3_18() {
		if (jj_3R_87())
			return true;
		return false;
	}

	private boolean jj_3_37() {
		if (jj_3R_86())
			return true;
		if (jj_scan_token(LBRACKET))
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_104())
			jj_scanpos = xsp;
		if (jj_scan_token(RBRACKET))
			return true;
		return false;
	}

	private boolean jj_3R_382() {
		if (jj_scan_token(THROWS))
			return true;
		if (jj_3R_394())
			return true;
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_395()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_301() {
		Token xsp;
		if (jj_3_37())
			return true;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3_37()) {
				jj_scanpos = xsp;
				break;
			}
		}
		xsp = jj_scanpos;
		if (jj_3R_328())
			jj_scanpos = xsp;
		return false;
	}

	private boolean jj_3R_192() {
		if (jj_3R_88())
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3_18())
			jj_scanpos = xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3_19()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_296() {
		if (jj_scan_token(COMMA))
			return true;
		if (jj_3R_90())
			return true;
		return false;
	}

	private boolean jj_3R_304() {
		if (jj_3R_178())
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3_36())
			jj_scanpos = xsp;
		return false;
	}

	private boolean jj_3R_303() {
		if (jj_3R_301())
			return true;
		return false;
	}

	private boolean jj_3_17() {
		if (jj_3R_86())
			return true;
		if (jj_scan_token(LBRACKET))
			return true;
		return false;
	}

	private boolean jj_3R_302() {
		if (jj_3R_87())
			return true;
		if (jj_3R_86())
			return true;
		return false;
	}

	private boolean jj_3R_269() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_302())
			jj_scanpos = xsp;
		if (jj_3R_294())
			return true;
		xsp = jj_scanpos;
		if (jj_3R_303()) {
			jj_scanpos = xsp;
			if (jj_3R_304())
				return true;
		}
		return false;
	}

	private boolean jj_3R_294() {
		if (jj_3R_86())
			return true;
		if (jj_3R_192())
			return true;
		return false;
	}

	private boolean jj_3_16() {
		if (jj_3R_86())
			return true;
		if (jj_scan_token(LBRACKET))
			return true;
		return false;
	}

	private boolean jj_3R_193() {
		if (jj_3R_116())
			return true;
		return false;
	}

	private boolean jj_3R_268() {
		if (jj_3R_97())
			return true;
		if (jj_3R_301())
			return true;
		return false;
	}

	private boolean jj_3R_215() {
		if (jj_scan_token(NEW))
			return true;
		if (jj_3R_86())
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_268()) {
			jj_scanpos = xsp;
			if (jj_3R_269())
				return true;
		}
		return false;
	}

	private boolean jj_3R_191() {
		if (jj_3R_116())
			return true;
		return false;
	}

	private boolean jj_3R_116() {
		if (jj_3R_86())
			return true;
		if (jj_scan_token(LBRACKET))
			return true;
		if (jj_scan_token(RBRACKET))
			return true;
		return false;
	}

	private boolean jj_3R_254() {
		if (jj_3R_90())
			return true;
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_296()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_210() {
		if (jj_3R_254())
			return true;
		return false;
	}

	private boolean jj_3R_130() {
		if (jj_3R_192())
			return true;
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_193()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_129() {
		if (jj_3R_97())
			return true;
		Token xsp;
		if (jj_3R_191())
			return true;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_191()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_178() {
		if (jj_scan_token(LPAREN))
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_210())
			jj_scanpos = xsp;
		if (jj_scan_token(RPAREN))
			return true;
		return false;
	}

	private boolean jj_3R_85() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_129()) {
			jj_scanpos = xsp;
			if (jj_3R_130())
				return true;
		}
		return false;
	}

	private boolean jj_3R_325() {
		if (jj_scan_token(FALSE))
			return true;
		return false;
	}

	private boolean jj_3R_298() {
		if (jj_scan_token(NULL))
			return true;
		return false;
	}

	private boolean jj_3R_324() {
		if (jj_scan_token(TRUE))
			return true;
		return false;
	}

	private boolean jj_3R_115() {
		if (jj_3R_97())
			return true;
		return false;
	}

	private boolean jj_3_15() {
		if (jj_3R_85())
			return true;
		return false;
	}

	private boolean jj_3R_76() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3_15()) {
			jj_scanpos = xsp;
			if (jj_3R_115())
				return true;
		}
		return false;
	}

	private boolean jj_3R_297() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_324()) {
			jj_scanpos = xsp;
			if (jj_3R_325())
				return true;
		}
		return false;
	}

	private boolean jj_3R_261() {
		if (jj_3R_298())
			return true;
		return false;
	}

	private boolean jj_3R_260() {
		if (jj_3R_297())
			return true;
		return false;
	}

	private boolean jj_3R_259() {
		if (jj_scan_token(STRING_LITERAL))
			return true;
		return false;
	}

	private boolean jj_3R_117() {
		if (jj_scan_token(STATIC))
			return true;
		return false;
	}

	private boolean jj_3R_78() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_117())
			jj_scanpos = xsp;
		if (jj_3R_118())
			return true;
		return false;
	}

	private boolean jj_3R_258() {
		if (jj_scan_token(CHARACTER_LITERAL))
			return true;
		return false;
	}

	private boolean jj_3_14() {
		if (jj_3R_84())
			return true;
		return false;
	}

	private boolean jj_3R_257() {
		if (jj_scan_token(FLOATING_POINT_LITERAL))
			return true;
		return false;
	}

	private boolean jj_3R_175() {
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3_14()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_442() {
		if (jj_3R_443())
			return true;
		return false;
	}

	private boolean jj_3_12() {
		if (jj_3R_82())
			return true;
		if (jj_scan_token(DOT))
			return true;
		return false;
	}

	private boolean jj_3R_256() {
		if (jj_scan_token(LONG_LITERAL))
			return true;
		return false;
	}

	private boolean jj_3R_255() {
		if (jj_scan_token(INTEGER_LITERAL))
			return true;
		return false;
	}

	private boolean jj_3R_83() {
		if (jj_3R_87())
			return true;
		return false;
	}

	private boolean jj_3R_180() {
		if (jj_3R_87())
			return true;
		return false;
	}

	private boolean jj_3_13() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_83())
			jj_scanpos = xsp;
		if (jj_scan_token(THIS))
			return true;
		if (jj_scan_token(LPAREN))
			return true;
		return false;
	}

	private boolean jj_3R_102() {
		if (jj_3R_87())
			return true;
		return false;
	}

	private boolean jj_3R_179() {
		if (jj_3R_82())
			return true;
		if (jj_scan_token(DOT))
			return true;
		return false;
	}

	private boolean jj_3_35() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_102())
			jj_scanpos = xsp;
		if (jj_3R_75())
			return true;
		return false;
	}

	private boolean jj_3R_123() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_179())
			jj_scanpos = xsp;
		xsp = jj_scanpos;
		if (jj_3R_180())
			jj_scanpos = xsp;
		if (jj_scan_token(SUPER))
			return true;
		if (jj_3R_178())
			return true;
		if (jj_scan_token(SEMICOLON))
			return true;
		return false;
	}

	private boolean jj_3R_211() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_255()) {
			jj_scanpos = xsp;
			if (jj_3R_256()) {
				jj_scanpos = xsp;
				if (jj_3R_257()) {
					jj_scanpos = xsp;
					if (jj_3R_258()) {
						jj_scanpos = xsp;
						if (jj_3R_259()) {
							jj_scanpos = xsp;
							if (jj_3R_260()) {
								jj_scanpos = xsp;
								if (jj_3R_261())
									return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	private boolean jj_3R_177() {
		if (jj_3R_87())
			return true;
		return false;
	}

	private boolean jj_3R_159() {
		if (jj_scan_token(LBRACKET))
			return true;
		if (jj_3R_90())
			return true;
		if (jj_scan_token(RBRACKET))
			return true;
		return false;
	}

	private boolean jj_3R_246() {
		if (jj_3R_178())
			return true;
		return false;
	}

	private boolean jj_3R_122() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_177())
			jj_scanpos = xsp;
		if (jj_scan_token(THIS))
			return true;
		if (jj_3R_178())
			return true;
		if (jj_scan_token(SEMICOLON))
			return true;
		return false;
	}

	private boolean jj_3R_245() {
		if (jj_3R_87())
			return true;
		return false;
	}

	private boolean jj_3R_200() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_245())
			jj_scanpos = xsp;
		if (jj_3R_88())
			return true;
		xsp = jj_scanpos;
		if (jj_3R_246())
			jj_scanpos = xsp;
		return false;
	}

	private boolean jj_3R_217() {
		if (jj_3R_75())
			return true;
		return false;
	}

	private boolean jj_3R_199() {
		if (jj_3R_215())
			return true;
		return false;
	}

	private boolean jj_3R_198() {
		if (jj_scan_token(THIS))
			return true;
		return false;
	}

	private boolean jj_3R_81() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_122()) {
			jj_scanpos = xsp;
			if (jj_3R_123())
				return true;
		}
		return false;
	}

	private boolean jj_3R_443() {
		if (jj_scan_token(_DEFAULT))
			return true;
		if (jj_3R_110())
			return true;
		return false;
	}

	private boolean jj_3_11() {
		if (jj_3R_81())
			return true;
		return false;
	}

	private boolean jj_3R_158() {
		if (jj_scan_token(DOT))
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_198()) {
			jj_scanpos = xsp;
			if (jj_3R_199()) {
				jj_scanpos = xsp;
				if (jj_3R_200())
					return true;
			}
		}
		return false;
	}

	private boolean jj_3R_299() {
		if (jj_3R_326())
			return true;
		return false;
	}

	private boolean jj_3R_440() {
		if (jj_3R_76())
			return true;
		if (jj_3R_88())
			return true;
		if (jj_scan_token(LPAREN))
			return true;
		if (jj_scan_token(RPAREN))
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_442())
			jj_scanpos = xsp;
		if (jj_scan_token(SEMICOLON))
			return true;
		return false;
	}

	private boolean jj_3R_99() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_158()) {
			jj_scanpos = xsp;
			if (jj_3R_159())
				return true;
		}
		return false;
	}

	private boolean jj_3R_383() {
		if (jj_3R_81())
			return true;
		return false;
	}

	private boolean jj_3_50() {
		if (jj_3R_76())
			return true;
		if (jj_3R_75())
			return true;
		if (jj_scan_token(LPAREN))
			return true;
		return false;
	}

	private boolean jj_3R_434() {
		if (jj_3R_341())
			return true;
		return false;
	}

	private boolean jj_3R_433() {
		if (jj_3R_339())
			return true;
		return false;
	}

	private boolean jj_3R_395() {
		if (jj_scan_token(COMMA))
			return true;
		if (jj_3R_394())
			return true;
		return false;
	}

	private boolean jj_3R_432() {
		if (jj_3R_338())
			return true;
		return false;
	}

	private boolean jj_3R_356() {
		if (jj_3R_114())
			return true;
		return false;
	}

	private boolean jj_3R_160() {
		if (jj_3R_86())
			return true;
		if (jj_scan_token(ELLIPSIS))
			return true;
		return false;
	}

	private boolean jj_3R_157() {
		if (jj_scan_token(DOT))
			return true;
		if (jj_scan_token(SUPER))
			return true;
		return false;
	}

	private boolean jj_3R_431() {
		if (jj_3R_189())
			return true;
		return false;
	}

	private boolean jj_3R_216() {
		if (jj_3R_87())
			return true;
		return false;
	}

	private boolean jj_3R_340() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_356())
			jj_scanpos = xsp;
		if (jj_3R_88())
			return true;
		if (jj_3R_381())
			return true;
		xsp = jj_scanpos;
		if (jj_3R_382())
			jj_scanpos = xsp;
		if (jj_scan_token(LBRACE))
			return true;
		xsp = jj_scanpos;
		if (jj_3R_383())
			jj_scanpos = xsp;
		if (jj_3R_175())
			return true;
		if (jj_scan_token(RBRACE))
			return true;
		return false;
	}

	private boolean jj_3_34() {
		if (jj_3R_99())
			return true;
		return false;
	}

	private boolean jj_3R_430() {
		if (jj_3R_440())
			return true;
		return false;
	}

	private boolean jj_3_49() {
		if (jj_scan_token(COMMA))
			return true;
		if (jj_3R_110())
			return true;
		return false;
	}

	private boolean jj_3R_98() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3_34()) {
			jj_scanpos = xsp;
			if (jj_3R_157())
				return true;
		}
		return false;
	}

	private boolean jj_3R_218() {
		if (jj_3R_178())
			return true;
		return false;
	}

	private boolean jj_3R_188() {
		if (jj_3R_88())
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_218())
			jj_scanpos = xsp;
		return false;
	}

	private boolean jj_3R_423() {
		if (jj_3R_106())
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_430()) {
			jj_scanpos = xsp;
			if (jj_3R_431()) {
				jj_scanpos = xsp;
				if (jj_3R_432()) {
					jj_scanpos = xsp;
					if (jj_3R_433()) {
						jj_scanpos = xsp;
						if (jj_3R_434())
							return true;
					}
				}
			}
		}
		return false;
	}

	private boolean jj_3_33() {
		if (jj_3R_101())
			return true;
		if (jj_scan_token(DOUBLECOLON))
			return true;
		return false;
	}

	private boolean jj_3R_121() {
		if (jj_3R_75())
			return true;
		if (jj_scan_token(DOT))
			return true;
		return false;
	}

	private boolean jj_3_32() {
		if (jj_3R_101())
			return true;
		if (jj_scan_token(DOT))
			return true;
		if (jj_scan_token(CLASS))
			return true;
		return false;
	}

	private boolean jj_3R_80() {
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_121()) {
				jj_scanpos = xsp;
				break;
			}
		}
		if (jj_scan_token(THIS))
			return true;
		return false;
	}

	private boolean jj_3R_344() {
		if (jj_scan_token(COMMA))
			return true;
		if (jj_3R_100())
			return true;
		return false;
	}

	private boolean jj_3R_265() {
		if (jj_3R_75())
			return true;
		return false;
	}

	private boolean jj_3R_412() {
		if (jj_3R_423())
			return true;
		return false;
	}

	private boolean jj_3R_392() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_412()) {
			jj_scanpos = xsp;
			if (jj_scan_token(123))
				return true;
		}
		return false;
	}

	private boolean jj_3R_300() {
		if (jj_3R_327())
			return true;
		return false;
	}

	private boolean jj_3R_380() {
		if (jj_scan_token(LBRACE))
			return true;
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_392()) {
				jj_scanpos = xsp;
				break;
			}
		}
		if (jj_scan_token(RBRACE))
			return true;
		return false;
	}

	private boolean jj_3R_187() {
		if (jj_3R_101())
			return true;
		if (jj_scan_token(DOUBLECOLON))
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_216())
			jj_scanpos = xsp;
		xsp = jj_scanpos;
		if (jj_3R_217()) {
			jj_scanpos = xsp;
			if (jj_scan_token(69))
				return true;
		}
		return false;
	}

	private boolean jj_3R_186() {
		if (jj_3R_101())
			return true;
		if (jj_scan_token(DOT))
			return true;
		if (jj_scan_token(CLASS))
			return true;
		return false;
	}

	private boolean jj_3_10() {
		if (jj_3R_80())
			return true;
		return false;
	}

	private boolean jj_3R_185() {
		if (jj_3R_215())
			return true;
		return false;
	}

	private boolean jj_3R_413() {
		if (jj_scan_token(COMMA))
			return true;
		if (jj_3R_100())
			return true;
		return false;
	}

	private boolean jj_3R_162() {
		if (jj_3R_201())
			return true;
		return false;
	}

	private boolean jj_3_31() {
		if (jj_3R_100())
			return true;
		return false;
	}

	private boolean jj_3R_339() {
		if (jj_scan_token(AT))
			return true;
		if (jj_scan_token(INTERFACE))
			return true;
		if (jj_3R_88())
			return true;
		if (jj_3R_380())
			return true;
		return false;
	}

	private boolean jj_3R_161() {
		if (jj_3R_80())
			return true;
		return false;
	}

	private boolean jj_3R_100() {
		if (jj_3R_106())
			return true;
		if (jj_3R_76())
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_160())
			jj_scanpos = xsp;
		xsp = jj_scanpos;
		if (jj_3R_161()) {
			jj_scanpos = xsp;
			if (jj_3R_162())
				return true;
		}
		return false;
	}

	private boolean jj_3R_267() {
		if (jj_3R_90())
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_300())
			jj_scanpos = xsp;
		return false;
	}

	private boolean jj_3R_266() {
		if (jj_3R_100())
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_299())
			jj_scanpos = xsp;
		return false;
	}

	private boolean jj_3R_333() {
		if (jj_scan_token(COMMA))
			return true;
		if (jj_3R_332())
			return true;
		return false;
	}

	private boolean jj_3R_214() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_266()) {
			jj_scanpos = xsp;
			if (jj_3R_267())
				return true;
		}
		return false;
	}

	private boolean jj_3R_353() {
		if (jj_3R_110())
			return true;
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3_49()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_264() {
		if (jj_3R_87())
			return true;
		return false;
	}

	private boolean jj_3R_345() {
		if (jj_scan_token(COMMA))
			return true;
		if (jj_3R_201())
			return true;
		return false;
	}

	private boolean jj_3R_184() {
		if (jj_scan_token(LPAREN))
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_214())
			jj_scanpos = xsp;
		if (jj_scan_token(RPAREN))
			return true;
		return false;
	}

	private boolean jj_3R_213() {
		if (jj_scan_token(DOUBLECOLON))
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_264())
			jj_scanpos = xsp;
		xsp = jj_scanpos;
		if (jj_3R_265()) {
			jj_scanpos = xsp;
			if (jj_scan_token(69))
				return true;
		}
		return false;
	}

	private boolean jj_3R_208() {
		if (jj_scan_token(LBRACE))
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_353())
			jj_scanpos = xsp;
		xsp = jj_scanpos;
		if (jj_scan_token(124))
			jj_scanpos = xsp;
		if (jj_scan_token(RBRACE))
			return true;
		return false;
	}

	private boolean jj_3_9() {
		if (jj_scan_token(COMMA))
			return true;
		if (jj_3R_79())
			return true;
		return false;
	}

	private boolean jj_3R_171() {
		if (jj_3R_145())
			return true;
		return false;
	}

	private boolean jj_3R_327() {
		if (jj_scan_token(COMMA))
			return true;
		if (jj_3R_201())
			return true;
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_345()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_170() {
		if (jj_3R_208())
			return true;
		return false;
	}

	private boolean jj_3R_169() {
		if (jj_3R_112())
			return true;
		return false;
	}

	private boolean jj_3R_385() {
		if (jj_3R_116())
			return true;
		return false;
	}

	private boolean jj_3R_263() {
		if (jj_3R_178())
			return true;
		return false;
	}

	private boolean jj_3R_212() {
		if (jj_scan_token(DOT))
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_262())
			jj_scanpos = xsp;
		if (jj_3R_88())
			return true;
		xsp = jj_scanpos;
		if (jj_3R_263())
			jj_scanpos = xsp;
		return false;
	}

	private boolean jj_3R_262() {
		if (jj_3R_87())
			return true;
		return false;
	}

	private boolean jj_3R_326() {
		if (jj_scan_token(COMMA))
			return true;
		if (jj_3R_100())
			return true;
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_344()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3_30() {
		if (jj_3R_99())
			return true;
		return false;
	}

	private boolean jj_3R_110() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_169()) {
			jj_scanpos = xsp;
			if (jj_3R_170()) {
				jj_scanpos = xsp;
				if (jj_3R_171())
					return true;
			}
		}
		return false;
	}

	private boolean jj_3R_183() {
		if (jj_scan_token(SUPER))
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_212()) {
			jj_scanpos = xsp;
			if (jj_3R_213())
				return true;
		}
		return false;
	}

	private boolean jj_3R_393() {
		if (jj_3R_100())
			return true;
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_413()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_182() {
		if (jj_scan_token(THIS))
			return true;
		return false;
	}

	private boolean jj_3R_181() {
		if (jj_3R_211())
			return true;
		return false;
	}

	private boolean jj_3R_332() {
		if (jj_3R_88())
			return true;
		if (jj_scan_token(ASSIGN))
			return true;
		if (jj_3R_110())
			return true;
		return false;
	}

	private boolean jj_3R_381() {
		if (jj_scan_token(LPAREN))
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_393())
			jj_scanpos = xsp;
		if (jj_scan_token(RPAREN))
			return true;
		return false;
	}

	private boolean jj_3R_125() {
		if (jj_3R_99())
			return true;
		return false;
	}

	private boolean jj_3R_124() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_181()) {
			jj_scanpos = xsp;
			if (jj_3R_182()) {
				jj_scanpos = xsp;
				if (jj_3R_183()) {
					jj_scanpos = xsp;
					if (jj_3R_184()) {
						jj_scanpos = xsp;
						if (jj_3R_185()) {
							jj_scanpos = xsp;
							if (jj_3R_186()) {
								jj_scanpos = xsp;
								if (jj_3R_187()) {
									jj_scanpos = xsp;
									if (jj_3R_188())
										return true;
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	private boolean jj_3R_109() {
		if (jj_3R_75())
			return true;
		if (jj_scan_token(ASSIGN))
			return true;
		return false;
	}

	private boolean jj_3_48() {
		if (jj_scan_token(LPAREN))
			return true;
		return false;
	}

	private boolean jj_3R_311() {
		if (jj_3R_332())
			return true;
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_333()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3_29() {
		if (jj_3R_98())
			return true;
		return false;
	}

	private boolean jj_3_47() {
		if (jj_scan_token(LPAREN))
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_109()) {
			jj_scanpos = xsp;
			if (jj_scan_token(118))
				return true;
		}
		return false;
	}

	private boolean jj_3R_238() {
		return false;
	}

	private boolean jj_3R_284() {
		if (jj_3R_311())
			return true;
		return false;
	}

	private boolean jj_3R_394() {
		if (jj_3R_86())
			return true;
		if (jj_3R_85())
			return true;
		return false;
	}

	private boolean jj_3_45() {
		if (jj_scan_token(SEMICOLON))
			return true;
		if (jj_3R_107())
			return true;
		return false;
	}

	private boolean jj_3R_237() {
		if (jj_scan_token(LPAREN))
			return true;
		if (jj_3R_110())
			return true;
		if (jj_scan_token(RPAREN))
			return true;
		return false;
	}

	private boolean jj_3R_236() {
		if (jj_scan_token(LPAREN))
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_284())
			jj_scanpos = xsp;
		if (jj_scan_token(RPAREN))
			return true;
		return false;
	}

	private boolean jj_3R_82() {
		if (jj_3R_124())
			return true;
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_125()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_396() {
		if (jj_scan_token(COMMA))
			return true;
		if (jj_3R_394())
			return true;
		return false;
	}

	private boolean jj_3R_387() {
		if (jj_3R_118())
			return true;
		return false;
	}

	private boolean jj_3R_112() {
		if (jj_scan_token(AT))
			return true;
		if (jj_3R_111())
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_236()) {
			jj_scanpos = xsp;
			if (jj_3R_237()) {
				jj_scanpos = xsp;
				if (jj_3R_238())
					return true;
			}
		}
		return false;
	}

	private boolean jj_3R_386() {
		if (jj_scan_token(THROWS))
			return true;
		if (jj_3R_394())
			return true;
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_396()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3_46() {
		if (jj_scan_token(AT))
			return true;
		return false;
	}

	private boolean jj_3R_313() {
		if (jj_3R_124())
			return true;
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3_29()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_357() {
		if (jj_3R_114())
			return true;
		return false;
	}

	private boolean jj_3R_343() {
		if (jj_3R_79())
			return true;
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3_9()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_342() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_357())
			jj_scanpos = xsp;
		if (jj_3R_86())
			return true;
		if (jj_3R_101())
			return true;
		if (jj_3R_88())
			return true;
		if (jj_3R_381())
			return true;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_385()) {
				jj_scanpos = xsp;
				break;
			}
		}
		xsp = jj_scanpos;
		if (jj_3R_386())
			jj_scanpos = xsp;
		xsp = jj_scanpos;
		if (jj_3R_387()) {
			jj_scanpos = xsp;
			if (jj_scan_token(123))
				return true;
		}
		return false;
	}

	private boolean jj_3R_248() {
		if (jj_3R_116())
			return true;
		return false;
	}

	private boolean jj_3R_131() {
		if (jj_3R_112())
			return true;
		return false;
	}

	private boolean jj_3R_86() {
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_131()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_196() {
		if (jj_scan_token(BIT_AND))
			return true;
		if (jj_3R_85())
			return true;
		return false;
	}

	private boolean jj_3R_146() {
		return false;
	}

	private boolean jj_3R_148() {
		if (jj_3R_85())
			return true;
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_196()) {
				jj_scanpos = xsp;
				break;
			}
		}
		if (jj_scan_token(RPAREN))
			return true;
		if (jj_3R_197())
			return true;
		return false;
	}

	private boolean jj_3_28() {
		if (jj_3R_97())
			return true;
		if (jj_scan_token(RPAREN))
			return true;
		if (jj_3R_168())
			return true;
		return false;
	}

	private boolean jj_3R_147() {
		return false;
	}

	private boolean jj_3R_176() {
		if (jj_scan_token(LBRACE))
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_343())
			jj_scanpos = xsp;
		xsp = jj_scanpos;
		if (jj_scan_token(124))
			jj_scanpos = xsp;
		if (jj_scan_token(RBRACE))
			return true;
		return false;
	}

	private boolean jj_3R_92() {
		jj_lookingAhead = true;
		jj_semLA = getToken(1).kind == GT && ((CustomToken) getToken(1)).realKind == RSIGNEDSHIFT;
		jj_lookingAhead = false;
		if (!jj_semLA || jj_3R_146())
			return true;
		if (jj_scan_token(GT))
			return true;
		if (jj_scan_token(GT))
			return true;
		return false;
	}

	private boolean jj_3R_120() {
		if (jj_3R_90())
			return true;
		return false;
	}

	private boolean jj_3R_94() {
		if (jj_scan_token(LPAREN))
			return true;
		if (jj_3R_86())
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3_28()) {
			jj_scanpos = xsp;
			if (jj_3R_148())
				return true;
		}
		return false;
	}

	private boolean jj_3R_119() {
		if (jj_3R_176())
			return true;
		return false;
	}

	private boolean jj_3R_203() {
		if (jj_scan_token(ASSIGN))
			return true;
		if (jj_3R_79())
			return true;
		return false;
	}

	private boolean jj_3R_93() {
		jj_lookingAhead = true;
		jj_semLA = getToken(1).kind == GT && ((CustomToken) getToken(1)).realKind == RUNSIGNEDSHIFT;
		jj_lookingAhead = false;
		if (!jj_semLA || jj_3R_147())
			return true;
		if (jj_scan_token(GT))
			return true;
		if (jj_scan_token(GT))
			return true;
		if (jj_scan_token(GT))
			return true;
		return false;
	}

	private boolean jj_3R_79() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_119()) {
			jj_scanpos = xsp;
			if (jj_3R_120())
				return true;
		}
		return false;
	}

	private boolean jj_3R_96() {
		if (jj_scan_token(DECR))
			return true;
		return false;
	}

	private boolean jj_3_27() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_95()) {
			jj_scanpos = xsp;
			if (jj_3R_96())
				return true;
		}
		return false;
	}

	private boolean jj_3R_95() {
		if (jj_scan_token(INCR))
			return true;
		return false;
	}

	private boolean jj_3_26() {
		if (jj_3R_94())
			return true;
		return false;
	}

	private boolean jj_3R_428() {
		if (jj_3R_107())
			return true;
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3_45()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_289() {
		if (jj_3R_313())
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3_27())
			jj_scanpos = xsp;
		return false;
	}

	private boolean jj_3R_201() {
		if (jj_3R_88())
			return true;
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_248()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_77() {
		if (jj_3R_116())
			return true;
		return false;
	}

	private boolean jj_3R_243() {
		if (jj_3R_94())
			return true;
		return false;
	}

	private boolean jj_3R_244() {
		if (jj_3R_289())
			return true;
		return false;
	}

	private boolean jj_3R_288() {
		if (jj_scan_token(BANG))
			return true;
		return false;
	}

	private boolean jj_3R_287() {
		if (jj_scan_token(TILDE))
			return true;
		return false;
	}

	private boolean jj_3R_422() {
		if (jj_scan_token(FINALLY))
			return true;
		if (jj_3R_118())
			return true;
		return false;
	}

	private boolean jj_3R_408() {
		if (jj_scan_token(FINALLY))
			return true;
		if (jj_3R_118())
			return true;
		return false;
	}

	private boolean jj_3_44() {
		if (jj_scan_token(SEMICOLON))
			return true;
		return false;
	}

	private boolean jj_3R_242() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_287()) {
			jj_scanpos = xsp;
			if (jj_3R_288())
				return true;
		}
		if (jj_3R_168())
			return true;
		return false;
	}

	private boolean jj_3R_331() {
		if (jj_scan_token(LPAREN))
			return true;
		if (jj_3R_428())
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3_44())
			jj_scanpos = xsp;
		if (jj_scan_token(RPAREN))
			return true;
		return false;
	}

	private boolean jj_3R_166() {
		if (jj_3R_201())
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_203())
			jj_scanpos = xsp;
		return false;
	}

	private boolean jj_3R_429() {
		if (jj_scan_token(BIT_OR))
			return true;
		if (jj_3R_394())
			return true;
		return false;
	}

	private boolean jj_3R_197() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_242()) {
			jj_scanpos = xsp;
			if (jj_3R_243()) {
				jj_scanpos = xsp;
				if (jj_3R_244())
					return true;
			}
		}
		return false;
	}

	private boolean jj_3R_421() {
		if (jj_scan_token(CATCH))
			return true;
		if (jj_scan_token(LPAREN))
			return true;
		if (jj_3R_106())
			return true;
		if (jj_3R_85())
			return true;
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_429()) {
				jj_scanpos = xsp;
				break;
			}
		}
		if (jj_3R_201())
			return true;
		if (jj_scan_token(RPAREN))
			return true;
		if (jj_3R_118())
			return true;
		return false;
	}

	private boolean jj_3_7() {
		if (jj_3R_76())
			return true;
		if (jj_3R_75())
			return true;
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_77()) {
				jj_scanpos = xsp;
				break;
			}
		}
		xsp = jj_scanpos;
		if (jj_scan_token(124)) {
			jj_scanpos = xsp;
			if (jj_scan_token(127)) {
				jj_scanpos = xsp;
				if (jj_scan_token(123))
					return true;
			}
		}
		return false;
	}

	private boolean jj_3R_74() {
		if (jj_3R_114())
			return true;
		return false;
	}

	private boolean jj_3R_384() {
		if (jj_scan_token(COMMA))
			return true;
		if (jj_3R_166())
			return true;
		return false;
	}

	private boolean jj_3_6() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_74())
			jj_scanpos = xsp;
		if (jj_3R_75())
			return true;
		if (jj_scan_token(LPAREN))
			return true;
		return false;
	}

	private boolean jj_3_5() {
		if (jj_scan_token(AT))
			return true;
		if (jj_scan_token(INTERFACE))
			return true;
		return false;
	}

	private boolean jj_3R_250() {
		if (jj_scan_token(DECR))
			return true;
		if (jj_3R_168())
			return true;
		return false;
	}

	private boolean jj_3R_341() {
		if (jj_3R_76())
			return true;
		if (jj_3R_166())
			return true;
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_384()) {
				jj_scanpos = xsp;
				break;
			}
		}
		if (jj_scan_token(SEMICOLON))
			return true;
		return false;
	}

	private boolean jj_3R_323() {
		if (jj_3R_342())
			return true;
		return false;
	}

	private boolean jj_3R_322() {
		if (jj_3R_341())
			return true;
		return false;
	}

	private boolean jj_3R_321() {
		if (jj_3R_340())
			return true;
		return false;
	}

	private boolean jj_3R_407() {
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_421()) {
				jj_scanpos = xsp;
				break;
			}
		}
		xsp = jj_scanpos;
		if (jj_3R_422())
			jj_scanpos = xsp;
		return false;
	}

	private boolean jj_3R_320() {
		if (jj_3R_339())
			return true;
		return false;
	}

	private boolean jj_3R_108() {
		if (jj_scan_token(INCR))
			return true;
		if (jj_3R_168())
			return true;
		return false;
	}

	private boolean jj_3R_319() {
		if (jj_3R_338())
			return true;
		return false;
	}

	private boolean jj_3R_318() {
		if (jj_3R_189())
			return true;
		return false;
	}

	private boolean jj_3R_207() {
		if (jj_3R_197())
			return true;
		return false;
	}

	private boolean jj_3R_310() {
		if (jj_3R_331())
			return true;
		return false;
	}

	private boolean jj_3R_252() {
		if (jj_scan_token(MINUS))
			return true;
		return false;
	}

	private boolean jj_3R_251() {
		if (jj_scan_token(PLUS))
			return true;
		return false;
	}

	private boolean jj_3R_293() {
		if (jj_3R_106())
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_318()) {
			jj_scanpos = xsp;
			if (jj_3R_319()) {
				jj_scanpos = xsp;
				if (jj_3R_320()) {
					jj_scanpos = xsp;
					if (jj_3R_321()) {
						jj_scanpos = xsp;
						if (jj_3R_322()) {
							jj_scanpos = xsp;
							if (jj_3R_323())
								return true;
						}
					}
				}
			}
		}
		return false;
	}

	private boolean jj_3R_283() {
		if (jj_scan_token(TRY))
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_310())
			jj_scanpos = xsp;
		if (jj_3R_118())
			return true;
		xsp = jj_scanpos;
		if (jj_3R_407()) {
			jj_scanpos = xsp;
			if (jj_3R_408())
				return true;
		}
		return false;
	}

	private boolean jj_3R_206() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_251()) {
			jj_scanpos = xsp;
			if (jj_3R_252())
				return true;
		}
		if (jj_3R_168())
			return true;
		return false;
	}

	private boolean jj_3R_205() {
		if (jj_3R_250())
			return true;
		return false;
	}

	private boolean jj_3_8() {
		if (jj_3R_78())
			return true;
		return false;
	}

	private boolean jj_3R_204() {
		if (jj_3R_108())
			return true;
		return false;
	}

	private boolean jj_3R_209() {
		if (jj_3R_253())
			return true;
		return false;
	}

	private boolean jj_3R_168() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_204()) {
			jj_scanpos = xsp;
			if (jj_3R_205()) {
				jj_scanpos = xsp;
				if (jj_3R_206()) {
					jj_scanpos = xsp;
					if (jj_3R_207())
						return true;
				}
			}
		}
		return false;
	}

	private boolean jj_3R_309() {
		if (jj_3R_90())
			return true;
		return false;
	}

	private boolean jj_3R_249() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3_8()) {
			jj_scanpos = xsp;
			if (jj_3R_293())
				return true;
		}
		return false;
	}

	private boolean jj_3R_416() {
		if (jj_scan_token(REM))
			return true;
		return false;
	}

	private boolean jj_3R_415() {
		if (jj_scan_token(SLASH))
			return true;
		return false;
	}

	private boolean jj_3R_414() {
		if (jj_scan_token(STAR))
			return true;
		return false;
	}

	private boolean jj_3R_165() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_202()) {
			jj_scanpos = xsp;
			if (jj_scan_token(123))
				return true;
		}
		return false;
	}

	private boolean jj_3R_202() {
		if (jj_3R_249())
			return true;
		return false;
	}

	private boolean jj_3R_282() {
		if (jj_scan_token(SYNCHRONIZED))
			return true;
		if (jj_scan_token(LPAREN))
			return true;
		if (jj_3R_90())
			return true;
		if (jj_scan_token(RPAREN))
			return true;
		if (jj_3R_118())
			return true;
		return false;
	}

	private boolean jj_3R_397() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_414()) {
			jj_scanpos = xsp;
			if (jj_3R_415()) {
				jj_scanpos = xsp;
				if (jj_3R_416())
					return true;
			}
		}
		if (jj_3R_168())
			return true;
		return false;
	}

	private boolean jj_3R_308() {
		if (jj_3R_88())
			return true;
		return false;
	}

	private boolean jj_3R_103() {
		if (jj_scan_token(LBRACE))
			return true;
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_165()) {
				jj_scanpos = xsp;
				break;
			}
		}
		if (jj_scan_token(RBRACE))
			return true;
		return false;
	}

	private boolean jj_3R_368() {
		if (jj_3R_168())
			return true;
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_397()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_281() {
		if (jj_scan_token(THROW))
			return true;
		if (jj_3R_90())
			return true;
		if (jj_scan_token(SEMICOLON))
			return true;
		return false;
	}

	private boolean jj_3R_399() {
		if (jj_scan_token(MINUS))
			return true;
		return false;
	}

	private boolean jj_3R_307() {
		if (jj_3R_88())
			return true;
		return false;
	}

	private boolean jj_3R_295() {
		if (jj_scan_token(BIT_AND))
			return true;
		if (jj_3R_294())
			return true;
		return false;
	}

	private boolean jj_3R_398() {
		if (jj_scan_token(PLUS))
			return true;
		return false;
	}

	private boolean jj_3R_388() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_398()) {
			jj_scanpos = xsp;
			if (jj_3R_399())
				return true;
		}
		if (jj_3R_368())
			return true;
		return false;
	}

	private boolean jj_3R_253() {
		if (jj_scan_token(EXTENDS))
			return true;
		if (jj_3R_294())
			return true;
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_295()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_441() {
		if (jj_scan_token(COMMA))
			return true;
		if (jj_3R_90())
			return true;
		return false;
	}

	private boolean jj_3R_280() {
		if (jj_scan_token(RETURN))
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_309())
			jj_scanpos = xsp;
		if (jj_scan_token(SEMICOLON))
			return true;
		return false;
	}

	private boolean jj_3R_364() {
		if (jj_3R_368())
			return true;
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_388()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3_4() {
		if (jj_scan_token(COMMA))
			return true;
		if (jj_3R_73())
			return true;
		return false;
	}

	private boolean jj_3R_173() {
		if (jj_3R_88())
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_209())
			jj_scanpos = xsp;
		return false;
	}

	private boolean jj_3_25() {
		if (jj_3R_93())
			return true;
		return false;
	}

	private boolean jj_3_24() {
		if (jj_3R_92())
			return true;
		return false;
	}

	private boolean jj_3R_279() {
		if (jj_scan_token(CONTINUE))
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_308())
			jj_scanpos = xsp;
		if (jj_scan_token(SEMICOLON))
			return true;
		return false;
	}

	private boolean jj_3R_91() {
		if (jj_scan_token(LSHIFT))
			return true;
		return false;
	}

	private boolean jj_3_23() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_91()) {
			jj_scanpos = xsp;
			if (jj_3_24()) {
				jj_scanpos = xsp;
				if (jj_3_25())
					return true;
			}
		}
		if (jj_3R_364())
			return true;
		return false;
	}

	private boolean jj_3R_410() {
		if (jj_3R_103())
			return true;
		return false;
	}

	private boolean jj_3R_420() {
		if (jj_3R_427())
			return true;
		return false;
	}

	private boolean jj_3R_174() {
		if (jj_scan_token(COMMA))
			return true;
		if (jj_3R_86())
			return true;
		if (jj_3R_173())
			return true;
		return false;
	}

	private boolean jj_3R_365() {
		if (jj_scan_token(INSTANCEOF))
			return true;
		if (jj_3R_86())
			return true;
		if (jj_3R_85())
			return true;
		return false;
	}

	private boolean jj_3R_278() {
		if (jj_scan_token(BREAK))
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_307())
			jj_scanpos = xsp;
		if (jj_scan_token(SEMICOLON))
			return true;
		return false;
	}

	private boolean jj_3R_362() {
		if (jj_3R_364())
			return true;
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3_23()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_114() {
		if (jj_scan_token(LT))
			return true;
		if (jj_3R_86())
			return true;
		if (jj_3R_173())
			return true;
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_174()) {
				jj_scanpos = xsp;
				break;
			}
		}
		if (jj_scan_token(GT))
			return true;
		return false;
	}

	private boolean jj_3R_373() {
		if (jj_scan_token(GE))
			return true;
		return false;
	}

	private boolean jj_3R_372() {
		if (jj_scan_token(LE))
			return true;
		return false;
	}

	private boolean jj_3R_371() {
		if (jj_scan_token(GT))
			return true;
		return false;
	}

	private boolean jj_3R_370() {
		if (jj_scan_token(LT))
			return true;
		return false;
	}

	private boolean jj_3R_427() {
		if (jj_3R_439())
			return true;
		return false;
	}

	private boolean jj_3R_369() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_370()) {
			jj_scanpos = xsp;
			if (jj_3R_371()) {
				jj_scanpos = xsp;
				if (jj_3R_372()) {
					jj_scanpos = xsp;
					if (jj_3R_373())
						return true;
				}
			}
		}
		if (jj_3R_362())
			return true;
		return false;
	}

	private boolean jj_3R_404() {
		if (jj_scan_token(ELSE))
			return true;
		if (jj_3R_190())
			return true;
		return false;
	}

	private boolean jj_3_43() {
		if (jj_3R_106())
			return true;
		if (jj_3R_76())
			return true;
		if (jj_3R_75())
			return true;
		return false;
	}

	private boolean jj_3R_409() {
		if (jj_3R_178())
			return true;
		return false;
	}

	private boolean jj_3R_360() {
		if (jj_3R_362())
			return true;
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_369()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_439() {
		if (jj_3R_90())
			return true;
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_441()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_113() {
		if (jj_3R_112())
			return true;
		return false;
	}

	private boolean jj_3R_438() {
		if (jj_3R_439())
			return true;
		return false;
	}

	private boolean jj_3R_73() {
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_113()) {
				jj_scanpos = xsp;
				break;
			}
		}
		if (jj_3R_88())
			return true;
		xsp = jj_scanpos;
		if (jj_3R_409())
			jj_scanpos = xsp;
		xsp = jj_scanpos;
		if (jj_3R_410())
			jj_scanpos = xsp;
		return false;
	}

	private boolean jj_3R_419() {
		if (jj_3R_90())
			return true;
		return false;
	}

	private boolean jj_3R_437() {
		if (jj_3R_107())
			return true;
		return false;
	}

	private boolean jj_3R_361() {
		if (jj_scan_token(BIT_AND))
			return true;
		if (jj_3R_354())
			return true;
		return false;
	}

	private boolean jj_3R_358() {
		if (jj_3R_360())
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_365())
			jj_scanpos = xsp;
		return false;
	}

	private boolean jj_3R_411() {
		if (jj_3R_249())
			return true;
		return false;
	}

	private boolean jj_3R_391() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_411()) {
			jj_scanpos = xsp;
			if (jj_scan_token(123))
				return true;
		}
		return false;
	}

	private boolean jj_3R_426() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_437()) {
			jj_scanpos = xsp;
			if (jj_3R_438())
				return true;
		}
		return false;
	}

	private boolean jj_3R_379() {
		if (jj_scan_token(SEMICOLON))
			return true;
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_391()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_367() {
		if (jj_scan_token(NE))
			return true;
		return false;
	}

	private boolean jj_3R_366() {
		if (jj_scan_token(EQ))
			return true;
		return false;
	}

	private boolean jj_3R_378() {
		if (jj_3R_73())
			return true;
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3_4()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3_42() {
		if (jj_3R_107())
			return true;
		if (jj_scan_token(COLON))
			return true;
		return false;
	}

	private boolean jj_3R_363() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_366()) {
			jj_scanpos = xsp;
			if (jj_3R_367())
				return true;
		}
		if (jj_3R_358())
			return true;
		return false;
	}

	private boolean jj_3R_359() {
		if (jj_scan_token(XOR))
			return true;
		if (jj_3R_334())
			return true;
		return false;
	}

	private boolean jj_3R_355() {
		if (jj_scan_token(BIT_OR))
			return true;
		if (jj_3R_312())
			return true;
		return false;
	}

	private boolean jj_3R_377() {
		if (jj_3R_390())
			return true;
		return false;
	}

	private boolean jj_3R_354() {
		if (jj_3R_358())
			return true;
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_363()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_418() {
		if (jj_3R_426())
			return true;
		return false;
	}

	private boolean jj_3R_406() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_418())
			jj_scanpos = xsp;
		if (jj_scan_token(SEMICOLON))
			return true;
		xsp = jj_scanpos;
		if (jj_3R_419())
			jj_scanpos = xsp;
		if (jj_scan_token(SEMICOLON))
			return true;
		xsp = jj_scanpos;
		if (jj_3R_420())
			jj_scanpos = xsp;
		return false;
	}

	private boolean jj_3R_338() {
		if (jj_scan_token(ENUM))
			return true;
		if (jj_3R_88())
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_377())
			jj_scanpos = xsp;
		if (jj_scan_token(LBRACE))
			return true;
		xsp = jj_scanpos;
		if (jj_3R_378())
			jj_scanpos = xsp;
		xsp = jj_scanpos;
		if (jj_scan_token(124))
			jj_scanpos = xsp;
		xsp = jj_scanpos;
		if (jj_3R_379())
			jj_scanpos = xsp;
		if (jj_scan_token(RBRACE))
			return true;
		return false;
	}

	private boolean jj_3R_405() {
		if (jj_3R_107())
			return true;
		if (jj_scan_token(COLON))
			return true;
		if (jj_3R_90())
			return true;
		return false;
	}

	private boolean jj_3R_335() {
		if (jj_scan_token(SC_AND))
			return true;
		if (jj_3R_286())
			return true;
		return false;
	}

	private boolean jj_3R_334() {
		if (jj_3R_354())
			return true;
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_361()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_277() {
		if (jj_scan_token(FOR))
			return true;
		if (jj_scan_token(LPAREN))
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_405()) {
			jj_scanpos = xsp;
			if (jj_3R_406())
				return true;
		}
		if (jj_scan_token(RPAREN))
			return true;
		if (jj_3R_190())
			return true;
		return false;
	}

	private boolean jj_3R_314() {
		if (jj_scan_token(SC_OR))
			return true;
		if (jj_3R_241())
			return true;
		return false;
	}

	private boolean jj_3R_401() {
		if (jj_scan_token(COMMA))
			return true;
		if (jj_3R_294())
			return true;
		return false;
	}

	private boolean jj_3R_390() {
		if (jj_scan_token(IMPLEMENTS))
			return true;
		if (jj_3R_294())
			return true;
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_401()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_312() {
		if (jj_3R_334())
			return true;
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_359()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_276() {
		if (jj_scan_token(DO))
			return true;
		if (jj_3R_190())
			return true;
		if (jj_scan_token(WHILE))
			return true;
		if (jj_scan_token(LPAREN))
			return true;
		if (jj_3R_90())
			return true;
		if (jj_scan_token(RPAREN))
			return true;
		if (jj_scan_token(SEMICOLON))
			return true;
		return false;
	}

	private boolean jj_3R_400() {
		if (jj_scan_token(COMMA))
			return true;
		if (jj_3R_294())
			return true;
		return false;
	}

	private boolean jj_3R_286() {
		if (jj_3R_312())
			return true;
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_355()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_389() {
		if (jj_scan_token(EXTENDS))
			return true;
		if (jj_3R_294())
			return true;
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_400()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_275() {
		if (jj_scan_token(WHILE))
			return true;
		if (jj_scan_token(LPAREN))
			return true;
		if (jj_3R_90())
			return true;
		if (jj_scan_token(RPAREN))
			return true;
		if (jj_3R_190())
			return true;
		return false;
	}

	private boolean jj_3R_220() {
		if (jj_scan_token(INTERFACE))
			return true;
		return false;
	}

	private boolean jj_3R_241() {
		if (jj_3R_286())
			return true;
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_335()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_376() {
		if (jj_3R_390())
			return true;
		return false;
	}

	private boolean jj_3R_375() {
		if (jj_3R_389())
			return true;
		return false;
	}

	private boolean jj_3R_374() {
		if (jj_3R_114())
			return true;
		return false;
	}

	private boolean jj_3R_189() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_scan_token(46)) {
			jj_scanpos = xsp;
			if (jj_3R_220())
				return true;
		}
		if (jj_3R_88())
			return true;
		xsp = jj_scanpos;
		if (jj_3R_374())
			jj_scanpos = xsp;
		xsp = jj_scanpos;
		if (jj_3R_375())
			jj_scanpos = xsp;
		xsp = jj_scanpos;
		if (jj_3R_376())
			jj_scanpos = xsp;
		if (jj_3R_103())
			return true;
		return false;
	}

	private boolean jj_3R_274() {
		if (jj_scan_token(IF))
			return true;
		if (jj_scan_token(LPAREN))
			return true;
		if (jj_3R_90())
			return true;
		if (jj_scan_token(RPAREN))
			return true;
		if (jj_3R_190())
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_404())
			jj_scanpos = xsp;
		return false;
	}

	private boolean jj_3R_195() {
		if (jj_3R_241())
			return true;
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_314()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_290() {
		if (jj_scan_token(HOOK))
			return true;
		if (jj_3R_90())
			return true;
		if (jj_scan_token(COLON))
			return true;
		if (jj_3R_145())
			return true;
		return false;
	}

	private boolean jj_3R_167() {
		if (jj_scan_token(COMMA))
			return true;
		if (jj_3R_166())
			return true;
		return false;
	}

	private boolean jj_3R_436() {
		if (jj_3R_75())
			return true;
		return false;
	}

	private boolean jj_3R_425() {
		if (jj_scan_token(_DEFAULT))
			return true;
		return false;
	}

	private boolean jj_3R_317() {
		if (jj_3R_75())
			return true;
		return false;
	}

	private boolean jj_3R_145() {
		if (jj_3R_195())
			return true;
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_290())
			jj_scanpos = xsp;
		return false;
	}

	private boolean jj_3R_424() {
		if (jj_scan_token(CASE))
			return true;
		if (jj_3R_90())
			return true;
		return false;
	}

	/** Generated Token Manager. */
	public GeneratedJavaParserTokenManager token_source;
	JavaCharStream jj_input_stream;
	/** Current token. */
	public Token token;
	/** Next token. */
	public Token jj_nt;
	private int jj_ntk;
	private Token jj_scanpos, jj_lastpos;
	private int jj_la;
	/** Whether we are looking ahead. */
	private boolean jj_lookingAhead = false;
	private boolean jj_semLA;
	private int jj_gen;
	final private int[] jj_la1 = new int[166];
	static private int[] jj_la1_0;
	static private int[] jj_la1_1;
	static private int[] jj_la1_2;
	static private int[] jj_la1_3;
	static private int[] jj_la1_4;
	static private int[] jj_la1_5;
	static {
		jj_la1_init_0();
		jj_la1_init_1();
		jj_la1_init_2();
		jj_la1_init_3();
		jj_la1_init_4();
		jj_la1_init_5();
	}

	private static void jj_la1_init_0() {
		jj_la1_0 = new int[] { 0x0, 0x0, 0x0, 0x1, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0,
				0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0,
				0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0,
				0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0,
				0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0,
				0x0, 0x0, 0x0, 0x0, };
	}

	private static void jj_la1_init_1() {
		jj_la1_1 = new int[] { 0x81224040, 0x204000, 0x81224040, 0x0, 0x0, 0x0, 0x1020040, 0x4000, 0x0, 0x400000, 0x40000000, 0x0, 0x0, 0x40000000, 0x0, 0x0, 0x52a6540, 0x52a6540,
				0x0, 0x0, 0x0, 0x0, 0x0, 0x400000, 0x0, 0x52a6540, 0x52a6540, 0x204000, 0x4082500, 0x52a6540, 0x0, 0x0, 0x0, 0x4882500, 0x4882500, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0,
				0x0, 0x50a2540, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x4882500, 0x0, 0x4082500, 0x4082500, 0x4082500, 0x0, 0x4082500, 0x4082500, 0x400000, 0x400000,
				0x4082500, 0x4082500, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x4882500, 0x0, 0x0,
				0x4882500, 0x0, 0x0, 0x4082500, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x4882500, 0x58a2540, 0x0, 0x0, 0x0, 0x800000, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x800000, 0x800000,
				0x4882500, 0x0, 0x0, 0x0, 0x4082500, 0x4882500, 0x0, 0x2c8d2780, 0x0, 0x2c8d2780, 0x0, 0x4882500, 0x0, 0x0, 0x0, 0x0, 0x4882500, 0x20800, 0x20800, 0x100000,
				0x58a2540, 0x4882500, 0x4882500, 0x58a2540, 0x4882500, 0x0, 0x0, 0x0, 0x4882500, 0x0, 0x1000, 0x0, 0x2000000, 0x0, 0x0, 0x4882500, 0x4882500, 0x0, 0x52a6540,
				0x52a6540, 0x4286500, 0x20000, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, };
	}

	private static void jj_la1_init_2() {
		jj_la1_2 = new int[] { 0x42226714, 0x40000004, 0x42226714, 0x0, 0x2000, 0x0, 0x2226710, 0x4, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0xf8000000, 0x0, 0xfb22771e, 0xfb22771e, 0x0,
				0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0xfb22771e, 0xfb22771e, 0x4, 0xf900100a, 0xfb22771e, 0x0, 0x0, 0x0, 0xf944906a, 0xf944906a, 0x0, 0x0, 0x0, 0x0, 0x100000, 0x0, 0x0,
				0xfa22771a, 0x0, 0x0, 0x0, 0xf8000000, 0xf8000000, 0x0, 0x0, 0x100000, 0x0, 0x0, 0xf944906a, 0x2000, 0x100a, 0xf800100a, 0xf800100a, 0x0, 0xf800100a, 0xf800100a,
				0x8000, 0x8000, 0x100a, 0xf900100a, 0xf8000000, 0x0, 0xf8000020, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x1, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0,
				0xf944906a, 0x0, 0x0, 0xf944906a, 0x0, 0x0, 0xf800100a, 0x0, 0x0, 0x0, 0xf8000020, 0x0, 0x0, 0x0, 0xf944906a, 0xfb66f77a, 0x0, 0xf8000020, 0x0, 0x448060,
				0xf8000000, 0x0, 0x0, 0x0, 0x40020, 0x0, 0x400040, 0x400000, 0xf944906a, 0x0, 0x0, 0x0, 0xf800100a, 0xf944906a, 0x0, 0xfdcf986a, 0x0, 0xfdcf986a, 0x0, 0xf944906a,
				0x0, 0xf8000020, 0x0, 0x0, 0xf944906a, 0x0, 0x0, 0x0, 0xfb66f77a, 0xf944906a, 0xf944906a, 0xfb66f77a, 0xf944906a, 0x0, 0xf8000000, 0xf8000000, 0xf944906a, 0x0, 0x0,
				0x0, 0x0, 0xf8000000, 0x0, 0xf944906a, 0xf944906a, 0x0, 0xfa22771e, 0xfa22771e, 0xf800100e, 0x0, 0x0, 0x10000000, 0x0, 0x10000000, 0x0, 0x88000000, 0x40000000,
				0x88000000, };
	}

	private static void jj_la1_init_3() {
		jj_la1_3 = new int[] { 0x48000012, 0x48000002, 0x48000012, 0x0, 0x0, 0x20000000, 0x40000010, 0x0, 0x0, 0x0, 0x0, 0x10000000, 0x10000000, 0x0, 0x4004001f, 0x10000000,
				0x4884001f, 0x4884001f, 0x8000000, 0x40000000, 0x200000, 0x800000, 0x10000000, 0x0, 0x0, 0x4884001f, 0x4884001f, 0x0, 0x4004001f, 0x4004001f, 0x10000000,
				0x80000000, 0x42000000, 0xa7087f, 0xa7087f, 0x10000000, 0x0, 0x42000000, 0x10000000, 0x0, 0x8800000, 0x10000000, 0x4004001f, 0x10000000, 0x10000000, 0x40000000,
				0x4001f, 0x4001f, 0x0, 0x10000000, 0x0, 0x0, 0x0, 0x27087f, 0x0, 0x0, 0x4001f, 0x4001f, 0x10000000, 0x4004001f, 0x4001f, 0x0, 0x0, 0x0, 0x4001f, 0x4001f, 0x0,
				0x4001f, 0x0, 0x80000000, 0x80000000, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x27087f, 0x0, 0x0, 0x27087f, 0x0, 0x0,
				0x4001f, 0x0, 0x200000, 0x0, 0x4001f, 0x20000000, 0x10000000, 0x10000000, 0x27087f, 0x4027087f, 0x0, 0x4001f, 0x200000, 0x230860, 0x4001f, 0x20000000, 0x0,
				0x200000, 0x0, 0x22000000, 0x30860, 0x0, 0x27087f, 0x10000000, 0x0, 0x42200000, 0x4004001f, 0x27087f, 0x800000, 0x8a7087f, 0x0, 0x8a7087f, 0x10000000, 0xa7087f,
				0x0, 0x4001f, 0x80000000, 0x80000000, 0x27087f, 0x0, 0x0, 0x0, 0x4027087f, 0x27087f, 0x27087f, 0x4827087f, 0x27087f, 0x10000000, 0x4001f, 0x4001f, 0x27087f,
				0x200000, 0x0, 0x0, 0x0, 0x4001f, 0x10000000, 0x40a7087f, 0x40a7087f, 0x10000000, 0x4804001f, 0x4804001f, 0x4004001f, 0x0, 0x10000000, 0x0, 0x10000000, 0x0,
				0x10000000, 0xd, 0x0, 0xd, };
	}

	private static void jj_la1_init_4() {
		jj_la1_4 = new int[] { 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x1, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x1, 0x1, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x20000, 0x1, 0x1, 0x0, 0x1,
				0x1, 0x0, 0x0, 0x0, 0x7806, 0x7806, 0x0, 0x1, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x1, 0x0, 0x0, 0x1, 0x1, 0x1, 0x0, 0x0, 0x0, 0x0, 0x0, 0x8,
				0x8, 0x0, 0x0, 0x0, 0x0, 0x0, 0x1, 0x0, 0x0, 0xffc00000, 0xffc00000, 0x8, 0x200, 0x400, 0x40000, 0x80000, 0x20000, 0x120, 0x120, 0x0, 0xc1, 0xc1, 0x200000, 0x6000,
				0x6000, 0x118000, 0x118000, 0x6000, 0x7806, 0x6, 0x6, 0x0, 0x1800, 0x20000, 0x0, 0x1, 0x0, 0x1, 0x0, 0x0, 0x0, 0x0, 0x7806, 0x7806, 0x1, 0x0, 0x0, 0x0, 0x0, 0x0,
				0x1, 0x0, 0x0, 0x0, 0x0, 0x0, 0x7806, 0x0, 0x1, 0x0, 0x1, 0x7806, 0x0, 0x1800, 0x10, 0x1800, 0x0, 0x7806, 0x1, 0x0, 0xffc01800, 0xffc01800, 0x1000, 0x0, 0x0, 0x0,
				0x7806, 0x7806, 0x7806, 0x7806, 0x7806, 0x0, 0x0, 0x0, 0x7806, 0x0, 0x0, 0x40000, 0x0, 0x0, 0x0, 0x7806, 0x7806, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0,
				0x0, 0x0, 0x0, };
	}

	private static void jj_la1_init_5() {
		jj_la1_5 = new int[] { 0x0, 0x0, 0x0, 0x80, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0,
				0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x2, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0,
				0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0xc, 0xd, 0x1, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x40, 0x40, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0,
				0x0, 0x0, 0x0, 0x0, 0x0, 0x8, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0,
				0x0, 0x0, 0x0, 0xd, 0xd, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0,
				0x0, 0x0, 0x0, 0x0, 0x0, 0x0, };
	}

	final private JJCalls[] jj_2_rtns = new JJCalls[50];
	private boolean jj_rescan = false;
	private int jj_gc = 0;

	/** Constructor. */
	public GeneratedJavaParser(Provider stream) {
		jj_input_stream = new JavaCharStream(stream, 1, 1);
		token_source = new GeneratedJavaParserTokenManager(jj_input_stream);
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 166; i++)
			jj_la1[i] = -1;
		for (int i = 0; i < jj_2_rtns.length; i++)
			jj_2_rtns[i] = new JJCalls();
	}

	/** Constructor. */
	public GeneratedJavaParser(String dsl) throws ParseException, TokenMgrException {
		this(new StringProvider(dsl));
	}

	public void ReInit(String s) {
		ReInit(new StringProvider(s));
	}

	/** Reinitialise. */
	public void ReInit(Provider stream) {
		if (jj_input_stream == null) {
			jj_input_stream = new JavaCharStream(stream, 1, 1);
		} else {
			jj_input_stream.ReInit(stream, 1, 1);
		}
		if (token_source == null) {
			token_source = new GeneratedJavaParserTokenManager(jj_input_stream);
		}

		token_source.ReInit(jj_input_stream);
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 166; i++)
			jj_la1[i] = -1;
		for (int i = 0; i < jj_2_rtns.length; i++)
			jj_2_rtns[i] = new JJCalls();
	}

	/** Constructor with generated Token Manager. */
	public GeneratedJavaParser(GeneratedJavaParserTokenManager tm) {
		token_source = tm;
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 166; i++)
			jj_la1[i] = -1;
		for (int i = 0; i < jj_2_rtns.length; i++)
			jj_2_rtns[i] = new JJCalls();
	}

	/** Reinitialise. */
	public void ReInit(GeneratedJavaParserTokenManager tm) {
		token_source = tm;
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 166; i++)
			jj_la1[i] = -1;
		for (int i = 0; i < jj_2_rtns.length; i++)
			jj_2_rtns[i] = new JJCalls();
	}

	private Token jj_consume_token(int kind) throws ParseException {
		Token oldToken;
		if ((oldToken = token).next != null)
			token = token.next;
		else
			token = token.next = token_source.getNextToken();
		jj_ntk = -1;
		if (token.kind == kind) {
			jj_gen++;
			if (++jj_gc > 100) {
				jj_gc = 0;
				for (int i = 0; i < jj_2_rtns.length; i++) {
					JJCalls c = jj_2_rtns[i];
					while (c != null) {
						if (c.gen < jj_gen)
							c.first = null;
						c = c.next;
					}
				}
			}
			return token;
		}
		token = oldToken;
		jj_kind = kind;
		throw generateParseException();
	}

	@SuppressWarnings("serial")
	static private final class LookaheadSuccess extends java.lang.RuntimeException {}

	final private LookaheadSuccess jj_ls = new LookaheadSuccess();

	private boolean jj_scan_token(int kind) {
		if (jj_scanpos == jj_lastpos) {
			jj_la--;
			if (jj_scanpos.next == null) {
				jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
			} else {
				jj_lastpos = jj_scanpos = jj_scanpos.next;
			}
		} else {
			jj_scanpos = jj_scanpos.next;
		}
		if (jj_rescan) {
			int i = 0;
			Token tok = token;
			while (tok != null && tok != jj_scanpos) {
				i++;
				tok = tok.next;
			}
			if (tok != null)
				jj_add_error_token(kind, i);
		}
		if (jj_scanpos.kind != kind)
			return true;
		if (jj_la == 0 && jj_scanpos == jj_lastpos)
			throw jj_ls;
		return false;
	}

	/** Get the next Token. */
	final public Token getNextToken() {
		if (token.next != null)
			token = token.next;
		else
			token = token.next = token_source.getNextToken();
		jj_ntk = -1;
		jj_gen++;
		return token;
	}

	/** Get the specific Token. */
	final public Token getToken(int index) {
		Token t = jj_lookingAhead ? jj_scanpos : token;
		for (int i = 0; i < index; i++) {
			if (t.next != null)
				t = t.next;
			else
				t = t.next = token_source.getNextToken();
		}
		return t;
	}

	private int jj_ntk_f() {
		if ((jj_nt = token.next) == null)
			return (jj_ntk = (token.next = token_source.getNextToken()).kind);
		else
			return (jj_ntk = jj_nt.kind);
	}

	private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
	private int[] jj_expentry;
	private int jj_kind = -1;
	private int[] jj_lasttokens = new int[100];
	private int jj_endpos;

	private void jj_add_error_token(int kind, int pos) {
		if (pos >= 100) {
			return;
		}

		if (pos == jj_endpos + 1) {
			jj_lasttokens[jj_endpos++] = kind;
		} else if (jj_endpos != 0) {
			jj_expentry = new int[jj_endpos];

			for (int i = 0; i < jj_endpos; i++) {
				jj_expentry[i] = jj_lasttokens[i];
			}

			for (int[] oldentry : jj_expentries) {
				if (oldentry.length == jj_expentry.length) {
					boolean isMatched = true;

					for (int i = 0; i < jj_expentry.length; i++) {
						if (oldentry[i] != jj_expentry[i]) {
							isMatched = false;
							break;
						}

					}
					if (isMatched) {
						jj_expentries.add(jj_expentry);
						break;
					}
				}
			}

			if (pos != 0) {
				jj_lasttokens[(jj_endpos = pos) - 1] = kind;
			}
		}
	}

	/** Generate ParseException. */
	public ParseException generateParseException() {
		jj_expentries.clear();
		boolean[] la1tokens = new boolean[168];
		if (jj_kind >= 0) {
			la1tokens[jj_kind] = true;
			jj_kind = -1;
		}
		for (int i = 0; i < 166; i++) {
			if (jj_la1[i] == jj_gen) {
				for (int j = 0; j < 32; j++) {
					if ((jj_la1_0[i] & (1 << j)) != 0) {
						la1tokens[j] = true;
					}
					if ((jj_la1_1[i] & (1 << j)) != 0) {
						la1tokens[32 + j] = true;
					}
					if ((jj_la1_2[i] & (1 << j)) != 0) {
						la1tokens[64 + j] = true;
					}
					if ((jj_la1_3[i] & (1 << j)) != 0) {
						la1tokens[96 + j] = true;
					}
					if ((jj_la1_4[i] & (1 << j)) != 0) {
						la1tokens[128 + j] = true;
					}
					if ((jj_la1_5[i] & (1 << j)) != 0) {
						la1tokens[160 + j] = true;
					}
				}
			}
		}
		for (int i = 0; i < 168; i++) {
			if (la1tokens[i]) {
				jj_expentry = new int[1];
				jj_expentry[0] = i;
				jj_expentries.add(jj_expentry);
			}
		}
		jj_endpos = 0;
		jj_rescan_token();
		jj_add_error_token(0, 0);
		int[][] exptokseq = new int[jj_expentries.size()][];
		for (int i = 0; i < jj_expentries.size(); i++) {
			exptokseq[i] = jj_expentries.get(i);
		}
		return new ParseException(token, exptokseq, tokenImage, token_source == null ? null : GeneratedJavaParserTokenManager.lexStateNames[token_source.curLexState]);
	}

	/** Enable tracing. */
	final public void enable_tracing() {}

	/** Disable tracing. */
	final public void disable_tracing() {}

	private void jj_rescan_token() {
		jj_rescan = true;
		for (int i = 0; i < 50; i++) {
			try {
				JJCalls p = jj_2_rtns[i];

				do {
					if (p.gen > jj_gen) {
						jj_la = p.arg;
						jj_lastpos = jj_scanpos = p.first;
						switch (i) {
							case 0:
								jj_3_1();
								break;
							case 1:
								jj_3_2();
								break;
							case 2:
								jj_3_3();
								break;
							case 3:
								jj_3_4();
								break;
							case 4:
								jj_3_5();
								break;
							case 5:
								jj_3_6();
								break;
							case 6:
								jj_3_7();
								break;
							case 7:
								jj_3_8();
								break;
							case 8:
								jj_3_9();
								break;
							case 9:
								jj_3_10();
								break;
							case 10:
								jj_3_11();
								break;
							case 11:
								jj_3_12();
								break;
							case 12:
								jj_3_13();
								break;
							case 13:
								jj_3_14();
								break;
							case 14:
								jj_3_15();
								break;
							case 15:
								jj_3_16();
								break;
							case 16:
								jj_3_17();
								break;
							case 17:
								jj_3_18();
								break;
							case 18:
								jj_3_19();
								break;
							case 19:
								jj_3_20();
								break;
							case 20:
								jj_3_21();
								break;
							case 21:
								jj_3_22();
								break;
							case 22:
								jj_3_23();
								break;
							case 23:
								jj_3_24();
								break;
							case 24:
								jj_3_25();
								break;
							case 25:
								jj_3_26();
								break;
							case 26:
								jj_3_27();
								break;
							case 27:
								jj_3_28();
								break;
							case 28:
								jj_3_29();
								break;
							case 29:
								jj_3_30();
								break;
							case 30:
								jj_3_31();
								break;
							case 31:
								jj_3_32();
								break;
							case 32:
								jj_3_33();
								break;
							case 33:
								jj_3_34();
								break;
							case 34:
								jj_3_35();
								break;
							case 35:
								jj_3_36();
								break;
							case 36:
								jj_3_37();
								break;
							case 37:
								jj_3_38();
								break;
							case 38:
								jj_3_39();
								break;
							case 39:
								jj_3_40();
								break;
							case 40:
								jj_3_41();
								break;
							case 41:
								jj_3_42();
								break;
							case 42:
								jj_3_43();
								break;
							case 43:
								jj_3_44();
								break;
							case 44:
								jj_3_45();
								break;
							case 45:
								jj_3_46();
								break;
							case 46:
								jj_3_47();
								break;
							case 47:
								jj_3_48();
								break;
							case 48:
								jj_3_49();
								break;
							case 49:
								jj_3_50();
								break;
						}
					}
					p = p.next;
				} while (p != null);

			} catch (LookaheadSuccess ls) {
			}
		}
		jj_rescan = false;
	}

	private void jj_save(int index, int xla) {
		JJCalls p = jj_2_rtns[index];
		while (p.gen > jj_gen) {
			if (p.next == null) {
				p = p.next = new JJCalls();
				break;
			}
			p = p.next;
		}

		p.gen = jj_gen + xla - jj_la;
		p.first = token;
		p.arg = xla;
	}

	static final class JJCalls {
		int gen;
		Token first;
		int arg;
		JJCalls next;
	}

}
