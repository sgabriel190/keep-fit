package com.example.plan_service.persistence.entities;

import com.example.plan_service.persistence.pojo.MealRecipeModel;
import com.example.plan_service.persistence.pojo.PlanModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "meal_recipe")
public class MealRecipeEntity {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "ID_meal", nullable = false)
    private Integer idMeal;

    @Column(name = "ID_recipe", nullable = false)
    private Integer idRecipe;

    public Integer getIdRecipe() {
        return idRecipe;
    }

    public void setIdRecipe(Integer idRecipe) {
        this.idRecipe = idRecipe;
    }

    public Integer getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(Integer idMeal) {
        this.idMeal = idMeal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MealRecipeModel toMealRecipeModel() {
        return new MealRecipeModel(this.idMeal, this. idRecipe);
    }
}
