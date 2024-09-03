package org.clarify4j.config.handler;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.clarify4j.common.annotation.Saga;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class SagaHandler {
    protected static Logger logger = LoggerFactory.getLogger(SagaHandler.class);
    protected static final TemplateParserContext TEMPLATE_PARSER_CONTEXT = new TemplateParserContext("{{", "}}");
    protected static final ExpressionParser EXPRESSION_PARSER = new SpelExpressionParser();

    @Pointcut(value = "@annotation(org.clarify4j.common.annotation.Saga)")
    public void controller() {
    }

    @Before(value = "controller()")
    public void execute(JoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Saga saga = method.getAnnotation(Saga.class);
        if (saga.disable()) {
            return;
        }
        EvaluationContext context = new StandardEvaluationContext();
        Object[] args = joinPoint.getArgs();
        String[] parameters = signature.getParameterNames();

        for (int i = 0; i < args.length; i++) {
            context.setVariable(parameters[i], args[i]);
        }
        String value = EXPRESSION_PARSER.parseExpression(saga.expression(), TEMPLATE_PARSER_CONTEXT).getValue(context, String.class);
        logger.info(String.format("Clarify4j, %s", value));
    }
}
