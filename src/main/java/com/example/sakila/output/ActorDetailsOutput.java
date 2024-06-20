package com.example.sakila.output;

import com.example.sakila.entities.Actor;
import lombok.Getter;

@Getter
public class ActorDetailsOutput {
    private final Short id;
    private final String firstName;
    private final String lastName;


    public ActorDetailsOutput(Actor a){
        this.id = a.getId();
        this.firstName = a.getFirstName();
        this.lastName = a.getLastName();
    }
}
