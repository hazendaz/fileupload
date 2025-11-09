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
package com.hazendaz.ws;

import jakarta.annotation.Resource;
import jakarta.enterprise.inject.Vetoed;
import jakarta.inject.Inject;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceContext;

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
            // Will add back later
            // this.init();
        }
        this.logger.info("I'm not null - logger man");
        return "Hello World JAX-WS " + name;
    }

}
