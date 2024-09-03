package org.clarify4j.common.annotation;

import org.clarify4j.config.validator.IPValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IPValidator.class)
@Documented
public @interface IsIP {
    String message() default "Invalid IP";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    boolean disabled() default false;
}
