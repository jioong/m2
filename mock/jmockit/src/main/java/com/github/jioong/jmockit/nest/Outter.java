package com.github.jioong.jmockit.nest;

public class Outter {
    public static String getInfo() {
        System.out.println("getInfo in Outter.class");
        return Inner.getName();
    }
}
