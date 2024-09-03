package org.clarify4j.common.annotation;

import org.clarify4j.config.validator.NumericValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NumericValidator.class)
@Documented
public @interface IsNumeric {
    String message() default "Invalid type of numeric";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    boolean disabled() default false;
}
