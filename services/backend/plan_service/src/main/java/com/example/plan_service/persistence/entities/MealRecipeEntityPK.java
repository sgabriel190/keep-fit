package com.example.plan_service.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class MealRecipeEntityPK implements Serializable {
    @Column(name = "ID_meal")
    private Integer mealId;

    @Column(name = "ID_recipe")
    private Integer idRecipe;

    public Integer getMealId() {
        return mealId;
    }

    public void setMealId(Integer meal) {
        this.mealId = meal;
    }

    public Integer getIdRecipe() {
        return idRecipe;
    }

    public void setIdRecipe(Integer idRecipe) {
        this.idRecipe = idRecipe;
    }
}

