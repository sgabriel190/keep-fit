package com.example.plan_service.persistence.pojo;

public final class MealModel {
    private final Integer menuId;
    private final String timeOfDay;

    public MealModel(Integer menuId, String timeOfDay){
        this.menuId = menuId;
        this.timeOfDay = timeOfDay;
    }

    public Integer getIdMenu() {
        return menuId;
    }

    public String getTimeOfDay() {
        return timeOfDay;
    }
}
