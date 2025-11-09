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

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.inject.spi.AfterDeploymentValidation;
import jakarta.enterprise.inject.spi.Bean;
import jakarta.enterprise.inject.spi.BeanManager;
import jakarta.enterprise.inject.spi.Extension;
import jakarta.enterprise.inject.spi.ProcessBean;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EagerExtension implements Extension {
    private final Logger logger = LoggerFactory.getLogger(EagerExtension.class);
    private final List<Bean<?>> eagerBeansList = new ArrayList<>();
    private final List<Bean<?>> invalidEagerBeansList = new ArrayList<>();

    public <T> void collect(@Observes final ProcessBean<T> event) {
        if (event.getAnnotated().isAnnotationPresent(Eager.class)) {
            if (event.getAnnotated().isAnnotationPresent(ApplicationScoped.class)) {
                this.eagerBeansList.add(event.getBean());
            } else {
                this.invalidEagerBeansList.add(event.getBean());
            }
        }
    }

    /**
     * @param event
     *            afterDeploymentValidation
     * @param beanManager
     *            cdi bean manager
     */
    public void load(@Observes final AfterDeploymentValidation event, final BeanManager beanManager) {
        for (final Bean<?> bean : this.eagerBeansList) {
            // note: toString() is important to instantiate the bean
            beanManager.getReference(bean, bean.getBeanClass(), beanManager.createCreationalContext(bean)).toString();
        }
        for (final Bean<?> bean : this.invalidEagerBeansList) {
            this.logger.error("@Eager annotation not used with @ApplicationScoped in {}", beanManager
                    .getReference(bean, bean.getBeanClass(), beanManager.createCreationalContext(bean)).toString());
        }
    }
}
