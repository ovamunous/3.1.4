package org.ovamunous.springsecurity.controller;


import org.ovamunous.springsecurity.service.SecurityService;
import org.ovamunous.springsecurity.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UserController {
    private final UserService userService;
    private final SecurityService controllerService;

    public UserController(UserService userService, SecurityService controllerService) {
        this.userService = userService;
        this.controllerService = controllerService;
    }

    @GetMapping("/user")
    public String welcomeUser(ModelMap model) {
        controllerService.getAuthentication(model);
        return "user";
    }
}
