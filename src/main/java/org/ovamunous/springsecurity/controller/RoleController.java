package org.ovamunous.springsecurity.controller;

import jakarta.persistence.EntityNotFoundException;
import org.ovamunous.springsecurity.model.Role;
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

    private final RoleService roleService;

    RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/admin/roles")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String allUsers(ModelMap model) {
        List<Role> roles = roleService.getRoles();
        model.addAttribute("roles", roles);
        model.addAttribute("newRole", new Role());
        return "roles";
    }

    @PostMapping("/admin/roles/roleAction")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String userAction(@ModelAttribute("newRole") Role newRole,
                             @RequestParam("action") String action,
                             @RequestParam(value = "id") String id,
                             ModelMap model) {
        switch (action) {
            case "add":
                roleService.addRole(newRole);
                break;
            case "update":
                try {
                    Role oldRole = roleService.getRoleById(Integer.parseInt(id));
                    oldRole.setRole(newRole.getRole());
                    roleService.updateRole(oldRole);
                } catch (Exception e) {
                    throw new EntityNotFoundException("Role not found");
                }
                break;
            case "delete":

                    roleService.deleteRoleById(Integer.parseInt(id));

                break;
        }
        return "redirect:/admin/roles";
    }
}
