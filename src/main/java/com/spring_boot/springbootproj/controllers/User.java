package com.spring_boot.springbootproj.controllers;

import com.spring_boot.springbootproj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping(value = "/user")
public class User {

    private UserService userService;

    @Autowired
    public User(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "")
    public String UserStr(Model model, Principal principal){
        model.addAttribute("user", userService.findUserByLogin(principal.getName()));
        return "user";
    }
}
