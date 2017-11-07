package com.hazendaz.beans;

import mockit.Deencapsulation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.LoggerFactory;

public class LogonBeanTest {

    LogonBean logonBean;

    @Test
    public void clearTest() {
        final LogonBean test = new LogonBean();
        Deencapsulation.setField(this.logonBean, "logger", LoggerFactory.getLogger("testClass"));
        this.logonBean.clear();
        Assert.assertEquals(test.getUserName(), this.logonBean.getUserName());
    }

    @Before
    public void init() {
        this.logonBean = new LogonBean();
    }

}
