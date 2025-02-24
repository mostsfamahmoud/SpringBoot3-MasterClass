package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(3)
public class MyApiAnalyticsAspect {

    // Applied for all methods in DAO package except getters/setters
    @Before("com.luv2code.aopdemo.aspect.MyAopExpressions.forDaoPackageNoGetterNoSetter()")
    public void performApiAnalyticsAdvice() {
        System.out.println("\n====>> Performing API Analytics");
    }
}
