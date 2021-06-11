package com.example.plan_service.presentation.requests;

public final class RecipeRequest {
    private final Integer idRecipe;

    public RecipeRequest(Integer idRecipe) {
        this.idRecipe = idRecipe;
    }

    public Integer getIdRecipe() {
        return idRecipe;
    }
}
