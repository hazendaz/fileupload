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

import jakarta.enterprise.inject.spi.AnnotatedCallable;
import jakarta.enterprise.inject.spi.AnnotatedParameter;

import java.lang.reflect.Type;

/**
 * Implementation of {@link AnnotatedParameter}.
 */
class AnnotatedParameterImpl<X> extends AnnotatedImpl implements AnnotatedParameter<X> {

    private final int position;
    private final AnnotatedCallable<X> declaringCallable;

    /**
     * Constructor
     */
    AnnotatedParameterImpl(AnnotatedCallable<X> declaringCallable, Class<?> type, int position,
            AnnotationStore annotations, Type genericType, Type typeOverride) {
        super(type, annotations, genericType, typeOverride);
        this.declaringCallable = declaringCallable;
        this.position = position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AnnotatedCallable<X> getDeclaringCallable() {
        return declaringCallable;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPosition() {
        return position;
    }

}
