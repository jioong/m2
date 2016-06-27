package com.github.jioong.jmockit;

import mockit.Mock;
import mockit.MockUp;
import org.junit.Test;
import org.junit.validator.PublicClassValidator;

public class JmockitTest {
    @Test
    public void testMokcUp() {
        new MockUp<Foo>() {
            @Mock
            public void say() {
                System.out.println("in mock one...");
            }
        };
        Foo foo = new Foo();
        System.out.print("testMockUp:");
        foo.say();
    }

    @Test
    public void testReal() {
        Foo foo = new Foo();
        System.out.print("testReal:");
        foo.say();
    }
}

class Foo {
    public void say() {
        System.out.println("in real one...");
    }
}