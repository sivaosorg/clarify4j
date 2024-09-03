package org.clarify4j.config.validator;

import org.clarify4j.common.annotation.IsEmail;
import org.unify4j.common.Regex4j;
import org.unify4j.common.String4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<IsEmail, String> {
    protected boolean disabled;

    @Override
    public void initialize(IsEmail url) {
        this.disabled = url.disabled();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (this.disabled) {
            return true;
        }
        if (String4j.isEmpty(value)) {
            return false;
        }
        return Regex4j.isEmail(value);
    }
}

