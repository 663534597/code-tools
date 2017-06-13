/*
 * Copyright (C) 2007-2010 Júlio Vilmar Gesser. Copyright (C) 2011, 2013-2016 The JavaParser Team. This file is part of JavaParser. JavaParser can be used either under the terms of
 * a) the GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. b) the terms of
 * the Apache License You should have received a copy of both licenses in LICENCE.LGPL and LICENCE.APACHE. Please refer to those files for details. JavaParser is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General
 * Public License for more details.
 */
package com.yijia.codegen.models.base.ast.visitor;

import java.util.Optional;
import javax.annotation.Generated;
import com.yijia.codegen.models.base.ast.ArrayCreationLevel;
import com.yijia.codegen.models.base.ast.CompilationUnit;
import com.yijia.codegen.models.base.ast.ImportDeclaration;
import com.yijia.codegen.models.base.ast.Node;
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
import com.yijia.codegen.models.base.ast.comments.BlockComment;
import com.yijia.codegen.models.base.ast.comments.Comment;
import com.yijia.codegen.models.base.ast.comments.JavadocComment;
import com.yijia.codegen.models.base.ast.comments.LineComment;
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
import com.yijia.codegen.models.base.ast.type.ArrayType;
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

/**
 * A visitor that clones (copies) a node and all its children.
 */
