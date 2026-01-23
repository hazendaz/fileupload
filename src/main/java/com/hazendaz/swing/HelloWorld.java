/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright 2009-2025 Hazendaz
 */
package com.hazendaz.swing;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Singleton;

import java.util.List;

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
     *            is the containerInitialized even being processed.
     * @param parameters
     *            is the paramter list being initialized.
     */
    void printHello(@Observes final ContainerInitialized event, @Parameters final List<String> parameters) {
        System.out.println("Hello " + parameters.get(0));
    }

}
