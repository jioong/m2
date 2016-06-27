package com.github.jioong.jmock;

import com.github.jioong.jmockit.MethodToBeMocked;
import com.github.jioong.jmockit.mock.MockMethodToBeMocked;
import org.junit.Assert;
import org.junit.Test;

public class MethodToBeMockedTest {

    @Test
    public void testGetName() {
        new MockMethodToBeMocked("a");
        MethodToBeMocked m = new MethodToBeMocked();
        Assert.assertEquals(m.getName(), "hello");
    }
}
