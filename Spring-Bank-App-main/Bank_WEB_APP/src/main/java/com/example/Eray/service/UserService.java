package com.example.Eray.service;

import com.example.Eray.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService {
    public User getUserByUsername(String username);
    void deposit(Long userId, double amount);

    void withdraw(Long userId, double amount);
    List<User> getAllBankers();
    List<User> getAllUsers();

    void registerUser(User user, String role);

    User findByUsername(String username);

    List<User> getUsersByRole(String role);

    User getUserById(Long id);

    void updateUser(Long id, User updatedUser);

    void deleteUser(Long id);
    public void removeBalance(String username, double amount);
    public void addBalance(String username, double amount);
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
