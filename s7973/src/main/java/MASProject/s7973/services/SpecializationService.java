package MASProject.s7973.services;

import MASProject.s7973.model.Specialization;
import MASProject.s7973.repository.SpecializationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecializationService {

    @Autowired
    private SpecializationRepository repository;

    public void saveSpec(Specialization spec) {
        repository.createSpec(spec);
    }

    public List<Specialization> findAll() {

        return repository.getAllSpecs();
    }

    public Specialization getSpec(int id) {
        return repository.getSpec(id);
    }

    public void removeSpec(int id) {repository.removeSpec(id);}

    public void updateSpec(Specialization spec) {
        repository.updateSpec(spec);
    }
}
