package com.example.plan_service.business.services;

import com.example.plan_service.business.interfaces.PlanServiceInterface;
import com.example.plan_service.persistence.entities.*;
import com.example.plan_service.persistence.pojo.MealModel;
import com.example.plan_service.persistence.pojo.MealRecipeModel;
import com.example.plan_service.persistence.pojo.MenuModel;
import com.example.plan_service.persistence.pojo.PlanModel;
import com.example.plan_service.persistence.repositories.MealRecipeRepository;
import com.example.plan_service.persistence.repositories.MealRepository;
import com.example.plan_service.persistence.repositories.MenuRepository;
import com.example.plan_service.persistence.repositories.PlanRepository;
import com.example.plan_service.presentation.http.Response;
import com.example.plan_service.presentation.requests.RecipeRequest;
import com.example.plan_service.presentation.requests.UserPlanRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanService implements PlanServiceInterface {
    private final MealRecipeRepository mealRecipeRepository;
    private final MealRepository mealRepository;
    private final MenuRepository menuRepository;
    private final PlanRepository planRepository;
    private final List<String> timeOfDay = new ArrayList<>();

    public PlanService(MealRecipeRepository mealRecipeRepository,
                       MealRepository mealRepository,
                       MenuRepository menuRepository,
                       PlanRepository planRepository) {
        this.mealRecipeRepository = mealRecipeRepository;
        this.mealRepository = mealRepository;
        this.menuRepository = menuRepository;
        this.planRepository = planRepository;
        timeOfDay.add(0, "Breakfast");
        timeOfDay.add(1, "Lunch");
        timeOfDay.add(2, "Dinner");
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
    public Response<PlanModel> getPlan(Integer idUser) {
        try {
            PlanEntity result = planRepository.getByIdUser(idUser);
            if (result == null){
                throw new NullPointerException("The user plan is not created yet.");
            }
            return new Response<>(true, 200, result.toPlanModel(), "", "");
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

    @Override
    public Response<PlanModel> createUserPlan(Integer idUser, UserPlanRequest data) {
        try{
            if (data.getMenus().size() != data.getPlanDays()){
                throw new Exception("The number of menus for the plan is insufficient.");
            }
            PlanEntity planEntity = new PlanEntity();
            planEntity.setIdUser(idUser);
            planEntity.setDescription(data.getDescription());
            planEntity.setPlanDays(data.getPlanDays());
            planRepository.save(planEntity);

            for(int day = 0; day < data.getPlanDays(); day++){
                MenuEntity menuEntity = new MenuEntity();
                menuEntity.setIdPlan(planEntity.getId());
                menuEntity.setDay("Day " + data.getMenus().get(day).getDay());
                menuRepository.save(menuEntity);
                for (int token = 0; token < timeOfDay.size(); token ++) {
                    if (data.getMenus().get(day).getRecipes().size() != 3){
                        throw new Exception("The number of menus for the plan is insufficient.");
                    }
                    RecipeRequest tmp = data.getMenus().get(day).getRecipes().get(token);
                    MealEntity mealEntity = new MealEntity();
                    mealEntity.setIdMenu(menuEntity.getId());
                    mealEntity.setTime(timeOfDay.get(token));
                    mealRepository.save(mealEntity);

                    MealRecipeEntityPK mealRecipeEntityPK = new MealRecipeEntityPK();
                    for (Integer idRecipe: tmp.getRecipesId()){
                        mealRecipeEntityPK.setIdRecipe(idRecipe);
                        mealRecipeEntityPK.setMealId(mealEntity.getId());
                        MealRecipeEntity mealRecipeEntity = new MealRecipeEntity();
                        mealRecipeEntity.setId(mealRecipeEntityPK);
                        mealRecipeRepository.save(mealRecipeEntity);
                    }
                }
            }
            PlanModel result = planRepository.findById(planEntity.getId()).get().toPlanModel();
            return new Response<>(true, 201, result, "", "");
        }
        catch (Throwable t){
            return new Response<>(false, 400, null, "", t.toString());
        }

    }
}
