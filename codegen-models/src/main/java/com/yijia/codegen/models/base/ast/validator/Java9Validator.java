package com.yijia.codegen.models.base.ast.validator;

import com.yijia.codegen.models.base.ast.validator.chunks.ModifierValidator;
import com.yijia.codegen.models.base.ast.validator.chunks.UnderscoreKeywordValidator;

/**
 * This validator validates according to Java 9 syntax rules.
 */
public class Java9Validator extends Java8Validator {
	protected final Validator underscoreKeywordValidator = new UnderscoreKeywordValidator();
	protected final Validator modifiers = new ModifierValidator(true, true, true);

	public Java9Validator() {
		super();
		add(underscoreKeywordValidator);
		remove(noModules);
		replace(modifiersWithoutPrivateInterfaceMethods, modifiers);
	}
}
