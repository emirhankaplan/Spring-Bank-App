package com.example.Eray.controller;
import com.example.Eray.model.User;
import com.example.Eray.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Controller
@RequestMapping("/bankers")
public class BankersController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/create")
    public String registerPage() {
        return "bankers/create";
    }

    @PostMapping("/create")
    public String registerUser(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String role,
                               Model model) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);

        userService.registerUser(user, role);

        return "redirect:/auth/login";
    }


    @GetMapping("/dashboard/{username}")
    public String adminDashboard(@PathVariable("username") String username, Model model) {

        model.addAttribute("username", username);

        return "bankers/dashboard";
    }
}
