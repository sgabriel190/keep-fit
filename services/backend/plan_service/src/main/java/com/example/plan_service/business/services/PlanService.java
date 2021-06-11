package com.example.plan_service.business.services;

import com.example.plan_service.business.interfaces.PlanServiceInterface;
import com.example.plan_service.persistence.repositories.MealRecipeRepository;
import com.example.plan_service.persistence.repositories.MealRepository;
import com.example.plan_service.persistence.repositories.MenuRepository;
import com.example.plan_service.persistence.repositories.PlanRepository;
import org.springframework.stereotype.Service;

@Service
public class PlanService implements PlanServiceInterface {

    private final MealRecipeRepository mealRecipeRepository;
    private final MealRepository mealRepository;
    private final MenuRepository menuRepository;
    private final PlanRepository planRepository;

    public PlanService(MealRecipeRepository mealRecipeRepository,
                       MealRepository mealRepository,
                       MenuRepository menuRepository,
                       PlanRepository planRepository) {
        this.mealRecipeRepository = mealRecipeRepository;
        this.mealRepository = mealRepository;
        this.menuRepository = menuRepository;
        this.planRepository = planRepository;
    }
}
