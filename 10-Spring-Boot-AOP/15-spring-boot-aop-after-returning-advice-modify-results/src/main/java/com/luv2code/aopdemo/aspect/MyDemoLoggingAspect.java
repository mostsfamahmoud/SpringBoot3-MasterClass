package com.luv2code.aopdemo.aspect;

import com.luv2code.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    // Applied for all methods in DAO package except getters/setters
    // Must use the fully qualified name of the class (com.luv2code.aopdemo.aspect.MyAopExpressions)
    @Before("com.luv2code.aopdemo.aspect.MyAopExpressions.forDaoPackageNoGetterNoSetter()")
    public void beforeAddAccountAdvice(JoinPoint joinPoint) {
        System.out.println("\n====>> Executing @Before advice on Method");

        // Display Method Signature
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("Method: " + methodSignature);

        // Display Method Arguments
        Object[] methodArgs = joinPoint.getArgs();

        System.out.println("Method Arguments:");
        for (Object tempArg : methodArgs) {
            System.out.println(tempArg);

            if (tempArg instanceof Account account) {
                // Cast the arg to be an account object to access it properties
                System.out.println("Account Name: " + account.getName());
                System.out.println("Account Level: " + account.getLevel());
            }

        }
    }

    // Add new advice for @AfterReturning on the findAccounts() methods
    @AfterReturning(
            pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result"
    )
    public void afterReturningFindAccountsAdvice(
            JoinPoint joinPoint, List<Account> result) {

        // Print out which method we are advising on
        String method = joinPoint.getSignature().toString();
        System.out.println("\n====>> Executing @AfterReturning on method: " + method);

        // Print out the results of the method call
        System.out.println("\n====>> Result: " + result);

        // Post-process the data (Modify it)

        // Convert the account names to UPPERCASE
        convertAccountNamesToUpperCase(result);

        // Print out the results of the method call
        System.out.println("\n====>> Result: " + result);

    }

    private void convertAccountNamesToUpperCase(List<Account> result) {

        // Loop through accounts
        for (Account account : result) {

            // Get UPPERCASE Version of name
            String upperName = account.getName().toUpperCase();

            // Update the name on the account
            account.setName(upperName);

        }


    }

/*
    // Applied for all methods in the DAO package
    @Before("forDaoPackage()")
    public void performApiAnalytics() {
        System.out.println("\n====>> Performing API Analytics");
    }

 */
}
