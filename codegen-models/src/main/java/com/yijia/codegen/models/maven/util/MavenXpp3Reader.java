package com.yijia.codegen.models.maven.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.codehaus.plexus.util.ReaderFactory;
import org.codehaus.plexus.util.xml.pull.EntityReplacementMap;
import org.codehaus.plexus.util.xml.pull.MXParser;
import org.codehaus.plexus.util.xml.pull.XmlPullParser;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import com.yijia.codegen.models.maven.Activation;
import com.yijia.codegen.models.maven.ActivationFile;
import com.yijia.codegen.models.maven.ActivationOS;
import com.yijia.codegen.models.maven.ActivationProperty;
import com.yijia.codegen.models.maven.Build;
import com.yijia.codegen.models.maven.BuildBase;
import com.yijia.codegen.models.maven.CiManagement;
import com.yijia.codegen.models.maven.ConfigurationContainer;
import com.yijia.codegen.models.maven.Contributor;
import com.yijia.codegen.models.maven.Dependency;
import com.yijia.codegen.models.maven.DependencyManagement;
import com.yijia.codegen.models.maven.DeploymentRepository;
import com.yijia.codegen.models.maven.Developer;
import com.yijia.codegen.models.maven.DistributionManagement;
import com.yijia.codegen.models.maven.Exclusion;
import com.yijia.codegen.models.maven.Extension;
import com.yijia.codegen.models.maven.FileSet;
import com.yijia.codegen.models.maven.IssueManagement;
import com.yijia.codegen.models.maven.License;
import com.yijia.codegen.models.maven.MailingList;
import com.yijia.codegen.models.maven.Model;
import com.yijia.codegen.models.maven.ModelBase;
import com.yijia.codegen.models.maven.Notifier;
import com.yijia.codegen.models.maven.Organization;
import com.yijia.codegen.models.maven.Parent;
import com.yijia.codegen.models.maven.PatternSet;
import com.yijia.codegen.models.maven.Plugin;
import com.yijia.codegen.models.maven.PluginConfiguration;
import com.yijia.codegen.models.maven.PluginContainer;
import com.yijia.codegen.models.maven.PluginExecution;
import com.yijia.codegen.models.maven.PluginManagement;
import com.yijia.codegen.models.maven.Prerequisites;
import com.yijia.codegen.models.maven.Profile;
import com.yijia.codegen.models.maven.Relocation;
import com.yijia.codegen.models.maven.ReportPlugin;
import com.yijia.codegen.models.maven.ReportSet;
import com.yijia.codegen.models.maven.Reporting;
import com.yijia.codegen.models.maven.Repository;
import com.yijia.codegen.models.maven.RepositoryBase;
import com.yijia.codegen.models.maven.RepositoryPolicy;
import com.yijia.codegen.models.maven.Resource;
import com.yijia.codegen.models.maven.Scm;
import com.yijia.codegen.models.maven.Site;

@SuppressWarnings("all")
public class MavenXpp3Reader {

	private boolean addDefaultEntities = true;

	/**
	 * Field contentTransformer.
	 */
	public final ContentTransformer contentTransformer;

	public MavenXpp3Reader() {
		this(new ContentTransformer() {
			public String transform(String source, String fieldName) {
				return source;
			}
		});
	}

	public MavenXpp3Reader(ContentTransformer contentTransformer) {
		this.contentTransformer = contentTransformer;
	}

	/**
	 * Method checkFieldWithDuplicate.
	 * @param parser
	 * @param parsed
	 * @param alias
	 * @param tagName
	 * @throws XmlPullParserException
	 * @return boolean
	 */
	private boolean checkFieldWithDuplicate(XmlPullParser parser, String tagName, String alias, java.util.Set parsed) throws XmlPullParserException {
		if (!(parser.getName().equals(tagName) || parser.getName().equals(alias))) {
			return false;
		}
		if (!parsed.add(tagName)) {
			throw new XmlPullParserException("Duplicated tag: '" + tagName + "'", parser, null);
		}
		return true;
	}

	/**
	 * Method checkUnknownAttribute.
	 * @param parser
	 * @param strict
	 * @param tagName
	 * @param attribute
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	private void checkUnknownAttribute(XmlPullParser parser, String attribute, String tagName, boolean strict) throws XmlPullParserException, IOException {
		// strictXmlAttributes = true for model: if strict == true, not only elements are checked but attributes too
		if (strict) {
			throw new XmlPullParserException("Unknown attribute '" + attribute + "' for tag '" + tagName + "'", parser, null);
		}
	}

	/**
	 * Method checkUnknownElement.
	 * @param parser
	 * @param strict
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	private void checkUnknownElement(XmlPullParser parser, boolean strict) throws XmlPullParserException, IOException {
		if (strict) {
			throw new XmlPullParserException("Unrecognised tag: '" + parser.getName() + "'", parser, null);
		}

		for (int unrecognizedTagCount = 1; unrecognizedTagCount > 0;) {
			int eventType = parser.next();
			if (eventType == XmlPullParser.START_TAG) {
				unrecognizedTagCount++;
			} else if (eventType == XmlPullParser.END_TAG) {
				unrecognizedTagCount--;
			}
		}
	}

	/**
	 * Returns the state of the "add default entities" flag.
	 * @return boolean
	 */
	public boolean getAddDefaultEntities() {
		return addDefaultEntities;
	} // -- boolean getAddDefaultEntities()

	/**
	 * Method getBooleanValue.
	 * @param s
	 * @param parser
	 * @param attribute
	 * @throws XmlPullParserException
	 * @return boolean
	 */
	private boolean getBooleanValue(String s, String attribute, XmlPullParser parser) throws XmlPullParserException {
		return getBooleanValue(s, attribute, parser, null);
	} // -- boolean getBooleanValue( String, String, XmlPullParser )

	/**
	 * Method getBooleanValue.
	 * @param s
	 * @param defaultValue
	 * @param parser
	 * @param attribute
	 * @throws XmlPullParserException
	 * @return boolean
	 */
	private boolean getBooleanValue(String s, String attribute, XmlPullParser parser, String defaultValue) throws XmlPullParserException {
		if (s != null && s.length() != 0) {
			return Boolean.valueOf(s).booleanValue();
		}
		if (defaultValue != null) {
			return Boolean.valueOf(defaultValue).booleanValue();
		}
		return false;
	} // -- boolean getBooleanValue( String, String, XmlPullParser, String )

	/**
	 * Method getByteValue.
	 * @param s
	 * @param strict
	 * @param parser
	 * @param attribute
	 * @throws XmlPullParserException
	 * @return byte
	 */
	private byte getByteValue(String s, String attribute, XmlPullParser parser, boolean strict) throws XmlPullParserException {
		if (s != null) {
			try {
				return Byte.valueOf(s).byteValue();
			} catch (NumberFormatException nfe) {
				if (strict) {
					throw new XmlPullParserException("Unable to parse element '" + attribute + "', must be a byte", parser, nfe);
				}
			}
		}
		return 0;
	} // -- byte getByteValue( String, String, XmlPullParser, boolean )

	/**
	 * Method getCharacterValue.
	 * @param s
	 * @param parser
	 * @param attribute
	 * @throws XmlPullParserException
	 * @return char
	 */
	private char getCharacterValue(String s, String attribute, XmlPullParser parser) throws XmlPullParserException {
		if (s != null) {
			return s.charAt(0);
		}
		return 0;
	} // -- char getCharacterValue( String, String, XmlPullParser )

	/**
	 * Method getDateValue.
	 * @param s
	 * @param parser
	 * @param attribute
	 * @throws XmlPullParserException
	 * @return Date
	 */
	private Date getDateValue(String s, String attribute, XmlPullParser parser) throws XmlPullParserException {
		return getDateValue(s, attribute, null, parser);
	} // -- java.util.Date getDateValue( String, String, XmlPullParser )

	/**
	 * Method getDateValue.
	 * @param s
	 * @param parser
	 * @param dateFormat
	 * @param attribute
	 * @throws XmlPullParserException
	 * @return Date
	 */
	private Date getDateValue(String s, String attribute, String dateFormat, XmlPullParser parser) throws XmlPullParserException {
		if (s != null) {
			String effectiveDateFormat = dateFormat;
			if (dateFormat == null) {
				effectiveDateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS";
			}
			if ("long".equals(effectiveDateFormat)) {
				try {
					return new java.util.Date(Long.parseLong(s));
				} catch (NumberFormatException e) {
					throw new XmlPullParserException(e.getMessage(), parser, e);
				}
			} else {
				try {
					DateFormat dateParser = new SimpleDateFormat(effectiveDateFormat, java.util.Locale.US);
					return dateParser.parse(s);
				} catch (java.text.ParseException e) {
					throw new XmlPullParserException(e.getMessage(), parser, e);
				}
			}
		}
		return null;
	} // -- java.util.Date getDateValue( String, String, String, XmlPullParser )

	/**
	 * Method getDoubleValue.
	 * @param s
	 * @param strict
	 * @param parser
	 * @param attribute
	 * @throws XmlPullParserException
	 * @return double
	 */
	private double getDoubleValue(String s, String attribute, XmlPullParser parser, boolean strict) throws XmlPullParserException {
		if (s != null) {
			try {
				return Double.valueOf(s).doubleValue();
			} catch (NumberFormatException nfe) {
				if (strict) {
					throw new XmlPullParserException("Unable to parse element '" + attribute + "', must be a floating point number", parser, nfe);
				}
			}
		}
		return 0;
	} // -- double getDoubleValue( String, String, XmlPullParser, boolean )

	/**
	 * Method getFloatValue.
	 * @param s
	 * @param strict
	 * @param parser
	 * @param attribute
	 * @throws XmlPullParserException
	 * @return float
	 */
	private float getFloatValue(String s, String attribute, XmlPullParser parser, boolean strict) throws XmlPullParserException {
		if (s != null) {
			try {
				return Float.valueOf(s).floatValue();
			} catch (NumberFormatException nfe) {
				if (strict) {
					throw new XmlPullParserException("Unable to parse element '" + attribute + "', must be a floating point number", parser, nfe);
				}
			}
		}
		return 0;
	} // -- float getFloatValue( String, String, XmlPullParser, boolean )

	/**
	 * Method getIntegerValue.
	 * @param s
	 * @param strict
	 * @param parser
	 * @param attribute
	 * @throws XmlPullParserException
	 * @return int
	 */
	private int getIntegerValue(String s, String attribute, XmlPullParser parser, boolean strict) throws XmlPullParserException {
		if (s != null) {
			try {
				return Integer.valueOf(s).intValue();
			} catch (NumberFormatException nfe) {
				if (strict) {
					throw new XmlPullParserException("Unable to parse element '" + attribute + "', must be an integer", parser, nfe);
				}
			}
		}
		return 0;
	} // -- int getIntegerValue( String, String, XmlPullParser, boolean )

	/**
	 * Method getLongValue.
	 * @param s
	 * @param strict
	 * @param parser
	 * @param attribute
	 * @throws XmlPullParserException
	 * @return long
	 */
	private long getLongValue(String s, String attribute, XmlPullParser parser, boolean strict) throws XmlPullParserException {
		if (s != null) {
			try {
				return Long.valueOf(s).longValue();
			} catch (NumberFormatException nfe) {
				if (strict) {
					throw new XmlPullParserException("Unable to parse element '" + attribute + "', must be a long integer", parser, nfe);
				}
			}
		}
		return 0;
	} // -- long getLongValue( String, String, XmlPullParser, boolean )

	/**
	 * Method getRequiredAttributeValue.
	 * @param s
	 * @param strict
	 * @param parser
	 * @param attribute
	 * @throws XmlPullParserException
	 * @return String
	 */
	private String getRequiredAttributeValue(String s, String attribute, XmlPullParser parser, boolean strict) throws XmlPullParserException {
		if (s == null) {
			if (strict) {
				throw new XmlPullParserException("Missing required value for attribute '" + attribute + "'", parser, null);
			}
		}
		return s;
	} // -- String getRequiredAttributeValue( String, String, XmlPullParser, boolean )

