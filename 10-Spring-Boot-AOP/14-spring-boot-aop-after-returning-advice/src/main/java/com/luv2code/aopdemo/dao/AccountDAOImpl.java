package com.luv2code.aopdemo.dao;

import com.luv2code.aopdemo.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountDAOImpl implements AccountDAO {

    private String name;

    private String serviceCode;

    @Override
    public void addAccount(Account account, boolean vipFlag) {
        System.out.println("[" + getClass() + "] Doing My DB Work: Adding an Account");
    }

    @Override
    public boolean doWork() {

        System.out.println("[" + getClass() + "] doWork()");
        return false;
    }

    @Override
    public String getName() {
        System.out.println("[" + getClass() + "] getName()");
        return name;
    }

    @Override
    public void setName(String name) {
        System.out.println("[" + getClass() + "] setName()");
        this.name = name;
    }

    @Override
    public String getServiceCode() {
        System.out.println("[" + getClass() + "] getServiceCode()");
        return serviceCode;
    }

    @Override
    public void setServiceCode(String serviceCode) {
        System.out.println("[" + getClass() + "] setServiceCode()");
        this.serviceCode = serviceCode;
    }

    @Override
    public List<Account> findAccounts() {

        List<Account> myAccounts = new ArrayList<>();

        // Creating sample accounts and Adding them to the accounts list
        myAccounts.add(new Account("Mostafa", "Platinum"));
        myAccounts.add(new Account("John", "Diamond"));
        myAccounts.add(new Account("Jane", "Gold"));

        return myAccounts;
    }
}
