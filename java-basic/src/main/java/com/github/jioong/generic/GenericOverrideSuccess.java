package com.github.jioong.generic;

import java.util.ArrayList;
import java.util.List;

/*
*  《深入理解Java虚拟机》中，说通过给定不同的返回值，可以使得下面可以成功运行。
*  实际上，Java1.8 在 Intellij IDEA 中会检查出错误
*  可能作者的意思是，下面的类编译成class文件之后，是可以正确执行的。
*  因为在Class文件中，只要描述符不是完全一样的两个方法就可以共存。即其他完全相同，
*  但返回值不同的两个方法是可以在一个Class文件中共存的。
* */
public class GenericOverrideSuccess {

    /*public static String method(List<String> list) {
        System.out.println("invoke method(List<String> list)");
        return "";
    }*/

    public static int method(List<Integer> list) {
        System.out.println("invoke method(List<Integer> list)");
        return 1;
    }

    public static void main(String[] args) {
        //method(new ArrayList<String>());
        method(new ArrayList<Integer>());
    }
}
