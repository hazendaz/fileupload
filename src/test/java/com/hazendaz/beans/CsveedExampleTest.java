/*
 * fileUploadResources (https://github.com/hazendaz/fileUploadResources)
 *
 * Copyright 2009-2023 Hazendaz.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of The Apache Software License,
 * Version 2.0 which accompanies this distribution, and is available at
 * https://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * Contributors:
 *     Hazendaz (Jeremy Landis).
 */
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
