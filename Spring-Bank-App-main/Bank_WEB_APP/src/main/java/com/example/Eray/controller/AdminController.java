package com.example.Eray.controller;

import com.example.Eray.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/admin/dashboard/{username}")
    public String adminDashboard(@PathVariable("username") String username, Model model) {

        model.addAttribute("username", username);

        return "admin/dashboard";
    }


}
