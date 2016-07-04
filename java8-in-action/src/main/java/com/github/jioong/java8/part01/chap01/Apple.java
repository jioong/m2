package com.github.jioong.java8.part01.chap01;

import com.github.jioong.java8.part01.chap02.ApplePredicate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Apple {
    private String color;
    private int weight;

    public Apple(String color, int weight) {
        this.color = color;
        this.weight = weight;
    }

    public static List<Apple> filterApple(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();

        for(Apple apple : inventory) {
            if(p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public String getColor() {
        return color;
    }

    public int getWeight() {
        return weight;
    }
}
