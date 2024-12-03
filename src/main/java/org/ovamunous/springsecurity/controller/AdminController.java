package org.ovamunous.springsecurity.controller;


import jakarta.persistence.EntityNotFoundException;
import org.ovamunous.springsecurity.model.Role;
import org.ovamunous.springsecurity.model.User;
import org.ovamunous.springsecurity.service.RoleService;
import org.ovamunous.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class AdminController {
    private final PasswordEncoder passwordEncoder;
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String admin(ModelMap model) {
        return "admin";
    }


    @GetMapping("/admin/users")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String allUsers(ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            String currentUser = authentication.getName(); // Предполагается, что имя пользователя - это email
            String currentRole = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(", "));

            model.addAttribute("currentUser", currentUser);
            model.addAttribute("currentRole", currentRole);
        }
        List<User> users = userService.getUsers();
        List<Role> roles = roleService.getRoles();
        model.addAttribute("users", users);
        model.addAttribute("newUser", new User());
        model.addAttribute("roles", roles);
        return "index";
    }
    @PostMapping("/admin/users/add")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addUser(@ModelAttribute("newUser") User newUser,
                             @RequestParam(value = "role", defaultValue = "USER") String stringRoles, ModelMap model) {
        Set<Role> roles = Arrays.stream(stringRoles.split(",")).map(String::trim).map(String::toUpperCase)
                .map(t -> roleService.getRole(t)).collect(Collectors.toSet());
        newUser.setRoles(roles);
        userService.addUser(newUser);
        newUser = null;
        return "redirect:/admin/users";
    }
    @PostMapping("/admin/users/update")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String userAction(@ModelAttribute("newUser") User newUser, @RequestParam(value = "id") int id,
                             @RequestParam(value = "role", defaultValue = "USER") String stringRoles, ModelMap model) {
        Set<Role> roles = Arrays.stream(stringRoles.split(",")).map(String::trim).map(String::toUpperCase)
                .map(t -> roleService.getRole(t)).collect(Collectors.toSet());
        User oldUser = userService.getUser(id);
        oldUser.setRoles(roles);
        System.out.println(oldUser.getPassword());
        System.out.println(newUser.getPassword());
        System.out.println(!(oldUser.getPassword().equals(newUser.getPassword())));
        if (!(oldUser.getPassword().equals(newUser.getPassword()))) {
            oldUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        }
        oldUser.setEmail(newUser.getEmail());
        oldUser.setUsername(newUser.getUsername());
        userService.updateUser(oldUser);
        newUser = null;
        return "redirect:/admin/users";
    }
    @PostMapping("/admin/users/delete")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String userAction(@RequestParam(value = "id", defaultValue = "0") String id, ModelMap model) {
        userService.deleteUser(userService.getUser(Integer.parseInt(id)));
        return "redirect:/admin/users";
    }
}