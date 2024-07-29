package com.example.Eray.controller;

import com.example.Eray.model.LoanApplication;
import com.example.Eray.model.User;
import com.example.Eray.service.LoanApplicationService;
import com.example.Eray.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/loanapplications")
public class LoanApplicationController {

    @Autowired
    private UserService userService;

    @Autowired
    private LoanApplicationService loanApplicationService;

    @GetMapping("/apply/{username}")
    public String loanApplicationForm(Model model, @PathVariable String username) {
        model.addAttribute("loanApplication", new LoanApplication());
        model.addAttribute("username", username);
        return "loanapplications/apply";
    }

    @PostMapping("/apply/{username}")
    public String applyLoan(@ModelAttribute LoanApplication loanApplication, @PathVariable String username) {
        User user = userService.findByUsername(username);
        if (user != null) {
            loanApplication.setUser(user);
            loanApplication.setStatus("Pending");
            loanApplicationService.saveLoanApplication(loanApplication);
            return "redirect:/loanapplications/list/{username}";
        } else {

            return "redirect:/loanapplications/pendinglist";
        }
    }

    @GetMapping("/list/{username}")
    public String listLoanApplications(Model model, @PathVariable String username) {
        User user = userService.getUserByUsername(username);
        List<LoanApplication> loanApplications = loanApplicationService.getAllLoanApplicationsByUser(user.getId());
        model.addAttribute("loanApplications", loanApplications);
        model.addAttribute("username", username);
        return "loanapplications/list";
    }

    @GetMapping("/pendinglist")
    public String listPendingLoanApplications(Model model) {
        List<LoanApplication> pendingLoanApplications = loanApplicationService.getAllPendingLoanApplications();
        model.addAttribute("pendingLoanApplications", pendingLoanApplications);
        return "loanapplications/pendinglist";
    }

    @PostMapping("/respond")
    public String respondToLoanApplication(@RequestParam Long loanApplicationId, @RequestParam String response) {
        LoanApplication loanApplication = loanApplicationService.getLoanApplicationById(loanApplicationId);

        if (loanApplication != null && "Pending".equals(loanApplication.getStatus())) {
            if ("approved".equals(response)) {
                loanApplication.setStatus("Approved");
            } else if ("rejected".equals(response)) {
                loanApplication.setStatus("Rejected");
            }
            loanApplicationService.saveLoanApplication(loanApplication);
        }

        return "redirect:/loanapplications/pendinglist";
    }

}


