package com.hazendaz.beans;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;
import org.slf4j.LoggerFactory;

public class LogonBeanTest {

    LogonBean logonBean;

    @Test
    public void clearTest() {
        final LogonBean test = new LogonBean();
        Whitebox.setInternalState(this.logonBean, "logger", LoggerFactory.getLogger("testClass"));
        this.logonBean.clear();
        Assert.assertEquals(test.getUserName(), this.logonBean.getUserName());
    }

    @Before
    public void init() {
        this.logonBean = new LogonBean();
    }

}
