package com.spring_boot.springbootproj.service;

import com.spring_boot.springbootproj.dao.RoleDao;
import com.spring_boot.springbootproj.dao.UserDao;
import com.spring_boot.springbootproj.models.Role;
import com.spring_boot.springbootproj.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Component
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserDao userDao;
    private RoleDao roleDao;

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    @Transactional
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    @Transactional
    public User getUser(long id){
        return userDao.getUser((int)id);
    }

    @Override
    @Transactional
    public User findUserByLogin(String userName) {
        return userDao.findByLogin(userName);
    }

    @Override
    @Transactional
    public void deleteUser(long id) {
        userDao.deleteUser((int)id);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }


    @Override
    @Transactional
    public void addRoleToUser(long userId, String nameRole) {
        userDao.addRoleToUser(userId, roleDao.getRoleByName(nameRole));
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User myUser = userDao.findByLogin(s);
        if (s == null) {
            throw new UsernameNotFoundException("Unknown user: "+s);
        }
        return myUser;
    }
}
