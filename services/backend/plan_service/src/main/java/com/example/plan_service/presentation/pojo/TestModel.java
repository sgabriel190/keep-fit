package com.example.plan_service.presentation.pojo;

public class TestModel {
    private final int id;
    private final String content;

    public TestModel(int id, String content){
        this.id = id;
        this.content = content;
    }

    public int getId(){
        return this.id;
    }

    public String getContent(){
        return this.content;
    }
}
