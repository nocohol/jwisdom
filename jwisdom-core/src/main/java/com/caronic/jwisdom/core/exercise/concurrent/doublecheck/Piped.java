package com.caronic.jwisdom.core.exercise.concurrent.doublecheck;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * It illustrates how threads communicate each other via Pipe stream
 * Created by caronic on 2016/7/31.
 */
public class Piped {

    public static void main(String[] args) throws Exception {
        PipedWriter out  = new PipedWriter();
        PipedReader in = new PipedReader();
        // Connect out and in stream together
        out.connect(in);

        Thread printThread = new Thread(new Print(in), "PrintThread");
        printThread.start();

        int receive;
        while ((receive = System.in.read()) != -1) {
            out.write(receive);
        }
    }

    static class Print implements Runnable {

        private PipedReader printer;

        public Print(PipedReader printer) {
            this.printer = printer;
        }

        @Override
        public void run() {
            int receive;
            try {
                while((receive = printer.read()) != -1) {
                    System.out.print((char) receive);
                }
            } catch (IOException e) {
            }
        }
    }

}
