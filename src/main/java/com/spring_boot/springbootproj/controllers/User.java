package com.spring_boot.springbootproj.controllers;

import com.spring_boot.springbootproj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping(value = "/user")
public class User {

    @Autowired
    private UserService us;

    @RequestMapping(value = "")
    public String UserStr(Model model, Principal principal){
        model.addAttribute("User", us.findUserByLogin(principal.getName()));
        return "user";
    }
}
