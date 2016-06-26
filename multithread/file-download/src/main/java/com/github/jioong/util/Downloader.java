package com.github.jioong.util;

import java.io.File;

public interface Downloader {
    void download(String readPath);
    void download(String readPath, int threadNum);
    void downloadTo(String readPath, String storePath);
    void downloadTo(String readPath, String storePath, int threadNum);
}
