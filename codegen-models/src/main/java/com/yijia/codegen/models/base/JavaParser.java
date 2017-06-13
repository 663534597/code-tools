package com.yijia.codegen.models.base;

import com.yijia.codegen.models.base.ast.CompilationUnit;
import com.yijia.codegen.models.base.ast.ImportDeclaration;
import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.body.BodyDeclaration;
import com.yijia.codegen.models.base.ast.body.Parameter;
import com.yijia.codegen.models.base.ast.comments.CommentsCollection;
import com.yijia.codegen.models.base.ast.expr.AnnotationExpr;
import com.yijia.codegen.models.base.ast.expr.Expression;
import com.yijia.codegen.models.base.ast.expr.Name;
import com.yijia.codegen.models.base.ast.expr.VariableDeclarationExpr;
import com.yijia.codegen.models.base.ast.stmt.BlockStmt;
import com.yijia.codegen.models.base.ast.stmt.ExplicitConstructorInvocationStmt;
import com.yijia.codegen.models.base.ast.stmt.Statement;
import com.yijia.codegen.models.base.ast.type.ClassOrInterfaceType;
import com.yijia.codegen.models.base.ast.type.Type;
import com.yijia.codegen.models.base.ast.validator.ProblemReporter;
import com.yijia.codegen.models.base.javadoc.Javadoc;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.TreeSet;
import static com.yijia.codegen.models.base.ParseStart.*;
import static com.yijia.codegen.models.base.Problem.PROBLEM_BY_BEGIN_POSITION;
import static com.yijia.codegen.models.base.Providers.*;
import static com.yijia.codegen.models.base.utils.Utils.assertNotNull;

@SuppressWarnings("rawtypes")
public final class JavaParser {
	private final CommentsInserter commentsInserter;
	private final ParserConfiguration configuration;

	private GeneratedJavaParser astParser = null;
	private static ParserConfiguration staticConfiguration = new ParserConfiguration();

	/**
	 * Instantiate the parser with default configuration. Note that parsing can also be done with the static methods on this class. Creating an instance will reduce setup time
	 * between parsing files.
	 */
	public JavaParser() {
		this(new ParserConfiguration());
	}

	/**
	 * Instantiate the parser. Note that parsing can also be done with the static methods on this class. Creating an instance will reduce setup time between parsing files.
	 */
	public JavaParser(ParserConfiguration configuration) {
		this.configuration = configuration;
		commentsInserter = new CommentsInserter(configuration);
	}

	/**
	 * Get the configuration for the static parse... methods. This is a STATIC field, so modifying it will directly change how all static parse... methods work!
	 */
	public static ParserConfiguration getStaticConfiguration() {
		return staticConfiguration;
	}

	/**
	 * Set the configuration for the static parse... methods. This is a STATIC field, so modifying it will directly change how all static parse... methods work!
	 */
	public static void setStaticConfiguration(ParserConfiguration staticConfiguration) {
		JavaParser.staticConfiguration = staticConfiguration;
	}

	private GeneratedJavaParser getParserForProvider(Provider provider) {
		if (astParser == null) {
			astParser = new GeneratedJavaParser(provider);
		} else {
			astParser.reset(provider);
		}
		astParser.setTabSize(configuration.getTabSize());
		return astParser;
	}

