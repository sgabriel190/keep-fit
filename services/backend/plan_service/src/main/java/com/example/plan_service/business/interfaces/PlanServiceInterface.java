package com.example.plan_service.business.interfaces;

import com.example.plan_service.persistence.pojo.MealModel;
import com.example.plan_service.persistence.pojo.MealRecipeModel;
import com.example.plan_service.persistence.pojo.MenuModel;
import com.example.plan_service.persistence.pojo.PlanModel;
import com.example.plan_service.presentation.http.Response;
import com.example.plan_service.presentation.requests.UserPlanRequest;

import java.util.List;

public interface PlanServiceInterface {
    Response<List<MealModel>> getMeals();
    Response<List<PlanModel>> getPlans();
    Response<PlanModel> getPlan(Integer idUser);
    Response<List<MenuModel>> getMenus();
    Response<List<MealRecipeModel>> getMealRecipes();
    Response<PlanModel> createUserPlan(Integer idUser, UserPlanRequest data);
}
