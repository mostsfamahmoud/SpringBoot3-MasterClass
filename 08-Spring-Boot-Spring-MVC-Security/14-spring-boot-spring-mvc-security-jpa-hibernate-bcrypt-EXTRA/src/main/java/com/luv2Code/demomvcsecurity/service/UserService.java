package com.luv2Code.demomvcsecurity.service;

import com.luv2Code.demomvcsecurity.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByUserName(String userName);
}
