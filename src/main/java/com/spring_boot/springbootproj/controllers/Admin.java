package com.spring_boot.springbootproj.controllers;

import com.spring_boot.springbootproj.models.Role;
import com.spring_boot.springbootproj.service.RoleService;
import com.spring_boot.springbootproj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.spring_boot.springbootproj.models.User;
import sun.plugin.liveconnect.SecurityContextHelper;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;

@Controller
@RequestMapping(value = "/admin")
public class Admin {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public Admin(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "")
    public String postInfo(Model model, Principal principal){
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("autUser", userService.findUserByLogin(principal.getName()));
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(a.getAuthorities());
        model.addAttribute("user", new User());
        model.addAttribute("userUp", new User());
        model.addAttribute("role", new Role());
        return "admin";
    }


    @GetMapping(value = "/deleteUser")
    public String deleteUser(@RequestParam("id") long id){
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @PostMapping(value = "/addUser")
    public String addUser(@ModelAttribute("user") User user, @RequestParam(name = "roleName") String roleName){
        user.addRoleToUser(roleService.getRoleByName(roleName));
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/updateUser")
    public String updateUser(@RequestParam("id") long id, Model model){
        model.addAttribute("roles", roleService.getAllRoles());
        User user = userService.getUser(id);
        model.addAttribute("userUp", user);
        return "update-user";
    }

    @PostMapping(value = "/saveUser")
    public String updateUser(@ModelAttribute(name = "userUp") User user, @RequestParam(name = "roleName", required = false) String roleName){
        System.out.println(user.toString());
        if(roleName == null){
            user.setRoles(userService.getUser(user.getId()).getRoles());
            userService.updateUser(user);
        } else {
            user.addRoleToUser(roleService.getRoleByName(roleName));
            userService.updateUser(user);
        }
        return "redirect:/admin";
    }
}
