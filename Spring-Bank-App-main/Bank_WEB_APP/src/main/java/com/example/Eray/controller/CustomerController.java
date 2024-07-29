package com.example.Eray.controller;

import com.example.Eray.model.User;
import com.example.Eray.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private UserService userService;

    @GetMapping("/customer/dashboard/{username}")
    public String customerDashboard(@PathVariable("username") String username, Model model) {
        UserDetails userDetails = userService.loadUserByUsername(username);
        model.addAttribute("username", userDetails.getUsername());

        model.addAttribute("balance", userService.findByUsername(username).getBalance());

        return "customer/dashboard";
    }

    @PostMapping("/customer/addmoney/{username}")
    public String addMoney(@PathVariable("username") String username, @RequestParam("amount") double amount) {
        userService.addBalance(username, amount);
        return "redirect:/customer/dashboard/" + username;
    }

    @PostMapping("/customer/rmmoney/{username}")
    public String removeMoney(@PathVariable("username") String username, @RequestParam("amount") double amount) {
        userService.removeBalance(username, amount);
        return "redirect:/customer/dashboard/" + username;
    }


    @GetMapping("/customer/list")
    public String bankerList(Model model) {
        List<User> bankerList = userService.getAllBankers();

        model.addAttribute("userList", bankerList);

        return "customer/list";
    }

    @PostMapping("/customer/delete")
    public String deleteUser(@RequestParam("userId") Long userId) {
        userService.deleteUser(userId);
        return "redirect:/customer/list";
    }

    @GetMapping("/customer/edit/{userId}")
    public String editUserForm(@PathVariable("userId") Long userId, Model model) {
        User user = userService.getUserById(userId);
        model.addAttribute("user", user);
        return "customer/edit";
    }

    @PostMapping("/customer/edit/{userId}")
    public String editUser(@PathVariable("userId") Long userId, @ModelAttribute User updatedUser) {
        userService.updateUser(userId, updatedUser);
        return "redirect:/customer/list";
    }


    //buradan sonrası customer rollerı ıcın
    @GetMapping("/customer/customerlist")
    public String customerList(Model model) {
        List<User> customerList = userService.getUsersByRole("customer");
        model.addAttribute("customerList", customerList);
        return "customer/customerlist";
    }
    @GetMapping("/customer/edit2/{userId}")
    public String editCustomerForm(@PathVariable("userId") Long userId, Model model) {
        User customer = userService.getUserById(userId);
        model.addAttribute("customer", customer);
        return "customer/editcustomer";
    }

    @PostMapping("/customer/edit2/{userId}")
    public String editCustomer(@PathVariable("userId") Long userId, @ModelAttribute User updatedCustomer) {
        userService.updateUser(userId, updatedCustomer);
        return "redirect:/customer/customerlist";
    }

    @PostMapping("/customer/delete2")
    public String deleteCustomer(@RequestParam("userId") Long userId) {
        userService.deleteUser(userId);
        return "redirect:/customer/customerlist";
    }

    @GetMapping("/customer/deposit2/{userId}")
    public String depositForm(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("userId", userId);
        return "customer/depositform";
    }

    @PostMapping("/customer/deposit2/{userId}")
    public String deposit(@PathVariable("userId") Long userId, @RequestParam("depositAmount") double depositAmount) {
        userService.deposit(userId, depositAmount);
        return "redirect:/customer/customerlist";
    }

    @GetMapping("/customer/withdraw2/{userId}")
    public String withdrawForm(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("userId", userId);
        return "customer/withdrawform";
    }

    @PostMapping("/customer/withdraw2/{userId}")
    public String withdraw(@PathVariable("userId") Long userId, @RequestParam("withdrawAmount") double withdrawAmount) {
        userService.withdraw(userId, withdrawAmount);
        return "redirect:/customer/customerlist";
    }

}
