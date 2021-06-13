package com.example.plan_service.presentation.requests;

import java.util.List;

public final class DailyMenuRequest {
    private final Integer day;
    private final List<RecipeRequest> recipes;

    public DailyMenuRequest(Integer day, List<RecipeRequest> recipes) {
        this.day = day;
        this.recipes = recipes;
    }

    public Integer getDay() {
        return day;
    }

    public List<RecipeRequest> getRecipes() {
        return recipes;
    }
}
