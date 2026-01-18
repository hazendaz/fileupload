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
package com.hazendaz.deltaspike;

import jakarta.enterprise.inject.spi.Annotated;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.deltaspike.core.util.HierarchyDiscovery;

/**
 * The base class for all New Annotated types.
 */
abstract class AnnotatedImpl implements Annotated {

    private final Type type;
    private final Set<Type> typeClosure;
    private final AnnotationStore annotations;

    protected AnnotatedImpl(Class<?> type, AnnotationStore annotations, Type genericType, Type overriddenType) {
        if (overriddenType == null) {
            if (genericType != null) {
                typeClosure = new HierarchyDiscovery(genericType).getTypeClosure();
                this.type = genericType;
            } else {
                typeClosure = new HierarchyDiscovery(type).getTypeClosure();
                this.type = type;
            }
        } else {
            this.type = overriddenType;
            typeClosure = Collections.singleton(overriddenType);
        }

        if (annotations == null) {
            this.annotations = new AnnotationStore();
        } else {
            this.annotations = annotations;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends Annotation> T getAnnotation(Class<T> annotationType) {
        return annotations.getAnnotation(annotationType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Annotation> getAnnotations() {
        return annotations.getAnnotations();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAnnotationPresent(Class<? extends Annotation> annotationType) {
        return annotations.isAnnotationPresent(annotationType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Type> getTypeClosure() {
        return new HashSet<>(typeClosure);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Type getBaseType() {
        return type;
    }

}
