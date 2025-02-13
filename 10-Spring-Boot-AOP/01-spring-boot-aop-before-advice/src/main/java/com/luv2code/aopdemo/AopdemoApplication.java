package com.luv2code.aopdemo;

import com.luv2code.aopdemo.dao.AccountDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AopdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopdemoApplication.class, args);
    }


	@Bean
    public CommandLineRunner commandLineRunner(AccountDAO accountDAO) {
        return runner -> {

			//System.out.println("Hello World!");

            demoTheBeforeAdvice(accountDAO);
        };
    }

    private void demoTheBeforeAdvice(AccountDAO accountDAO) {

        // Call the Business Method
        accountDAO.addAccount();

        accountDAO.addAccount();
    }

}

