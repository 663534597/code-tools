package com.yijia.codegen.net.handler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NIOHandlerBuilder {

	private enum HandlerType {
		accept {
			@Override
			NIOHandler getInstance() {
				return null;
			}
		},
		reader {
			@Override
			NIOHandler getInstance() {
				return null;
			}
		},
		writer {
			@Override
			NIOHandler getInstance() {
				return null;
			}
		};
		abstract NIOHandler getInstance();
	}

	private static Map<String, NIOHandler> cache = new ConcurrentHashMap<String, NIOHandler>();

	private static NIOHandler newInstance(String handlerName) {
		NIOHandler handler = null;
		synchronized (cache) {
			if (cache.containsKey(handlerName)) {
				handler = cache.get(handlerName);
			} else {
				handler = HandlerType.valueOf(handlerName).getInstance();
				cache.put(handlerName, handler);
			}
		}
		return handler;
	}

	public static NIOHandler getHandler(String handlerName) {
		if (cache.containsKey(handlerName)) {
			return cache.get(handlerName);
		} else
			return newInstance(handlerName);
	}

}
