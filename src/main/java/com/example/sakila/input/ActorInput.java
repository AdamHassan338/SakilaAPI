package com.example.sakila.input;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import static com.example.sakila.input.ValidationGroup.*;

@Data
public class ActorInput {

    @NotNull(groups = {Create.class})
    @Size(min =1,max=45)
    private final String firstName;

    @NotNull(groups = {Create.class})
    @Size(min =1,max=45)
    private final String lastName;


    public ActorInput(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }



}
