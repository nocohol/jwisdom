package com.caronic.jwisdom.core.exercise.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by caronic on 2016/6/19.
 */
public class NIOClient {

    private Selector selector;

    public void initClient(String ip, int port) throws Exception {
        // create a client socket channel
        SocketChannel channel = SocketChannel.open();
        // set it to non-blocking
        channel.configureBlocking(false);
        // get a selector
        this.selector = Selector.open();
        // connect to server, actually before invoke channel.finishConnect() method,
        // it doesn't connect to server.
        channel.connect(new InetSocketAddress(ip, port));
        // Register channel to selector and bind to OP_CONNECT event
        channel.register(selector, SelectionKey.OP_CONNECT);
    }

    // Adopt iteration way to listen the events on selector.
    public void listen() throws Exception {
        while (true) {
            selector.select();
            Iterator ite = selector.selectedKeys().iterator();
            while (ite.hasNext()) {
                SelectionKey key = (SelectionKey) ite.next();
                ite.remove();
                if (key.isConnectable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    if (channel.isConnectionPending()) {
                        channel.finishConnect();
                    }
                    channel.configureBlocking(false);
                    // send message to server after connection is established.
                    channel.write(ByteBuffer.wrap(new String("Hello Server").getBytes()));
                    // set the read privilege to accept message from server
                    channel.register(selector, SelectionKey.OP_READ);
                } else {
                    read(key);
                }
            }
        }
    }

    private void read(SelectionKey key) throws Exception{
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(10);
        channel.read(buffer);
        byte[] data = buffer.array();
        String msg = new String(data).trim();
        System.out.println("Client received msg from server: " + msg);
        ByteBuffer outBuffer = ByteBuffer.wrap(msg.getBytes());
        channel.read(outBuffer);
    }

    public static void main(String[] args) throws Exception{
        NIOClient client = new NIOClient();
        client.initClient("127.0.0.1", 8080);
        client.listen();
    }

}
