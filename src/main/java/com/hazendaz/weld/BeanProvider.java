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

/**
 * Customized BeanProvider which allows to perform field-injection on non-CDI managed classes.
 * <p>
 * This class contains utility methods to resolve contextual references in situations where no injection is available
 * because the current class is not managed by the CDI Container. This can happen in e.g. a JPA-2.0 EntityListener, a
 * ServletFilter, a Spring managed Bean, etc.
 * <p>
 * <b>Attention:</b> This method is intended for being used in user code at runtime. If this method gets used during
 * Container boot (in an Extension), non-portable behavior results. The CDI specification only allows injection of the
 * BeanManager during CDI-Container boot time.
 */
@Vetoed
public final class BeanProvider {

    /**
     * Allows to perform dependency injection for instances which aren't managed by CDI.
     * <p>
     * Attention: The resulting instance isn't managed by CDI; only fields annotated with {@link Inject} (or marked with
     * {@link PostInject} and then synthesized to {@link Inject}) get initialized.
     *
     * @param instance
     *            current instance (required)
     * @param <T>
     *            current type
     * @param ignoreMap
     *            map of field names to annotation types which should be removed from those fields (required)
     *
     * @return the same instance with injected fields
     */
    public static <T> T injectFields(final T instance, final Map<String, Class<? extends Annotation>> ignoreMap) {
        if (instance == null) {
            throw new IllegalArgumentException("BeanProvider `injectFields` method requires a non-null instance.");
        }
        if (ignoreMap == null) {
            throw new IllegalArgumentException("BeanProvider `injectFields` method requires a non-null ignoreMap.");
        }

        // Initialize processing using core 'deltaspike' bean provider
        final BeanManager beanManager = BeanProvider.getBeanManager();

        // Handle 'ignoreMap' customization to injection
        @SuppressWarnings("unchecked")
        final AnnotatedTypeBuilder<Object> builder = new AnnotatedTypeBuilder<>()
                .readFromType((AnnotatedType<Object>) beanManager.createAnnotatedType(instance.getClass()), true);

        // Remove annotations as specified in ignoreMap
        for (final Entry<String, Class<? extends Annotation>> entry : ignoreMap.entrySet()) {
            final String fieldName = entry.getKey();
            if (fieldName == null) {
                throw new IllegalArgumentException("BeanProvider `ignoreMap` contains a null field name.");
            }

            final Class<? extends Annotation> annotationType = entry.getValue();
            if (annotationType == null) {
                throw new IllegalArgumentException(
                        "BeanProvider `ignoreMap` contains a null annotation type for field: " + fieldName);
            }

            final Field field;
            try {
                field = instance.getClass().getDeclaredField(fieldName);
            } catch (final NoSuchFieldException e) {
                throw new IllegalArgumentException("BeanProvider `ignoreMap` references missing field: " + fieldName,
                        e);
            }

            builder.removeFromField(field, annotationType);
        }

        // Support @PostInject annotation by adding @Inject along side it
        final Annotation injectAnnotation = AnnotationInstanceProvider.of(Inject.class);
        for (final Field field : instance.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(PostInject.class)) {
                builder.addToField(field, injectAnnotation);
            }
        }

        // Finalize processing using core 'deltaspike' bean provider
        final CreationalContext<Object> creationalContext = beanManager.createCreationalContext(null);

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
