package org.clarify4j.config.handler;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.clarify4j.common.Clarify4j;
import org.clarify4j.common.annotation.SagaAuthHeader;
import org.clarify4j.service.Clarify4jService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.unify4j.common.Request4j;

import java.lang.reflect.Method;
import java.util.Map;

@Aspect
@Component
public class SagaAuthHeaderHandler {
    protected static final Logger logger = LoggerFactory.getLogger(SagaAuthHeaderHandler.class);

    protected final Clarify4jService clarify4jService;

    @Autowired
    public SagaAuthHeaderHandler(Clarify4jService environment) {
        this.clarify4jService = environment;
    }

    /**
     * AOP advice that intercepts method calls annotated with {@link SagaAuthHeader} and performs
     * authentication checks based on the presence and value of specified headers.
     * <p>
     * This method is executed around the annotated method. It first proceeds with the method execution
     * and then checks if authentication is disabled through the annotation. If authentication is
     * enabled, it resolves the header key and expected value from the annotation, retrieves the
     * headers from the current request, and validates the presence and value of the specified
     * authentication header. If the header is missing or its value does not match the expected value,
     * an {@link AccessDeniedException} is thrown with an appropriate message.
     *
     * @param joinPoint The join point providing reflective access to both the state available at
     *                  a join point and static information about it. It represents the method
     *                  being intercepted.
     * @return The result of the method execution if authentication is successful; otherwise, an
     * {@link AccessDeniedException} is thrown.
     * @throws Throwable If the underlying method throws an exception, it will propagate it through
     *                   this method.
     */
    @Around(value = "@annotation(org.clarify4j.common.annotation.SagaAuthHeader)")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = joinPoint.proceed();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SagaAuthHeader auth = method.getAnnotation(SagaAuthHeader.class);
        if (auth.disabled()) {
            return proceed;
        }
        String headerKey = clarify4jService.resolveValue(auth.key());
        String headerValue = clarify4jService.resolveValue(auth.value());
        Map<String, Object> headers = Request4j.getHeaders(Clarify4j.getRequest());
        if (!headers.containsKey(headerKey)) {
            String message = String.format("Authentication header by key: '%s' not found", headerKey);
            throw new AccessDeniedException(message);
        }
        if (!headers.get(headerKey).equals(headerValue)) {
            throw new AccessDeniedException(auth.message());
        }
        return proceed;
    }
}
