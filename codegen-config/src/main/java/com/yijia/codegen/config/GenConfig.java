/**
 * 
 */
package com.yijia.codegen.config;

import com.yijia.codegen.core.Config;

/**
 * @ClassName: GenConfig
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author chengj@justep.com
 * @date 2017-06-09 17:57:37
 */
public class GenConfig implements Config {

	/**
	 * 
	 */
	private GenConfig() {
		// TODO Auto-generated constructor stub
	}

	private static GenConfig config;
	public static synchronized void init(String... options) {
		if(null != config)
			throw new IllegalStateException("");
		config = new GenConfig();
	}
	public GenConfig getConfig() {
		if (null == config)
			throw new IllegalAccessError("服务未被初始化");
		return config;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
