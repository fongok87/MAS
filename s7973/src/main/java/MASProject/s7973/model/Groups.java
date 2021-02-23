package MASProject.s7973.model;



import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.text.DateFormat;
import java.util.*;

@Entity
@Table(name = "StudentGroups")
public class Groups {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Kod nie może być pusty!")
    private String code;
    @Min(value=2020, message="Wartość musi być większa lub równa 2020!")
    @Max(value=2021, message="Wartość musi być mniejsza lub równa 2021!")
    private int year;

    private static int maxStudentsInGroup = 20;
    private static int minStudentsInGroup = 1;
    private static int minSubjectsAssigned = 1;
    private static int maxSubjectsAssigned = 5;

    public Groups(int id, String code, int year) {
        this.id = id;
        this.code = code;
        this.year = year;
    }

    public Groups() {

    }

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private Set<Student> students;

    public Set<Student> getStudents() { return students; }

    public void setStudents(Set<Student> student) {
        this.students = student;
        for (Student s : students) {
            s.removeGroup();
        }
        if(students.size() < minStudentsInGroup) {
            throw new ArrayStoreException("W grupie musi być minimum " + minStudentsInGroup + " student");
        }
        else if (students.size() > maxStudentsInGroup) {
            throw new ArrayStoreException("W grupie może być maksymalnie " + maxStudentsInGroup + " studentów");
        } else {
            for (Student s : students) {
                s.setGroup(this);
            }
        }
    }
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name="subject_group",
            joinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id", referencedColumnName = "id")
    )
    private Set<Subject> subjects = new HashSet<>();

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subject) {
        if (subject.size() < minSubjectsAssigned)
        {
            throw new ArrayStoreException("Nie przypisałeś żadnego przedmiotu!");
        }
        else if (subject.size() > maxSubjectsAssigned)
        {
            throw new ArrayStoreException("Przypisałeś za dużo przedmiotów!");
        }
        else {
            this.subjects = subject;
        }
    }

    public void removeSubject(Subject subject) {
        this.subjects.remove(subject);
        subject.getGroups().remove(this);
    }


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "mode_id")
    private TeachingMode teachingMode;

    public TeachingMode getMode() { return teachingMode; }
    public TeachingMode setMode(TeachingMode teachingMode) { return this.teachingMode = teachingMode; }


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "spec_id")
    private Specialization specialization;

    public Specialization getSpecialization() { return specialization; }
    public Specialization setSpecialization(Specialization specialization) { return this.specialization = specialization; }


    public String getCode() {
        return code;
    }


    public void setCode(String code) {
        this.code = code;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
