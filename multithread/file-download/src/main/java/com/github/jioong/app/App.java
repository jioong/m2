package com.github.jioong.app;

import com.github.jioong.util.FileDownloader;

public class App {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            long start = System.currentTimeMillis();
            new FileDownloader().download("pom.xml");
            long end = System.currentTimeMillis();
            System.out.println("线程数为" + (100 * i) + " :" + (end - start));
        }
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
