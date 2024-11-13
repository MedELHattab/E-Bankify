package org.example.ebankify.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.ebankify.annotation.UniqueEmail;
import org.example.ebankify.repository.UserManagementRepository;
import org.example.ebankify.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private UserManagementRepository userRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null) {
            return true; // @NotNull will handle null checks if applied on the field
        }
        return !userRepository.existsByEmail(email);
    }
}
