/*
 * fileUploadResources (https://github.com/hazendaz/fileUploadResources)
 *
 * Copyright 2009-2023 Hazendaz.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of The Apache Software License,
 * Version 2.0 which accompanies this distribution, and is available at
 * https://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * Contributors:
 *     Hazendaz (Jeremy Landis).
 */
package com.hazendaz.ws;

import com.hazendaz.weld.BeanProvider;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.inject.Vetoed;
import javax.inject.Inject;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

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
        final Map<String, Class<?>> ignoreMap = new HashMap<>();
        ignoreMap.put("context", Resource.class);
        try {
            BeanProvider.injectFields(this, ignoreMap);
        } catch (final IllegalStateException e) {
            // Do nothing - Tomcat causes this failure
        }
    }

}
