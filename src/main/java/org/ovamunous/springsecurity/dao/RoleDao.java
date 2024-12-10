package org.ovamunous.springsecurity.dao;

import org.ovamunous.springsecurity.model.Role;

import java.util.List;

public interface RoleDao {
    void addRole(Role role);
    void updateRole(Role role);
    void deleteRole(Role role);
    List<Role> getRoles();
    Role getRole(String roleName);
    Role getRoleById(int id);
    void deleteRoleById(int id);
}
