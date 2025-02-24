package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;


/**
 * @Aspect is optional here if you have only Pointcuts (No advices)
 * But i added it in case of adding advices here in the future
 */
@Aspect
public class MyAopExpressions {
    // 1. Creating the pointcut declarations
    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.*(..))")
    public void forDaoPackage() {
    }

    // Create pointcut for Getter methods
    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.get*(..))")
    public void getter() {
    }

    // Create pointcut for Setter methods
    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.set*(..))")
    public void setter() {
    }

    // 2. Combine the pointcut declarations
    // Include Package and Exclude Getters and Setters
    @Pointcut("forDaoPackage() && !(getter() || setter())")
    public void forDaoPackageNoGetterNoSetter() {
    }
}
