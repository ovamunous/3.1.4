package org.ovamunous.springsecurity.controller;


import org.ovamunous.springsecurity.model.User;
import org.ovamunous.springsecurity.service.SecurityService;
import org.ovamunous.springsecurity.service.RoleService;
import org.ovamunous.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class AdminRestController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(userService.getUser(id));
    }
    @PutMapping("/{id}")
    public void updateUser(@RequestBody User user, @PathVariable long id) {
        user.setRoles(roleService.checkRoles(user.getRoles()));
        userService.updateUser(user, id);
    }
    @PostMapping
    public User addUser(@Validated @RequestBody User user) {
        user.setRoles(roleService.checkRoles(user.getRoles()));
        userService.addUser(user);
        return user;
    }
}