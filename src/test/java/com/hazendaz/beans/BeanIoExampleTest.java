package com.hazendaz.beans;

import org.junit.Assert;
import org.junit.Test;

public class BeanIoExampleTest {

    BeanIoExample beanIoExample = new BeanIoExample();

    @Test
    public void csvExample() {
        Assert.assertEquals(2, this.beanIoExample.csvExample());
    }

}
