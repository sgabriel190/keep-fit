package com.example.plan_service.persistence.repositories;

import com.example.plan_service.persistence.entities.MealEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRepository extends JpaRepository<MealEntity, Integer> {
    @Query(value = "SELECT time_of_day FROM meal WHERE id = ?1", nativeQuery = true)
    String gettime_of_dayById(Integer id);
}