	/**
	 * Parses source code. It takes the source code from a Provider. The start indicates what can be found in the source code (compilation unit, block, import...)
	 * @param start refer to the constants in ParseStart to see what can be parsed.
	 * @param provider refer to Providers to see how you can read source. The provider will be closed after parsing.
	 * @param <N> the subclass of Node that is the result of parsing in the start.
	 * @return the parse result, a collection of encountered problems, and some extra data.
	 */
	public <N extends Node> ParseResult<N> parse(ParseStart<N> start, Provider provider) {
		assertNotNull(start);
		assertNotNull(provider);
		final GeneratedJavaParser parser = getParserForProvider(provider);
		try {
			N resultNode = start.parse(parser);
			if (configuration.isAttributeComments()) {
				final CommentsCollection comments = parser.getCommentsCollection();
				commentsInserter.insertComments(resultNode, comments.copy().getComments());
			}

			configuration.getValidator().accept(resultNode, new ProblemReporter(parser.problems));
			parser.problems.sort(PROBLEM_BY_BEGIN_POSITION);

			return new ParseResult<>(resultNode, parser.problems, parser.getTokens(), parser.getCommentsCollection());
		} catch (ParseException p) {
			TokenRange tokenRange = null;
			if (p.currentToken != null) {
				if (p.currentToken instanceof GeneratedJavaParser.CustomToken) {
					final JavaToken token = ((GeneratedJavaParser.CustomToken) p.currentToken).javaToken;
					tokenRange = new TokenRange(token, token);
				}
			}
			parser.problems.add(new Problem(makeMessageForParseException(p), tokenRange, p));
			return new ParseResult<>(null, parser.problems, parser.getTokens(), parser.getCommentsCollection());
		} catch (Exception e) {
			final String message = e.getMessage() == null ? "Unknown error" : e.getMessage();
			parser.problems.add(new Problem(message, null, e));
			return new ParseResult<>(null, parser.problems, parser.getTokens(), parser.getCommentsCollection());
		} finally {
			try {
				provider.close();
			} catch (IOException e) {
				// Since we're done parsing and have our result, we don't care about any errors.
			}
		}
	}

	/**
	 * This is the code from ParseException.initialise, modified to be more horizontal.
	 */
	private String makeMessageForParseException(ParseException exception) {
		final StringBuilder sb = new StringBuilder("Parse error. Found ");
		final StringBuilder expected = new StringBuilder();

		int maxExpectedTokenSequenceLength = 0;
		TreeSet<String> sortedOptions = new TreeSet<>();
		for (int i = 0; i < exception.expectedTokenSequences.length; i++) {
			if (maxExpectedTokenSequenceLength < exception.expectedTokenSequences[i].length) {
				maxExpectedTokenSequenceLength = exception.expectedTokenSequences[i].length;
			}
			for (int j = 0; j < exception.expectedTokenSequences[i].length; j++) {
				sortedOptions.add(exception.tokenImage[exception.expectedTokenSequences[i][j]]);
			}
		}

		for (String option : sortedOptions) {
			expected.append(" ").append(option);
		}

		sb.append("");

		Token token = exception.currentToken.next;
		for (int i = 0; i < maxExpectedTokenSequenceLength; i++) {
			String tokenText = token.image;
			String escapedTokenText = ParseException.add_escapes(tokenText);
			if (i != 0) {
				sb.append(" ");
			}
			if (token.kind == 0) {
				sb.append(exception.tokenImage[0]);
				break;
			}
			escapedTokenText = "\"" + escapedTokenText + "\"";
			String image = exception.tokenImage[token.kind];
			if (image.equals(escapedTokenText)) {
				sb.append(image);
			} else {
				sb.append(" ").append(escapedTokenText).append(" ").append(image);
			}
			token = token.next;
		}

		if (exception.expectedTokenSequences.length != 0) {
			int numExpectedTokens = exception.expectedTokenSequences.length;
			sb.append(", expected").append(numExpectedTokens == 1 ? "" : " one of ").append(expected.toString());
		}
		return sb.toString();

	}

	/**
	 * Parses the Java code contained in the {@link InputStream} and returns a {@link CompilationUnit} that represents it.
	 * @param in {@link InputStream} containing Java source code. It will be closed after parsing.
	 * @param encoding encoding of the source code
	 * @return CompilationUnit representing the Java source code
	 * @throws ParseProblemException if the source code has parser errors
	 */
	public static CompilationUnit parse(final InputStream in, Charset encoding) {
		return simplifiedParse(COMPILATION_UNIT, provider(in, encoding));
	}

	/**
	 * Parses the Java code contained in the {@link InputStream} and returns a {@link CompilationUnit} that represents it.<br>
	 * Note: Uses UTF-8 encoding
	 * @param in {@link InputStream} containing Java source code. It will be closed after parsing.
	 * @return CompilationUnit representing the Java source code
	 * @throws ParseProblemException if the source code has parser errors
	 */
	public static CompilationUnit parse(final InputStream in) {
		return parse(in, UTF8);
	}

