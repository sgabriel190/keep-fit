package com.example.plan_service.persistence.pojo;

public final class MealModel {
    private final Integer idMenu;
    private final String timeOfDay;

    public MealModel(Integer idMenu, String timeOfDay){
        this.idMenu = idMenu;
        this.timeOfDay = timeOfDay;
    }

    public Integer getIdMenu() {
        return idMenu;
    }

    public String getTimeOfDay() {
        return timeOfDay;
    }
}
