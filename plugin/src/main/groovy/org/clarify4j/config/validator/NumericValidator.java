package org.clarify4j.config.validator;

import org.clarify4j.common.annotation.IsNumeric;
import org.unify4j.common.Regex4j;
import org.unify4j.common.String4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NumericValidator implements ConstraintValidator<IsNumeric, String> {
    protected boolean disabled;

    @Override
    public void initialize(IsNumeric numeric) {
        this.disabled = numeric.disabled();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (this.disabled) {
            return true;
        }
        if (String4j.isEmpty(value)) {
            return false;
        }
        return Regex4j.isNumeric(value);
    }
}
