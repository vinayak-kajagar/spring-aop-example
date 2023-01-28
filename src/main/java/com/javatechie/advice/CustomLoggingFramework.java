package com.javatechie.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class CustomLoggingFramework {


    //before & after returning
    //@Around("execution (* com.javatechie.*.*.*(..))")
    //@Around("@annotation(com.javatechie.annotation.LogRequestAndResponse)")
    public Object captureRequestAndResponse(ProceedingJoinPoint pjp) throws Throwable {
        //execute your before logic
        System.out.println("=====================BEFORE START==========================================");
        log.info("execution started {} ", pjp.getSignature());
        log.info("Request body {}", new ObjectMapper().writeValueAsString(pjp.getArgs()));
        System.out.println("=====================BEFORE END============================================");
        Object obj = pjp.proceed();
        //after logic

        System.out.println("=====================AFTER START===========================================");
        log.info("execution ended {} ", pjp.getSignature());
        log.info("Response  body {}", new ObjectMapper().writeValueAsString(obj));
        System.out.println("=====================AFTER END=============================================");
        return obj;
    }
}
