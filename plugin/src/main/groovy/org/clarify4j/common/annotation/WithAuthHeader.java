package org.clarify4j.common.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface WithAuthHeader {
    boolean disabled() default false;

    String message() default "Access denied";

    String key() default "x-api-key";

    String value() default "";
}
