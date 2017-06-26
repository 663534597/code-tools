package com.yijia.codegen.config.nio.handler.impl;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

import com.yijia.codegen.config.nio.handler.NioHandler;

public class WriteHandlerImpl implements NioHandler {

    @Override
    public void handler(SelectionKey key) throws IOException {
        ByteBuffer buf = (ByteBuffer) key.attachment();
        // 从先前写入的起始位置读取
        buf.flip();
        SocketChannel clntChan = (SocketChannel) key.channel();
        // 将客户端发送的数据写回客户端，完成“回显”功能
        clntChan.write(buf);
        // 缓冲区没有数据
        if (!buf.hasRemaining()) {
            // 不需要再往通道写，只对读事件（客户端又发送数据到服务端）感兴趣
            key.interestOps(SelectionKey.OP_READ);
        }
        // 缓冲区剩余的数据，移动到缓冲区的起始部位，给下次存放数据腾出空间
        buf.compact();
    }
}