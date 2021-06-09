package com.example.plan_service.persistence.controllers;

import com.example.plan_service.presentation.pojo.TestModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/plan")
public class PlanController {


    @GetMapping("/ping")
    @ResponseStatus(HttpStatus.OK)
    public void ping(){
    }

    @PostMapping("/test")
    @ResponseStatus(HttpStatus.CREATED)
    public TestModel test(){
        return new TestModel(1, "test");
    }
}
