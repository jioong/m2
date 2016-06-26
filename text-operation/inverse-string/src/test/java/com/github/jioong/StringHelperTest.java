package com.github.jioong;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class StringHelperTest {

    // 测试输入 null
    @Test
    public void testInverseStringWithNull() {
        String actual = StringHelper.inverseString(null);
        Assert.assertThat(actual, Matchers.equalTo(null));
    }

    // 测试正常输入字符串
    @Test
    public void testInverseStringWithNormal() {
        String actual = StringHelper.inverseString("happy to see you");
        String except = "uoy ees ot yppah";
        Assert.assertThat(actual, Matchers.equalTo(except));
    }

}
