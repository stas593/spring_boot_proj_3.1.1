package com.spring_boot.springbootproj.controllers;

import com.spring_boot.springbootproj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.spring_boot.springbootproj.models.User;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class Rest {

    private final UserService userService;

    @Autowired
    public Rest(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getListAllUsers(){
        List<User> users = userService.getAllUsers();
        return users;
    }
}
