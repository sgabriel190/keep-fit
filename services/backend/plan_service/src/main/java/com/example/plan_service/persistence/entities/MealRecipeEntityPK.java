package com.example.plan_service.persistence.entities;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class MealRecipeEntityPK implements Serializable {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_meal")
    private MealEntity meal;

    @Column(name = "ID_recipe")
    private Integer idRecipe;

    public Integer getMealId() {
        return meal.getId();
    }

    public void setMeal(MealEntity meal) {
        this.meal = meal;
    }

    public MealEntity getMeal() {
        return this.meal;
    }

    public Integer getIdRecipe() {
        return idRecipe;
    }

    public void setIdRecipe(Integer idRecipe) {
        this.idRecipe = idRecipe;
    }
}

