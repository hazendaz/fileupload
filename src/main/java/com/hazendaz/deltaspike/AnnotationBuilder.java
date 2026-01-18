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

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * A store of annotations to be used {@link AnnotatedTypeBuilder} and other places where a collection of annotations
 * needs manipulation.
 */
// this class is intentionally made package scope
class AnnotationBuilder {
    private final Map<Class<? extends Annotation>, Annotation> annotationMap;
    private final Set<Annotation> annotationSet;

    /**
     * Default constructor.
     */
    AnnotationBuilder() {
        annotationMap = new HashMap<Class<? extends Annotation>, Annotation>();
        annotationSet = new HashSet<Annotation>();
    }

    /**
     * Adds the annotation to the collections.
     *
     * @param annotation
     *            annotation to be added
     *
     * @return this
     */
    public AnnotationBuilder add(Annotation annotation) {
        if (annotation == null) {
            throw new IllegalArgumentException("annotation parameter must not be null");
        }
        annotationSet.add(annotation);
        annotationMap.put(annotation.annotationType(), annotation);
        return this;
    }

    /**
     * Removes the given annotation from the collections.
     *
     * @param annotationType
     *            to be removed
     *
     * @return this
     */
    public AnnotationBuilder remove(Class<? extends Annotation> annotationType) {
        if (annotationType == null) {
            throw new IllegalArgumentException("annotationType parameter must not be null");
        }

        Iterator<Annotation> it = annotationSet.iterator();
        while (it.hasNext()) {
            Annotation an = it.next();
            if (annotationType.isAssignableFrom(an.annotationType())) {
                it.remove();
            }
        }
        annotationMap.remove(annotationType);
        return this;
    }

    /**
     * Creates an {@link AnnotationStore} using the annotations from this instance.
     *
     * @return new AnnotationStore
     */
    public AnnotationStore create() {
        return new AnnotationStore(annotationMap, annotationSet);
    }

    /**
     * Adds all annotations from the given collection
     *
     * @param annotations
     *            collection of annotations to be added
     *
     * @return this
     */
    public AnnotationBuilder addAll(Collection<Annotation> annotations) {
        for (Annotation annotation : annotations) {
            add(annotation);
        }
        return this;
    }

    /**
     * Adds all annotations from an {@link AnnotationStore}.
     *
     * @param annotations
     *            annotations to be added
     *
     * @return this
     */
    public AnnotationBuilder addAll(AnnotationStore annotations) {
        for (Annotation annotation : annotations.getAnnotations()) {
            add(annotation);
        }
        return this;
    }

    /**
     * Adds all annotations from the given {@link AnnotatedElement}.
     *
     * @param element
     *            element containing annotations to be added
     *
     * @return this
     */
    public AnnotationBuilder addAll(AnnotatedElement element) {
        for (Annotation a : element.getAnnotations()) {
            add(a);
        }
        return this;
    }

    /**
     * Getter.
     */
    public <T extends Annotation> T getAnnotation(Class<T> anType) {
        return (T) annotationMap.get(anType);
    }

    /**
     * Simple check for an annotation.
     */
    public boolean isAnnotationPresent(Class<?> annotationType) {
        return annotationMap.containsKey(annotationType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return annotationSet.toString();
    }
}
