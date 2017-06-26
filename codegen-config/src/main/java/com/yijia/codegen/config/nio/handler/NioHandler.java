package com.yijia.codegen.config.nio.handler;

import java.io.IOException;
import java.nio.channels.SelectionKey;

public interface NioHandler {
    void handler(SelectionKey key) throws IOException;
}
