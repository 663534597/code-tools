package com.yijia.codegen.net.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HttpServerInvoke implements Runnable {

	private final Socket socket;

	public HttpServerInvoke(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		BufferedReader reader = null;
		PrintWriter writer = null;
		try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new PrintWriter(socket.getOutputStream());

			boolean flag = true;
			String line = reader.readLine();
			HttpMessage message = new HttpMessage(line);
			while ((line = reader.readLine()) != null) {
				if ("".equals(line)) {
					flag = false;
					continue;
				}
				if (flag) {
					message.readHead(line);
				} else
					message.readBody(line);
			}
		} catch (Exception e) {
			writer.println(e);
			writer.flush();
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
			}
			try {
				writer.close();
				if (socket.isClosed())
					socket.close();
			} catch (Exception e) {
			}
		}
	}

}
