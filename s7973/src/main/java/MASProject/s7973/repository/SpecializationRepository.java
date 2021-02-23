package MASProject.s7973.repository;

import MASProject.s7973.model.Specialization;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class SpecializationRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void createSpec(Specialization spec) { em.persist(spec); }

    public List<Specialization> getAllSpecs() {
        return em.createQuery("from Specialization", Specialization.class).getResultList();
    }

    public Specialization getSpec(int id) {
        return em.find(Specialization.class, id);
    }

    @Transactional
    public void removeSpec(int id) {
        Query query = em.createQuery("delete from Specialization where id = '" + id + "'");
        query.executeUpdate();
    }

    @Transactional
    public void updateSpec(Specialization spec) { em.merge(spec); }

}
