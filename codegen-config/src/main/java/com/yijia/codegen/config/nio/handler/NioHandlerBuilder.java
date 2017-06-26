package com.yijia.codegen.config.nio.handler;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.yijia.codegen.config.nio.handler.impl.AcceptHandlerImpl;
import com.yijia.codegen.config.nio.handler.impl.ReadHandlerImpl;
import com.yijia.codegen.config.nio.handler.impl.WriteHandlerImpl;

public class NioHandlerBuilder {

	private boolean stoped = true;
	private Selector selector = null;
	protected ConcurrentLinkedQueue<Runnable> events = new ConcurrentLinkedQueue<Runnable>();

	public NioHandlerBuilder() {
		// TODO Auto-generated constructor stub
	}

	public Selector getSelector() throws IOException {
		if(null == selector)
			selector = Selector.open();
		return selector;
	}

	public void addEvent(Runnable event) {
		if (selector == null) {
			return;
		}

		events.add(event);
		if (stoped == false && selector != null) {
			selector.wakeup();
		}
	}

	public static void cancelKey(SelectionKey key) {
		if (key == null)
			return;
		key.cancel();
		try {
			((SocketChannel) key.channel()).socket().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			key.channel().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Map<String, NioHandler> handlerMap = new HashMap<>();

	private enum HandlerType {
		accept {
			@Override
			NioHandler newInstance() {
				return new AcceptHandlerImpl();
			}
		},
		reader {
			@Override
			NioHandler newInstance() {
				return new ReadHandlerImpl();
			}
		},
		writer {
			@Override
			NioHandler newInstance() {
				return new WriteHandlerImpl();
			}
		};
		abstract NioHandler newInstance();
	}

	public static NioHandler getHandler(String handlerName) {
		NioHandler handler = handlerMap.get(handlerName);
		if (null == handler) {
			handler = HandlerType.valueOf(handlerName).newInstance();
			handlerMap.put(handlerName, handler);
		}
		return handler;
	}
}
