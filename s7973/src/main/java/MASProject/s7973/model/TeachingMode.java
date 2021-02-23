package MASProject.s7973.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TeachingMode")
public class TeachingMode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Nazwa nie może być pusta!")
    private String mode;

    public TeachingMode(int id, String mode) {
        this.id = id;
        this.mode = mode;
    }

    public TeachingMode() {

    }

    @OneToMany(mappedBy = "teachingMode", cascade = CascadeType.ALL)
    private List<Groups> groups = new ArrayList<Groups>();;
    public List<Groups> getGroups() { return groups; }

    public void setGroups(List<Groups> groups) { this.groups = groups; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
