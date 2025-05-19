package com.example.demo.validation.validator;


import com.example.demo.repository.StoreRepository.StoreRepository;
import com.example.demo.validation.annotation.StoreExists;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StoreExistsValidator implements ConstraintValidator<StoreExists, Long> {
    private final StoreRepository storeRepo;
    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return value != null && storeRepo.existsById(value);
    }
}
