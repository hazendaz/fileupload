package com.hazendaz.weld;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;
import javax.xml.ws.WebServiceContext;

import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(CdiTestRunner.class)
public class BeanProviderTest {

    @Resource
    private WebServiceContext context;

    @Test
    public void injectFields_empty_map() {
        final Map<String, Class<?>> ignoreMap = new ConcurrentHashMap<>();
        Assert.assertSame(this, BeanProvider.injectFields(this, ignoreMap));
    }

    @Test
    public void injectFields_full_map() {
        final Map<String, Class<?>> ignoreMap = new ConcurrentHashMap<>();
        ignoreMap.put("context", Resource.class);
        Assert.assertSame(this, BeanProvider.injectFields(this, ignoreMap));
    }

    @Test
    public void injectFields_missing_property() {
        final Map<String, Class<?>> ignoreMap = new ConcurrentHashMap<>();
        ignoreMap.put("string", String.class);
        Assert.assertSame(this, BeanProvider.injectFields(this, ignoreMap));
    }

    @Test
    public void injectFields_null_instance() {
        final Map<String, Class<?>> ignoreMap = new ConcurrentHashMap<>();
        Assert.assertNull(BeanProvider.injectFields(null, ignoreMap));
    }

    @Test
    public void injectFields_null_map() {
        Assert.assertNull(BeanProvider.injectFields(this, null));
    }

    @Test
    public void privateConstructorTest() throws Exception {
        final Constructor<?>[] constructors = BeanProvider.class.getDeclaredConstructors();
        constructors[0].setAccessible(true);
        constructors[0].newInstance((Object[]) null);
        for (final Constructor<?> constructor : constructors) {
            Assert.assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        }
    }

}