	/**
	 * Method getShortValue.
	 * @param s
	 * @param strict
	 * @param parser
	 * @param attribute
	 * @throws XmlPullParserException
	 * @return short
	 */
	private short getShortValue(String s, String attribute, XmlPullParser parser, boolean strict) throws XmlPullParserException {
		if (s != null) {
			try {
				return Short.valueOf(s).shortValue();
			} catch (NumberFormatException nfe) {
				if (strict) {
					throw new XmlPullParserException("Unable to parse element '" + attribute + "', must be a short integer", parser, nfe);
				}
			}
		}
		return 0;
	} // -- short getShortValue( String, String, XmlPullParser, boolean )

	/**
	 * Method getTrimmedValue.
	 * @param s
	 * @return String
	 */
	private String getTrimmedValue(String s) {
		if (s != null) {
			s = s.trim();
		}
		return s;
	} // -- String getTrimmedValue( String )

	/**
	 * Method interpolatedTrimmed.
	 * @param value
	 * @param context
	 * @return String
	 */
	private String interpolatedTrimmed(String value, String context) {
		return getTrimmedValue(contentTransformer.transform(value, context));
	} // -- String interpolatedTrimmed( String, String )

	/**
	 * Method nextTag.
	 * @param parser
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return int
	 */
	private int nextTag(XmlPullParser parser) throws IOException, XmlPullParserException {
		int eventType = parser.next();
		if (eventType == XmlPullParser.TEXT) {
			eventType = parser.next();
		}
		if (eventType != XmlPullParser.START_TAG && eventType != XmlPullParser.END_TAG) {
			throw new XmlPullParserException("expected START_TAG or END_TAG not " + XmlPullParser.TYPES[eventType], parser, null);
		}
		return eventType;
	} // -- int nextTag( XmlPullParser )

	/**
	 * @see ReaderFactory#newXmlReader
	 * @param reader
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return Model
	 */
	public Model read(Reader reader, boolean strict) throws IOException, XmlPullParserException {
		XmlPullParser parser = addDefaultEntities ? new MXParser(EntityReplacementMap.defaultEntityReplacementMap) : new MXParser();

		parser.setInput(reader);

		return read(parser, strict);
	} // -- Model read( Reader, boolean )

	/**
	 * @see ReaderFactory#newXmlReader
	 * @param reader
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return Model
	 */
	public Model read(Reader reader) throws IOException, XmlPullParserException {
		return read(reader, true);
	} // -- Model read( Reader )

	/**
	 * Method read.
	 * @param in
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return Model
	 */
	public Model read(InputStream in, boolean strict) throws IOException, XmlPullParserException {
		return read(ReaderFactory.newXmlReader(in), strict);
	} // -- Model read( InputStream, boolean )

	/**
	 * Method read.
	 * @param in
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return Model
	 */
	public Model read(InputStream in) throws IOException, XmlPullParserException {
		return read(ReaderFactory.newXmlReader(in));
	} // -- Model read( InputStream )

