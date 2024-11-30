package org.ovamunous.springsecurity.dao;

import jakarta.persistence.EntityManager;
import org.ovamunous.springsecurity.model.Role;
import org.ovamunous.springsecurity.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDaoImp implements RoleDao {

    EntityManager em;

    public RoleDaoImp(EntityManager em) {
        this.em = em;
    }

    @Override
    public void addRole(Role role) {
        em.persist(role);
    }

    @Override
    public void updateRole(Role role) {
        em.merge(role);
    }

    @Override
    public void deleteRole(Role role) {
        em.remove(role);
    }

    @Override
    public List<Role> getRoles() {
        return  em.createQuery("select r from Role r", Role.class).getResultList();
    }
    @Override
    public Role getRole(String roleName) {
        return em.find(Role.class, roleName);
    }
}
