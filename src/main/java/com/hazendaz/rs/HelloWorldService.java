package com.hazendaz.rs;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;

import com.hazendaz.beans.BeanIo;

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