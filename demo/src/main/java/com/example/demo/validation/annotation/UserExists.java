package com.example.demo.validation.annotation;

import com.example.demo.validation.validator.UserExistsValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UserExistsValidator.class)
@Target({ ElementType.PARAMETER, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UserExists {
    String message() default "MEMBER_NOT_FOUND";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
