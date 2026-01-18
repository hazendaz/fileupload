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

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.inject.Alternative;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Typed;
import jakarta.enterprise.inject.spi.AnnotatedConstructor;
import jakarta.enterprise.inject.spi.AnnotatedMethod;
import jakarta.enterprise.inject.spi.AnnotatedParameter;
import jakarta.enterprise.inject.spi.AnnotatedType;
import jakarta.enterprise.util.AnnotationLiteral;
import jakarta.inject.Named;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

class AnnotatedTypeBuilderTest {

    @Test
    void testTypeLevelAnnotationRedefinition() {
        AnnotatedTypeBuilder<Cat> builder = new AnnotatedTypeBuilder<Cat>();
        builder.readFromType(Cat.class);

        AnnotatedType<Cat> cat = builder.create();

        assertThat(cat).isNotNull();
        assertThat(cat.getAnnotation(Named.class)).isNotNull();
        assertThat(cat.getAnnotation(Named.class).value()).isEqualTo("cat");

        builder.addToClass(new AlternativeLiteral()).addToClass(new ApplicationScopedLiteral())
                .removeFromClass(Named.class).addToClass(new NamedLiteral("tomcat"));

        cat = builder.create();
        assertThat(cat).isNotNull();

        assertThat(cat.getAnnotations().size()).isEqualTo(3);
        assertThat(cat.isAnnotationPresent(Named.class)).isTrue();
        assertThat(cat.isAnnotationPresent(Alternative.class)).isTrue();
        assertThat(cat.isAnnotationPresent(ApplicationScoped.class)).isTrue();
        assertThat(cat.getAnnotation(Named.class).value()).isEqualTo("tomcat");

        AnnotatedMethod observerMethod = null;
        for (AnnotatedMethod m : cat.getMethods()) {
            if ("doSomeObservation".equals(m.getJavaMember().getName())) {
                observerMethod = m;
                break;
            }
        }
        assertThat(observerMethod).isNotNull();
        boolean hasObserves = false;
        for (Object paramObj : observerMethod.getParameters()) {
            AnnotatedParameter<?> param = (AnnotatedParameter<?>) paramObj;
            if (param.getAnnotation(Observes.class) != null) {
                hasObserves = true;
                break;
            }
        }
        assertThat(hasObserves).isTrue();

        {
            // test reading from an AnnotatedType
            AnnotatedTypeBuilder<Cat> builder2 = new AnnotatedTypeBuilder<Cat>();
            builder2.readFromType(cat);
            builder2.removeFromAll(Named.class);

            final AnnotatedType<Cat> noNameCat = builder2.create();
            assertThat(noNameCat.isAnnotationPresent(Named.class)).isFalse();
            assertThat(noNameCat.getAnnotations().size()).isEqualTo(2);
        }

        {

            // test reading from an AnnotatedType in non-overwrite mode
            AnnotatedTypeBuilder<Cat> builder3 = new AnnotatedTypeBuilder<Cat>();
            builder3.readFromType(cat, true);
            builder3.removeFromAll(Named.class);

            builder3.readFromType(cat, false);

            final AnnotatedType<Cat> namedCat = builder3.create();
            assertThat(namedCat.isAnnotationPresent(Named.class)).isTrue();
            assertThat(namedCat.getAnnotations().size()).isEqualTo(3);
        }
    }

    @Test
    void testAdditionOfAnnotation() {
        final AnnotatedTypeBuilder<Cat> builder = new AnnotatedTypeBuilder<Cat>();
        builder.readFromType(Cat.class, true);
        builder.addToClass(new TypedLiteral());

        final AnnotatedType<Cat> catAnnotatedType = builder.create();
        assertThat(catAnnotatedType.isAnnotationPresent(Typed.class)).isTrue();
    }

    @Test
    void modifyAnnotationsOnConstructorParameter() throws NoSuchMethodException {
        final AnnotatedTypeBuilder<Cat> builder = new AnnotatedTypeBuilder<Cat>();
        builder.readFromType(Cat.class, true);
        builder.removeFromConstructorParameter(Cat.class.getConstructor(String.class, String.class), 1, Default.class);
        builder.addToConstructorParameter(Cat.class.getConstructor(String.class, String.class), 1, new AnyLiteral());

        final AnnotatedType<Cat> catAnnotatedType = builder.create();
        Set<AnnotatedConstructor<Cat>> catCtors = catAnnotatedType.getConstructors();

        assertThat(catCtors.size()).isEqualTo(2);

        for (AnnotatedConstructor<Cat> ctor : catCtors) {
            if (ctor.getParameters().size() == 2) {
                List<AnnotatedParameter<Cat>> ctorParams = ctor.getParameters();

                assertThat(ctorParams.get(1).getAnnotations().size()).isEqualTo(1);
                assertThat((AnyLiteral) ctorParams.get(1).getAnnotations().toArray()[0]).isEqualTo(new AnyLiteral());
            }
        }
    }

    @Test
    void buildValidAnnotationAnnotatedType() {
        final AnnotatedTypeBuilder<Small> builder = new AnnotatedTypeBuilder<Small>();
        builder.readFromType(Small.class);
        final AnnotatedType<Small> smallAnnotatedType = builder.create();

        assertThat(smallAnnotatedType.getMethods().size()).isEqualTo(1);
        assertThat(smallAnnotatedType.getConstructors().size()).isEqualTo(0);
        assertThat(smallAnnotatedType.getFields().size()).isEqualTo(0);
    }

    @Test
    void testCtWithMultipleParams() {
        final AnnotatedTypeBuilder<TypeWithParamsInCt> builder = new AnnotatedTypeBuilder<TypeWithParamsInCt>();
        builder.readFromType(TypeWithParamsInCt.class);
        builder.addToClass(new AnnotationLiteral<Default>() {
        });

        AnnotatedType<TypeWithParamsInCt> newAt = builder.create();
        assertThat(newAt).isNotNull();
    }

    @Test
    void testEnumWithParam() {
        final AnnotatedTypeBuilder<EnumWithParams> builder = new AnnotatedTypeBuilder<EnumWithParams>();
        builder.readFromType(EnumWithParams.class);
        builder.addToClass(new AnnotationLiteral<Default>() {
        });

        AnnotatedType<EnumWithParams> newAt = builder.create();
        assertThat(newAt).isNotNull();
    }

    public static class TypeWithParamsInCt {
        public TypeWithParamsInCt(String a, int b, String c) {
            // all fine
        }
    }

    public enum EnumWithParams {
        VALUE("A");

        EnumWithParams(String val) {
            // all fine
        }
    }

    @Test
    void testExceptionPerformance() {
        long start = System.nanoTime();
        long val = -230349823423L;
        Exception e = new Exception("static");
        for (int i = 0; i < 10_000_000; i++) {
            try {
                val += 19;
                throw e;
            } catch (Exception e2) {
                // do nothing
            }
        }
        long end = System.nanoTime();
        System.out.println("Exeptions took ms " + TimeUnit.NANOSECONDS.toMillis(end - start));
    }

}
