package com.hazendaz.undertow;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletException;

import com.sun.xml.ws.transport.http.servlet.WSServlet;
import com.sun.xml.ws.transport.http.servlet.WSServletContextListener;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.handlers.PathHandler;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import io.undertow.servlet.api.ListenerInfo;
import waffle.servlet.WaffleInfoServlet;

public class HelloWorld {

    public static void main(final String[] args) {
        try {
            DeploymentInfo servletBuilder = Servlets.deployment().setClassLoader(HelloWorld.class.getClassLoader())
                    .setContextPath("/fileupload").setDeploymentName("fileupload.war")
                    // Doesn't work, fails with missing /WEB-INF/sun-jaxws.xml
                    // .addListener(new ListenerInfo(WSServletContextListener.class))
                    .addWelcomePage("pages/logon.faces")
                    .addServlets(Servlets.servlet("FacesServlet", FacesServlet.class).addMapping("*.faces"),
                            // This one works...
                            Servlets.servlet("WaffleInfo", WaffleInfoServlet.class).addMapping("/waffle"),
                            Servlets.servlet("hello", WSServlet.class).addMapping("/hello"));

            DeploymentManager manager = Servlets.defaultContainer().addDeployment(servletBuilder);
            manager.deploy();

            HttpHandler servletHandler = manager.start();
            PathHandler path = Handlers.path(Handlers.redirect("/fileupload")).addPrefixPath("/fileupload",
                    servletHandler);

            Undertow server = Undertow.builder().addHttpListener(8080, "localhost").setHandler(path).build();
            server.start();
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }
}
