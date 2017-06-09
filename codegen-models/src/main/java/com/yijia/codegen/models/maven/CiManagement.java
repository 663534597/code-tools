
// Generated by Modello 1.9.1,
// any modifications will be overwritten.
// ==============================================================

package com.yijia.codegen.models.maven;

/**
 * The <code>&lt;CiManagement&gt;</code> element contains informations required to the continuous integration system of the project.
 * @version $Revision$ $Date$
 */
@SuppressWarnings("all")
public class CiManagement implements java.io.Serializable, java.lang.Cloneable, InputLocationTracker {

	// --------------------------/
	// - Class/Member Variables -/
	// --------------------------/

	/**
	 * The name of the continuous integration system, e.g. <code>continuum</code>.
	 */
	private String system;

	/**
	 * URL for the continuous integration system used by the project if it has a web interface.
	 */
	private String url;

	/**
	 * Field notifiers.
	 */
	private java.util.List<Notifier> notifiers;

	/**
	 * Field locations.
	 */
	private java.util.Map<Object, InputLocation> locations;

	// -----------/
	// - Methods -/
	// -----------/

	/**
	 * Method addNotifier.
	 * @param notifier
	 */
	public void addNotifier(Notifier notifier) {
		getNotifiers().add(notifier);
	} // -- void addNotifier( Notifier )

	/**
	 * Method clone.
	 * @return CiManagement
	 */
	public CiManagement clone() {
		try {
			CiManagement copy = (CiManagement) super.clone();

			if (this.notifiers != null) {
				copy.notifiers = new java.util.ArrayList<Notifier>();
				for (Notifier item : this.notifiers) {
					copy.notifiers.add(((Notifier) item).clone());
				}
			}

			if (copy.locations != null) {
				copy.locations = new java.util.LinkedHashMap(copy.locations);
			}

			return copy;
		} catch (java.lang.Exception ex) {
			throw (java.lang.RuntimeException) new java.lang.UnsupportedOperationException(getClass().getName() + " does not support clone()").initCause(ex);
		}
	} // -- CiManagement clone()

	/**
	 * @param key
	 * @return InputLocation
	 */
	public InputLocation getLocation(Object key) {
		return (locations != null) ? locations.get(key) : null;
	} // -- InputLocation getLocation( Object )

	/**
	 * Method getNotifiers.
	 * @return List
	 */
	public java.util.List<Notifier> getNotifiers() {
		if (this.notifiers == null) {
			this.notifiers = new java.util.ArrayList<Notifier>();
		}

		return this.notifiers;
	} // -- java.util.List<Notifier> getNotifiers()

	/**
	 * Get the name of the continuous integration system, e.g. <code>continuum</code>.
	 * @return String
	 */
	public String getSystem() {
		return this.system;
	} // -- String getSystem()

	/**
	 * Get uRL for the continuous integration system used by the project if it has a web interface.
	 * @return String
	 */
	public String getUrl() {
		return this.url;
	} // -- String getUrl()

	/**
	 * Method removeNotifier.
	 * @param notifier
	 */
	public void removeNotifier(Notifier notifier) {
		getNotifiers().remove(notifier);
	} // -- void removeNotifier( Notifier )

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
	} // -- void setLocation( Object, InputLocation )

	/**
	 * Set configuration for notifying developers/users when a build is unsuccessful, including user information and notification mode.
	 * @param notifiers
	 */
	public void setNotifiers(java.util.List<Notifier> notifiers) {
		this.notifiers = notifiers;
	} // -- void setNotifiers( java.util.List )

	/**
	 * Set the name of the continuous integration system, e.g. <code>continuum</code>.
	 * @param system
	 */
	public void setSystem(String system) {
		this.system = system;
	} // -- void setSystem( String )

	/**
	 * Set uRL for the continuous integration system used by the project if it has a web interface.
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
	} // -- void setUrl( String )

}
