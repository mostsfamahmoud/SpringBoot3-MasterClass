package com.luv2Code.springboot.cruddemo.dao;

import com.luv2Code.springboot.cruddemo.entity.Role;

public interface RoleDao {

    Role findRoleByName(String roleName);
}
