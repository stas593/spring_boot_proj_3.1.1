package com.spring_boot.springbootproj.controllers;

import com.spring_boot.springbootproj.service.RoleService;
import com.spring_boot.springbootproj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.spring_boot.springbootproj.models.User;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class Rest {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public Rest(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/userInfo")
    public User getUserInfo(Principal principal){
        User user = userService.findUserByLogin(principal.getName());
        return user;
    }

    @GetMapping("/users")
    public List<User> getListAllUsers(){
        List<User> users = userService.getAllUsers();
        return users;
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable long id){
        User user = userService.getUser(id);
        return user;
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User user){
        user.addRoleToUser(roleService.getRoleByName(user.getNewRole()));
        userService.addUser(user);
        return user;

    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user){
        System.out.println(user.toString());
        if(user.getNewRole() == null){
            user.setRoles(userService.getUser(user.getId()).getRoles());
            userService.updateUser(user);
        } else {
            user.addRoleToUser(roleService.getRoleByName(user.getNewRole()));
            userService.updateUser(user);
        }
        return user;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable long id){
        userService.deleteUser(id);
    }
}
