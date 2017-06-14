package com.yijia.codegen.config.console;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;

public class Client {

	private int port = 39001;
	private String host = "127.0.0.1";
	private String cmd = "status";

	public Client() {
	}

	public Client(String[] options) {
		for (int i = 0; i < options.length; i++) {
			if ("-h".equalsIgnoreCase(options[i])) {
				this.host = options[++i];
			} else if ("-p".equalsIgnoreCase(options[i])) {
				this.port = Integer.parseInt(options[++i]);
			} else if ("-o".equalsIgnoreCase(options[i])) {
				this.cmd = options[++i];
			}
		}
	}

	public String send() {

		Socket socket = null;
		StringBuffer logger = new StringBuffer();

		try {
			socket = new Socket(host, port);
			socket.setSoTimeout(1000 * 10);
			InputStream input = socket.getInputStream();
			OutputStream output = socket.getOutputStream();

			output.write(cmd.getBytes());
			output.write(3);
			output.flush();

			int ch = -1;
			while (true) {
				try {
					ch = input.read();
				} catch (Exception e) {
					ch = -1;
				}
				if (ch < 0) {
					break;
				}
				logger.append((char) ch);
			}

		} catch (ConnectException ce) {
			logger.append("can not connect to server, is server up?");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (Exception e) {
				}
			}
		}
		return logger.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Client console = new Client(args);
		System.out.println(console.send());
		
	}

}
