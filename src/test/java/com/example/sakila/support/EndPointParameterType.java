package com.example.sakila.support;

import com.example.sakila.support.EndPoint;
import io.cucumber.java.ParameterType;
public class EndPointParameterType {
    @ParameterType("(/api/\\w+)(/(\\d+))?")
    public EndPoint endpoint(String name, String id) {
        if(id==null)
            return new EndPoint(name,null);
        return new EndPoint(name+"/",Integer.valueOf(id.substring(1)));
    }
}
