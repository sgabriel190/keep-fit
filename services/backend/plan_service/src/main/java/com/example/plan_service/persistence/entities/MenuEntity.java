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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_plan")
    private PlanEntity plan;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY,  mappedBy = "menu")
    private final List<MealEntity> meals = new ArrayList<>();

    @Column(name = "day")
    private String day;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PlanEntity getPlan() {
        return plan;
    }

    public void setPlan(PlanEntity plan) {
        this.plan = plan;
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
