package com.example.plan_service.presentation.requests;

import java.util.List;

public final class RecipeRequest {
    private List<Integer> recipesId;

    public RecipeRequest() {

    }

    public RecipeRequest(List<Integer> recipesId) {
        this.recipesId = recipesId;
    }

    public List<Integer> getRecipesId() {
        return recipesId;
    }

    public void setRecipesId(List<Integer> recipesId) {
        this.recipesId = recipesId;
    }
}
