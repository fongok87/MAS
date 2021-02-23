package MASProject.s7973.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Person")
public class Teacher extends Person {
    @Column(name="salary")
    private double salary;

    private static double maxBonus = 10.0;
    private static double minBonus = -10.0;

    private static List<Teacher> extent = new ArrayList<>();


    private static int minSubjectsAssigned = 1;
    private static int maxSubjectsAssigned = 5;

    public Teacher() {
        super();
    }

    public Teacher(int id, String name, String surname) {
        super(id, name, surname);
    }

    public Teacher(int id, String name, String surname, String eMail, String telephone, double salary) {
        super(id, name, surname, eMail, telephone);
        this.salary = salary;
    }

    public void modifyBonus(double bonus) {
        if (bonus > this.maxBonus) {
            throw new ArithmeticException("Podwyżka nie może być większa niż " + maxBonus + " procent");
        }
        else if (bonus < this.minBonus) {
            throw new ArithmeticException("Podwyżka nie może być mniejsza niż " + minBonus + " procent");
        }
        else {
            salary = salary + (salary * bonus);
        }
    }

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private List<Employment> employments = new ArrayList<Employment>();

    public List<Employment> getEmployments() { return employments; }
    public void setEmployment(List<Employment> employment) { this.employments = employment; }

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.PERSIST)
    private List<Subject> subjects;

    public List<Subject> getSubject() { return subjects; }
    public void setSubject(List<Subject> subject) {
        if (subjects.size() < minSubjectsAssigned) {
            throw new ArrayStoreException("Nauczyciel musi być przypisany do conajmniej " + minSubjectsAssigned + " przedmiotu");
        } else if (subjects.size() > maxSubjectsAssigned) {
            throw new ArrayStoreException("Nauczyciel może być przypisany do maksymalnie " + maxSubjectsAssigned + " przedmiotów");
        } else {
            this.subjects = subject;
        }
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public String getSurname() {
        return super.getSurname();
    }

    @Override
    public void setSurname(String surname) {
        super.setSurname(surname);
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public void setEmail(String Email) {
        super.setEmail(Email);
    }

    @Override
    public String getTelephone() {
        return super.getTelephone();
    }

    @Override
    public void setTelephone(String telephone) {
        super.setTelephone(telephone);
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

}
