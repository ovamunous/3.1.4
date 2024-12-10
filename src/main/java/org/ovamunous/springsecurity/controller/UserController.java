package org.ovamunous.springsecurity.controller;


import org.ovamunous.springsecurity.model.User;
import org.ovamunous.springsecurity.service.SecurityService;
import org.ovamunous.springsecurity.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UserController {
    private final UserService userService;
    private final SecurityService securityService;

    public UserController(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }

    @GetMapping("/user")
    public String welcomeUser(ModelMap model) {
        String currentUsername = securityService.getAuthenticationName();
        String currentRole = securityService.getAuthenticationRoles();
        User currentUser = userService.getUserByUsername(currentUsername);
        model.addAttribute("user", currentUser);
        model.addAttribute("currentUser", currentUsername);
        model.addAttribute("currentRole", currentRole);
        return "user";
    }
}
