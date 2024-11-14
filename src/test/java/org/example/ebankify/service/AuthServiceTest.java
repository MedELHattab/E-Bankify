package org.example.ebankify.service;

import org.example.ebankify.model.User;
import org.example.ebankify.repository.UserRepository;
import org.example.ebankify.util.PasswordUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthService authService;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setEmail("test@example.com");
        user.setPassword("plaintextPassword");
        user.setId(1L);
    }

    @Test
    void testRegister() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password123");

        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User savedUser = invocation.getArgument(0);
            savedUser.setId(1L); // Set an ID to simulate saving to DB
            return savedUser;
        });

        User result = authService.register(user);

        assertNotNull(result.getId(), "User ID should be set after registration");
        assertNotEquals("password123", result.getPassword(), "Password should be hashed");
        assertTrue(PasswordUtil.verifyPassword("password123", result.getPassword()), "Stored password should match the hashed input password");
    }


    @Test
    void testAuthenticate_Success() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword(PasswordUtil.hashPassword("password123")); // Ensure the password is hashed

        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));

        User loginUser = new User();
        loginUser.setEmail("test@example.com");
        loginUser.setPassword("password123"); // Use plain password to authenticate

        boolean result = authService.authenticate(loginUser);

        assertTrue(result, "Authentication should be successful with correct password");
    }


    @Test
    public void testAuthenticate_Fail_WrongPassword() {
        // Hashing and storing a different password for the test case
        user.setPassword(PasswordUtil.hashPassword("differentPassword"));

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        // Setting an incorrect password to simulate failure
        User loginAttempt = new User();
        loginAttempt.setEmail("test@example.com");
        loginAttempt.setPassword("wrongPassword");

        boolean isAuthenticated = authService.authenticate(loginAttempt);

        assertFalse(isAuthenticated);
        verify(userRepository, times(1)).findByEmail(user.getEmail());
    }

    @Test
    void testAuthenticate_Fail_UserNotFound() {
        when(userRepository.findByEmail("notfound@example.com")).thenReturn(Optional.empty());

        User loginUser = new User();
        loginUser.setEmail("notfound@example.com");
        loginUser.setPassword("password123");

        boolean result = authService.authenticate(loginUser);

        assertFalse(result, "Authentication should fail when user is not found");
    }
}

