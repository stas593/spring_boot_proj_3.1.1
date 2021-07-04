package com.spring_boot.springbootproj.service;

import com.spring_boot.springbootproj.dao.RoleDao;
import com.spring_boot.springbootproj.dao.UserDao;
import com.spring_boot.springbootproj.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Component
public class RoleServiceImpl implements RoleService {

    public RoleDao roleDao;
    private UserDao userDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao, UserDao userDao) {
        this.roleDao = roleDao;
        this.userDao = userDao;
    }

    @Override
    public void addRole(Role role) {
        roleDao.save(role);
    }

    @Override
    public Role getRole(long id) {
        return roleDao.getById(id);
    }

    @Override
    public Role getRoleByName(String roleName) {
        return roleDao.findByNameRole(roleName);
    }

    @Override
    public void deleteRole(long id) {
        roleDao.deleteById(id);
    }

    @Override
    public void updateRole(Role role) {
        roleDao.save(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDao.findAll();
    }

    @Override
    @Transactional
    public Set<Role> getAllRolesFromUser(long userId) {
        return userDao.getById(userId).getRoles();
    }
}
