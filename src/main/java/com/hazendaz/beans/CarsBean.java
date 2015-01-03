package com.hazendaz.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Data;

import org.slf4j.Logger;

@Data
@Named
@ViewScoped
public class CarsBean implements Serializable {

    private static final long serialVersionUID         = -3832235132261771583L;
    private static final int  DECIMALS                 = 1;
    private static final int  CLIENT_ROWS_IN_AJAX_MODE = 15;
    private static final int  ROUNDING_MODE            = BigDecimal.ROUND_HALF_UP;

    private static void itemToVendorItem(final InventoryItem item, final InventoryVendorItem newItem) {
        newItem.setActivity(item.getActivity());
        newItem.setChangePrice(item.getChangePrice());
        newItem.setChangeSearches(item.getChangeSearches());
        newItem.setDaysLive(item.getDaysLive());
        newItem.setExposure(item.getExposure());
        newItem.setInquiries(item.getInquiries());
        newItem.setMileage(item.getMileage());
        newItem.setMileageMarket(item.getMileageMarket());
        newItem.setModel(item.getModel());
        newItem.setPrice(item.getPrice());
        newItem.setPriceMarket(item.getPriceMarket());
        newItem.setPrinted(item.getPrinted());
        newItem.setStock(item.getStock());
        newItem.setVin(item.getVin());
    }

    private List<InventoryItem>       allInventoryItems    = null;
    private List<InventoryVendorList> inventoryVendorLists = null;
    private int                       currentCarIndex;
    private InventoryItem             editedCar;

    private int                       page                 = 1;

    private int                       clientRows;

    @Inject
    private Logger                    logger;

    public List<InventoryItem> createCar(final String vendor, final String model, final int count) {
        ArrayList<InventoryItem> iiList = null;

        try {
            final InventoryItem[] demoInventoryItemArrays = new InventoryItem[count];

            for (int j = 0; j < demoInventoryItemArrays.length; j++) {
                final InventoryItem ii = new InventoryItem();

                ii.setVendor(vendor);
                ii.setModel(model);
                ii.setStock(RandomHelper.randomstring(6, 7));
                ii.setVin(RandomHelper.randomstring(17, 17));
                ii.setMileage(new BigDecimal(RandomHelper.rand(5000, 80000)).setScale(CarsBean.DECIMALS,
                        CarsBean.ROUNDING_MODE));
                ii.setMileageMarket(new BigDecimal(RandomHelper.rand(25000, 45000)).setScale(CarsBean.DECIMALS,
                        CarsBean.ROUNDING_MODE));
                ii.setPrice(new Integer(RandomHelper.rand(15000, 55000)));
                ii.setPriceMarket(new BigDecimal(RandomHelper.rand(15000, 55000)).setScale(CarsBean.DECIMALS,
                        CarsBean.ROUNDING_MODE));
                ii.setDaysLive(RandomHelper.rand(1, 90));
                ii.setChangeSearches(new BigDecimal(RandomHelper.rand(0, 5)).setScale(CarsBean.DECIMALS,
                        CarsBean.ROUNDING_MODE));
                ii.setChangePrice(new BigDecimal(RandomHelper.rand(0, 5)).setScale(CarsBean.DECIMALS,
                        CarsBean.ROUNDING_MODE));
                ii.setExposure(new BigDecimal(RandomHelper.rand(0, 5)).setScale(CarsBean.DECIMALS,
                        CarsBean.ROUNDING_MODE));
                ii.setActivity(new BigDecimal(RandomHelper.rand(0, 5)).setScale(CarsBean.DECIMALS,
                        CarsBean.ROUNDING_MODE));
                ii.setPrinted(new BigDecimal(RandomHelper.rand(0, 5)).setScale(CarsBean.DECIMALS,
                        CarsBean.ROUNDING_MODE));
                ii.setInquiries(new BigDecimal(RandomHelper.rand(0, 5)).setScale(CarsBean.DECIMALS,
                        CarsBean.ROUNDING_MODE));
                demoInventoryItemArrays[j] = ii;
            }

            iiList = new ArrayList<InventoryItem>(Arrays.asList(demoInventoryItemArrays));
        } catch (final Exception e) {
            e.printStackTrace();
        }

        return iiList;
    }

