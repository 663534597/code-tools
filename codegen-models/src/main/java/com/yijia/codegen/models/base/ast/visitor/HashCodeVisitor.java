/*
 * Copyright (C) 2007-2010 Júlio Vilmar Gesser. Copyright (C) 2011, 2013-2016 The JavaParser Team. This file is part of JavaParser. JavaParser can be used either under the terms of
 * a) the GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. b) the terms of
 * the Apache License You should have received a copy of both licenses in LICENCE.LGPL and LICENCE.APACHE. Please refer to those files for details. JavaParser is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General
 * Public License for more details.
 */
package com.yijia.codegen.models.base.ast.visitor;

import javax.annotation.Generated;
import com.yijia.codegen.models.base.ast.ArrayCreationLevel;
import com.yijia.codegen.models.base.ast.CompilationUnit;
import com.yijia.codegen.models.base.ast.ImportDeclaration;
import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.NodeList;
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
import com.yijia.codegen.models.base.ast.comments.BlockComment;
import com.yijia.codegen.models.base.ast.comments.JavadocComment;
import com.yijia.codegen.models.base.ast.comments.LineComment;
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

/**
 * A visitor that calculates a deep hash code for a node by using the hash codes of all its properties, and the hash codes of all its child nodes (by visiting those too.)
 */
public class HashCodeVisitor implements GenericVisitor<Integer, Void> {

	private static final HashCodeVisitor SINGLETON = new HashCodeVisitor();

	private HashCodeVisitor() {
		// hide constructor
	}

