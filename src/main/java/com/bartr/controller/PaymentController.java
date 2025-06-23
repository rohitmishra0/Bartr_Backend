package com.bartr.controller;

import com.bartr.model.Payment;
import com.bartr.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // 🔹 Calculate price for XP purchase
    @GetMapping("/price")
    public ResponseEntity<Integer> calculatePrice(@RequestParam int xp) {
        int price = paymentService.calculatePrice(xp);
        return ResponseEntity.ok(price);
    }

    // 🔹 Buy XP (fake payment)
    @PostMapping("/buy-xp")
    public ResponseEntity<Payment> buyXp(
            @RequestParam int userId,
            @RequestParam int xpToBuy
    ) {
        Payment payment = paymentService.createPayment(userId, xpToBuy);
        return ResponseEntity.ok(payment);
    }

    // 🔹 User's XP purchase history
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Payment>> getUserPayments(@PathVariable int userId) {
        return ResponseEntity.ok(paymentService.getPaymentsByUserId(userId));
    }
}

