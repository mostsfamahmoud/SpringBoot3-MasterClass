package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

    // This is where we add all of our related advices for logging

    // @Before Advice
    // @Before("execution(public void add*())")
    // @Before("execution(* add*())")

    // Using the fully-qualified class name for parameters
    // @Before("execution(* add*(com.luv2code.aopdemo.Account, ..))")

    //@Before("execution(* add*(..))")
    // com.luv2code.aopdemo.dao

    // Match on any method in the package (com.luv2code.aopdemo.dao)
    @Before("execution(* com.luv2code.aopdemo.dao.*.*(..))")
    public void beforeAddAccountAdvice() {
        System.out.println("\n====>> Executing @Before advice on Method");
    }
}
