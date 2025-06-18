package com.bartr.service;

import java.util.List;

import com.bartr.model.Transaction;

public interface TransactionService {
    Transaction createTransaction(Transaction transaction);
    Transaction getTransactionById(int id);
    List<Transaction> getAllTransactions();
    List<Transaction> getTransactionsByUser(int userId);
    List<Transaction> getTransactionsByCourse(int courseId);
    void deleteTransaction(int id);
}
