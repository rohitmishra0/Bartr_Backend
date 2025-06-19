package com.bartr.service.impl;

import com.bartr.model.Payment;
import com.bartr.model.User;
import com.bartr.repository.PaymentRepository;
import com.bartr.repository.UserRepository;
import com.bartr.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public int calculatePrice(int xp) {
        return xp * 5; // ₹5 per XP
    }

    @Override
    public Payment createPayment(int userId, int xpToBuy) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        int amount = calculatePrice(xpToBuy);

        Payment payment = new Payment();
        payment.setUserId(userId);
        payment.setXpPurchased(xpToBuy);
        payment.setAmount(amount);
        payment.setMode("upi");


        paymentRepository.save(payment);

        user.setXp(user.getXp() + xpToBuy);
        userRepository.save(user);

        return payment;
    }

    @Override
    public List<Payment> getPaymentsByUserId(int userId) {
        return paymentRepository.findByUserId(userId);
    }
}
