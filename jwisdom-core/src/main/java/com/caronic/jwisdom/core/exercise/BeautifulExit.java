package com.caronic.jwisdom.core.exercise;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * There are several jobs in a queue, when system is forced to shut down, some of jobs may be not executed.
 * to resolve this, we can create a shutdown hook to do something before system exits.
 * Created by caronic on 2016/6/21.
 */
public class BeautifulExit {

    public static void main(String[] args) throws Exception{

        // add a shutdown hook in case system was shut down unexpected
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                //if(!queue.isEmpty()) {
//                    System.out.println("still has " + queue.size() + " to be consumed.");
                System.out.println("hook invocation");
               // }
            }
        });

        BeautifulExit instance = new BeautifulExit();

        BlockingQueue<Message> queue = new LinkedBlockingDeque<>(10);
        // produce some messages and put them into the queue
        for (int i=0; i<10; i++) {
            queue.put(instance.produceMessage());
        }

        ExecutorService es = Executors.newFixedThreadPool(1);
//        while (!"hello 9".equalsIgnoreCase(message = queue.take().getMessage())) {
            es.submit(() -> {
                try {
                    while(!queue.isEmpty()) {
                        // Suppose it took 5 seconds to print the message
                        Thread.sleep(1000);
                        System.out.println(queue.take().getMessage());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
//        }
        es.shutdown();
    }

    private AtomicInteger id = new AtomicInteger(0);

    class Message {
        int msgId;
        String message;

        public int getMsgId() {
            return msgId;
        }

        public void setMsgId(int msgId) {
            this.msgId = msgId;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    private Message produceMessage() {
//        Random randomNumber = new Random();
        Message message = new Message();
        message.setMsgId(id.getAndIncrement());
        message.setMessage("hello " + message.getMsgId());
        return message;
    }

}
