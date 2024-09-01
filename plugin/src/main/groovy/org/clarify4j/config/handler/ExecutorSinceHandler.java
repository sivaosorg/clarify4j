package org.clarify4j.config.handler;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.unify4j.common.Time4j;

import java.util.Date;

@Aspect
@Component
public class ExecutorSinceHandler {
    protected static final Logger logger = LoggerFactory.getLogger(ExecutorSinceHandler.class);

    @Around(value = "@annotation(org.clarify4j.common.annotation.ExecutorSince)")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        Date start = new Date();
        Object proceed = joinPoint.proceed();
        String since = Time4j.sinceSmallRecently(new Date(), start);
        Signature signature = joinPoint.getSignature();
        String clazz = joinPoint.getTarget().getClass().getSimpleName();
        String method = signature.getName();
        logger.info("Execution of method '{}' in class '{}' completed in {}", method, clazz, since);
        return proceed;
    }
}
