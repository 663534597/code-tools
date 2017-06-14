package com.yijia.codegen.config;

import java.util.HashMap;
import java.util.Map;

import com.yijia.codegen.core.Config;

public class GenConfig implements Config {

	private final long startTime = System.currentTimeMillis();

	protected GenConfig() {
		// TODO Auto-generated constructor stub
	}

	public Map<String, Object> getStatus() {
		Map<String, Object> info = new HashMap<String, Object>();
		info.put("StartTime", String.format("%tc", startTime));
		long runtime = System.currentTimeMillis() - startTime;
		StringBuffer up = new StringBuffer();
		if (runtime > 86400000)
			up.append(runtime / 86400000).append(" D ");
		if (runtime > 3600000)
			up.append(runtime / 3600000).append(" H ");
		if (runtime > 60000) {
			up.append(runtime / 60000).append(" MIN");
		} else
			up.append(runtime).append(" Millis");
		info.put("UpTime", up.toString());
		info.put("JVM-Max", Runtime.getRuntime().maxMemory());
		info.put("JVM-Total", Runtime.getRuntime().totalMemory());
		info.put("JVM-Free", Runtime.getRuntime().freeMemory());
		return info;
	}

	public void gc() {
		System.gc();
	}

}
