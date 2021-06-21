package com.example.plan_service.persistence.entities;

import com.example.plan_service.persistence.pojo.MealModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "meal")
public class MealEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "time_of_day")
    private String timeOfDay;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_meal")
    private final List<MealRecipeEntity> recipes = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_menu")
    private MenuEntity menu;

    public void addMealRecipeEntity(MealRecipeEntity data) {
        this.recipes.add(data);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setTime(String timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public String getTime() {
        return this.timeOfDay;
    }

    public MealModel toMealModel(){
        return new MealModel(
                this.recipes.stream()
                        .map(MealRecipeEntity::toMealRecipeModel)
                        .collect(Collectors.toList()),
                this.timeOfDay
        );
    }

    public MenuEntity getMenu() {
        return menu;
    }

    public void setMenu(MenuEntity menu) {
        this.menu = menu;
    }
}