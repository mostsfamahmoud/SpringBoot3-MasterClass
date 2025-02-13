package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

    // This is where we add all of our related advices for logging

    // 1. Creating pointcut declaration
    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.*(..))")
    private void forDaoPackage() {
    }

    // Apply pointcut declaration to the advice
    // Match on any method in the package (com.luv2code.aopdemo.dao)
    @Before("forDaoPackage()")
    public void beforeAddAccountAdvice() {
        System.out.println("\n====>> Executing @Before advice on Method");
    }

    // Reusing the pointcut declaration by applying it to another advice
    @Before("forDaoPackage()")
    public void performApiAnalytics() {
        System.out.println("\n====>> Performing API Analytics");
    }
}
