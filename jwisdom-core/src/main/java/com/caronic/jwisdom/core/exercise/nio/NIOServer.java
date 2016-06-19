package com.caronic.jwisdom.core.exercise.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by caronic on 2016/6/19.
 */
public class NIOServer {
    // Channel selector
    private Selector selector;

    public void initServer(int port) throws Exception {
        // create a Socket channel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // set channel to non-blocking
        serverSocketChannel.configureBlocking(false);
        // bind socket to given port number
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        // obtain a channel selector
        this.selector = Selector.open();

        // Register the channel to selector, and bind to OP_ACCEPT event.
        // when the event is triggered, selector.select() will return the channel key who is ready for IO operation
        // whereas it is keeping blocked.
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    // Adopt iteration way to listen the event on the selector
    public void listen() throws Exception {
        // Listen to selector.select();
        while (true) {
            // keep blocking till some registered event is triggered
            selector.select();
            //Iterate selected keys
            Iterator ite = selector.selectedKeys().iterator();
            while (ite.hasNext()) {
                SelectionKey key = (SelectionKey)ite.next();
                // Remove selected key, avoid duplicate process
                ite.remove();
                // if it the event client requires to connect
                if (key.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    // Obtain the communication channel with client
                    SocketChannel channel =  server.accept();
                    // set to non-blocking
                    channel.configureBlocking(false);
                    // send message to client
                    channel.write(ByteBuffer.wrap(new String("Hello Client").getBytes()));
                    // To accept client message, set the readable privilege to the channel.
                    channel.register(this.selector, SelectionKey.OP_READ);
                }
                // else if client message is sent
                else if (key.isReadable()) {
                    read(key);
                }
            }
        }
    }

    private void read(SelectionKey key) throws Exception{
        SocketChannel channel = (SocketChannel)key.channel();
        // create buffer
        ByteBuffer buffer = ByteBuffer.allocate(10);
        // read message into buffer
        channel.read(buffer);
        byte[] data = buffer.array();
        String msg = new String(data).trim();
        System.out.println("server receives from client: " + msg);
        ByteBuffer outBuffer = ByteBuffer.wrap(msg.getBytes());
        channel.write(outBuffer);
    }

    public static void main(String[] args) throws Exception{
        NIOServer server = new NIOServer();
        server.initServer(8080);
        server.listen();
    }

}
