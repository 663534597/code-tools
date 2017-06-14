package com.yijia.codegen.config.process;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yijia.codegen.config.core.Command;


public class GenerateSocketProcess implements Runnable {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private int port = 39001;

	private ServerSocket serverSocket = null;
	private int defaultMaxLen = 1024;
	private int timeout = 10000;

	@Override
	public void run() {

		try {
			serverSocket = new ServerSocket(port, 1);
		} catch (IOException e) {
			logger.error("socket error", e);
			System.exit(1);
		}

		logger.info("LOGGER> console listening port: {}", port);
		while (true) {
			try {
				processCommand();
			} catch (Throwable throwable) {
				logger.error("PROCESS ERR!!", throwable);
			}
		}

	}

	public void processCommand() throws Exception {
		Socket socket = null;
		socket = serverSocket.accept();
		socket.setSoTimeout(timeout);
		InputStream input = socket.getInputStream();
		OutputStream output = socket.getOutputStream();

		StringBuffer command = new StringBuffer();
		int maxLen = defaultMaxLen;

		while (maxLen > 0) {
			int ch = -1;
			try {
				ch = input.read();
			} catch (IOException e) {
				logger.error("读取错误", e);
				ch = -1;
			}
			if (ch < 32) // Control character or EOF terminates loop
				break;
			command.append((char) ch);
			maxLen--;
		}

		Command.newInstance(command.toString().trim()).execute(output);

		try {
			socket.close();
		} catch (IOException e) {
			logger.error("socket error", e);
		}
	}

}
