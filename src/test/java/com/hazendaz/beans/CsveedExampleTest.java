package com.hazendaz.beans;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CsveedExampleTest {

    CsveedExample csveedExample = new CsveedExample();

    @Test
    public void csvExample() {
        Assertions.assertEquals(1, this.csveedExample.csvExample());
    }

}
