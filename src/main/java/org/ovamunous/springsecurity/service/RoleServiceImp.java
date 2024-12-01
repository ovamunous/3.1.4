package org.ovamunous.springsecurity.service;

import jakarta.transaction.Transactional;
import org.ovamunous.springsecurity.dao.RoleDao;
import org.ovamunous.springsecurity.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImp implements RoleService {

    private RoleDao roleDao;

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

}
