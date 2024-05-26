/*
 * fileUploadResources (https://github.com/hazendaz/fileUploadResources)
 *
 * Copyright 2009-2024 Hazendaz.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of The Apache Software License,
 * Version 2.0 which accompanies this distribution, and is available at
 * https://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * Contributors:
 *     Hazendaz (Jeremy Landis).
 */
package com.hazendaz.rs;

import com.hazendaz.beans.BeanIo;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.slf4j.Logger;

@Path("hello")
public class HelloWorldService {

    @Inject
    private Logger logger;

    @POST
    @Path("bean")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BeanIo getBean() {
        return new BeanIo();
    }

    @POST
    @Path("hello")
    @Produces(MediaType.APPLICATION_JSON)
    public String getHello() {
        if (this.logger != null) {
            this.logger.info("logging worked in restful");
        }
        System.out.println("DONE");
        return "Hello World!";
    }

}
