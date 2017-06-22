package com.yijia.codegen.config.nio2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EchoServer {

	private static final int DEFAULT_POOL_SIZE = 4;

	private int port = 8000;
	private ServerSocketChannel serverSocketChannel;
	private ExecutorService executorService;

	public EchoServer() throws Exception {
		executorService = Executors.newFixedThreadPool(DEFAULT_POOL_SIZE);
		serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.configureBlocking(false);
		serverSocketChannel.socket().bind(new InetSocketAddress(port));
		System.out.println("等待连接...");
	}

	public void service() {
		SocketChannel socketChannel = null;
		while (true) {
			try {
				System.out.println("waitting for connect...");
				socketChannel = serverSocketChannel.accept();
				System.out.println("get the connect...");

				executorService.execute(new Handler(socketChannel));
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	public static void main(String[] args) throws Exception {
		new EchoServer().service();
	}

	class Handler implements Runnable {
		private SocketChannel socketChannel = null;

		public Handler(SocketChannel socketChannel) {
			this.socketChannel = socketChannel;
		}

		@Override
		public void run() {
			handler(socketChannel);
		}

		private void handler(SocketChannel socketChannel) {
			Socket socket = socketChannel.socket();
			System.out.printf("new connection accepted %s:%d%n", socket.getInetAddress(), socket.getPort());
			try {
				BufferedReader reader = SocketUtils.getReader(this.socketChannel);
				PrintWriter pw = SocketUtils.getWriter(this.socketChannel);
				String msg = null;
				while (true) {
					if ((msg = reader.readLine()) != null) {
						try {
							Thread.sleep(20000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println(msg);
						// pw.write(SocketUtils.echo(msg)+"\r\n");
						pw.println(SocketUtils.echo(msg));
						pw.flush();
						if ("bye".equals(msg)) {
							break;
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				SocketUtils.close(socketChannel);
			}
		}
	}
}
