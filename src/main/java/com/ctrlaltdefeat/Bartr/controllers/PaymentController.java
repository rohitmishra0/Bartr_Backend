package com.ctrlaltdefeat.Bartr.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctrlaltdefeat.Bartr.services.PaymentService;
import com.ctrlaltdefeat.Bartr.models.Payment;


@RestController
@RequestMapping("/api/payments")
public class PaymentController {
   
    private final PaymentService paymentService;


   public PaymentController(PaymentService paymentService) {
       this.paymentService = paymentService;
   }
    @PostMapping
    public Payment createPayment(@RequestBody Payment payment) {
        return paymentService.createPayment(payment);
    }

    @GetMapping("/{id}")
    public Payment getPaymentById(@PathVariable String id) {
        return paymentService.getPaymentById(id);
    }

    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @DeleteMapping("/{id}")
    public void deletePayment(@PathVariable String id){
        paymentService.deletePayment(id);
    }

}
