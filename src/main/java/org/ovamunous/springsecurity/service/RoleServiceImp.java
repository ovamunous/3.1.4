package org.ovamunous.springsecurity.service;

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

    @Override
    public void addRole(Role role) {
        roleDao.addRole(role);
    }

    @Override
    public void updateRole(Role role) {
        roleDao.updateRole(role);
    }

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


}
