package com.example.plan_service.presentation.http;

public final class Response<T> {
    private final Boolean successfulOperation;
    private final Integer code;
    private final T data;
    private final String error;
    private final String message;

    public Response(Boolean successfulOperation, Integer code, T data, String error, String message){
        this.successfulOperation = successfulOperation;
        this.code = code;
        this.data = data;
        this.error = error;
        this.message = message;
    }

    public Boolean getSuccessfulOperation() {
        return successfulOperation;
    }

    public Integer getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}
