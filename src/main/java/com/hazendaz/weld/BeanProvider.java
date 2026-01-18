/*
 * fileupload (https://github.com/hazendaz/fileupload)
 *
 * Copyright 2009-2026 Hazendaz.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of The Apache Software License,
 * Version 2.0 which accompanies this distribution, and is available at
 * https://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * Contributors:
 *     Hazendaz (Jeremy Landis).
 */
/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements. See the NOTICE
 * file distributed with this work for additional information regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by
 * applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package com.hazendaz.weld;

import com.hazendaz.deltaspike.AnnotatedTypeBuilder;

import jakarta.enterprise.context.spi.CreationalContext;
import jakarta.enterprise.inject.Vetoed;
import jakarta.enterprise.inject.spi.AnnotatedType;
import jakarta.enterprise.inject.spi.BeanManager;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.enterprise.inject.spi.InjectionTarget;

import java.lang.annotation.Annotation;
import java.util.Iterator;
import java.util.Map;

/**
 * <p>
 * This class contains utility methods to resolve contextual references in situations where no injection is available
 * because the current class is not managed by the CDI Container. This can happen in e.g. a JPA-2.0 EntityListener, a
 * ServletFilter, a Spring managed Bean, etc.
 * </p>
 * <p>
 * <b>Attention:</b> This method is intended for being used in user code at runtime. If this method gets used during
 * Container boot (in an Extension), non-portable behavior results. The CDI specification only allows injection of the
 * BeanManager during CDI-Container boot time.
 * </p>
 */
@Vetoed
public final class BeanProvider {

    /**
     * Allows to perform dependency injection for instances which aren't managed by CDI. Attention: The resulting
     * instance isn't managed by CDI; only fields annotated with @Inject get initialized.
     *
     * @param instance
     *            current instance
     * @param <T>
     *            current type
     * @param ignoreMap
     *            map of properties to ignore
     *
     * @return instance with injected fields (if possible - or null if the given instance is null)
     */
    @SuppressWarnings("unchecked")
    public static <T> T injectFields(final T instance, final Map<String, Class<?>> ignoreMap) {
        // Initialize processing using core 'deltaspike' bean provider
        if (instance == null || ignoreMap == null) {
            return null;
        }
        final BeanManager beanManager = BeanProvider.getBeanManager();
        final CreationalContext<Object> creationalContext = beanManager.createCreationalContext(null);

        // Handle 'ignoreMap' customization to injection
        final AnnotatedTypeBuilder<Object> builder = new AnnotatedTypeBuilder<>()
                .readFromType((AnnotatedType<Object>) beanManager.createAnnotatedType(instance.getClass()), true);
        try {
            final Iterator<?> iterator = ignoreMap.entrySet().iterator();
            for (; iterator.hasNext();) {
                final Map.Entry<String, Class<?>> pairs = (Map.Entry<String, Class<?>>) iterator.next();
                builder.removeFromField(instance.getClass().getDeclaredField(pairs.getKey()),
                        (Class<? extends Annotation>) pairs.getValue());
            }
        } catch (final SecurityException e) {
            e.printStackTrace();
        } catch (final NoSuchFieldException e) {
            e.printStackTrace();
        }

        // Finalize processing using core 'deltaspiek' bean provider
        final AnnotatedType<Object> annotatedType = builder.create();
        final InjectionTarget<Object> injectionTarget = beanManager.getInjectionTargetFactory(annotatedType)
                .createInjectionTarget(null);
        injectionTarget.inject(instance, creationalContext);
        return instance;
    }

    /**
     * Internal method to resolve the BeanManager.
     *
     * @return current bean-manager
     */
    private static BeanManager getBeanManager() {
        return CDI.current().getBeanManager();
    }

    private BeanProvider() {
        // this is a utility class which doesn't get instantiated.
    }

}
