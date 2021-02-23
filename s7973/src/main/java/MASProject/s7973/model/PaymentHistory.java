package MASProject.s7973.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "PaymentHistory")
public class PaymentHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private LocalDate dateOfPayment;


    public PaymentHistory(int id, LocalDate dateOfPayment) {
        this.id = id;
        this.dateOfPayment = dateOfPayment;
    }

    public PaymentHistory() {

    }

    @ManyToOne(cascade = CascadeType.ALL)
    private Student student;

    public Student getStudent() {
        return student;
    }
    public Student setStudent(Student student) {
        return this.student = student;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    private Payment payment;

    public Payment getPayment() {
        return payment;
    }
    public Payment setPayment(Payment payment) {
        return this.payment = payment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateOfPayment() {
        return dateOfPayment;
    }

    public void setDateOfPayment(LocalDate dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
    }
}
