package com.yijia.codegen.models.base.ast;

import static com.yijia.codegen.models.base.utils.Utils.assertNotNull;
import javax.annotation.Generated;
import com.yijia.codegen.models.base.TokenRange;
import com.yijia.codegen.models.base.ast.expr.Name;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithName;
import com.yijia.codegen.models.base.ast.observer.ObservableProperty;
import com.yijia.codegen.models.base.ast.visitor.CloneVisitor;
import com.yijia.codegen.models.base.ast.visitor.GenericVisitor;
import com.yijia.codegen.models.base.ast.visitor.VoidVisitor;
import com.yijia.codegen.models.base.metamodel.ImportDeclarationMetaModel;
import com.yijia.codegen.models.base.metamodel.JavaParserMetaModel;

/**
 * An import declaration. <br/>
 * <code>import com.yijia.codegen.models.base.JavaParser;</code> <br/>
 * <code>import com.yijia.codegen.models.base.*;</code> <br/>
 * <code>import com.yijia.codegen.models.base.JavaParser.*; </code> <br/>
 * <code>import static com.yijia.codegen.models.base.JavaParser.*;</code> <br/>
 * <code>import static com.yijia.codegen.models.base.JavaParser.parse;</code>
 * <p>
 * The name does not include the asterisk or the static keyword.
 * </p>
 * @author Julio Vilmar Gesser
 */
public final class ImportDeclaration extends Node implements NodeWithName<ImportDeclaration> {

	private Name name;

	private boolean isStatic;

	private boolean isAsterisk;

	@SuppressWarnings("unused")
	private ImportDeclaration() {
		this(null, new Name(), false, false);
	}

	@AllFieldsConstructor
	public ImportDeclaration(Name name, boolean isStatic, boolean isAsterisk) {
		this(null, name, isStatic, isAsterisk);
	}

	/** This constructor is used by the parser and is considered private. */
	@Generated("com.yijia.codegen.models.base.generator.core.node.MainConstructorGenerator")
	public ImportDeclaration(TokenRange tokenRange, Name name, boolean isStatic, boolean isAsterisk) {
		super(tokenRange);
		setName(name);
		setStatic(isStatic);
		setAsterisk(isAsterisk);
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

	/**
	 * Retrieves the name of the import (.* is not included.)
	 */
	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public Name getName() {
		return name;
	}

	/**
	 * Return if the import ends with "*".
	 */
	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public boolean isAsterisk() {
		return isAsterisk;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public boolean isStatic() {
		return isStatic;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public ImportDeclaration setAsterisk(final boolean isAsterisk) {
		if (isAsterisk == this.isAsterisk) {
			return (ImportDeclaration) this;
		}
		notifyPropertyChange(ObservableProperty.ASTERISK, this.isAsterisk, isAsterisk);
		this.isAsterisk = isAsterisk;
		return this;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public ImportDeclaration setName(final Name name) {
		assertNotNull(name);
		if (name == this.name) {
			return (ImportDeclaration) this;
		}
		notifyPropertyChange(ObservableProperty.NAME, this.name, name);
		if (this.name != null)
			this.name.setParentNode(null);
		this.name = name;
		setAsParentNodeOf(name);
		return this;
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public ImportDeclaration setStatic(final boolean isStatic) {
		if (isStatic == this.isStatic) {
			return (ImportDeclaration) this;
		}
		notifyPropertyChange(ObservableProperty.STATIC, this.isStatic, isStatic);
		this.isStatic = isStatic;
		return this;
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
	public ImportDeclaration clone() {
		return (ImportDeclaration) accept(new CloneVisitor(), null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetMetaModelGenerator")
	public ImportDeclarationMetaModel getMetaModel() {
		return JavaParserMetaModel.importDeclarationMetaModel;
	}
}
