package MASProject.s7973.repository;

import MASProject.s7973.model.Groups;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class GroupsRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void createGroup(Groups group) { em.persist(group); }

    public List<Groups> getAllGroups() {
        return em.createQuery("from Groups", Groups.class).getResultList();
    }

    public Groups getGroup(int id) {
        return em.find(Groups.class, id);
    }

    @Transactional
    public void removeGroup(int id) {
        Query query = em.createQuery("delete from Groups where id = '" + id + "'");
        query.executeUpdate();
    }

    @Transactional
    public void updateGroup(Groups group) { em.merge(group); }

}

