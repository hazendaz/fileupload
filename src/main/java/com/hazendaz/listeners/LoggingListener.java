package com.hazendaz.listeners;

import java.util.logging.Handler;
import java.util.logging.LogManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.bridge.SLF4JBridgeHandler;

public class LoggingListener implements ServletContextListener {

    @Override
    public void contextDestroyed(final ServletContextEvent sce) {
        // Do Nothing
    }

    @Override
    public void contextInitialized(final ServletContextEvent arg0) {
        // Remove any loggers JUL created before installing SLF4JBridgeHandler
        final java.util.logging.Logger rootLogger = LogManager.getLogManager().getLogger("");
        final Handler[] handlers = rootLogger.getHandlers();
        for (final Handler handler : handlers) {
            rootLogger.removeHandler(handler);
        }
        SLF4JBridgeHandler.install();
    }

}
