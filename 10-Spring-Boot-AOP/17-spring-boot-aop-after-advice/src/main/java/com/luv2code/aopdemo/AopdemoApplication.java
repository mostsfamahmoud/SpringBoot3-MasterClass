package com.luv2code.aopdemo;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AopdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopdemoApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(AccountDAO accountDAO, MembershipDAO membershipDAO) {
        return runner -> {

            //System.out.println("Hello World!");

            //demoTheBeforeAdvice(accountDAO, membershipDAO);

            //demoTheAfterReturningAdvice(accountDAO);

            //demoTheAfterThrowingAdvice(accountDAO);

            demoTheAfterAdvice(accountDAO);
        };
    }

    private void demoTheAfterAdvice(AccountDAO accountDAO) {

        List<Account> accounts = null;

        try {
            boolean activateExp = false;
            accounts = accountDAO.findAccounts(activateExp);
        } catch (Exception exp) {
            System.out.println("\n\nMain Program: ... caught exception: " + exp);
        }

        // Displaying the accounts
        System.out.println("\n\nMain Program: demoTheAfterAdvice");
        System.out.println("----------------");
        System.out.println(accounts + "\n");
    }

    private void demoTheAfterThrowingAdvice(AccountDAO accountDAO) {

        List<Account> accounts = null;

        try {
            boolean activateExp = true;
            accounts = accountDAO.findAccounts(activateExp);
        } catch (Exception exp) {
            System.out.println("\n\nMain Program: ... caught exception: " + exp);
        }

        // Displaying the accounts
        System.out.println("\n\nMain Program: demoTheAfterThrowingAdvice");
        System.out.println("----------------");
        System.out.println(accounts + "\n");

    }

    private void demoTheAfterReturningAdvice(AccountDAO accountDAO) {

        List<Account> accounts = accountDAO.findAccounts();

        // Displaying the accounts
        System.out.println("\n\nMain Program: demoTheAfterReturningAdvice");
        System.out.println("----------------");
        System.out.println(accounts + "\n");

    }

    private void demoTheBeforeAdvice(AccountDAO accountDAO, MembershipDAO membershipDAO) {

        // Call the Business Method
        Account myAccount = new Account();
        myAccount.setName("Mostafa");
        myAccount.setLevel("Platinum");
        accountDAO.addAccount(myAccount, true);

        accountDAO.doWork();

        // Call the accountdao getter/setter method
        accountDAO.setName("foobar");

        accountDAO.setServiceCode("silver");

        String name = accountDAO.getName();

        String code = accountDAO.getServiceCode();

        // Call the Membership Business Method
        membershipDAO.addMember();

        membershipDAO.goToSleep();
    }
}

