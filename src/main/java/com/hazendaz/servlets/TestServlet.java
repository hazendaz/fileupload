package com.hazendaz.servlets;

import javax.inject.Inject;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;

public class TestServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Inject
    private Logger logger;

    @Override
    public void init() {
        this.logger.info("servlet started"); //$NON-NLS-1$
        this.logger.info("java endorsed directory: {}", System.getProperty("java.endorsed.dirs"));
    }

}
