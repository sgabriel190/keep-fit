package com.example.plan_service.persistence.repositories;

import com.example.plan_service.persistence.entities.PlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository<PlanEntity, Integer> {
    PlanEntity getByIdUser(Integer idUser);
}
