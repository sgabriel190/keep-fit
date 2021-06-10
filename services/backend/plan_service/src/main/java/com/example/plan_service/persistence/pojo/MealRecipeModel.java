package com.example.plan_service.persistence.pojo;

public final class MealRecipeModel {
    private final Integer idMeal;
    private final Integer idRecipe;

    public MealRecipeModel(Integer idMeal, Integer idRecipe){
        this.idMeal = idMeal;
        this.idRecipe = idRecipe;
    }

    public Integer getIdMeal() {
        return idMeal;
    }

    public Integer getIdRecipe() {
        return idRecipe;
    }

}
