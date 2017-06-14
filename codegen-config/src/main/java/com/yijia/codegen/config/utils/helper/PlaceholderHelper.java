package com.yijia.codegen.config.utils.helper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class PlaceholderHelper {

	private static final Map<String, String> wellKnownSimplePrefixes = new HashMap<String, String>(4);
	static {
		wellKnownSimplePrefixes.put("}", "{");
		wellKnownSimplePrefixes.put("]", "[");
		wellKnownSimplePrefixes.put(")", "(");
	}

	private final String simplePrefix;
	private final String prefix;
	private final String suffix;
	private final String separator;
	private final boolean ignoreUnresolvablePlaceholders;

	public PlaceholderHelper(String placeholderPrefix, String placeholderSuffix) {
		this(placeholderPrefix, placeholderSuffix, null, true);
	}

	public PlaceholderHelper(String placeholderPrefix, String placeholderSuffix, String valueSeparator, boolean ignoreUnresolvablePlaceholders) {

		notNull(placeholderPrefix, "'placeholderPrefix' must not be null");
		notNull(placeholderSuffix, "'placeholderSuffix' must not be null");

		String simplePrefixForSuffix = wellKnownSimplePrefixes.get(placeholderSuffix);
		if (simplePrefixForSuffix != null && placeholderPrefix.endsWith(simplePrefixForSuffix)) {
			this.simplePrefix = simplePrefixForSuffix;
		} else {
			this.simplePrefix = placeholderPrefix;
		}
		this.prefix = placeholderPrefix;
		this.suffix = placeholderSuffix;
		this.separator = valueSeparator;
		this.ignoreUnresolvablePlaceholders = ignoreUnresolvablePlaceholders;
	}

	public String replacePlaceholders(String value, final Properties properties) {
		notNull(properties, "'properties' must not be null");
		return replacePlaceholders(value, new PlaceholderResolver() {
			@Override
			public String resolvePlaceholder(String placeholderName) {
				return properties.getProperty(placeholderName);
			}
		});
	}

	public String replacePlaceholders(String value, PlaceholderResolver placeholderResolver) {
		notNull(value, "'value' must not be null");
		return parseStringValue(value, placeholderResolver, new HashSet<String>());
	}

	private String parseStringValue(String value, PlaceholderResolver placeholderResolver, Set<String> visitedPlaceholders) {

		StringBuilder result = new StringBuilder(value);

		int startIndex = value.indexOf(prefix);
		while (startIndex != -1) {
			int endIndex = findPlaceholderEndIndex(result, startIndex);
			if (endIndex != -1) {
				String placeholder = result.substring(startIndex + prefix.length(), endIndex);
				String originalPlaceholder = placeholder;
				if (!visitedPlaceholders.add(originalPlaceholder)) {
					throw new IllegalArgumentException("Circular placeholder reference '" + originalPlaceholder + "' in property definitions");
				}
				placeholder = parseStringValue(placeholder, placeholderResolver, visitedPlaceholders);
				String propVal = placeholderResolver.resolvePlaceholder(placeholder);
				if (propVal == null && separator != null) {
					int separatorIndex = placeholder.indexOf(separator);
					if (separatorIndex != -1) {
						String actualPlaceholder = placeholder.substring(0, separatorIndex);
						String defaultValue = placeholder.substring(separatorIndex + separator.length());
						propVal = placeholderResolver.resolvePlaceholder(actualPlaceholder);
						if (propVal == null) {
							propVal = defaultValue;
						}
					}
				}
				if (propVal != null) {
					propVal = parseStringValue(propVal, placeholderResolver, visitedPlaceholders);
					result.replace(startIndex, endIndex + suffix.length(), propVal);
					startIndex = result.indexOf(prefix, startIndex + propVal.length());
				} else if (ignoreUnresolvablePlaceholders) {
					startIndex = result.indexOf(prefix, endIndex + suffix.length());
				} else {
					throw new IllegalArgumentException("Could not resolve placeholder '" + placeholder + "'" + " in value \"" + value + "\"");
				}
				visitedPlaceholders.remove(originalPlaceholder);
			} else {
				startIndex = -1;
			}
		}
		return result.toString();
	}

	private int findPlaceholderEndIndex(CharSequence buf, int startIndex) {
		int index = startIndex + prefix.length();
		int withinNestedPlaceholder = 0;
		while (index < buf.length()) {
			if (substringMatch(buf, index, suffix)) {
				if (withinNestedPlaceholder > 0) {
					withinNestedPlaceholder--;
					index = index + suffix.length();
				} else {
					return index;
				}
			} else if (substringMatch(buf, index, simplePrefix)) {
				withinNestedPlaceholder++;
				index = index + simplePrefix.length();
			} else {
				index++;
			}
		}
		return -1;
	}

	public boolean substringMatch(CharSequence str, int index, CharSequence substring) {
		for (int j = 0; j < substring.length(); j++) {
			int i = index + j;
			if (i >= str.length() || str.charAt(i) != substring.charAt(j)) {
				return false;
			}
		}
		return true;
	}

	public void notNull(Object object, String message) {
		if (object == null) {
			throw new IllegalArgumentException(message);
		}
	}

	public interface PlaceholderResolver {
		String resolvePlaceholder(String placeholderName);
	}

}
