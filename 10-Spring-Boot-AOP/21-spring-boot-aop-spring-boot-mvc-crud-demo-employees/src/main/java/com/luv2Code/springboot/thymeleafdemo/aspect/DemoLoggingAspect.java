// Define the package for the Aspect-Oriented Programming (AOP) aspect
package com.luv2Code.springboot.thymeleafdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * Aspect for logging application execution flow.
 * <p>
 * This aspect logs method calls and their return values in the controller,
 * service, and DAO layers using Spring AOP.
 */
@Aspect
@Component
public class DemoLoggingAspect {

    // Setup Logger for this class to log method calls and return values
    private final Logger myLogger = Logger.getLogger(getClass().getName());

    /**
     * Pointcut declaration for matching any method in any class
     * inside the controller package.
     */
    @Pointcut("execution(* com.luv2Code.springboot.thymeleafdemo.controller.*.*(..))")
    private void forControllerPackage() {
    }

    /**
     * Pointcut declaration for matching any method in any class
     * inside the service package.
     */
    @Pointcut("execution(* com.luv2Code.springboot.thymeleafdemo.service.*.*(..))")
    private void forServicePackage() {
    }

    /**
     * Pointcut declaration for matching any method in any class
     * inside the DAO (Data Access Object) package.
     */
    @Pointcut("execution(* com.luv2Code.springboot.thymeleafdemo.dao.*.*(..))")
    private void forDaoPackage() {
    }

    /**
     * Pointcut that combines the above three pointcuts.
     * This applies logging to all methods in the controller, service, and DAO layers.
     */
    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow() {
    }

    /**
     * Advice that runs before any method matched by the forAppFlow() pointcut.
     * Logs the method signature and its arguments before execution.
     *
     * @param joinPoint Provides runtime information about the method being called.
     */
    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint) {

        // Retrieve the method signature being called
        String methodSignature = joinPoint.getSignature().toString();
        myLogger.info("===>> In @Before: Calling method: " + methodSignature);

        // Retrieve and log the arguments passed to the method
        Object[] methodArgs = joinPoint.getArgs();
        for (Object arg : methodArgs) {
            myLogger.info("======>> Argument: " + arg);
        }
    }

    /**
     * Advice that runs after a method successfully returns.
     * Logs the method signature and the returned result.
     *
     * @param joinPoint Provides runtime information about the method being executed.
     * @param result    The value returned by the method.
     */
    @AfterReturning(
            pointcut = "forAppFlow()", // Apply to all matched methods
            returning = "result"       // Capture the returned result
    )
    public void afterReturning(JoinPoint joinPoint, Object result) {

        // Retrieve the method signature from which we are returning
        String methodSignature = joinPoint.getSignature().toString();
        myLogger.info("===>> In @AfterReturning: From method: " + methodSignature);

        // Log the returned result
        myLogger.info("===>> The Result: " + result);
    }
}
