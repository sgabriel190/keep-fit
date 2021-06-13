package com.example.plan_service.presentation.requests;

public final class RecipeRequest {
    private Integer idRecipe;

    public RecipeRequest() {

    }

    public RecipeRequest(Integer idRecipe) {
        this.idRecipe = idRecipe;
    }

    public Integer getIdRecipe() {
        return idRecipe;
    }

    public void setIdRecipe(Integer idRecipe) {
        this.idRecipe = idRecipe;
    }
}
