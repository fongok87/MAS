package MASProject.s7973.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@MappedSuperclass
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @NotBlank(message = "Imię nie może być puste!")
    @Column(name="name")
    private String name;
    @NotBlank(message = "Nazwisko nie może być puste!")
    @Column(name="surname")
    private String surname;
    @Column(name="email")
    private String eMail;
    @Column(name="telephone")
    private String telephone;

    public Person() {

    }

    public Person(int id,  String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public Person(int id,  String name,  String surname, String eMail, String telephone) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.eMail = eMail;
        this.telephone = telephone;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return eMail;
    }

    public void setEmail(String Email) {
        this.eMail = Email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
