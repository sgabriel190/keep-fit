package com.example.plan_service.business.interfaces;

import com.example.plan_service.presentation.http.Response;
import com.example.plan_service.presentation.requests.UserPlanRequest;

public interface PlanServiceInterface {
    Response<Object> getPlan(Integer idUser);
    Response<Object> deletePlan(Integer idUser);
    Response<Object> createUserPlan(Integer idUser, UserPlanRequest data);
}
