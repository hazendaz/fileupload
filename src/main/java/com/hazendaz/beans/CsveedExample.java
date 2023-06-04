/*
 * fileUploadResources (https://github.com/hazendaz/fileUploadResources)
 *
 * Copyright 2009-2015 Hazendaz.
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

import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import org.csveed.api.Row;
import org.csveed.row.RowInstructions;
import org.csveed.row.RowInstructionsImpl;
import org.csveed.row.RowReader;
import org.csveed.row.RowReaderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CsveedExample {

    private final Logger logger = LoggerFactory.getLogger(CsveedExample.class);

    public int csvExample() {
        final RowInstructions instructions = new RowInstructionsImpl();
        instructions.setUseHeader(false);
        // instructions.setEscape('\0');
        instructions.setQuote('\0');
        final Reader reader = new StringReader("bob\"bob\n");

        final RowReader rowReader = new RowReaderImpl(reader, instructions);
        final List<Row> rows = rowReader.readRows();
        this.logger.info("number of rows: {}", Integer.valueOf(rows.size()));
        this.logger.info("column 1: {}", rows.get(0).get(1));
        return rows.size();
    }
}
