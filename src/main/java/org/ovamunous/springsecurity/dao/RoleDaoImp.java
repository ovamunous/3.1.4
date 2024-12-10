package org.ovamunous.springsecurity.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.ovamunous.springsecurity.model.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDaoImp implements RoleDao {

    private final EntityManager em;


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
    public Role getRole(String role) {
        try {
            return (Role) em.createQuery("FROM Role WHERE role = :role")
                    .setParameter("role", "ROLE_"+role).getSingleResult();
        } catch (NoResultException e) {
            return new Role("ROLE_"+role);
        }
    }
    @Override
    public Role getRoleById(int id) {
        return em.find(Role.class, id);
    }
    @Override
    public void deleteRoleById(int id) {
        Role role = getRoleById(id);
        em.createNativeQuery("DELETE FROM user_roles WHERE roles_id="+role.getId()).executeUpdate();
        em.remove(getRoleById(id));
    }
}