	public static int hashCode(final Node node) {
		return node.accept(SINGLETON, null);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(AnnotationDeclaration n, Void arg) {
		return (n.getMembers().accept(this, arg)) * 31 + (n.getModifiers().hashCode()) * 31 + (n.getName().accept(this, arg)) * 31 + (n.getAnnotations().accept(this, arg)) * 31
				+ (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(AnnotationMemberDeclaration n, Void arg) {
		return (n.getDefaultValue().isPresent() ? n.getDefaultValue().get().accept(this, arg) : 0) * 31 + (n.getModifiers().hashCode()) * 31 + (n.getName().accept(this, arg)) * 31
				+ (n.getType().accept(this, arg)) * 31 + (n.getAnnotations().accept(this, arg)) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(ArrayAccessExpr n, Void arg) {
		return (n.getIndex().accept(this, arg)) * 31 + (n.getName().accept(this, arg)) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(ArrayCreationExpr n, Void arg) {
		return (n.getElementType().accept(this, arg)) * 31 + (n.getInitializer().isPresent() ? n.getInitializer().get().accept(this, arg) : 0) * 31
				+ (n.getLevels().accept(this, arg)) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(ArrayCreationLevel n, Void arg) {
		return (n.getAnnotations().accept(this, arg)) * 31 + (n.getDimension().isPresent() ? n.getDimension().get().accept(this, arg) : 0) * 31
				+ (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(ArrayInitializerExpr n, Void arg) {
		return (n.getValues().accept(this, arg)) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(ArrayType n, Void arg) {
		return (n.getComponentType().accept(this, arg)) * 31 + (n.getAnnotations().accept(this, arg)) * 31
				+ (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(AssertStmt n, Void arg) {
		return (n.getCheck().accept(this, arg)) * 31 + (n.getMessage().isPresent() ? n.getMessage().get().accept(this, arg) : 0) * 31
				+ (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(AssignExpr n, Void arg) {
		return (n.getOperator().hashCode()) * 31 + (n.getTarget().accept(this, arg)) * 31 + (n.getValue().accept(this, arg)) * 31
				+ (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(BinaryExpr n, Void arg) {
		return (n.getLeft().accept(this, arg)) * 31 + (n.getOperator().hashCode()) * 31 + (n.getRight().accept(this, arg)) * 31
				+ (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(BlockComment n, Void arg) {
		return (n.getContent().hashCode()) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(BlockStmt n, Void arg) {
		return (n.getStatements().accept(this, arg)) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(BooleanLiteralExpr n, Void arg) {
		return (n.getValue() ? 1 : 0) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(BreakStmt n, Void arg) {
		return (n.getLabel().isPresent() ? n.getLabel().get().accept(this, arg) : 0) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(CastExpr n, Void arg) {
		return (n.getExpression().accept(this, arg)) * 31 + (n.getType().accept(this, arg)) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(CatchClause n, Void arg) {
		return (n.getBody().accept(this, arg)) * 31 + (n.getParameter().accept(this, arg)) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(CharLiteralExpr n, Void arg) {
		return (n.getValue().hashCode()) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(ClassExpr n, Void arg) {
		return (n.getType().accept(this, arg)) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(ClassOrInterfaceDeclaration n, Void arg) {
		return (n.getExtendedTypes().accept(this, arg)) * 31 + (n.getImplementedTypes().accept(this, arg)) * 31 + (n.isInterface() ? 1 : 0) * 31
				+ (n.getTypeParameters().accept(this, arg)) * 31 + (n.getMembers().accept(this, arg)) * 31 + (n.getModifiers().hashCode()) * 31
				+ (n.getName().accept(this, arg)) * 31 + (n.getAnnotations().accept(this, arg)) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(ClassOrInterfaceType n, Void arg) {
		return (n.getName().accept(this, arg)) * 31 + (n.getScope().isPresent() ? n.getScope().get().accept(this, arg) : 0) * 31
				+ (n.getTypeArguments().isPresent() ? n.getTypeArguments().get().accept(this, arg) : 0) * 31 + (n.getAnnotations().accept(this, arg)) * 31
				+ (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(CompilationUnit n, Void arg) {
		return (n.getImports().accept(this, arg)) * 31 + (n.getModule().isPresent() ? n.getModule().get().accept(this, arg) : 0) * 31
				+ (n.getPackageDeclaration().isPresent() ? n.getPackageDeclaration().get().accept(this, arg) : 0) * 31 + (n.getTypes().accept(this, arg)) * 31
				+ (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(ConditionalExpr n, Void arg) {
		return (n.getCondition().accept(this, arg)) * 31 + (n.getElseExpr().accept(this, arg)) * 31 + (n.getThenExpr().accept(this, arg)) * 31
				+ (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(ConstructorDeclaration n, Void arg) {
		return (n.getBody().accept(this, arg)) * 31 + (n.getModifiers().hashCode()) * 31 + (n.getName().accept(this, arg)) * 31 + (n.getParameters().accept(this, arg)) * 31
				+ (n.getThrownExceptions().accept(this, arg)) * 31 + (n.getTypeParameters().accept(this, arg)) * 31 + (n.getAnnotations().accept(this, arg)) * 31
				+ (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(ContinueStmt n, Void arg) {
		return (n.getLabel().isPresent() ? n.getLabel().get().accept(this, arg) : 0) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(DoStmt n, Void arg) {
		return (n.getBody().accept(this, arg)) * 31 + (n.getCondition().accept(this, arg)) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(DoubleLiteralExpr n, Void arg) {
		return (n.getValue().hashCode()) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(EmptyStmt n, Void arg) {
		return (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(EnclosedExpr n, Void arg) {
		return (n.getInner().isPresent() ? n.getInner().get().accept(this, arg) : 0) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(EnumConstantDeclaration n, Void arg) {
		return (n.getArguments().accept(this, arg)) * 31 + (n.getClassBody().accept(this, arg)) * 31 + (n.getName().accept(this, arg)) * 31
				+ (n.getAnnotations().accept(this, arg)) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(EnumDeclaration n, Void arg) {
		return (n.getEntries().accept(this, arg)) * 31 + (n.getImplementedTypes().accept(this, arg)) * 31 + (n.getMembers().accept(this, arg)) * 31
				+ (n.getModifiers().hashCode()) * 31 + (n.getName().accept(this, arg)) * 31 + (n.getAnnotations().accept(this, arg)) * 31
				+ (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(ExplicitConstructorInvocationStmt n, Void arg) {
		return (n.getArguments().accept(this, arg)) * 31 + (n.getExpression().isPresent() ? n.getExpression().get().accept(this, arg) : 0) * 31 + (n.isThis() ? 1 : 0) * 31
				+ (n.getTypeArguments().isPresent() ? n.getTypeArguments().get().accept(this, arg) : 0) * 31
				+ (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(ExpressionStmt n, Void arg) {
		return (n.getExpression().accept(this, arg)) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(FieldAccessExpr n, Void arg) {
		return (n.getName().accept(this, arg)) * 31 + (n.getScope().accept(this, arg)) * 31
				+ (n.getTypeArguments().isPresent() ? n.getTypeArguments().get().accept(this, arg) : 0) * 31
				+ (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(FieldDeclaration n, Void arg) {
		return (n.getModifiers().hashCode()) * 31 + (n.getVariables().accept(this, arg)) * 31 + (n.getAnnotations().accept(this, arg)) * 31
				+ (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(ForStmt n, Void arg) {
		return (n.getBody().accept(this, arg)) * 31 + (n.getCompare().isPresent() ? n.getCompare().get().accept(this, arg) : 0) * 31
				+ (n.getInitialization().accept(this, arg)) * 31 + (n.getUpdate().accept(this, arg)) * 31
				+ (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(ForeachStmt n, Void arg) {
		return (n.getBody().accept(this, arg)) * 31 + (n.getIterable().accept(this, arg)) * 31 + (n.getVariable().accept(this, arg)) * 31
				+ (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(IfStmt n, Void arg) {
		return (n.getCondition().accept(this, arg)) * 31 + (n.getElseStmt().isPresent() ? n.getElseStmt().get().accept(this, arg) : 0) * 31
				+ (n.getThenStmt().accept(this, arg)) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(ImportDeclaration n, Void arg) {
		return (n.isAsterisk() ? 1 : 0) * 31 + (n.isStatic() ? 1 : 0) * 31 + (n.getName().accept(this, arg)) * 31
				+ (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(InitializerDeclaration n, Void arg) {
		return (n.getBody().accept(this, arg)) * 31 + (n.isStatic() ? 1 : 0) * 31 + (n.getAnnotations().accept(this, arg)) * 31
				+ (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(InstanceOfExpr n, Void arg) {
		return (n.getExpression().accept(this, arg)) * 31 + (n.getType().accept(this, arg)) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(IntegerLiteralExpr n, Void arg) {
		return (n.getValue().hashCode()) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(IntersectionType n, Void arg) {
		return (n.getElements().accept(this, arg)) * 31 + (n.getAnnotations().accept(this, arg)) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(JavadocComment n, Void arg) {
		return (n.getContent().hashCode()) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(LabeledStmt n, Void arg) {
		return (n.getLabel().accept(this, arg)) * 31 + (n.getStatement().accept(this, arg)) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(LambdaExpr n, Void arg) {
		return (n.getBody().accept(this, arg)) * 31 + (n.isEnclosingParameters() ? 1 : 0) * 31 + (n.getParameters().accept(this, arg)) * 31
				+ (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(LineComment n, Void arg) {
		return (n.getContent().hashCode()) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(LocalClassDeclarationStmt n, Void arg) {
		return (n.getClassDeclaration().accept(this, arg)) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(LongLiteralExpr n, Void arg) {
		return (n.getValue().hashCode()) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(MarkerAnnotationExpr n, Void arg) {
		return (n.getName().accept(this, arg)) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(MemberValuePair n, Void arg) {
		return (n.getName().accept(this, arg)) * 31 + (n.getValue().accept(this, arg)) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(MethodCallExpr n, Void arg) {
		return (n.getArguments().accept(this, arg)) * 31 + (n.getName().accept(this, arg)) * 31 + (n.getScope().isPresent() ? n.getScope().get().accept(this, arg) : 0) * 31
				+ (n.getTypeArguments().isPresent() ? n.getTypeArguments().get().accept(this, arg) : 0) * 31
				+ (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(MethodDeclaration n, Void arg) {
		return (n.getBody().isPresent() ? n.getBody().get().accept(this, arg) : 0) * 31 + (n.getType().accept(this, arg)) * 31 + (n.getModifiers().hashCode()) * 31
				+ (n.getName().accept(this, arg)) * 31 + (n.getParameters().accept(this, arg)) * 31 + (n.getThrownExceptions().accept(this, arg)) * 31
				+ (n.getTypeParameters().accept(this, arg)) * 31 + (n.getAnnotations().accept(this, arg)) * 31
				+ (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(MethodReferenceExpr n, Void arg) {
		return (n.getIdentifier().hashCode()) * 31 + (n.getScope().accept(this, arg)) * 31
				+ (n.getTypeArguments().isPresent() ? n.getTypeArguments().get().accept(this, arg) : 0) * 31
				+ (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(NameExpr n, Void arg) {
		return (n.getName().accept(this, arg)) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(Name n, Void arg) {
		return (n.getAnnotations().accept(this, arg)) * 31 + (n.getIdentifier().hashCode()) * 31
				+ (n.getQualifier().isPresent() ? n.getQualifier().get().accept(this, arg) : 0) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	public Integer visit(NodeList n, Void arg) {
		int result = 0;
		for (Object node : n) {
			result += 31 * ((Visitable) node).accept(this, arg);
		}
		return result;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(NormalAnnotationExpr n, Void arg) {
		return (n.getPairs().accept(this, arg)) * 31 + (n.getName().accept(this, arg)) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(NullLiteralExpr n, Void arg) {
		return (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(ObjectCreationExpr n, Void arg) {
		return (n.getAnonymousClassBody().isPresent() ? n.getAnonymousClassBody().get().accept(this, arg) : 0) * 31 + (n.getArguments().accept(this, arg)) * 31
				+ (n.getScope().isPresent() ? n.getScope().get().accept(this, arg) : 0) * 31 + (n.getType().accept(this, arg)) * 31
				+ (n.getTypeArguments().isPresent() ? n.getTypeArguments().get().accept(this, arg) : 0) * 31
				+ (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(PackageDeclaration n, Void arg) {
		return (n.getAnnotations().accept(this, arg)) * 31 + (n.getName().accept(this, arg)) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(Parameter n, Void arg) {
		return (n.getAnnotations().accept(this, arg)) * 31 + (n.isVarArgs() ? 1 : 0) * 31 + (n.getModifiers().hashCode()) * 31 + (n.getName().accept(this, arg)) * 31
				+ (n.getType().accept(this, arg)) * 31 + (n.getVarArgsAnnotations().accept(this, arg)) * 31
				+ (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(PrimitiveType n, Void arg) {
		return (n.getType().hashCode()) * 31 + (n.getAnnotations().accept(this, arg)) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(ReturnStmt n, Void arg) {
		return (n.getExpression().isPresent() ? n.getExpression().get().accept(this, arg) : 0) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(SimpleName n, Void arg) {
		return (n.getIdentifier().hashCode()) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(SingleMemberAnnotationExpr n, Void arg) {
		return (n.getMemberValue().accept(this, arg)) * 31 + (n.getName().accept(this, arg)) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(StringLiteralExpr n, Void arg) {
		return (n.getValue().hashCode()) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(SuperExpr n, Void arg) {
		return (n.getClassExpr().isPresent() ? n.getClassExpr().get().accept(this, arg) : 0) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(SwitchEntryStmt n, Void arg) {
		return (n.getLabel().isPresent() ? n.getLabel().get().accept(this, arg) : 0) * 31 + (n.getStatements().accept(this, arg)) * 31
				+ (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(SwitchStmt n, Void arg) {
		return (n.getEntries().accept(this, arg)) * 31 + (n.getSelector().accept(this, arg)) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(SynchronizedStmt n, Void arg) {
		return (n.getBody().accept(this, arg)) * 31 + (n.getExpression().accept(this, arg)) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(ThisExpr n, Void arg) {
		return (n.getClassExpr().isPresent() ? n.getClassExpr().get().accept(this, arg) : 0) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(ThrowStmt n, Void arg) {
		return (n.getExpression().accept(this, arg)) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(TryStmt n, Void arg) {
		return (n.getCatchClauses().accept(this, arg)) * 31 + (n.getFinallyBlock().isPresent() ? n.getFinallyBlock().get().accept(this, arg) : 0) * 31
				+ (n.getResources().accept(this, arg)) * 31 + (n.getTryBlock().isPresent() ? n.getTryBlock().get().accept(this, arg) : 0) * 31
				+ (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(TypeExpr n, Void arg) {
		return (n.getType().accept(this, arg)) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(TypeParameter n, Void arg) {
		return (n.getName().accept(this, arg)) * 31 + (n.getTypeBound().accept(this, arg)) * 31 + (n.getAnnotations().accept(this, arg)) * 31
				+ (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(UnaryExpr n, Void arg) {
		return (n.getExpression().accept(this, arg)) * 31 + (n.getOperator().hashCode()) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(UnionType n, Void arg) {
		return (n.getElements().accept(this, arg)) * 31 + (n.getAnnotations().accept(this, arg)) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(UnknownType n, Void arg) {
		return (n.getAnnotations().accept(this, arg)) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(VariableDeclarationExpr n, Void arg) {
		return (n.getAnnotations().accept(this, arg)) * 31 + (n.getModifiers().hashCode()) * 31 + (n.getVariables().accept(this, arg)) * 31
				+ (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(VariableDeclarator n, Void arg) {
		return (n.getInitializer().isPresent() ? n.getInitializer().get().accept(this, arg) : 0) * 31 + (n.getName().accept(this, arg)) * 31 + (n.getType().accept(this, arg)) * 31
				+ (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(VoidType n, Void arg) {
		return (n.getAnnotations().accept(this, arg)) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(WhileStmt n, Void arg) {
		return (n.getBody().accept(this, arg)) * 31 + (n.getCondition().accept(this, arg)) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(WildcardType n, Void arg) {
		return (n.getExtendedType().isPresent() ? n.getExtendedType().get().accept(this, arg) : 0) * 31
				+ (n.getSuperType().isPresent() ? n.getSuperType().get().accept(this, arg) : 0) * 31 + (n.getAnnotations().accept(this, arg)) * 31
				+ (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(ModuleDeclaration n, Void arg) {
		return (n.getAnnotations().accept(this, arg)) * 31 + (n.isOpen() ? 1 : 0) * 31 + (n.getModuleStmts().accept(this, arg)) * 31 + (n.getName().accept(this, arg)) * 31
				+ (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(ModuleRequiresStmt n, Void arg) {
		return (n.getModifiers().hashCode()) * 31 + (n.getName().accept(this, arg)) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Override()
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(ModuleExportsStmt n, Void arg) {
		return (n.getModuleNames().accept(this, arg)) * 31 + (n.getName().accept(this, arg)) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Override()
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(ModuleProvidesStmt n, Void arg) {
		return (n.getType().accept(this, arg)) * 31 + (n.getWithTypes().accept(this, arg)) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Override()
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(ModuleUsesStmt n, Void arg) {
		return (n.getType().accept(this, arg)) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.HashCodeVisitorGenerator")
	public Integer visit(ModuleOpensStmt n, Void arg) {
		return (n.getModuleNames().accept(this, arg)) * 31 + (n.getName().accept(this, arg)) * 31 + (n.getComment().isPresent() ? n.getComment().get().accept(this, arg) : 0);
	}
}
