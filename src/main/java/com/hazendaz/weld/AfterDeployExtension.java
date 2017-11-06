package com.hazendaz.weld;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterDeploymentValidation;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.InjectionTarget;
import javax.enterprise.inject.spi.ProcessInjectionTarget;

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
