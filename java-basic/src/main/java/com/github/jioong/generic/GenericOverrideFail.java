package com.github.jioong.generic;

import java.util.List;

public class GenericOverrideFail {

    /*
    *  both methods have same erasure
    * */
/*    public static void method(List<String> list) {
        System.out.println("invoke method(List<String> list)");
    }*/

    public static void method(List<Integer> list) {
        System.out.println("invoke method(List<Integer> list)");
    }
}
