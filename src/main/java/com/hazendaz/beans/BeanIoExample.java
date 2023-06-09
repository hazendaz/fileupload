/*
 * fileUploadResources (https://github.com/hazendaz/fileUploadResources)
 *
 * Copyright 2009-2023 Hazendaz.
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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.beanio.BeanReader;
import org.beanio.StreamFactory;
import org.beanio.builder.DelimitedParserBuilder;
import org.beanio.builder.StreamBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeanIoExample {

    private final Logger logger = LoggerFactory.getLogger(BeanIoExample.class);

    public int csvExample() {
        final StreamFactory factory = StreamFactory.newInstance();
        final StreamBuilder builder = new StreamBuilder("beanio").format("delimited")
                .parser(new DelimitedParserBuilder(',')).addRecord(BeanIo.class);
        factory.define(builder);

        final BeanReader beanReader = factory.createReader("beanio", new File("src/main/resources/beanio.txt"));

        final List<BeanIo> rows = new ArrayList<>();

        Object row = null;
        while ((row = beanReader.read()) != null) {
            rows.add((BeanIo) row);
        }

        for (int i = rows.size(); --i >= 0;) {
            this.logger.info("Data :'{}'", rows.get(i));
        }
        return rows.size();
    }
}
