package com.example.plan_service.persistence.pojo;

import java.util.List;

public final class PlanModel {
    private final Integer idUser;
    private final Integer planDays;
    private final List<MenuModel> menus;
    private final String description;

    public PlanModel(Integer idUser, Integer planDays, String description, List<MenuModel> menus){
        this.idUser = idUser;
        this.planDays = planDays;
        this.description = description;
        this.menus = menus;
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

    public List<MenuModel> getMenus() {
        return menus;
    }
}
