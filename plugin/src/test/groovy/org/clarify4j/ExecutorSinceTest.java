package org.clarify4j;

import org.clarify4j.config.annotation.ExecutorSince;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ExecutorSinceTest {
    @ExecutorSince
    private static class TestClass {
        @ExecutorSince
        public void testMethod() {
        }
    }

    @Test
    void annotationTypeShouldBeAvailableOnClass() {
        ExecutorSince annotation = TestClass.class.getAnnotation(ExecutorSince.class);
        assertNotNull(annotation, "Annotation should be present on the class.");
    }

    @Test
    void annotationTypeShouldBeAvailableOnMethod() throws NoSuchMethodException {
        Method method = TestClass.class.getMethod("testMethod");
        ExecutorSince annotation = method.getAnnotation(ExecutorSince.class);
        assertNotNull(annotation, "Annotation should be present on the method.");
    }

    @Test
    void shouldHaveRuntimeRetention() {
        RetentionPolicy retentionPolicy = ExecutorSince.class.getAnnotation(Retention.class).value();
        assertEquals(RetentionPolicy.RUNTIME, retentionPolicy, "Retention policy should be RUNTIME.");
    }

    @Test
    void shouldBeDocumented() {
        Annotation documented = ExecutorSince.class.getAnnotation(Documented.class);
        assertNotNull(documented, "Annotation should be documented.");
    }
}
