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
