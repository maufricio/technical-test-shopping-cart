package com.service.orders.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Slf4j
@Aspect
@Component
public class LoggerAspect {

    @Around(
            "execution(* com.service.orders.controllers..*(..)) || " +
                    "execution(* com.service.orders.services..*(..))"
    )
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {

        String method = joinPoint.getSignature().toShortString();

        log.info("{} - START", method);

        Instant start = Instant.now();

        Object returnObj = joinPoint.proceed();

        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();

        log.info("{} - END ({} ms)", method, timeElapsed);

        return returnObj;
    }

    @AfterThrowing(
            value =
                    "execution(* com.service.orders.controllers..*(..)) || " +
                            "execution(* com.service.orders.services..*(..))",
            throwing = "ex"
    )
    public void logException(JoinPoint joinPoint, Exception ex) {

        String method = joinPoint.getSignature().toShortString();

        log.error("{} - EXCEPTION: {}", method, ex.getMessage());
    }
}

