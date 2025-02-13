package com.luv2code.aopdemo.dao;

import com.luv2code.aopdemo.Account;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAOImpl implements AccountDAO {


    @Override
    public void addAccount(Account account, boolean vipFlag) {
        System.out.println("[" + getClass() + "] Doing My DB Work: Adding an Account");
    }
}
