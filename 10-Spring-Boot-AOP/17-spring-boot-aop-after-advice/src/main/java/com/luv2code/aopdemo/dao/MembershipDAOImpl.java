package com.luv2code.aopdemo.dao;

import org.springframework.stereotype.Repository;

@Repository
public class MembershipDAOImpl implements MembershipDAO {
    @Override
    public boolean addMember() {
        System.out.println("[" + getClass() + "] Doing My DB Work: Adding a Membership Account");
        return true;
    }

    @Override
    public void goToSleep() {
        System.out.println("[" + getClass() + "] goToSleep()");
    }
}
