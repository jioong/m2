package com.github.jioong.ac.CountBits;


import java.util.Arrays;

public class CountBits {
    public int[] countBits(int num) {
        int[] result = new int[num + 1];
        for(int i = 0; i <= num; i ++) {
            result[i] = 0;
        }
        int index = 0;
        int step = 1;
        while (index < num) {
            result = addOneToLastHalf(result, step, num);
            index += step;
            step *= 2;
        }

        return result;
    }

    private int[] addOneToLastHalf(int[] array, int step, int num) {
        for (int i = 0; i < step && (step + i) <= num; i++) {
            array[step + i] = array[i] + 1;
        }
        return array;
    }

    public static void main(String[] args) {
        Arrays.asList(new CountBits().countBits(5)).forEach(System.out::println);
    }
}
