package org.ovamunous.springsecurity.service;

import org.ovamunous.springsecurity.model.Role;
import org.ovamunous.springsecurity.model.User;

import java.util.List;
import java.util.Set;

public interface RoleService {
    void addRole(Role role);
    void updateRole(Role role);
    void deleteRole(Role role);
    List<Role> getRoles();
    Role getRole(String roleName);
    Role getRoleById(int id);
    void deleteRoleById(int id);
    Set<Role> getRolesByString(String string);
}
