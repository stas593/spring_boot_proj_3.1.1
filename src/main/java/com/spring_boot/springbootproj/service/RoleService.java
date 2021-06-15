package com.spring_boot.springbootproj.service;


import com.spring_boot.springbootproj.models.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    void addRole(Role role);
    Role getRole(long id);
    Role getRoleByName(String roleName);
    void deleteRole(long id);
    void updateRole(Role role);
    List<Role> getAllRoles();
    Set<Role> getAllRolesFromUser(long userId);
}
