package org.ovamunous.springsecurity.controller;


import org.ovamunous.springsecurity.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final SecurityService controllerService;

    @Autowired
    public AdminController(SecurityService controllerService) {
        this.controllerService = controllerService;
    }

    @GetMapping()
    public String allUsers(ModelMap model) {
        String currentUser = controllerService.getAuthenticationName();
        String currentRoles = controllerService.getAuthenticationRoles();
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("currentRole", currentRoles);
        return "index";
    }
}