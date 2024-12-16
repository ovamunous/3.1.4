package org.ovamunous.springsecurity.service;

import jakarta.transaction.Transactional;
import org.ovamunous.springsecurity.dao.RoleDao;
import org.ovamunous.springsecurity.model.Role;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImp implements RoleService {

    private final RoleDao roleDao;

    public RoleServiceImp(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Transactional
    @Override
    public void addRole(Role role) {
        roleDao.addRole(role);
    }

    @Transactional
    @Override
    public void updateRole(Role role) {
        roleDao.updateRole(role);
    }

    @Transactional
    @Override
    public void deleteRole(Role role) {
        roleDao.deleteRole(role);
    }

    @Override
    public List<Role> getRoles() {
        return roleDao.getRoles();
    }

    @Override
    public Role getRole(String roleName) {
        return roleDao.getRole(roleName);
    }

    @Override
    public Role getRoleById(int id) {
        return roleDao.getRoleById(id);
    }

    @Transactional
    @Override
    public void deleteRoleById(int id) {
        roleDao.deleteRoleById(id);
    }

    @Override
    public Set<Role> getRolesByString(String stringRoles) {
        return Arrays.stream(stringRoles.split(",")).map(String::trim).map(String::toUpperCase)
                .map(this::getRole).collect(Collectors.toSet());
    }

    @Override
    public Set<Role> checkRoles(Set<Role> roles) {
        return roles.stream().map(t -> this.getRole(t.getRole())).collect(Collectors.toSet());
    }

}
