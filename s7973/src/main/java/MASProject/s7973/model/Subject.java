package MASProject.s7973.model;

import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Nazwa nie może być pusta!")
    private String name;
    @NotBlank(message = "Alias nie może być pusty!")
    private String alias;
    @Min(value=1, message="Wartość musi być większa lub równa 1!")
    @Max(value=8, message="Wartość musi być mniejsza lub równa 8!")
    private int semester;

    public Subject(int id, String name, String alias, int semester) {
        this.id = id;
        this.name = name;
        this.alias = alias;
        this.semester = semester;
    }

    public Subject() {

    }

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private List<Grade> grades = new ArrayList<Grade>();

    public List<Grade> getGrades() {
        return grades;
    }
    public void setGrade(List<Grade> grade) {
        this.grades = grade;
    }


    @ManyToOne(cascade = CascadeType.PERSIST)
    private Teacher teacher;

    public Teacher getTeacher() {
        return teacher;
    }
    public Teacher setTeacher(Teacher teacher) {
        return this.teacher = teacher;
    }


@ManyToMany(mappedBy = "subjects")
private Set<Groups> groups = new HashSet<>();

    public Set<Groups> getGroups() {
        return groups;
    }

    public void setGroups(Set<Groups> groups) {
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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }
}
