package com.example.plan_service.presentation.controllers;

import com.example.plan_service.business.interfaces.PlanServiceInterface;
import com.example.plan_service.persistence.pojo.MealModel;
import com.example.plan_service.persistence.pojo.MealRecipeModel;
import com.example.plan_service.persistence.pojo.MenuModel;
import com.example.plan_service.persistence.pojo.PlanModel;
import com.example.plan_service.persistence.repositories.MealRepository;
import com.example.plan_service.presentation.http.MyError;
import com.example.plan_service.presentation.http.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/plan")
public class PlanController {
    private final MealRepository mealRepository;

    private final PlanServiceInterface planService;

    public PlanController(MealRepository mealRepository, PlanServiceInterface planService) {
        this.mealRepository = mealRepository;
        this.planService = planService;
    }

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Response<Object> ping(){
        return new Response<>(true, 200, null, "", "");
    }

    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.CREATED)
    public Response<String> test2(){
        String result = mealRepository.gettime_of_dayById(1);
        return new Response<>(true, 200, result, "", "");
    }

    @RequestMapping(value = "/meal", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> getMeals(){
        Response<List<MealModel>> result = planService.getMeals();
        if (result.getSuccessfulOperation()){
            return new ResponseEntity<>(result, HttpStatus.valueOf(result.getCode()));
        }
        else{
            MyError tmp = new MyError(result.getCode(), result.getMessage());
            return new ResponseEntity<>(tmp, HttpStatus.valueOf(tmp.getCode()));
        }
    }

    @RequestMapping(value = "/plan", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> getPlans(){
        Response<List<PlanModel>> result = planService.getPlans();
        if (result.getSuccessfulOperation()){
            return new ResponseEntity<>(result, HttpStatus.valueOf(result.getCode()));
        }
        else{
            MyError tmp = new MyError(result.getCode(), result.getMessage());
            return new ResponseEntity<>(tmp, HttpStatus.valueOf(tmp.getCode()));
        }
    }

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> getMenus(){
        Response<List<MenuModel>> result = planService.getMenus();
        if (result.getSuccessfulOperation()){
            return new ResponseEntity<>(result, HttpStatus.valueOf(result.getCode()));
        }
        else{
            MyError tmp = new MyError(result.getCode(), result.getMessage());
            return new ResponseEntity<>(tmp, HttpStatus.valueOf(tmp.getCode()));
        }
    }

    @RequestMapping(value = "/mealRecipe", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> getMealRecipe(){
        Response<List<MealRecipeModel>> result = planService.getMealRecipes();
        if (result.getSuccessfulOperation()){
            return new ResponseEntity<>(result, HttpStatus.valueOf(result.getCode()));
        }
        else{
            MyError tmp = new MyError(result.getCode(), result.getMessage());
            return new ResponseEntity<>(tmp, HttpStatus.valueOf(tmp.getCode()));
        }
    }
}
