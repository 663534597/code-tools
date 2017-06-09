package com.yijia.codegen.models.base.ast.modules;

import static com.yijia.codegen.models.base.utils.Utils.assertNotNull;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Generated;
import com.yijia.codegen.models.base.TokenRange;
import com.yijia.codegen.models.base.ast.AllFieldsConstructor;
import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.NodeList;
import com.yijia.codegen.models.base.ast.expr.Name;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithName;
import com.yijia.codegen.models.base.ast.observer.ObservableProperty;
import com.yijia.codegen.models.base.ast.visitor.CloneVisitor;
import com.yijia.codegen.models.base.ast.visitor.GenericVisitor;
import com.yijia.codegen.models.base.ast.visitor.VoidVisitor;
import com.yijia.codegen.models.base.metamodel.JavaParserMetaModel;
import com.yijia.codegen.models.base.metamodel.ModuleOpensStmtMetaModel;

public class ModuleOpensStmt extends ModuleStmt implements NodeWithName<ModuleOpensStmt> {

	private Name name;

	private NodeList<Name> moduleNames;

	public ModuleOpensStmt() {
		this(null, new Name(), new NodeList<>());
	}

	@AllFieldsConstructor
	public ModuleOpensStmt(Name name, NodeList<Name> moduleNames) {
		this(null, name, moduleNames);
	}

	/** This constructor is used by the parser and is considered private. */
	@Generated("com.yijia.codegen.models.base.generator.core.node.MainConstructorGenerator")
	public ModuleOpensStmt(TokenRange tokenRange, Name name, NodeList<Name> moduleNames) {
		super(tokenRange);
		setName(name);
		setModuleNames(moduleNames);
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

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.RemoveMethodGenerator")
	public boolean remove(Node node) {
		if (node == null)
			return false;
		for (int i = 0; i < moduleNames.size(); i++) {
			if (moduleNames.get(i) == node) {
				moduleNames.remove(i);
				return true;
			}
		}
		return super.remove(node);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public Name getName() {
		return name;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public ModuleOpensStmt setName(final Name name) {
		assertNotNull(name);
		if (name == this.name) {
			return (ModuleOpensStmt) this;
		}
		notifyPropertyChange(ObservableProperty.NAME, this.name, name);
		if (this.name != null)
			this.name.setParentNode(null);
		this.name = name;
		setAsParentNodeOf(name);
		return this;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public NodeList<Name> getModuleNames() {
		return moduleNames;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public ModuleOpensStmt setModuleNames(final NodeList<Name> moduleNames) {
		assertNotNull(moduleNames);
		if (moduleNames == this.moduleNames) {
			return (ModuleOpensStmt) this;
		}
		notifyPropertyChange(ObservableProperty.MODULE_NAMES, this.moduleNames, moduleNames);
		if (this.moduleNames != null)
			this.moduleNames.setParentNode(null);
		this.moduleNames = moduleNames;
		setAsParentNodeOf(moduleNames);
		return this;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetNodeListsGenerator")
	public List<NodeList<?>> getNodeLists() {
		return Arrays.asList(getModuleNames());
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.CloneGenerator")
	public ModuleOpensStmt clone() {
		return (ModuleOpensStmt) accept(new CloneVisitor(), null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetMetaModelGenerator")
	public ModuleOpensStmtMetaModel getMetaModel() {
		return JavaParserMetaModel.moduleOpensStmtMetaModel;
	}
}
