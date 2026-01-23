/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright 2009-2025 Hazendaz
 */
package com.hazendaz.servlets;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UploadListener implements ServletContextListener {

    private ExecutorService threadPool;

    @Override
    public void contextDestroyed(final ServletContextEvent arg0) {
        this.threadPool.shutdownNow();
    }

    @Override
    public void contextInitialized(final ServletContextEvent arg0) {
        final ServletContext context = arg0.getServletContext();
        this.threadPool = Executors.newFixedThreadPool(10);
        context.setAttribute("THREAD_POOL", this.threadPool);
    }

}