    public List<InventoryItem> getAllInventoryItems() {
        synchronized (this) {
            if (this.allInventoryItems == null) {
                this.allInventoryItems = new ArrayList<InventoryItem>();

                this.logger.info("Started building car list");
                for (int k = 0; k <= 18; k++) {
                    try {
                        switch (k) {
                            case 0:
                                this.allInventoryItems.addAll(this.createCar("Chevrolet", "Corvette", 5000));
                                this.allInventoryItems.addAll(this.createCar("Chevrolet", "Malibu", 8000));
                                this.allInventoryItems.addAll(this.createCar("Chevrolet", "Tahoe", 6000));

                                break;

                            case 1:
                                this.allInventoryItems.addAll(this.createCar("Ford", "Taurus", 1200));
                                this.allInventoryItems.addAll(this.createCar("Ford", "Explorer", 1100));

                                break;

                            case 2:
                                this.allInventoryItems.addAll(this.createCar("Nissan", "Maxima", 9000));
                                this.allInventoryItems.addAll(this.createCar("Nissan", "Frontier", 6000));

                                break;

                            case 3:
                                this.allInventoryItems.addAll(this.createCar("Toyota", "4-Runner", 7000));
                                this.allInventoryItems.addAll(this.createCar("Toyota", "Camry", 1500));
                                this.allInventoryItems.addAll(this.createCar("Toyota", "Avalon", 1300));

                                break;

                            case 4:
                                this.allInventoryItems.addAll(this.createCar("GMC", "Sierra", 8000));
                                this.allInventoryItems.addAll(this.createCar("GMC", "Yukon", 1000));

                                break;

                            case 5:
                                this.allInventoryItems.addAll(this.createCar("Infiniti", "G35", 6000));
                                this.allInventoryItems.addAll(this.createCar("Infiniti", "EX35", 5000));

                                break;

                            case 6:
                                this.allInventoryItems.addAll(this.createCar("Infiniti2", "G35", 6000));
                                this.allInventoryItems.addAll(this.createCar("Infiniti2", "EX35", 5000));

                                break;

                            case 7:
                                this.allInventoryItems.addAll(this.createCar("Infiniti3", "G35", 6000));
                                this.allInventoryItems.addAll(this.createCar("Infiniti3", "EX35", 5000));

                                break;

                            case 8:
                                this.allInventoryItems.addAll(this.createCar("Infiniti4", "G35", 6000));
                                this.allInventoryItems.addAll(this.createCar("Infiniti4", "EX35", 5000));

                                break;

                            case 9:
                                this.allInventoryItems.addAll(this.createCar("Infiniti5", "G35", 60));
                                this.allInventoryItems.addAll(this.createCar("Infiniti5", "EX35", 50));

                                break;

                            case 10:
                                this.allInventoryItems.addAll(this.createCar("Infiniti6", "G35", 60));
                                this.allInventoryItems.addAll(this.createCar("Infiniti6", "EX35", 50));

                                break;

                            case 11:
                                this.allInventoryItems.addAll(this.createCar("Infiniti7", "G35", 60));
                                this.allInventoryItems.addAll(this.createCar("Infiniti7", "EX35", 50));

                                break;

                            case 12:
                                this.allInventoryItems.addAll(this.createCar("Infiniti8", "G35", 60));
                                this.allInventoryItems.addAll(this.createCar("Infiniti8", "EX35", 50));

                                break;

                            case 13:
                                this.allInventoryItems.addAll(this.createCar("Infiniti9", "G35", 60));
                                this.allInventoryItems.addAll(this.createCar("Infiniti9", "EX35", 50));

                                break;

                            case 14:
                                this.allInventoryItems.addAll(this.createCar("Infiniti10", "G35", 60));
                                this.allInventoryItems.addAll(this.createCar("Infiniti10", "EX35", 50));

                                break;

                            case 15:
                                this.allInventoryItems.addAll(this.createCar("Infiniti11", "G35", 60));
                                this.allInventoryItems.addAll(this.createCar("Infiniti11", "EX35", 50));

                                break;

                            case 16:
                                this.allInventoryItems.addAll(this.createCar("Infiniti12", "G35", 60));
                                this.allInventoryItems.addAll(this.createCar("Infiniti12", "EX35", 50));

                                break;

                            case 17:
                                this.allInventoryItems.addAll(this.createCar("Infiniti13", "G35", 60));
                                this.allInventoryItems.addAll(this.createCar("Infiniti13", "EX35", 50));

                                break;

                            case 18:
                                this.allInventoryItems.addAll(this.createCar("Infiniti14", "G35", 60));
                                this.allInventoryItems.addAll(this.createCar("Infiniti14", "EX35", 50));

                                break;

                            default:
                                break;
                        }
                    } catch (final Exception e) {
                        e.printStackTrace();
                    }
                }
                this.logger.info("Ended building car list");
                this.logger.info("Car List follows: {}", this);
            }
        }
        return this.allInventoryItems;
    }

