package org.clarify4j.config.validator;

import org.clarify4j.common.annotation.IsIP;
import org.unify4j.common.Regex4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IPValidator implements ConstraintValidator<IsIP, String> {
    protected boolean disabled;

    @Override
    public void initialize(IsIP ip) {
        this.disabled = ip.disabled();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (this.disabled) {
            return true;
        }
        return Regex4j.isIp(value);
    }
}
