package MASProject.s7973.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private double currentTuition;
    private double additionalFees;
    private LocalDate deadline;

    private boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Payment(int id, double currentTuition, LocalDate deadline, boolean active) {
        this.id = id;
        this.currentTuition = currentTuition;
        this.deadline = deadline;
        this.active = active;
    }

    public Payment(int id, double currentTuition, double additionalFees, LocalDate deadline, boolean active) {
        this.id = id;
        this.currentTuition = currentTuition;
        this.additionalFees = additionalFees;
        this.deadline = deadline;
        this.active = active;
    }

    public Payment() {

    }

    public List<PaymentHistory> getPayments() {
        return payments;
    }

    public void setPayment(List<PaymentHistory> payment) {
        this.payments = payment;
    }

    @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL)
    private List<PaymentHistory> payments;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCurrentTuition() {
        return currentTuition;
    }

    public void setCurrentTuition(double currentTuition) {
        this.currentTuition = currentTuition;
    }

    public double getAdditionalFees() {
        return additionalFees;
    }

    public void setAdditionalFees(double additionalFees) {
        this.additionalFees = additionalFees;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
}
