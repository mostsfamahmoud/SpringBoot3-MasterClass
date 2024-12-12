package com.luv2Code.springboot.cruddemo.dao;

import com.luv2Code.springboot.cruddemo.entity.User;

public interface UserDao {

    User findByUserName(String userName);
}
