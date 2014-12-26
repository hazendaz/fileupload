package com.hazendaz.servlets;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

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
