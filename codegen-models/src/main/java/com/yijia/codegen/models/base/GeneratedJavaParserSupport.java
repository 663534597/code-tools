package com.yijia.codegen.models.base;

import static com.yijia.codegen.models.base.ast.type.ArrayType.unwrapArrayTypes;
import static com.yijia.codegen.models.base.ast.type.ArrayType.wrapInArrayTypes;

import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.yijia.codegen.models.base.GeneratedJavaParser.CustomToken;
import com.yijia.codegen.models.base.ast.ArrayCreationLevel;
import com.yijia.codegen.models.base.ast.Modifier;
import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.NodeList;
import com.yijia.codegen.models.base.ast.body.Parameter;
import com.yijia.codegen.models.base.ast.expr.AnnotationExpr;
import com.yijia.codegen.models.base.ast.expr.ArrayCreationExpr;
import com.yijia.codegen.models.base.ast.expr.ArrayInitializerExpr;
import com.yijia.codegen.models.base.ast.expr.CastExpr;
import com.yijia.codegen.models.base.ast.expr.EnclosedExpr;
import com.yijia.codegen.models.base.ast.expr.Expression;
import com.yijia.codegen.models.base.ast.expr.LambdaExpr;
import com.yijia.codegen.models.base.ast.expr.NameExpr;
import com.yijia.codegen.models.base.ast.expr.SimpleName;
import com.yijia.codegen.models.base.ast.stmt.Statement;
import com.yijia.codegen.models.base.ast.type.ArrayType;
import com.yijia.codegen.models.base.ast.type.Type;
import com.yijia.codegen.models.base.ast.type.UnknownType;
import com.yijia.codegen.models.base.utils.Pair;

/**
 * Support class for {@link GeneratedJavaParser}
 */
class GeneratedJavaParserSupport {
	static <X extends Node> NodeList<X> emptyList() {
		return new NodeList<X>();
	}

	static <T extends Node> NodeList<T> add(NodeList<T> list, T obj) {
		if (list == null) {
			list = new NodeList<T>();
		}
		list.add(obj);
		return list;
	}

	static <T extends Node> NodeList<T> addWhenNotNull(NodeList<T> list, T obj) {
		if (obj == null) {
			return list;
		}
		return add(list, obj);
	}

	static <T extends Node> NodeList<T> add(int pos, NodeList<T> list, T obj) {
		if (list == null) {
			list = new NodeList<T>();
		}
		list.add(pos, obj);
		return list;
	}

	static <T> List<T> add(List<T> list, T obj) {
		if (list == null) {
			list = new LinkedList<T>();
		}
		list.add(obj);
		return list;
	}

	static void addModifier(GeneratedJavaParser generatedJavaParser, EnumSet<Modifier> modifiers, Modifier mod) {
		if (modifiers.contains(mod)) {
			generatedJavaParser.addProblem("Duplicated modifier");
		}
		modifiers.add(mod);
	}

	static TokenRange range(JavaToken begin, JavaToken end) {
		return new TokenRange(begin, end);
	}

	static TokenRange range(Node begin, Node end) {
		return new TokenRange(begin.getTokenRange().get().getBegin(), end.getTokenRange().get().getEnd());
	}

	static Expression generateLambda(GeneratedJavaParser generatedJavaParser, Expression ret, Statement lambdaBody) {
		if (ret instanceof EnclosedExpr) {
			Optional<Expression> inner = ((EnclosedExpr) ret).getInner();
			if (inner.isPresent() && inner.get() instanceof NameExpr) {
				SimpleName id = ((NameExpr) inner.get()).getName();
				NodeList<Parameter> params = add(new NodeList<>(),
						new Parameter(ret.getTokenRange().get(), EnumSet.noneOf(Modifier.class), new NodeList<>(), new UnknownType(), false, new NodeList<>(), id));
				ret = new LambdaExpr(range(ret, lambdaBody), params, lambdaBody, true);
			} else {
				ret = new LambdaExpr(range(ret, lambdaBody), new NodeList<>(), lambdaBody, true);
			}
		} else if (ret instanceof NameExpr) {
			SimpleName id = ((NameExpr) ret).getName();
			NodeList<Parameter> params = add(new NodeList<>(),
					new Parameter(ret.getTokenRange().get(), EnumSet.noneOf(Modifier.class), new NodeList<>(), new UnknownType(), false, new NodeList<>(), id));
			ret = new LambdaExpr(range(ret, lambdaBody), params, lambdaBody, false);
		} else if (ret instanceof LambdaExpr) {
			((LambdaExpr) ret).setBody(lambdaBody);
			ret.setTokenRange(range(ret, lambdaBody));
		} else if (ret instanceof CastExpr) {
			CastExpr castExpr = (CastExpr) ret;
			Expression inner = generateLambda(generatedJavaParser, castExpr.getExpression(), lambdaBody);
			castExpr.setExpression(inner);
		} else {
			generatedJavaParser.addProblem("Failed to parse lambda expression! Please create an issue at https://github.com/javaparser/javaparser/issues");
		}
		return ret;
	}

	static ArrayCreationExpr juggleArrayCreation(TokenRange range, List<TokenRange> levelRanges, Type type, NodeList<Expression> dimensions,
			List<NodeList<AnnotationExpr>> arrayAnnotations, ArrayInitializerExpr arrayInitializerExpr) {
		NodeList<ArrayCreationLevel> levels = new NodeList<ArrayCreationLevel>();

		for (int i = 0; i < arrayAnnotations.size(); i++) {
			levels.add(new ArrayCreationLevel(levelRanges.get(i), dimensions.get(i), arrayAnnotations.get(i)));
		}
		return new ArrayCreationExpr(range, type, levels, arrayInitializerExpr);
	}

	static Type juggleArrayType(Type partialType, List<ArrayType.ArrayBracketPair> additionalBrackets) {
		Pair<Type, List<ArrayType.ArrayBracketPair>> partialParts = unwrapArrayTypes(partialType);
		Type elementType = partialParts.a;
		List<ArrayType.ArrayBracketPair> leftMostBrackets = partialParts.b;
		return wrapInArrayTypes(elementType, leftMostBrackets, additionalBrackets);
	}

	static TokenRange tokenRange(Token token) {
		JavaToken javaToken = ((CustomToken) token).javaToken;
		return new TokenRange(javaToken, javaToken);
	}

	static JavaToken nodeListBegin(NodeList<?> l) {
		if (l.isEmpty()) {
			return JavaToken.INVALID;
		}
		return l.get(0).getTokenRange().get().getBegin();
	}
}
