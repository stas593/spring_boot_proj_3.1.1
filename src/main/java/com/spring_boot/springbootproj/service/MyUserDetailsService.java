package com.spring_boot.springbootproj.service;

import com.spring_boot.springbootproj.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDetailService us;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User myUser = us.findUserByLogin(s);
        if (s == null) {
            throw new UsernameNotFoundException("Unknown user: "+s);
        }
        return myUser;
    }
}
