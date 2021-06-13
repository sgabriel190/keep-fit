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

    @Column(name = "ID_menu")
    private Integer menuId;

    @Column(name = "time_of_day")
    private String timeOfDay;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ID_meal")
    private final List<MealRecipeEntity> recipes = new ArrayList<>();

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
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
        return new MealModel(
                this.recipes.stream()
                        .map(MealRecipeEntity::toMealRecipeModel)
                        .collect(Collectors.toList()),
                this.timeOfDay
        );
    }
}