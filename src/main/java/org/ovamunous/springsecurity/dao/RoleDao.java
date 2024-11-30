package org.ovamunous.springsecurity.dao;

import org.ovamunous.springsecurity.model.Role;
import org.ovamunous.springsecurity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleDao {
    void addRole(Role role);
    void updateRole(Role role);
    void deleteRole(Role role);
    List<Role> getRoles();
    Role getRole(String roleName);
}
