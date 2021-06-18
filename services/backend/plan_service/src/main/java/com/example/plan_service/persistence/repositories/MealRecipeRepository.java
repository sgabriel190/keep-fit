package com.example.plan_service.persistence.repositories;

import com.example.plan_service.persistence.entities.MealRecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRecipeRepository extends JpaRepository<MealRecipeEntity, Integer> {
}
