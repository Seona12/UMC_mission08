package com.example.demo.repository;

import com.example.demo.domain.foodCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodCategoryRepository extends JpaRepository<foodCategory, Long> {
}