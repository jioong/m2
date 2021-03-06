package com.github.jioong.util;

import com.github.jioong.thread.FileDownloadThread;

import java.io.*;

public class FileDownloader implements Downloader {

    private static final int DEFAULT_THREAD_NUMBER = Runtime.getRuntime().availableProcessors() * 2;

    public void download(String readPath) {
        download(readPath, DEFAULT_THREAD_NUMBER);
    }

    public void download(String readPath, int threadNum) {
        File file = new File(readPath);
        String filename = file.getName();
        downloadTo(readPath, filename, threadNum);
    }

    public void downloadTo(String readPath, String storePath) {
        downloadTo(readPath, storePath, DEFAULT_THREAD_NUMBER);
    }

    public void downloadTo(String readPath, String storePath, int threadNum) {
        long length = downloadLengthPerThread(readPath, threadNum);
        for (int i = 0; i < threadNum; i++) {
            //realDownload(readPath, storePath, i);
            Thread t = new Thread(new FileDownloadThread(readPath, storePath, i, (int) length));
            t.start();
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private long downloadLengthPerThread(String readPath, int num) {
        File readFrom = new File(readPath);
        long length = readFrom.length();
        if (!readFrom.exists()) {
            //TODO 应该处理，而不是直接打印异常。用户不友好
            new FileNotFoundException("下载的文件不存在:" + readPath).printStackTrace();
        }
        return length % num == 0 ? length / num : length / num + 1;
    }
}

