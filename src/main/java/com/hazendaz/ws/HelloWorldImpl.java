/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright 2009-2026 Hazendaz
 */
package com.hazendaz.ws;

import com.hazendaz.weld.BeanProvider;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.enterprise.inject.Vetoed;
import jakarta.inject.Inject;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceContext;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;

@Vetoed
@WebService(endpointInterface = "com.hazendaz.ws.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

    @Inject
    private Logger logger;

    @Resource
    private WebServiceContext context;

    @Override()
    public String getHelloWorld(final String name) {
        if (this.logger == null) {
            this.init();
        }
        this.logger.info("I'm not null - logger man");
        return "Hello World JAX-WS " + name;
    }

    @PostConstruct
    public void init() {
        final Map<String, Class<? extends Annotation>> ignoreMap = new HashMap<>();
        ignoreMap.put("context", Resource.class);
        try {
            BeanProvider.injectFields(this, ignoreMap);
        } catch (final IllegalStateException e) {
            // Do nothing - Tomcat causes this failure
        }
    }

}
