package MASProject.s7973.services;

import MASProject.s7973.model.Teacher;
import MASProject.s7973.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository repository;

    public void saveTeacher(Teacher teacher) {
        repository.createTeacher(teacher);
    }

    public List<Teacher> findAll() {

        return repository.getAllTeachers();
    }

    public Teacher getTeacher(int id) {
        return repository.getTeacher(id);
    }

    public void removeTeacher(int id) {repository.removeTeacher(id);}

    public void updateTeacher(Teacher teacher) {
        repository.updateTeacher(teacher);
    }
}

