package com.example.plan_service.persistence.entities;

import com.example.plan_service.persistence.pojo.MealRecipeModel;
import com.example.plan_service.persistence.pojo.MenuModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "menu")
public class MenuEntity {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "ID_plan", nullable = false)
    private Integer idPlan;

    @Column(name = "day", nullable = false)
    private String day;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(Integer idPlan) {
        this.idPlan = idPlan;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public MenuModel toMenuModel() {
        return new MenuModel(this.idPlan, this.day);
    }
}
