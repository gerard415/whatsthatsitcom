package com.whatsthatsitcom.service;

import com.whatsthatsitcom.model.User;
import com.whatsthatsitcom.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    // Constructor-based injection
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Save (used for registering a new user)
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    // Get user by ID (returns Optional)
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Get user by email (custom method defined in the repo)
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Save updates (e.g., watchlist change)
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
