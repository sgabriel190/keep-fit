package com.example.plan_service.presentation.http;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class MyError {
    private final Integer code;
    private final String error;
    private final String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

    public MyError(Integer code, String error){
        this.code = code;
        this.error = error;
    }

    public Integer getCode() {
        return code;
    }

    public String getError() {
        return error;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
