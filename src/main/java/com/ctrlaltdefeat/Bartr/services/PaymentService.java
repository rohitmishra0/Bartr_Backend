package com.ctrlaltdefeat.Bartr.services;
import com.ctrlaltdefeat.Bartr.models.Payment;
import com.ctrlaltdefeat.Bartr.repository.PaymentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;
import java.util.Map;
@Service
public class PaymentService {
   private final PaymentRepository paymentRepository;
      private final ObjectMapper objectMapper =new ObjectMapper();

   public PaymentService(PaymentRepository paymentRepository) {
       this.paymentRepository = paymentRepository;
   }
   public String createPayment(Payment payment) {
    Map<String,Object> data = objectMapper.convertValue(payment, Map.class);
       return paymentRepository.createDocument(data);
   }
   public String getPaymentById(String id) {
       return paymentRepository.getDocument(id);
   }
   public String getAllPayments() {
       return paymentRepository.listDocuments();
   }
   public String deletePayment(String id) {
       return paymentRepository.deleteDocument(id);
   }
}