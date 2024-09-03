package org.clarify4j.config.validator;

import org.clarify4j.common.annotation.IsTime;
import org.unify4j.common.Regex4j;
import org.unify4j.common.String4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TimeValidator implements ConstraintValidator<IsTime, String> {
    protected boolean disabled;

    @Override
    public void initialize(IsTime time) {
        this.disabled = time.disabled();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (this.disabled) {
            return true;
        }
        if (String4j.isEmpty(value)) {
            return false;
        }
        return Regex4j.isTime24HFully(value) || Regex4j.isTime12HFully(value);
    }
}

