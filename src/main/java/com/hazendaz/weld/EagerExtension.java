package com.hazendaz.weld;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterDeploymentValidation;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EagerExtension implements Extension {
    private final Logger        logger                = LoggerFactory.getLogger(EagerExtension.class);
    private final List<Bean<?>> eagerBeansList        = new ArrayList<>();
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
