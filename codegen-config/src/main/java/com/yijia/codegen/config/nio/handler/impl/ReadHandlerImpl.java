package com.yijia.codegen.config.nio.handler.impl;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

import com.yijia.codegen.config.nio.handler.NioHandler;

public class ReadHandlerImpl implements NioHandler {

    @Override
    public void handler(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        // 获取key关联的附件，也就是前面创建的缓冲区
        ByteBuffer buf = (ByteBuffer) key.attachment();
        // 将客户端发过来的数据放到缓冲区
        int bytesRead = channel.read(buf);
        // 从客户端读取到的数据为空（当客户端关闭通道时，会触发该读取事件），说明已关闭
        if (bytesRead > 0) {
            // 接收客户端发送数据事件 | 客户端读取服务端返回事件
            key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        } else
            channel.close();
    }

}