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

import static java.util.Collections.emptyMap;
import static java.util.Collections.emptySet;
import static java.util.Collections.unmodifiableSet;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Set;

/**
 * A helper class used to hold annotations on a type or member.
 */
class AnnotationStore {
    private final Map<Class<? extends Annotation>, Annotation> annotationMap;
    private final Set<Annotation> annotationSet;

    AnnotationStore(Map<Class<? extends Annotation>, Annotation> annotationMap, Set<Annotation> annotationSet) {
        this.annotationMap = annotationMap;
        this.annotationSet = unmodifiableSet(annotationSet);
    }

    AnnotationStore() {
        annotationMap = emptyMap();
        annotationSet = emptySet();
    }

    <T extends Annotation> T getAnnotation(Class<T> annotationType) {
        return annotationType.cast(annotationMap.get(annotationType));
    }

    Set<Annotation> getAnnotations() {
        return annotationSet;
    }

    boolean isAnnotationPresent(Class<? extends Annotation> annotationType) {
        return annotationMap.containsKey(annotationType);
    }
}
