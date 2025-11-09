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
package com.hazendaz.weld;

import jakarta.enterprise.event.Observes;
import jakarta.enterprise.inject.spi.AfterDeploymentValidation;
import jakarta.enterprise.inject.spi.BeanManager;
import jakarta.enterprise.inject.spi.Extension;
import jakarta.enterprise.inject.spi.InjectionTarget;
import jakarta.enterprise.inject.spi.ProcessInjectionTarget;

import java.util.ArrayList;
import java.util.List;

public class AfterDeployExtension implements Extension {

    private final List<InjectionTarget<?>> methods = new ArrayList<>();

    public <T> void collect(@Observes final ProcessInjectionTarget<T> event) {
        if (event.getAnnotatedType().isAnnotationPresent(AfterDeploy.class)) {
            this.methods.add(event.getInjectionTarget());
        }
    }

    /**
     * @param event
     *            afterDeploymentEvent
     * @param beanManager
     *            cdi bean manager
     */
    public void load(@Observes final AfterDeploymentValidation event, final BeanManager beanManager) {
        beanManager.getBeans("applicationTest");
        for (final InjectionTarget<?> bean : this.methods) {
            bean.postConstruct(null);
        }
    }

}
