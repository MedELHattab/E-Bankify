package org.example.ebankify.service;

import org.example.ebankify.model.User;
import org.example.ebankify.repository.UserRepository;
import org.example.ebankify.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(User user) {
        user.setPassword(PasswordUtil.hashPassword(user.getPassword()));
        return userRepository.save(user);
    }

    public boolean authenticate(User u) {
        Optional<User> optionalUser = userRepository.findByEmail(u.getEmail());
        if (optionalUser.isEmpty()) {
            return false; // Return false if user not found
        }

        User user = optionalUser.get();
        u.setId(user.getId());
        u.setRole(user.getRole());
        return PasswordUtil.verifyPassword(u.getPassword(), user.getPassword());
    }

}
