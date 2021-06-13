package com.example.plan_service.persistence.entities;

import com.example.plan_service.persistence.pojo.MenuModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "menu")
public class MenuEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ID_plan")
    private Integer planId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ID_menu")
    private final List<MealEntity> meals = new ArrayList<>();

    @Column(name = "day")
    private String day;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdPlan() {
        return planId;
    }

    public void setIdPlan(Integer idPlan) {
        this.planId = idPlan;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void addMeal(MealEntity mealEntity) {
        meals.add(mealEntity);
    }

    public MenuModel toMenuModel() {
        return new MenuModel(
                this.meals.stream()
                        .map(MealEntity::toMealModel)
                        .collect(Collectors.toList()),
                this.day);
    }
}
