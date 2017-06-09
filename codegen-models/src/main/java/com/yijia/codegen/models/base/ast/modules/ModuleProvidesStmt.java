package com.yijia.codegen.models.base.ast.modules;

import static com.yijia.codegen.models.base.utils.Utils.assertNotNull;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Generated;
import com.yijia.codegen.models.base.TokenRange;
import com.yijia.codegen.models.base.ast.AllFieldsConstructor;
import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.NodeList;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithType;
import com.yijia.codegen.models.base.ast.observer.ObservableProperty;
import com.yijia.codegen.models.base.ast.type.ClassOrInterfaceType;
import com.yijia.codegen.models.base.ast.type.Type;
import com.yijia.codegen.models.base.ast.visitor.CloneVisitor;
import com.yijia.codegen.models.base.ast.visitor.GenericVisitor;
import com.yijia.codegen.models.base.ast.visitor.VoidVisitor;
import com.yijia.codegen.models.base.metamodel.JavaParserMetaModel;
import com.yijia.codegen.models.base.metamodel.ModuleProvidesStmtMetaModel;

public class ModuleProvidesStmt extends ModuleStmt implements NodeWithType<ModuleProvidesStmt, Type> {

	private Type type;

	private NodeList<Type> withTypes;

	public ModuleProvidesStmt() {
		this(null, new ClassOrInterfaceType(), new NodeList<>());
	}

	@AllFieldsConstructor
	public ModuleProvidesStmt(Type type, NodeList<Type> withTypes) {
		this(null, type, withTypes);
	}

	/** This constructor is used by the parser and is considered private. */
	@Generated("com.yijia.codegen.models.base.generator.core.node.MainConstructorGenerator")
	public ModuleProvidesStmt(TokenRange tokenRange, Type type, NodeList<Type> withTypes) {
		super(tokenRange);
		setType(type);
		setWithTypes(withTypes);
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
		for (int i = 0; i < withTypes.size(); i++) {
			if (withTypes.get(i) == node) {
				withTypes.remove(i);
				return true;
			}
		}
		return super.remove(node);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public Type getType() {
		return type;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public ModuleProvidesStmt setType(final Type type) {
		assertNotNull(type);
		if (type == this.type) {
			return (ModuleProvidesStmt) this;
		}
		notifyPropertyChange(ObservableProperty.TYPE, this.type, type);
		if (this.type != null)
			this.type.setParentNode(null);
		this.type = type;
		setAsParentNodeOf(type);
		return this;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public NodeList<Type> getWithTypes() {
		return withTypes;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public ModuleProvidesStmt setWithTypes(final NodeList<Type> withTypes) {
		assertNotNull(withTypes);
		if (withTypes == this.withTypes) {
			return (ModuleProvidesStmt) this;
		}
		notifyPropertyChange(ObservableProperty.WITH_TYPES, this.withTypes, withTypes);
		if (this.withTypes != null)
			this.withTypes.setParentNode(null);
		this.withTypes = withTypes;
		setAsParentNodeOf(withTypes);
		return this;
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetNodeListsGenerator")
	public List<NodeList<?>> getNodeLists() {
		return Arrays.asList(getWithTypes());
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.CloneGenerator")
	public ModuleProvidesStmt clone() {
		return (ModuleProvidesStmt) accept(new CloneVisitor(), null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetMetaModelGenerator")
	public ModuleProvidesStmtMetaModel getMetaModel() {
		return JavaParserMetaModel.moduleProvidesStmtMetaModel;
	}
}
