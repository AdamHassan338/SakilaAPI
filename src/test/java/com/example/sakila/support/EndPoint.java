package com.example.sakila.support;

public class EndPoint {
    public String mapping;
    public Integer id;

    public EndPoint(String mapping, Integer id){
        this.mapping = mapping;
        this.id= id;
    }

    public String full(){
        if(this.id==null)
            return this.mapping;
        else
            return mapping + id.toString();
    }
}
