package com.yijia.codegen.models.maven;

@SuppressWarnings("all")
public class Build extends BuildBase implements java.io.Serializable, java.lang.Cloneable {

	// --------------------------/
	// - Class/Member Variables -/
	// --------------------------/

	/**
	 * This element specifies a directory containing the source of the project. The generated build system will compile the sources from this directory when the project is built.
	 * The path given is relative to the project descriptor. The default value is <code>src/main/java</code>.
	 */
	private String sourceDirectory;

	/**
	 * This element specifies a directory containing the script sources of the project. This directory is meant to be different from the sourceDirectory, in that its contents will
	 * be copied to the output directory in most cases (since scripts are interpreted rather than compiled). The default value is <code>src/main/scripts</code>.
	 */
	private String scriptSourceDirectory;

	/**
	 * This element specifies a directory containing the unit test source of the project. The generated build system will compile these directories when the project is being
	 * tested. The path given is relative to the project descriptor. The default value is <code>src/test/java</code>.
	 */
	private String testSourceDirectory;

	/**
	 * The directory where compiled application classes are placed. The default value is <code>target/classes</code>.
	 */
	private String outputDirectory;

	/**
	 * The directory where compiled test classes are placed. The default value is <code>target/test-classes</code>.
	 */
	private String testOutputDirectory;

	/**
	 * Field extensions.
	 */
	private java.util.List<Extension> extensions;

	// -----------/
	// - Methods -/
	// -----------/

	/**
	 * Method addExtension.
	 * @param extension
	 */
	public void addExtension(Extension extension) {
		getExtensions().add(extension);
	} // -- void addExtension( Extension )

	/**
	 * Method clone.
	 * @return Build
	 */
	public Build clone() {
		try {
			Build copy = (Build) super.clone();

			if (this.extensions != null) {
				copy.extensions = new java.util.ArrayList<Extension>();
				for (Extension item : this.extensions) {
					copy.extensions.add(((Extension) item).clone());
				}
			}

			return copy;
		} catch (java.lang.Exception ex) {
			throw (java.lang.RuntimeException) new java.lang.UnsupportedOperationException(getClass().getName() + " does not support clone()").initCause(ex);
		}
	} // -- Build clone()

	/**
	 * Method getExtensions.
	 * @return List
	 */
	public java.util.List<Extension> getExtensions() {
		if (this.extensions == null) {
			this.extensions = new java.util.ArrayList<Extension>();
		}

		return this.extensions;
	} // -- java.util.List<Extension> getExtensions()

	/**
	 * Get the directory where compiled application classes are placed. The default value is <code>target/classes</code>.
	 * @return String
	 */
	public String getOutputDirectory() {
		return this.outputDirectory;
	} // -- String getOutputDirectory()

	/**
	 * Get this element specifies a directory containing the script sources of the project. This directory is meant to be different from the sourceDirectory, in that its contents
	 * will be copied to the output directory in most cases (since scripts are interpreted rather than compiled). The default value is <code>src/main/scripts</code>.
	 * @return String
	 */
	public String getScriptSourceDirectory() {
		return this.scriptSourceDirectory;
	} // -- String getScriptSourceDirectory()

	/**
	 * Get this element specifies a directory containing the source of the project. The generated build system will compile the sources from this directory when the project is
	 * built. The path given is relative to the project descriptor. The default value is <code>src/main/java</code>.
	 * @return String
	 */
	public String getSourceDirectory() {
		return this.sourceDirectory;
	} // -- String getSourceDirectory()

	/**
	 * Get the directory where compiled test classes are placed. The default value is <code>target/test-classes</code>.
	 * @return String
	 */
	public String getTestOutputDirectory() {
		return this.testOutputDirectory;
	} // -- String getTestOutputDirectory()

	/**
	 * Get this element specifies a directory containing the unit test source of the project. The generated build system will compile these directories when the project is being
	 * tested. The path given is relative to the project descriptor. The default value is <code>src/test/java</code>.
	 * @return String
	 */
	public String getTestSourceDirectory() {
		return this.testSourceDirectory;
	} // -- String getTestSourceDirectory()

	/**
	 * Method removeExtension.
	 * @param extension
	 */
	public void removeExtension(Extension extension) {
		getExtensions().remove(extension);
	} // -- void removeExtension( Extension )

	/**
	 * Set a set of build extensions to use from this project.
	 * @param extensions
	 */
	public void setExtensions(java.util.List<Extension> extensions) {
		this.extensions = extensions;
	} // -- void setExtensions( java.util.List )

	/**
	 * Set the directory where compiled application classes are placed. The default value is <code>target/classes</code>.
	 * @param outputDirectory
	 */
	public void setOutputDirectory(String outputDirectory) {
		this.outputDirectory = outputDirectory;
	} // -- void setOutputDirectory( String )

	/**
	 * Set this element specifies a directory containing the script sources of the project. This directory is meant to be different from the sourceDirectory, in that its contents
	 * will be copied to the output directory in most cases (since scripts are interpreted rather than compiled). The default value is <code>src/main/scripts</code>.
	 * @param scriptSourceDirectory
	 */
	public void setScriptSourceDirectory(String scriptSourceDirectory) {
		this.scriptSourceDirectory = scriptSourceDirectory;
	} // -- void setScriptSourceDirectory( String )

	/**
	 * Set this element specifies a directory containing the source of the project. The generated build system will compile the sources from this directory when the project is
	 * built. The path given is relative to the project descriptor. The default value is <code>src/main/java</code>.
	 * @param sourceDirectory
	 */
	public void setSourceDirectory(String sourceDirectory) {
		this.sourceDirectory = sourceDirectory;
	} // -- void setSourceDirectory( String )

	/**
	 * Set the directory where compiled test classes are placed. The default value is <code>target/test-classes</code>.
	 * @param testOutputDirectory
	 */
	public void setTestOutputDirectory(String testOutputDirectory) {
		this.testOutputDirectory = testOutputDirectory;
	} // -- void setTestOutputDirectory( String )

	/**
	 * Set this element specifies a directory containing the unit test source of the project. The generated build system will compile these directories when the project is being
	 * tested. The path given is relative to the project descriptor. The default value is <code>src/test/java</code>.
	 * @param testSourceDirectory
	 */
	public void setTestSourceDirectory(String testSourceDirectory) {
		this.testSourceDirectory = testSourceDirectory;
	} // -- void setTestSourceDirectory( String )

}
