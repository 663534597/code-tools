package com.yijia.codegen.models.maven;

import java.io.Serializable;
import java.util.Map;

@SuppressWarnings("all")
public class ConfigurationContainer implements Serializable, Cloneable, InputLocationTracker {

	private String inherited;

	private Object configuration;

	/**
	 * Field locations.
	 */
	private Map<Object, InputLocation> locations;

	/**
	 * Method clone.
	 * @return ConfigurationContainer
	 */
	public ConfigurationContainer clone() {
		try {
			ConfigurationContainer copy = (ConfigurationContainer) super.clone();

			if (this.configuration != null) {
				copy.configuration = new org.codehaus.plexus.util.xml.Xpp3Dom((org.codehaus.plexus.util.xml.Xpp3Dom) this.configuration);
			}

			if (copy.locations != null) {
				copy.locations = new java.util.LinkedHashMap(copy.locations);
			}

			return copy;
		} catch (java.lang.Exception ex) {
			throw (java.lang.RuntimeException) new java.lang.UnsupportedOperationException(getClass().getName() + " does not support clone()").initCause(ex);
		}
	}

	public Object getConfiguration() {
		return this.configuration;
	}

	public String getInherited() {
		return this.inherited;
	} // -- String getInherited()

	public InputLocation getLocation(Object key) {
		return (locations != null) ? locations.get(key) : null;
	} // -- InputLocation getLocation( Object )

	public void setConfiguration(Object configuration) {
		this.configuration = configuration;
	} // -- void setConfiguration( Object )

	public void setInherited(String inherited) {
		this.inherited = inherited;
	} // -- void setInherited( String )

	/**
	 * @param key
	 * @param location
	 */
	public void setLocation(Object key, InputLocation location) {
		if (location != null) {
			if (this.locations == null) {
				this.locations = new java.util.LinkedHashMap<Object, InputLocation>();
			}
			this.locations.put(key, location);
		}
	}

	public boolean isInherited() {
		return (inherited != null) ? Boolean.parseBoolean(inherited) : true;
	}

	public void setInherited(boolean inherited) {
		this.inherited = String.valueOf(inherited);
	}

	private boolean inheritanceApplied = true;

	public void unsetInheritanceApplied() {
		this.inheritanceApplied = false;
	}

	public boolean isInheritanceApplied() {
		return inheritanceApplied;
	}

}
