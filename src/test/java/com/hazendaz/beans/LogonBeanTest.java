package com.hazendaz.beans;

import mockit.Deencapsulation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

public class LogonBeanTest {

    LogonBean logonBean;

    @Test
    public void clearTest() {
        final LogonBean test = new LogonBean();
        Deencapsulation.setField(this.logonBean, "logger", LoggerFactory.getLogger("testClass"));
        this.logonBean.clear();
        Assertions.assertEquals(test.getUserName(), this.logonBean.getUserName());
    }

    @BeforeEach
    public void init() {
        this.logonBean = new LogonBean();
    }

}
