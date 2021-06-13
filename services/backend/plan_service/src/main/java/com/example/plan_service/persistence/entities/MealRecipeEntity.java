package com.example.plan_service.persistence.entities;

import com.example.plan_service.persistence.pojo.MealRecipeModel;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "meal_recipe",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"ID_meal", "ID_recipe"})})
public class MealRecipeEntity {
    @EmbeddedId
    private MealRecipeEntityPK id;

    public MealRecipeModel toMealRecipeModel() {
        return new MealRecipeModel(this.id.getMealId(), this.id.getIdRecipe());
    }

    public void setId(MealRecipeEntityPK id) {
        this.id = id;
    }
}
