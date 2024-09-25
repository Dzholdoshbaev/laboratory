package dzholdoshbaev.laboratory.annotations.impl;


import dzholdoshbaev.laboratory.annotations.UniqueEmail;
import dzholdoshbaev.laboratory.repository.UsersRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private UsersRepository userRepository;

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null || email.isEmpty()) {
            return true;
        }
        return !userRepository.existsByEmail(email);
    }
}
