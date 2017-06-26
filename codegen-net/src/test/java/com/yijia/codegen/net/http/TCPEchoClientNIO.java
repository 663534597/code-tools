package com.yijia.codegen.net.http;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class TCPEchoClientNIO {

	public static void main(String[] args) throws IOException {
		args = new String[3];
		args[0] = "localhost";
		args[1] = "Hello NIO";
		args[2] = "4451";

		String server = args[0];

		byte[] words = args[1].getBytes(); // 待发送字符串的字节数组形式

		int servPort = args.length == 3 ? Integer.parseInt(args[2]) : 4451;

		// 静态工厂方法创建通道，设置为非阻塞模式
		SocketChannel clntChan = SocketChannel.open();
		clntChan.configureBlocking(false);

		// 初始化连接，并等待连接过程结束
		// 调用非阻塞通道的方法总是立即返回而不管是否完成，所以要采用轮询方式获取状态
		if (!clntChan.connect(new InetSocketAddress(server, servPort))) { // 触发服务端通道的OP_ACCEPT
			while (!clntChan.finishConnect()) {
				System.out.println("Wait for connecting finished");
			}
		}

		ByteBuffer writeBuf = ByteBuffer.wrap(words); // 发送缓冲区
		ByteBuffer readBuf = ByteBuffer.allocate(words.length); // 接收缓冲区，长度和发送数据相同（回显）
		int totalBytesRcvd = 0;
		int bytesRcvd;
		while (totalBytesRcvd < words.length) {
			if (writeBuf.hasRemaining()) { // 发送缓冲区还有数据
				clntChan.write(writeBuf); // 向服务器发送数据，触发通道的OP_READ
			}
			bytesRcvd = clntChan.read(readBuf); // 获取服务端返回的数据，触发通道的OP_WRITE
			totalBytesRcvd += bytesRcvd;
			System.out.println("Receiving " + bytesRcvd + " bytes");
		}

		System.out.println("Received: " + new String(readBuf.array(), 0, totalBytesRcvd)); // 转化为字符串

		clntChan.close(); // 关闭通道，触发通道的OP_READ，数据为空
	}
}