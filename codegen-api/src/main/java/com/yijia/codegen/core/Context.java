package com.yijia.codegen.core;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public abstract interface Context {

	boolean containsKey(String key);

	void addProperty(String key, Object value);

	void setProperty(String key, Object value);

	void clearProperty(String key);

	Object getProperty(String key);

	Iterator<String> getKeys(String prefix);

	Properties getProperties(String key);

	boolean getBoolean(String key);

	boolean getBoolean(String key, boolean defaultValue);

	byte getByte(String key);

	byte getByte(String key, byte defaultValue);

	double getDouble(String key);

	double getDouble(String key, double defaultValue);

	float getFloat(String key);

	float getFloat(String key, float defaultValue);

	int getInt(String key);

	int getInt(String key, int defaultValue);

	long getLong(String key);

	long getLong(String key, long defaultValue);

	short getShort(String key);

	short getShort(String key, short defaultValue);

	BigDecimal getBigDecimal(String key);

	BigDecimal getBigDecimal(String key, BigDecimal defaultValue);

	BigInteger getBigInteger(String key);

	BigInteger getBigInteger(String key, BigInteger defaultValue);

	String getString(String key);

	String getString(String key, String defaultValue);

	String[] getStringArray(String key);

	List<Object> getList(String key);

	List<Object> getList(String key, List<?> defaultValue);

}
