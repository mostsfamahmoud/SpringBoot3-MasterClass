package com.luv2Code.demomvcsecurity.dao;

import com.luv2Code.demomvcsecurity.entity.User;

public interface UserDao {

    User findByUserName(String userName);
}
