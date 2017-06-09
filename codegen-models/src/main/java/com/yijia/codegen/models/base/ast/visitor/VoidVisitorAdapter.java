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
 * A visitor that returns nothing, and has a default implementation for all its visit methods that simply visit their children in an unspecified order.
 * @author Julio Vilmar Gesser
 */
public abstract class VoidVisitorAdapter<A> implements VoidVisitor<A> {

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final AnnotationDeclaration n, final A arg) {
		n.getMembers().forEach(p -> p.accept(this, arg));
		n.getName().accept(this, arg);
		n.getAnnotations().forEach(p -> p.accept(this, arg));
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final AnnotationMemberDeclaration n, final A arg) {
		n.getDefaultValue().ifPresent(l -> l.accept(this, arg));
		n.getName().accept(this, arg);
		n.getType().accept(this, arg);
		n.getAnnotations().forEach(p -> p.accept(this, arg));
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final ArrayAccessExpr n, final A arg) {
		n.getIndex().accept(this, arg);
		n.getName().accept(this, arg);
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final ArrayCreationExpr n, final A arg) {
		n.getElementType().accept(this, arg);
		n.getInitializer().ifPresent(l -> l.accept(this, arg));
		n.getLevels().forEach(p -> p.accept(this, arg));
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final ArrayInitializerExpr n, final A arg) {
		n.getValues().forEach(p -> p.accept(this, arg));
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final AssertStmt n, final A arg) {
		n.getCheck().accept(this, arg);
		n.getMessage().ifPresent(l -> l.accept(this, arg));
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final AssignExpr n, final A arg) {
		n.getTarget().accept(this, arg);
		n.getValue().accept(this, arg);
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final BinaryExpr n, final A arg) {
		n.getLeft().accept(this, arg);
		n.getRight().accept(this, arg);
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final BlockComment n, final A arg) {
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final BlockStmt n, final A arg) {
		n.getStatements().forEach(p -> p.accept(this, arg));
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final BooleanLiteralExpr n, final A arg) {
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final BreakStmt n, final A arg) {
		n.getLabel().ifPresent(l -> l.accept(this, arg));
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final CastExpr n, final A arg) {
		n.getExpression().accept(this, arg);
		n.getType().accept(this, arg);
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final CatchClause n, final A arg) {
		n.getBody().accept(this, arg);
		n.getParameter().accept(this, arg);
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final CharLiteralExpr n, final A arg) {
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final ClassExpr n, final A arg) {
		n.getType().accept(this, arg);
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final ClassOrInterfaceDeclaration n, final A arg) {
		n.getExtendedTypes().forEach(p -> p.accept(this, arg));
		n.getImplementedTypes().forEach(p -> p.accept(this, arg));
		n.getTypeParameters().forEach(p -> p.accept(this, arg));
		n.getMembers().forEach(p -> p.accept(this, arg));
		n.getName().accept(this, arg);
		n.getAnnotations().forEach(p -> p.accept(this, arg));
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final ClassOrInterfaceType n, final A arg) {
		n.getName().accept(this, arg);
		n.getScope().ifPresent(l -> l.accept(this, arg));
		n.getTypeArguments().ifPresent(l -> l.forEach(v -> v.accept(this, arg)));
		n.getAnnotations().forEach(p -> p.accept(this, arg));
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final CompilationUnit n, final A arg) {
		n.getImports().forEach(p -> p.accept(this, arg));
		n.getModule().ifPresent(l -> l.accept(this, arg));
		n.getPackageDeclaration().ifPresent(l -> l.accept(this, arg));
		n.getTypes().forEach(p -> p.accept(this, arg));
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final ConditionalExpr n, final A arg) {
		n.getCondition().accept(this, arg);
		n.getElseExpr().accept(this, arg);
		n.getThenExpr().accept(this, arg);
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final ConstructorDeclaration n, final A arg) {
		n.getBody().accept(this, arg);
		n.getName().accept(this, arg);
		n.getParameters().forEach(p -> p.accept(this, arg));
		n.getThrownExceptions().forEach(p -> p.accept(this, arg));
		n.getTypeParameters().forEach(p -> p.accept(this, arg));
		n.getAnnotations().forEach(p -> p.accept(this, arg));
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final ContinueStmt n, final A arg) {
		n.getLabel().ifPresent(l -> l.accept(this, arg));
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final DoStmt n, final A arg) {
		n.getBody().accept(this, arg);
		n.getCondition().accept(this, arg);
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final DoubleLiteralExpr n, final A arg) {
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final EmptyStmt n, final A arg) {
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final EnclosedExpr n, final A arg) {
		n.getInner().ifPresent(l -> l.accept(this, arg));
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final EnumConstantDeclaration n, final A arg) {
		n.getArguments().forEach(p -> p.accept(this, arg));
		n.getClassBody().forEach(p -> p.accept(this, arg));
		n.getName().accept(this, arg);
		n.getAnnotations().forEach(p -> p.accept(this, arg));
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final EnumDeclaration n, final A arg) {
		n.getEntries().forEach(p -> p.accept(this, arg));
		n.getImplementedTypes().forEach(p -> p.accept(this, arg));
		n.getMembers().forEach(p -> p.accept(this, arg));
		n.getName().accept(this, arg);
		n.getAnnotations().forEach(p -> p.accept(this, arg));
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final ExplicitConstructorInvocationStmt n, final A arg) {
		n.getArguments().forEach(p -> p.accept(this, arg));
		n.getExpression().ifPresent(l -> l.accept(this, arg));
		n.getTypeArguments().ifPresent(l -> l.forEach(v -> v.accept(this, arg)));
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final ExpressionStmt n, final A arg) {
		n.getExpression().accept(this, arg);
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final FieldAccessExpr n, final A arg) {
		n.getName().accept(this, arg);
		n.getScope().accept(this, arg);
		n.getTypeArguments().ifPresent(l -> l.forEach(v -> v.accept(this, arg)));
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final FieldDeclaration n, final A arg) {
		n.getVariables().forEach(p -> p.accept(this, arg));
		n.getAnnotations().forEach(p -> p.accept(this, arg));
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final ForeachStmt n, final A arg) {
		n.getBody().accept(this, arg);
		n.getIterable().accept(this, arg);
		n.getVariable().accept(this, arg);
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final ForStmt n, final A arg) {
		n.getBody().accept(this, arg);
		n.getCompare().ifPresent(l -> l.accept(this, arg));
		n.getInitialization().forEach(p -> p.accept(this, arg));
		n.getUpdate().forEach(p -> p.accept(this, arg));
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final IfStmt n, final A arg) {
		n.getCondition().accept(this, arg);
		n.getElseStmt().ifPresent(l -> l.accept(this, arg));
		n.getThenStmt().accept(this, arg);
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final InitializerDeclaration n, final A arg) {
		n.getBody().accept(this, arg);
		n.getAnnotations().forEach(p -> p.accept(this, arg));
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final InstanceOfExpr n, final A arg) {
		n.getExpression().accept(this, arg);
		n.getType().accept(this, arg);
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final IntegerLiteralExpr n, final A arg) {
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final JavadocComment n, final A arg) {
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final LabeledStmt n, final A arg) {
		n.getLabel().accept(this, arg);
		n.getStatement().accept(this, arg);
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final LineComment n, final A arg) {
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final LongLiteralExpr n, final A arg) {
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final MarkerAnnotationExpr n, final A arg) {
		n.getName().accept(this, arg);
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final MemberValuePair n, final A arg) {
		n.getName().accept(this, arg);
		n.getValue().accept(this, arg);
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final MethodCallExpr n, final A arg) {
		n.getArguments().forEach(p -> p.accept(this, arg));
		n.getName().accept(this, arg);
		n.getScope().ifPresent(l -> l.accept(this, arg));
		n.getTypeArguments().ifPresent(l -> l.forEach(v -> v.accept(this, arg)));
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final MethodDeclaration n, final A arg) {
		n.getBody().ifPresent(l -> l.accept(this, arg));
		n.getType().accept(this, arg);
		n.getName().accept(this, arg);
		n.getParameters().forEach(p -> p.accept(this, arg));
		n.getThrownExceptions().forEach(p -> p.accept(this, arg));
		n.getTypeParameters().forEach(p -> p.accept(this, arg));
		n.getAnnotations().forEach(p -> p.accept(this, arg));
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final NameExpr n, final A arg) {
		n.getName().accept(this, arg);
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final NormalAnnotationExpr n, final A arg) {
		n.getPairs().forEach(p -> p.accept(this, arg));
		n.getName().accept(this, arg);
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final NullLiteralExpr n, final A arg) {
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final ObjectCreationExpr n, final A arg) {
		n.getAnonymousClassBody().ifPresent(l -> l.forEach(v -> v.accept(this, arg)));
		n.getArguments().forEach(p -> p.accept(this, arg));
		n.getScope().ifPresent(l -> l.accept(this, arg));
		n.getType().accept(this, arg);
		n.getTypeArguments().ifPresent(l -> l.forEach(v -> v.accept(this, arg)));
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final PackageDeclaration n, final A arg) {
		n.getAnnotations().forEach(p -> p.accept(this, arg));
		n.getName().accept(this, arg);
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final Parameter n, final A arg) {
		n.getAnnotations().forEach(p -> p.accept(this, arg));
		n.getName().accept(this, arg);
		n.getType().accept(this, arg);
		n.getVarArgsAnnotations().forEach(p -> p.accept(this, arg));
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final PrimitiveType n, final A arg) {
		n.getAnnotations().forEach(p -> p.accept(this, arg));
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final Name n, final A arg) {
		n.getAnnotations().forEach(p -> p.accept(this, arg));
		n.getQualifier().ifPresent(l -> l.accept(this, arg));
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(SimpleName n, A arg) {
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(ArrayType n, A arg) {
		n.getComponentType().accept(this, arg);
		n.getAnnotations().forEach(p -> p.accept(this, arg));
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(ArrayCreationLevel n, A arg) {
		n.getAnnotations().forEach(p -> p.accept(this, arg));
		n.getDimension().ifPresent(l -> l.accept(this, arg));
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final IntersectionType n, final A arg) {
		n.getElements().forEach(p -> p.accept(this, arg));
		n.getAnnotations().forEach(p -> p.accept(this, arg));
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final UnionType n, final A arg) {
		n.getElements().forEach(p -> p.accept(this, arg));
		n.getAnnotations().forEach(p -> p.accept(this, arg));
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final ReturnStmt n, final A arg) {
		n.getExpression().ifPresent(l -> l.accept(this, arg));
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final SingleMemberAnnotationExpr n, final A arg) {
		n.getMemberValue().accept(this, arg);
		n.getName().accept(this, arg);
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final StringLiteralExpr n, final A arg) {
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final SuperExpr n, final A arg) {
		n.getClassExpr().ifPresent(l -> l.accept(this, arg));
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final SwitchEntryStmt n, final A arg) {
		n.getLabel().ifPresent(l -> l.accept(this, arg));
		n.getStatements().forEach(p -> p.accept(this, arg));
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final SwitchStmt n, final A arg) {
		n.getEntries().forEach(p -> p.accept(this, arg));
		n.getSelector().accept(this, arg);
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final SynchronizedStmt n, final A arg) {
		n.getBody().accept(this, arg);
		n.getExpression().accept(this, arg);
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final ThisExpr n, final A arg) {
		n.getClassExpr().ifPresent(l -> l.accept(this, arg));
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final ThrowStmt n, final A arg) {
		n.getExpression().accept(this, arg);
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final TryStmt n, final A arg) {
		n.getCatchClauses().forEach(p -> p.accept(this, arg));
		n.getFinallyBlock().ifPresent(l -> l.accept(this, arg));
		n.getResources().forEach(p -> p.accept(this, arg));
		n.getTryBlock().ifPresent(l -> l.accept(this, arg));
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final LocalClassDeclarationStmt n, final A arg) {
		n.getClassDeclaration().accept(this, arg);
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final TypeParameter n, final A arg) {
		n.getName().accept(this, arg);
		n.getTypeBound().forEach(p -> p.accept(this, arg));
		n.getAnnotations().forEach(p -> p.accept(this, arg));
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final UnaryExpr n, final A arg) {
		n.getExpression().accept(this, arg);
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final UnknownType n, final A arg) {
		n.getAnnotations().forEach(p -> p.accept(this, arg));
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final VariableDeclarationExpr n, final A arg) {
		n.getAnnotations().forEach(p -> p.accept(this, arg));
		n.getVariables().forEach(p -> p.accept(this, arg));
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final VariableDeclarator n, final A arg) {
		n.getInitializer().ifPresent(l -> l.accept(this, arg));
		n.getName().accept(this, arg);
		n.getType().accept(this, arg);
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final VoidType n, final A arg) {
		n.getAnnotations().forEach(p -> p.accept(this, arg));
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final WhileStmt n, final A arg) {
		n.getBody().accept(this, arg);
		n.getCondition().accept(this, arg);
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final WildcardType n, final A arg) {
		n.getExtendedType().ifPresent(l -> l.accept(this, arg));
		n.getSuperType().ifPresent(l -> l.accept(this, arg));
		n.getAnnotations().forEach(p -> p.accept(this, arg));
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(LambdaExpr n, final A arg) {
		n.getBody().accept(this, arg);
		n.getParameters().forEach(p -> p.accept(this, arg));
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(MethodReferenceExpr n, final A arg) {
		n.getScope().accept(this, arg);
		n.getTypeArguments().ifPresent(l -> l.forEach(v -> v.accept(this, arg)));
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(TypeExpr n, final A arg) {
		n.getType().accept(this, arg);
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	public void visit(NodeList n, A arg) {
		for (Object node : n) {
			((Node) node).accept(this, arg);
		}
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(final ImportDeclaration n, final A arg) {
		n.getName().accept(this, arg);
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(ModuleDeclaration n, A arg) {
		n.getAnnotations().forEach(p -> p.accept(this, arg));
		n.getModuleStmts().forEach(p -> p.accept(this, arg));
		n.getName().accept(this, arg);
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(ModuleRequiresStmt n, A arg) {
		n.getName().accept(this, arg);
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(ModuleExportsStmt n, A arg) {
		n.getModuleNames().forEach(p -> p.accept(this, arg));
		n.getName().accept(this, arg);
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(ModuleProvidesStmt n, A arg) {
		n.getType().accept(this, arg);
		n.getWithTypes().forEach(p -> p.accept(this, arg));
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(ModuleUsesStmt n, A arg) {
		n.getType().accept(this, arg);
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorAdapterGenerator")
	public void visit(ModuleOpensStmt n, A arg) {
		n.getModuleNames().forEach(p -> p.accept(this, arg));
		n.getName().accept(this, arg);
		n.getComment().ifPresent(l -> l.accept(this, arg));
	}
}
