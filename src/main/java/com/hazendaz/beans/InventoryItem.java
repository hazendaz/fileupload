/*
 * SPDX-License-Identifier: Apache-2.0
 * See LICENSE file for details.
 *
 * Copyright 2009-2026 Hazendaz
 */
package com.hazendaz.beans;

import jakarta.enterprise.context.RequestScoped;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The Class InventoryItem.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@RequestScoped
public class InventoryItem extends InventoryVendorItem {
    /** The serial version uid. */
    private static final long serialVersionUID = 2052446469750935597L;
    /** The vendor. */
    private String vendor;
}
