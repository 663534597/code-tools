package com.yijia.codegen.config;

import java.util.HashMap;
import java.util.Map;

public class NioHandlerBuilder {

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
        if(null == handler) {
            handler = HandlerType.valueOf(handlerName).newInstance();
            handlerMap.put(handlerName, handler);
        }
        return handler;
    }
}
