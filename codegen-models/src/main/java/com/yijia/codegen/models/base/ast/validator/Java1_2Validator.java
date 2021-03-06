package com.yijia.codegen.models.base.ast.validator;

import com.yijia.codegen.models.base.ast.validator.chunks.ModifierValidator;

/**
 * This validator validates according to Java 1.2 syntax rules.
 */
public class Java1_2Validator extends Java1_1Validator {
	protected final Validator modifiersWithoutDefaultAndStaticInterfaceMethodsAndPrivateInterfaceMethods = new ModifierValidator(true, false, false);

	public Java1_2Validator() {
		super();
		replace(modifiersWithoutStrictfpAndDefaultAndStaticInterfaceMethodsAndPrivateInterfaceMethods, modifiersWithoutDefaultAndStaticInterfaceMethodsAndPrivateInterfaceMethods);
	}
}
