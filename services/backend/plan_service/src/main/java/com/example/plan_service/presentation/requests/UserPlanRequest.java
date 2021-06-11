package com.example.plan_service.presentation.requests;

import java.util.List;

public final class UserPlanRequest {
    private final String description;
    private final Integer planDays;
    private final List<RecipeRequest> recipes;

    public UserPlanRequest(String description, Integer planDays, List<RecipeRequest> recipes){
        this.description = description;
        this.planDays = planDays;
        this.recipes = recipes;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPlanDays() {
        return planDays;
    }

    public List<RecipeRequest> getRecipes() {
        return recipes;
    }
}
