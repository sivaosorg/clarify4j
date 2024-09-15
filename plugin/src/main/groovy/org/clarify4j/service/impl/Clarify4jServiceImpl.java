package org.clarify4j.service.impl;

import org.clarify4j.service.Clarify4jService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.unify4j.common.String4j;

@SuppressWarnings({"FieldCanBeLocal", "DuplicatedCode"})
@Service
public class Clarify4jServiceImpl implements Clarify4jService {
    protected static final Logger logger = LoggerFactory.getLogger(Clarify4jServiceImpl.class);

    protected final Environment environment;

    @Autowired
    public Clarify4jServiceImpl(Environment environment) {
        this.environment = environment;
    }

    /**
     * Resolves placeholders in a given string value using Spring's environment property resolution.
     * <p>
     * This method checks if the provided value string contains placeholders in the format of
     * "${property.name}". If it does, the method uses Spring's `Environment` to resolve these
     * placeholders to their actual values from the application's property sources (such as
     * application.yml or application.properties). If the value does not contain placeholders,
     * it is returned as-is.
     *
     * @param value The string value to be resolved. This can be a literal string or a string
     *              containing placeholders.
     * @return The resolved value if it contains placeholders, or the original value if no
     * placeholders are present.
     */
    @Override
    public String resolveValue(String value) {
        if (String4j.isEmpty(value)) {
            return value;
        }
        if (value.startsWith("${") && value.endsWith("}")) {
            return environment.resolvePlaceholders(value);
        }
        return value;
    }
}
