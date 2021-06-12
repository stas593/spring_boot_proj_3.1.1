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

    @Autowired
    private UserDao ud;
    @Autowired
    private RoleDao rd;

    @Override
    @Transactional
    public void addUser(User user) {
        ud.addUser(user);
    }

    @Override
    @Transactional
    public User getUser(long id){
        return ud.getUser((int)id);
    }

    @Override
    @Transactional
    public User findUserByLogin(String userName) {
        return ud.findByLogin(userName);
    }

    @Override
    @Transactional
    public void deleteUser(long id) {
        ud.deleteUser((int)id);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        ud.updateUser(user);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return ud.getAllUsers();
    }


    @Override
    @Transactional
    public void addRoleToUser(long userId, String nameRole) {
        ud.addRoleToUser(userId, rd.getRoleByName(nameRole));
    }

    @Override
    @Transactional
    public Set<Role> getAllRolesFromUser(long userId) {
        return ud.getAllRolesFromUser(userId);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User myUser = ud.findByLogin(s);
        if (s == null) {
            throw new UsernameNotFoundException("Unknown user: "+s);
        }
        return myUser;
    }
}
