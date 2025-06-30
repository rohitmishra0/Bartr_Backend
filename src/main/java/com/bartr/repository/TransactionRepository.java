package com.bartr.repository;

import com.bartr.model.Course;
import com.bartr.model.Transaction;
import com.bartr.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> findByUser(User user);

    List<Transaction> findByCourse(Course course);

    void deleteByCourse(Course courseId);
    void deleteByUser(User user);
}
