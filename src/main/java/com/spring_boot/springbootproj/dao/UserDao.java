package com.spring_boot.springbootproj.dao;


import com.spring_boot.springbootproj.models.Role;
import com.spring_boot.springbootproj.models.User;

import java.util.List;
import java.util.Set;

public interface UserDao {
    void addUser(User user);
    User getUser(long id);
    User findByLogin(String userName);
    void deleteUser(long id);
    void updateUser(User user);
    List<User> getAllUsers();
    void addRoleToUser(long userId, Role role);
    Set<Role> getAllRolesFromUser(long userId);
}
