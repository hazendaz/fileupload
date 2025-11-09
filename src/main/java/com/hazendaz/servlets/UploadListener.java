/*
 * fileupload (https://github.com/hazendaz/fileupload)
 *
 * Copyright 2009-2025 Hazendaz.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of The Apache Software License,
 * Version 2.0 which accompanies this distribution, and is available at
 * https://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * Contributors:
 *     Hazendaz (Jeremy Landis).
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
