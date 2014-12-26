package com.hazendaz.swing;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.inject.Singleton;

import org.jboss.weld.environment.se.bindings.Parameters;
import org.jboss.weld.environment.se.events.ContainerInitialized;

@Singleton
public class HelloWorld {

    @PostConstruct
    void init() {
        System.out.println("Initializing Hello World application");
    }

    /**
     * @param event
     */
    void printHello(@Observes final ContainerInitialized event, @Parameters final List<String> parameters) {
        System.out.println("Hello " + parameters.get(0));
    }

}
