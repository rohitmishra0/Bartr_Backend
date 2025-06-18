package com.bartr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bartr.model.Course;
import com.bartr.model.Transaction;
import com.bartr.model.User;
import com.bartr.repository.CourseRepository;
import com.bartr.repository.TransactionRepository;
import com.bartr.repository.UserRepository;
import com.bartr.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Transaction createTransaction(Transaction transaction) {
        
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction getTransactionById(int id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> getTransactionsByUser(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return transactionRepository.findByUser(user);
    }

    @Override
    public List<Transaction> getTransactionsByCourse(int courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        return transactionRepository.findByCourse(course);
    }

    @Override
    public void deleteTransaction(int id) {
        transactionRepository.deleteById(id);
    }
}
