package com.yijia.codegen.models.base.ast.modules;

import static com.yijia.codegen.models.base.ast.Modifier.TRANSITIVE;
import static com.yijia.codegen.models.base.utils.Utils.assertNotNull;
import java.util.EnumSet;
import javax.annotation.Generated;
import com.yijia.codegen.models.base.TokenRange;
import com.yijia.codegen.models.base.ast.AllFieldsConstructor;
import com.yijia.codegen.models.base.ast.Modifier;
import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.expr.Name;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithName;
import com.yijia.codegen.models.base.ast.nodeTypes.modifiers.NodeWithStaticModifier;
import com.yijia.codegen.models.base.ast.observer.ObservableProperty;
import com.yijia.codegen.models.base.ast.visitor.CloneVisitor;
import com.yijia.codegen.models.base.ast.visitor.GenericVisitor;
import com.yijia.codegen.models.base.ast.visitor.VoidVisitor;
import com.yijia.codegen.models.base.metamodel.JavaParserMetaModel;
import com.yijia.codegen.models.base.metamodel.ModuleRequiresStmtMetaModel;

/**
 * A require statement in module-info.java. <code>require a.b.C;</code>
 */
public class ModuleRequiresStmt extends ModuleStmt implements NodeWithStaticModifier<ModuleRequiresStmt>, NodeWithName<ModuleRequiresStmt> {

	private EnumSet<Modifier> modifiers;

	private Name name;

	public ModuleRequiresStmt() {
		this(null, EnumSet.noneOf(Modifier.class), new Name());
	}

	@AllFieldsConstructor
	public ModuleRequiresStmt(EnumSet<Modifier> modifiers, Name name) {
		this(null, modifiers, name);
	}

	/** This constructor is used by the parser and is considered private. */
	@Generated("com.yijia.codegen.models.base.generator.core.node.MainConstructorGenerator")
	public ModuleRequiresStmt(TokenRange tokenRange, EnumSet<Modifier> modifiers, Name name) {
		super(tokenRange);
		setModifiers(modifiers);
		setName(name);
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
	public EnumSet<Modifier> getModifiers() {
		return modifiers;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public ModuleRequiresStmt setModifiers(final EnumSet<Modifier> modifiers) {
		assertNotNull(modifiers);
		if (modifiers == this.modifiers) {
			return (ModuleRequiresStmt) this;
		}
		notifyPropertyChange(ObservableProperty.MODIFIERS, this.modifiers, modifiers);
		this.modifiers = modifiers;
		return this;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public Name getName() {
		return name;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public ModuleRequiresStmt setName(final Name name) {
		assertNotNull(name);
		if (name == this.name) {
			return (ModuleRequiresStmt) this;
		}
		notifyPropertyChange(ObservableProperty.NAME, this.name, name);
		if (this.name != null)
			this.name.setParentNode(null);
		this.name = name;
		setAsParentNodeOf(name);
		return this;
	}

	public boolean isTransitive() {
		return getModifiers().contains(TRANSITIVE);
	}

	public ModuleRequiresStmt setTransitive(boolean set) {
		return setModifier(TRANSITIVE, set);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.RemoveMethodGenerator")
	public boolean remove(Node node) {
		if (node == null)
			return false;
		return super.remove(node);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.CloneGenerator")
	public ModuleRequiresStmt clone() {
		return (ModuleRequiresStmt) accept(new CloneVisitor(), null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetMetaModelGenerator")
	public ModuleRequiresStmtMetaModel getMetaModel() {
		return JavaParserMetaModel.moduleRequiresStmtMetaModel;
	}
}
