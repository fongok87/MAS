package MASProject.s7973.repository;

import MASProject.s7973.model.Student;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class StudentRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void createStudent(Student student) { em.persist(student); }

    public List<Student> getAllStudents() {
        return em.createQuery("from Student where alias != null", Student.class).getResultList();
    }

    public Student getStudent(int id) {
        return em.find(Student.class, id);
    }

    @Transactional
    public void removeStudent(int id) {
        Query query = em.createQuery("delete from Student where id = '" + id + "'");
        query.executeUpdate();
    }

    @Transactional
    public void updateStudent(Student student) { em.merge(student); }

    @Transactional
    public void deleteGroup(int id) {
        Query query = em.createQuery("update Student set group_id = null where id = '" + id + "'");
        query.executeUpdate();
    }
}
