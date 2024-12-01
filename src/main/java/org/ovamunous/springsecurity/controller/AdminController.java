package org.ovamunous.springsecurity.controller;


import jakarta.persistence.EntityNotFoundException;
import org.ovamunous.springsecurity.model.Role;
import org.ovamunous.springsecurity.model.User;
import org.ovamunous.springsecurity.service.RoleService;
import org.ovamunous.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class AdminController {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String admin(ModelMap model) {

        return "admin";
    }


    @GetMapping("/admin/users")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String allUsers(ModelMap model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        model.addAttribute("newUser", new User());
        return "index";
    }

    @PostMapping("/admin/users/userAction")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String userAction(@ModelAttribute("newUser") User newUser,
                         @RequestParam("action") String action, @RequestParam(value = "id") String id,
                         @RequestParam(value = "role", defaultValue = "USER") String stringRoles, ModelMap model) {
        switch (action) {
            case "add":
                userService.addUser(newUser, stringRoles);
                break;
            case "update":
                try {
                    User oldUser = userService.getUser(Integer.parseInt(id));
                    oldUser.setPassword(newUser.getPassword());
                    oldUser.setEmail(newUser.getEmail());
                    oldUser.setUsername(newUser.getUsername());
                    userService.updateUser(oldUser);
                } catch (Exception e) {
                    throw new EntityNotFoundException("User not found");
                }
                break;
            case "delete":
                try {
                    userService.deleteUser(userService.getUser(Integer.parseInt(id)));
                } catch (Exception e) {
                    throw new EntityNotFoundException("User not found");
                }
                break;
        }
        newUser = null;
        return "redirect:/admin/users";
    }
}