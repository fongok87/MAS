package MASProject.s7973.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Grade")
public class Grade  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int grade_id;

    @Min(value=2, message = "Ocena musi być większa lub równa 2!")
    @Max(value=5, message = "Ocena musi być mniejsz lub równa 5!")
    private int grade;

    @NotNull(message = "Data nie może być pusta!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    public Grade(int id, int grade, LocalDate date) {
        this.grade_id = id;
        this.grade = grade;
        this.date = date;
    }

    public Grade() {

    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    public Subject getSubject() {
        return subject;
    }
    public Subject setSubject(Subject subject) {
        return this.subject = subject;
    }


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student student;

    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }

    public int getId() {
        return grade_id;
    }

    public void setId(int grade_id) {
        this.grade_id = grade_id;
    }


    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
