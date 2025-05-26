package com.example.demo.validation.validator;

import com.example.demo.validation.annotation.UserExists;
import com.example.demo.repository.MemberRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserExistsValidator implements ConstraintValidator<UserExists, Long> {

    private final MemberRepository memberRepository;

    @Override
    public boolean isValid(Long memberId, ConstraintValidatorContext context) {
        if (memberId == null) {
            return false;
        }
        return memberRepository.existsById(memberId);
    }
}

