package com.github.jioong.jmockit.nest;

import com.sun.istack.internal.NotNull;

public class Inner {
    @NotNull public  static String getName() {
        System.out.println("getName() in Inner.class");
        return "jioong";
    }
}
