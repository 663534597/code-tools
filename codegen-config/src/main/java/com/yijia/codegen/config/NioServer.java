package com.yijia.codegen.config;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

public class NioServer {

    private static final int DEFAULT_PORT = 8088;
    private static final long DEFAULT_TIMEOUT = 3000;

    private InetSocketAddress address;
    private boolean active = false;

    private Selector selector;
    private ServerSocketChannel channel;

    public NioServer() {
        address = new InetSocketAddress("127.0.0.1", DEFAULT_PORT);
    }

    public NioServer(int port) {
        address = new InetSocketAddress("127.0.0.1", port);
    }

    public void init() throws IOException {

        selector = Selector.open();
        channel = ServerSocketChannel.open();

        // 绑定到指定端口
        channel.socket().bind(address);
        // 非阻塞模式（必须）
        channel.configureBlocking(false);
        // 通道注册到选择器中。是通道的方法，不是选择器的。
        channel.register(selector, SelectionKey.OP_ACCEPT);

        // 当主线程关闭后
        Runtime.getRuntime().addShutdownHook(new Defenders());
    }

    public void startup() throws IOException {
        this.active = true;
        this.init();

        while (active) {
            if (selector.select(DEFAULT_TIMEOUT) == 0)
                continue;

            // 获取可进行I/O操作的通道的SelectionKey集
            Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
            while (iter.hasNext()) {
                SelectionKey key = iter.next();
                // key关联的通道已准备好接收套接字连接（当客户端connect服务端时）
                if (key.isAcceptable())
                    NioHandlerBuilder.getHandler("accept").handler(key);

                // key关联的通道已准备好读取（客户端发送的数据已到达）
                if (key.isReadable())
                    NioHandlerBuilder.getHandler("reader").handler(key);

                // key有效（自key创建后，至调用cancel()、或通道关闭，或selector关闭之前），且通道已准备好写（客户端读取服务端返回）
                if (key.isValid() && key.isWritable())
                    NioHandlerBuilder.getHandler("writer").handler(key);

                // 每次select()都会append新的SelectionKey，所以移除已处理的，防止下次重复处理
                iter.remove(); 
            }
        }
    }

    public void shutdown() {
        this.active = false;
        try {
            if(channel.isOpen())
                channel.close();
            if(selector.isOpen())
                selector.close();
        }catch (Exception e) {
        }
    }

    public void restart() throws IOException {
        this.shutdown();
        this.startup();
    }

    class Defenders extends Thread {
        @Override
        public void run() {
            NioServer.this.shutdown();
        }
    }

    public static void main(String[] args) {
        NioServer server = new NioServer(8081);
        try {
            server.startup();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}