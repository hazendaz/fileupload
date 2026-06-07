/*
 * SPDX-License-Identifier: Apache-2.0
 * See LICENSE file for details.
 *
 * Copyright 2009-2026 Hazendaz
 */
package com.hazendaz.beans;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * The Class InventoryVendorListTest.
 */
class InventoryVendorListTest {

    /**
     * Should start with empty vendor items and zero count.
     */
    @Test
    void shouldStartWithEmptyVendorItemsAndZeroCount() {
        final InventoryVendorList list = new InventoryVendorList();

        Assertions.assertNotNull(list.getVendorItems());
        Assertions.assertEquals(0, list.getCount());
    }

    /**
     * Gets the count should reflect vendor items size.
     */
    @Test
    void getCountShouldReflectVendorItemsSize() {
        final InventoryVendorList list = new InventoryVendorList();
        final List<InventoryVendorItem> items = new ArrayList<>();
        items.add(new InventoryVendorItem());
        items.add(new InventoryVendorItem());
        list.setVendorItems(items);

        Assertions.assertEquals(2, list.getCount());
    }

    /**
     * Gets the count should return zero when vendor items is null.
     */
    @Test
    void getCountShouldReturnZeroWhenVendorItemsIsNull() {
        final InventoryVendorList list = new InventoryVendorList();
        list.setVendorItems(null);

        Assertions.assertEquals(0, list.getCount());
    }
}
