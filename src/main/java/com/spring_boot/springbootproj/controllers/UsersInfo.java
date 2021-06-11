package com.spring_boot.springbootproj.controllers;

import com.spring_boot.springbootproj.models.Role;
import com.spring_boot.springbootproj.models.User;
import com.spring_boot.springbootproj.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;

@Controller
@RequestMapping("")
public class UsersInfo {

    @Autowired
    private UserDetailService us;

    @GetMapping(value = "/")
    public String mainStr(Principal principal, Model model){
        model.addAttribute("principal", principal);
        return "index";
    }

    @GetMapping(value = "/admin")
    public String postInfo(Model model, Principal principal){
        model.addAttribute("roles", us.getAllRoles());
        model.addAttribute("users", us.getAllUsers());
        model.addAttribute("user", new User());
        model.addAttribute("role", new Role());
        model.addAttribute("autUser", us.findUserByLogin(principal.getName()));
        return "admin";
    }

    @GetMapping(value = "/user")
    public String UserStr(Model model, Principal principal){
        model.addAttribute("user", us.findUserByLogin(principal.getName()));
        return "user";
    }


    @GetMapping(value = "/deleteUser")
    public String deleteUser(@RequestParam("id") long id){
        us.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping(value = "/addUser")
    public String addUser(@ModelAttribute("user") User user, @RequestParam(name = "roleName") String roleName){
        user.addRoleToUser(us.getRoleByName(roleName));
        us.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/updateUser")
    public String updateUser(@RequestParam("id") long id, Model model){
        model.addAttribute("roles", us.getAllRoles());
        User user = us.getUser(id);
        model.addAttribute("userUp", user);
        return "update-user";
    }

    @GetMapping(value = "/saveUser")
    public String updateUser(@ModelAttribute User user, @RequestParam(name = "roleName", required = false) String roleName){
        System.out.println(user.toString());
        if(roleName == null){
            user.setRoles(us.getUser(user.getId()).getRoles());
            us.updateUser(user);
        } else {
            user.addRoleToUser(us.getRoleByName(roleName));
            us.updateUser(user);
        }
        return "redirect:/admin";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }
}
