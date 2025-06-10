package com.ctrlaltdefeat.Bartr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ctrlaltdefeat.Bartr.services.PaymentService;
import com.ctrlaltdefeat.Bartr.models.Payment;

import io.appwrite.exceptions.AppwriteException;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    @Autowired
    private final PaymentService paymentService;

    @PostMapping
    public Payment createPayment(@RequestBody Payment payment) throws AppwriteException{
        return paymentService.createPayment(payment);
    }

    @GetMapping("/id")
    public Payment getPaymentById(@PathVariable String id) throws AppwriteException{
        return paymentService.getPaymentById(id);
    }

    @GetMapping
    public List<Payment> getAllPayments() throws AppwriteException{
        return paymentService.getAllPayments();
    }

    @DeleteMapping("/{id}")
    public void deletePayment(@PathVariable String id) throws AppwriteException{
        paymentService.deletePayment(id);
    }

}
