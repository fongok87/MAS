package MASProject.s7973.services;

import MASProject.s7973.model.TeachingMode;
import MASProject.s7973.repository.TeachingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeachingService {

    @Autowired
    private TeachingRepository repository;

    public void saveMode(TeachingMode mode) {
        repository.createMode(mode);
    }

    public List<TeachingMode> findAll() {

        return repository.getAllModes();
    }

    public TeachingMode getMode(int id) {
        return repository.getMode(id);
    }

    public void removeMode(int id) {repository.removeMode(id);}

    public void updateMode(TeachingMode mode) {
        repository.updateMode(mode);
    }
}

