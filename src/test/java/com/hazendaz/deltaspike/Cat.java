/*
 * fileupload (https://github.com/hazendaz/fileupload)
 *
 * Copyright 2009-2026 Hazendaz.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of The Apache Software License,
 * Version 2.0 which accompanies this distribution, and is available at
 * https://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * Contributors:
 *     Hazendaz (Jeremy Landis).
 */
package com.hazendaz.deltaspike;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.inject.spi.BeanManager;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("cat")
public class Cat {
    private String color;
    private String gender;

    public Cat() {
    }

    @Inject
    public Cat(String color, String gender) {
        this.color = color;
        this.gender = gender;
    }

    @PostConstruct
    public void setup() {
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    protected void doSomeObservation(@Observes Cat cat, BeanManager beanManager) {
        // whoah, someone fires cats around ^^
        // at least it tests parameter scanning ;)
        color = cat.color;
    }
}
