package web.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import web.model.Role;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
@Transactional
public class RoleDao {
    @PersistenceContext
    private EntityManager em;

    public void addRole(Role role) {
        em.persist(role);
    }

    public void updateRole(Role role) {
        em.merge(role);
    }

    public void removeRoleById(long id) {
        em.remove(em.find(Role.class, id));
    }

    public List<Role> getAllRoles() {
        return em.createQuery("select r from Role r").getResultList();
    }

    public Role getRoleByName(String nameRole) {
        TypedQuery<Role> queryRole = em.createQuery("select r from Role r where r.nameRole=:role",
                Role.class).setParameter("role", nameRole);
        return queryRole.getSingleResult();
    }

}
