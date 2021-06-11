package com.example.plan_service.persistence.entities;

import com.example.plan_service.persistence.pojo.PlanModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "plan")
public class PlanEntity {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "ID_user", nullable = false)
    private Integer idUser;

    @Column(name = "plan_days", nullable = false)
    private Integer planDays;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ID_plan")
    private final List<MenuEntity> menus = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getPlanDays() {
        return planDays;
    }

    public void setPlanDays(Integer planDays) {
        this.planDays = planDays;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PlanModel toPlanModel() {
        return new PlanModel(
                this.idUser,
                this.planDays,
                this.description,
                this.menus.stream()
                        .map(MenuEntity::toMenuModel)
                        .collect(Collectors.toList())
        );
    }
}
