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
import jakarta.inject.Inject;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.deltaspike.core.util.metadata.AnnotationInstanceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Customized BeanProvider which allows to perform field-injection on non-CDI managed classes.
 * <p>
 * This class contains utility methods to resolve contextual references in situations where no injection is available
 * because the current class is not managed by the CDI Container. This can happen in e.g. a JPA-2.0 EntityListener, a
 * ServletFilter, a Spring managed Bean, etc.
 * </p>
 * <p>
 * <b>Attention:</b> This method is intended for being used in user code at runtime. If this method gets used during
 * Container boot (in an Extension), non-portable behavior results. The CDI specification only allows injection of the
 * BeanManager during CDI-Container boot time.
 */
@Vetoed
public final class BeanProvider {

    /** Logger instance. */
    private static final Logger logger = LoggerFactory.getLogger(BeanProvider.class);

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
            logger.error("BeanProvider 'injectFields' method requires a non-null instance and ignoreMap.");
            return null;
        }
        final BeanManager beanManager = BeanProvider.getBeanManager();
        final CreationalContext<Object> creationalContext = beanManager.createCreationalContext(null);

        // Handle 'ignoreMap' customization to injection
        final AnnotatedTypeBuilder<Object> builder = new AnnotatedTypeBuilder<>()
                .readFromType((AnnotatedType<Object>) beanManager.createAnnotatedType(instance.getClass()), true);
        try {
            // Remove annotations as specified in ignoreMap
            for (final Entry<String, Class<?>> entry : ignoreMap.entrySet()) {
                builder.removeFromField(instance.getClass().getDeclaredField(entry.getKey()),
                        (Class<? extends Annotation>) entry.getValue());
            }

            // Support @PostInject annotation by adding @Inject along side it
            final Annotation injectAnnotation = AnnotationInstanceProvider.of(Inject.class);
            for (final Field field : instance.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(PostInject.class)) {
                    builder.addToField(field, injectAnnotation);
                }
            }
        } catch (final SecurityException e) {
            logger.error(e.getMessage());
            logger.trace("SecurityException: ", e);
        } catch (final NoSuchFieldException e) {
            logger.error(e.getMessage());
            logger.trace("NoSuchFieldException: ", e);
        }

        // Finalize processing using core 'deltaspike' bean provider
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
