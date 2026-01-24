/*
 * SPDX-License-Identifier: Apache-2.0
 * See LICENSE file for details.
 *
 * Copyright 2009-2026 Hazendaz
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
