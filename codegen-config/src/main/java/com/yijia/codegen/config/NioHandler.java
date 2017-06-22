package com.yijia.codegen.config;

import java.io.IOException;
import java.nio.channels.SelectionKey;

public interface NioHandler {
    void handler(SelectionKey key) throws IOException;
}
