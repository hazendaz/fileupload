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
package com.hazendaz.weld;

import jakarta.annotation.Resource;
import jakarta.xml.ws.WebServiceContext;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldJunit5Extension;
import org.jboss.weld.junit5.WeldSetup;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(WeldJunit5Extension.class)
class BeanProviderTest {

    @Resource
    private WebServiceContext context;

    @WeldSetup
    public WeldInitiator weld = WeldInitiator.of(new Weld());

    @Test
    void injectFields_empty_map() {
        final Map<String, Class<?>> ignoreMap = new ConcurrentHashMap<>();
        Assertions.assertSame(this, BeanProvider.injectFields(this, ignoreMap));
    }

    @Test
    void injectFields_full_map() {
        final Map<String, Class<?>> ignoreMap = new ConcurrentHashMap<>();
        ignoreMap.put("context", Resource.class);
        Assertions.assertSame(this, BeanProvider.injectFields(this, ignoreMap));
    }

    @Test
    void injectFields_missing_property() {
        final Map<String, Class<?>> ignoreMap = new ConcurrentHashMap<>();
        ignoreMap.put("string", String.class);
        Assertions.assertSame(this, BeanProvider.injectFields(this, ignoreMap));
    }

    @Test
    void injectFields_null_instance() {
        final Map<String, Class<?>> ignoreMap = new ConcurrentHashMap<>();
        Assertions.assertNull(BeanProvider.injectFields(null, ignoreMap));
    }

    @Test
    void injectFields_null_map() {
        Assertions.assertNull(BeanProvider.injectFields(this, null));
    }

    @Test
    void privateConstructorTest() throws Exception {
        final Constructor<?>[] constructors = BeanProvider.class.getDeclaredConstructors();
        constructors[0].setAccessible(true);
        constructors[0].newInstance((Object[]) null);
        for (final Constructor<?> constructor : constructors) {
            Assertions.assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        }
    }

}
