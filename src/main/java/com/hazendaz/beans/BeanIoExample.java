/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright 2009-2026 Hazendaz
 */
package com.hazendaz.beans;

import java.nio.file.Path;
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

        final BeanReader beanReader = factory.createReader("beanio", Path.of("src/main/resources/beanio.txt").toFile());

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
