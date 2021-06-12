package com.example.plan_service.persistence.entities;

import com.example.plan_service.persistence.pojo.MealModel;

import javax.persistence.*;

@Entity
@Table(name = "meal")
public class MealEntity {
    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "ID_menu")
    private Integer menuId;

    @Column(name = "time_of_day")
    private String timeOfDay;

    public MealEntity(Integer id, Integer menuId, String timeOfDay){
        super();
        this.id = id;
        this.menuId = menuId;
        this.timeOfDay = timeOfDay;
    }

    public MealEntity() {
        super();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setIdMenu(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getIdMenu() {
        return this.menuId;
    }

    public void setTime(String timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public String getTime() {
        return this.timeOfDay;
    }

    public MealModel toMealModel(){
        return new MealModel(this.menuId, this.timeOfDay);
    }
}