package com.luv2Code.demomvcsecurity.dao;

import com.luv2Code.demomvcsecurity.entity.Role;

public interface RoleDao {

    Role findRoleByName(String roleName);
}
