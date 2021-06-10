package com.example.plan_service.persistence.entities;

import com.example.plan_service.persistence.pojo.MealModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "meal")
public class MealEntity {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "ID_menu", nullable = false)
    private Integer idMenu;

    @Column(name = "time_of_day", nullable = false)
    private String timeOfDay;

    public MealEntity(Integer id, Integer idMenu, String timeOfDay){
        super();
        this.id = id;
        this.idMenu = idMenu;
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

    public void setIdMenu(Integer id) {
        this.idMenu = id;
    }

    public Integer getIdMenu() {
        return this.idMenu;
    }

    public void setTime(String timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public String getTime() {
        return this.timeOfDay;
    }

    public MealModel toMealModel(){
        return new MealModel(this.idMenu, this.timeOfDay);
    }
}