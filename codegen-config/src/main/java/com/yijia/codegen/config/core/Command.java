package com.yijia.codegen.config.core;

import java.io.OutputStream;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.yijia.codegen.config.GenServer;

public enum Command {

	GC {
		@Override
		public void execute(OutputStream response) throws Exception {
			response.write(("cmd not found. should be: stop | status | gc").getBytes());
			response.flush();
		}
	},
	ERR {
		@Override
		public void execute(OutputStream response) throws Exception {
			System.gc();
			response.write("gc committed".getBytes());
			response.flush();
		}
	},
	STATUS {
		@Override
		public void execute(OutputStream response) throws Exception {
			Map<String, Object> status = GenServer.getInstance().getStatus();
			response.write(JSONObject.toJSONBytes(status));
			response.flush();
		}
	},
	SHUTDOWN {
		@Override
		public void execute(OutputStream response) throws Exception {
			response.write("shutdown server committed".getBytes());
			response.flush();
		}
	};

	public abstract void execute(OutputStream response) throws Exception;

	public static Command newInstance(String cmd) {
		try {
			return Command.valueOf(cmd.toUpperCase());
		} catch (Exception e) {
			return Command.ERR;
		}
	}
}
