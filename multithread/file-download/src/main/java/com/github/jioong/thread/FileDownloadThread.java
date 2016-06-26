package com.github.jioong.thread;

import com.github.jioong.util.FileDownloader;

import java.io.*;

public class FileDownloadThread implements Runnable {

    private String readFrom;
    private String writeTo;
    private int index;
    private long length;


    public FileDownloadThread(String readFrom, String writeTo, int index, long length) {
        this.readFrom = readFrom;
        this.writeTo = writeTo;
        this.index = index;
        this.length = length;
    }

    public void run() {
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(readFrom)));
            RandomAccessFile raf = new RandomAccessFile(writeTo, "rw");

            long writeOffset = index * length;
            bis.skip(writeOffset);
            byte[] buff = new byte[1024];
            int times = (int) (length % 1024 == 0 ? length / 1024 : length / 1024 + 1);
            while(--times >= 0) {
                int len = bis.read(buff);
                raf.seek(writeOffset);
                raf.write(buff);
                writeOffset += len;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
