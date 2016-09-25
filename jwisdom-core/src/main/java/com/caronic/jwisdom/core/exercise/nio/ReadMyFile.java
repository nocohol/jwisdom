package com.caronic.jwisdom.core.exercise.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by caronic on 2016/9/24.
 */
public class ReadMyFile {

    public static void main(String[] args) throws IOException {
        processWithNIO("myfile.txt");
        processWithIO("myfile2.txt");
    }

    public static void processWithIO(String fileName) throws IOException {
        long start = System.currentTimeMillis();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(ReadMyFile.class.getClassLoader().getResource(fileName).getPath()), 48);
        while (bufferedReader.read() != -1) {
            System.out.println(bufferedReader.readLine());
        }
        System.out.println("Total time with IO: " + (System.currentTimeMillis() - start));
    }

    public static void processWithNIO(String fileName) throws IOException {
        long start = System.currentTimeMillis();
        RandomAccessFile file = new RandomAccessFile(ReadMyFile.class.getClassLoader().getResource(fileName).getPath(), "r");
        FileChannel channel = file.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(32);
        byte[] data = new byte[32];

        // read data into buffer from channel
        int bytesRead = channel.read(buffer);
        StringBuffer sb = new StringBuffer();
        while (bytesRead != -1) {
            buffer.rewind();
            for (int i = 0; i < 32; i++) {
                data[i] = buffer.get(i);
            }

            String strRead = new String(data);
            int lineSeparatorPosition = 0;
            while (strRead.indexOf("\n") > 0) {
                lineSeparatorPosition = lineSeparatorPosition + strRead.indexOf("\n");
                sb.append(strRead.substring(0, lineSeparatorPosition));
                System.out.println(sb.toString());
                sb.setLength(0);
                strRead = strRead.substring(lineSeparatorPosition + 1);
            }

            if (lineSeparatorPosition == 0) {
//                buffer.position(lineSeparatorPosition);
                buffer.clear();
                sb.append(strRead);
            } else {
                buffer.position(lineSeparatorPosition + 1);
                sb.setLength(0);
                buffer.compact();
            }

            bytesRead = channel.read(buffer);

        }
        file.close();
        System.out.println("Total time with NIO: " + (System.currentTimeMillis() - start));
    }

}
