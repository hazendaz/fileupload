/*
 * SPDX-License-Identifier: Apache-2.0
 * See LICENSE file for details.
 *
 * Copyright 2009-2026 Hazendaz
 */
package com.hazendaz.filters;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * The Class MinimizeServletResponseWrapper.
 */
public class MinimizeServletResponseWrapper extends HttpServletResponseWrapper {

    /** The output. */
    private final ByteArrayOutputStream output;
    /** The filter output. */
    private MinimizeResponseStream filterOutput;
    /** The pw. */
    private PrintWriter pw;

    /**
     * Instantiates a new minimize servlet response wrapper.
     *
     * @param response
     *            the response
     */
    public MinimizeServletResponseWrapper(final HttpServletResponse response) {
        super(response);
        this.output = new ByteArrayOutputStream();
    }

    // get the output from byte stream
    /**
     * Gets the data stream.
     *
     * @return the data stream
     */
    public byte[] getDataStream() {
        return this.output.toByteArray();
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if (this.filterOutput == null) {
            this.filterOutput = new MinimizeResponseStream(this.output);
        }
        return this.filterOutput;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        if (this.pw == null) {
            this.pw = new PrintWriter(this.getOutputStream(), true);
        }
        return this.pw;
    }

}
