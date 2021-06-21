package com.example.plan_service.presentation.controllers;

import com.example.plan_service.business.interfaces.PlanServiceInterface;
import com.example.plan_service.presentation.http.MyError;
import com.example.plan_service.presentation.http.Response;
import com.example.plan_service.presentation.requests.UserPlanRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/planning")
public class PlanController {
    private final PlanServiceInterface planService;

    public PlanController(PlanServiceInterface planService) {
        this.planService = planService;
    }

    @Async
    @RequestMapping(value = "/plan/user/{idUser}", method = RequestMethod.POST)
    @ResponseBody
    public CompletableFuture<ResponseEntity<Object>> addUserPlan(
            @PathVariable Integer idUser,
            @RequestBody UserPlanRequest data){
        Response<Object> result = planService.createUserPlan(idUser, data);
        if (result.getSuccessfulOperation()){
            return CompletableFuture.completedFuture(new ResponseEntity<>(result, HttpStatus.valueOf(result.getCode())));
        }
        else{
            MyError tmp = new MyError(result.getCode(), result.getMessage());
            return CompletableFuture.completedFuture(new ResponseEntity<>(tmp, HttpStatus.valueOf(tmp.getCode())));
        }
    }

    @Async
    @RequestMapping(value = "/plan/user/{idUser}", method = RequestMethod.GET)
    @ResponseBody
    public CompletableFuture<ResponseEntity<Object>> getPlan(@PathVariable Integer idUser){
        Response<Object> result = planService.getPlan(idUser);
        if (result.getSuccessfulOperation()){
            return CompletableFuture.completedFuture(new ResponseEntity<>(result, HttpStatus.valueOf(result.getCode())));
        }
        else{
            MyError tmp = new MyError(result.getCode(), result.getMessage());
            return CompletableFuture.completedFuture(new ResponseEntity<>(tmp, HttpStatus.valueOf(tmp.getCode())));
        }
    }

    @Async
    @RequestMapping(value = "/plan/user/{idUser}", method = RequestMethod.DELETE)
    @ResponseBody
    public CompletableFuture<ResponseEntity<Object>> deleteUserPlan(@PathVariable Integer idUser){
        Response<Object> result = planService.deletePlan(idUser);
        if (result.getSuccessfulOperation()){
            return CompletableFuture.completedFuture(new ResponseEntity<>(null, HttpStatus.valueOf(result.getCode())));
        }
        else{
            MyError tmp = new MyError(result.getCode(), result.getMessage());
            return CompletableFuture.completedFuture(new ResponseEntity<>(tmp, HttpStatus.valueOf(tmp.getCode())));
        }
    }
}
