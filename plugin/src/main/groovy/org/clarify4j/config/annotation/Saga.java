package org.clarify4j.config.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Saga {
    String expression();

    boolean disable() default false;

    Class<?> clazz() default Saga.class;
}
