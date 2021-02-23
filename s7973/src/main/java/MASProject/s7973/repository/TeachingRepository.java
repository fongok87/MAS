package MASProject.s7973.repository;

import MASProject.s7973.model.TeachingMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class TeachingRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void createMode(TeachingMode mode) { em.persist(mode); }

    public List<TeachingMode> getAllModes() {
        return em.createQuery("from TeachingMode", TeachingMode.class).getResultList();
    }

    public TeachingMode getMode(int id) {
        return em.find(TeachingMode.class, id);
    }

    @Transactional
    public void removeMode(int id) {
        Query query = em.createQuery("delete from TeachingMode where id = '" + id + "'");
        query.executeUpdate();
    }

    @Transactional
    public void updateMode(TeachingMode mode) { em.merge(mode); }

}

