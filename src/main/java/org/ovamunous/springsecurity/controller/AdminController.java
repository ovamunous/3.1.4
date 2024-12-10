package org.ovamunous.springsecurity.controller;


import org.ovamunous.springsecurity.model.Role;
import org.ovamunous.springsecurity.model.User;
import org.ovamunous.springsecurity.service.SecurityService;
import org.ovamunous.springsecurity.service.RoleService;
import org.ovamunous.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@Controller
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;
    private final SecurityService controllerService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService, SecurityService controllerService) {
        this.userService = userService;
        this.roleService = roleService;
        this.controllerService = controllerService;
    }

    @GetMapping("/admin")
    public String admin(ModelMap model) {
        return "admin";
    }


    @GetMapping("/admin/users")
    public String allUsers(ModelMap model) {
        controllerService.getAuthentication(model);
        List<User> users = userService.getUsers();
        List<Role> roles = roleService.getRoles();
        model.addAttribute("users", users);
        model.addAttribute("newUser", new User());
        model.addAttribute("roles", roles);
        return "index";
    }

    @PostMapping("/admin/users/add")
    public String addUser(@Validated @ModelAttribute("newUser") User newUser,
                          @RequestParam(value = "role", defaultValue = "USER") String stringRoles, ModelMap model) {
        Set<Role> roles = roleService.getRolesByString(stringRoles);
        newUser.setRoles(roles);
        userService.addUser(newUser);
        return "redirect:/admin/users";
    }

    @PostMapping("/admin/users/update")
    public String userAction(@Validated @ModelAttribute("newUser") User newUser, @RequestParam(value = "id") int id,
                             @RequestParam(value = "role", defaultValue = "USER") String stringRoles, ModelMap model) {
        Set<Role> roles = roleService.getRolesByString(stringRoles);
        userService.updateUser(newUser, id, roles);
        return "redirect:/admin/users";
    }

    @PostMapping("/admin/users/delete")
    public String userAction(@RequestParam(value = "id", defaultValue = "0") String id, ModelMap model) {
        userService.deleteUser(userService.getUser(Integer.parseInt(id)));
        return "redirect:/admin/users";
    }
}