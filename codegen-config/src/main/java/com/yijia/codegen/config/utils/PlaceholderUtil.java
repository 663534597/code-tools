package com.yijia.codegen.config.utils;

import java.util.Map;
import java.util.Properties;

import com.yijia.codegen.config.utils.helper.PlaceholderHelper;
import com.yijia.codegen.config.utils.helper.PlaceholderHelper.PlaceholderResolver;

/**
 * @author chengj@justep.com
 */
public class PlaceholderUtil {

	private static final PlaceholderHelper placeholder;
	static {
		placeholder = new PlaceholderHelper("#{", "}", ":", true);
	}

	public static String replacePlaceholders(String key, final Properties properties) {
		return placeholder.replacePlaceholders(key, properties);
	}

	@SuppressWarnings("rawtypes")
	public static String replacePlaceholders(String key, Map properties) {
		return placeholder.replacePlaceholders(key, new PlaceholderResolver() {
			@Override
			public String resolvePlaceholder(String placeholderName) {
				return (String) properties.get(placeholderName);
			}
		});
	}

	public static String replacePlaceholders(String key, PlaceholderResolver placeholderResolver) {
		return placeholder.replacePlaceholders(key, placeholderResolver);
	}

}
