package MASProject.s7973.services;

import MASProject.s7973.model.Groups;
import MASProject.s7973.repository.GroupsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupsService {

    @Autowired
    private GroupsRepository repository;

    public void saveGroup(Groups group) {
        repository.createGroup(group);
    }

    public List<Groups> findAll() {

        return repository.getAllGroups();
    }

    public Groups getGroup(int id) {
        return repository.getGroup(id);
    }

    public void removeGroup(int id) {repository.removeGroup(id);}

    public void updateGroup(Groups group) {
        repository.updateGroup(group);
    }
}
