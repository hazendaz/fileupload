package com.hazendaz.weld;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class LogManager {

    @Produces
    public Logger getLogger(final InjectionPoint injectionPoint) {
        final String category = injectionPoint.getMember().getDeclaringClass().getName();
        return LoggerFactory.getLogger(category);
    }

}
