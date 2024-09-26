package dzholdoshbaev.laboratory.annotations.impl;


import dzholdoshbaev.laboratory.annotations.UniqueLogin;
import dzholdoshbaev.laboratory.repository.UsersRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueLoginValidator implements ConstraintValidator<UniqueLogin, String> {
    @Autowired
    private UsersRepository userRepository;

    @Override
    public void initialize(UniqueLogin constraintAnnotation) {
    }

    @Override
    public boolean isValid(String login, ConstraintValidatorContext context) {
        if (login == null || login.isEmpty()) {
            return true;
        }
        return !userRepository.existsByLogin(login);
    }
}
