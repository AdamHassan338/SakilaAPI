package com.example.sakila.input;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


import static com.example.sakila.input.ValidationGroup.*;

@Data
public class CategoryInput {

    @NotNull(groups = {Create.class})
    @Size(min =1,max=25)
    private String name = new String();

    public CategoryInput(String name){
        this.name = name;
    }

}
