/*
 * SPDX-License-Identifier: Apache-2.0
 * See LICENSE file for details.
 *
 * Copyright 2009-2026 Hazendaz
 */
package com.hazendaz.beans;

import lombok.Data;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

/**
 * The Class BeanIo.
 */
@Data
@Record(minOccurs = 1)
public class BeanIo {

    /** The first name. */
    @Field(at = 0)
    private String firstName;
    /** The last name. */
    @Field(at = 1)
    private String lastName;
    /** The title. */
    @Field(at = 2)
    private String title;
    /** The salary. */
    @Field(at = 3)
    private String salary;
    /** The years. */
    @Field(at = 4)
    private String years;

}
