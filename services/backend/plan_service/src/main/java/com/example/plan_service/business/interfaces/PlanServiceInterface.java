package com.example.plan_service.business.interfaces;

import com.example.plan_service.persistence.pojo.MealModel;
import com.example.plan_service.persistence.pojo.MealRecipeModel;
import com.example.plan_service.persistence.pojo.MenuModel;
import com.example.plan_service.persistence.pojo.PlanModel;
import com.example.plan_service.presentation.http.Response;

import java.util.List;

public interface PlanServiceInterface {
    Response<List<MealModel>> getMeals();
    Response<List<PlanModel>> getPlans();
    Response<List<MenuModel>> getMenus();
    Response<List<MealRecipeModel>> getMealRecipes();
}
