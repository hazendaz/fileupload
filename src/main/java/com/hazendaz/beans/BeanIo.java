/*
 * fileUploadResources (https://github.com/hazendaz/fileUploadResources)
 *
 * Copyright 2009-2018 Hazendaz.
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

import lombok.Data;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

@Data
@Record(minOccurs = 1)
public class BeanIo {

    @Field(at = 0)
    private String firstName;
    @Field(at = 1)
    private String lastName;
    @Field(at = 2)
    private String title;
    @Field(at = 3)
    private String salary;
    @Field(at = 4)
    private String years;

}
