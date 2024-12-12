package com.luv2Code.springboot.cruddemo.service;

import com.luv2Code.springboot.cruddemo.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByUserName(String userName);
}
