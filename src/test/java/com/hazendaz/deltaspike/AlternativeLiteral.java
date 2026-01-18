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

import jakarta.enterprise.inject.Alternative;
import jakarta.enterprise.util.AnnotationLiteral;

/**
 * Literal for {@link jakarta.enterprise.inject.Alternative}.
 */
public class AlternativeLiteral extends AnnotationLiteral<Alternative> implements Alternative {
    private static final long serialVersionUID = -4865048799125718216L;
}
