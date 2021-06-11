package com.example.plan_service.persistence.pojo;

import java.util.List;

public final class MealModel {
    private final List<MealRecipeModel> mealRecipe;
    private final String timeOfDay;

    public MealModel(List<MealRecipeModel> mealRecipe, String timeOfDay){
        this.mealRecipe = mealRecipe;
        this.timeOfDay = timeOfDay;
    }

    public List<MealRecipeModel> getMealRecipe() {
        return mealRecipe;
    }

    public String getTimeOfDay() {
        return timeOfDay;
    }
}
