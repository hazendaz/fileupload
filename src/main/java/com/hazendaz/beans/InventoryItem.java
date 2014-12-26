package com.hazendaz.beans;

import javax.enterprise.context.RequestScoped;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@RequestScoped
public class InventoryItem extends InventoryVendorItem {
    private static final long serialVersionUID = 2052446469750935597L;
    private String            vendor;
}
