package com.yijia.codegen.models.base.ast.modules;

import static com.yijia.codegen.models.base.utils.Utils.assertNotNull;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Generated;
import com.yijia.codegen.models.base.TokenRange;
import com.yijia.codegen.models.base.ast.AllFieldsConstructor;
import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.NodeList;
import com.yijia.codegen.models.base.ast.expr.AnnotationExpr;
import com.yijia.codegen.models.base.ast.expr.Name;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithAnnotations;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithName;
import com.yijia.codegen.models.base.ast.observer.ObservableProperty;
import com.yijia.codegen.models.base.ast.visitor.CloneVisitor;
import com.yijia.codegen.models.base.ast.visitor.GenericVisitor;
import com.yijia.codegen.models.base.ast.visitor.VoidVisitor;
import com.yijia.codegen.models.base.metamodel.JavaParserMetaModel;
import com.yijia.codegen.models.base.metamodel.ModuleDeclarationMetaModel;

/**
 * A Java 9 Jigsaw module declaration. <code>@Foo module com.github.abc { requires a.B; }</code>
 */
public class ModuleDeclaration extends Node implements NodeWithName<ModuleDeclaration>, NodeWithAnnotations<ModuleDeclaration> {

	private Name name;

	private NodeList<AnnotationExpr> annotations;

	private boolean isOpen;

	private NodeList<ModuleStmt> moduleStmts;

	public ModuleDeclaration() {
		this(null, new NodeList<>(), new Name(), false, new NodeList<>());
	}

	public ModuleDeclaration(Name name, boolean isOpen) {
		this(null, new NodeList<>(), name, isOpen, new NodeList<>());
	}

	@AllFieldsConstructor
	public ModuleDeclaration(NodeList<AnnotationExpr> annotations, Name name, boolean isOpen, NodeList<ModuleStmt> moduleStmts) {
		this(null, annotations, name, isOpen, moduleStmts);
	}

	/** This constructor is used by the parser and is considered private. */
	@Generated("com.yijia.codegen.models.base.generator.core.node.MainConstructorGenerator")
	public ModuleDeclaration(TokenRange tokenRange, NodeList<AnnotationExpr> annotations, Name name, boolean isOpen, NodeList<ModuleStmt> moduleStmts) {
		super(tokenRange);
		setAnnotations(annotations);
		setName(name);
		setOpen(isOpen);
		setModuleStmts(moduleStmts);
		customInitialization();
	}

	@Override
	public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
		return v.visit(this, arg);
	}

	@Override
	public <A> void accept(VoidVisitor<A> v, A arg) {
		v.visit(this, arg);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public Name getName() {
		return name;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public ModuleDeclaration setName(final Name name) {
		assertNotNull(name);
		if (name == this.name) {
			return (ModuleDeclaration) this;
		}
		notifyPropertyChange(ObservableProperty.NAME, this.name, name);
		if (this.name != null)
			this.name.setParentNode(null);
		this.name = name;
		setAsParentNodeOf(name);
		return this;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public NodeList<AnnotationExpr> getAnnotations() {
		return annotations;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public ModuleDeclaration setAnnotations(final NodeList<AnnotationExpr> annotations) {
		assertNotNull(annotations);
		if (annotations == this.annotations) {
			return (ModuleDeclaration) this;
		}
		notifyPropertyChange(ObservableProperty.ANNOTATIONS, this.annotations, annotations);
		if (this.annotations != null)
			this.annotations.setParentNode(null);
		this.annotations = annotations;
		setAsParentNodeOf(annotations);
		return this;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetNodeListsGenerator")
	public List<NodeList<?>> getNodeLists() {
		return Arrays.asList(getAnnotations(), getModuleStmts());
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.RemoveMethodGenerator")
	public boolean remove(Node node) {
		if (node == null)
			return false;
		for (int i = 0; i < annotations.size(); i++) {
			if (annotations.get(i) == node) {
				annotations.remove(i);
				return true;
			}
		}
		for (int i = 0; i < moduleStmts.size(); i++) {
			if (moduleStmts.get(i) == node) {
				moduleStmts.remove(i);
				return true;
			}
		}
		return super.remove(node);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public boolean isOpen() {
		return isOpen;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public ModuleDeclaration setOpen(final boolean isOpen) {
		if (isOpen == this.isOpen) {
			return (ModuleDeclaration) this;
		}
		notifyPropertyChange(ObservableProperty.OPEN, this.isOpen, isOpen);
		this.isOpen = isOpen;
		return this;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public NodeList<ModuleStmt> getModuleStmts() {
		return moduleStmts;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public ModuleDeclaration setModuleStmts(final NodeList<ModuleStmt> moduleStmts) {
		assertNotNull(moduleStmts);
		if (moduleStmts == this.moduleStmts) {
			return (ModuleDeclaration) this;
		}
		notifyPropertyChange(ObservableProperty.MODULE_STMTS, this.moduleStmts, moduleStmts);
		if (this.moduleStmts != null)
			this.moduleStmts.setParentNode(null);
		this.moduleStmts = moduleStmts;
		setAsParentNodeOf(moduleStmts);
		return this;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.CloneGenerator")
	public ModuleDeclaration clone() {
		return (ModuleDeclaration) accept(new CloneVisitor(), null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetMetaModelGenerator")
	public ModuleDeclarationMetaModel getMetaModel() {
		return JavaParserMetaModel.moduleDeclarationMetaModel;
	}
}
