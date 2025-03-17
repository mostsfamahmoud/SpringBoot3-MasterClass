package com.luv2Code.demomvcsecurity.service;

import com.luv2Code.demomvcsecurity.dao.RoleDao;
import com.luv2Code.demomvcsecurity.dao.UserDao;
import com.luv2Code.demomvcsecurity.entity.Role;
import com.luv2Code.demomvcsecurity.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service // Marks this class as a Spring service component, enabling dependency injection
public class UserServiceImpl implements UserService {

    private UserDao userDao; // DAO for user-related database operations
    private RoleDao roleDao; // DAO for role-related database operations

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao; // Injects the UserDao dependency
        this.roleDao = roleDao; // Injects the RoleDao dependency
    }

    /**
     * Finds a user by their username.
     *
     * @param userName the username to search for
     * @return the User entity, or null if not found
     */
    @Override
    public User findByUserName(String userName) {
        return userDao.findByUserName(userName); // Calls the UserDao to retrieve a user by username
    }

    /**
     * Maps a collection of Role entities to a collection of GrantedAuthority objects.
     *
     * @param roles the roles to map
     * @return a collection of GrantedAuthority objects
     */
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles
                .stream() // Converts the roles collection to a stream
                .map(role -> new SimpleGrantedAuthority(role.getName())) // Maps each Role to a SimpleGrantedAuthority
                .collect(Collectors.toList()); // Collects the results into a list
    }

    /**
     * Loads user details for Spring Security authentication.
     *
     * @param username the username to search for
     * @return UserDetails object for Spring Security
     * @throws UsernameNotFoundException if the username is not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetches the User entity using the UserDao
        User user = userDao.findByUserName(username);

        // If the user is not found, throw an exception
        if (user == null) {
            throw new UsernameNotFoundException("Invalid Username or Password");
        }

        // Return a UserDetails implementation provided by Spring Security
        return new org.springframework.security.core.userdetails.User(
                user.getUserName(), // Username
                user.getPassword(), // Password
                mapRolesToAuthorities(user.getRoles()) // Granted authorities (roles)
        );
    }
}

