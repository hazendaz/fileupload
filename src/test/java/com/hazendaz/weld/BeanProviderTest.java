package com.hazendaz.weld;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;
import javax.xml.ws.WebServiceContext;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldJunit5Extension;
import org.jboss.weld.junit5.WeldSetup;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(WeldJunit5Extension.class)
public class BeanProviderTest {

    @Resource
    private WebServiceContext context;

    @WeldSetup
    public WeldInitiator weld = WeldInitiator.of(new Weld());

    @Test
    public void injectFields_empty_map() {
        final Map<String, Class<?>> ignoreMap = new ConcurrentHashMap<>();
        Assertions.assertSame(this, BeanProvider.injectFields(this, ignoreMap));
    }

    @Test
    public void injectFields_full_map() {
        final Map<String, Class<?>> ignoreMap = new ConcurrentHashMap<>();
        ignoreMap.put("context", Resource.class);
        Assertions.assertSame(this, BeanProvider.injectFields(this, ignoreMap));
    }

    @Test
    public void injectFields_missing_property() {
        final Map<String, Class<?>> ignoreMap = new ConcurrentHashMap<>();
        ignoreMap.put("string", String.class);
        Assertions.assertSame(this, BeanProvider.injectFields(this, ignoreMap));
    }

    @Test
    public void injectFields_null_instance() {
        final Map<String, Class<?>> ignoreMap = new ConcurrentHashMap<>();
        Assertions.assertNull(BeanProvider.injectFields(null, ignoreMap));
    }

    @Test
    public void injectFields_null_map() {
        Assertions.assertNull(BeanProvider.injectFields(this, null));
    }

    @Test
    public void privateConstructorTest() throws Exception {
        final Constructor<?>[] constructors = BeanProvider.class.getDeclaredConstructors();
        constructors[0].setAccessible(true);
        constructors[0].newInstance((Object[]) null);
        for (final Constructor<?> constructor : constructors) {
            Assertions.assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        }
    }

}
