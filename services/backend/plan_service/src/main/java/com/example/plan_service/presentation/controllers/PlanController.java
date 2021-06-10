package com.example.plan_service.presentation.controllers;

import com.example.plan_service.persistence.entities.MealEntity;
import com.example.plan_service.persistence.repositories.PlanRepository;
import com.example.plan_service.presentation.http.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plan")
public class PlanController {
    @Autowired
    private PlanRepository planRepository;

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void ping(){
    }

    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.CREATED)
    public Response<String> test2(){
        String result = planRepository.gettime_of_dayById(1);
        return new Response<>(true, 200, result, "", "");
    }
}
