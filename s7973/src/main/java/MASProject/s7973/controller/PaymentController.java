package MASProject.s7973.controller;

import MASProject.s7973.model.Payment;
import MASProject.s7973.model.PaymentHistory;
import MASProject.s7973.services.PaymentService;
import MASProject.s7973.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.List;

@Controller
public class PaymentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/admin/student/payment/details/{id}")
    public String getPayment(Model model, @PathVariable int id) {
        var student = studentService.getStudent(id);
        List<PaymentHistory> payment = student.getPayments();
        Collections.reverse(payment);
        PaymentHistory active = student.getPayments().get(student.getPayments().size() - 1);

        model.addAttribute("students", student);
        model.addAttribute("payments", payment);
        model.addAttribute("active", active);

        return "/admin/student/payment/details.html";
    }
}
