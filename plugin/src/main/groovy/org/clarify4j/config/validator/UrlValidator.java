package org.clarify4j.config.validator;

import org.clarify4j.common.annotation.IsUrl;
import org.unify4j.common.Regex4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UrlValidator implements ConstraintValidator<IsUrl, String> {

    @Override
    public boolean isValid(String url, ConstraintValidatorContext context) {
        return Regex4j.isURL(url);
    }
}
