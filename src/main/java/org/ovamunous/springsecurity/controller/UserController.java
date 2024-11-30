package org.ovamunous.springsecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String welcomeUser(ModelMap model) {
        return "user";
    }
}
