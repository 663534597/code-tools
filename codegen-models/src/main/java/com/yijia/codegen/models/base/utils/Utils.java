package com.yijia.codegen.models.base.utils;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class Utils {
	public static final String EOL = System.getProperty("line.separator");

	public static final Predicate<String> STRING_NOT_EMPTY = s -> !s.isEmpty();

	/**
	 * @deprecated This is no longer in use by JavaParser, please write your own replacement.
	 */
	public static <T> List<T> ensureNotNull(List<T> list) {
		return list == null ? new ArrayList<>() : list;
	}

	public static <E> boolean isNullOrEmpty(Collection<E> collection) {
		return collection == null || collection.isEmpty();
	}

	public static <T> T assertNotNull(T o) {
		if (o == null) {
			throw new AssertionError("A reference was unexpectedly null.");
		}
		return o;
	}

	public static String assertNonEmpty(String string) {
		if (string == null || string.isEmpty()) {
			throw new AssertionError("A string was unexpectedly empty.");
		}
		return string;
	}

	/**
	 * @return string with ASCII characters 10 and 13 replaced by the text "\n" and "\r".
	 */
	public static String escapeEndOfLines(String string) {
		StringBuilder escapedString = new StringBuilder();
		for (char c : string.toCharArray()) {
			switch (c) {
				case '\n':
					escapedString.append("\\n");
					break;
				case '\r':
					escapedString.append("\\r");
					break;
				default:
					escapedString.append(c);
			}
		}
		return escapedString.toString();
	}

	public static String readerToString(Reader reader) throws IOException {
		final StringBuilder result = new StringBuilder();
		final char[] buffer = new char[8 * 1024];
		int numChars;

		while ((numChars = reader.read(buffer, 0, buffer.length)) > 0) {
			result.append(buffer, 0, numChars);
		}

		return result.toString();
	}

	/**
	 * Puts varargs in a mutable list. This does not have the disadvantage of Arrays#asList that it has a static size.
	 * @deprecated This is no longer in use by JavaParser, please write your own replacement.
	 */
	public static <T> List<T> arrayToList(T[] array) {
		List<T> list = new LinkedList<>();
		Collections.addAll(list, array);
		return list;
	}

	/**
	 * @deprecated use screamingToCamelCase
	 */
	public static String toCamelCase(String original) {
		return screamingToCamelCase(original);
	}

	/**
	 * Transform a string to the camel case conversion.
	 * <p>
	 * For example "ABC_DEF" becomes "abcDef"
	 */
	public static String screamingToCamelCase(String original) {
		StringBuilder sb = new StringBuilder();
		String[] parts = original.toLowerCase().split("_");
		for (int i = 0; i < parts.length; i++) {
			sb.append(i == 0 ? parts[i] : capitalize(parts[i]));
		}
		return sb.toString();
	}

	/**
	 * @param input "aCamelCaseString"
	 * @return "A_CAMEL_CASE_STRING"
	 */
	public static String camelCaseToScreaming(String input) {
		if (input.isEmpty()) {
			return "";
		}
		StringBuilder scream = new StringBuilder(input.substring(0, 1).toUpperCase());
		for (char c : input.substring(1).toCharArray()) {
			if (Character.isUpperCase(c)) {
				scream.append("_");
			}
			scream.append(Character.toUpperCase(c));
		}
		return scream.toString();
	}

	/**
	 * Return the next word of the string, in other words it stops when a space is encountered.
	 */
	public static String nextWord(String string) {
		int index = 0;
		while (index < string.length() && !Character.isWhitespace(string.charAt(index))) {
			index++;
		}
		return string.substring(0, index);
	}

	/**
	 * Make an indent by appending indentLevel tab characters to the builder.
	 */
	public static StringBuilder indent(StringBuilder builder, int indentLevel) {
		for (int i = 0; i < indentLevel; i++) {
			builder.append("\t");
		}
		return builder;
	}

	/**
	 * Capitalizes the first character in the string.
	 */
	public static String capitalize(String s) {
		return stringTransformer(s, "capitalize", it -> it.toUpperCase());
	}

	/**
	 * Lower-cases the first character in the string.
	 */
	public static String decapitalize(String s) {
		return stringTransformer(s, "decapitalize", it -> it.toLowerCase());
	}

	private static String stringTransformer(String s, String operationDescription, Function<String, String> transformation) {
		if (s.isEmpty()) {
			throw new IllegalArgumentException(String.format("You cannot %s an empty string", operationDescription));
		}
		StringBuilder sb = new StringBuilder();
		sb.append(transformation.apply(s.substring(0, 1)));
		sb.append(s.substring(1));
		return sb.toString();
	}

	/**
	 * Return true if the value is null, an empty Optional or an empty String.
	 * @param value
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean valueIsNullOrEmpty(Object value) {
		if (value == null) {
			return true;
		}
		if (value instanceof Optional) {
			if (((Optional) value).isPresent()) {
				value = ((Optional) value).get();
			} else {
				return true;
			}
		}
		if (value instanceof Collection) {
			if (((Collection) value).isEmpty()) {
				return true;
			}
		}
		return false;
	}
}
