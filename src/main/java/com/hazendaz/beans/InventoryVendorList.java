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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class InventoryVendorList implements Serializable {
    private static final long serialVersionUID = -6547391197128734913L;
    private String vendor;
    private List<InventoryVendorItem> vendorItems;

    public InventoryVendorList() {
        this.vendorItems = new ArrayList<>();
    }

    public long getCount() {
        if (this.vendorItems != null) {
            return this.vendorItems.size();
        }
        return 0;
    }
}
