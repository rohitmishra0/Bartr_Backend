package com.bartr.service;

import com.bartr.model.Payment;

import java.util.List;

public interface PaymentService
{
    int calculatePrice(int xp);
    Payment createPayment(int userId, int xpToBuy);
    List<Payment> getPaymentsByUserId(int userId);
}
