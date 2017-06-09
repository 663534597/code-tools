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
 * A visitor that does not return anything.
 * @author Julio Vilmar Gesser
 */
public interface VoidVisitor<A> {

	void visit(NodeList n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(AnnotationDeclaration n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(AnnotationMemberDeclaration n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(ArrayAccessExpr n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(ArrayCreationExpr n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(ArrayCreationLevel n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(ArrayInitializerExpr n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(ArrayType n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(AssertStmt n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(AssignExpr n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(BinaryExpr n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(BlockComment n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(BlockStmt n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(BooleanLiteralExpr n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(BreakStmt n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(CastExpr n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(CatchClause n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(CharLiteralExpr n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(ClassExpr n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(ClassOrInterfaceDeclaration n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(ClassOrInterfaceType n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(CompilationUnit n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(ConditionalExpr n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(ConstructorDeclaration n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(ContinueStmt n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(DoStmt n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(DoubleLiteralExpr n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(EmptyStmt n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(EnclosedExpr n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(EnumConstantDeclaration n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(EnumDeclaration n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(ExplicitConstructorInvocationStmt n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(ExpressionStmt n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(FieldAccessExpr n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(FieldDeclaration n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(ForStmt n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(ForeachStmt n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(IfStmt n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(ImportDeclaration n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(InitializerDeclaration n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(InstanceOfExpr n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(IntegerLiteralExpr n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(IntersectionType n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(JavadocComment n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(LabeledStmt n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(LambdaExpr n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(LineComment n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(LocalClassDeclarationStmt n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(LongLiteralExpr n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(MarkerAnnotationExpr n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(MemberValuePair n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(MethodCallExpr n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(MethodDeclaration n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(MethodReferenceExpr n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(NameExpr n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(Name n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(NormalAnnotationExpr n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(NullLiteralExpr n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(ObjectCreationExpr n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(PackageDeclaration n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(Parameter n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(PrimitiveType n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(ReturnStmt n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(SimpleName n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(SingleMemberAnnotationExpr n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(StringLiteralExpr n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(SuperExpr n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(SwitchEntryStmt n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(SwitchStmt n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(SynchronizedStmt n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(ThisExpr n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(ThrowStmt n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(TryStmt n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(TypeExpr n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(TypeParameter n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(UnaryExpr n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(UnionType n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(UnknownType n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(VariableDeclarationExpr n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(VariableDeclarator n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(VoidType n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(WhileStmt n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(WildcardType n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(ModuleDeclaration n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(ModuleRequiresStmt n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(ModuleExportsStmt n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(ModuleProvidesStmt n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(ModuleUsesStmt n, A arg);

	@Generated("com.yijia.codegen.models.base.generator.core.visitor.VoidVisitorGenerator")
	void visit(ModuleOpensStmt n, A arg);
}
