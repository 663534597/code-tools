package com.yijia.codegen.net.handler;

import java.io.IOException;
import java.nio.channels.SelectionKey;

public interface NIOHandler {

	void handler(SelectionKey key) throws IOException;

}
