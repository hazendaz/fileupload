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
import jakarta.enterprise.inject.spi.AnnotatedType;

import java.lang.reflect.Member;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Implementation of {@link AnnotatedCallable}
 */
abstract class AnnotatedCallableImpl<X, Y extends Member> extends AnnotatedMemberImpl<X, Y>
        implements AnnotatedCallable<X> {

    private final List<AnnotatedParameter<X>> parameters;

    protected AnnotatedCallableImpl(AnnotatedType<X> declaringType, Y member, Class<?> memberType,
            Class<?>[] parameterTypes, Type[] genericTypes, AnnotationStore annotations,
            Map<Integer, AnnotationStore> parameterAnnotations, Type genericType,
            Map<Integer, Type> parameterTypeOverrides) {
        super(declaringType, member, memberType, annotations, genericType, null);
        parameters = getAnnotatedParameters(this, parameterTypes, genericTypes, parameterAnnotations,
                parameterTypeOverrides);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AnnotatedParameter<X>> getParameters() {
        return Collections.unmodifiableList(parameters);
    }

    public AnnotatedParameter<X> getParameter(int index) {
        return parameters.get(index);

    }

    private static <X, Y extends Member> List<AnnotatedParameter<X>> getAnnotatedParameters(
            AnnotatedCallableImpl<X, Y> callable, Class<?>[] parameterTypes, Type[] genericTypes,
            Map<Integer, AnnotationStore> parameterAnnotations, Map<Integer, Type> parameterTypeOverrides) {
        List<AnnotatedParameter<X>> parameters = new ArrayList<AnnotatedParameter<X>>();
        int len = parameterTypes.length;

        for (int i = 0; i < len; ++i) {
            AnnotationBuilder builder = new AnnotationBuilder();
            if (parameterAnnotations != null && parameterAnnotations.containsKey(i)) {
                builder.addAll(parameterAnnotations.get(i));
            }
            Type over = null;
            if (parameterTypeOverrides != null) {
                over = parameterTypeOverrides.get(i);
            }
            AnnotatedParameterImpl<X> p = new AnnotatedParameterImpl<X>(callable, parameterTypes[i], i,
                    builder.create(), genericTypes[i], over);

            parameters.add(p);
        }
        return parameters;
    }

}
