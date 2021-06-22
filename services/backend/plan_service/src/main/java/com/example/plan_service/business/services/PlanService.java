package com.example.plan_service.business.services;

import com.example.plan_service.business.interfaces.PlanServiceInterface;
import com.example.plan_service.persistence.entities.*;
import com.example.plan_service.persistence.pojo.PlanModel;
import com.example.plan_service.persistence.repositories.PlanRepository;
import com.example.plan_service.presentation.http.Response;
import com.example.plan_service.presentation.requests.RecipeRequest;
import com.example.plan_service.presentation.requests.UserPlanRequest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlanService implements PlanServiceInterface {
    private final PlanRepository planRepository;
    private final List<String> timeOfDay = new ArrayList<>();

    public PlanService(PlanRepository planRepository) {
        this.planRepository = planRepository;
        timeOfDay.add(0, "Breakfast");
        timeOfDay.add(1, "Lunch");
        timeOfDay.add(2, "Dinner");
    }

    @Override
    public Response<Object> getPlan(Integer idUser) {
        try {
            PlanEntity result = planRepository.getByIdUser(idUser);
            if (result == null){
                throw new NullPointerException("The user plan is not created yet.");
            }
            return new Response<>(true, 200, result.toPlanModel(), "", "");
        }
        catch (Throwable t){
            return new Response<>(false, 400, null, "", t.getMessage());
        }
    }

    @Override
    public Response<Object> deletePlan(Integer idUser) {
        try {
            PlanEntity entity = planRepository.getByIdUser(idUser);
            planRepository.delete(entity);
            return new Response<>(true, 204, null, "", "");
        }
        catch (InvalidDataAccessApiUsageException e){
            return new Response<>(false, 404, null, "", e.getMessage());
        }
        catch (Throwable t){
            return new Response<>(false, 400, null, "", t.getMessage());
        }
    }

    @Override
    public Response<Object> createUserPlan(Integer idUser, UserPlanRequest data) {
        try{
            if (data.getMenus().size() != data.getPlanDays()){
                throw new Exception("The number of menus for the plan is insufficient.");
            }
            PlanEntity planEntity = new PlanEntity();
            planEntity.setIdUser(idUser);
            planEntity.setDescription(data.getDescription());
            planEntity.setPlanDays(data.getPlanDays());

            for(int day = 0; day < data.getPlanDays(); day++){
                MenuEntity menuEntity = new MenuEntity();
                menuEntity.setPlan(planEntity);
                menuEntity.setDay("Day " + data.getMenus().get(day).getDay());
                planEntity.addMenu(menuEntity);
                for (int token = 0; token < timeOfDay.size(); token ++) {
                    if (data.getMenus().get(day).getRecipes().size() != 3){
                        throw new Exception("The number of menus for the plan is insufficient.");
                    }
                    RecipeRequest tmp = data.getMenus().get(day).getRecipes().get(token);
                    MealEntity mealEntity = new MealEntity();
                    mealEntity.setMenu(menuEntity);
                    mealEntity.setTime(timeOfDay.get(token));
                    menuEntity.addMeal(mealEntity);

                    MealRecipeEntityPK mealRecipeEntityPK = new MealRecipeEntityPK();
                    for (Integer idRecipe: tmp.getRecipesId()){
                        mealRecipeEntityPK.setIdRecipe(idRecipe);
                        mealRecipeEntityPK.setMeal(mealEntity);
                        MealRecipeEntity mealRecipeEntity = new MealRecipeEntity();
                        mealRecipeEntity.setId(mealRecipeEntityPK);
                        mealEntity.addMealRecipeEntity(mealRecipeEntity);
                    }
                }
            }
            planRepository.save(planEntity);
            PlanModel result = planRepository.getByIdUser(idUser).toPlanModel();
            return new Response<>(true, 201, result, "", "");
        } catch (JpaSystemException e){
            return new Response<>(false, 400, null, "", e.getMessage());
        }
        catch (Throwable t){
            return new Response<>(false, 400, null, "", t.getMessage());
        }

    }
}
