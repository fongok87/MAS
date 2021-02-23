package MASProject.s7973.repository;

import MASProject.s7973.model.Subject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Set;

@Repository
public class SubjectRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void createSubject(Subject subject) { em.persist(subject); }

    public List<Subject> getAllSubjects() {
        return em.createQuery("from Subject", Subject.class).getResultList();
    }


    public Subject getSubject(int id) {
        return em.find(Subject.class, id);
    }

    @Transactional
    public void removeSubject(int id) {
        Query query = em.createQuery("delete from Subject where id = '" + id + "'");
        query.executeUpdate();
    }

    @Transactional
    public void updateSubject(Subject subject) { em.merge(subject); }
}
