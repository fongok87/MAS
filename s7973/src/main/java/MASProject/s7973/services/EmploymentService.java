package MASProject.s7973.services;

import MASProject.s7973.model.Employment;
import MASProject.s7973.repository.EmploymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmploymentService {

    @Autowired
    private EmploymentRepository repository;

    public void saveEmployment(Employment employment) {
        repository.createEmployment(employment);
    }

    public List<Employment> findAll() {

        return repository.getAllEmployments();
    }

    public Employment getEmployment(int id) { return repository.getEmployment(id); }

    public void removeEmployment(int id) {repository.removeEmployment(id);}

    public void updateEmployment(Employment employment) {
        repository.updateEmployment(employment);
    }
}