	/**
	 * Parses the Java code contained in a {@link File} and returns a {@link CompilationUnit} that represents it.
	 * @param file {@link File} containing Java source code. It will be closed after parsing.
	 * @param encoding encoding of the source code
	 * @return CompilationUnit representing the Java source code
	 * @throws ParseProblemException if the source code has parser errors
	 * @throws FileNotFoundException the file was not found
	 */
	public static CompilationUnit parse(final File file, final Charset encoding) throws FileNotFoundException {
		return simplifiedParse(COMPILATION_UNIT, provider(file, encoding)).setStorage(file.toPath());
	}

	/**
	 * Parses the Java code contained in a {@link File} and returns a {@link CompilationUnit} that represents it.<br>
	 * Note: Uses UTF-8 encoding
	 * @param file {@link File} containing Java source code. It will be closed after parsing.
	 * @return CompilationUnit representing the Java source code
	 * @throws ParseProblemException if the source code has parser errors
	 * @throws FileNotFoundException the file was not found
	 */
	public static CompilationUnit parse(final File file) throws FileNotFoundException {
		return simplifiedParse(COMPILATION_UNIT, provider(file)).setStorage(file.toPath());
	}

	/**
	 * Parses the Java code contained in a file and returns a {@link CompilationUnit} that represents it.
	 * @param path path to a file containing Java source code
	 * @param encoding encoding of the source code
	 * @return CompilationUnit representing the Java source code
	 * @throws IOException the path could not be accessed
	 * @throws ParseProblemException if the source code has parser errors
	 */
	public static CompilationUnit parse(final Path path, final Charset encoding) throws IOException {
		return simplifiedParse(COMPILATION_UNIT, provider(path, encoding)).setStorage(path);
	}

	/**
	 * Parses the Java code contained in a file and returns a {@link CompilationUnit} that represents it.<br>
	 * Note: Uses UTF-8 encoding
	 * @param path path to a file containing Java source code
	 * @return CompilationUnit representing the Java source code
	 * @throws ParseProblemException if the source code has parser errors
	 * @throws IOException the path could not be accessed
	 */
	public static CompilationUnit parse(final Path path) throws IOException {
		return simplifiedParse(COMPILATION_UNIT, provider(path)).setStorage(path);
	}

	/**
	 * Parses the Java code contained in a resource and returns a {@link CompilationUnit} that represents it.<br>
	 * Note: Uses UTF-8 encoding
	 * @param path path to a resource containing Java source code. As resource is accessed through a class loader, a leading "/" is not allowed in pathToResource
	 * @return CompilationUnit representing the Java source code
	 * @throws ParseProblemException if the source code has parser errors
	 * @throws IOException the path could not be accessed
	 */
	public static CompilationUnit parseResource(final String path) throws IOException {
		return simplifiedParse(COMPILATION_UNIT, resourceProvider(path));
	}

	/**
	 * Parses the Java code contained in a resource and returns a {@link CompilationUnit} that represents it.<br>
	 * @param path path to a resource containing Java source code. As resource is accessed through a class loader, a leading "/" is not allowed in pathToResource
	 * @param encoding encoding of the source code
	 * @return CompilationUnit representing the Java source code
	 * @throws ParseProblemException if the source code has parser errors
	 * @throws IOException the path could not be accessed
	 */
	public static CompilationUnit parseResource(final String path, Charset encoding) throws IOException {
		return simplifiedParse(COMPILATION_UNIT, resourceProvider(path, encoding));
	}

	/**
	 * Parses the Java code contained in a resource and returns a {@link CompilationUnit} that represents it.<br>
	 * @param classLoader the classLoader that is asked to load the resource
	 * @param path path to a resource containing Java source code. As resource is accessed through a class loader, a leading "/" is not allowed in pathToResource
	 * @return CompilationUnit representing the Java source code
	 * @throws ParseProblemException if the source code has parser errors
	 * @throws IOException the path could not be accessed
	 */
	public static CompilationUnit parseResource(final ClassLoader classLoader, final String path, Charset encoding) throws IOException {
		return simplifiedParse(COMPILATION_UNIT, resourceProvider(classLoader, path, encoding));
	}

