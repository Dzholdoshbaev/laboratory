package dzholdoshbaev.laboratory.annotations;

import dzholdoshbaev.laboratory.annotations.impl.UniqueEmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueEmailValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmail {
    String message() default "Sorry this email is already taken , you can use a different email address";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
