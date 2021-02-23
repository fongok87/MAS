package MASProject.s7973.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Specialization")
public class Specialization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Nazwa nie może być pusta!")
    private String name;
    @NotBlank(message = "Kod nie może być pusty!")
    private String code;

    public Specialization(int id, String name,  String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public Specialization() {

    }

    @OneToMany(mappedBy = "specialization", cascade = CascadeType.ALL)
    private List<Groups> groups = new ArrayList<Groups>();
    public List<Groups> getGroups() {
        return groups;
    }

    public void setGroups(List<Groups> groups) {
        this.groups = groups;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
