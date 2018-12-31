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
