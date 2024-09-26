package dzholdoshbaev.laboratory.annotations;


import dzholdoshbaev.laboratory.annotations.impl.UniqueLoginValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueLoginValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueLogin {
    String message() default "Sorry this Login is already taken , you can use a different Login";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