	/**
	 * Parses Java code from a Reader and returns a {@link CompilationUnit} that represents it.<br>
	 * @param reader the reader containing Java source code. It will be closed after parsing.
	 * @return CompilationUnit representing the Java source code
	 * @throws ParseProblemException if the source code has parser errors
	 */
	public static CompilationUnit parse(final Reader reader) {
		return simplifiedParse(COMPILATION_UNIT, provider(reader));
	}

	/**
	 * Parses the Java code contained in code and returns a {@link CompilationUnit} that represents it.
	 * @param code Java source code
	 * @return CompilationUnit representing the Java source code
	 * @throws ParseProblemException if the source code has parser errors
	 */
	public static CompilationUnit parse(String code) {
		return simplifiedParse(COMPILATION_UNIT, provider(code));
	}

	/**
	 * Parses the Java block contained in a {@link String} and returns a {@link BlockStmt} that represents it.
	 * @param blockStatement {@link String} containing Java block code
	 * @return BlockStmt representing the Java block
	 * @throws ParseProblemException if the source code has parser errors
	 */
	public static BlockStmt parseBlock(final String blockStatement) {
		return simplifiedParse(BLOCK, provider(blockStatement));
	}

	/**
	 * Parses the Java statement contained in a {@link String} and returns a {@link Statement} that represents it.
	 * @param statement {@link String} containing Java statement code
	 * @return Statement representing the Java statement
	 * @throws ParseProblemException if the source code has parser errors
	 */
	public static Statement parseStatement(final String statement) {
		return simplifiedParse(STATEMENT, provider(statement));
	}

	private static <T extends Node> T simplifiedParse(ParseStart<T> context, Provider provider) {
		ParseResult<T> result = new JavaParser(staticConfiguration).parse(context, provider);
		if (result.isSuccessful()) {
			return result.getResult().get();
		}
		throw new ParseProblemException(result.getProblems());
	}

	/**
	 * Parses the Java import contained in a {@link String} and returns a {@link ImportDeclaration} that represents it.
	 * @param importDeclaration {@link String} containing Java import code
	 * @return ImportDeclaration representing the Java import declaration
	 * @throws ParseProblemException if the source code has parser errors
	 */
	public static ImportDeclaration parseImport(final String importDeclaration) {
		return simplifiedParse(IMPORT_DECLARATION, provider(importDeclaration));
	}

	/**
	 * Parses the Java expression contained in a {@link String} and returns a {@link Expression} that represents it.
	 * @param expression {@link String} containing Java expression
	 * @return Expression representing the Java expression
	 * @throws ParseProblemException if the source code has parser errors
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Expression> T parseExpression(final String expression) {
		return (T) simplifiedParse(EXPRESSION, provider(expression));
	}

	/**
	 * Parses the Java annotation contained in a {@link String} and returns a {@link AnnotationExpr} that represents it.
	 * @param annotation {@link String} containing Java annotation
	 * @return AnnotationExpr representing the Java annotation
	 * @throws ParseProblemException if the source code has parser errors
	 */
	public static AnnotationExpr parseAnnotation(final String annotation) {
		return simplifiedParse(ANNOTATION, provider(annotation));
	}

	/**
	 * Parses the Java annotation body declaration(e.g fields or methods) contained in a {@link String} and returns a {@link BodyDeclaration} that represents it.
	 * @param body {@link String} containing Java body declaration
	 * @return BodyDeclaration representing the Java annotation
	 * @throws ParseProblemException if the source code has parser errors
	 */
	public static BodyDeclaration<?> parseAnnotationBodyDeclaration(final String body) {
		return simplifiedParse(ANNOTATION_BODY, provider(body));
	}

	/**
	 * Parses a Java class body declaration(e.g fields or methods) and returns a {@link BodyDeclaration} that represents it.
	 * @param body the body of a class
	 * @return BodyDeclaration representing the Java class body
	 * @throws ParseProblemException if the source code has parser errors
	 * @deprecated just use parseBodyDeclaration now.
	 */
	@Deprecated
	public static BodyDeclaration<?> parseClassBodyDeclaration(String body) {
		return parseBodyDeclaration(body);
	}

