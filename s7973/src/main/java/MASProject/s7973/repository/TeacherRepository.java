package MASProject.s7973.repository;

import MASProject.s7973.model.Student;
import MASProject.s7973.model.Teacher;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class TeacherRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void createTeacher(Teacher teacher) { em.persist(teacher); }

    public List<Teacher> getAllTeachers() {
        return em.createQuery("from Teacher where salary != null", Teacher.class).getResultList();
    }

    public Teacher getTeacher(int id) {
        return em.find(Teacher.class, id);
    }

    @Transactional
    public void removeTeacher(int id) {
        Query query = em.createQuery("delete from Teacher where id = '" + id + "'");
        query.executeUpdate();
    }

    @Transactional
    public void updateTeacher(Teacher teacher) { em.merge(teacher); }
}



