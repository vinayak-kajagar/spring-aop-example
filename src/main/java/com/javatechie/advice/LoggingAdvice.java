package com.javatechie.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAdvice {

    //    Logger logger= LoggerFactory.getLogger(LoggingAdvice.class);

    @Pointcut("execution(* com.javatechie.*.*.*(..))")
    //@Pointcut("within(com.javatechie..*)")
    //@Pointcut("target(com.javatechie.service.ProductService)")
    //@Pointcut("execution(* com.javatechie.service.ProductService.get*(int))")
//  @Pointcut("execution(* com.javatechie.controller.ProductController.*(..)) || " +
//            "execution(* com.javatechie.service.ProductService.*(..))")
    private void logPointcut(){
    }

    @Before("logPointcut()")
    public void logRequest(JoinPoint joinPoint) throws JsonProcessingException {
        log.info("class name {} ,method name {} ", joinPoint.getTarget(), joinPoint.getSignature().getName());
        log.info("Request Body {} ", new ObjectMapper().writeValueAsString(joinPoint.getArgs()));
    }


}
