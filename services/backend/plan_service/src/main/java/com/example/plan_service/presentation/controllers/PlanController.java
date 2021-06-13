package com.example.plan_service.presentation.controllers;

import com.example.plan_service.business.interfaces.PlanServiceInterface;
import com.example.plan_service.persistence.pojo.MealModel;
import com.example.plan_service.persistence.pojo.MealRecipeModel;
import com.example.plan_service.persistence.pojo.MenuModel;
import com.example.plan_service.persistence.pojo.PlanModel;
import com.example.plan_service.persistence.repositories.MealRepository;
import com.example.plan_service.presentation.http.MyError;
import com.example.plan_service.presentation.http.Response;
import com.example.plan_service.presentation.requests.UserPlanRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planning")
public class PlanController {
    private final PlanServiceInterface planService;

    public PlanController(PlanServiceInterface planService) {
        this.planService = planService;
    }

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public ResponseEntity<Object> ping(){
        Response<Object> result = new Response<>(true, 200, null, "", "");
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getCode()));
    }

    @RequestMapping(value = "/meal", method = RequestMethod.GET)
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

    @RequestMapping(value = "/plan/user/{idUser}", method = RequestMethod.POST)
    public ResponseEntity<Object> addUserPlan(@PathVariable Integer idUser, @RequestBody UserPlanRequest data){
        Response<PlanModel> result = planService.createUserPlan(idUser, data);
        if (result.getSuccessfulOperation()){
            return new ResponseEntity<>(result, HttpStatus.valueOf(result.getCode()));
        }
        else{
            MyError tmp = new MyError(result.getCode(), result.getMessage());
            return new ResponseEntity<>(tmp, HttpStatus.valueOf(tmp.getCode()));
        }
    }

    @RequestMapping(value = "/plan/user/{idUser}", method = RequestMethod.GET)
    public ResponseEntity<Object> getPlan(@PathVariable Integer idUser){
        Response<PlanModel> result = planService.getPlan(idUser);
        if (result.getSuccessfulOperation()){
            return new ResponseEntity<>(result, HttpStatus.valueOf(result.getCode()));
        }
        else{
            MyError tmp = new MyError(result.getCode(), result.getMessage());
            return new ResponseEntity<>(tmp, HttpStatus.valueOf(tmp.getCode()));
        }
    }

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
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
