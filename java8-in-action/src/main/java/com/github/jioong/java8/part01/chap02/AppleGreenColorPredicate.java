package com.github.jioong.java8.part01.chap02;

import com.github.jioong.java8.part01.chap01.Apple;

public class AppleGreenColorPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return "green".equals(apple.getColor());
    }
}
