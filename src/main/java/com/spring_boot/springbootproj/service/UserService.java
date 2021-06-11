package com.spring_boot.springbootproj.service;


import com.spring_boot.springbootproj.models.Role;
import com.spring_boot.springbootproj.models.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    void addUser(User user);
    User getUser(long id);
    User findUserByLogin(String userName);
    void deleteUser(long id);
    void updateUser(User user);
    List<User> getAllUsers();
    void addRoleToUser(long userId, String nameRole);
    Set<Role> getAllRolesFromUser(long userId);
}
