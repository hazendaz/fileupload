/*
 * SPDX-License-Identifier: Apache-2.0
 * See LICENSE file for details.
 *
 * Copyright 2009-2026 Hazendaz
 */
package com.hazendaz.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

/**
 * The Class InventoryVendorItem.
 */
@Data
public class InventoryVendorItem implements Serializable {
    /** The serial version uid. */
    private static final long serialVersionUID = -5424674835711375626L;
    /** The activity. */
    private BigDecimal activity;
    /** The change price. */
    private BigDecimal changePrice;
    /** The change searches. */
    private BigDecimal changeSearches;
    /** The days live. */
    private int daysLive;
    /** The exposure. */
    private BigDecimal exposure;
    /** The inquiries. */
    private BigDecimal inquiries;
    /** The mileage. */
    private BigDecimal mileage;
    /** The mileage market. */
    private BigDecimal mileageMarket;
    /** The model. */
    private String model;
    /** The price. */
    private Integer price;
    /** The price market. */
    private BigDecimal priceMarket;
    /** The printed. */
    private BigDecimal printed;
    /** The stock. */
    private String stock;
    /** The vin. */
    private String vin;
}
