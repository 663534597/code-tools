
package com.yijia.codegen.models.maven.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.text.DateFormat;
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
import com.yijia.codegen.models.maven.InputLocation;
import com.yijia.codegen.models.maven.InputSource;
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
public class MavenXpp3ReaderEx {

	private boolean addDefaultEntities = true;

	/**
	 * Field contentTransformer.
	 */
	public final ContentTransformer contentTransformer;

	public MavenXpp3ReaderEx() {
		this(new ContentTransformer() {
			public String transform(String source, String fieldName) {
				return source;
			}
		});
	} // -- com.example.io.io.xpp3.MavenXpp3ReaderEx()

	public MavenXpp3ReaderEx(ContentTransformer contentTransformer) {
		this.contentTransformer = contentTransformer;
	} // -- com.example.io.io.xpp3.MavenXpp3ReaderEx(ContentTransformer)

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
	} // -- boolean checkFieldWithDuplicate( XmlPullParser, String, String, java.util.Set )

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
	} // -- void checkUnknownAttribute( XmlPullParser, String, String, boolean )

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
	} // -- void checkUnknownElement( XmlPullParser, boolean )

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
	private java.util.Date getDateValue(String s, String attribute, XmlPullParser parser) throws XmlPullParserException {
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
	private java.util.Date getDateValue(String s, String attribute, String dateFormat, XmlPullParser parser) throws XmlPullParserException {
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
					DateFormat dateParser = new java.text.SimpleDateFormat(effectiveDateFormat, java.util.Locale.US);
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
	 * @param source
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return Model
	 */
	public Model read(Reader reader, boolean strict, InputSource source) throws IOException, XmlPullParserException {
		XmlPullParser parser = addDefaultEntities ? new MXParser(EntityReplacementMap.defaultEntityReplacementMap) : new MXParser();

		parser.setInput(reader);

		return read(parser, strict, source);
	} // -- Model read( Reader, boolean, InputSource )

	/**
	 * Method read.
	 * @param in
	 * @param source
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return Model
	 */
	public Model read(InputStream in, boolean strict, InputSource source) throws IOException, XmlPullParserException {
		return read(ReaderFactory.newXmlReader(in), strict, source);
	} // -- Model read( InputStream, boolean, InputSource )

	/**
	 * Method parseActivation.
	 * @param parser
	 * @param source
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return Activation
	 */
	private Activation parseActivation(XmlPullParser parser, boolean strict, InputSource source) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		Activation activation = new Activation();
		InputLocation _location;
		_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
		activation.setLocation("", _location);
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
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				activation.setLocation("activeByDefault", _location);
				activation.setActiveByDefault(getBooleanValue(interpolatedTrimmed(parser.nextText(), "activeByDefault"), "activeByDefault", parser, "false"));
			} else if (checkFieldWithDuplicate(parser, "jdk", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				activation.setLocation("jdk", _location);
				activation.setJdk(interpolatedTrimmed(parser.nextText(), "jdk"));
			} else if (checkFieldWithDuplicate(parser, "os", null, parsed)) {
				activation.setOs(parseActivationOS(parser, strict, source));
			} else if (checkFieldWithDuplicate(parser, "property", null, parsed)) {
				activation.setProperty(parseActivationProperty(parser, strict, source));
			} else if (checkFieldWithDuplicate(parser, "file", null, parsed)) {
				activation.setFile(parseActivationFile(parser, strict, source));
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return activation;
	} // -- Activation parseActivation( XmlPullParser, boolean, InputSource )

	/**
	 * Method parseActivationFile.
	 * @param parser
	 * @param source
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return ActivationFile
	 */
	private ActivationFile parseActivationFile(XmlPullParser parser, boolean strict, InputSource source) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		ActivationFile activationFile = new ActivationFile();
		InputLocation _location;
		_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
		activationFile.setLocation("", _location);
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
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				activationFile.setLocation("missing", _location);
				activationFile.setMissing(interpolatedTrimmed(parser.nextText(), "missing"));
			} else if (checkFieldWithDuplicate(parser, "exists", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				activationFile.setLocation("exists", _location);
				activationFile.setExists(interpolatedTrimmed(parser.nextText(), "exists"));
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return activationFile;
	} // -- ActivationFile parseActivationFile( XmlPullParser, boolean, InputSource )

	/**
	 * Method parseActivationOS.
	 * @param parser
	 * @param source
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return ActivationOS
	 */
	private ActivationOS parseActivationOS(XmlPullParser parser, boolean strict, InputSource source) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		ActivationOS activationOS = new ActivationOS();
		InputLocation _location;
		_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
		activationOS.setLocation("", _location);
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
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				activationOS.setLocation("name", _location);
				activationOS.setName(interpolatedTrimmed(parser.nextText(), "name"));
			} else if (checkFieldWithDuplicate(parser, "family", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				activationOS.setLocation("family", _location);
				activationOS.setFamily(interpolatedTrimmed(parser.nextText(), "family"));
			} else if (checkFieldWithDuplicate(parser, "arch", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				activationOS.setLocation("arch", _location);
				activationOS.setArch(interpolatedTrimmed(parser.nextText(), "arch"));
			} else if (checkFieldWithDuplicate(parser, "version", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				activationOS.setLocation("version", _location);
				activationOS.setVersion(interpolatedTrimmed(parser.nextText(), "version"));
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return activationOS;
	} // -- ActivationOS parseActivationOS( XmlPullParser, boolean, InputSource )

	/**
	 * Method parseActivationProperty.
	 * @param parser
	 * @param source
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return ActivationProperty
	 */
	private ActivationProperty parseActivationProperty(XmlPullParser parser, boolean strict, InputSource source) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		ActivationProperty activationProperty = new ActivationProperty();
		InputLocation _location;
		_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
		activationProperty.setLocation("", _location);
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
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				activationProperty.setLocation("name", _location);
				activationProperty.setName(interpolatedTrimmed(parser.nextText(), "name"));
			} else if (checkFieldWithDuplicate(parser, "value", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				activationProperty.setLocation("value", _location);
				activationProperty.setValue(interpolatedTrimmed(parser.nextText(), "value"));
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return activationProperty;
	} // -- ActivationProperty parseActivationProperty( XmlPullParser, boolean, InputSource )

	/**
	 * Method parseBuild.
	 * @param parser
	 * @param source
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return Build
	 */
	private Build parseBuild(XmlPullParser parser, boolean strict, InputSource source) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		Build build = new Build();
		InputLocation _location;
		_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
		build.setLocation("", _location);
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
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				build.setLocation("sourceDirectory", _location);
				build.setSourceDirectory(interpolatedTrimmed(parser.nextText(), "sourceDirectory"));
			} else if (checkFieldWithDuplicate(parser, "scriptSourceDirectory", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				build.setLocation("scriptSourceDirectory", _location);
				build.setScriptSourceDirectory(interpolatedTrimmed(parser.nextText(), "scriptSourceDirectory"));
			} else if (checkFieldWithDuplicate(parser, "testSourceDirectory", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				build.setLocation("testSourceDirectory", _location);
				build.setTestSourceDirectory(interpolatedTrimmed(parser.nextText(), "testSourceDirectory"));
			} else if (checkFieldWithDuplicate(parser, "outputDirectory", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				build.setLocation("outputDirectory", _location);
				build.setOutputDirectory(interpolatedTrimmed(parser.nextText(), "outputDirectory"));
			} else if (checkFieldWithDuplicate(parser, "testOutputDirectory", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				build.setLocation("testOutputDirectory", _location);
				build.setTestOutputDirectory(interpolatedTrimmed(parser.nextText(), "testOutputDirectory"));
			} else if (checkFieldWithDuplicate(parser, "extensions", null, parsed)) {
				java.util.List<Extension> extensions = new java.util.ArrayList<Extension>();
				build.setExtensions(extensions);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("extension".equals(parser.getName())) {
						extensions.add(parseExtension(parser, strict, source));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "defaultGoal", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				build.setLocation("defaultGoal", _location);
				build.setDefaultGoal(interpolatedTrimmed(parser.nextText(), "defaultGoal"));
			} else if (checkFieldWithDuplicate(parser, "resources", null, parsed)) {
				java.util.List<Resource> resources = new java.util.ArrayList<Resource>();
				build.setResources(resources);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("resource".equals(parser.getName())) {
						resources.add(parseResource(parser, strict, source));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "testResources", null, parsed)) {
				java.util.List<Resource> testResources = new java.util.ArrayList<Resource>();
				build.setTestResources(testResources);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("testResource".equals(parser.getName())) {
						testResources.add(parseResource(parser, strict, source));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "directory", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				build.setLocation("directory", _location);
				build.setDirectory(interpolatedTrimmed(parser.nextText(), "directory"));
			} else if (checkFieldWithDuplicate(parser, "finalName", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				build.setLocation("finalName", _location);
				build.setFinalName(interpolatedTrimmed(parser.nextText(), "finalName"));
			} else if (checkFieldWithDuplicate(parser, "filters", null, parsed)) {
				java.util.List<String> filters = new java.util.ArrayList<String>();
				build.setFilters(filters);
				InputLocation _locations;
				_locations = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				build.setLocation("filters", _locations);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("filter".equals(parser.getName())) {
						_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
						_locations.setLocation(Integer.valueOf(filters.size()), _location);
						filters.add(interpolatedTrimmed(parser.nextText(), "filters"));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "pluginManagement", null, parsed)) {
				build.setPluginManagement(parsePluginManagement(parser, strict, source));
			} else if (checkFieldWithDuplicate(parser, "plugins", null, parsed)) {
				java.util.List<Plugin> plugins = new java.util.ArrayList<Plugin>();
				build.setPlugins(plugins);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("plugin".equals(parser.getName())) {
						plugins.add(parsePlugin(parser, strict, source));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return build;
	} // -- Build parseBuild( XmlPullParser, boolean, InputSource )

	/**
	 * Method parseBuildBase.
	 * @param parser
	 * @param source
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return BuildBase
	 */
	private BuildBase parseBuildBase(XmlPullParser parser, boolean strict, InputSource source) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		BuildBase buildBase = new BuildBase();
		InputLocation _location;
		_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
		buildBase.setLocation("", _location);
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
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				buildBase.setLocation("defaultGoal", _location);
				buildBase.setDefaultGoal(interpolatedTrimmed(parser.nextText(), "defaultGoal"));
			} else if (checkFieldWithDuplicate(parser, "resources", null, parsed)) {
				java.util.List<Resource> resources = new java.util.ArrayList<Resource>();
				buildBase.setResources(resources);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("resource".equals(parser.getName())) {
						resources.add(parseResource(parser, strict, source));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "testResources", null, parsed)) {
				java.util.List<Resource> testResources = new java.util.ArrayList<Resource>();
				buildBase.setTestResources(testResources);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("testResource".equals(parser.getName())) {
						testResources.add(parseResource(parser, strict, source));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "directory", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				buildBase.setLocation("directory", _location);
				buildBase.setDirectory(interpolatedTrimmed(parser.nextText(), "directory"));
			} else if (checkFieldWithDuplicate(parser, "finalName", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				buildBase.setLocation("finalName", _location);
				buildBase.setFinalName(interpolatedTrimmed(parser.nextText(), "finalName"));
			} else if (checkFieldWithDuplicate(parser, "filters", null, parsed)) {
				java.util.List<String> filters = new java.util.ArrayList<String>();
				buildBase.setFilters(filters);
				InputLocation _locations;
				_locations = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				buildBase.setLocation("filters", _locations);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("filter".equals(parser.getName())) {
						_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
						_locations.setLocation(Integer.valueOf(filters.size()), _location);
						filters.add(interpolatedTrimmed(parser.nextText(), "filters"));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "pluginManagement", null, parsed)) {
				buildBase.setPluginManagement(parsePluginManagement(parser, strict, source));
			} else if (checkFieldWithDuplicate(parser, "plugins", null, parsed)) {
				java.util.List<Plugin> plugins = new java.util.ArrayList<Plugin>();
				buildBase.setPlugins(plugins);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("plugin".equals(parser.getName())) {
						plugins.add(parsePlugin(parser, strict, source));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return buildBase;
	} // -- BuildBase parseBuildBase( XmlPullParser, boolean, InputSource )

	/**
	 * Method parseCiManagement.
	 * @param parser
	 * @param source
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return CiManagement
	 */
	private CiManagement parseCiManagement(XmlPullParser parser, boolean strict, InputSource source) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		CiManagement ciManagement = new CiManagement();
		InputLocation _location;
		_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
		ciManagement.setLocation("", _location);
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
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				ciManagement.setLocation("system", _location);
				ciManagement.setSystem(interpolatedTrimmed(parser.nextText(), "system"));
			} else if (checkFieldWithDuplicate(parser, "url", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				ciManagement.setLocation("url", _location);
				ciManagement.setUrl(interpolatedTrimmed(parser.nextText(), "url"));
			} else if (checkFieldWithDuplicate(parser, "notifiers", null, parsed)) {
				java.util.List<Notifier> notifiers = new java.util.ArrayList<Notifier>();
				ciManagement.setNotifiers(notifiers);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("notifier".equals(parser.getName())) {
						notifiers.add(parseNotifier(parser, strict, source));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return ciManagement;
	} // -- CiManagement parseCiManagement( XmlPullParser, boolean, InputSource )

	/**
	 * Method parseConfigurationContainer.
	 * @param parser
	 * @param source
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return ConfigurationContainer
	 */
	private ConfigurationContainer parseConfigurationContainer(XmlPullParser parser, boolean strict, InputSource source) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		ConfigurationContainer configurationContainer = new ConfigurationContainer();
		InputLocation _location;
		_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
		configurationContainer.setLocation("", _location);
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
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				configurationContainer.setLocation("inherited", _location);
				configurationContainer.setInherited(interpolatedTrimmed(parser.nextText(), "inherited"));
			} else if (checkFieldWithDuplicate(parser, "configuration", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				configurationContainer.setLocation("configuration", _location);
				configurationContainer.setConfiguration(org.codehaus.plexus.util.xml.Xpp3DomBuilder.build(parser, true));
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return configurationContainer;
	} // -- ConfigurationContainer parseConfigurationContainer( XmlPullParser, boolean, InputSource )

	/**
	 * Method parseContributor.
	 * @param parser
	 * @param source
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return Contributor
	 */
	private Contributor parseContributor(XmlPullParser parser, boolean strict, InputSource source) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		Contributor contributor = new Contributor();
		InputLocation _location;
		_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
		contributor.setLocation("", _location);
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
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				contributor.setLocation("name", _location);
				contributor.setName(interpolatedTrimmed(parser.nextText(), "name"));
			} else if (checkFieldWithDuplicate(parser, "email", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				contributor.setLocation("email", _location);
				contributor.setEmail(interpolatedTrimmed(parser.nextText(), "email"));
			} else if (checkFieldWithDuplicate(parser, "url", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				contributor.setLocation("url", _location);
				contributor.setUrl(interpolatedTrimmed(parser.nextText(), "url"));
			} else if (checkFieldWithDuplicate(parser, "organization", "organisation", parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				contributor.setLocation("organization", _location);
				contributor.setOrganization(interpolatedTrimmed(parser.nextText(), "organization"));
			} else if (checkFieldWithDuplicate(parser, "organizationUrl", "organisationUrl", parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				contributor.setLocation("organizationUrl", _location);
				contributor.setOrganizationUrl(interpolatedTrimmed(parser.nextText(), "organizationUrl"));
			} else if (checkFieldWithDuplicate(parser, "roles", null, parsed)) {
				java.util.List<String> roles = new java.util.ArrayList<String>();
				contributor.setRoles(roles);
				InputLocation _locations;
				_locations = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				contributor.setLocation("roles", _locations);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("role".equals(parser.getName())) {
						_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
						_locations.setLocation(Integer.valueOf(roles.size()), _location);
						roles.add(interpolatedTrimmed(parser.nextText(), "roles"));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "timezone", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				contributor.setLocation("timezone", _location);
				contributor.setTimezone(interpolatedTrimmed(parser.nextText(), "timezone"));
			} else if (checkFieldWithDuplicate(parser, "properties", null, parsed)) {
				InputLocation _locations;
				_locations = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				contributor.setLocation("properties", _locations);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					String key = parser.getName();
					_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
					_locations.setLocation(key, _location);
					String value = parser.nextText().trim();
					contributor.addProperty(key, value);
				}
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return contributor;
	} // -- Contributor parseContributor( XmlPullParser, boolean, InputSource )

	/**
	 * Method parseDependency.
	 * @param parser
	 * @param source
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return Dependency
	 */
	private Dependency parseDependency(XmlPullParser parser, boolean strict, InputSource source) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		Dependency dependency = new Dependency();
		InputLocation _location;
		_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
		dependency.setLocation("", _location);
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
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				dependency.setLocation("groupId", _location);
				dependency.setGroupId(interpolatedTrimmed(parser.nextText(), "groupId"));
			} else if (checkFieldWithDuplicate(parser, "artifactId", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				dependency.setLocation("artifactId", _location);
				dependency.setArtifactId(interpolatedTrimmed(parser.nextText(), "artifactId"));
			} else if (checkFieldWithDuplicate(parser, "version", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				dependency.setLocation("version", _location);
				dependency.setVersion(interpolatedTrimmed(parser.nextText(), "version"));
			} else if (checkFieldWithDuplicate(parser, "type", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				dependency.setLocation("type", _location);
				dependency.setType(interpolatedTrimmed(parser.nextText(), "type"));
			} else if (checkFieldWithDuplicate(parser, "classifier", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				dependency.setLocation("classifier", _location);
				dependency.setClassifier(interpolatedTrimmed(parser.nextText(), "classifier"));
			} else if (checkFieldWithDuplicate(parser, "scope", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				dependency.setLocation("scope", _location);
				dependency.setScope(interpolatedTrimmed(parser.nextText(), "scope"));
			} else if (checkFieldWithDuplicate(parser, "systemPath", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				dependency.setLocation("systemPath", _location);
				dependency.setSystemPath(interpolatedTrimmed(parser.nextText(), "systemPath"));
			} else if (checkFieldWithDuplicate(parser, "exclusions", null, parsed)) {
				java.util.List<Exclusion> exclusions = new java.util.ArrayList<Exclusion>();
				dependency.setExclusions(exclusions);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("exclusion".equals(parser.getName())) {
						exclusions.add(parseExclusion(parser, strict, source));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "optional", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				dependency.setLocation("optional", _location);
				dependency.setOptional(interpolatedTrimmed(parser.nextText(), "optional"));
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return dependency;
	} // -- Dependency parseDependency( XmlPullParser, boolean, InputSource )

	/**
	 * Method parseDependencyManagement.
	 * @param parser
	 * @param source
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return DependencyManagement
	 */
	private DependencyManagement parseDependencyManagement(XmlPullParser parser, boolean strict, InputSource source) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		DependencyManagement dependencyManagement = new DependencyManagement();
		InputLocation _location;
		_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
		dependencyManagement.setLocation("", _location);
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
						dependencies.add(parseDependency(parser, strict, source));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return dependencyManagement;
	} // -- DependencyManagement parseDependencyManagement( XmlPullParser, boolean, InputSource )

	/**
	 * Method parseDeploymentRepository.
	 * @param parser
	 * @param source
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return DeploymentRepository
	 */
	private DeploymentRepository parseDeploymentRepository(XmlPullParser parser, boolean strict, InputSource source) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		DeploymentRepository deploymentRepository = new DeploymentRepository();
		InputLocation _location;
		_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
		deploymentRepository.setLocation("", _location);
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
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				deploymentRepository.setLocation("uniqueVersion", _location);
				deploymentRepository.setUniqueVersion(getBooleanValue(interpolatedTrimmed(parser.nextText(), "uniqueVersion"), "uniqueVersion", parser, "true"));
			} else if (checkFieldWithDuplicate(parser, "releases", null, parsed)) {
				deploymentRepository.setReleases(parseRepositoryPolicy(parser, strict, source));
			} else if (checkFieldWithDuplicate(parser, "snapshots", null, parsed)) {
				deploymentRepository.setSnapshots(parseRepositoryPolicy(parser, strict, source));
			} else if (checkFieldWithDuplicate(parser, "id", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				deploymentRepository.setLocation("id", _location);
				deploymentRepository.setId(interpolatedTrimmed(parser.nextText(), "id"));
			} else if (checkFieldWithDuplicate(parser, "name", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				deploymentRepository.setLocation("name", _location);
				deploymentRepository.setName(interpolatedTrimmed(parser.nextText(), "name"));
			} else if (checkFieldWithDuplicate(parser, "url", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				deploymentRepository.setLocation("url", _location);
				deploymentRepository.setUrl(interpolatedTrimmed(parser.nextText(), "url"));
			} else if (checkFieldWithDuplicate(parser, "layout", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				deploymentRepository.setLocation("layout", _location);
				deploymentRepository.setLayout(interpolatedTrimmed(parser.nextText(), "layout"));
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return deploymentRepository;
	} // -- DeploymentRepository parseDeploymentRepository( XmlPullParser, boolean, InputSource )

	/**
	 * Method parseDeveloper.
	 * @param parser
	 * @param source
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return Developer
	 */
	private Developer parseDeveloper(XmlPullParser parser, boolean strict, InputSource source) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		Developer developer = new Developer();
		InputLocation _location;
		_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
		developer.setLocation("", _location);
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
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				developer.setLocation("id", _location);
				developer.setId(interpolatedTrimmed(parser.nextText(), "id"));
			} else if (checkFieldWithDuplicate(parser, "name", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				developer.setLocation("name", _location);
				developer.setName(interpolatedTrimmed(parser.nextText(), "name"));
			} else if (checkFieldWithDuplicate(parser, "email", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				developer.setLocation("email", _location);
				developer.setEmail(interpolatedTrimmed(parser.nextText(), "email"));
			} else if (checkFieldWithDuplicate(parser, "url", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				developer.setLocation("url", _location);
				developer.setUrl(interpolatedTrimmed(parser.nextText(), "url"));
			} else if (checkFieldWithDuplicate(parser, "organization", "organisation", parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				developer.setLocation("organization", _location);
				developer.setOrganization(interpolatedTrimmed(parser.nextText(), "organization"));
			} else if (checkFieldWithDuplicate(parser, "organizationUrl", "organisationUrl", parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				developer.setLocation("organizationUrl", _location);
				developer.setOrganizationUrl(interpolatedTrimmed(parser.nextText(), "organizationUrl"));
			} else if (checkFieldWithDuplicate(parser, "roles", null, parsed)) {
				java.util.List<String> roles = new java.util.ArrayList<String>();
				developer.setRoles(roles);
				InputLocation _locations;
				_locations = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				developer.setLocation("roles", _locations);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("role".equals(parser.getName())) {
						_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
						_locations.setLocation(Integer.valueOf(roles.size()), _location);
						roles.add(interpolatedTrimmed(parser.nextText(), "roles"));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "timezone", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				developer.setLocation("timezone", _location);
				developer.setTimezone(interpolatedTrimmed(parser.nextText(), "timezone"));
			} else if (checkFieldWithDuplicate(parser, "properties", null, parsed)) {
				InputLocation _locations;
				_locations = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				developer.setLocation("properties", _locations);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					String key = parser.getName();
					_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
					_locations.setLocation(key, _location);
					String value = parser.nextText().trim();
					developer.addProperty(key, value);
				}
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return developer;
	} // -- Developer parseDeveloper( XmlPullParser, boolean, InputSource )

	/**
	 * Method parseDistributionManagement.
	 * @param parser
	 * @param source
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return DistributionManagement
	 */
	private DistributionManagement parseDistributionManagement(XmlPullParser parser, boolean strict, InputSource source) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		DistributionManagement distributionManagement = new DistributionManagement();
		InputLocation _location;
		_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
		distributionManagement.setLocation("", _location);
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
				distributionManagement.setRepository(parseDeploymentRepository(parser, strict, source));
			} else if (checkFieldWithDuplicate(parser, "snapshotRepository", null, parsed)) {
				distributionManagement.setSnapshotRepository(parseDeploymentRepository(parser, strict, source));
			} else if (checkFieldWithDuplicate(parser, "site", null, parsed)) {
				distributionManagement.setSite(parseSite(parser, strict, source));
			} else if (checkFieldWithDuplicate(parser, "downloadUrl", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				distributionManagement.setLocation("downloadUrl", _location);
				distributionManagement.setDownloadUrl(interpolatedTrimmed(parser.nextText(), "downloadUrl"));
			} else if (checkFieldWithDuplicate(parser, "relocation", null, parsed)) {
				distributionManagement.setRelocation(parseRelocation(parser, strict, source));
			} else if (checkFieldWithDuplicate(parser, "status", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				distributionManagement.setLocation("status", _location);
				distributionManagement.setStatus(interpolatedTrimmed(parser.nextText(), "status"));
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return distributionManagement;
	} // -- DistributionManagement parseDistributionManagement( XmlPullParser, boolean, InputSource )

	/**
	 * Method parseExclusion.
	 * @param parser
	 * @param source
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return Exclusion
	 */
	private Exclusion parseExclusion(XmlPullParser parser, boolean strict, InputSource source) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		Exclusion exclusion = new Exclusion();
		InputLocation _location;
		_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
		exclusion.setLocation("", _location);
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
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				exclusion.setLocation("artifactId", _location);
				exclusion.setArtifactId(interpolatedTrimmed(parser.nextText(), "artifactId"));
			} else if (checkFieldWithDuplicate(parser, "groupId", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				exclusion.setLocation("groupId", _location);
				exclusion.setGroupId(interpolatedTrimmed(parser.nextText(), "groupId"));
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return exclusion;
	} // -- Exclusion parseExclusion( XmlPullParser, boolean, InputSource )

	/**
	 * Method parseExtension.
	 * @param parser
	 * @param source
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return Extension
	 */
	private Extension parseExtension(XmlPullParser parser, boolean strict, InputSource source) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		Extension extension = new Extension();
		InputLocation _location;
		_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
		extension.setLocation("", _location);
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
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				extension.setLocation("groupId", _location);
				extension.setGroupId(interpolatedTrimmed(parser.nextText(), "groupId"));
			} else if (checkFieldWithDuplicate(parser, "artifactId", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				extension.setLocation("artifactId", _location);
				extension.setArtifactId(interpolatedTrimmed(parser.nextText(), "artifactId"));
			} else if (checkFieldWithDuplicate(parser, "version", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				extension.setLocation("version", _location);
				extension.setVersion(interpolatedTrimmed(parser.nextText(), "version"));
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return extension;
	} // -- Extension parseExtension( XmlPullParser, boolean, InputSource )

	/**
	 * Method parseFileSet.
	 * @param parser
	 * @param source
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return FileSet
	 */
	private FileSet parseFileSet(XmlPullParser parser, boolean strict, InputSource source) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		FileSet fileSet = new FileSet();
		InputLocation _location;
		_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
		fileSet.setLocation("", _location);
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
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				fileSet.setLocation("directory", _location);
				fileSet.setDirectory(interpolatedTrimmed(parser.nextText(), "directory"));
			} else if (checkFieldWithDuplicate(parser, "includes", null, parsed)) {
				java.util.List<String> includes = new java.util.ArrayList<String>();
				fileSet.setIncludes(includes);
				InputLocation _locations;
				_locations = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				fileSet.setLocation("includes", _locations);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("include".equals(parser.getName())) {
						_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
						_locations.setLocation(Integer.valueOf(includes.size()), _location);
						includes.add(interpolatedTrimmed(parser.nextText(), "includes"));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "excludes", null, parsed)) {
				java.util.List<String> excludes = new java.util.ArrayList<String>();
				fileSet.setExcludes(excludes);
				InputLocation _locations;
				_locations = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				fileSet.setLocation("excludes", _locations);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("exclude".equals(parser.getName())) {
						_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
						_locations.setLocation(Integer.valueOf(excludes.size()), _location);
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
	} // -- FileSet parseFileSet( XmlPullParser, boolean, InputSource )

	/**
	 * Method parseIssueManagement.
	 * @param parser
	 * @param source
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return IssueManagement
	 */
	private IssueManagement parseIssueManagement(XmlPullParser parser, boolean strict, InputSource source) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		IssueManagement issueManagement = new IssueManagement();
		InputLocation _location;
		_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
		issueManagement.setLocation("", _location);
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
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				issueManagement.setLocation("system", _location);
				issueManagement.setSystem(interpolatedTrimmed(parser.nextText(), "system"));
			} else if (checkFieldWithDuplicate(parser, "url", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				issueManagement.setLocation("url", _location);
				issueManagement.setUrl(interpolatedTrimmed(parser.nextText(), "url"));
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return issueManagement;
	} // -- IssueManagement parseIssueManagement( XmlPullParser, boolean, InputSource )

	/**
	 * Method parseLicense.
	 * @param parser
	 * @param source
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return License
	 */
	private License parseLicense(XmlPullParser parser, boolean strict, InputSource source) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		License license = new License();
		InputLocation _location;
		_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
		license.setLocation("", _location);
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
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				license.setLocation("name", _location);
				license.setName(interpolatedTrimmed(parser.nextText(), "name"));
			} else if (checkFieldWithDuplicate(parser, "url", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				license.setLocation("url", _location);
				license.setUrl(interpolatedTrimmed(parser.nextText(), "url"));
			} else if (checkFieldWithDuplicate(parser, "distribution", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				license.setLocation("distribution", _location);
				license.setDistribution(interpolatedTrimmed(parser.nextText(), "distribution"));
			} else if (checkFieldWithDuplicate(parser, "comments", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				license.setLocation("comments", _location);
				license.setComments(interpolatedTrimmed(parser.nextText(), "comments"));
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return license;
	} // -- License parseLicense( XmlPullParser, boolean, InputSource )

	/**
	 * Method parseMailingList.
	 * @param parser
	 * @param source
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return MailingList
	 */
	private MailingList parseMailingList(XmlPullParser parser, boolean strict, InputSource source) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		MailingList mailingList = new MailingList();
		InputLocation _location;
		_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
		mailingList.setLocation("", _location);
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
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				mailingList.setLocation("name", _location);
				mailingList.setName(interpolatedTrimmed(parser.nextText(), "name"));
			} else if (checkFieldWithDuplicate(parser, "subscribe", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				mailingList.setLocation("subscribe", _location);
				mailingList.setSubscribe(interpolatedTrimmed(parser.nextText(), "subscribe"));
			} else if (checkFieldWithDuplicate(parser, "unsubscribe", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				mailingList.setLocation("unsubscribe", _location);
				mailingList.setUnsubscribe(interpolatedTrimmed(parser.nextText(), "unsubscribe"));
			} else if (checkFieldWithDuplicate(parser, "post", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				mailingList.setLocation("post", _location);
				mailingList.setPost(interpolatedTrimmed(parser.nextText(), "post"));
			} else if (checkFieldWithDuplicate(parser, "archive", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				mailingList.setLocation("archive", _location);
				mailingList.setArchive(interpolatedTrimmed(parser.nextText(), "archive"));
			} else if (checkFieldWithDuplicate(parser, "otherArchives", null, parsed)) {
				java.util.List<String> otherArchives = new java.util.ArrayList<String>();
				mailingList.setOtherArchives(otherArchives);
				InputLocation _locations;
				_locations = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				mailingList.setLocation("otherArchives", _locations);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("otherArchive".equals(parser.getName())) {
						_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
						_locations.setLocation(Integer.valueOf(otherArchives.size()), _location);
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
	} // -- MailingList parseMailingList( XmlPullParser, boolean, InputSource )

	/**
	 * Method parseModel.
	 * @param parser
	 * @param source
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return Model
	 */
	private Model parseModel(XmlPullParser parser, boolean strict, InputSource source) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		Model model = new Model();
		InputLocation _location;
		_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
		model.setLocation("", _location);
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
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				model.setLocation("modelVersion", _location);
				model.setModelVersion(interpolatedTrimmed(parser.nextText(), "modelVersion"));
			} else if (checkFieldWithDuplicate(parser, "parent", null, parsed)) {
				model.setParent(parseParent(parser, strict, source));
			} else if (checkFieldWithDuplicate(parser, "groupId", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				model.setLocation("groupId", _location);
				model.setGroupId(interpolatedTrimmed(parser.nextText(), "groupId"));
			} else if (checkFieldWithDuplicate(parser, "artifactId", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				model.setLocation("artifactId", _location);
				model.setArtifactId(interpolatedTrimmed(parser.nextText(), "artifactId"));
			} else if (checkFieldWithDuplicate(parser, "version", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				model.setLocation("version", _location);
				model.setVersion(interpolatedTrimmed(parser.nextText(), "version"));
			} else if (checkFieldWithDuplicate(parser, "packaging", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				model.setLocation("packaging", _location);
				model.setPackaging(interpolatedTrimmed(parser.nextText(), "packaging"));
			} else if (checkFieldWithDuplicate(parser, "name", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				model.setLocation("name", _location);
				model.setName(interpolatedTrimmed(parser.nextText(), "name"));
			} else if (checkFieldWithDuplicate(parser, "description", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				model.setLocation("description", _location);
				model.setDescription(interpolatedTrimmed(parser.nextText(), "description"));
			} else if (checkFieldWithDuplicate(parser, "url", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				model.setLocation("url", _location);
				model.setUrl(interpolatedTrimmed(parser.nextText(), "url"));
			} else if (checkFieldWithDuplicate(parser, "inceptionYear", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				model.setLocation("inceptionYear", _location);
				model.setInceptionYear(interpolatedTrimmed(parser.nextText(), "inceptionYear"));
			} else if (checkFieldWithDuplicate(parser, "organization", "organisation", parsed)) {
				model.setOrganization(parseOrganization(parser, strict, source));
			} else if (checkFieldWithDuplicate(parser, "licenses", null, parsed)) {
				java.util.List<License> licenses = new java.util.ArrayList<License>();
				model.setLicenses(licenses);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("license".equals(parser.getName())) {
						licenses.add(parseLicense(parser, strict, source));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "developers", null, parsed)) {
				java.util.List<Developer> developers = new java.util.ArrayList<Developer>();
				model.setDevelopers(developers);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("developer".equals(parser.getName())) {
						developers.add(parseDeveloper(parser, strict, source));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "contributors", null, parsed)) {
				java.util.List<Contributor> contributors = new java.util.ArrayList<Contributor>();
				model.setContributors(contributors);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("contributor".equals(parser.getName())) {
						contributors.add(parseContributor(parser, strict, source));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "mailingLists", null, parsed)) {
				java.util.List<MailingList> mailingLists = new java.util.ArrayList<MailingList>();
				model.setMailingLists(mailingLists);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("mailingList".equals(parser.getName())) {
						mailingLists.add(parseMailingList(parser, strict, source));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "prerequisites", null, parsed)) {
				model.setPrerequisites(parsePrerequisites(parser, strict, source));
			} else if (checkFieldWithDuplicate(parser, "modules", null, parsed)) {
				java.util.List<String> modules = new java.util.ArrayList<String>();
				model.setModules(modules);
				InputLocation _locations;
				_locations = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				model.setLocation("modules", _locations);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("module".equals(parser.getName())) {
						_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
						_locations.setLocation(Integer.valueOf(modules.size()), _location);
						modules.add(interpolatedTrimmed(parser.nextText(), "modules"));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "scm", null, parsed)) {
				model.setScm(parseScm(parser, strict, source));
			} else if (checkFieldWithDuplicate(parser, "issueManagement", null, parsed)) {
				model.setIssueManagement(parseIssueManagement(parser, strict, source));
			} else if (checkFieldWithDuplicate(parser, "ciManagement", null, parsed)) {
				model.setCiManagement(parseCiManagement(parser, strict, source));
			} else if (checkFieldWithDuplicate(parser, "distributionManagement", null, parsed)) {
				model.setDistributionManagement(parseDistributionManagement(parser, strict, source));
			} else if (checkFieldWithDuplicate(parser, "properties", null, parsed)) {
				InputLocation _locations;
				_locations = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				model.setLocation("properties", _locations);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					String key = parser.getName();
					_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
					_locations.setLocation(key, _location);
					String value = parser.nextText().trim();
					model.addProperty(key, value);
				}
			} else if (checkFieldWithDuplicate(parser, "dependencyManagement", null, parsed)) {
				model.setDependencyManagement(parseDependencyManagement(parser, strict, source));
			} else if (checkFieldWithDuplicate(parser, "dependencies", null, parsed)) {
				java.util.List<Dependency> dependencies = new java.util.ArrayList<Dependency>();
				model.setDependencies(dependencies);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("dependency".equals(parser.getName())) {
						dependencies.add(parseDependency(parser, strict, source));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "repositories", null, parsed)) {
				java.util.List<Repository> repositories = new java.util.ArrayList<Repository>();
				model.setRepositories(repositories);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("repository".equals(parser.getName())) {
						repositories.add(parseRepository(parser, strict, source));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "pluginRepositories", null, parsed)) {
				java.util.List<Repository> pluginRepositories = new java.util.ArrayList<Repository>();
				model.setPluginRepositories(pluginRepositories);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("pluginRepository".equals(parser.getName())) {
						pluginRepositories.add(parseRepository(parser, strict, source));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "build", null, parsed)) {
				model.setBuild(parseBuild(parser, strict, source));
			} else if (checkFieldWithDuplicate(parser, "reports", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				model.setLocation("reports", _location);
				model.setReports(org.codehaus.plexus.util.xml.Xpp3DomBuilder.build(parser, true));
			} else if (checkFieldWithDuplicate(parser, "reporting", null, parsed)) {
				model.setReporting(parseReporting(parser, strict, source));
			} else if (checkFieldWithDuplicate(parser, "profiles", null, parsed)) {
				java.util.List<Profile> profiles = new java.util.ArrayList<Profile>();
				model.setProfiles(profiles);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("profile".equals(parser.getName())) {
						profiles.add(parseProfile(parser, strict, source));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return model;
	} // -- Model parseModel( XmlPullParser, boolean, InputSource )

	/**
	 * Method parseModelBase.
	 * @param parser
	 * @param source
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return ModelBase
	 */
	private ModelBase parseModelBase(XmlPullParser parser, boolean strict, InputSource source) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		ModelBase modelBase = new ModelBase();
		InputLocation _location;
		_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
		modelBase.setLocation("", _location);
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
				InputLocation _locations;
				_locations = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				modelBase.setLocation("modules", _locations);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("module".equals(parser.getName())) {
						_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
						_locations.setLocation(Integer.valueOf(modules.size()), _location);
						modules.add(interpolatedTrimmed(parser.nextText(), "modules"));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "distributionManagement", null, parsed)) {
				modelBase.setDistributionManagement(parseDistributionManagement(parser, strict, source));
			} else if (checkFieldWithDuplicate(parser, "properties", null, parsed)) {
				InputLocation _locations;
				_locations = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				modelBase.setLocation("properties", _locations);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					String key = parser.getName();
					_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
					_locations.setLocation(key, _location);
					String value = parser.nextText().trim();
					modelBase.addProperty(key, value);
				}
			} else if (checkFieldWithDuplicate(parser, "dependencyManagement", null, parsed)) {
				modelBase.setDependencyManagement(parseDependencyManagement(parser, strict, source));
			} else if (checkFieldWithDuplicate(parser, "dependencies", null, parsed)) {
				java.util.List<Dependency> dependencies = new java.util.ArrayList<Dependency>();
				modelBase.setDependencies(dependencies);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("dependency".equals(parser.getName())) {
						dependencies.add(parseDependency(parser, strict, source));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "repositories", null, parsed)) {
				java.util.List<Repository> repositories = new java.util.ArrayList<Repository>();
				modelBase.setRepositories(repositories);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("repository".equals(parser.getName())) {
						repositories.add(parseRepository(parser, strict, source));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "pluginRepositories", null, parsed)) {
				java.util.List<Repository> pluginRepositories = new java.util.ArrayList<Repository>();
				modelBase.setPluginRepositories(pluginRepositories);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("pluginRepository".equals(parser.getName())) {
						pluginRepositories.add(parseRepository(parser, strict, source));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "reports", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				modelBase.setLocation("reports", _location);
				modelBase.setReports(org.codehaus.plexus.util.xml.Xpp3DomBuilder.build(parser, true));
			} else if (checkFieldWithDuplicate(parser, "reporting", null, parsed)) {
				modelBase.setReporting(parseReporting(parser, strict, source));
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return modelBase;
	} // -- ModelBase parseModelBase( XmlPullParser, boolean, InputSource )

	/**
	 * Method parseNotifier.
	 * @param parser
	 * @param source
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return Notifier
	 */
	private Notifier parseNotifier(XmlPullParser parser, boolean strict, InputSource source) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		Notifier notifier = new Notifier();
		InputLocation _location;
		_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
		notifier.setLocation("", _location);
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
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				notifier.setLocation("type", _location);
				notifier.setType(interpolatedTrimmed(parser.nextText(), "type"));
			} else if (checkFieldWithDuplicate(parser, "sendOnError", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				notifier.setLocation("sendOnError", _location);
				notifier.setSendOnError(getBooleanValue(interpolatedTrimmed(parser.nextText(), "sendOnError"), "sendOnError", parser, "true"));
			} else if (checkFieldWithDuplicate(parser, "sendOnFailure", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				notifier.setLocation("sendOnFailure", _location);
				notifier.setSendOnFailure(getBooleanValue(interpolatedTrimmed(parser.nextText(), "sendOnFailure"), "sendOnFailure", parser, "true"));
			} else if (checkFieldWithDuplicate(parser, "sendOnSuccess", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				notifier.setLocation("sendOnSuccess", _location);
				notifier.setSendOnSuccess(getBooleanValue(interpolatedTrimmed(parser.nextText(), "sendOnSuccess"), "sendOnSuccess", parser, "true"));
			} else if (checkFieldWithDuplicate(parser, "sendOnWarning", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				notifier.setLocation("sendOnWarning", _location);
				notifier.setSendOnWarning(getBooleanValue(interpolatedTrimmed(parser.nextText(), "sendOnWarning"), "sendOnWarning", parser, "true"));
			} else if (checkFieldWithDuplicate(parser, "address", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				notifier.setLocation("address", _location);
				notifier.setAddress(interpolatedTrimmed(parser.nextText(), "address"));
			} else if (checkFieldWithDuplicate(parser, "configuration", null, parsed)) {
				InputLocation _locations;
				_locations = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				notifier.setLocation("configuration", _locations);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					String key = parser.getName();
					_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
					_locations.setLocation(key, _location);
					String value = parser.nextText().trim();
					notifier.addConfiguration(key, value);
				}
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return notifier;
	} // -- Notifier parseNotifier( XmlPullParser, boolean, InputSource )

	/**
	 * Method parseOrganization.
	 * @param parser
	 * @param source
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return Organization
	 */
	private Organization parseOrganization(XmlPullParser parser, boolean strict, InputSource source) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		Organization organization = new Organization();
		InputLocation _location;
		_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
		organization.setLocation("", _location);
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
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				organization.setLocation("name", _location);
				organization.setName(interpolatedTrimmed(parser.nextText(), "name"));
			} else if (checkFieldWithDuplicate(parser, "url", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				organization.setLocation("url", _location);
				organization.setUrl(interpolatedTrimmed(parser.nextText(), "url"));
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return organization;
	} // -- Organization parseOrganization( XmlPullParser, boolean, InputSource )

	/**
	 * Method parseParent.
	 * @param parser
	 * @param source
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return Parent
	 */
	private Parent parseParent(XmlPullParser parser, boolean strict, InputSource source) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		Parent parent = new Parent();
		InputLocation _location;
		_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
		parent.setLocation("", _location);
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
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				parent.setLocation("groupId", _location);
				parent.setGroupId(interpolatedTrimmed(parser.nextText(), "groupId"));
			} else if (checkFieldWithDuplicate(parser, "artifactId", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				parent.setLocation("artifactId", _location);
				parent.setArtifactId(interpolatedTrimmed(parser.nextText(), "artifactId"));
			} else if (checkFieldWithDuplicate(parser, "version", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				parent.setLocation("version", _location);
				parent.setVersion(interpolatedTrimmed(parser.nextText(), "version"));
			} else if (checkFieldWithDuplicate(parser, "relativePath", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				parent.setLocation("relativePath", _location);
				parent.setRelativePath(interpolatedTrimmed(parser.nextText(), "relativePath"));
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return parent;
	} // -- Parent parseParent( XmlPullParser, boolean, InputSource )

	/**
	 * Method parsePatternSet.
	 * @param parser
	 * @param source
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return PatternSet
	 */
	private PatternSet parsePatternSet(XmlPullParser parser, boolean strict, InputSource source) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		PatternSet patternSet = new PatternSet();
		InputLocation _location;
		_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
		patternSet.setLocation("", _location);
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
				InputLocation _locations;
				_locations = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				patternSet.setLocation("includes", _locations);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("include".equals(parser.getName())) {
						_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
						_locations.setLocation(Integer.valueOf(includes.size()), _location);
						includes.add(interpolatedTrimmed(parser.nextText(), "includes"));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "excludes", null, parsed)) {
				java.util.List<String> excludes = new java.util.ArrayList<String>();
				patternSet.setExcludes(excludes);
				InputLocation _locations;
				_locations = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				patternSet.setLocation("excludes", _locations);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("exclude".equals(parser.getName())) {
						_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
						_locations.setLocation(Integer.valueOf(excludes.size()), _location);
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
	} // -- PatternSet parsePatternSet( XmlPullParser, boolean, InputSource )

	/**
	 * Method parsePlugin.
	 * @param parser
	 * @param source
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return Plugin
	 */
	private Plugin parsePlugin(XmlPullParser parser, boolean strict, InputSource source) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		Plugin plugin = new Plugin();
		InputLocation _location;
		_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
		plugin.setLocation("", _location);
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
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				plugin.setLocation("groupId", _location);
				plugin.setGroupId(interpolatedTrimmed(parser.nextText(), "groupId"));
			} else if (checkFieldWithDuplicate(parser, "artifactId", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				plugin.setLocation("artifactId", _location);
				plugin.setArtifactId(interpolatedTrimmed(parser.nextText(), "artifactId"));
			} else if (checkFieldWithDuplicate(parser, "version", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				plugin.setLocation("version", _location);
				plugin.setVersion(interpolatedTrimmed(parser.nextText(), "version"));
			} else if (checkFieldWithDuplicate(parser, "extensions", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				plugin.setLocation("extensions", _location);
				plugin.setExtensions(interpolatedTrimmed(parser.nextText(), "extensions"));
			} else if (checkFieldWithDuplicate(parser, "executions", null, parsed)) {
				java.util.List<PluginExecution> executions = new java.util.ArrayList<PluginExecution>();
				plugin.setExecutions(executions);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("execution".equals(parser.getName())) {
						executions.add(parsePluginExecution(parser, strict, source));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "dependencies", null, parsed)) {
				java.util.List<Dependency> dependencies = new java.util.ArrayList<Dependency>();
				plugin.setDependencies(dependencies);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("dependency".equals(parser.getName())) {
						dependencies.add(parseDependency(parser, strict, source));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "goals", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				plugin.setLocation("goals", _location);
				plugin.setGoals(org.codehaus.plexus.util.xml.Xpp3DomBuilder.build(parser, true));
			} else if (checkFieldWithDuplicate(parser, "inherited", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				plugin.setLocation("inherited", _location);
				plugin.setInherited(interpolatedTrimmed(parser.nextText(), "inherited"));
			} else if (checkFieldWithDuplicate(parser, "configuration", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				plugin.setLocation("configuration", _location);
				plugin.setConfiguration(org.codehaus.plexus.util.xml.Xpp3DomBuilder.build(parser, true));
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return plugin;
	} // -- Plugin parsePlugin( XmlPullParser, boolean, InputSource )

	/**
	 * Method parsePluginConfiguration.
	 * @param parser
	 * @param source
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return PluginConfiguration
	 */
	private PluginConfiguration parsePluginConfiguration(XmlPullParser parser, boolean strict, InputSource source) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		PluginConfiguration pluginConfiguration = new PluginConfiguration();
		InputLocation _location;
		_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
		pluginConfiguration.setLocation("", _location);
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
				pluginConfiguration.setPluginManagement(parsePluginManagement(parser, strict, source));
			} else if (checkFieldWithDuplicate(parser, "plugins", null, parsed)) {
				java.util.List<Plugin> plugins = new java.util.ArrayList<Plugin>();
				pluginConfiguration.setPlugins(plugins);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("plugin".equals(parser.getName())) {
						plugins.add(parsePlugin(parser, strict, source));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return pluginConfiguration;
	} // -- PluginConfiguration parsePluginConfiguration( XmlPullParser, boolean, InputSource )

	/**
	 * Method parsePluginContainer.
	 * @param parser
	 * @param source
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return PluginContainer
	 */
	private PluginContainer parsePluginContainer(XmlPullParser parser, boolean strict, InputSource source) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		PluginContainer pluginContainer = new PluginContainer();
		InputLocation _location;
		_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
		pluginContainer.setLocation("", _location);
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
						plugins.add(parsePlugin(parser, strict, source));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return pluginContainer;
	} // -- PluginContainer parsePluginContainer( XmlPullParser, boolean, InputSource )

	/**
	 * Method parsePluginExecution.
	 * @param parser
	 * @param source
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return PluginExecution
	 */
	private PluginExecution parsePluginExecution(XmlPullParser parser, boolean strict, InputSource source) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		PluginExecution pluginExecution = new PluginExecution();
		InputLocation _location;
		_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
		pluginExecution.setLocation("", _location);
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
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				pluginExecution.setLocation("id", _location);
				pluginExecution.setId(interpolatedTrimmed(parser.nextText(), "id"));
			} else if (checkFieldWithDuplicate(parser, "phase", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				pluginExecution.setLocation("phase", _location);
				pluginExecution.setPhase(interpolatedTrimmed(parser.nextText(), "phase"));
			} else if (checkFieldWithDuplicate(parser, "goals", null, parsed)) {
				java.util.List<String> goals = new java.util.ArrayList<String>();
				pluginExecution.setGoals(goals);
				InputLocation _locations;
				_locations = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				pluginExecution.setLocation("goals", _locations);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("goal".equals(parser.getName())) {
						_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
						_locations.setLocation(Integer.valueOf(goals.size()), _location);
						goals.add(interpolatedTrimmed(parser.nextText(), "goals"));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "inherited", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				pluginExecution.setLocation("inherited", _location);
				pluginExecution.setInherited(interpolatedTrimmed(parser.nextText(), "inherited"));
			} else if (checkFieldWithDuplicate(parser, "configuration", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				pluginExecution.setLocation("configuration", _location);
				pluginExecution.setConfiguration(org.codehaus.plexus.util.xml.Xpp3DomBuilder.build(parser, true));
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return pluginExecution;
	} // -- PluginExecution parsePluginExecution( XmlPullParser, boolean, InputSource )

	/**
	 * Method parsePluginManagement.
	 * @param parser
	 * @param source
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return PluginManagement
	 */
	private PluginManagement parsePluginManagement(XmlPullParser parser, boolean strict, InputSource source) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		PluginManagement pluginManagement = new PluginManagement();
		InputLocation _location;
		_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
		pluginManagement.setLocation("", _location);
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
						plugins.add(parsePlugin(parser, strict, source));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return pluginManagement;
	} // -- PluginManagement parsePluginManagement( XmlPullParser, boolean, InputSource )

	/**
	 * Method parsePrerequisites.
	 * @param parser
	 * @param source
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return Prerequisites
	 */
	private Prerequisites parsePrerequisites(XmlPullParser parser, boolean strict, InputSource source) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		Prerequisites prerequisites = new Prerequisites();
		InputLocation _location;
		_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
		prerequisites.setLocation("", _location);
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
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				prerequisites.setLocation("maven", _location);
				prerequisites.setMaven(interpolatedTrimmed(parser.nextText(), "maven"));
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return prerequisites;
	} // -- Prerequisites parsePrerequisites( XmlPullParser, boolean, InputSource )

	/**
	 * Method parseProfile.
	 * @param parser
	 * @param source
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return Profile
	 */
	private Profile parseProfile(XmlPullParser parser, boolean strict, InputSource source) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		Profile profile = new Profile();
		InputLocation _location;
		_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
		profile.setLocation("", _location);
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
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				profile.setLocation("id", _location);
				profile.setId(interpolatedTrimmed(parser.nextText(), "id"));
			} else if (checkFieldWithDuplicate(parser, "activation", null, parsed)) {
				profile.setActivation(parseActivation(parser, strict, source));
			} else if (checkFieldWithDuplicate(parser, "build", null, parsed)) {
				profile.setBuild(parseBuildBase(parser, strict, source));
			} else if (checkFieldWithDuplicate(parser, "modules", null, parsed)) {
				java.util.List<String> modules = new java.util.ArrayList<String>();
				profile.setModules(modules);
				InputLocation _locations;
				_locations = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				profile.setLocation("modules", _locations);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("module".equals(parser.getName())) {
						_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
						_locations.setLocation(Integer.valueOf(modules.size()), _location);
						modules.add(interpolatedTrimmed(parser.nextText(), "modules"));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "distributionManagement", null, parsed)) {
				profile.setDistributionManagement(parseDistributionManagement(parser, strict, source));
			} else if (checkFieldWithDuplicate(parser, "properties", null, parsed)) {
				InputLocation _locations;
				_locations = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				profile.setLocation("properties", _locations);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					String key = parser.getName();
					_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
					_locations.setLocation(key, _location);
					String value = parser.nextText().trim();
					profile.addProperty(key, value);
				}
			} else if (checkFieldWithDuplicate(parser, "dependencyManagement", null, parsed)) {
				profile.setDependencyManagement(parseDependencyManagement(parser, strict, source));
			} else if (checkFieldWithDuplicate(parser, "dependencies", null, parsed)) {
				java.util.List<Dependency> dependencies = new java.util.ArrayList<Dependency>();
				profile.setDependencies(dependencies);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("dependency".equals(parser.getName())) {
						dependencies.add(parseDependency(parser, strict, source));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "repositories", null, parsed)) {
				java.util.List<Repository> repositories = new java.util.ArrayList<Repository>();
				profile.setRepositories(repositories);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("repository".equals(parser.getName())) {
						repositories.add(parseRepository(parser, strict, source));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "pluginRepositories", null, parsed)) {
				java.util.List<Repository> pluginRepositories = new java.util.ArrayList<Repository>();
				profile.setPluginRepositories(pluginRepositories);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("pluginRepository".equals(parser.getName())) {
						pluginRepositories.add(parseRepository(parser, strict, source));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "reports", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				profile.setLocation("reports", _location);
				profile.setReports(org.codehaus.plexus.util.xml.Xpp3DomBuilder.build(parser, true));
			} else if (checkFieldWithDuplicate(parser, "reporting", null, parsed)) {
				profile.setReporting(parseReporting(parser, strict, source));
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return profile;
	} // -- Profile parseProfile( XmlPullParser, boolean, InputSource )

	/**
	 * Method parseRelocation.
	 * @param parser
	 * @param source
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return Relocation
	 */
	private Relocation parseRelocation(XmlPullParser parser, boolean strict, InputSource source) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		Relocation relocation = new Relocation();
		InputLocation _location;
		_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
		relocation.setLocation("", _location);
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
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				relocation.setLocation("groupId", _location);
				relocation.setGroupId(interpolatedTrimmed(parser.nextText(), "groupId"));
			} else if (checkFieldWithDuplicate(parser, "artifactId", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				relocation.setLocation("artifactId", _location);
				relocation.setArtifactId(interpolatedTrimmed(parser.nextText(), "artifactId"));
			} else if (checkFieldWithDuplicate(parser, "version", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				relocation.setLocation("version", _location);
				relocation.setVersion(interpolatedTrimmed(parser.nextText(), "version"));
			} else if (checkFieldWithDuplicate(parser, "message", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				relocation.setLocation("message", _location);
				relocation.setMessage(interpolatedTrimmed(parser.nextText(), "message"));
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return relocation;
	} // -- Relocation parseRelocation( XmlPullParser, boolean, InputSource )

	/**
	 * Method parseReportPlugin.
	 * @param parser
	 * @param source
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return ReportPlugin
	 */
	private ReportPlugin parseReportPlugin(XmlPullParser parser, boolean strict, InputSource source) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		ReportPlugin reportPlugin = new ReportPlugin();
		InputLocation _location;
		_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
		reportPlugin.setLocation("", _location);
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
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				reportPlugin.setLocation("groupId", _location);
				reportPlugin.setGroupId(interpolatedTrimmed(parser.nextText(), "groupId"));
			} else if (checkFieldWithDuplicate(parser, "artifactId", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				reportPlugin.setLocation("artifactId", _location);
				reportPlugin.setArtifactId(interpolatedTrimmed(parser.nextText(), "artifactId"));
			} else if (checkFieldWithDuplicate(parser, "version", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				reportPlugin.setLocation("version", _location);
				reportPlugin.setVersion(interpolatedTrimmed(parser.nextText(), "version"));
			} else if (checkFieldWithDuplicate(parser, "reportSets", null, parsed)) {
				java.util.List<ReportSet> reportSets = new java.util.ArrayList<ReportSet>();
				reportPlugin.setReportSets(reportSets);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("reportSet".equals(parser.getName())) {
						reportSets.add(parseReportSet(parser, strict, source));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "inherited", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				reportPlugin.setLocation("inherited", _location);
				reportPlugin.setInherited(interpolatedTrimmed(parser.nextText(), "inherited"));
			} else if (checkFieldWithDuplicate(parser, "configuration", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				reportPlugin.setLocation("configuration", _location);
				reportPlugin.setConfiguration(org.codehaus.plexus.util.xml.Xpp3DomBuilder.build(parser, true));
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return reportPlugin;
	} // -- ReportPlugin parseReportPlugin( XmlPullParser, boolean, InputSource )

	/**
	 * Method parseReportSet.
	 * @param parser
	 * @param source
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return ReportSet
	 */
	private ReportSet parseReportSet(XmlPullParser parser, boolean strict, InputSource source) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		ReportSet reportSet = new ReportSet();
		InputLocation _location;
		_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
		reportSet.setLocation("", _location);
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
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				reportSet.setLocation("id", _location);
				reportSet.setId(interpolatedTrimmed(parser.nextText(), "id"));
			} else if (checkFieldWithDuplicate(parser, "reports", null, parsed)) {
				java.util.List<String> reports = new java.util.ArrayList<String>();
				reportSet.setReports(reports);
				InputLocation _locations;
				_locations = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				reportSet.setLocation("reports", _locations);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("report".equals(parser.getName())) {
						_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
						_locations.setLocation(Integer.valueOf(reports.size()), _location);
						reports.add(interpolatedTrimmed(parser.nextText(), "reports"));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "inherited", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				reportSet.setLocation("inherited", _location);
				reportSet.setInherited(interpolatedTrimmed(parser.nextText(), "inherited"));
			} else if (checkFieldWithDuplicate(parser, "configuration", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				reportSet.setLocation("configuration", _location);
				reportSet.setConfiguration(org.codehaus.plexus.util.xml.Xpp3DomBuilder.build(parser, true));
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return reportSet;
	} // -- ReportSet parseReportSet( XmlPullParser, boolean, InputSource )

	/**
	 * Method parseReporting.
	 * @param parser
	 * @param source
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return Reporting
	 */
	private Reporting parseReporting(XmlPullParser parser, boolean strict, InputSource source) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		Reporting reporting = new Reporting();
		InputLocation _location;
		_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
		reporting.setLocation("", _location);
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
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				reporting.setLocation("excludeDefaults", _location);
				reporting.setExcludeDefaults(interpolatedTrimmed(parser.nextText(), "excludeDefaults"));
			} else if (checkFieldWithDuplicate(parser, "outputDirectory", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				reporting.setLocation("outputDirectory", _location);
				reporting.setOutputDirectory(interpolatedTrimmed(parser.nextText(), "outputDirectory"));
			} else if (checkFieldWithDuplicate(parser, "plugins", null, parsed)) {
				java.util.List<ReportPlugin> plugins = new java.util.ArrayList<ReportPlugin>();
				reporting.setPlugins(plugins);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("plugin".equals(parser.getName())) {
						plugins.add(parseReportPlugin(parser, strict, source));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return reporting;
	} // -- Reporting parseReporting( XmlPullParser, boolean, InputSource )

	/**
	 * Method parseRepository.
	 * @param parser
	 * @param source
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return Repository
	 */
	private Repository parseRepository(XmlPullParser parser, boolean strict, InputSource source) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		Repository repository = new Repository();
		InputLocation _location;
		_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
		repository.setLocation("", _location);
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
				repository.setReleases(parseRepositoryPolicy(parser, strict, source));
			} else if (checkFieldWithDuplicate(parser, "snapshots", null, parsed)) {
				repository.setSnapshots(parseRepositoryPolicy(parser, strict, source));
			} else if (checkFieldWithDuplicate(parser, "id", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				repository.setLocation("id", _location);
				repository.setId(interpolatedTrimmed(parser.nextText(), "id"));
			} else if (checkFieldWithDuplicate(parser, "name", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				repository.setLocation("name", _location);
				repository.setName(interpolatedTrimmed(parser.nextText(), "name"));
			} else if (checkFieldWithDuplicate(parser, "url", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				repository.setLocation("url", _location);
				repository.setUrl(interpolatedTrimmed(parser.nextText(), "url"));
			} else if (checkFieldWithDuplicate(parser, "layout", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				repository.setLocation("layout", _location);
				repository.setLayout(interpolatedTrimmed(parser.nextText(), "layout"));
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return repository;
	} // -- Repository parseRepository( XmlPullParser, boolean, InputSource )

	/**
	 * Method parseRepositoryBase.
	 * @param parser
	 * @param source
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return RepositoryBase
	 */
	private RepositoryBase parseRepositoryBase(XmlPullParser parser, boolean strict, InputSource source) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		RepositoryBase repositoryBase = new RepositoryBase();
		InputLocation _location;
		_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
		repositoryBase.setLocation("", _location);
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
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				repositoryBase.setLocation("id", _location);
				repositoryBase.setId(interpolatedTrimmed(parser.nextText(), "id"));
			} else if (checkFieldWithDuplicate(parser, "name", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				repositoryBase.setLocation("name", _location);
				repositoryBase.setName(interpolatedTrimmed(parser.nextText(), "name"));
			} else if (checkFieldWithDuplicate(parser, "url", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				repositoryBase.setLocation("url", _location);
				repositoryBase.setUrl(interpolatedTrimmed(parser.nextText(), "url"));
			} else if (checkFieldWithDuplicate(parser, "layout", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				repositoryBase.setLocation("layout", _location);
				repositoryBase.setLayout(interpolatedTrimmed(parser.nextText(), "layout"));
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return repositoryBase;
	} // -- RepositoryBase parseRepositoryBase( XmlPullParser, boolean, InputSource )

	/**
	 * Method parseRepositoryPolicy.
	 * @param parser
	 * @param source
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return RepositoryPolicy
	 */
	private RepositoryPolicy parseRepositoryPolicy(XmlPullParser parser, boolean strict, InputSource source) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		RepositoryPolicy repositoryPolicy = new RepositoryPolicy();
		InputLocation _location;
		_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
		repositoryPolicy.setLocation("", _location);
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
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				repositoryPolicy.setLocation("enabled", _location);
				repositoryPolicy.setEnabled(interpolatedTrimmed(parser.nextText(), "enabled"));
			} else if (checkFieldWithDuplicate(parser, "updatePolicy", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				repositoryPolicy.setLocation("updatePolicy", _location);
				repositoryPolicy.setUpdatePolicy(interpolatedTrimmed(parser.nextText(), "updatePolicy"));
			} else if (checkFieldWithDuplicate(parser, "checksumPolicy", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				repositoryPolicy.setLocation("checksumPolicy", _location);
				repositoryPolicy.setChecksumPolicy(interpolatedTrimmed(parser.nextText(), "checksumPolicy"));
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return repositoryPolicy;
	} // -- RepositoryPolicy parseRepositoryPolicy( XmlPullParser, boolean, InputSource )

	/**
	 * Method parseResource.
	 * @param parser
	 * @param source
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return Resource
	 */
	private Resource parseResource(XmlPullParser parser, boolean strict, InputSource source) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		Resource resource = new Resource();
		InputLocation _location;
		_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
		resource.setLocation("", _location);
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
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				resource.setLocation("targetPath", _location);
				resource.setTargetPath(interpolatedTrimmed(parser.nextText(), "targetPath"));
			} else if (checkFieldWithDuplicate(parser, "filtering", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				resource.setLocation("filtering", _location);
				resource.setFiltering(interpolatedTrimmed(parser.nextText(), "filtering"));
			} else if (checkFieldWithDuplicate(parser, "directory", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				resource.setLocation("directory", _location);
				resource.setDirectory(interpolatedTrimmed(parser.nextText(), "directory"));
			} else if (checkFieldWithDuplicate(parser, "includes", null, parsed)) {
				java.util.List<String> includes = new java.util.ArrayList<String>();
				resource.setIncludes(includes);
				InputLocation _locations;
				_locations = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				resource.setLocation("includes", _locations);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("include".equals(parser.getName())) {
						_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
						_locations.setLocation(Integer.valueOf(includes.size()), _location);
						includes.add(interpolatedTrimmed(parser.nextText(), "includes"));
					} else {
						checkUnknownElement(parser, strict);
					}
				}
			} else if (checkFieldWithDuplicate(parser, "excludes", null, parsed)) {
				java.util.List<String> excludes = new java.util.ArrayList<String>();
				resource.setExcludes(excludes);
				InputLocation _locations;
				_locations = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				resource.setLocation("excludes", _locations);
				while (parser.nextTag() == XmlPullParser.START_TAG) {
					if ("exclude".equals(parser.getName())) {
						_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
						_locations.setLocation(Integer.valueOf(excludes.size()), _location);
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
	} // -- Resource parseResource( XmlPullParser, boolean, InputSource )

	/**
	 * Method parseScm.
	 * @param parser
	 * @param source
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return Scm
	 */
	private Scm parseScm(XmlPullParser parser, boolean strict, InputSource source) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		Scm scm = new Scm();
		InputLocation _location;
		_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
		scm.setLocation("", _location);
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
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				scm.setLocation("connection", _location);
				scm.setConnection(interpolatedTrimmed(parser.nextText(), "connection"));
			} else if (checkFieldWithDuplicate(parser, "developerConnection", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				scm.setLocation("developerConnection", _location);
				scm.setDeveloperConnection(interpolatedTrimmed(parser.nextText(), "developerConnection"));
			} else if (checkFieldWithDuplicate(parser, "tag", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				scm.setLocation("tag", _location);
				scm.setTag(interpolatedTrimmed(parser.nextText(), "tag"));
			} else if (checkFieldWithDuplicate(parser, "url", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				scm.setLocation("url", _location);
				scm.setUrl(interpolatedTrimmed(parser.nextText(), "url"));
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return scm;
	} // -- Scm parseScm( XmlPullParser, boolean, InputSource )

	/**
	 * Method parseSite.
	 * @param parser
	 * @param source
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return Site
	 */
	private Site parseSite(XmlPullParser parser, boolean strict, InputSource source) throws IOException, XmlPullParserException {
		String tagName = parser.getName();
		Site site = new Site();
		InputLocation _location;
		_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
		site.setLocation("", _location);
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
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				site.setLocation("id", _location);
				site.setId(interpolatedTrimmed(parser.nextText(), "id"));
			} else if (checkFieldWithDuplicate(parser, "name", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				site.setLocation("name", _location);
				site.setName(interpolatedTrimmed(parser.nextText(), "name"));
			} else if (checkFieldWithDuplicate(parser, "url", null, parsed)) {
				_location = new InputLocation(parser.getLineNumber(), parser.getColumnNumber(), source);
				site.setLocation("url", _location);
				site.setUrl(interpolatedTrimmed(parser.nextText(), "url"));
			} else {
				checkUnknownElement(parser, strict);
			}
		}
		return site;
	} // -- Site parseSite( XmlPullParser, boolean, InputSource )

	/**
	 * Method read.
	 * @param parser
	 * @param source
	 * @param strict
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @return Model
	 */
	private Model read(XmlPullParser parser, boolean strict, InputSource source) throws IOException, XmlPullParserException {
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
				model = parseModel(parser, strict, source);
				model.setModelEncoding(parser.getInputEncoding());
				parsed = true;
			}
			eventType = parser.next();
		}
		if (parsed) {
			return model;
		}
		throw new XmlPullParserException("Expected root element 'project' but found no element at all: invalid XML document", parser, null);
	} // -- Model read( XmlPullParser, boolean, InputSource )

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
