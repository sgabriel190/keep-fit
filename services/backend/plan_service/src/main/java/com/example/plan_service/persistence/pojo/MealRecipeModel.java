package com.example.plan_service.persistence.pojo;

import com.example.plan_service.persistence.entities.MealEntity;

public final class MealRecipeModel {
    private final MealModel meal;
    private final Integer idRecipe;

    public MealRecipeModel(MealModel meal, Integer idRecipe){
        this.meal = meal;
        this.idRecipe = idRecipe;
    }

    public MealModel getMeal() {
        return meal;
    }

    public Integer getIdRecipe() {
        return idRecipe;
    }

}
