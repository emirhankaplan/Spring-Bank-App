package com.example.Eray.service;

import com.example.Eray.model.User;
import com.example.Eray.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    @Override
    public void deposit(Long userId, double amount) {
        User user = userRepository.findById(userId).orElse(null);

        if (user != null) {
            double currentBalance = user.getBalance();
            user.setBalance(currentBalance + amount);
            userRepository.save(user);
        }
    }

    @Override
    public void withdraw(Long userId, double amount) {
        User user = userRepository.findById(userId).orElse(null);

        if (user != null) {
            double currentBalance = user.getBalance();

            // Bakiyenin negatif olmaması
            if (currentBalance >= amount) {
                user.setBalance(currentBalance - amount);
                userRepository.save(user);
            }
        }
    }
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void registerUser(User user, String role) {
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getUsersByRole(String role) {
        return userRepository.findByRole(role);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void updateUser(Long id, User updatedUser) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setUsername(updatedUser.getUsername());
            user.setPassword(updatedUser.getPassword());
            user.setBalance(updatedUser.getBalance());
            userRepository.save(user);
        }
    }
    @Override
    public void addBalance(String username, double amount) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            double currentBalance = user.getBalance();
            user.setBalance(currentBalance + amount);
            userRepository.save(user);
        }
    }

    @Override
    public void removeBalance(String username, double amount) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            double currentBalance = user.getBalance();
            if (currentBalance >= amount) {
                user.setBalance(currentBalance - amount);
                userRepository.save(user);
            }
        }
    }
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(Collections.emptyList())
                .build();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @Override
    public List<User> getAllBankers() {
        return userRepository.findByRole("banker");
    }
}
