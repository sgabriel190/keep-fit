package com.example.plan_service.business.services;

import com.example.plan_service.business.interfaces.PlanServiceInterface;
import com.example.plan_service.persistence.entities.MealEntity;
import com.example.plan_service.persistence.entities.MealRecipeEntity;
import com.example.plan_service.persistence.entities.MenuEntity;
import com.example.plan_service.persistence.entities.PlanEntity;
import com.example.plan_service.persistence.pojo.MealModel;
import com.example.plan_service.persistence.pojo.MealRecipeModel;
import com.example.plan_service.persistence.pojo.MenuModel;
import com.example.plan_service.persistence.pojo.PlanModel;
import com.example.plan_service.persistence.repositories.MealRecipeRepository;
import com.example.plan_service.persistence.repositories.MealRepository;
import com.example.plan_service.persistence.repositories.MenuRepository;
import com.example.plan_service.persistence.repositories.PlanRepository;
import com.example.plan_service.presentation.http.Response;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanService implements PlanServiceInterface {

    private final MealRecipeRepository mealRecipeRepository;
    private final MealRepository mealRepository;
    private final MenuRepository menuRepository;
    private final PlanRepository planRepository;

    public PlanService(MealRecipeRepository mealRecipeRepository,
                       MealRepository mealRepository,
                       MenuRepository menuRepository,
                       PlanRepository planRepository) {
        this.mealRecipeRepository = mealRecipeRepository;
        this.mealRepository = mealRepository;
        this.menuRepository = menuRepository;
        this.planRepository = planRepository;
    }

    @Override
    public final Response<List<MealModel>> getMeals(){
        try {
            List<MealModel> result = mealRepository.findAll()
                    .stream()
                    .map(MealEntity::toMealModel)
                    .collect(Collectors.toList());
            return new Response<>(true, 200, result, "", "");
        }
        catch(Throwable t){
            return new Response<>(false, 400, null, "", t.toString());
        }
    }

    @Override
    public final Response<List<PlanModel>> getPlans() {
        try{
            List<PlanModel> result = planRepository.findAll()
                    .stream()
                    .map(PlanEntity::toPlanModel)
                    .collect(Collectors.toList());
            return new Response<>(true, 200, result, "", "");
        }
        catch (Throwable t){
            return new Response<>(false, 400, null, "", t.toString());
        }
    }

    @Override
    public final Response<List<MenuModel>> getMenus() {
        try{
            List<MenuModel> result = menuRepository.findAll()
                    .stream()
                    .map(MenuEntity::toMenuModel)
                    .collect(Collectors.toList());
            return new Response<>(true, 200, result, "", "");
        }
        catch (Throwable t){
            return new Response<>(false, 400, null, "", t.toString());
        }
    }

    @Override
    public final Response<List<MealRecipeModel>> getMealRecipes() {
        try{
            List<MealRecipeModel> result = mealRecipeRepository.findAll()
                    .stream()
                    .map(MealRecipeEntity::toMealRecipeModel)
                    .collect(Collectors.toList());
            return new Response<>(true, 200, result, "", "");
        }
        catch (Throwable t){
            return new Response<>(false, 400, null, "", t.toString());
        }
    }
}
