package org.example.ebankify.service;

import org.example.ebankify.model.User;
import org.example.ebankify.repository.UserRepository;
import org.example.ebankify.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        User user = userRepository.findByEmail(u.getEmail()).get();
        return user != null && PasswordUtil.verifyPassword(u.getPassword(), user.getPassword());
    }
}
