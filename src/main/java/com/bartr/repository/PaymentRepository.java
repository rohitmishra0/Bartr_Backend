package com.bartr.repository;

import com.bartr.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    // Add custom query methods if needed
    List<Payment> findByUserId(int userId);

    void deleteByUserId(int userId);
}
