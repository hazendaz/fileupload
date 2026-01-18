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

import jakarta.enterprise.util.AnnotationLiteral;
import jakarta.inject.Named;

/**
 * Literal for {@link jakarta.inject.Named} qualifier.
 */
public class NamedLiteral extends AnnotationLiteral<Named> implements Named {
    private static final long serialVersionUID = -1457223276475846060L;

    private final String value;

    public NamedLiteral(String value) {
        this.value = value;
    }

    public NamedLiteral() {
        value = "";
    }

    @Override
    public String value() {
        return value;
    }
}
