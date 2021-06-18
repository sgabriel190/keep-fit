package com.example.plan_service.persistence.repositories;

import com.example.plan_service.persistence.entities.MealEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRepository extends JpaRepository<MealEntity, Integer> {
}
