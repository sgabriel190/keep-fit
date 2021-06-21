package com.example.plan_service.presentation.controllers;

import com.example.plan_service.presentation.http.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/planning")
public class HealthController {
    @Async
    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    @ResponseBody
    public CompletableFuture<ResponseEntity<Object>> ping(){
        Response<Object> result = new Response<>(true, 200, null, "", "");
        return CompletableFuture.completedFuture(new ResponseEntity<>(result, HttpStatus.valueOf(result.getCode())));
    }
}
