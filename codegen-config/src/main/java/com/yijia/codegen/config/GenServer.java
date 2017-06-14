/**
 * 
 */
package com.yijia.codegen.config;

import com.yijia.codegen.core.Server;

/**
 * @ClassName: Bootstrap
 * @Description: CodeGen 服务 main
 * @author qc_chen@qq.com
 * @date 2017-06-09 17:57:37
 */
public class GenServer extends GenConfig implements Server {

	/**
	 * 
	 */
	private GenServer() {}

	private boolean started = false;

	@Override
	public void start() throws Exception {
		if (started)
			return;
		started = true;

		// TODO 启动方法
		while (started) {
			
		}
	}

	@Override
	public void shutdown() throws Exception {
		if (!started)
			started = false;
	}

	private static GenServer generate;

	public static synchronized GenServer getInstance() {
		if (null == generate) {
			generate = new GenServer();
		}
		return generate;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Server genserver = GenServer.getInstance();
			genserver.start();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

}
