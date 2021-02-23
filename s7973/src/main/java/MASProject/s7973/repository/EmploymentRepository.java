package MASProject.s7973.repository;

import MASProject.s7973.model.Employment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class EmploymentRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void createEmployment(Employment employment) { em.persist(employment); }

    public List<Employment> getAllEmployments() {
        return em.createQuery("from Employment", Employment.class).getResultList();
    }

    public Employment getEmployment(int id) {
        return em.find(Employment.class, id);
    }

    @Transactional
    public void removeEmployment(int id) {
        Query query = em.createQuery("delete from Employment where id = '" + id + "'");
        query.executeUpdate();
    }

    @Transactional
    public void updateEmployment(Employment employment) { em.merge(employment); }

}

