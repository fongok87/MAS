package MASProject.s7973.services;

import MASProject.s7973.model.Subject;
import MASProject.s7973.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository repository;

    public void saveSubject(Subject subject) {
        repository.createSubject(subject);
    }

    public List<Subject> findAll() {

        return repository.getAllSubjects();
    }

    public Subject getSubject(int id) {
        return repository.getSubject(id);
    }

    public void removeSubject(int id) {repository.removeSubject(id);}

    public void updateSubject(Subject subject) {
        repository.updateSubject(subject);
    }
}