	/**
	 * Parses a Java interface body declaration(e.g fields or methods) and returns a {@link BodyDeclaration} that represents it.
	 * @param body the body of an interface
	 * @return BodyDeclaration representing the Java interface body
	 * @throws ParseProblemException if the source code has parser errors
	 * @deprecated just use parseBodyDeclaration now.
	 */
	@Deprecated
	public static BodyDeclaration parseInterfaceBodyDeclaration(String body) {
		return parseBodyDeclaration(body);
	}

	/**
	 * Parses a Java class or interface body declaration(e.g fields or methods) and returns a {@link BodyDeclaration} that represents it.
	 * @param body the body of a class or interface
	 * @return BodyDeclaration representing the Java interface body
	 * @throws ParseProblemException if the source code has parser errors
	 */
	public static BodyDeclaration parseBodyDeclaration(String body) {
		return simplifiedParse(CLASS_BODY, provider(body));
	}

	/**
	 * Parses a Java class or interface type name and returns a {@link ClassOrInterfaceType} that represents it.
	 * @param type the type name like a.b.c.X or Y
	 * @return ClassOrInterfaceType representing the type
	 * @throws ParseProblemException if the source code has parser errors
	 */
	public static ClassOrInterfaceType parseClassOrInterfaceType(String type) {
		return simplifiedParse(CLASS_OR_INTERFACE_TYPE, provider(type));
	}

	/**
	 * Parses a Java type name and returns a {@link Type} that represents it.
	 * @param type the type name like a.b.c.X, Y, or int
	 * @return ClassOrInterfaceType representing the type
	 * @throws ParseProblemException if the source code has parser errors
	 */
	public static Type parseType(String type) {
		return simplifiedParse(TYPE, provider(type));
	}

	/**
	 * Parses a variable declaration expression and returns a {@link com.yijia.codegen.models.base.ast.expr.VariableDeclarationExpr} that represents it.
	 * @param declaration a variable declaration like <code>int x=2;</code>
	 * @return VariableDeclarationExpr representing the type
	 * @throws ParseProblemException if the source code has parser errors
	 */
	public static VariableDeclarationExpr parseVariableDeclarationExpr(String declaration) {
		return simplifiedParse(VARIABLE_DECLARATION_EXPR, provider(declaration));
	}

	/**
	 * Parses the content of a JavadocComment and returns a {@link com.yijia.codegen.models.base.javadoc.Javadoc} that represents it.
	 * @param content a variable declaration like <code>content of my javadoc\n * second line\n * third line</code>
	 * @return Javadoc representing the content of the comment
	 * @throws ParseProblemException if the source code has parser errors
	 */
	public static Javadoc parseJavadoc(String content) {
		return JavadocParser.parse(content);
	}

	/**
	 * Parses the this(...) and super(...) statements that may occur at the start of a constructor.
	 * @param statement a statement like super("hello");
	 * @return the AST for the statement.
	 * @throws ParseProblemException if the source code has parser errors
	 */
	public static ExplicitConstructorInvocationStmt parseExplicitConstructorInvocationStmt(String statement) {
		return simplifiedParse(EXPLICIT_CONSTRUCTOR_INVOCATION_STMT, provider(statement));
	}

	/**
	 * Parses a qualified name (one that can have "."s in it) and returns it as a Name.
	 * @param qualifiedName a name like "com.laamella.parameter_source"
	 * @return the AST for the name
	 * @throws ParseProblemException if the source code has parser errors
	 */
	public static Name parseName(String qualifiedName) {
		return simplifiedParse(NAME, provider(qualifiedName));
	}

	/**
	 * Parses a single parameter (a type and a name) and returns it as a Parameter.
	 * @param parameter a parameter like "int[] x"
	 * @return the AST for the parameter
	 * @throws ParseProblemException if the source code has parser errors
	 */
	public static Parameter parseParameter(String parameter) {
		return simplifiedParse(PARAMETER, provider(parameter));
	}

}
