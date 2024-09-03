package org.clarify4j.config.validator;

import org.clarify4j.common.annotation.IsUrl;
import org.unify4j.common.Regex4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UrlValidator implements ConstraintValidator<IsUrl, String> {
    protected boolean disabled;

    @Override
    public void initialize(IsUrl url) {
        this.disabled = url.disabled();
    }

    @Override
    public boolean isValid(String url, ConstraintValidatorContext context) {
        if (this.disabled) {
            return true;
        }
        return Regex4j.isURL(url);
    }
}
