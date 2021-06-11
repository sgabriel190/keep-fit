package com.example.plan_service.presentation.controllers;

import com.example.plan_service.business.interfaces.PlanServiceInterface;
import com.example.plan_service.persistence.repositories.MealRepository;
import com.example.plan_service.presentation.http.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
}
