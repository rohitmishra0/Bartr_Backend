package com.ctrlaltdefeat.Bartr.services;
import com.ctrlaltdefeat.Bartr.models.Payment;
import com.ctrlaltdefeat.Bartr.repository.PaymentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class PaymentService {
   private final PaymentRepository paymentRepository;
      private final ObjectMapper objectMapper =new ObjectMapper();

   public PaymentService(PaymentRepository paymentRepository) {
       this.paymentRepository = paymentRepository;
   }
   public Payment createPayment(Payment payment) {
    Map<String,Object> data = objectMapper.convertValue(payment, Map.class);
       return paymentRepository.createDocument(data);
   }
   public Payment getPaymentById(String id) {
       return paymentRepository.getDocument(id);
   }
   public List<Payment> getAllPayments() {
       return paymentRepository.listDocuments();
   }
   public void deletePayment(String id) {
        paymentRepository.deleteDocument(id);
   }
}