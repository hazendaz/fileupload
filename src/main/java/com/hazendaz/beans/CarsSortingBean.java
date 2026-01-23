/*
 * SPDX-License-Identifier: Apache-2.0
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

@Data
@Named
@SessionScoped
public class CarsSortingBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Map<String, SortOrder> sortsOrders;
    private List<String> sortPriorities;

    private boolean multipleSorting = false;

    private static final String SORT_PROPERTY_PARAMETER = "sortProperty";

    @Inject
    private Logger logger;

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

    public void reset() {
        this.sortPriorities.clear();
        this.sortsOrders.clear();
    }

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
