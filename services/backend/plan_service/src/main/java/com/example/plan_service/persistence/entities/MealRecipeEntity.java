package com.example.plan_service.persistence.entities;

import com.example.plan_service.persistence.pojo.MealRecipeModel;

import javax.persistence.*;

@Entity
@Table(name = "meal_recipe",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"ID_meal", "ID_recipe"})})
public class MealRecipeEntity {
    @EmbeddedId
    private MealRecipeEntityPK id;

    public MealRecipeModel toMealRecipeModel() {
        return new MealRecipeModel(this.id.getIdRecipe());
    }

    public void setId(MealRecipeEntityPK id) {
        this.id = id;
    }
}
