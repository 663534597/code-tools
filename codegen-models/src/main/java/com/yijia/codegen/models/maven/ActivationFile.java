package com.yijia.codegen.models.maven;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("all")
public class ActivationFile implements Serializable, Cloneable, InputLocationTracker {

	/**
	 * The name of the file that must be missing to activate the profile.
	 */
	private String missing;

	/**
	 * The name of the file that must exist to activate the profile.
	 */
	private String exists;

	/**
	 * Field locations.
	 */
	private Map<Object, InputLocation> locations;

	/**
	 * Method clone.
	 * @return ActivationFile
	 */
	public ActivationFile clone() {
		try {
			ActivationFile copy = (ActivationFile) super.clone();

			if (copy.locations != null) {
				copy.locations = new LinkedHashMap(copy.locations);
			}

			return copy;
		} catch (Exception ex) {
			throw (RuntimeException) new UnsupportedOperationException(getClass().getName() + " does not support clone()").initCause(ex);
		}
	}

	/**
	 * Get the name of the file that must exist to activate the profile.
	 * @return String
	 */
	public String getExists() {
		return this.exists;
	}

	/**
	 * @param key
	 * @return InputLocation
	 */
	public InputLocation getLocation(Object key) {
		return (locations != null) ? locations.get(key) : null;
	}

	/**
	 * Get the name of the file that must be missing to activate the profile.
	 * @return String
	 */
	public String getMissing() {
		return this.missing;
	}

	/**
	 * Set the name of the file that must exist to activate the profile.
	 * @param exists
	 */
	public void setExists(String exists) {
		this.exists = exists;
	}

	/**
	 * @param key
	 * @param location
	 */
	public void setLocation(Object key, InputLocation location) {
		if (location != null) {
			if (this.locations == null) {
				this.locations = new LinkedHashMap<Object, InputLocation>();
			}
			this.locations.put(key, location);
		}
	}

	/**
	 * Set the name of the file that must be missing to activate the profile.
	 * @param missing
	 */
	public void setMissing(String missing) {
		this.missing = missing;
	}

}
