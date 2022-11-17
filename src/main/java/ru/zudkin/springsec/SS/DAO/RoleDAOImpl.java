package ru.zudkin.springsec.SS.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.zudkin.springsec.SS.model.Role;
import ru.zudkin.springsec.SS.model.User;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class RoleDAOImpl implements RoleDAO{

    private final EntityManager entityManager;

    @Autowired
    public RoleDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Role> getRoles() {
        return entityManager.createQuery("from Role", Role.class).getResultList();
    }
    @Transactional
    @Override
    public void save(Role role) {
        entityManager.persist(role);
    }
    @Transactional
    @Override
    public Role getRoleByName(String role) {
        System.out.println(role);
        if (role.equals("ADMIN")) {
            role = "ROLE_ADMIN";
        } else if (role.equals("USER")) {
            role = "ROLE_USER";
        }
        return entityManager.createQuery("FROM Role r WHERE r.name=:role", Role.class)
                .setParameter("role", role).getSingleResult();
    }
}
