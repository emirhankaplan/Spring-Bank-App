package com.example.Eray.service;

import com.example.Eray.model.LoanApplication;
import com.example.Eray.repository.LoanApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanApplicationService {

    @Autowired
    private LoanApplicationRepository loanApplicationRepository;

    public List<LoanApplication> getAllLoanApplications() {
        return loanApplicationRepository.findAll();
    }

    public LoanApplication getLoanApplicationById(Long id) {
        return loanApplicationRepository.findById(id).orElse(null);
    }

    public void saveLoanApplication(LoanApplication loanApplication) {
        loanApplicationRepository.save(loanApplication);
    }


    public List<LoanApplication> getAllLoanApplicationsByUser(Long userId) {
        return loanApplicationRepository.findByUserId(userId);
    }

    public List<LoanApplication> getAllPendingLoanApplications() {
        return loanApplicationRepository.findByStatus("Pending");
    }


}