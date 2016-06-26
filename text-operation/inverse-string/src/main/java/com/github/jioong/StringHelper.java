package com.github.jioong;

public class StringHelper {
    public static String inverseString(String src) {
        if(src == null) {
            return null;
        }
        StringBuffer sb = new StringBuffer(src);
        return sb.reverse().toString();
    }
}
