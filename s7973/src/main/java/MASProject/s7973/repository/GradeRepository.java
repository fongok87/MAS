package MASProject.s7973.repository;

import MASProject.s7973.model.Grade;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class GradeRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void createGrade(Grade grade) {
        em.persist(grade);
    }

    public List<Grade> getAllGrade() {
        return em.createQuery("from Grade", Grade.class).getResultList();
    }

    public Grade getGrade(int id) {
        return em.find(Grade.class, id);
    }

    @Transactional
    public void removeGrade(int id) {
        Query query = em.createQuery("delete from Grade where id = '" + id + "'");
        query.executeUpdate();
    }

}