	/**
	 * Method parseActivation.
	 * @param parser
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return Activation
	 */
	private Activation parseActivation(XmlPullParser parser, boolean strict) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		Activation activation = new Activation();
		for (int i = parser.getAttributeCount() - 1; i >= 0; i--) {
			String name = parser.getAttributeName(i);
			String value = parser.getAttributeValue(i);

			if (name.indexOf(':') >= 0) {
				// just ignore attributes with non-default namespace (for example: xmlns:xsi)
			} else {
				checkUnknownAttribute(parser, name, tagName, strict);
			}
		}
		java.util.Set parsed = new java.util.HashSet();
		while ((strict ? parser.nextTag() : nextTag(parser)) == XmlPullParser.START_TAG) {
			if (checkFieldWithDuplicate(parser, "activeByDefault", null, parsed)) {
				activation.setActiveByDefault(getBooleanValue(interpolatedTrimmed(parser.nextText(), "activeByDefault"), "activeByDefault", parser, "false"));
			} else if (checkFieldWithDuplicate(parser, "jdk", null, parsed)) {
				activation.setJdk(interpolatedTrimmed(parser.nextText(), "jdk"));
			} else if (checkFieldWithDuplicate(parser, "os", null, parsed)) {
				activation.setOs(parseActivationOS(parser, strict));
			} else if (checkFieldWithDuplicate(parser, "property", null, parsed)) {
				activation.setProperty(parseActivationProperty(parser, strict));
			} else if (checkFieldWithDuplicate(parser, "file", null, parsed)) {
				activation.setFile(parseActivationFile(parser, strict));
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return activation;
	} // -- Activation parseActivation( XmlPullParser, boolean )

	/**
	 * Method parseActivationFile.
	 * @param parser
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return ActivationFile
	 */
	private ActivationFile parseActivationFile(XmlPullParser parser, boolean strict) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		ActivationFile activationFile = new ActivationFile();
		for (int i = parser.getAttributeCount() - 1; i >= 0; i--) {
			String name = parser.getAttributeName(i);
			String value = parser.getAttributeValue(i);

			if (name.indexOf(':') >= 0) {
				// just ignore attributes with non-default namespace (for example: xmlns:xsi)
			} else {
				checkUnknownAttribute(parser, name, tagName, strict);
			}
		}
		java.util.Set parsed = new java.util.HashSet();
		while ((strict ? parser.nextTag() : nextTag(parser)) == XmlPullParser.START_TAG) {
			if (checkFieldWithDuplicate(parser, "missing", null, parsed)) {
				activationFile.setMissing(interpolatedTrimmed(parser.nextText(), "missing"));
			} else if (checkFieldWithDuplicate(parser, "exists", null, parsed)) {
				activationFile.setExists(interpolatedTrimmed(parser.nextText(), "exists"));
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return activationFile;
	} // -- ActivationFile parseActivationFile( XmlPullParser, boolean )

	/**
	 * Method parseActivationOS.
	 * @param parser
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return ActivationOS
	 */
	private ActivationOS parseActivationOS(XmlPullParser parser, boolean strict) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		ActivationOS activationOS = new ActivationOS();
		for (int i = parser.getAttributeCount() - 1; i >= 0; i--) {
			String name = parser.getAttributeName(i);
			String value = parser.getAttributeValue(i);

			if (name.indexOf(':') >= 0) {
				// just ignore attributes with non-default namespace (for example: xmlns:xsi)
			} else {
				checkUnknownAttribute(parser, name, tagName, strict);
			}
		}
		java.util.Set parsed = new java.util.HashSet();
		while ((strict ? parser.nextTag() : nextTag(parser)) == XmlPullParser.START_TAG) {
			if (checkFieldWithDuplicate(parser, "name", null, parsed)) {
				activationOS.setName(interpolatedTrimmed(parser.nextText(), "name"));
			} else if (checkFieldWithDuplicate(parser, "family", null, parsed)) {
				activationOS.setFamily(interpolatedTrimmed(parser.nextText(), "family"));
			} else if (checkFieldWithDuplicate(parser, "arch", null, parsed)) {
				activationOS.setArch(interpolatedTrimmed(parser.nextText(), "arch"));
			} else if (checkFieldWithDuplicate(parser, "version", null, parsed)) {
				activationOS.setVersion(interpolatedTrimmed(parser.nextText(), "version"));
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return activationOS;
	} // -- ActivationOS parseActivationOS( XmlPullParser, boolean )

	/**
	 * Method parseActivationProperty.
	 * @param parser
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return ActivationProperty
	 */
	private ActivationProperty parseActivationProperty(XmlPullParser parser, boolean strict) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		ActivationProperty activationProperty = new ActivationProperty();
		for (int i = parser.getAttributeCount() - 1; i >= 0; i--) {
			String name = parser.getAttributeName(i);
			String value = parser.getAttributeValue(i);

			if (name.indexOf(':') >= 0) {
				// just ignore attributes with non-default namespace (for example: xmlns:xsi)
			} else {
				checkUnknownAttribute(parser, name, tagName, strict);
			}
		}
		java.util.Set parsed = new java.util.HashSet();
		while ((strict ? parser.nextTag() : nextTag(parser)) == XmlPullParser.START_TAG) {
			if (checkFieldWithDuplicate(parser, "name", null, parsed)) {
				activationProperty.setName(interpolatedTrimmed(parser.nextText(), "name"));
			} else if (checkFieldWithDuplicate(parser, "value", null, parsed)) {
				activationProperty.setValue(interpolatedTrimmed(parser.nextText(), "value"));
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return activationProperty;
	} // -- ActivationProperty parseActivationProperty( XmlPullParser, boolean )

	/**
	 * Method parseBuild.
	 * @param parser
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return Build
	 */
	private Build parseBuild(XmlPullParser parser, boolean strict) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		Build build = new Build();
		for (int i = parser.getAttributeCount() - 1; i >= 0; i--) {
			String name = parser.getAttributeName(i);
			String value = parser.getAttributeValue(i);

			if (name.indexOf(':') >= 0) {
				// just ignore attributes with non-default namespace (for example: xmlns:xsi)
			} else {
				checkUnknownAttribute(parser, name, tagName, strict);
			}
		}
		java.util.Set parsed = new java.util.HashSet();
		while ((strict ? parser.nextTag() : nextTag(parser)) == XmlPullParser.START_TAG) {
			if (checkFieldWithDuplicate(parser, "sourceDirectory", null, parsed)) {
				build.setSourceDirectory(interpolatedTrimmed(parser.nextText(), "sourceDirectory"));
			} else if (checkFieldWithDuplicate(parser, "scriptSourceDirectory", null, parsed)) {
				build.setScriptSourceDirectory(interpolatedTrimmed(parser.nextText(), "scriptSourceDirectory"));
			} else if (checkFieldWithDuplicate(parser, "testSourceDirectory", null, parsed)) {
				build.setTestSourceDirectory(interpolatedTrimmed(parser.nextText(), "testSourceDirectory"));
			} else if (checkFieldWithDuplicate(parser, "outputDirectory", null, parsed)) {
				build.setOutputDirectory(interpolatedTrimmed(parser.nextText(), "outputDirectory"));
			} else if (checkFieldWithDuplicate(parser, "testOutputDirectory", null, parsed)) {
				build.setTestOutputDirectory(interpolatedTrimmed(parser.nextText(), "testOutputDirectory"));
			} else if (checkFieldWithDuplicate(parser, "extensions", null, parsed)) {
				java.util.List<Extension> extensions = new java.util.ArrayList<Extension>();
				build.setExtensions(extensions);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("extension".equals(parser.getName())) {
						extensions.add(parseExtension(parser, strict));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "defaultGoal", null, parsed)) {
				build.setDefaultGoal(interpolatedTrimmed(parser.nextText(), "defaultGoal"));
			} else if (checkFieldWithDuplicate(parser, "resources", null, parsed)) {
				java.util.List<Resource> resources = new java.util.ArrayList<Resource>();
				build.setResources(resources);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("resource".equals(parser.getName())) {
						resources.add(parseResource(parser, strict));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "testResources", null, parsed)) {
				java.util.List<Resource> testResources = new java.util.ArrayList<Resource>();
				build.setTestResources(testResources);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("testResource".equals(parser.getName())) {
						testResources.add(parseResource(parser, strict));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "directory", null, parsed)) {
				build.setDirectory(interpolatedTrimmed(parser.nextText(), "directory"));
			} else if (checkFieldWithDuplicate(parser, "finalName", null, parsed)) {
				build.setFinalName(interpolatedTrimmed(parser.nextText(), "finalName"));
			} else if (checkFieldWithDuplicate(parser, "filters", null, parsed)) {
				java.util.List<String> filters = new java.util.ArrayList<String>();
				build.setFilters(filters);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("filter".equals(parser.getName())) {
						filters.add(interpolatedTrimmed(parser.nextText(), "filters"));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "pluginManagement", null, parsed)) {
				build.setPluginManagement(parsePluginManagement(parser, strict));
			} else if (checkFieldWithDuplicate(parser, "plugins", null, parsed)) {
				java.util.List<Plugin> plugins = new java.util.ArrayList<Plugin>();
				build.setPlugins(plugins);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("plugin".equals(parser.getName())) {
						plugins.add(parsePlugin(parser, strict));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return build;
	} // -- Build parseBuild( XmlPullParser, boolean )

	/**
	 * Method parseBuildBase.
	 * @param parser
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return BuildBase
	 */
	private BuildBase parseBuildBase(XmlPullParser parser, boolean strict) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		BuildBase buildBase = new BuildBase();
		for (int i = parser.getAttributeCount() - 1; i >= 0; i--) {
			String name = parser.getAttributeName(i);
			String value = parser.getAttributeValue(i);

			if (name.indexOf(':') >= 0) {
				// just ignore attributes with non-default namespace (for example: xmlns:xsi)
			} else {
				checkUnknownAttribute(parser, name, tagName, strict);
			}
		}
		java.util.Set parsed = new java.util.HashSet();
		while ((strict ? parser.nextTag() : nextTag(parser)) == XmlPullParser.START_TAG) {
			if (checkFieldWithDuplicate(parser, "defaultGoal", null, parsed)) {
				buildBase.setDefaultGoal(interpolatedTrimmed(parser.nextText(), "defaultGoal"));
			} else if (checkFieldWithDuplicate(parser, "resources", null, parsed)) {
				java.util.List<Resource> resources = new java.util.ArrayList<Resource>();
				buildBase.setResources(resources);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("resource".equals(parser.getName())) {
						resources.add(parseResource(parser, strict));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "testResources", null, parsed)) {
				java.util.List<Resource> testResources = new java.util.ArrayList<Resource>();
				buildBase.setTestResources(testResources);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("testResource".equals(parser.getName())) {
						testResources.add(parseResource(parser, strict));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "directory", null, parsed)) {
				buildBase.setDirectory(interpolatedTrimmed(parser.nextText(), "directory"));
			} else if (checkFieldWithDuplicate(parser, "finalName", null, parsed)) {
				buildBase.setFinalName(interpolatedTrimmed(parser.nextText(), "finalName"));
			} else if (checkFieldWithDuplicate(parser, "filters", null, parsed)) {
				java.util.List<String> filters = new java.util.ArrayList<String>();
				buildBase.setFilters(filters);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("filter".equals(parser.getName())) {
						filters.add(interpolatedTrimmed(parser.nextText(), "filters"));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "pluginManagement", null, parsed)) {
				buildBase.setPluginManagement(parsePluginManagement(parser, strict));
			} else if (checkFieldWithDuplicate(parser, "plugins", null, parsed)) {
				java.util.List<Plugin> plugins = new java.util.ArrayList<Plugin>();
				buildBase.setPlugins(plugins);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("plugin".equals(parser.getName())) {
						plugins.add(parsePlugin(parser, strict));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return buildBase;
	} // -- BuildBase parseBuildBase( XmlPullParser, boolean )

	/**
	 * Method parseCiManagement.
	 * @param parser
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return CiManagement
	 */
	private CiManagement parseCiManagement(XmlPullParser parser, boolean strict) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		CiManagement ciManagement = new CiManagement();
		for (int i = parser.getAttributeCount() - 1; i >= 0; i--) {
			String name = parser.getAttributeName(i);
			String value = parser.getAttributeValue(i);

			if (name.indexOf(':') >= 0) {
				// just ignore attributes with non-default namespace (for example: xmlns:xsi)
			} else {
				checkUnknownAttribute(parser, name, tagName, strict);
			}
		}
		java.util.Set parsed = new java.util.HashSet();
		while ((strict ? parser.nextTag() : nextTag(parser)) == XmlPullParser.START_TAG) {
			if (checkFieldWithDuplicate(parser, "system", null, parsed)) {
				ciManagement.setSystem(interpolatedTrimmed(parser.nextText(), "system"));
			} else if (checkFieldWithDuplicate(parser, "url", null, parsed)) {
				ciManagement.setUrl(interpolatedTrimmed(parser.nextText(), "url"));
			} else if (checkFieldWithDuplicate(parser, "notifiers", null, parsed)) {
				java.util.List<Notifier> notifiers = new java.util.ArrayList<Notifier>();
				ciManagement.setNotifiers(notifiers);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("notifier".equals(parser.getName())) {
						notifiers.add(parseNotifier(parser, strict));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return ciManagement;
	} // -- CiManagement parseCiManagement( XmlPullParser, boolean )

	/**
	 * Method parseConfigurationContainer.
	 * @param parser
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return ConfigurationContainer
	 */
	private ConfigurationContainer parseConfigurationContainer(XmlPullParser parser, boolean strict) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		ConfigurationContainer configurationContainer = new ConfigurationContainer();
		for (int i = parser.getAttributeCount() - 1; i >= 0; i--) {
			String name = parser.getAttributeName(i);
			String value = parser.getAttributeValue(i);

			if (name.indexOf(':') >= 0) {
				// just ignore attributes with non-default namespace (for example: xmlns:xsi)
			} else {
				checkUnknownAttribute(parser, name, tagName, strict);
			}
		}
		java.util.Set parsed = new java.util.HashSet();
		while ((strict ? parser.nextTag() : nextTag(parser)) == XmlPullParser.START_TAG) {
			if (checkFieldWithDuplicate(parser, "inherited", null, parsed)) {
				configurationContainer.setInherited(interpolatedTrimmed(parser.nextText(), "inherited"));
			} else if (checkFieldWithDuplicate(parser, "configuration", null, parsed)) {
				configurationContainer.setConfiguration(org.codehaus.plexus.util.xml.Xpp3DomBuilder.build(parser, true));
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return configurationContainer;
	} // -- ConfigurationContainer parseConfigurationContainer( XmlPullParser, boolean )

	/**
	 * Method parseContributor.
	 * @param parser
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return Contributor
	 */
	private Contributor parseContributor(XmlPullParser parser, boolean strict) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		Contributor contributor = new Contributor();
		for (int i = parser.getAttributeCount() - 1; i >= 0; i--) {
			String name = parser.getAttributeName(i);
			String value = parser.getAttributeValue(i);

			if (name.indexOf(':') >= 0) {
				// just ignore attributes with non-default namespace (for example: xmlns:xsi)
			} else {
				checkUnknownAttribute(parser, name, tagName, strict);
			}
		}
		java.util.Set parsed = new java.util.HashSet();
		while ((strict ? parser.nextTag() : nextTag(parser)) == XmlPullParser.START_TAG) {
			if (checkFieldWithDuplicate(parser, "name", null, parsed)) {
				contributor.setName(interpolatedTrimmed(parser.nextText(), "name"));
			} else if (checkFieldWithDuplicate(parser, "email", null, parsed)) {
				contributor.setEmail(interpolatedTrimmed(parser.nextText(), "email"));
			} else if (checkFieldWithDuplicate(parser, "url", null, parsed)) {
				contributor.setUrl(interpolatedTrimmed(parser.nextText(), "url"));
			} else if (checkFieldWithDuplicate(parser, "organization", "organisation", parsed)) {
				contributor.setOrganization(interpolatedTrimmed(parser.nextText(), "organization"));
			} else if (checkFieldWithDuplicate(parser, "organizationUrl", "organisationUrl", parsed)) {
				contributor.setOrganizationUrl(interpolatedTrimmed(parser.nextText(), "organizationUrl"));
			} else if (checkFieldWithDuplicate(parser, "roles", null, parsed)) {
				java.util.List<String> roles = new java.util.ArrayList<String>();
				contributor.setRoles(roles);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("role".equals(parser.getName())) {
						roles.add(interpolatedTrimmed(parser.nextText(), "roles"));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "timezone", null, parsed)) {
				contributor.setTimezone(interpolatedTrimmed(parser.nextText(), "timezone"));
			} else if (checkFieldWithDuplicate(parser, "properties", null, parsed)) {
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					String key = parser.getName();
					String value = parser.nextText().trim();
					contributor.addProperty(key, value);
				}
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return contributor;
	} // -- Contributor parseContributor( XmlPullParser, boolean )

	/**
	 * Method parseDependency.
	 * @param parser
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return Dependency
	 */
	private Dependency parseDependency(XmlPullParser parser, boolean strict) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		Dependency dependency = new Dependency();
		for (int i = parser.getAttributeCount() - 1; i >= 0; i--) {
			String name = parser.getAttributeName(i);
			String value = parser.getAttributeValue(i);

			if (name.indexOf(':') >= 0) {
				// just ignore attributes with non-default namespace (for example: xmlns:xsi)
			} else {
				checkUnknownAttribute(parser, name, tagName, strict);
			}
		}
		java.util.Set parsed = new java.util.HashSet();
		while ((strict ? parser.nextTag() : nextTag(parser)) == XmlPullParser.START_TAG) {
			if (checkFieldWithDuplicate(parser, "groupId", null, parsed)) {
				dependency.setGroupId(interpolatedTrimmed(parser.nextText(), "groupId"));
			} else if (checkFieldWithDuplicate(parser, "artifactId", null, parsed)) {
				dependency.setArtifactId(interpolatedTrimmed(parser.nextText(), "artifactId"));
			} else if (checkFieldWithDuplicate(parser, "version", null, parsed)) {
				dependency.setVersion(interpolatedTrimmed(parser.nextText(), "version"));
			} else if (checkFieldWithDuplicate(parser, "type", null, parsed)) {
				dependency.setType(interpolatedTrimmed(parser.nextText(), "type"));
			} else if (checkFieldWithDuplicate(parser, "classifier", null, parsed)) {
				dependency.setClassifier(interpolatedTrimmed(parser.nextText(), "classifier"));
			} else if (checkFieldWithDuplicate(parser, "scope", null, parsed)) {
				dependency.setScope(interpolatedTrimmed(parser.nextText(), "scope"));
			} else if (checkFieldWithDuplicate(parser, "systemPath", null, parsed)) {
				dependency.setSystemPath(interpolatedTrimmed(parser.nextText(), "systemPath"));
			} else if (checkFieldWithDuplicate(parser, "exclusions", null, parsed)) {
				java.util.List<Exclusion> exclusions = new java.util.ArrayList<Exclusion>();
				dependency.setExclusions(exclusions);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("exclusion".equals(parser.getName())) {
						exclusions.add(parseExclusion(parser, strict));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "optional", null, parsed)) {
				dependency.setOptional(interpolatedTrimmed(parser.nextText(), "optional"));
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return dependency;
	} // -- Dependency parseDependency( XmlPullParser, boolean )

	/**
	 * Method parseDependencyManagement.
	 * @param parser
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return DependencyManagement
	 */
	private DependencyManagement parseDependencyManagement(XmlPullParser parser, boolean strict) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		DependencyManagement dependencyManagement = new DependencyManagement();
		for (int i = parser.getAttributeCount() - 1; i >= 0; i--) {
			String name = parser.getAttributeName(i);
			String value = parser.getAttributeValue(i);

			if (name.indexOf(':') >= 0) {
				// just ignore attributes with non-default namespace (for example: xmlns:xsi)
			} else {
				checkUnknownAttribute(parser, name, tagName, strict);
			}
		}
		java.util.Set parsed = new java.util.HashSet();
		while ((strict ? parser.nextTag() : nextTag(parser)) == XmlPullParser.START_TAG) {
			if (checkFieldWithDuplicate(parser, "dependencies", null, parsed)) {
				java.util.List<Dependency> dependencies = new java.util.ArrayList<Dependency>();
				dependencyManagement.setDependencies(dependencies);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("dependency".equals(parser.getName())) {
						dependencies.add(parseDependency(parser, strict));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return dependencyManagement;
	} // -- DependencyManagement parseDependencyManagement( XmlPullParser, boolean )

	/**
	 * Method parseDeploymentRepository.
	 * @param parser
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return DeploymentRepository
	 */
	private DeploymentRepository parseDeploymentRepository(XmlPullParser parser, boolean strict) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		DeploymentRepository deploymentRepository = new DeploymentRepository();
		for (int i = parser.getAttributeCount() - 1; i >= 0; i--) {
			String name = parser.getAttributeName(i);
			String value = parser.getAttributeValue(i);

			if (name.indexOf(':') >= 0) {
				// just ignore attributes with non-default namespace (for example: xmlns:xsi)
			} else {
				checkUnknownAttribute(parser, name, tagName, strict);
			}
		}
		java.util.Set parsed = new java.util.HashSet();
		while ((strict ? parser.nextTag() : nextTag(parser)) == XmlPullParser.START_TAG) {
			if (checkFieldWithDuplicate(parser, "uniqueVersion", null, parsed)) {
				deploymentRepository.setUniqueVersion(getBooleanValue(interpolatedTrimmed(parser.nextText(), "uniqueVersion"), "uniqueVersion", parser, "true"));
			} else if (checkFieldWithDuplicate(parser, "releases", null, parsed)) {
				deploymentRepository.setReleases(parseRepositoryPolicy(parser, strict));
			} else if (checkFieldWithDuplicate(parser, "snapshots", null, parsed)) {
				deploymentRepository.setSnapshots(parseRepositoryPolicy(parser, strict));
			} else if (checkFieldWithDuplicate(parser, "id", null, parsed)) {
				deploymentRepository.setId(interpolatedTrimmed(parser.nextText(), "id"));
			} else if (checkFieldWithDuplicate(parser, "name", null, parsed)) {
				deploymentRepository.setName(interpolatedTrimmed(parser.nextText(), "name"));
			} else if (checkFieldWithDuplicate(parser, "url", null, parsed)) {
				deploymentRepository.setUrl(interpolatedTrimmed(parser.nextText(), "url"));
			} else if (checkFieldWithDuplicate(parser, "layout", null, parsed)) {
				deploymentRepository.setLayout(interpolatedTrimmed(parser.nextText(), "layout"));
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return deploymentRepository;
	} // -- DeploymentRepository parseDeploymentRepository( XmlPullParser, boolean )

	/**
	 * Method parseDeveloper.
	 * @param parser
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return Developer
	 */
	private Developer parseDeveloper(XmlPullParser parser, boolean strict) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		Developer developer = new Developer();
		for (int i = parser.getAttributeCount() - 1; i >= 0; i--) {
			String name = parser.getAttributeName(i);
			String value = parser.getAttributeValue(i);

			if (name.indexOf(':') >= 0) {
				// just ignore attributes with non-default namespace (for example: xmlns:xsi)
			} else {
				checkUnknownAttribute(parser, name, tagName, strict);
			}
		}
		java.util.Set parsed = new java.util.HashSet();
		while ((strict ? parser.nextTag() : nextTag(parser)) == XmlPullParser.START_TAG) {
			if (checkFieldWithDuplicate(parser, "id", null, parsed)) {
				developer.setId(interpolatedTrimmed(parser.nextText(), "id"));
			} else if (checkFieldWithDuplicate(parser, "name", null, parsed)) {
				developer.setName(interpolatedTrimmed(parser.nextText(), "name"));
			} else if (checkFieldWithDuplicate(parser, "email", null, parsed)) {
				developer.setEmail(interpolatedTrimmed(parser.nextText(), "email"));
			} else if (checkFieldWithDuplicate(parser, "url", null, parsed)) {
				developer.setUrl(interpolatedTrimmed(parser.nextText(), "url"));
			} else if (checkFieldWithDuplicate(parser, "organization", "organisation", parsed)) {
				developer.setOrganization(interpolatedTrimmed(parser.nextText(), "organization"));
			} else if (checkFieldWithDuplicate(parser, "organizationUrl", "organisationUrl", parsed)) {
				developer.setOrganizationUrl(interpolatedTrimmed(parser.nextText(), "organizationUrl"));
			} else if (checkFieldWithDuplicate(parser, "roles", null, parsed)) {
				java.util.List<String> roles = new java.util.ArrayList<String>();
				developer.setRoles(roles);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("role".equals(parser.getName())) {
						roles.add(interpolatedTrimmed(parser.nextText(), "roles"));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "timezone", null, parsed)) {
				developer.setTimezone(interpolatedTrimmed(parser.nextText(), "timezone"));
			} else if (checkFieldWithDuplicate(parser, "properties", null, parsed)) {
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					String key = parser.getName();
					String value = parser.nextText().trim();
					developer.addProperty(key, value);
				}
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return developer;
	} // -- Developer parseDeveloper( XmlPullParser, boolean )

	/**
	 * Method parseDistributionManagement.
	 * @param parser
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return DistributionManagement
	 */
	private DistributionManagement parseDistributionManagement(XmlPullParser parser, boolean strict) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		DistributionManagement distributionManagement = new DistributionManagement();
		for (int i = parser.getAttributeCount() - 1; i >= 0; i--) {
			String name = parser.getAttributeName(i);
			String value = parser.getAttributeValue(i);

			if (name.indexOf(':') >= 0) {
				// just ignore attributes with non-default namespace (for example: xmlns:xsi)
			} else {
				checkUnknownAttribute(parser, name, tagName, strict);
			}
		}
		java.util.Set parsed = new java.util.HashSet();
		while ((strict ? parser.nextTag() : nextTag(parser)) == XmlPullParser.START_TAG) {
			if (checkFieldWithDuplicate(parser, "repository", null, parsed)) {
				distributionManagement.setRepository(parseDeploymentRepository(parser, strict));
			} else if (checkFieldWithDuplicate(parser, "snapshotRepository", null, parsed)) {
				distributionManagement.setSnapshotRepository(parseDeploymentRepository(parser, strict));
			} else if (checkFieldWithDuplicate(parser, "site", null, parsed)) {
				distributionManagement.setSite(parseSite(parser, strict));
			} else if (checkFieldWithDuplicate(parser, "downloadUrl", null, parsed)) {
				distributionManagement.setDownloadUrl(interpolatedTrimmed(parser.nextText(), "downloadUrl"));
			} else if (checkFieldWithDuplicate(parser, "relocation", null, parsed)) {
				distributionManagement.setRelocation(parseRelocation(parser, strict));
			} else if (checkFieldWithDuplicate(parser, "status", null, parsed)) {
				distributionManagement.setStatus(interpolatedTrimmed(parser.nextText(), "status"));
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return distributionManagement;
	} // -- DistributionManagement parseDistributionManagement( XmlPullParser, boolean )

	/**
	 * Method parseExclusion.
	 * @param parser
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return Exclusion
	 */
	private Exclusion parseExclusion(XmlPullParser parser, boolean strict) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		Exclusion exclusion = new Exclusion();
		for (int i = parser.getAttributeCount() - 1; i >= 0; i--) {
			String name = parser.getAttributeName(i);
			String value = parser.getAttributeValue(i);

			if (name.indexOf(':') >= 0) {
				// just ignore attributes with non-default namespace (for example: xmlns:xsi)
			} else {
				checkUnknownAttribute(parser, name, tagName, strict);
			}
		}
		java.util.Set parsed = new java.util.HashSet();
		while ((strict ? parser.nextTag() : nextTag(parser)) == XmlPullParser.START_TAG) {
			if (checkFieldWithDuplicate(parser, "artifactId", null, parsed)) {
				exclusion.setArtifactId(interpolatedTrimmed(parser.nextText(), "artifactId"));
			} else if (checkFieldWithDuplicate(parser, "groupId", null, parsed)) {
				exclusion.setGroupId(interpolatedTrimmed(parser.nextText(), "groupId"));
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return exclusion;
	} // -- Exclusion parseExclusion( XmlPullParser, boolean )

	/**
	 * Method parseExtension.
	 * @param parser
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return Extension
	 */
	private Extension parseExtension(XmlPullParser parser, boolean strict) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		Extension extension = new Extension();
		for (int i = parser.getAttributeCount() - 1; i >= 0; i--) {
			String name = parser.getAttributeName(i);
			String value = parser.getAttributeValue(i);

			if (name.indexOf(':') >= 0) {
				// just ignore attributes with non-default namespace (for example: xmlns:xsi)
			} else {
				checkUnknownAttribute(parser, name, tagName, strict);
			}
		}
		java.util.Set parsed = new java.util.HashSet();
		while ((strict ? parser.nextTag() : nextTag(parser)) == XmlPullParser.START_TAG) {
			if (checkFieldWithDuplicate(parser, "groupId", null, parsed)) {
				extension.setGroupId(interpolatedTrimmed(parser.nextText(), "groupId"));
			} else if (checkFieldWithDuplicate(parser, "artifactId", null, parsed)) {
				extension.setArtifactId(interpolatedTrimmed(parser.nextText(), "artifactId"));
			} else if (checkFieldWithDuplicate(parser, "version", null, parsed)) {
				extension.setVersion(interpolatedTrimmed(parser.nextText(), "version"));
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return extension;
	} // -- Extension parseExtension( XmlPullParser, boolean )

	/**
	 * Method parseFileSet.
	 * @param parser
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return FileSet
	 */
	private FileSet parseFileSet(XmlPullParser parser, boolean strict) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		FileSet fileSet = new FileSet();
		for (int i = parser.getAttributeCount() - 1; i >= 0; i--) {
			String name = parser.getAttributeName(i);
			String value = parser.getAttributeValue(i);

			if (name.indexOf(':') >= 0) {
				// just ignore attributes with non-default namespace (for example: xmlns:xsi)
			} else {
				checkUnknownAttribute(parser, name, tagName, strict);
			}
		}
		java.util.Set parsed = new java.util.HashSet();
		while ((strict ? parser.nextTag() : nextTag(parser)) == XmlPullParser.START_TAG) {
			if (checkFieldWithDuplicate(parser, "directory", null, parsed)) {
				fileSet.setDirectory(interpolatedTrimmed(parser.nextText(), "directory"));
			} else if (checkFieldWithDuplicate(parser, "includes", null, parsed)) {
				java.util.List<String> includes = new java.util.ArrayList<String>();
				fileSet.setIncludes(includes);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("include".equals(parser.getName())) {
						includes.add(interpolatedTrimmed(parser.nextText(), "includes"));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "excludes", null, parsed)) {
				java.util.List<String> excludes = new java.util.ArrayList<String>();
				fileSet.setExcludes(excludes);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("exclude".equals(parser.getName())) {
						excludes.add(interpolatedTrimmed(parser.nextText(), "excludes"));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return fileSet;
	} // -- FileSet parseFileSet( XmlPullParser, boolean )

	/**
	 * Method parseIssueManagement.
	 * @param parser
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return IssueManagement
	 */
	private IssueManagement parseIssueManagement(XmlPullParser parser, boolean strict) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		IssueManagement issueManagement = new IssueManagement();
		for (int i = parser.getAttributeCount() - 1; i >= 0; i--) {
			String name = parser.getAttributeName(i);
			String value = parser.getAttributeValue(i);

			if (name.indexOf(':') >= 0) {
				// just ignore attributes with non-default namespace (for example: xmlns:xsi)
			} else {
				checkUnknownAttribute(parser, name, tagName, strict);
			}
		}
		java.util.Set parsed = new java.util.HashSet();
		while ((strict ? parser.nextTag() : nextTag(parser)) == XmlPullParser.START_TAG) {
			if (checkFieldWithDuplicate(parser, "system", null, parsed)) {
				issueManagement.setSystem(interpolatedTrimmed(parser.nextText(), "system"));
			} else if (checkFieldWithDuplicate(parser, "url", null, parsed)) {
				issueManagement.setUrl(interpolatedTrimmed(parser.nextText(), "url"));
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return issueManagement;
	} // -- IssueManagement parseIssueManagement( XmlPullParser, boolean )

	/**
	 * Method parseLicense.
	 * @param parser
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return License
	 */
	private License parseLicense(XmlPullParser parser, boolean strict) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		License license = new License();
		for (int i = parser.getAttributeCount() - 1; i >= 0; i--) {
			String name = parser.getAttributeName(i);
			String value = parser.getAttributeValue(i);

			if (name.indexOf(':') >= 0) {
				// just ignore attributes with non-default namespace (for example: xmlns:xsi)
			} else {
				checkUnknownAttribute(parser, name, tagName, strict);
			}
		}
		java.util.Set parsed = new java.util.HashSet();
		while ((strict ? parser.nextTag() : nextTag(parser)) == XmlPullParser.START_TAG) {
			if (checkFieldWithDuplicate(parser, "name", null, parsed)) {
				license.setName(interpolatedTrimmed(parser.nextText(), "name"));
			} else if (checkFieldWithDuplicate(parser, "url", null, parsed)) {
				license.setUrl(interpolatedTrimmed(parser.nextText(), "url"));
			} else if (checkFieldWithDuplicate(parser, "distribution", null, parsed)) {
				license.setDistribution(interpolatedTrimmed(parser.nextText(), "distribution"));
			} else if (checkFieldWithDuplicate(parser, "comments", null, parsed)) {
				license.setComments(interpolatedTrimmed(parser.nextText(), "comments"));
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return license;
	} // -- License parseLicense( XmlPullParser, boolean )

	/**
	 * Method parseMailingList.
	 * @param parser
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return MailingList
	 */
	private MailingList parseMailingList(XmlPullParser parser, boolean strict) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		MailingList mailingList = new MailingList();
		for (int i = parser.getAttributeCount() - 1; i >= 0; i--) {
			String name = parser.getAttributeName(i);
			String value = parser.getAttributeValue(i);

			if (name.indexOf(':') >= 0) {
				// just ignore attributes with non-default namespace (for example: xmlns:xsi)
			} else {
				checkUnknownAttribute(parser, name, tagName, strict);
			}
		}
		java.util.Set parsed = new java.util.HashSet();
		while ((strict ? parser.nextTag() : nextTag(parser)) == XmlPullParser.START_TAG) {
			if (checkFieldWithDuplicate(parser, "name", null, parsed)) {
				mailingList.setName(interpolatedTrimmed(parser.nextText(), "name"));
			} else if (checkFieldWithDuplicate(parser, "subscribe", null, parsed)) {
				mailingList.setSubscribe(interpolatedTrimmed(parser.nextText(), "subscribe"));
			} else if (checkFieldWithDuplicate(parser, "unsubscribe", null, parsed)) {
				mailingList.setUnsubscribe(interpolatedTrimmed(parser.nextText(), "unsubscribe"));
			} else if (checkFieldWithDuplicate(parser, "post", null, parsed)) {
				mailingList.setPost(interpolatedTrimmed(parser.nextText(), "post"));
			} else if (checkFieldWithDuplicate(parser, "archive", null, parsed)) {
				mailingList.setArchive(interpolatedTrimmed(parser.nextText(), "archive"));
			} else if (checkFieldWithDuplicate(parser, "otherArchives", null, parsed)) {
				java.util.List<String> otherArchives = new java.util.ArrayList<String>();
				mailingList.setOtherArchives(otherArchives);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("otherArchive".equals(parser.getName())) {
						otherArchives.add(interpolatedTrimmed(parser.nextText(), "otherArchives"));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return mailingList;
	} // -- MailingList parseMailingList( XmlPullParser, boolean )

	/**
	 * Method parseModel.
	 * @param parser
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return Model
	 */
	private Model parseModel(XmlPullParser parser, boolean strict) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		Model model = new Model();
		for (int i = parser.getAttributeCount() - 1; i >= 0; i--) {
			String name = parser.getAttributeName(i);
			String value = parser.getAttributeValue(i);

			if (name.indexOf(':') >= 0) {
				// just ignore attributes with non-default namespace (for example: xmlns:xsi)
			} else if ("xmlns".equals(name)) {
				// ignore xmlns attribute in root class, which is a reserved attribute name
			} else {
				checkUnknownAttribute(parser, name, tagName, strict);
			}
		}
		java.util.Set parsed = new java.util.HashSet();
		while ((strict ? parser.nextTag() : nextTag(parser)) == XmlPullParser.START_TAG) {
			if (checkFieldWithDuplicate(parser, "modelVersion", null, parsed)) {
				model.setModelVersion(interpolatedTrimmed(parser.nextText(), "modelVersion"));
			} else if (checkFieldWithDuplicate(parser, "parent", null, parsed)) {
				model.setParent(parseParent(parser, strict));
			} else if (checkFieldWithDuplicate(parser, "groupId", null, parsed)) {
				model.setGroupId(interpolatedTrimmed(parser.nextText(), "groupId"));
			} else if (checkFieldWithDuplicate(parser, "artifactId", null, parsed)) {
				model.setArtifactId(interpolatedTrimmed(parser.nextText(), "artifactId"));
			} else if (checkFieldWithDuplicate(parser, "version", null, parsed)) {
				model.setVersion(interpolatedTrimmed(parser.nextText(), "version"));
			} else if (checkFieldWithDuplicate(parser, "packaging", null, parsed)) {
				model.setPackaging(interpolatedTrimmed(parser.nextText(), "packaging"));
			} else if (checkFieldWithDuplicate(parser, "name", null, parsed)) {
				model.setName(interpolatedTrimmed(parser.nextText(), "name"));
			} else if (checkFieldWithDuplicate(parser, "description", null, parsed)) {
				model.setDescription(interpolatedTrimmed(parser.nextText(), "description"));
			} else if (checkFieldWithDuplicate(parser, "url", null, parsed)) {
				model.setUrl(interpolatedTrimmed(parser.nextText(), "url"));
			} else if (checkFieldWithDuplicate(parser, "inceptionYear", null, parsed)) {
				model.setInceptionYear(interpolatedTrimmed(parser.nextText(), "inceptionYear"));
			} else if (checkFieldWithDuplicate(parser, "organization", "organisation", parsed)) {
				model.setOrganization(parseOrganization(parser, strict));
			} else if (checkFieldWithDuplicate(parser, "licenses", null, parsed)) {
				java.util.List<License> licenses = new java.util.ArrayList<License>();
				model.setLicenses(licenses);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("license".equals(parser.getName())) {
						licenses.add(parseLicense(parser, strict));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "developers", null, parsed)) {
				java.util.List<Developer> developers = new java.util.ArrayList<Developer>();
				model.setDevelopers(developers);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("developer".equals(parser.getName())) {
						developers.add(parseDeveloper(parser, strict));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "contributors", null, parsed)) {
				java.util.List<Contributor> contributors = new java.util.ArrayList<Contributor>();
				model.setContributors(contributors);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("contributor".equals(parser.getName())) {
						contributors.add(parseContributor(parser, strict));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "mailingLists", null, parsed)) {
				java.util.List<MailingList> mailingLists = new java.util.ArrayList<MailingList>();
				model.setMailingLists(mailingLists);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("mailingList".equals(parser.getName())) {
						mailingLists.add(parseMailingList(parser, strict));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "prerequisites", null, parsed)) {
				model.setPrerequisites(parsePrerequisites(parser, strict));
			} else if (checkFieldWithDuplicate(parser, "modules", null, parsed)) {
				java.util.List<String> modules = new java.util.ArrayList<String>();
				model.setModules(modules);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("module".equals(parser.getName())) {
						modules.add(interpolatedTrimmed(parser.nextText(), "modules"));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "scm", null, parsed)) {
				model.setScm(parseScm(parser, strict));
			} else if (checkFieldWithDuplicate(parser, "issueManagement", null, parsed)) {
				model.setIssueManagement(parseIssueManagement(parser, strict));
			} else if (checkFieldWithDuplicate(parser, "ciManagement", null, parsed)) {
				model.setCiManagement(parseCiManagement(parser, strict));
			} else if (checkFieldWithDuplicate(parser, "distributionManagement", null, parsed)) {
				model.setDistributionManagement(parseDistributionManagement(parser, strict));
			} else if (checkFieldWithDuplicate(parser, "properties", null, parsed)) {
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					String key = parser.getName();
					String value = parser.nextText().trim();
					model.addProperty(key, value);
				}
			} else if (checkFieldWithDuplicate(parser, "dependencyManagement", null, parsed)) {
				model.setDependencyManagement(parseDependencyManagement(parser, strict));
			} else if (checkFieldWithDuplicate(parser, "dependencies", null, parsed)) {
				java.util.List<Dependency> dependencies = new java.util.ArrayList<Dependency>();
				model.setDependencies(dependencies);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("dependency".equals(parser.getName())) {
						dependencies.add(parseDependency(parser, strict));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "repositories", null, parsed)) {
				java.util.List<Repository> repositories = new java.util.ArrayList<Repository>();
				model.setRepositories(repositories);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("repository".equals(parser.getName())) {
						repositories.add(parseRepository(parser, strict));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "pluginRepositories", null, parsed)) {
				java.util.List<Repository> pluginRepositories = new java.util.ArrayList<Repository>();
				model.setPluginRepositories(pluginRepositories);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("pluginRepository".equals(parser.getName())) {
						pluginRepositories.add(parseRepository(parser, strict));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "build", null, parsed)) {
				model.setBuild(parseBuild(parser, strict));
			} else if (checkFieldWithDuplicate(parser, "reports", null, parsed)) {
				model.setReports(org.codehaus.plexus.util.xml.Xpp3DomBuilder.build(parser, true));
			} else if (checkFieldWithDuplicate(parser, "reporting", null, parsed)) {
				model.setReporting(parseReporting(parser, strict));
			} else if (checkFieldWithDuplicate(parser, "profiles", null, parsed)) {
				java.util.List<Profile> profiles = new java.util.ArrayList<Profile>();
				model.setProfiles(profiles);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("profile".equals(parser.getName())) {
						profiles.add(parseProfile(parser, strict));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return model;
	} // -- Model parseModel( XmlPullParser, boolean )

	/**
	 * Method parseModelBase.
	 * @param parser
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return ModelBase
	 */
	private ModelBase parseModelBase(XmlPullParser parser, boolean strict) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		ModelBase modelBase = new ModelBase();
		for (int i = parser.getAttributeCount() - 1; i >= 0; i--) {
			String name = parser.getAttributeName(i);
			String value = parser.getAttributeValue(i);

			if (name.indexOf(':') >= 0) {
				// just ignore attributes with non-default namespace (for example: xmlns:xsi)
			} else {
				checkUnknownAttribute(parser, name, tagName, strict);
			}
		}
		java.util.Set parsed = new java.util.HashSet();
		while ((strict ? parser.nextTag() : nextTag(parser)) == XmlPullParser.START_TAG) {
			if (checkFieldWithDuplicate(parser, "modules", null, parsed)) {
				java.util.List<String> modules = new java.util.ArrayList<String>();
				modelBase.setModules(modules);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("module".equals(parser.getName())) {
						modules.add(interpolatedTrimmed(parser.nextText(), "modules"));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "distributionManagement", null, parsed)) {
				modelBase.setDistributionManagement(parseDistributionManagement(parser, strict));
			} else if (checkFieldWithDuplicate(parser, "properties", null, parsed)) {
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					String key = parser.getName();
					String value = parser.nextText().trim();
					modelBase.addProperty(key, value);
				}
			} else if (checkFieldWithDuplicate(parser, "dependencyManagement", null, parsed)) {
				modelBase.setDependencyManagement(parseDependencyManagement(parser, strict));
			} else if (checkFieldWithDuplicate(parser, "dependencies", null, parsed)) {
				java.util.List<Dependency> dependencies = new java.util.ArrayList<Dependency>();
				modelBase.setDependencies(dependencies);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("dependency".equals(parser.getName())) {
						dependencies.add(parseDependency(parser, strict));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "repositories", null, parsed)) {
				java.util.List<Repository> repositories = new java.util.ArrayList<Repository>();
				modelBase.setRepositories(repositories);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("repository".equals(parser.getName())) {
						repositories.add(parseRepository(parser, strict));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "pluginRepositories", null, parsed)) {
				java.util.List<Repository> pluginRepositories = new java.util.ArrayList<Repository>();
				modelBase.setPluginRepositories(pluginRepositories);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("pluginRepository".equals(parser.getName())) {
						pluginRepositories.add(parseRepository(parser, strict));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "reports", null, parsed)) {
				modelBase.setReports(org.codehaus.plexus.util.xml.Xpp3DomBuilder.build(parser, true));
			} else if (checkFieldWithDuplicate(parser, "reporting", null, parsed)) {
				modelBase.setReporting(parseReporting(parser, strict));
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return modelBase;
	} // -- ModelBase parseModelBase( XmlPullParser, boolean )

	/**
	 * Method parseNotifier.
	 * @param parser
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return Notifier
	 */
	private Notifier parseNotifier(XmlPullParser parser, boolean strict) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		Notifier notifier = new Notifier();
		for (int i = parser.getAttributeCount() - 1; i >= 0; i--) {
			String name = parser.getAttributeName(i);
			String value = parser.getAttributeValue(i);

			if (name.indexOf(':') >= 0) {
				// just ignore attributes with non-default namespace (for example: xmlns:xsi)
			} else {
				checkUnknownAttribute(parser, name, tagName, strict);
			}
		}
		java.util.Set parsed = new java.util.HashSet();
		while ((strict ? parser.nextTag() : nextTag(parser)) == XmlPullParser.START_TAG) {
			if (checkFieldWithDuplicate(parser, "type", null, parsed)) {
				notifier.setType(interpolatedTrimmed(parser.nextText(), "type"));
			} else if (checkFieldWithDuplicate(parser, "sendOnError", null, parsed)) {
				notifier.setSendOnError(getBooleanValue(interpolatedTrimmed(parser.nextText(), "sendOnError"), "sendOnError", parser, "true"));
			} else if (checkFieldWithDuplicate(parser, "sendOnFailure", null, parsed)) {
				notifier.setSendOnFailure(getBooleanValue(interpolatedTrimmed(parser.nextText(), "sendOnFailure"), "sendOnFailure", parser, "true"));
			} else if (checkFieldWithDuplicate(parser, "sendOnSuccess", null, parsed)) {
				notifier.setSendOnSuccess(getBooleanValue(interpolatedTrimmed(parser.nextText(), "sendOnSuccess"), "sendOnSuccess", parser, "true"));
			} else if (checkFieldWithDuplicate(parser, "sendOnWarning", null, parsed)) {
				notifier.setSendOnWarning(getBooleanValue(interpolatedTrimmed(parser.nextText(), "sendOnWarning"), "sendOnWarning", parser, "true"));
			} else if (checkFieldWithDuplicate(parser, "address", null, parsed)) {
				notifier.setAddress(interpolatedTrimmed(parser.nextText(), "address"));
			} else if (checkFieldWithDuplicate(parser, "configuration", null, parsed)) {
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					String key = parser.getName();
					String value = parser.nextText().trim();
					notifier.addConfiguration(key, value);
				}
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return notifier;
	} // -- Notifier parseNotifier( XmlPullParser, boolean )

	/**
	 * Method parseOrganization.
	 * @param parser
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return Organization
	 */
	private Organization parseOrganization(XmlPullParser parser, boolean strict) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		Organization organization = new Organization();
		for (int i = parser.getAttributeCount() - 1; i >= 0; i--) {
			String name = parser.getAttributeName(i);
			String value = parser.getAttributeValue(i);

			if (name.indexOf(':') >= 0) {
				// just ignore attributes with non-default namespace (for example: xmlns:xsi)
			} else {
				checkUnknownAttribute(parser, name, tagName, strict);
			}
		}
		java.util.Set parsed = new java.util.HashSet();
		while ((strict ? parser.nextTag() : nextTag(parser)) == XmlPullParser.START_TAG) {
			if (checkFieldWithDuplicate(parser, "name", null, parsed)) {
				organization.setName(interpolatedTrimmed(parser.nextText(), "name"));
			} else if (checkFieldWithDuplicate(parser, "url", null, parsed)) {
				organization.setUrl(interpolatedTrimmed(parser.nextText(), "url"));
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return organization;
	} // -- Organization parseOrganization( XmlPullParser, boolean )

	/**
	 * Method parseParent.
	 * @param parser
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return Parent
	 */
	private Parent parseParent(XmlPullParser parser, boolean strict) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		Parent parent = new Parent();
		for (int i = parser.getAttributeCount() - 1; i >= 0; i--) {
			String name = parser.getAttributeName(i);
			String value = parser.getAttributeValue(i);

			if (name.indexOf(':') >= 0) {
				// just ignore attributes with non-default namespace (for example: xmlns:xsi)
			} else {
				checkUnknownAttribute(parser, name, tagName, strict);
			}
		}
		java.util.Set parsed = new java.util.HashSet();
		while ((strict ? parser.nextTag() : nextTag(parser)) == XmlPullParser.START_TAG) {
			if (checkFieldWithDuplicate(parser, "groupId", null, parsed)) {
				parent.setGroupId(interpolatedTrimmed(parser.nextText(), "groupId"));
			} else if (checkFieldWithDuplicate(parser, "artifactId", null, parsed)) {
				parent.setArtifactId(interpolatedTrimmed(parser.nextText(), "artifactId"));
			} else if (checkFieldWithDuplicate(parser, "version", null, parsed)) {
				parent.setVersion(interpolatedTrimmed(parser.nextText(), "version"));
			} else if (checkFieldWithDuplicate(parser, "relativePath", null, parsed)) {
				parent.setRelativePath(interpolatedTrimmed(parser.nextText(), "relativePath"));
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return parent;
	} // -- Parent parseParent( XmlPullParser, boolean )

	/**
	 * Method parsePatternSet.
	 * @param parser
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return PatternSet
	 */
	private PatternSet parsePatternSet(XmlPullParser parser, boolean strict) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		PatternSet patternSet = new PatternSet();
		for (int i = parser.getAttributeCount() - 1; i >= 0; i--) {
			String name = parser.getAttributeName(i);
			String value = parser.getAttributeValue(i);

			if (name.indexOf(':') >= 0) {
				// just ignore attributes with non-default namespace (for example: xmlns:xsi)
			} else {
				checkUnknownAttribute(parser, name, tagName, strict);
			}
		}
		java.util.Set parsed = new java.util.HashSet();
		while ((strict ? parser.nextTag() : nextTag(parser)) == XmlPullParser.START_TAG) {
			if (checkFieldWithDuplicate(parser, "includes", null, parsed)) {
				java.util.List<String> includes = new java.util.ArrayList<String>();
				patternSet.setIncludes(includes);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("include".equals(parser.getName())) {
						includes.add(interpolatedTrimmed(parser.nextText(), "includes"));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "excludes", null, parsed)) {
				java.util.List<String> excludes = new java.util.ArrayList<String>();
				patternSet.setExcludes(excludes);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("exclude".equals(parser.getName())) {
						excludes.add(interpolatedTrimmed(parser.nextText(), "excludes"));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return patternSet;
	} // -- PatternSet parsePatternSet( XmlPullParser, boolean )

	/**
	 * Method parsePlugin.
	 * @param parser
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return Plugin
	 */
	private Plugin parsePlugin(XmlPullParser parser, boolean strict) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		Plugin plugin = new Plugin();
		for (int i = parser.getAttributeCount() - 1; i >= 0; i--) {
			String name = parser.getAttributeName(i);
			String value = parser.getAttributeValue(i);

			if (name.indexOf(':') >= 0) {
				// just ignore attributes with non-default namespace (for example: xmlns:xsi)
			} else {
				checkUnknownAttribute(parser, name, tagName, strict);
			}
		}
		java.util.Set parsed = new java.util.HashSet();
		while ((strict ? parser.nextTag() : nextTag(parser)) == XmlPullParser.START_TAG) {
			if (checkFieldWithDuplicate(parser, "groupId", null, parsed)) {
				plugin.setGroupId(interpolatedTrimmed(parser.nextText(), "groupId"));
			} else if (checkFieldWithDuplicate(parser, "artifactId", null, parsed)) {
				plugin.setArtifactId(interpolatedTrimmed(parser.nextText(), "artifactId"));
			} else if (checkFieldWithDuplicate(parser, "version", null, parsed)) {
				plugin.setVersion(interpolatedTrimmed(parser.nextText(), "version"));
			} else if (checkFieldWithDuplicate(parser, "extensions", null, parsed)) {
				plugin.setExtensions(interpolatedTrimmed(parser.nextText(), "extensions"));
			} else if (checkFieldWithDuplicate(parser, "executions", null, parsed)) {
				java.util.List<PluginExecution> executions = new java.util.ArrayList<PluginExecution>();
				plugin.setExecutions(executions);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("execution".equals(parser.getName())) {
						executions.add(parsePluginExecution(parser, strict));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "dependencies", null, parsed)) {
				java.util.List<Dependency> dependencies = new java.util.ArrayList<Dependency>();
				plugin.setDependencies(dependencies);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("dependency".equals(parser.getName())) {
						dependencies.add(parseDependency(parser, strict));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "goals", null, parsed)) {
				plugin.setGoals(org.codehaus.plexus.util.xml.Xpp3DomBuilder.build(parser, true));
			} else if (checkFieldWithDuplicate(parser, "inherited", null, parsed)) {
				plugin.setInherited(interpolatedTrimmed(parser.nextText(), "inherited"));
			} else if (checkFieldWithDuplicate(parser, "configuration", null, parsed)) {
				plugin.setConfiguration(org.codehaus.plexus.util.xml.Xpp3DomBuilder.build(parser, true));
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return plugin;
	} // -- Plugin parsePlugin( XmlPullParser, boolean )

	/**
	 * Method parsePluginConfiguration.
	 * @param parser
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return PluginConfiguration
	 */
	private PluginConfiguration parsePluginConfiguration(XmlPullParser parser, boolean strict) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		PluginConfiguration pluginConfiguration = new PluginConfiguration();
		for (int i = parser.getAttributeCount() - 1; i >= 0; i--) {
			String name = parser.getAttributeName(i);
			String value = parser.getAttributeValue(i);

			if (name.indexOf(':') >= 0) {
				// just ignore attributes with non-default namespace (for example: xmlns:xsi)
			} else {
				checkUnknownAttribute(parser, name, tagName, strict);
			}
		}
		java.util.Set parsed = new java.util.HashSet();
		while ((strict ? parser.nextTag() : nextTag(parser)) == XmlPullParser.START_TAG) {
			if (checkFieldWithDuplicate(parser, "pluginManagement", null, parsed)) {
				pluginConfiguration.setPluginManagement(parsePluginManagement(parser, strict));
			} else if (checkFieldWithDuplicate(parser, "plugins", null, parsed)) {
				java.util.List<Plugin> plugins = new java.util.ArrayList<Plugin>();
				pluginConfiguration.setPlugins(plugins);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("plugin".equals(parser.getName())) {
						plugins.add(parsePlugin(parser, strict));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return pluginConfiguration;
	} // -- PluginConfiguration parsePluginConfiguration( XmlPullParser, boolean )

	/**
	 * Method parsePluginContainer.
	 * @param parser
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return PluginContainer
	 */
	private PluginContainer parsePluginContainer(XmlPullParser parser, boolean strict) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		PluginContainer pluginContainer = new PluginContainer();
		for (int i = parser.getAttributeCount() - 1; i >= 0; i--) {
			String name = parser.getAttributeName(i);
			String value = parser.getAttributeValue(i);

			if (name.indexOf(':') >= 0) {
				// just ignore attributes with non-default namespace (for example: xmlns:xsi)
			} else {
				checkUnknownAttribute(parser, name, tagName, strict);
			}
		}
		java.util.Set parsed = new java.util.HashSet();
		while ((strict ? parser.nextTag() : nextTag(parser)) == XmlPullParser.START_TAG) {
			if (checkFieldWithDuplicate(parser, "plugins", null, parsed)) {
				java.util.List<Plugin> plugins = new java.util.ArrayList<Plugin>();
				pluginContainer.setPlugins(plugins);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("plugin".equals(parser.getName())) {
						plugins.add(parsePlugin(parser, strict));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return pluginContainer;
	} // -- PluginContainer parsePluginContainer( XmlPullParser, boolean )

	/**
	 * Method parsePluginExecution.
	 * @param parser
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return PluginExecution
	 */
	private PluginExecution parsePluginExecution(XmlPullParser parser, boolean strict) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		PluginExecution pluginExecution = new PluginExecution();
		for (int i = parser.getAttributeCount() - 1; i >= 0; i--) {
			String name = parser.getAttributeName(i);
			String value = parser.getAttributeValue(i);

			if (name.indexOf(':') >= 0) {
				// just ignore attributes with non-default namespace (for example: xmlns:xsi)
			} else {
				checkUnknownAttribute(parser, name, tagName, strict);
			}
		}
		java.util.Set parsed = new java.util.HashSet();
		while ((strict ? parser.nextTag() : nextTag(parser)) == XmlPullParser.START_TAG) {
			if (checkFieldWithDuplicate(parser, "id", null, parsed)) {
				pluginExecution.setId(interpolatedTrimmed(parser.nextText(), "id"));
			} else if (checkFieldWithDuplicate(parser, "phase", null, parsed)) {
				pluginExecution.setPhase(interpolatedTrimmed(parser.nextText(), "phase"));
			} else if (checkFieldWithDuplicate(parser, "goals", null, parsed)) {
				java.util.List<String> goals = new java.util.ArrayList<String>();
				pluginExecution.setGoals(goals);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("goal".equals(parser.getName())) {
						goals.add(interpolatedTrimmed(parser.nextText(), "goals"));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "inherited", null, parsed)) {
				pluginExecution.setInherited(interpolatedTrimmed(parser.nextText(), "inherited"));
			} else if (checkFieldWithDuplicate(parser, "configuration", null, parsed)) {
				pluginExecution.setConfiguration(org.codehaus.plexus.util.xml.Xpp3DomBuilder.build(parser, true));
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return pluginExecution;
	} // -- PluginExecution parsePluginExecution( XmlPullParser, boolean )

	/**
	 * Method parsePluginManagement.
	 * @param parser
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return PluginManagement
	 */
	private PluginManagement parsePluginManagement(XmlPullParser parser, boolean strict) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		PluginManagement pluginManagement = new PluginManagement();
		for (int i = parser.getAttributeCount() - 1; i >= 0; i--) {
			String name = parser.getAttributeName(i);
			String value = parser.getAttributeValue(i);

			if (name.indexOf(':') >= 0) {
				// just ignore attributes with non-default namespace (for example: xmlns:xsi)
			} else {
				checkUnknownAttribute(parser, name, tagName, strict);
			}
		}
		java.util.Set parsed = new java.util.HashSet();
		while ((strict ? parser.nextTag() : nextTag(parser)) == XmlPullParser.START_TAG) {
			if (checkFieldWithDuplicate(parser, "plugins", null, parsed)) {
				java.util.List<Plugin> plugins = new java.util.ArrayList<Plugin>();
				pluginManagement.setPlugins(plugins);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("plugin".equals(parser.getName())) {
						plugins.add(parsePlugin(parser, strict));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return pluginManagement;
	} // -- PluginManagement parsePluginManagement( XmlPullParser, boolean )

	/**
	 * Method parsePrerequisites.
	 * @param parser
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return Prerequisites
	 */
	private Prerequisites parsePrerequisites(XmlPullParser parser, boolean strict) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		Prerequisites prerequisites = new Prerequisites();
		for (int i = parser.getAttributeCount() - 1; i >= 0; i--) {
			String name = parser.getAttributeName(i);
			String value = parser.getAttributeValue(i);

			if (name.indexOf(':') >= 0) {
				// just ignore attributes with non-default namespace (for example: xmlns:xsi)
			} else {
				checkUnknownAttribute(parser, name, tagName, strict);
			}
		}
		java.util.Set parsed = new java.util.HashSet();
		while ((strict ? parser.nextTag() : nextTag(parser)) == XmlPullParser.START_TAG) {
			if (checkFieldWithDuplicate(parser, "maven", null, parsed)) {
				prerequisites.setMaven(interpolatedTrimmed(parser.nextText(), "maven"));
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return prerequisites;
	} // -- Prerequisites parsePrerequisites( XmlPullParser, boolean )

	/**
	 * Method parseProfile.
	 * @param parser
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return Profile
	 */
	private Profile parseProfile(XmlPullParser parser, boolean strict) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		Profile profile = new Profile();
		for (int i = parser.getAttributeCount() - 1; i >= 0; i--) {
			String name = parser.getAttributeName(i);
			String value = parser.getAttributeValue(i);

			if (name.indexOf(':') >= 0) {
				// just ignore attributes with non-default namespace (for example: xmlns:xsi)
			} else {
				checkUnknownAttribute(parser, name, tagName, strict);
			}
		}
		java.util.Set parsed = new java.util.HashSet();
		while ((strict ? parser.nextTag() : nextTag(parser)) == XmlPullParser.START_TAG) {
			if (checkFieldWithDuplicate(parser, "id", null, parsed)) {
				profile.setId(interpolatedTrimmed(parser.nextText(), "id"));
			} else if (checkFieldWithDuplicate(parser, "activation", null, parsed)) {
				profile.setActivation(parseActivation(parser, strict));
			} else if (checkFieldWithDuplicate(parser, "build", null, parsed)) {
				profile.setBuild(parseBuildBase(parser, strict));
			} else if (checkFieldWithDuplicate(parser, "modules", null, parsed)) {
				java.util.List<String> modules = new java.util.ArrayList<String>();
				profile.setModules(modules);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("module".equals(parser.getName())) {
						modules.add(interpolatedTrimmed(parser.nextText(), "modules"));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "distributionManagement", null, parsed)) {
				profile.setDistributionManagement(parseDistributionManagement(parser, strict));
			} else if (checkFieldWithDuplicate(parser, "properties", null, parsed)) {
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					String key = parser.getName();
					String value = parser.nextText().trim();
					profile.addProperty(key, value);
				}
			} else if (checkFieldWithDuplicate(parser, "dependencyManagement", null, parsed)) {
				profile.setDependencyManagement(parseDependencyManagement(parser, strict));
			} else if (checkFieldWithDuplicate(parser, "dependencies", null, parsed)) {
				java.util.List<Dependency> dependencies = new java.util.ArrayList<Dependency>();
				profile.setDependencies(dependencies);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("dependency".equals(parser.getName())) {
						dependencies.add(parseDependency(parser, strict));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "repositories", null, parsed)) {
				java.util.List<Repository> repositories = new java.util.ArrayList<Repository>();
				profile.setRepositories(repositories);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("repository".equals(parser.getName())) {
						repositories.add(parseRepository(parser, strict));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "pluginRepositories", null, parsed)) {
				java.util.List<Repository> pluginRepositories = new java.util.ArrayList<Repository>();
				profile.setPluginRepositories(pluginRepositories);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("pluginRepository".equals(parser.getName())) {
						pluginRepositories.add(parseRepository(parser, strict));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "reports", null, parsed)) {
				profile.setReports(org.codehaus.plexus.util.xml.Xpp3DomBuilder.build(parser, true));
			} else if (checkFieldWithDuplicate(parser, "reporting", null, parsed)) {
				profile.setReporting(parseReporting(parser, strict));
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return profile;
	} // -- Profile parseProfile( XmlPullParser, boolean )

	/**
	 * Method parseRelocation.
	 * @param parser
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return Relocation
	 */
	private Relocation parseRelocation(XmlPullParser parser, boolean strict) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		Relocation relocation = new Relocation();
		for (int i = parser.getAttributeCount() - 1; i >= 0; i--) {
			String name = parser.getAttributeName(i);
			String value = parser.getAttributeValue(i);

			if (name.indexOf(':') >= 0) {
				// just ignore attributes with non-default namespace (for example: xmlns:xsi)
			} else {
				checkUnknownAttribute(parser, name, tagName, strict);
			}
		}
		java.util.Set parsed = new java.util.HashSet();
		while ((strict ? parser.nextTag() : nextTag(parser)) == XmlPullParser.START_TAG) {
			if (checkFieldWithDuplicate(parser, "groupId", null, parsed)) {
				relocation.setGroupId(interpolatedTrimmed(parser.nextText(), "groupId"));
			} else if (checkFieldWithDuplicate(parser, "artifactId", null, parsed)) {
				relocation.setArtifactId(interpolatedTrimmed(parser.nextText(), "artifactId"));
			} else if (checkFieldWithDuplicate(parser, "version", null, parsed)) {
				relocation.setVersion(interpolatedTrimmed(parser.nextText(), "version"));
			} else if (checkFieldWithDuplicate(parser, "message", null, parsed)) {
				relocation.setMessage(interpolatedTrimmed(parser.nextText(), "message"));
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return relocation;
	} // -- Relocation parseRelocation( XmlPullParser, boolean )

	/**
	 * Method parseReportPlugin.
	 * @param parser
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return ReportPlugin
	 */
	private ReportPlugin parseReportPlugin(XmlPullParser parser, boolean strict) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		ReportPlugin reportPlugin = new ReportPlugin();
		for (int i = parser.getAttributeCount() - 1; i >= 0; i--) {
			String name = parser.getAttributeName(i);
			String value = parser.getAttributeValue(i);

			if (name.indexOf(':') >= 0) {
				// just ignore attributes with non-default namespace (for example: xmlns:xsi)
			} else {
				checkUnknownAttribute(parser, name, tagName, strict);
			}
		}
		java.util.Set parsed = new java.util.HashSet();
		while ((strict ? parser.nextTag() : nextTag(parser)) == XmlPullParser.START_TAG) {
			if (checkFieldWithDuplicate(parser, "groupId", null, parsed)) {
				reportPlugin.setGroupId(interpolatedTrimmed(parser.nextText(), "groupId"));
			} else if (checkFieldWithDuplicate(parser, "artifactId", null, parsed)) {
				reportPlugin.setArtifactId(interpolatedTrimmed(parser.nextText(), "artifactId"));
			} else if (checkFieldWithDuplicate(parser, "version", null, parsed)) {
				reportPlugin.setVersion(interpolatedTrimmed(parser.nextText(), "version"));
			} else if (checkFieldWithDuplicate(parser, "reportSets", null, parsed)) {
				java.util.List<ReportSet> reportSets = new java.util.ArrayList<ReportSet>();
				reportPlugin.setReportSets(reportSets);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("reportSet".equals(parser.getName())) {
						reportSets.add(parseReportSet(parser, strict));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "inherited", null, parsed)) {
				reportPlugin.setInherited(interpolatedTrimmed(parser.nextText(), "inherited"));
			} else if (checkFieldWithDuplicate(parser, "configuration", null, parsed)) {
				reportPlugin.setConfiguration(org.codehaus.plexus.util.xml.Xpp3DomBuilder.build(parser, true));
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return reportPlugin;
	} // -- ReportPlugin parseReportPlugin( XmlPullParser, boolean )

	/**
	 * Method parseReportSet.
	 * @param parser
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return ReportSet
	 */
	private ReportSet parseReportSet(XmlPullParser parser, boolean strict) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		ReportSet reportSet = new ReportSet();
		for (int i = parser.getAttributeCount() - 1; i >= 0; i--) {
			String name = parser.getAttributeName(i);
			String value = parser.getAttributeValue(i);

			if (name.indexOf(':') >= 0) {
				// just ignore attributes with non-default namespace (for example: xmlns:xsi)
			} else {
				checkUnknownAttribute(parser, name, tagName, strict);
			}
		}
		java.util.Set parsed = new java.util.HashSet();
		while ((strict ? parser.nextTag() : nextTag(parser)) == XmlPullParser.START_TAG) {
			if (checkFieldWithDuplicate(parser, "id", null, parsed)) {
				reportSet.setId(interpolatedTrimmed(parser.nextText(), "id"));
			} else if (checkFieldWithDuplicate(parser, "reports", null, parsed)) {
				java.util.List<String> reports = new java.util.ArrayList<String>();
				reportSet.setReports(reports);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("report".equals(parser.getName())) {
						reports.add(interpolatedTrimmed(parser.nextText(), "reports"));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "inherited", null, parsed)) {
				reportSet.setInherited(interpolatedTrimmed(parser.nextText(), "inherited"));
			} else if (checkFieldWithDuplicate(parser, "configuration", null, parsed)) {
				reportSet.setConfiguration(org.codehaus.plexus.util.xml.Xpp3DomBuilder.build(parser, true));
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return reportSet;
	} // -- ReportSet parseReportSet( XmlPullParser, boolean )

	/**
	 * Method parseReporting.
	 * @param parser
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return Reporting
	 */
	private Reporting parseReporting(XmlPullParser parser, boolean strict) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		Reporting reporting = new Reporting();
		for (int i = parser.getAttributeCount() - 1; i >= 0; i--) {
			String name = parser.getAttributeName(i);
			String value = parser.getAttributeValue(i);

			if (name.indexOf(':') >= 0) {
				// just ignore attributes with non-default namespace (for example: xmlns:xsi)
			} else {
				checkUnknownAttribute(parser, name, tagName, strict);
			}
		}
		java.util.Set parsed = new java.util.HashSet();
		while ((strict ? parser.nextTag() : nextTag(parser)) == XmlPullParser.START_TAG) {
			if (checkFieldWithDuplicate(parser, "excludeDefaults", null, parsed)) {
				reporting.setExcludeDefaults(interpolatedTrimmed(parser.nextText(), "excludeDefaults"));
			} else if (checkFieldWithDuplicate(parser, "outputDirectory", null, parsed)) {
				reporting.setOutputDirectory(interpolatedTrimmed(parser.nextText(), "outputDirectory"));
			} else if (checkFieldWithDuplicate(parser, "plugins", null, parsed)) {
				java.util.List<ReportPlugin> plugins = new java.util.ArrayList<ReportPlugin>();
				reporting.setPlugins(plugins);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("plugin".equals(parser.getName())) {
						plugins.add(parseReportPlugin(parser, strict));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return reporting;
	} // -- Reporting parseReporting( XmlPullParser, boolean )

	/**
	 * Method parseRepository.
	 * @param parser
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return Repository
	 */
	private Repository parseRepository(XmlPullParser parser, boolean strict) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		Repository repository = new Repository();
		for (int i = parser.getAttributeCount() - 1; i >= 0; i--) {
			String name = parser.getAttributeName(i);
			String value = parser.getAttributeValue(i);

			if (name.indexOf(':') >= 0) {
				// just ignore attributes with non-default namespace (for example: xmlns:xsi)
			} else {
				checkUnknownAttribute(parser, name, tagName, strict);
			}
		}
		java.util.Set parsed = new java.util.HashSet();
		while ((strict ? parser.nextTag() : nextTag(parser)) == XmlPullParser.START_TAG) {
			if (checkFieldWithDuplicate(parser, "releases", null, parsed)) {
				repository.setReleases(parseRepositoryPolicy(parser, strict));
			} else if (checkFieldWithDuplicate(parser, "snapshots", null, parsed)) {
				repository.setSnapshots(parseRepositoryPolicy(parser, strict));
			} else if (checkFieldWithDuplicate(parser, "id", null, parsed)) {
				repository.setId(interpolatedTrimmed(parser.nextText(), "id"));
			} else if (checkFieldWithDuplicate(parser, "name", null, parsed)) {
				repository.setName(interpolatedTrimmed(parser.nextText(), "name"));
			} else if (checkFieldWithDuplicate(parser, "url", null, parsed)) {
				repository.setUrl(interpolatedTrimmed(parser.nextText(), "url"));
			} else if (checkFieldWithDuplicate(parser, "layout", null, parsed)) {
				repository.setLayout(interpolatedTrimmed(parser.nextText(), "layout"));
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return repository;
	} // -- Repository parseRepository( XmlPullParser, boolean )

	/**
	 * Method parseRepositoryBase.
	 * @param parser
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return RepositoryBase
	 */
	private RepositoryBase parseRepositoryBase(XmlPullParser parser, boolean strict) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		RepositoryBase repositoryBase = new RepositoryBase();
		for (int i = parser.getAttributeCount() - 1; i >= 0; i--) {
			String name = parser.getAttributeName(i);
			String value = parser.getAttributeValue(i);

			if (name.indexOf(':') >= 0) {
				// just ignore attributes with non-default namespace (for example: xmlns:xsi)
			} else {
				checkUnknownAttribute(parser, name, tagName, strict);
			}
		}
		java.util.Set parsed = new java.util.HashSet();
		while ((strict ? parser.nextTag() : nextTag(parser)) == XmlPullParser.START_TAG) {
			if (checkFieldWithDuplicate(parser, "id", null, parsed)) {
				repositoryBase.setId(interpolatedTrimmed(parser.nextText(), "id"));
			} else if (checkFieldWithDuplicate(parser, "name", null, parsed)) {
				repositoryBase.setName(interpolatedTrimmed(parser.nextText(), "name"));
			} else if (checkFieldWithDuplicate(parser, "url", null, parsed)) {
				repositoryBase.setUrl(interpolatedTrimmed(parser.nextText(), "url"));
			} else if (checkFieldWithDuplicate(parser, "layout", null, parsed)) {
				repositoryBase.setLayout(interpolatedTrimmed(parser.nextText(), "layout"));
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return repositoryBase;
	} // -- RepositoryBase parseRepositoryBase( XmlPullParser, boolean )

	/**
	 * Method parseRepositoryPolicy.
	 * @param parser
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return RepositoryPolicy
	 */
	private RepositoryPolicy parseRepositoryPolicy(XmlPullParser parser, boolean strict) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		RepositoryPolicy repositoryPolicy = new RepositoryPolicy();
		for (int i = parser.getAttributeCount() - 1; i >= 0; i--) {
			String name = parser.getAttributeName(i);
			String value = parser.getAttributeValue(i);

			if (name.indexOf(':') >= 0) {
				// just ignore attributes with non-default namespace (for example: xmlns:xsi)
			} else {
				checkUnknownAttribute(parser, name, tagName, strict);
			}
		}
		java.util.Set parsed = new java.util.HashSet();
		while ((strict ? parser.nextTag() : nextTag(parser)) == XmlPullParser.START_TAG) {
			if (checkFieldWithDuplicate(parser, "enabled", null, parsed)) {
				repositoryPolicy.setEnabled(interpolatedTrimmed(parser.nextText(), "enabled"));
			} else if (checkFieldWithDuplicate(parser, "updatePolicy", null, parsed)) {
				repositoryPolicy.setUpdatePolicy(interpolatedTrimmed(parser.nextText(), "updatePolicy"));
			} else if (checkFieldWithDuplicate(parser, "checksumPolicy", null, parsed)) {
				repositoryPolicy.setChecksumPolicy(interpolatedTrimmed(parser.nextText(), "checksumPolicy"));
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return repositoryPolicy;
	} // -- RepositoryPolicy parseRepositoryPolicy( XmlPullParser, boolean )

	/**
	 * Method parseResource.
	 * @param parser
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return Resource
	 */
	private Resource parseResource(XmlPullParser parser, boolean strict) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		Resource resource = new Resource();
		for (int i = parser.getAttributeCount() - 1; i >= 0; i--) {
			String name = parser.getAttributeName(i);
			String value = parser.getAttributeValue(i);

			if (name.indexOf(':') >= 0) {
				// just ignore attributes with non-default namespace (for example: xmlns:xsi)
			} else {
				checkUnknownAttribute(parser, name, tagName, strict);
			}
		}
		java.util.Set parsed = new java.util.HashSet();
		while ((strict ? parser.nextTag() : nextTag(parser)) == XmlPullParser.START_TAG) {
			if (checkFieldWithDuplicate(parser, "targetPath", null, parsed)) {
				resource.setTargetPath(interpolatedTrimmed(parser.nextText(), "targetPath"));
			} else if (checkFieldWithDuplicate(parser, "filtering", null, parsed)) {
				resource.setFiltering(interpolatedTrimmed(parser.nextText(), "filtering"));
			} else if (checkFieldWithDuplicate(parser, "directory", null, parsed)) {
				resource.setDirectory(interpolatedTrimmed(parser.nextText(), "directory"));
			} else if (checkFieldWithDuplicate(parser, "includes", null, parsed)) {
				java.util.List<String> includes = new java.util.ArrayList<String>();
				resource.setIncludes(includes);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("include".equals(parser.getName())) {
						includes.add(interpolatedTrimmed(parser.nextText(), "includes"));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "excludes", null, parsed)) {
				java.util.List<String> excludes = new java.util.ArrayList<String>();
				resource.setExcludes(excludes);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("exclude".equals(parser.getName())) {
						excludes.add(interpolatedTrimmed(parser.nextText(), "excludes"));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return resource;
	} // -- Resource parseResource( XmlPullParser, boolean )

	/**
	 * Method parseScm.
	 * @param parser
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return Scm
	 */
	private Scm parseScm(XmlPullParser parser, boolean strict) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		Scm scm = new Scm();
		for (int i = parser.getAttributeCount() - 1; i >= 0; i--) {
			String name = parser.getAttributeName(i);
			String value = parser.getAttributeValue(i);

			if (name.indexOf(':') >= 0) {
				// just ignore attributes with non-default namespace (for example: xmlns:xsi)
			} else {
				checkUnknownAttribute(parser, name, tagName, strict);
			}
		}
		java.util.Set parsed = new java.util.HashSet();
		while ((strict ? parser.nextTag() : nextTag(parser)) == XmlPullParser.START_TAG) {
			if (checkFieldWithDuplicate(parser, "connection", null, parsed)) {
				scm.setConnection(interpolatedTrimmed(parser.nextText(), "connection"));
			} else if (checkFieldWithDuplicate(parser, "developerConnection", null, parsed)) {
				scm.setDeveloperConnection(interpolatedTrimmed(parser.nextText(), "developerConnection"));
			} else if (checkFieldWithDuplicate(parser, "tag", null, parsed)) {
				scm.setTag(interpolatedTrimmed(parser.nextText(), "tag"));
			} else if (checkFieldWithDuplicate(parser, "url", null, parsed)) {
				scm.setUrl(interpolatedTrimmed(parser.nextText(), "url"));
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return scm;
	} // -- Scm parseScm( XmlPullParser, boolean )

	/**
	 * Method parseSite.
	 * @param parser
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return Site
	 */
	private Site parseSite(XmlPullParser parser, boolean strict) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		Site site = new Site();
		for (int i = parser.getAttributeCount() - 1; i >= 0; i--) {
			String name = parser.getAttributeName(i);
			String value = parser.getAttributeValue(i);

			if (name.indexOf(':') >= 0) {
				// just ignore attributes with non-default namespace (for example: xmlns:xsi)
			} else {
				checkUnknownAttribute(parser, name, tagName, strict);
			}
		}
		java.util.Set parsed = new java.util.HashSet();
		while ((strict ? parser.nextTag() : nextTag(parser)) == XmlPullParser.START_TAG) {
			if (checkFieldWithDuplicate(parser, "id", null, parsed)) {
				site.setId(interpolatedTrimmed(parser.nextText(), "id"));
			} else if (checkFieldWithDuplicate(parser, "name", null, parsed)) {
				site.setName(interpolatedTrimmed(parser.nextText(), "name"));
			} else if (checkFieldWithDuplicate(parser, "url", null, parsed)) {
				site.setUrl(interpolatedTrimmed(parser.nextText(), "url"));
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return site;
	} // -- Site parseSite( XmlPullParser, boolean )

	/**
	 * Method read.
	 * @param parser
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return Model
	 */
	private Model read(XmlPullParser parser, boolean strict) throws IOException, XmlPullParserException {
		Model model = null;
		int eventType = parser.getEventType();
		boolean parsed = false;
		while (eventType != XmlPullParser.END_DOCUMENT) {
			if (eventType == XmlPullParser.START_TAG) {
				if (strict && !"project".equals(parser.getName())) {
					throw new XmlPullParserException("Expected root element 'project' but found '" + parser.getName() + "'", parser, null);
				} else if (parsed) {
					// fallback, already expected a XmlPullParserException due to invalid XML
					throw new XmlPullParserException("Duplicated tag: 'project'", parser, null);
				}
				model = parseModel(parser, strict);
				model.setModelEncoding(parser.getInputEncoding());
				parsed = true;
			}
			eventType = parser.next();
		}
		if (parsed) {
			return model;
		}
		throw new XmlPullParserException("Expected root element 'project' but found no element at all: invalid XML document", parser, null);
	} // -- Model read( XmlPullParser, boolean )

	/**
	 * Sets the state of the "add default entities" flag.
	 * @param addDefaultEntities
	 */
	public void setAddDefaultEntities(boolean addDefaultEntities) {
		this.addDefaultEntities = addDefaultEntities;
	} // -- void setAddDefaultEntities( boolean )

	public static interface ContentTransformer {
		/**
		 * Interpolate the value read from the xpp3 document
		 * @param source The source value
		 * @param fieldName A description of the field being interpolated. The implementation may use this to log stuff.
		 * @return The interpolated value.
		 */
		String transform(String source, String fieldName);
	}

}
