package MASProject.s7973.services;

import MASProject.s7973.model.Grade;
import MASProject.s7973.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {

    @Autowired
    private GradeRepository repository;

    public void saveGrade(Grade grade) {
        repository.createGrade(grade);
    }

    public List<Grade> findAll() {

        return repository.getAllGrade();
    }

    public Grade getGrade(int id) {
        return repository.getGrade(id);
    }

    public void removeGrade(int id) {repository.removeGrade(id);}

}
