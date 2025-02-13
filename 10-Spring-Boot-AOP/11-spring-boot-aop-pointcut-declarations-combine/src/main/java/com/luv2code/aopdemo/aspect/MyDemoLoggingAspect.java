package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

    // This is where we add all of our related advices for logging

    // 1. Creating the pointcut declarations
    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.*(..))")
    private void forDaoPackage() {
    }

    // Create pointcut for Getter methods
    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.get*(..))")
    private void getter() {
    }

    // Create pointcut for Setter methods
    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.set*(..))")
    private void setter() {
    }

    // 2. Combine the pointcut declarations
    // Include Package and Exclude Getters and Setters
    @Pointcut("forDaoPackage() && !(getter() || setter())")
    private void forDaoPackageNoGetterNoSetter() {
    }

    // 3. Apply pointcut declaration to the advice

    // Applied for all methods in DAO package except getters/setters
    @Before("forDaoPackageNoGetterNoSetter()")
    public void beforeAddAccountAdvice() {
        System.out.println("\n====>> Executing @Before advice on Method");
    }

/*
    // Applied for all methods in the DAO package
    @Before("forDaoPackage()")
    public void performApiAnalytics() {
        System.out.println("\n====>> Performing API Analytics");
    }

 */
}
