package com.example.plan_service.persistence.pojo;

public final class MenuModel {
    private final Integer idPlan;
    private final String day;

    public MenuModel(Integer idPlan, String day){
        this.idPlan = idPlan;
        this.day = day;
    }

    public Integer getIdPlan() {
        return idPlan;
    }

    public String getDay() {
        return day;
    }
}
