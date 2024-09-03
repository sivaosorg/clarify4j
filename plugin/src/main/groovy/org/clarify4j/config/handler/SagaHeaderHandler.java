package org.clarify4j.config.handler;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.clarify4j.common.Clarify4j;
import org.clarify4j.common.annotation.SagaHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.unify4j.common.Json4j;
import org.unify4j.common.Request4j;

import java.lang.reflect.Method;
import java.util.Map;

@Aspect
@Component
public class SagaHeaderHandler {
    protected static Logger logger = LoggerFactory.getLogger(SagaHeaderHandler.class);

    @Around(value = "@annotation(org.clarify4j.common.annotation.SagaHeader)")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = joinPoint.proceed();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SagaHeader saga = method.getAnnotation(SagaHeader.class);
        if (saga.disabled()) {
            return proceed;
        }
        Map<String, Object> headers = Request4j.getHeaders(Clarify4j.getRequest());
        logger.info("Clarify4j, HTTP requesting: [JSESSIONID.REQ.ID]: {}, URL: {}, header(s): {}",
                Clarify4j.getCurrentSessionId(),
                Request4j.getFullUrl(Clarify4j.getRequest()),
                Json4j.toJson(headers));
        return proceed;
    }
}
