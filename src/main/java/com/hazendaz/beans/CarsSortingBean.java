/*
 * SPDX-License-Identifier: Apache-2.0
 * See LICENSE file for details.
 *
 * Copyright 2009-2026 Hazendaz
 */
package com.hazendaz.beans;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ValueChangeEvent;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

import org.richfaces.component.SortOrder;
import org.slf4j.Logger;

/**
 * The Class CarsSortingBean.
 */
@Data
@Named
@SessionScoped
public class CarsSortingBean implements Serializable {

    /** The serial version uid. */
    private static final long serialVersionUID = 1L;

    /** The sorts orders. */
    private Map<String, SortOrder> sortsOrders;
    /** The sort priorities. */
    private List<String> sortPriorities;

    /** The multiple sorting. */
    private boolean multipleSorting = false;

    /** The sort property parameter. */
    private static final String SORT_PROPERTY_PARAMETER = "sortProperty";

    /** The logger. */
    @Inject
    private Logger logger;

    /**
     * Init.
     */
    @PostConstruct
    public void init() {
        this.sortsOrders = new HashMap<>();
        this.sortPriorities = new ArrayList<>();
    }

    /**
     * @param event
     *            ValueChangeEvent for modeChanged
     */
    public void modeChanged(final ValueChangeEvent event) {
        this.reset();
    }

    /**
     * Reset.
     */
    public void reset() {
        this.sortPriorities.clear();
        this.sortsOrders.clear();
    }

    /**
     * Sort.
     */
    public void sort() {
        this.logger.info("Sort started");
        final String property = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
                .get(CarsSortingBean.SORT_PROPERTY_PARAMETER);
        if (property != null) {
            final SortOrder currentPropertySortOrder = this.sortsOrders.get(property);
            if (this.multipleSorting) {
                if (!this.sortPriorities.contains(property)) {
                    this.sortPriorities.add(property);
                }
            } else {
                this.sortsOrders.clear();
            }
            if (currentPropertySortOrder == null || currentPropertySortOrder.equals(SortOrder.descending)) {
                this.sortsOrders.put(property, SortOrder.ascending);
            } else {
                this.sortsOrders.put(property, SortOrder.descending);
            }
        }
        this.logger.info("Sort ended");
    }

}
