package MASProject.s7973.services;

import MASProject.s7973.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import MASProject.s7973.model.*;

import java.util.List;

@Service
public class StudentService  {

    @Autowired
    private StudentRepository repository;

    public void saveStudent(Student student) {
        repository.createStudent(student);
    }

    public List<Student> findAll() {

        return repository.getAllStudents();
    }

    public Student getStudent(int id) {
        return repository.getStudent(id);
    }

    public void removeStudent(int id) {repository.removeStudent(id);}

    public void updateStudent(Student student) {
        repository.updateStudent(student);
    }

    public void deleteGroup(int id) {repository.deleteGroup(id);}
}


