package com.yijia.codegen.config;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class AcceptHandlerImpl implements NioHandler {

    private int BUFFER_SIZE = 256;

    public AcceptHandlerImpl() {
    }

    public AcceptHandlerImpl(int bufferSize) {
        this.BUFFER_SIZE = bufferSize;
    }

    @Override
    public void handler(SelectionKey key) throws IOException {
        SocketChannel channel = ((ServerSocketChannel) key.channel()).accept();
        if (channel != null) {
            // 通道必须为非阻塞模式
            channel.configureBlocking(false);
            // 注册通道的读取事件（当客户端发送数据到服务端时发生），并创建一个缓冲区存放传输数据
            channel.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(BUFFER_SIZE));
        }
    }
}