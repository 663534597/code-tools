/*
 * Copyright (C) 2007-2010 Júlio Vilmar Gesser. Copyright (C) 2011, 2013-2016 The JavaParser Team. This file is part of JavaParser. JavaParser can be used either under the terms of
 * a) the GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. b) the terms of
 * the Apache License You should have received a copy of both licenses in LICENCE.LGPL and LICENCE.APACHE. Please refer to those files for details. JavaParser is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General
 * Public License for more details.
 */
package com.yijia.codegen.models.base.ast.type;

import static com.yijia.codegen.models.base.JavaParser.parseClassOrInterfaceType;
import static com.yijia.codegen.models.base.utils.Utils.assertNotNull;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Generated;
import com.yijia.codegen.models.base.TokenRange;
import com.yijia.codegen.models.base.ast.AllFieldsConstructor;
import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.NodeList;
import com.yijia.codegen.models.base.ast.expr.AnnotationExpr;
import com.yijia.codegen.models.base.ast.nodeTypes.NodeWithAnnotations;
import com.yijia.codegen.models.base.ast.observer.ObservableProperty;
import com.yijia.codegen.models.base.ast.visitor.CloneVisitor;
import com.yijia.codegen.models.base.ast.visitor.GenericVisitor;
import com.yijia.codegen.models.base.ast.visitor.VoidVisitor;
import com.yijia.codegen.models.base.metamodel.JavaParserMetaModel;
import com.yijia.codegen.models.base.metamodel.PrimitiveTypeMetaModel;

/**
 * A primitive type. <br/>
 * <code>int</code> <br/>
 * <code>boolean</code> <br/>
 * <code>short</code>
 * @author Julio Vilmar Gesser
 */
public final class PrimitiveType extends Type implements NodeWithAnnotations<PrimitiveType> {

	public static PrimitiveType booleanType() {
		return new PrimitiveType(Primitive.BOOLEAN);
	}

	public static PrimitiveType charType() {
		return new PrimitiveType(Primitive.CHAR);
	}

	public static PrimitiveType byteType() {
		return new PrimitiveType(Primitive.BYTE);
	}

	public static PrimitiveType shortType() {
		return new PrimitiveType(Primitive.SHORT);
	}

	public static PrimitiveType intType() {
		return new PrimitiveType(Primitive.INT);
	}

	public static PrimitiveType longType() {
		return new PrimitiveType(Primitive.LONG);
	}

	public static PrimitiveType floatType() {
		return new PrimitiveType(Primitive.FLOAT);
	}

	public static PrimitiveType doubleType() {
		return new PrimitiveType(Primitive.DOUBLE);
	}

	public enum Primitive {

		BOOLEAN("Boolean"), CHAR("Character"), BYTE("Byte"), SHORT("Short"), INT("Integer"), LONG("Long"), FLOAT("Float"), DOUBLE("Double");

		final String nameOfBoxedType;

		private String codeRepresentation;

		public ClassOrInterfaceType toBoxedType() {
			return parseClassOrInterfaceType(nameOfBoxedType);
		}

		public String asString() {
			return codeRepresentation;
		}

		Primitive(String nameOfBoxedType) {
			this.nameOfBoxedType = nameOfBoxedType;
			this.codeRepresentation = name().toLowerCase();
		}
	}

	static final HashMap<String, Primitive> unboxMap = new HashMap<>();

	static {
		for (Primitive unboxedType : Primitive.values()) {
			unboxMap.put(unboxedType.nameOfBoxedType, unboxedType);
		}
	}

	private Primitive type;

	public PrimitiveType() {
		this(null, Primitive.INT);
	}

	@AllFieldsConstructor
	public PrimitiveType(final Primitive type) {
		this(null, type);
	}

	/** This constructor is used by the parser and is considered private. */
	@Generated("com.yijia.codegen.models.base.generator.core.node.MainConstructorGenerator")
	public PrimitiveType(TokenRange tokenRange, Primitive type) {
		super(tokenRange);
		setType(type);
		customInitialization();
	}

	@Override
	public <R, A> R accept(final GenericVisitor<R, A> v, final A arg) {
		return v.visit(this, arg);
	}

	@Override
	public <A> void accept(final VoidVisitor<A> v, final A arg) {
		v.visit(this, arg);
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public Primitive getType() {
		return type;
	}

	public ClassOrInterfaceType toBoxedType() {
		return type.toBoxedType();
	}

	@Generated("com.yijia.codegen.models.base.generator.core.node.PropertyGenerator")
	public PrimitiveType setType(final Primitive type) {
		assertNotNull(type);
		if (type == this.type) {
			return (PrimitiveType) this;
		}
		notifyPropertyChange(ObservableProperty.TYPE, this.type, type);
		this.type = type;
		return this;
	}

	@Override
	public String asString() {
		return type.asString();
	}

	@Override
	public PrimitiveType setAnnotations(NodeList<AnnotationExpr> annotations) {
		return (PrimitiveType) super.setAnnotations(annotations);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetNodeListsGenerator")
	public List<NodeList<?>> getNodeLists() {
		return Arrays.asList(getAnnotations());
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
	public PrimitiveType clone() {
		return (PrimitiveType) accept(new CloneVisitor(), null);
	}

	@Override
	@Generated("com.yijia.codegen.models.base.generator.core.node.GetMetaModelGenerator")
	public PrimitiveTypeMetaModel getMetaModel() {
		return JavaParserMetaModel.primitiveTypeMetaModel;
	}
}
