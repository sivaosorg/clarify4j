package org.clarify4j.config.handler;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.clarify4j.common.Clarify4j;
import org.clarify4j.common.annotation.WithAuthHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.unify4j.common.Request4j;

import java.lang.reflect.Method;
import java.util.Map;

@Aspect
@Component
public class WithAuthHeaderHandler {
    protected static final Logger logger = LoggerFactory.getLogger(WithAuthHeaderHandler.class);

    @Around(value = "@annotation(org.clarify4j.common.annotation.WithAuthHeader)")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = joinPoint.proceed();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        WithAuthHeader auth = method.getAnnotation(WithAuthHeader.class);
        if (auth.disabled()) {
            return proceed;
        }
        Map<String, Object> headers = Request4j.getHeaders(Clarify4j.getRequest());
        if (!headers.containsKey(auth.key())) {
            String message = String.format("Authentication header by key: '%s' not found", auth.key());
            throw new AccessDeniedException(message);
        }
        if (!headers.get(auth.key()).equals(auth.value())) {
            throw new AccessDeniedException(auth.message());
        }
        return proceed;
    }
}
