package org.clarify4j.common.annotation;

import org.clarify4j.config.validator.UrlValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UrlValidator.class)
public @interface IsUrl {
    String message() default "invalid URL";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
