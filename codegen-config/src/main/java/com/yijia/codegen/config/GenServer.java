/**
 * 
 */
package com.yijia.codegen.config;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.yijia.codegen.config.nio.handler.NioHandlerBuilder;
import com.yijia.codegen.core.Config;
import com.yijia.codegen.core.Server;

/**
 * @ClassName: Bootstrap
 * @Description: CodeGen 服务 main
 * @author qc_chen@qq.com
 * @date 2017-06-09 17:57:37
 */
public class GenServer implements Server {

	private static final int DEFAULT_PORT = 8088;
	private static final long DEFAULT_TIMEOUT = 3000;

	private final String workdir;

	private long startTime;
	private GenConfig config;

	private boolean started = false;
	private ExecutorService executor;
	private Selector selector;
	private ServerSocketChannel channel;

	private GenServer() {
		this.workdir = System.getProperty("user.dir");
	}

	private void init() throws Exception {
		this.startTime = System.currentTimeMillis();
		this.config = new GenConfig(workdir);

		initExecutor();
		initChannel();
	}

	public void initExecutor() throws Exception {
		if (executor == null) {
			executor = Executors.newFixedThreadPool(3);
		}
	}

	public void initChannel() throws Exception {
		channel = ServerSocketChannel.open();
		channel.socket().bind(new InetSocketAddress("127.0.0.1", DEFAULT_PORT));
		channel.configureBlocking(false);

		selector = Selector.open();
		channel.register(selector, SelectionKey.OP_ACCEPT);
		System.out.printf("server listener provider: %s%n", selector.provider().getClass().getCanonicalName());
	}

	@Override
	public synchronized void start() throws Exception {
		if (started)
			return;
		started = true;

		System.out.printf("working dir: %s%n", workdir);
		this.init();
		System.out.printf("server listener port: %d%n", DEFAULT_PORT);

		final Thread mainT = Thread.currentThread();
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				System.out.println("shut down server... ");
				try {
					mainT.join();
					shutdown();
				} catch (Exception e) {
				}
			}
		});

		System.out.printf("server is up. [StartTime: %tF]%n", startTime);
		while (started && selector != null) {
			if (selector.select(DEFAULT_TIMEOUT) == 0)
				continue;

			Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
			while (iter.hasNext()) {
				SelectionKey key = iter.next();
				if (key.isAcceptable())
					NioHandlerBuilder.getHandler("accept").handler(key);

				if (key.isReadable())
					NioHandlerBuilder.getHandler("reader").handler(key);

				if (key.isValid() && key.isWritable())
					NioHandlerBuilder.getHandler("writer").handler(key);

				iter.remove();
			}
		}

		closeSelector();
		stopExecutor();
	}

	private void stopExecutor() {
		try {
			if (executor != null)
				executor.shutdownNow();// ignore left overs
		} catch (Exception e) {
			e.printStackTrace();
		}
		executor = null;
	}

	private void closeSelector() {
		if (selector != null) {
			try {
				selector.wakeup();
				selector.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				selector = null;
			}
		}
	}

	@Override
	public void shutdown() {
		if (!started)
			started = false;
		if (!executor.isShutdown()) {
			try {
				executor.shutdownNow();
				executor.awaitTermination(500L, TimeUnit.MICROSECONDS);
			} catch (InterruptedException e) {
			}
		}
		if (channel.isOpen()) {
			try {
				channel.close();
			} catch (IOException e1) {
			}
		}
		if (selector.isOpen()) {
			try {
				selector.close();
			} catch (IOException e1) {
			}
		}
		System.out.printf("server is down, bye!! [EndTime: %tF]%n", System.currentTimeMillis());
	}

	public Config getConfig() {
		return config;
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

	public static GenServer getInstance() {
		return ServerHolder.instance;
	}

	private static class ServerHolder {
		public static GenServer instance = new GenServer();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GenServer server = GenServer.getInstance();
		try {
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		} finally {
			server.shutdown();
		}
	}

}
