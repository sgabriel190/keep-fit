package com.example.plan_service.persistence.pojo;

public final class PlanModel {
    private final Integer idUser;
    private final Integer planDays;
    private final String description;

    public PlanModel(Integer idUser, Integer planDays, String description){
        this.idUser = idUser;
        this.planDays = planDays;
        this.description = description;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPlanDays() {
        return planDays;
    }
}
