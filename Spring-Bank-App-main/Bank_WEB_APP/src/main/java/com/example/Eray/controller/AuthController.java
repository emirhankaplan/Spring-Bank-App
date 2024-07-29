package com.example.Eray.controller;

import com.example.Eray.model.User;
import com.example.Eray.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        RedirectAttributes redirectAttributes,
                        Model model) {
        User user = userService.findByUsername(username);

        // Kullanıcı adı doğrulaması
        if (user == null) {
            model.addAttribute("error", "Invalid username");
            return "auth/login";
        }

        // Şifre doğrulaması
        if (!passwordEncoder.matches(password, user.getPassword())) {
            // Şifre yanlışsa
            model.addAttribute("error", "Invalid password");
            return "auth/login";
        }

        // Giriş başarılı ise kullanıcının rolüne göre yönlendirme yapılır
        if ("admin".equals(user.getRole())) {
            return "redirect:/admin/dashboard/" + username;
        } else if ("banker".equals(user.getRole())) {
            return "redirect:/bankers/dashboard/" + username;
        } else if ("customer".equals(user.getRole())) {
            return "redirect:/customer/dashboard/" + username;
        }

        // Kullanıcı bulunamazsa veya rol belirtilmemişse, hata mesajı ile login sayfasına yönlendir
        model.addAttribute("error", "Invalid role");
        return "auth/login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "auth/register";
    }

    @PostMapping("/register")
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
}
