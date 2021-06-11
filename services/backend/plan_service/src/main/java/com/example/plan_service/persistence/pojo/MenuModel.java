package com.example.plan_service.persistence.pojo;

import java.util.List;

public final class MenuModel {
    private final List<MealModel> meals;
    private final String day;

    public MenuModel(List<MealModel> meals, String day){
        this.meals = meals;
        this.day = day;
    }

    public List<MealModel> getPlans() {
        return meals;
    }

    public String getDay() {
        return day;
    }
}
