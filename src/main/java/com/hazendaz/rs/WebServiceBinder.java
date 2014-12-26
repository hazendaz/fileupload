package com.hazendaz.rs;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;

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
