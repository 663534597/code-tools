package com.yijia.codegen.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.yijia.codegen.net.handler.NIOHandlerBuilder;

public class NIOServer {

	private static final int DEFAULT_PORT = 8088;
	private static final long DEFAULT_TIMEOUT = 5000L;

	private boolean active = false;

	private ExecutorService executor;
	private InetSocketAddress address;

	private Selector selector;
	private ServerSocketChannel channel;

	public NIOServer() {
		address = new InetSocketAddress("127.0.0.1", DEFAULT_PORT);
	}

	public NIOServer(int port) {
		address = new InetSocketAddress("127.0.0.1", port);
	}

	private void init() throws IOException {

		this.selector = Selector.open();
		this.channel = ServerSocketChannel.open();
		this.executor = Executors.newFixedThreadPool(3);

		// 绑定到指定端口
		channel.socket().bind(address);
		// 非阻塞模式（必须）
		channel.configureBlocking(false);
		// 通道注册到选择器中。是通道的方法，不是选择器的。
		channel.register(selector, SelectionKey.OP_ACCEPT);

		// 当主线程关闭后
		Runtime.getRuntime().addShutdownHook(new Defenders());
	}

	private void excute(Iterator<SelectionKey> keys) {
		this.executor.execute(new Runnable() {
			@Override
			public void run() {
				while (keys.hasNext()) {
					SelectionKey key = keys.next();
					try {
						// key 关联的通道已准备好接收套接字连接（当客户端connect服务端时）
						if (key.isAcceptable())
							NIOHandlerBuilder.getHandler("accept").handler(key);

						// key 关联的通道已准备好读取（客户端发送的数据已到达）
						if (key.isReadable())
							NIOHandlerBuilder.getHandler("reader").handler(key);

						// key 有效（自key创建后，至调用cancel()、或通道关闭，或selector关闭之前），且通道已准备好写（客户端读取服务端返回）
						if (key.isValid() && key.isWritable())
							NIOHandlerBuilder.getHandler("writer").handler(key);

					} catch (Exception e) {
						e.printStackTrace();
					}
					// 每次select()都会append新的SelectionKey，所以移除已处理的，防止下次重复处理
					keys.remove();
				}
			}
		});
	}

	public void startup() throws IOException {
		this.active = true;
		this.init();

		while (active) {
			if (selector.select(DEFAULT_TIMEOUT) == 0)
				continue;

			this.excute(selector.selectedKeys().iterator());
		}
	}

	public void shutdown() {
		this.active = false;
		try {
			if (channel.isOpen())
				channel.close();
			if (selector.isOpen())
				selector.close();
		} catch (Exception e) {
		}
	}

	public void restart() throws IOException {
		this.shutdown();
		this.startup();
	}

	class Defenders extends Thread {
		@Override
		public void run() {
			NIOServer.this.shutdown();
		}
	}

	public static void main(String[] args) {
		NIOServer server = new NIOServer(8081);
		try {
			server.startup();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}