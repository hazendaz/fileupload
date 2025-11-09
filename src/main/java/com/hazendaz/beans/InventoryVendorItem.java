/*
 * fileupload (https://github.com/hazendaz/fileupload)
 *
 * Copyright 2009-2025 Hazendaz.
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
import java.math.BigDecimal;

import lombok.Data;

@Data
public class InventoryVendorItem implements Serializable {
    private static final long serialVersionUID = -5424674835711375626L;
    private BigDecimal activity;
    private BigDecimal changePrice;
    private BigDecimal changeSearches;
    private int daysLive;
    private BigDecimal exposure;
    private BigDecimal inquiries;
    private BigDecimal mileage;
    private BigDecimal mileageMarket;
    private String model;
    private Integer price;
    private BigDecimal priceMarket;
    private BigDecimal printed;
    private String stock;
    private String vin;
}
