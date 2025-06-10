package com.ctrlaltdefeat.Bartr.services;
import com.ctrlaltdefeat.Bartr.models.Transaction;
import com.ctrlaltdefeat.Bartr.repository.TransactionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;
import java.util.Map;
@Service
public class TransactionService {
   private final TransactionRepository transactionRepository;
      private final ObjectMapper objectMapper =new ObjectMapper();

   public TransactionService(TransactionRepository transactionRepository) {
       this.transactionRepository = transactionRepository;
   }
   public String createTransaction(Transaction transaction) {
    Map<String,Object> data = objectMapper.convertValue(transaction, Map.class);
       return transactionRepository.createDocument(data);
   }
   public String getTransactionById(String id) {
       return transactionRepository.getDocument(id);
   }
   public String getAllTransactions() {
       return transactionRepository.listDocuments();
   }
   public String deleteTransaction(String id) {
       return transactionRepository.deleteDocument(id);
   }
}