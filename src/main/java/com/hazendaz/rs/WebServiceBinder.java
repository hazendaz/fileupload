/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright 2009-2026 Hazendaz
 */
package com.hazendaz.rs;

import jakarta.enterprise.context.spi.CreationalContext;
import jakarta.enterprise.inject.spi.Bean;
import jakarta.enterprise.inject.spi.BeanManager;

import org.apache.deltaspike.core.api.provider.BeanManagerProvider;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class WebServiceBinder extends AbstractBinder {

    @Override
    protected void configure() {
        this.bind(this.getBean(this.getBeanManager(), HelloWorldService.class)).to(HelloWorldService.class);
    }

    @SuppressWarnings("unchecked")
    private <T> T getBean(final BeanManager beanManager, final Class<T> clazz) {
        final Bean<T> bean = (Bean<T>) beanManager.getBeans(clazz).iterator().next();
        final CreationalContext<T> creationalContext = beanManager.createCreationalContext(bean);
        return (T) beanManager.getReference(bean, clazz, creationalContext);
    }

    private BeanManager getBeanManager() {
        return BeanManagerProvider.getInstance().getBeanManager();
    }
}
