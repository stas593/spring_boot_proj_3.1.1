package com.spring_boot.springbootproj.service;

import com.spring_boot.springbootproj.dao.RoleDao;
import com.spring_boot.springbootproj.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class RoleServiceImpl implements RoleService {

    @Autowired
    public RoleDao rd;

    @Override
    public void addRole(Role role) {
        rd.addRole(role);
    }

    @Override
    public Role getRole(long id) {
        return rd.getRole(id);
    }

    @Override
    public Role getRoleByName(String roleName) {
        return rd.getRoleByName(roleName);
    }

    @Override
    public void deleteRole(long id) {
        rd.deleteRole(id);
    }

    @Override
    public void updateRole(Role role) {
        rd.updateRole(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return rd.getAllRoles();
    }
}
