package com.example.plan_service.presentation.requests;

import java.util.List;

public final class UserPlanRequest {
    private final String description;
    private final Integer planDays;
    private final List<DailyMenuRequest> menus;


    public UserPlanRequest(String description, Integer planDays, List<DailyMenuRequest> menus){
        this.description = description;
        this.planDays = planDays;
        this.menus = menus;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPlanDays() {
        return planDays;
    }

    public List<DailyMenuRequest> getMenus() {
        return menus;
    }
}
