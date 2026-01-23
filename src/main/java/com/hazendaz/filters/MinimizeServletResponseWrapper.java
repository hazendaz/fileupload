/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright 2009-2026 Hazendaz
 */
package com.hazendaz.filters;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class MinimizeServletResponseWrapper extends HttpServletResponseWrapper {

    private final ByteArrayOutputStream output;
    private MinimizeResponseStream filterOutput;
    private PrintWriter pw;

    public MinimizeServletResponseWrapper(final HttpServletResponse response) {
        super(response);
        this.output = new ByteArrayOutputStream();
    }

    // get the output from byte stream
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
