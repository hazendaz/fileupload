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

import jakarta.enterprise.inject.spi.AnnotatedMember;
import jakarta.enterprise.inject.spi.AnnotatedType;

import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

/**
 * An implementation of {@link AnnotatedMember} to be used in CDI life cycle events and
 * {@link org.apache.deltaspike.core.util.metadata.builder.AnnotatedTypeBuilder}.
 */
abstract class AnnotatedMemberImpl<X, M extends Member> extends AnnotatedImpl implements AnnotatedMember<X> {
    private final AnnotatedType<X> declaringType;
    private final M javaMember;

    protected AnnotatedMemberImpl(AnnotatedType<X> declaringType, M member, Class<?> memberType,
            AnnotationStore annotations, Type genericType, Type overriddenType) {
        super(memberType, annotations, genericType, overriddenType);
        this.declaringType = declaringType;
        javaMember = member;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AnnotatedType<X> getDeclaringType() {
        return declaringType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public M getJavaMember() {
        return javaMember;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isStatic() {
        return Modifier.isStatic(javaMember.getModifiers());
    }

}
