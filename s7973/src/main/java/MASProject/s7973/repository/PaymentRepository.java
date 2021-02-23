package MASProject.s7973.repository;

import MASProject.s7973.model.Payment;
import MASProject.s7973.model.PaymentHistory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class PaymentRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void createPayment(Payment payment) { em.persist(payment); }

    public List<Payment> getAllPayments() {
        return em.createQuery("from Payment", Payment.class).getResultList();
    }

    public List<Payment> getActivePayments() {
        return em.createQuery("from Payment where active = 1", Payment.class).getResultList();
    }

    public Payment getPayment(int id) { return em.find(Payment.class, id); }

    @Transactional
    public void removePayment(int id) {
        Query query = em.createQuery("delete from Payment where id = '" + id + "'");
        query.executeUpdate();
    }

    @Transactional
    public void updatePayment(Payment payment) { em.merge(payment); }

}



