package com.hazendaz.beans;

import org.junit.Assert;
import org.junit.Test;

public class CsveedExampleTest {

    CsveedExample csveedExample = new CsveedExample();

    @Test
    public void csvExample() {
        Assert.assertEquals(1, this.csveedExample.csvExample());
    }

}
