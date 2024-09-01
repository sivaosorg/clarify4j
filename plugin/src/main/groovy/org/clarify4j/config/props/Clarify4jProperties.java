package org.clarify4j.config.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@ConfigurationProperties(prefix = "spring.wizards2s4j")
public class Clarify4jProperties implements Serializable {
    public Clarify4jProperties() {
        super();
    }
}