public class CloneVisitor implements GenericVisitor<Visitable, Object> {

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(CompilationUnit n, Object arg) {
		NodeList<ImportDeclaration> imports = cloneList(n.getImports(), arg);
		ModuleDeclaration module = cloneNode(n.getModule(), arg);
		PackageDeclaration packageDeclaration = cloneNode(n.getPackageDeclaration(), arg);
		NodeList<TypeDeclaration<?>> types = cloneList(n.getTypes(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		CompilationUnit r = new CompilationUnit(n.getTokenRange().orElse(null), packageDeclaration, imports, types, module);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(PackageDeclaration n, Object arg) {
		NodeList<AnnotationExpr> annotations = cloneList(n.getAnnotations(), arg);
		Name name = cloneNode(n.getName(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		PackageDeclaration r = new PackageDeclaration(n.getTokenRange().orElse(null), annotations, name);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(TypeParameter n, Object arg) {
		SimpleName name = cloneNode(n.getName(), arg);
		NodeList<ClassOrInterfaceType> typeBound = cloneList(n.getTypeBound(), arg);
		NodeList<AnnotationExpr> annotations = cloneList(n.getAnnotations(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		TypeParameter r = new TypeParameter(n.getTokenRange().orElse(null), name, typeBound, annotations);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(LineComment n, Object arg) {
		Comment comment = cloneNode(n.getComment(), arg);
		LineComment r = new LineComment(n.getTokenRange().orElse(null), n.getContent());
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(BlockComment n, Object arg) {
		Comment comment = cloneNode(n.getComment(), arg);
		BlockComment r = new BlockComment(n.getTokenRange().orElse(null), n.getContent());
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(ClassOrInterfaceDeclaration n, Object arg) {
		NodeList<ClassOrInterfaceType> extendedTypes = cloneList(n.getExtendedTypes(), arg);
		NodeList<ClassOrInterfaceType> implementedTypes = cloneList(n.getImplementedTypes(), arg);
		NodeList<TypeParameter> typeParameters = cloneList(n.getTypeParameters(), arg);
		NodeList<BodyDeclaration<?>> members = cloneList(n.getMembers(), arg);
		SimpleName name = cloneNode(n.getName(), arg);
		NodeList<AnnotationExpr> annotations = cloneList(n.getAnnotations(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		ClassOrInterfaceDeclaration r = new ClassOrInterfaceDeclaration(n.getTokenRange().orElse(null), n.getModifiers(), annotations, n.isInterface(), name, typeParameters,
				extendedTypes, implementedTypes, members);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(EnumDeclaration n, Object arg) {
		NodeList<EnumConstantDeclaration> entries = cloneList(n.getEntries(), arg);
		NodeList<ClassOrInterfaceType> implementedTypes = cloneList(n.getImplementedTypes(), arg);
		NodeList<BodyDeclaration<?>> members = cloneList(n.getMembers(), arg);
		SimpleName name = cloneNode(n.getName(), arg);
		NodeList<AnnotationExpr> annotations = cloneList(n.getAnnotations(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		EnumDeclaration r = new EnumDeclaration(n.getTokenRange().orElse(null), n.getModifiers(), annotations, name, implementedTypes, entries, members);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(EnumConstantDeclaration n, Object arg) {
		NodeList<Expression> arguments = cloneList(n.getArguments(), arg);
		NodeList<BodyDeclaration<?>> classBody = cloneList(n.getClassBody(), arg);
		SimpleName name = cloneNode(n.getName(), arg);
		NodeList<AnnotationExpr> annotations = cloneList(n.getAnnotations(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		EnumConstantDeclaration r = new EnumConstantDeclaration(n.getTokenRange().orElse(null), annotations, name, arguments, classBody);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(AnnotationDeclaration n, Object arg) {
		NodeList<BodyDeclaration<?>> members = cloneList(n.getMembers(), arg);
		SimpleName name = cloneNode(n.getName(), arg);
		NodeList<AnnotationExpr> annotations = cloneList(n.getAnnotations(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		AnnotationDeclaration r = new AnnotationDeclaration(n.getTokenRange().orElse(null), n.getModifiers(), annotations, name, members);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(AnnotationMemberDeclaration n, Object arg) {
		Expression defaultValue = cloneNode(n.getDefaultValue(), arg);
		SimpleName name = cloneNode(n.getName(), arg);
		Type type = cloneNode(n.getType(), arg);
		NodeList<AnnotationExpr> annotations = cloneList(n.getAnnotations(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		AnnotationMemberDeclaration r = new AnnotationMemberDeclaration(n.getTokenRange().orElse(null), n.getModifiers(), annotations, type, name, defaultValue);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(FieldDeclaration n, Object arg) {
		NodeList<VariableDeclarator> variables = cloneList(n.getVariables(), arg);
		NodeList<AnnotationExpr> annotations = cloneList(n.getAnnotations(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		FieldDeclaration r = new FieldDeclaration(n.getTokenRange().orElse(null), n.getModifiers(), annotations, variables);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(VariableDeclarator n, Object arg) {
		Expression initializer = cloneNode(n.getInitializer(), arg);
		SimpleName name = cloneNode(n.getName(), arg);
		Type type = cloneNode(n.getType(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		VariableDeclarator r = new VariableDeclarator(n.getTokenRange().orElse(null), type, name, initializer);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(ConstructorDeclaration n, Object arg) {
		BlockStmt body = cloneNode(n.getBody(), arg);
		SimpleName name = cloneNode(n.getName(), arg);
		NodeList<Parameter> parameters = cloneList(n.getParameters(), arg);
		NodeList<ReferenceType> thrownExceptions = cloneList(n.getThrownExceptions(), arg);
		NodeList<TypeParameter> typeParameters = cloneList(n.getTypeParameters(), arg);
		NodeList<AnnotationExpr> annotations = cloneList(n.getAnnotations(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		ConstructorDeclaration r = new ConstructorDeclaration(n.getTokenRange().orElse(null), n.getModifiers(), annotations, typeParameters, name, parameters, thrownExceptions,
				body);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(MethodDeclaration n, Object arg) {
		BlockStmt body = cloneNode(n.getBody(), arg);
		Type type = cloneNode(n.getType(), arg);
		SimpleName name = cloneNode(n.getName(), arg);
		NodeList<Parameter> parameters = cloneList(n.getParameters(), arg);
		NodeList<ReferenceType> thrownExceptions = cloneList(n.getThrownExceptions(), arg);
		NodeList<TypeParameter> typeParameters = cloneList(n.getTypeParameters(), arg);
		NodeList<AnnotationExpr> annotations = cloneList(n.getAnnotations(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		MethodDeclaration r = new MethodDeclaration(n.getTokenRange().orElse(null), n.getModifiers(), annotations, typeParameters, type, name, parameters, thrownExceptions, body);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(Parameter n, Object arg) {
		NodeList<AnnotationExpr> annotations = cloneList(n.getAnnotations(), arg);
		SimpleName name = cloneNode(n.getName(), arg);
		Type type = cloneNode(n.getType(), arg);
		NodeList<AnnotationExpr> varArgsAnnotations = cloneList(n.getVarArgsAnnotations(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		Parameter r = new Parameter(n.getTokenRange().orElse(null), n.getModifiers(), annotations, type, n.isVarArgs(), varArgsAnnotations, name);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(InitializerDeclaration n, Object arg) {
		BlockStmt body = cloneNode(n.getBody(), arg);
		NodeList<AnnotationExpr> annotations = cloneList(n.getAnnotations(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		InitializerDeclaration r = new InitializerDeclaration(n.getTokenRange().orElse(null), n.isStatic(), body);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(JavadocComment n, Object arg) {
		Comment comment = cloneNode(n.getComment(), arg);
		JavadocComment r = new JavadocComment(n.getTokenRange().orElse(null), n.getContent());
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(ClassOrInterfaceType n, Object arg) {
		SimpleName name = cloneNode(n.getName(), arg);
		ClassOrInterfaceType scope = cloneNode(n.getScope(), arg);
		NodeList<Type> typeArguments = cloneList(n.getTypeArguments().orElse(null), arg);
		NodeList<AnnotationExpr> annotations = cloneList(n.getAnnotations(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		ClassOrInterfaceType r = new ClassOrInterfaceType(n.getTokenRange().orElse(null), scope, name, typeArguments, annotations);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(PrimitiveType n, Object arg) {
		NodeList<AnnotationExpr> annotations = cloneList(n.getAnnotations(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		PrimitiveType r = new PrimitiveType(n.getTokenRange().orElse(null), n.getType());
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(ArrayType n, Object arg) {
		Type componentType = cloneNode(n.getComponentType(), arg);
		NodeList<AnnotationExpr> annotations = cloneList(n.getAnnotations(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		ArrayType r = new ArrayType(n.getTokenRange().orElse(null), componentType, annotations);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(ArrayCreationLevel n, Object arg) {
		NodeList<AnnotationExpr> annotations = cloneList(n.getAnnotations(), arg);
		Expression dimension = cloneNode(n.getDimension(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		ArrayCreationLevel r = new ArrayCreationLevel(n.getTokenRange().orElse(null), dimension, annotations);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(IntersectionType n, Object arg) {
		NodeList<ReferenceType> elements = cloneList(n.getElements(), arg);
		NodeList<AnnotationExpr> annotations = cloneList(n.getAnnotations(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		IntersectionType r = new IntersectionType(n.getTokenRange().orElse(null), elements);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(UnionType n, Object arg) {
		NodeList<ReferenceType> elements = cloneList(n.getElements(), arg);
		NodeList<AnnotationExpr> annotations = cloneList(n.getAnnotations(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		UnionType r = new UnionType(n.getTokenRange().orElse(null), elements);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(VoidType n, Object arg) {
		NodeList<AnnotationExpr> annotations = cloneList(n.getAnnotations(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		VoidType r = new VoidType(n.getTokenRange().orElse(null));
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(WildcardType n, Object arg) {
		ReferenceType extendedType = cloneNode(n.getExtendedType(), arg);
		ReferenceType superType = cloneNode(n.getSuperType(), arg);
		NodeList<AnnotationExpr> annotations = cloneList(n.getAnnotations(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		WildcardType r = new WildcardType(n.getTokenRange().orElse(null), extendedType, superType);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(UnknownType n, Object arg) {
		NodeList<AnnotationExpr> annotations = cloneList(n.getAnnotations(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		UnknownType r = new UnknownType(n.getTokenRange().orElse(null));
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(ArrayAccessExpr n, Object arg) {
		Expression index = cloneNode(n.getIndex(), arg);
		Expression name = cloneNode(n.getName(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		ArrayAccessExpr r = new ArrayAccessExpr(n.getTokenRange().orElse(null), name, index);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(ArrayCreationExpr n, Object arg) {
		Type elementType = cloneNode(n.getElementType(), arg);
		ArrayInitializerExpr initializer = cloneNode(n.getInitializer(), arg);
		NodeList<ArrayCreationLevel> levels = cloneList(n.getLevels(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		ArrayCreationExpr r = new ArrayCreationExpr(n.getTokenRange().orElse(null), elementType, levels, initializer);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(ArrayInitializerExpr n, Object arg) {
		NodeList<Expression> values = cloneList(n.getValues(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		ArrayInitializerExpr r = new ArrayInitializerExpr(n.getTokenRange().orElse(null), values);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(AssignExpr n, Object arg) {
		Expression target = cloneNode(n.getTarget(), arg);
		Expression value = cloneNode(n.getValue(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		AssignExpr r = new AssignExpr(n.getTokenRange().orElse(null), target, value, n.getOperator());
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(BinaryExpr n, Object arg) {
		Expression left = cloneNode(n.getLeft(), arg);
		Expression right = cloneNode(n.getRight(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		BinaryExpr r = new BinaryExpr(n.getTokenRange().orElse(null), left, right, n.getOperator());
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(CastExpr n, Object arg) {
		Expression expression = cloneNode(n.getExpression(), arg);
		Type type = cloneNode(n.getType(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		CastExpr r = new CastExpr(n.getTokenRange().orElse(null), type, expression);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(ClassExpr n, Object arg) {
		Type type = cloneNode(n.getType(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		ClassExpr r = new ClassExpr(n.getTokenRange().orElse(null), type);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(ConditionalExpr n, Object arg) {
		Expression condition = cloneNode(n.getCondition(), arg);
		Expression elseExpr = cloneNode(n.getElseExpr(), arg);
		Expression thenExpr = cloneNode(n.getThenExpr(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		ConditionalExpr r = new ConditionalExpr(n.getTokenRange().orElse(null), condition, thenExpr, elseExpr);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(EnclosedExpr n, Object arg) {
		Expression inner = cloneNode(n.getInner(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		EnclosedExpr r = new EnclosedExpr(n.getTokenRange().orElse(null), inner);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(FieldAccessExpr n, Object arg) {
		SimpleName name = cloneNode(n.getName(), arg);
		Expression scope = cloneNode(n.getScope(), arg);
		NodeList<Type> typeArguments = cloneList(n.getTypeArguments().orElse(null), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		FieldAccessExpr r = new FieldAccessExpr(n.getTokenRange().orElse(null), scope, typeArguments, name);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(InstanceOfExpr n, Object arg) {
		Expression expression = cloneNode(n.getExpression(), arg);
		ReferenceType type = cloneNode(n.getType(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		InstanceOfExpr r = new InstanceOfExpr(n.getTokenRange().orElse(null), expression, type);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(StringLiteralExpr n, Object arg) {
		Comment comment = cloneNode(n.getComment(), arg);
		StringLiteralExpr r = new StringLiteralExpr(n.getTokenRange().orElse(null), n.getValue());
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(IntegerLiteralExpr n, Object arg) {
		Comment comment = cloneNode(n.getComment(), arg);
		IntegerLiteralExpr r = new IntegerLiteralExpr(n.getTokenRange().orElse(null), n.getValue());
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(LongLiteralExpr n, Object arg) {
		Comment comment = cloneNode(n.getComment(), arg);
		LongLiteralExpr r = new LongLiteralExpr(n.getTokenRange().orElse(null), n.getValue());
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(CharLiteralExpr n, Object arg) {
		Comment comment = cloneNode(n.getComment(), arg);
		CharLiteralExpr r = new CharLiteralExpr(n.getTokenRange().orElse(null), n.getValue());
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(DoubleLiteralExpr n, Object arg) {
		Comment comment = cloneNode(n.getComment(), arg);
		DoubleLiteralExpr r = new DoubleLiteralExpr(n.getTokenRange().orElse(null), n.getValue());
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(BooleanLiteralExpr n, Object arg) {
		Comment comment = cloneNode(n.getComment(), arg);
		BooleanLiteralExpr r = new BooleanLiteralExpr(n.getTokenRange().orElse(null), n.getValue());
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(NullLiteralExpr n, Object arg) {
		Comment comment = cloneNode(n.getComment(), arg);
		NullLiteralExpr r = new NullLiteralExpr(n.getTokenRange().orElse(null));
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(MethodCallExpr n, Object arg) {
		NodeList<Expression> arguments = cloneList(n.getArguments(), arg);
		SimpleName name = cloneNode(n.getName(), arg);
		Expression scope = cloneNode(n.getScope(), arg);
		NodeList<Type> typeArguments = cloneList(n.getTypeArguments().orElse(null), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		MethodCallExpr r = new MethodCallExpr(n.getTokenRange().orElse(null), scope, typeArguments, name, arguments);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(NameExpr n, Object arg) {
		SimpleName name = cloneNode(n.getName(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		NameExpr r = new NameExpr(n.getTokenRange().orElse(null), name);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(ObjectCreationExpr n, Object arg) {
		NodeList<BodyDeclaration<?>> anonymousClassBody = cloneList(n.getAnonymousClassBody().orElse(null), arg);
		NodeList<Expression> arguments = cloneList(n.getArguments(), arg);
		Expression scope = cloneNode(n.getScope(), arg);
		ClassOrInterfaceType type = cloneNode(n.getType(), arg);
		NodeList<Type> typeArguments = cloneList(n.getTypeArguments().orElse(null), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		ObjectCreationExpr r = new ObjectCreationExpr(n.getTokenRange().orElse(null), scope, type, typeArguments, arguments, anonymousClassBody);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(Name n, Object arg) {
		NodeList<AnnotationExpr> annotations = cloneList(n.getAnnotations(), arg);
		Name qualifier = cloneNode(n.getQualifier(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		Name r = new Name(n.getTokenRange().orElse(null), qualifier, n.getIdentifier(), annotations);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(SimpleName n, Object arg) {
		Comment comment = cloneNode(n.getComment(), arg);
		SimpleName r = new SimpleName(n.getTokenRange().orElse(null), n.getIdentifier());
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(ThisExpr n, Object arg) {
		Expression classExpr = cloneNode(n.getClassExpr(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		ThisExpr r = new ThisExpr(n.getTokenRange().orElse(null), classExpr);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(SuperExpr n, Object arg) {
		Expression classExpr = cloneNode(n.getClassExpr(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		SuperExpr r = new SuperExpr(n.getTokenRange().orElse(null), classExpr);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(UnaryExpr n, Object arg) {
		Expression expression = cloneNode(n.getExpression(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		UnaryExpr r = new UnaryExpr(n.getTokenRange().orElse(null), expression, n.getOperator());
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(VariableDeclarationExpr n, Object arg) {
		NodeList<AnnotationExpr> annotations = cloneList(n.getAnnotations(), arg);
		NodeList<VariableDeclarator> variables = cloneList(n.getVariables(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		VariableDeclarationExpr r = new VariableDeclarationExpr(n.getTokenRange().orElse(null), n.getModifiers(), annotations, variables);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(MarkerAnnotationExpr n, Object arg) {
		Name name = cloneNode(n.getName(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		MarkerAnnotationExpr r = new MarkerAnnotationExpr(n.getTokenRange().orElse(null), name);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(SingleMemberAnnotationExpr n, Object arg) {
		Expression memberValue = cloneNode(n.getMemberValue(), arg);
		Name name = cloneNode(n.getName(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		SingleMemberAnnotationExpr r = new SingleMemberAnnotationExpr(n.getTokenRange().orElse(null), name, memberValue);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(NormalAnnotationExpr n, Object arg) {
		NodeList<MemberValuePair> pairs = cloneList(n.getPairs(), arg);
		Name name = cloneNode(n.getName(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		NormalAnnotationExpr r = new NormalAnnotationExpr(n.getTokenRange().orElse(null), name, pairs);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(MemberValuePair n, Object arg) {
		SimpleName name = cloneNode(n.getName(), arg);
		Expression value = cloneNode(n.getValue(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		MemberValuePair r = new MemberValuePair(n.getTokenRange().orElse(null), name, value);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(ExplicitConstructorInvocationStmt n, Object arg) {
		NodeList<Expression> arguments = cloneList(n.getArguments(), arg);
		Expression expression = cloneNode(n.getExpression(), arg);
		NodeList<Type> typeArguments = cloneList(n.getTypeArguments().orElse(null), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		ExplicitConstructorInvocationStmt r = new ExplicitConstructorInvocationStmt(n.getTokenRange().orElse(null), typeArguments, n.isThis(), expression, arguments);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(LocalClassDeclarationStmt n, Object arg) {
		ClassOrInterfaceDeclaration classDeclaration = cloneNode(n.getClassDeclaration(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		LocalClassDeclarationStmt r = new LocalClassDeclarationStmt(n.getTokenRange().orElse(null), classDeclaration);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(AssertStmt n, Object arg) {
		Expression check = cloneNode(n.getCheck(), arg);
		Expression message = cloneNode(n.getMessage(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		AssertStmt r = new AssertStmt(n.getTokenRange().orElse(null), check, message);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(BlockStmt n, Object arg) {
		NodeList<Statement> statements = cloneList(n.getStatements(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		BlockStmt r = new BlockStmt(n.getTokenRange().orElse(null), statements);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(LabeledStmt n, Object arg) {
		SimpleName label = cloneNode(n.getLabel(), arg);
		Statement statement = cloneNode(n.getStatement(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		LabeledStmt r = new LabeledStmt(n.getTokenRange().orElse(null), label, statement);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(EmptyStmt n, Object arg) {
		Comment comment = cloneNode(n.getComment(), arg);
		EmptyStmt r = new EmptyStmt(n.getTokenRange().orElse(null));
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(ExpressionStmt n, Object arg) {
		Expression expression = cloneNode(n.getExpression(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		ExpressionStmt r = new ExpressionStmt(n.getTokenRange().orElse(null), expression);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(SwitchStmt n, Object arg) {
		NodeList<SwitchEntryStmt> entries = cloneList(n.getEntries(), arg);
		Expression selector = cloneNode(n.getSelector(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		SwitchStmt r = new SwitchStmt(n.getTokenRange().orElse(null), selector, entries);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(SwitchEntryStmt n, Object arg) {
		Expression label = cloneNode(n.getLabel(), arg);
		NodeList<Statement> statements = cloneList(n.getStatements(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		SwitchEntryStmt r = new SwitchEntryStmt(n.getTokenRange().orElse(null), label, statements);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(BreakStmt n, Object arg) {
		SimpleName label = cloneNode(n.getLabel(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		BreakStmt r = new BreakStmt(n.getTokenRange().orElse(null), label);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(ReturnStmt n, Object arg) {
		Expression expression = cloneNode(n.getExpression(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		ReturnStmt r = new ReturnStmt(n.getTokenRange().orElse(null), expression);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(IfStmt n, Object arg) {
		Expression condition = cloneNode(n.getCondition(), arg);
		Statement elseStmt = cloneNode(n.getElseStmt(), arg);
		Statement thenStmt = cloneNode(n.getThenStmt(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		IfStmt r = new IfStmt(n.getTokenRange().orElse(null), condition, thenStmt, elseStmt);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(WhileStmt n, Object arg) {
		Statement body = cloneNode(n.getBody(), arg);
		Expression condition = cloneNode(n.getCondition(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		WhileStmt r = new WhileStmt(n.getTokenRange().orElse(null), condition, body);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(ContinueStmt n, Object arg) {
		SimpleName label = cloneNode(n.getLabel(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		ContinueStmt r = new ContinueStmt(n.getTokenRange().orElse(null), label);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(DoStmt n, Object arg) {
		Statement body = cloneNode(n.getBody(), arg);
		Expression condition = cloneNode(n.getCondition(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		DoStmt r = new DoStmt(n.getTokenRange().orElse(null), body, condition);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(ForeachStmt n, Object arg) {
		Statement body = cloneNode(n.getBody(), arg);
		Expression iterable = cloneNode(n.getIterable(), arg);
		VariableDeclarationExpr variable = cloneNode(n.getVariable(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		ForeachStmt r = new ForeachStmt(n.getTokenRange().orElse(null), variable, iterable, body);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(ForStmt n, Object arg) {
		Statement body = cloneNode(n.getBody(), arg);
		Expression compare = cloneNode(n.getCompare(), arg);
		NodeList<Expression> initialization = cloneList(n.getInitialization(), arg);
		NodeList<Expression> update = cloneList(n.getUpdate(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		ForStmt r = new ForStmt(n.getTokenRange().orElse(null), initialization, compare, update, body);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(ThrowStmt n, Object arg) {
		Expression expression = cloneNode(n.getExpression(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		ThrowStmt r = new ThrowStmt(n.getTokenRange().orElse(null), expression);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(SynchronizedStmt n, Object arg) {
		BlockStmt body = cloneNode(n.getBody(), arg);
		Expression expression = cloneNode(n.getExpression(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		SynchronizedStmt r = new SynchronizedStmt(n.getTokenRange().orElse(null), expression, body);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(TryStmt n, Object arg) {
		NodeList<CatchClause> catchClauses = cloneList(n.getCatchClauses(), arg);
		BlockStmt finallyBlock = cloneNode(n.getFinallyBlock(), arg);
		NodeList<VariableDeclarationExpr> resources = cloneList(n.getResources(), arg);
		BlockStmt tryBlock = cloneNode(n.getTryBlock(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		TryStmt r = new TryStmt(n.getTokenRange().orElse(null), resources, tryBlock, catchClauses, finallyBlock);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(CatchClause n, Object arg) {
		BlockStmt body = cloneNode(n.getBody(), arg);
		Parameter parameter = cloneNode(n.getParameter(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		CatchClause r = new CatchClause(n.getTokenRange().orElse(null), parameter, body);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(LambdaExpr n, Object arg) {
		Statement body = cloneNode(n.getBody(), arg);
		NodeList<Parameter> parameters = cloneList(n.getParameters(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		LambdaExpr r = new LambdaExpr(n.getTokenRange().orElse(null), parameters, body, n.isEnclosingParameters());
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(MethodReferenceExpr n, Object arg) {
		Expression scope = cloneNode(n.getScope(), arg);
		NodeList<Type> typeArguments = cloneList(n.getTypeArguments().orElse(null), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		MethodReferenceExpr r = new MethodReferenceExpr(n.getTokenRange().orElse(null), scope, typeArguments, n.getIdentifier());
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(TypeExpr n, Object arg) {
		Type type = cloneNode(n.getType(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		TypeExpr r = new TypeExpr(n.getTokenRange().orElse(null), type);
		r.setComment(comment);
		return r;
	}

	@Override
	public Visitable visit(NodeList n, Object arg) {
		NodeList<Node> newNodes = new NodeList<>();
		for (Object node : n) {
			Node resultNode = (Node) ((Node) node).accept(this, arg);
			if (resultNode != null) {
				newNodes.add(resultNode);
			}
		}
		return newNodes;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Node visit(ImportDeclaration n, Object arg) {
		Name name = cloneNode(n.getName(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		ImportDeclaration r = new ImportDeclaration(n.getTokenRange().orElse(null), name, n.isStatic(), n.isAsterisk());
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(ModuleDeclaration n, Object arg) {
		NodeList<AnnotationExpr> annotations = cloneList(n.getAnnotations(), arg);
		NodeList<ModuleStmt> moduleStmts = cloneList(n.getModuleStmts(), arg);
		Name name = cloneNode(n.getName(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		ModuleDeclaration r = new ModuleDeclaration(n.getTokenRange().orElse(null), annotations, name, n.isOpen(), moduleStmts);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(ModuleRequiresStmt n, Object arg) {
		Name name = cloneNode(n.getName(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		ModuleRequiresStmt r = new ModuleRequiresStmt(n.getTokenRange().orElse(null), n.getModifiers(), name);
		r.setComment(comment);
		return r;
	}

	@SuppressWarnings("unchecked")
	protected <T extends Node> T cloneNode(Optional<T> node, Object arg) {
		if (!node.isPresent()) {
			return null;
		}
		Node r = (Node) node.get().accept(this, arg);
		if (r == null) {
			return null;
		}
		return (T) r;
	}

	@SuppressWarnings("unchecked")
	protected <T extends Node> T cloneNode(T node, Object arg) {
		if (node == null) {
			return null;
		}
		Node r = (Node) node.accept(this, arg);
		if (r == null) {
			return null;
		}
		return (T) r;
	}

	private <N extends Node> NodeList<N> cloneList(NodeList<N> list, Object arg) {
		if (list == null) {
			return null;
		}
		return (NodeList<N>) list.accept(this, arg);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(ModuleExportsStmt n, Object arg) {
		NodeList<Name> moduleNames = cloneList(n.getModuleNames(), arg);
		Name name = cloneNode(n.getName(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		ModuleExportsStmt r = new ModuleExportsStmt(n.getTokenRange().orElse(null), name, moduleNames);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(ModuleProvidesStmt n, Object arg) {
		Type type = cloneNode(n.getType(), arg);
		NodeList<Type> withTypes = cloneList(n.getWithTypes(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		ModuleProvidesStmt r = new ModuleProvidesStmt(n.getTokenRange().orElse(null), type, withTypes);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(ModuleUsesStmt n, Object arg) {
		Type type = cloneNode(n.getType(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		ModuleUsesStmt r = new ModuleUsesStmt(n.getTokenRange().orElse(null), type);
		r.setComment(comment);
		return r;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.CloneVisitorGenerator")
	public Visitable visit(ModuleOpensStmt n, Object arg) {
		NodeList<Name> moduleNames = cloneList(n.getModuleNames(), arg);
		Name name = cloneNode(n.getName(), arg);
		Comment comment = cloneNode(n.getComment(), arg);
		ModuleOpensStmt r = new ModuleOpensStmt(n.getTokenRange().orElse(null), name, moduleNames);
		r.setComment(comment);
		return r;
	}
}