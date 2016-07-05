package com.github.jioong.java8.part01.chap02;

import com.github.jioong.java8.part01.chap01.Apple;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.Predicate;

public class App {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple("green", 80),
                new Apple("green", 155),
                new Apple("red", 120));
        List<Apple> heavyApples = Apple.filterApple(inventory, new AppleHeavyWeightPredicate());
        List<Apple> greenApples = Apple.filterApple(inventory, new AppleGreenColorPredicate());
        App app = new App();
        app.printApple(heavyApples);
        System.out.println("----------------");
        app.printApple(greenApples);

        Thread t = new Thread(() -> System.out.println(Thread.currentThread().getName()));
        t.start();
        System.out.println(Thread.currentThread().getName());
        Predicate<String> getGreen = app.color("green");
        System.out.println("----------------");
        List<Apple> green = Apple.filterApple(inventory, getGreen);
        app.printApple(green);
    }

    public void printApple(List<Apple> apples) {
        for(Apple a: apples) {
            System.out.println("A apple " + a.getColor() + " " + a.getWeight());
        }
    }

    public Predicate color(String color) {
        Predicate p = (s) -> color.equals(s);
        return p;
    }


}
