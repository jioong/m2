package com.github.jioong.jmockit.mock;

import com.github.jioong.jmockit.MethodToBeMocked;
import mockit.Mock;
import mockit.MockUp;

public class MockMethodToBeMocked extends MockUp<MethodToBeMocked> {

    private String flag = "";
    @Mock
    public String getName() {
        return "hello";
    }

    public MockMethodToBeMocked(String a) {
        this.flag = a;
    }
}
