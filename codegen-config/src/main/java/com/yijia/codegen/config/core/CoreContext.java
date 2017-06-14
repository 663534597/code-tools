package com.yijia.codegen.config.core;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.EnvironmentConfiguration;
import org.apache.commons.configuration.SystemConfiguration;
import org.apache.commons.lang.StringUtils;

import com.yijia.codegen.models.datasource.Persistence;

public abstract class CoreContext {

	protected long timestamp = System.currentTimeMillis();

	protected CompositeConfiguration config;
	protected Persistence persistence;
	protected String persistencePath;

	protected String mode = "CLOUD";
	protected String group;
	protected String baseDir;
	protected String workDir;

	public CoreContext() {
		config = new CompositeConfiguration();
		config.addConfiguration(new SystemConfiguration());
		config.addConfiguration(new EnvironmentConfiguration());
	}

	public void init(Configuration configuration) {
		if (null == configuration || configuration.isEmpty())
			return;
		config.addConfiguration(configuration, true);
	}

	public void setPersistence(Persistence persistence) {
		if (null != persistence)
			this.persistence = persistence;
	}

	public void setPersistencePath(String persistencePath) {
		if (StringUtils.isNotBlank(persistencePath))
			this.persistencePath = persistencePath;
	}

	public void setMode(String mode) {
		if (StringUtils.isNotBlank(mode))
			this.mode = mode;
	}

	public void setGroup(String group) {
		if (StringUtils.isNotBlank(group))
			this.group = group;
	}

	public void setBaseDir(String baseDir) {
		if (StringUtils.isBlank(baseDir))
			throw new IllegalArgumentException("The BASE directory is not empty");
		this.baseDir = baseDir;
	}

	public void setWorkDir(String workDir) {
		if (StringUtils.isBlank(workDir))
			throw new IllegalArgumentException("The WORK directory is not empty");
		this.workDir = workDir;
	}

}
