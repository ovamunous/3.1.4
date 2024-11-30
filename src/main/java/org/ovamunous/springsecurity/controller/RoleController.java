package org.ovamunous.springsecurity.controller;

import jakarta.persistence.EntityNotFoundException;
import org.ovamunous.springsecurity.model.Role;
import org.ovamunous.springsecurity.model.User;
import org.ovamunous.springsecurity.service.RoleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RoleController {

    private RoleService roleService;

    RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/admin/roles")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String allUsers(ModelMap model) {
        List<Role> roles = roleService.getRoles();
        model.addAttribute("newRole", new Role());
        return "roles";
    }

    @PostMapping("/roleAction")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String userAction(@ModelAttribute("newRole") Role newRole,
                             @RequestParam("action") String action, @RequestParam("id") String id,
                             ModelMap model) {
        newRole = null;
        return "redirect:/";
    }
}
