package com.yijia.codegen.config.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class GenerateHTTPProcess implements Runnable {

	private ServerSocket server;

	public GenerateHTTPProcess() throws IOException {
		server = new ServerSocket(8888);
	}

	@Override
	public void run() {
		try {

			while (true) {
				Socket socket = server.accept();
				BufferedReader bd = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				/**
				 * 接受HTTP请求
				 */
				String requestHeader;
				int contentLength = 0;
				while ((requestHeader = bd.readLine()) != null && !requestHeader.isEmpty()) {
					System.out.println(requestHeader);
					/**
					 * 获得GET参数
					 */
					if (requestHeader.startsWith("GET")) {
						int begin = requestHeader.indexOf("/?") + 2;
						int end = requestHeader.indexOf("HTTP/");
						String condition = requestHeader.substring(begin, end);
						System.out.println("GET参数是：" + condition);
					}
					/**
					 * 获得POST参数 1.获取请求内容长度
					 */
					if (requestHeader.startsWith("Content-Length")) {
						int begin = requestHeader.indexOf("Content-Lengh:") + "Content-Length:".length();
						String postParamterLength = requestHeader.substring(begin).trim();
						contentLength = Integer.parseInt(postParamterLength);
						System.out.println("POST参数长度是：" + Integer.parseInt(postParamterLength));
					}
				}
				StringBuffer sb = new StringBuffer();
				if (contentLength > 0) {
					for (int i = 0; i < contentLength; i++) {
						sb.append((char) bd.read());
					}
					System.out.println("POST参数是：" + sb.toString());
				}
				// 发送回执
				PrintWriter pw = new PrintWriter(socket.getOutputStream());

				pw.println("HTTP/1.1 200 OK");
				pw.println("Content-type:text/html");
				pw.println();
				pw.println("<h1>访问成功！</h1>");

				pw.flush();
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void doGET() {
		// TODO Auto-generated method stub

	}
	
	private void doPOST() {
		// TODO Auto-generated method stub

	}
}
