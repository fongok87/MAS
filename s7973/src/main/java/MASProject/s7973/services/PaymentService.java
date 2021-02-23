package MASProject.s7973.services;

import MASProject.s7973.model.Payment;
import MASProject.s7973.model.PaymentHistory;
import MASProject.s7973.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {


    @Autowired
    private PaymentRepository repository;

    public void savePayment(Payment payment) {
        repository.createPayment(payment);
    }

    public List<Payment> findAll() {

        return repository.getAllPayments();
    }

    public List<Payment> getActive() {
        return repository.getActivePayments();
    }

    public Payment getPayment(int id) { return repository.getPayment(id); }

    public void removePayment(int id) {repository.removePayment(id);}

    public void updatePayment(Payment payment) {
        repository.updatePayment(payment);
    }
}

