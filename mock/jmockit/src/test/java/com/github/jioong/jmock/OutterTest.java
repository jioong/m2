package com.github.jioong.jmock;

import com.github.jioong.jmockit.nest.Inner;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Test;

public class OutterTest {

    @Mocked
    private Inner inner = new Inner();

    @Test
    public void testGetInfo() {
        new Expectations(Inner.class) {
        };
    }
}
