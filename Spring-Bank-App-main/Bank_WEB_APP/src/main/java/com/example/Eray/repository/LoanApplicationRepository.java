package com.example.Eray.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Eray.model.LoanApplication;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Long> {

    List<LoanApplication> findByUserId(Long userId);

    List<LoanApplication> findByStatus(String status);

}
