package com.github.jioong.java8.part01.chap02;

import com.github.jioong.java8.part01.chap01.Apple;

public class AppleHeavyWeightPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 150;
    }
}
