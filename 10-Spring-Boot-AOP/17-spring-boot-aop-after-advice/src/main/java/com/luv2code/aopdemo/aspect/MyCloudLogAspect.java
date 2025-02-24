package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(1) // The lower the number, the higher the priority
public class MyCloudLogAspect {

    @Before("com.luv2code.aopdemo.aspect.MyAopExpressions.forDaoPackageNoGetterNoSetter()")
    public void logToCloudAsyncAdvice() {
        System.out.println("\n====>> Logging To Cloud In Async Fashion");
    }
}
