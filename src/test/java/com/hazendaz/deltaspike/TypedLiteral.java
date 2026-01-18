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

import jakarta.enterprise.inject.Typed;
import jakarta.enterprise.util.AnnotationLiteral;

/**
 * Literal for {@link jakarta.enterprise.inject.Typed}
 */
public class TypedLiteral extends AnnotationLiteral<Typed> implements Typed {
    private static final long serialVersionUID = 6805980497117269525L;

    private final Class<?>[] value;

    public TypedLiteral() {
        value = new Class<?>[0];
    }

    public TypedLiteral(Class<?>[] value) {
        this.value = value;
    }

    @Override
    public Class<?>[] value() {
        return value;
    }
}
