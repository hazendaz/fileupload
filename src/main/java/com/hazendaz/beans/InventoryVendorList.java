/*
 * SPDX-License-Identifier: Apache-2.0
 * See LICENSE file for details.
 *
 * Copyright 2009-2026 Hazendaz
 */
package com.hazendaz.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * The Class InventoryVendorList.
 */
@Data
public class InventoryVendorList implements Serializable {
    /** The serial version uid. */
    private static final long serialVersionUID = -6547391197128734913L;
    /** The vendor. */
    private String vendor;
    /** The vendor items. */
    private List<InventoryVendorItem> vendorItems;

    /**
     * Instantiates a new inventory vendor list.
     */
    public InventoryVendorList() {
        this.vendorItems = new ArrayList<>();
    }

    /**
     * Gets the count.
     *
     * @return the count
     */
    public long getCount() {
        if (this.vendorItems != null) {
            return this.vendorItems.size();
        }
        return 0;
    }
}
