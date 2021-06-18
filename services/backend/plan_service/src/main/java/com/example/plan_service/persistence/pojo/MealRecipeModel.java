package com.example.plan_service.persistence.pojo;

public final class MealRecipeModel {
    private final Integer mealId;
    private final Integer idRecipe;

    public MealRecipeModel(Integer mealId, Integer idRecipe){
        this.mealId = mealId;
        this.idRecipe = idRecipe;
    }

    public Integer getMealId() {
        return mealId;
    }

    public Integer getIdRecipe() {
        return idRecipe;
    }

}
