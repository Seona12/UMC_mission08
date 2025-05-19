package com.example.demo.validation.annotation;

import com.example.demo.validation.validator.MissionNotChallengedValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = MissionNotChallengedValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MissionNotChallenged {
    String message() default "MISSION_ALREADY_IN_PROGRESS";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}