    public List<String> getAllVendors() {
        final List<String> result = new ArrayList<String>();
        for (final InventoryVendorList vendorList : this.getInventoryVendorLists()) {
            result.add(vendorList.getVendor());
        }
        return result;
    }

    public List<InventoryVendorList> getInventoryVendorLists() {
        synchronized (this) {
            if (this.inventoryVendorLists == null) {
                this.inventoryVendorLists = new ArrayList<InventoryVendorList>();
                final List<InventoryItem> inventoryItems = this.getAllInventoryItems();

                Collections.sort(inventoryItems, new Comparator<InventoryItem>() {
                    @Override
                    public int compare(final InventoryItem o1, final InventoryItem o2) {
                        return o1.getVendor().compareTo(o2.getVendor());
                    }
                });
                final Iterator<InventoryItem> iterator = inventoryItems.iterator();
                InventoryVendorList vendorList = new InventoryVendorList();
                vendorList.setVendor(inventoryItems.get(0).getVendor());
                while (iterator.hasNext()) {
                    final InventoryItem item = iterator.next();
                    final InventoryVendorItem newItem = new InventoryVendorItem();
                    CarsBean.itemToVendorItem(item, newItem);
                    if (!item.getVendor().equals(vendorList.getVendor())) {
                        this.inventoryVendorLists.add(vendorList);
                        vendorList = new InventoryVendorList();
                        vendorList.setVendor(item.getVendor());
                    }
                    vendorList.getVendorItems().add(newItem);
                }
                this.inventoryVendorLists.add(vendorList);
            }
        }
        return this.inventoryVendorLists;
    }

    public List<SelectItem> getVendorOptions() {
        final List<SelectItem> result = new ArrayList<SelectItem>();
        result.add(new SelectItem("", ""));
        for (final InventoryVendorList vendorList : this.getInventoryVendorLists()) {
            result.add(new SelectItem(vendorList.getVendor()));
        }
        return result;
    }

    public void remove() {
        this.allInventoryItems.remove(this.allInventoryItems.get(this.currentCarIndex));
    }

    public void store() {
        this.allInventoryItems.set(this.currentCarIndex, this.editedCar);
    }

    public void switchAjaxLoading(final ValueChangeEvent event) {
        this.clientRows = ((Boolean) event.getNewValue()).booleanValue() ? CarsBean.CLIENT_ROWS_IN_AJAX_MODE : 0;
    }

}
