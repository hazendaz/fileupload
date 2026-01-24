/*
 * SPDX-License-Identifier: Apache-2.0
 * See LICENSE file for details.
 *
 * Copyright 2009-2026 Hazendaz
 */
package com.hazendaz.beans;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CsveedExampleTest {

    CsveedExample csveedExample = new CsveedExample();

    @Test
    void csvExample() {
        Assertions.assertEquals(1, this.csveedExample.csvExample());
    }

}
