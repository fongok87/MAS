package MASProject.s7973.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Person")
public class Student extends Person {
    @Column(name="alias", unique = true)
    private String alias;

    public Student() { super(); }

    public Student(int id, String name,  String surname) {
        super(id, name, surname);
    }

    public Student(int id, String name, String surname, String eMail, String telephone, String alias) {
        super(id, name, surname, eMail, telephone);
        this.alias = alias;
    }

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Grade> grades = new ArrayList<Grade>();

    public List<Grade> getGrades() {
        return grades;
    }
    public void setGrade(List<Grade> grade) {
        this.grades = grade;
    }


    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<PaymentHistory> payments;

    public List<PaymentHistory> getPayments() {
        return payments;
    }
    public void setPayment(List<PaymentHistory> payment) {
        this.payments = payment;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id")
    private Groups group;

    public Groups getGroup() {return group; }

    public void setGroup(Groups group) {
        this.group = group;
        }

    public void removeGroup() {
        this.group = null;
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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
