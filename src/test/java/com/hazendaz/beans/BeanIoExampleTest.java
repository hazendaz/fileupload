package com.hazendaz.beans;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BeanIoExampleTest {

    BeanIoExample beanIoExample = new BeanIoExample();

    @Test
    public void csvExample() {
        Assertions.assertEquals(2, this.beanIoExample.csvExample());
    }

}
