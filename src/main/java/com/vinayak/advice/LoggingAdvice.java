package com.vinayak.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAdvice {

    //    Logger logger= LoggerFactory.getLogger(LoggingAdvice.class);

    @Pointcut("execution(* com.javatechie.*.*.*(..))")// anyPublicMethod insidePackageCom.JavaTechie insideAnySubPackage anyClass anyMethod(any argument)
    //@Pointcut("within(com.javatechie..*)") // other approach, go to this package each subpackage each class
    //@Pointcut("target(com.javatechie.service.ProductService)") // other approach
    //@Pointcut("execution(* com.javatechie.service.ProductService.get*(int))")
//  @Pointcut("execution(* com.javatechie.controller.ProductController.*(..)) || " +
//            "execution(* com.javatechie.service.ProductService.*(..))")
    private void logPointcut() {
    }

   //@Before("logPointcut()")     //before executing method
    public void logRequest(JoinPoint joinPoint) throws JsonProcessingException {
        log.info("class name {} ,method name {} ", joinPoint.getTarget(), joinPoint.getSignature().getName());
        log.info("Request Body {} ", new ObjectMapper().writeValueAsString(joinPoint.getArgs()));
    }

//    @AfterReturning(value = "execution (* com.javatechie.controller.ProductController.*(..))",returning = "object")
//    public void logResponse(JoinPoint joinPoint,Object object) throws JsonProcessingException { //define wat its returning in argument
//        log.info("LoggingAdvice::logResponse class name {} ,method name {} ", joinPoint.getTarget(), joinPoint.getSignature().getName());
//        log.info("LoggingAdvice::logResponse Response Body {} ", new ObjectMapper().writeValueAsString(object));
//    }

   // @After(value = "execution (* com.javatechie.controller.ProductController.*(..))")   //after executing method with or without exception
    public void logResponse(JoinPoint joinPoint) throws JsonProcessingException { //no need to pass what its returning
        log.info("LoggingAdvice::logResponse class name {} ,method name {} ", joinPoint.getTarget(), joinPoint.getSignature().getName());
        log.info("LoggingAdvice::logResponse Response Body {} ", new ObjectMapper().writeValueAsString(joinPoint.getArgs()));
    }


}
