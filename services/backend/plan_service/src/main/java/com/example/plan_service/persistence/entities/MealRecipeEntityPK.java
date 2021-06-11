package com.example.plan_service.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
class MealRecipeEntityPK implements Serializable {
    @ManyToOne
    @JoinColumn(name = "ID_meal")
    private MealEntity meal;

    @Column(name = "ID_recipe")
    private Integer idRecipe;

    public MealEntity getMeal() {
        return meal;
    }

    public void setMeal(MealEntity meal) {
        this.meal = meal;
    }

    public Integer getIdRecipe() {
        return idRecipe;
    }

    public void setIdRecipe(Integer idRecipe) {
        this.idRecipe = idRecipe;
    }
}

